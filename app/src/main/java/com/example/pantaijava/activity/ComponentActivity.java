package com.example.pantaijava.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

class CustomComponentActivity extends androidx.core.app.ComponentActivity implements
        SavedStateRegistryOwner,
        ViewModelStoreOwner,
        OnBackPressedDispatcherOwner,
        ActivityResultRegistryOwner,
        ContextAware {

    // Constants
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";

    // Core components
    private ViewModelStore _viewModelStore;
    private final ActivityResultRegistry activityResultRegistry;
    private int contentLayoutId;
    private final ContextAwareHelper contextAwareHelper = new ContextAwareHelper();
    private final androidx.core.view.MenuHostHelper menuHostHelper;
    private final SavedStateRegistryController savedStateRegistryController;
    private final AtomicInteger nextLocalRequestCode = new AtomicInteger();

    // Lifecycle
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    // Listeners
    private final CopyOnWriteArrayList<Consumer<Configuration>> onConfigurationChangedListeners =
            new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Consumer<Integer>> onTrimMemoryListeners =
            new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Consumer<Intent>> onNewIntentListeners =
            new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Consumer<MultiWindowModeChangedInfo>> onMultiWindowModeChangedListeners =
            new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Consumer<PictureInPictureModeChangedInfo>> onPictureInPictureModeChangedListeners =
            new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Runnable> onUserLeaveHintListeners =
            new CopyOnWriteArrayList<>();

    // Flags
    private boolean dispatchingOnMultiWindowModeChanged = false;
    private boolean dispatchingOnPictureInPictureModeChanged = false;

    // Back pressed dispatcher
    private final OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher();

    // ViewModel factory
    private ViewModelProvider.Factory defaultViewModelProviderFactory;

    // Report fully drawn executor
    private final ReportFullyDrawnExecutor reportFullyDrawnExecutor;

    public CustomComponentActivity() {
        super();
        this.savedStateRegistryController = SavedStateRegistryController.create(this);
        this.reportFullyDrawnExecutor = createFullyDrawnExecutor();
        this.activityResultRegistry = new CustomActivityResultRegistry();
        this.menuHostHelper = new androidx.core.view.MenuHostHelper(this::invalidateMenu);
        initialize();
    }

    public CustomComponentActivity(int contentLayoutId) {
        this();
        this.contentLayoutId = contentLayoutId;
    }

    private void initialize() {
        // Setup lifecycle observers
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source,
                                       @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_STOP) {
                    Window window = getWindow();
                    if (window != null) {
                        View peekDecorView = window.peekDecorView();
                        if (peekDecorView != null) {
                            peekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            }
        });

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source,
                                       @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    contextAwareHelper.clearAvailableContext();
                    if (!isChangingConfigurations()) {
                        getViewModelStore().clear();
                    }
                    reportFullyDrawnExecutor.activityDestroyed();
                }
            }
        });

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source,
                                       @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_CREATE) {
                    ensureViewModelStore();
                    getLifecycle().removeObserver(this);
                }
            }
        });

        // Perform saved state registry attach
        savedStateRegistryController.performAttach();
        SavedStateHandleSupport.enableSavedStateHandles(this);

        // Register activity result saved state provider
        getSavedStateRegistry().registerSavedStateProvider(ACTIVITY_RESULT_TAG,
                () -> {
                    Bundle bundle = new Bundle();
                    activityResultRegistry.onSaveInstanceState(bundle);
                    return bundle;
                });

        // Add context available listener
        addOnContextAvailableListener(context -> {
            Bundle savedState = getSavedStateRegistry().consumeRestoredStateForKey(ACTIVITY_RESULT_TAG);
            if (savedState != null) {
                activityResultRegistry.onRestoreInstanceState(savedState);
            }
        });

        // Setup back invoker for API 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source,
                                           @NonNull Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_CREATE) {
                        onBackPressedDispatcher.setOnBackInvokedDispatcher(
                                getOnBackInvokedDispatcher()
                        );
                    }
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        savedStateRegistryController.performRestore(savedInstanceState);
        contextAwareHelper.dispatchOnContextAvailable(this);
        super.onCreate(savedInstanceState);

        androidx.lifecycle.ReportFragment.injectIfNeededIn(this);

        if (contentLayoutId != 0) {
            setContentView(contentLayoutId);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getLifecycle() instanceof LifecycleRegistry) {
            ((LifecycleRegistry) getLifecycle()).setCurrentState(Lifecycle.State.CREATED);
        }
        savedStateRegistryController.performSave(outState);
    }

    @Nullable

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override
    public final Object onRetainNonConfigurationInstance() {
        Object customInstance = onRetainCustomNonConfigurationInstance();
        ViewModelStore viewModelStore = this._viewModelStore;

        if (viewModelStore == null) {
            Object lastNonConfig = getLastNonConfigurationInstance();
            if (lastNonConfig instanceof NonConfigurationInstances) {
                viewModelStore = ((NonConfigurationInstances) lastNonConfig).viewModelStore;
            }
        }

        if (viewModelStore == null && customInstance == null) {
            return null;
        }

        NonConfigurationInstances instances = new NonConfigurationInstances();
        instances.custom = customInstance;
        instances.viewModelStore = viewModelStore;
        return instances;
    }

    @Nullable
    public Object getLastCustomNonConfigurationInstance() {
        Object lastNonConfig = getLastNonConfigurationInstance();
        if (lastNonConfig instanceof NonConfigurationInstances) {
            return ((NonConfigurationInstances) lastNonConfig).custom;
        }
        return null;
    }

    @Override
    public void setContentView(int layoutResID) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(@NonNull View view) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(view);
    }

    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(view, params);
    }

    @Override
    public void addContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        reportFullyDrawnExecutor.viewCreated(decorView);
        super.addContentView(view, params);
    }

    private void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        androidx.lifecycle.ViewTreeLifecycleOwner.set(decorView, this);
        androidx.lifecycle.ViewTreeViewModelStoreOwner.set(decorView, this);
        androidx.savedstate.ViewTreeSavedStateRegistryOwner.set(decorView, this);
        androidx.activity.ViewTreeOnBackPressedDispatcherOwner.set(decorView, this);
    }

    @Nullable
    @Override
    public Context peekAvailableContext() {
        return contextAwareHelper.peekAvailableContext();
    }

    @Override
    public void addOnContextAvailableListener(@NonNull OnContextAvailableListener listener) {
        contextAwareHelper.addOnContextAvailableListener(listener);
    }

    @Override
    public void removeOnContextAvailableListener(@NonNull OnContextAvailableListener listener) {
        contextAwareHelper.removeOnContextAvailableListener(listener);
    }

    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        if (featureId != 0) {
            return true;
        }
        super.onPreparePanel(featureId, view, menu);
        menuHostHelper.onPrepareMenu(menu);
        return true;
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        if (featureId != 0) {
            return true;
        }
        super.onCreatePanelMenu(featureId, menu);
        menuHostHelper.onCreateMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        if (super.onMenuItemSelected(featureId, item)) {
            return true;
        }
        if (featureId == 0) {
            return menuHostHelper.onMenuItemSelected(item);
        }
        return false;
    }

    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        menuHostHelper.onMenuClosed(menu);
        super.onPanelClosed(featureId, menu);
    }

    public void addMenuProvider(@NonNull androidx.core.view.MenuProvider provider) {
        menuHostHelper.addMenuProvider(provider);
    }

    public void addMenuProvider(@NonNull androidx.core.view.MenuProvider provider,
                                @NonNull LifecycleOwner owner) {
        menuHostHelper.addMenuProvider(provider, owner);
    }

    public void addMenuProvider(@NonNull androidx.core.view.MenuProvider provider,
                                @NonNull LifecycleOwner owner,
                                @NonNull Lifecycle.State state) {
        menuHostHelper.addMenuProvider(provider, owner, state);
    }

    public void removeMenuProvider(@NonNull androidx.core.view.MenuProvider provider) {
        menuHostHelper.removeMenuProvider(provider);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException(
                    "Your activity is not yet attached to the Application instance. " +
                            "You can't request ViewModel before onCreate call."
            );
        }

        ensureViewModelStore();
        return _viewModelStore;
    }

    private void ensureViewModelStore() {
        if (_viewModelStore == null) {
            Object lastNonConfig = getLastNonConfigurationInstance();
            if (lastNonConfig instanceof NonConfigurationInstances) {
                _viewModelStore = ((NonConfigurationInstances) lastNonConfig).viewModelStore;
            }

            if (_viewModelStore == null) {
                _viewModelStore = new ViewModelStore();
            }
        }
    }

    @NonNull

    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (defaultViewModelProviderFactory == null) {
            defaultViewModelProviderFactory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        }
        return defaultViewModelProviderFactory;
    }

    @NonNull
    public androidx.lifecycle.viewmodel.CreationExtras getDefaultViewModelCreationExtras() {
        androidx.lifecycle.viewmodel.MutableCreationExtras extras =
                new androidx.lifecycle.viewmodel.MutableCreationExtras();

        if (getApplication() != null) {
            extras.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, getApplication());
        }

        extras.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        extras.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            extras.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, intent.getExtras());
        }

        return extras;
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        onBackPressedDispatcher.onBackPressed();
    }

    @NonNull
    @Override
    public OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return onBackPressedDispatcher;
    }

    @NonNull
    @Override
    public SavedStateRegistry getSavedStateRegistry() {
        return savedStateRegistryController.getSavedStateRegistry();
    }

    @NonNull
    @Override
    public ActivityResultRegistry getActivityResultRegistry() {
        return activityResultRegistry;
    }

    @Deprecated
    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Deprecated
    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode,
                                       @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Deprecated
    @Override
    public void startIntentSenderForResult(@NonNull IntentSender intent, int requestCode,
                                           @Nullable Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags)
            throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
                flagsValues, extraFlags);
    }

    @Deprecated
    @Override
    public void startIntentSenderForResult(@NonNull IntentSender intent, int requestCode,
                                           @Nullable Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags,
                                           @Nullable Bundle options)
            throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
                flagsValues, extraFlags, options);
    }

    @Deprecated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!activityResultRegistry.dispatchResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Deprecated
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (activityResultRegistry.dispatchResult(requestCode, -1,
                new Intent()
                        .putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS, permissions)
                        .putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSION_GRANT_RESULTS, grantResults))) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @NonNull
    public <I, O> ActivityResultLauncher<I> registerForActivityResult(
            @NonNull ActivityResultContract<I, O> contract,
            @NonNull ActivityResultRegistry registry,
            @NonNull ActivityResultCallback<O> callback) {
        return registry.register(
                "activity_rq#" + nextLocalRequestCode.getAndIncrement(),
                this,
                contract,
                callback
        );
    }

    @NonNull
    public <I, O> ActivityResultLauncher<I> registerForActivityResult(
            @NonNull ActivityResultContract<I, O> contract,
            @NonNull ActivityResultCallback<O> callback) {
        return registerForActivityResult(contract, activityResultRegistry, callback);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (Consumer<Configuration> listener : onConfigurationChangedListeners) {
            listener.accept(newConfig);
        }
    }

    public void addOnConfigurationChangedListener(@NonNull Consumer<Configuration> listener) {
        onConfigurationChangedListeners.add(listener);
    }

    public void removeOnConfigurationChangedListener(@NonNull Consumer<Configuration> listener) {
        onConfigurationChangedListeners.remove(listener);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (Consumer<Integer> listener : onTrimMemoryListeners) {
            listener.accept(level);
        }
    }

    public void addOnTrimMemoryListener(@NonNull Consumer<Integer> listener) {
        onTrimMemoryListeners.add(listener);
    }

    public void removeOnTrimMemoryListener(@NonNull Consumer<Integer> listener) {
        onTrimMemoryListeners.remove(listener);
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        for (Consumer<Intent> listener : onNewIntentListeners) {
            listener.accept(intent);
        }
    }

    public void addOnNewIntentListener(@NonNull Consumer<Intent> listener) {
        onNewIntentListeners.add(listener);
    }

    public void removeOnNewIntentListener(@NonNull Consumer<Intent> listener) {
        onNewIntentListeners.remove(listener);
    }

    @Deprecated
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        if (!dispatchingOnMultiWindowModeChanged) {
            for (Consumer<MultiWindowModeChangedInfo> listener : onMultiWindowModeChangedListeners) {
                listener.accept(new MultiWindowModeChangedInfo(isInMultiWindowMode));
            }
        }
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode,
                                         @NonNull Configuration newConfig) {
        dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        } finally {
            dispatchingOnMultiWindowModeChanged = false;
        }

        for (Consumer<MultiWindowModeChangedInfo> listener : onMultiWindowModeChangedListeners) {
            listener.accept(new MultiWindowModeChangedInfo(isInMultiWindowMode, newConfig));
        }
    }

    public void addOnMultiWindowModeChangedListener(@NonNull Consumer<MultiWindowModeChangedInfo> listener) {
        onMultiWindowModeChangedListeners.add(listener);
    }

    public void removeOnMultiWindowModeChangedListener(@NonNull Consumer<MultiWindowModeChangedInfo> listener) {
        onMultiWindowModeChangedListeners.remove(listener);
    }

    @Deprecated
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        if (!dispatchingOnPictureInPictureModeChanged) {
            for (Consumer<PictureInPictureModeChangedInfo> listener : onPictureInPictureModeChangedListeners) {
                listener.accept(new PictureInPictureModeChangedInfo(isInPictureInPictureMode));
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode,
                                              @NonNull Configuration newConfig) {
        dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        } finally {
            dispatchingOnPictureInPictureModeChanged = false;
        }

        for (Consumer<PictureInPictureModeChangedInfo> listener : onPictureInPictureModeChangedListeners) {
            listener.accept(new PictureInPictureModeChangedInfo(isInPictureInPictureMode, newConfig));
        }
    }

    public void addOnPictureInPictureModeChangedListener(@NonNull Consumer<PictureInPictureModeChangedInfo> listener) {
        onPictureInPictureModeChangedListeners.add(listener);
    }

    public void removeOnPictureInPictureModeChangedListener(@NonNull Consumer<PictureInPictureModeChangedInfo> listener) {
        onPictureInPictureModeChangedListeners.remove(listener);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        for (Runnable listener : onUserLeaveHintListeners) {
            listener.run();
        }
    }

    public void addOnUserLeaveHintListener(@NonNull Runnable listener) {
        onUserLeaveHintListeners.add(listener);
    }

    public void removeOnUserLeaveHintListener(@NonNull Runnable listener) {
        onUserLeaveHintListeners.remove(listener);
    }

    @Override
    public void reportFullyDrawn() {
        try {
            if (androidx.tracing.Trace.isEnabled()) {
                androidx.tracing.Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
        } finally {
            androidx.tracing.Trace.endSection();
        }
    }

    private ReportFullyDrawnExecutor createFullyDrawnExecutor() {
        return new ReportFullyDrawnExecutorImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        return getOnBackInvokedDispatcher();
    }

    // Helper classes
    static class NonConfigurationInstances {
        Object custom;
        ViewModelStore viewModelStore;
    }

    interface ReportFullyDrawnExecutor extends Executor {
        void activityDestroyed();
        void viewCreated(View view);
    }

    class ReportFullyDrawnExecutorImpl implements ReportFullyDrawnExecutor,
            ViewTreeObserver.OnDrawListener,
            Runnable {
        private Runnable currentRunnable;
        private final long endWatchTimeMillis = SystemClock.uptimeMillis() + 10000;
        private boolean onDrawScheduled;

        public long getEndWatchTimeMillis() {
            return endWatchTimeMillis;
        }

        public Runnable getCurrentRunnable() {
            return currentRunnable;
        }

        public void setCurrentRunnable(Runnable runnable) {
            this.currentRunnable = runnable;
        }

        public boolean getOnDrawScheduled() {
            return onDrawScheduled;
        }

        public void setOnDrawScheduled(boolean scheduled) {
            this.onDrawScheduled = scheduled;
        }

        @Override
        public void viewCreated(View view) {
            if (!onDrawScheduled) {
                onDrawScheduled = true;
                view.getViewTreeObserver().addOnDrawListener(this);
            }
        }

        @Override
        public void activityDestroyed() {
            getWindow().getDecorView().removeCallbacks(this);
            getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override
        public void execute(Runnable runnable) {
            this.currentRunnable = runnable;
            View decorView = getWindow().getDecorView();

            if (!onDrawScheduled) {
                decorView.postOnAnimation(() -> {
                    if (currentRunnable != null) {
                        currentRunnable.run();
                        currentRunnable = null;
                    }
                });
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        @Override
        public void onDraw() {
            if (currentRunnable != null) {
                currentRunnable.run();
                currentRunnable = null;
            } else if (SystemClock.uptimeMillis() > endWatchTimeMillis) {
                onDrawScheduled = false;
                getWindow().getDecorView().post(this);
            }
        }

        @Override
        public void run() {
            getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }
    }

    class CustomActivityResultRegistry extends ActivityResultRegistry {
        public CustomActivityResultRegistry() {
            super();
        }

        @Override
        public <I, O> void onLaunch(int i, ActivityResultContract<I, O> activityResultContract, I i2, ActivityOptionsCompat activityOptionsCompat) {

        }
    }

    static class Companion {
        private Companion() {}
    }
}