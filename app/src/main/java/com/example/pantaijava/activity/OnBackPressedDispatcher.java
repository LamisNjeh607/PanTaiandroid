package com.example.pantaijava.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedDispatcherKt$addCallback$callback$1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * A dispatcher for back button and gesture events.
 * This is a simplified Java implementation of AndroidX OnBackPressedDispatcher.
 */
public class OnBackPressedDispatcher {
    // Fields
    private boolean backInvokedCallbackRegistered = false;
    private final Runnable fallbackOnBackPressed;
    private boolean hasEnabledCallbacks = false;
    private OnBackPressedCallback inProgressCallback;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackInvokedCallback onBackInvokedCallback;
    private final Deque<OnBackPressedCallback> onBackPressedCallbacks = new ArrayDeque<>();
    private final Consumer<Boolean> onHasEnabledCallbacksChanged;

    // Constructors
    public OnBackPressedDispatcher() {
        this(null, null);
    }

    public OnBackPressedDispatcher(@Nullable Runnable fallbackOnBackPressed) {
        this(fallbackOnBackPressed, null);
    }

    public OnBackPressedDispatcher(@Nullable Runnable fallbackOnBackPressed,
                                   @Nullable Consumer<Boolean> onHasEnabledCallbacksChanged) {
        this.fallbackOnBackPressed = fallbackOnBackPressed;
        this.onHasEnabledCallbacksChanged = onHasEnabledCallbacksChanged;

        // Initialize OnBackInvokedCallback for API 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.onBackInvokedCallback = createOnBackInvokedCallback();
        }
    }

    // Public API Methods
    public void addCallback(@NonNull OnBackPressedDispatcherKt$addCallback$callback$1 onBackPressedCallback) {
        if (onBackPressedCallback == null) {
            throw new NullPointerException("onBackPressedCallback cannot be null");
        }

        onBackPressedCallbacks.add(onBackPressedCallback);
        Cancellable cancellable = new OnBackPressedCancellable(this, onBackPressedCallback);
        onBackPressedCallback.addCancellable(cancellable);
        updateEnabledCallbacks();

        onBackPressedCallback.setEnabledChangedCallback(new Runnable() {
            @Override
            public void run() {
                updateEnabledCallbacks();
            }
        });
    }

    public void addCallback(@NonNull LifecycleOwner owner,
                            @NonNull OnBackPressedDispatcherKt$addCallback$callback$1 onBackPressedCallback) {
        if (owner == null) {
            throw new NullPointerException("owner cannot be null");
        }
        if (onBackPressedCallback == null) {
            throw new NullPointerException("onBackPressedCallback cannot be null");
        }

        Lifecycle lifecycle = owner.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
            Cancellable cancellable = new LifecycleOnBackPressedCancellable(
                    this, lifecycle, onBackPressedCallback
            );
            onBackPressedCallback.addCancellable(cancellable);
            updateEnabledCallbacks();

            onBackPressedCallback.setEnabledChangedCallback(new Runnable() {
                @Override
                public void run() {
                    updateEnabledCallbacks();
                }
            });
        }
    }

    public boolean hasEnabledCallbacks() {
        return hasEnabledCallbacks;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void setOnBackInvokedDispatcher(@NonNull OnBackInvokedDispatcher invoker) {
        if (invoker == null) {
            throw new NullPointerException("invoker cannot be null");
        }

        this.invokedDispatcher = invoker;
        updateBackInvokedCallbackState(this.hasEnabledCallbacks);
    }

    public void onBackPressed() {
        OnBackPressedCallback callback = inProgressCallback;

        // If no callback is in progress, find the first enabled callback
        if (callback == null) {
            Iterator<OnBackPressedCallback> iterator = onBackPressedCallbacks.descendingIterator();
            while (iterator.hasNext()) {
                OnBackPressedCallback next = iterator.next();
                if (next.isEnabled()) {
                    callback = next;
                    break;
                }
            }
        }

        // Clear in-progress callback
        inProgressCallback = null;

        // Handle the callback
        if (callback != null) {
            callback.handleOnBackPressed();
        } else if (fallbackOnBackPressed != null) {
            // Fallback to default behavior
            fallbackOnBackPressed.run();
        }
    }

    // Internal helper methods
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private OnBackInvokedCallback createOnBackInvokedCallback() {
        return new OnBackInvokedCallback() {
            @Override
            public void onBackInvoked() {
                OnBackPressedDispatcher.this.onBackPressed();
            }
        };
    }

    private void updateBackInvokedCallbackState(boolean shouldBeRegistered) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (invokedDispatcher != null && onBackInvokedCallback != null) {
                if (shouldBeRegistered && !backInvokedCallbackRegistered) {
                    // Register with default priority
                    invokedDispatcher.registerOnBackInvokedCallback(
                            OnBackInvokedDispatcher.PRIORITY_DEFAULT,
                            onBackInvokedCallback
                    );
                    backInvokedCallbackRegistered = true;
                } else if (!shouldBeRegistered && backInvokedCallbackRegistered) {
                    invokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback);
                    backInvokedCallbackRegistered = false;
                }
            }
        }
    }

    public void updateEnabledCallbacks() {
        boolean oldValue = hasEnabledCallbacks;
        boolean newValue = false;

        // Check if any callback is enabled (iterating in reverse for correct order)
        Iterator<OnBackPressedCallback> iterator = onBackPressedCallbacks.descendingIterator();
        while (iterator.hasNext()) {
            if (iterator.next().isEnabled()) {
                newValue = true;
                break;
            }
        }

        hasEnabledCallbacks = newValue;

        // Notify if state changed
        if (newValue != oldValue) {
            if (onHasEnabledCallbacksChanged != null) {
                onHasEnabledCallbacksChanged.accept(newValue);
            }

            // Update the back invoked callback registration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                updateBackInvokedCallbackState(newValue);
            }
        }
    }

    // Callback class
    public static abstract class OnBackPressedCallback {
        private boolean enabled;
        private Runnable enabledChangedCallback;
        private Cancellable cancellable;

        public OnBackPressedCallback(boolean enabled) {
            this.enabled = enabled;
        }

        public final boolean isEnabled() {
            return enabled;
        }

        public final void setEnabled(boolean enabled) {
            if (this.enabled != enabled) {
                this.enabled = enabled;
                if (enabledChangedCallback != null) {
                    enabledChangedCallback.run();
                }
            }
        }

        public abstract void handleOnBackPressed();

        public void handleOnBackStarted(BackEventCompat backEvent) {
            // Default implementation - override if needed
        }

        public void handleOnBackProgressed(BackEventCompat backEvent) {
            // Default implementation - override if needed
        }

        public void handleOnBackCancelled() {
            // Default implementation - override if needed
        }

        void addCancellable(Cancellable cancellable) {
            this.cancellable = cancellable;
        }

        void removeCancellable(Cancellable cancellable) {
            if (this.cancellable == cancellable) {
                this.cancellable = null;
            }
        }

        void setEnabledChangedCallback(Runnable callback) {
            this.enabledChangedCallback = callback;
        }

        Runnable getEnabledChangedCallback() {
            return enabledChangedCallback;
        }
    }

    // BackEventCompat for gesture progress tracking
    public static class BackEventCompat {
        private final float progress;
        private final float touchX;
        private final float touchY;

        public BackEventCompat(float progress) {
            this(progress, 0f, 0f);
        }

        public BackEventCompat(float progress, float touchX, float touchY) {
            this.progress = progress;
            this.touchX = touchX;
            this.touchY = touchY;
        }

        public float getProgress() {
            return progress;
        }

        public float getTouchX() {
            return touchX;
        }

        public float getTouchY() {
            return touchY;
        }
    }

    // Cancellable interface
    public interface Cancellable {
        void cancel();
    }

    // Simple cancellable implementation
    private class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedDispatcher dispatcher;
        private final OnBackPressedCallback callback;

        public OnBackPressedCancellable(OnBackPressedDispatcher dispatcher,
                                        OnBackPressedCallback callback) {
            this.dispatcher = dispatcher;
            this.callback = callback;
        }

        @Override
        public void cancel() {
            dispatcher.onBackPressedCallbacks.remove(callback);

            // If this callback was in progress, cancel it
            if (dispatcher.inProgressCallback == callback) {
                callback.handleOnBackCancelled();
                dispatcher.inProgressCallback = null;
            }

            callback.removeCancellable(this);

            Runnable enabledChangedCallback = callback.getEnabledChangedCallback();
            if (enabledChangedCallback != null) {
                enabledChangedCallback.run();
            }

            callback.setEnabledChangedCallback(null);
            dispatcher.updateEnabledCallbacks();
        }
    }

    // Lifecycle-aware cancellable
    private class LifecycleOnBackPressedCancellable implements Cancellable, LifecycleEventObserver {
        private final OnBackPressedDispatcher dispatcher;
        private final Lifecycle lifecycle;
        private final OnBackPressedCallback callback;
        private Cancellable currentCancellable;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher dispatcher,
                                                 Lifecycle lifecycle,
                                                 OnBackPressedCallback callback) {
            this.dispatcher = dispatcher;
            this.lifecycle = lifecycle;
            this.callback = callback;
            lifecycle.addObserver(this);
        }

        @Override
        public void onStateChanged(@NonNull LifecycleOwner source,
                                   @NonNull Lifecycle.Event event) {
            switch (event) {
                case ON_START:
                    currentCancellable = dispatcher.new OnBackPressedCancellable(dispatcher, callback);
                    break;
                case ON_STOP:
                    if (currentCancellable != null) {
                        currentCancellable.cancel();
                        currentCancellable = null;
                    }
                    break;
                case ON_DESTROY:
                    cancel();
                    break;
            }
        }

        @Override
        public void cancel() {
            lifecycle.removeObserver(this);
            callback.removeCancellable(this);

            if (currentCancellable != null) {
                currentCancellable.cancel();
                currentCancellable = null;
            }

            dispatcher.updateEnabledCallbacks();
        }
    }

    // API 33+ helper methods
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {}

        public static void registerOnBackInvokedCallback(@NonNull OnBackInvokedDispatcher dispatcher,
                                                         int priority,
                                                         @NonNull OnBackInvokedCallback callback) {
            dispatcher.registerOnBackInvokedCallback(priority, callback);
        }

        public static void unregisterOnBackInvokedCallback(@NonNull OnBackInvokedDispatcher dispatcher,
                                                           @NonNull OnBackInvokedCallback callback) {
            dispatcher.unregisterOnBackInvokedCallback(callback);
        }

        public static OnBackInvokedCallback createOnBackInvokedCallback(
                @NonNull final Runnable onBackInvoked
        ) {
            return new OnBackInvokedCallback() {
                @Override
                public void onBackInvoked() {
                    onBackInvoked.run();
                }
            };
        }
    }

    // API 34+ helper methods (Android 14+)
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {}

        public interface OnBackStartedCallback {
            void onBackStarted(BackEventCompat backEvent);
        }

        public interface OnBackProgressedCallback {
            void onBackProgressed(BackEventCompat backEvent);
        }

        public static OnBackInvokedCallback createOnBackAnimationCallback(
                @NonNull final OnBackStartedCallback onBackStarted,
                @NonNull final OnBackProgressedCallback onBackProgressed,
                @NonNull final Runnable onBackInvoked,
                @NonNull final Runnable onBackCancelled
        ) {
            // Note: This is a simplified version
            // The actual API 34 has more complex animation handling
            return new OnBackInvokedCallback() {
                @Override
                public void onBackInvoked() {
                    onBackInvoked.run();
                }

                // For API 34+, there would be additional methods for animation callbacks
                // but they're not accessible in the public API yet
            };
        }
    }

    // Utility methods for testing
    public int getCallbackCount() {
        return onBackPressedCallbacks.size();
    }

    public void clearCallbacks() {
        onBackPressedCallbacks.clear();
        updateEnabledCallbacks();
    }
}