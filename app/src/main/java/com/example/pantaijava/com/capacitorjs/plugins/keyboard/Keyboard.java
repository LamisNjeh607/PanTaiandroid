package com.capacitorjs.plugins.keyboard;

import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.getcapacitor.Bridge;
import java.util.List;
import kotlinx.coroutines.DebugKt;

public class Keyboard {
    static final String EVENT_KB_DID_HIDE = "keyboardDidHide";
    static final String EVENT_KB_DID_SHOW = "keyboardDidShow";
    static final String EVENT_KB_WILL_HIDE = "keyboardWillHide";
    static final String EVENT_KB_WILL_SHOW = "keyboardWillShow";
    private AppCompatActivity activity;
    private Bridge bridge;
    private FrameLayout.LayoutParams frameLayoutParams;
    /* access modifiers changed from: private */
    public KeyboardEventListener keyboardEventListener;
    private View mChildOfContent;
    /* access modifiers changed from: private */
    public View rootView;
    private int usableHeightPrevious;

    interface KeyboardEventListener {
        void onKeyboardEvent(String str, int i);
    }

    public void setKeyboardEventListener(KeyboardEventListener keyboardEventListener2) {
        this.keyboardEventListener = keyboardEventListener2;
    }

    public Keyboard(Bridge bridge2, boolean z) {
        this(bridge2.getActivity(), z);
        this.bridge = bridge2;
    }

    public Keyboard(final AppCompatActivity appCompatActivity, final boolean z) {
        this.activity = appCompatActivity;
        FrameLayout frameLayout = (FrameLayout) appCompatActivity.getWindow().getDecorView().findViewById(16908290);
        View rootView2 = frameLayout.getRootView();
        this.rootView = rootView2;
        ViewCompat.setWindowInsetsAnimationCallback(rootView2, new WindowInsetsAnimationCompat.Callback(0) {
            public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
                return windowInsetsCompat;
            }

            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
                boolean isVisible = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).isVisible(WindowInsetsCompat.Type.ime());
                int i = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).getInsets(WindowInsetsCompat.Type.ime()).bottom;
                float f = appCompatActivity.getResources().getDisplayMetrics().density;
                if (z) {
                    Keyboard.this.possiblyResizeChildOfContent(isVisible);
                }
                if (isVisible) {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_WILL_SHOW, Math.round(((float) i) / f));
                } else {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_WILL_HIDE, 0);
                }
                return super.onStart(windowInsetsAnimationCompat, boundsCompat);
            }

            public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
                super.onEnd(windowInsetsAnimationCompat);
                boolean isVisible = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).isVisible(WindowInsetsCompat.Type.ime());
                int i = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).getInsets(WindowInsetsCompat.Type.ime()).bottom;
                float f = appCompatActivity.getResources().getDisplayMetrics().density;
                if (isVisible) {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_DID_SHOW, Math.round(((float) i) / f));
                } else {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_DID_HIDE, 0);
                }
            }
        });
        View childAt = frameLayout.getChildAt(0);
        this.mChildOfContent = childAt;
        this.frameLayoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
    }

    public void show() {
        ((InputMethodManager) this.activity.getSystemService("input_method")).showSoftInput(this.activity.getCurrentFocus(), 0);
    }

    public boolean hide() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method");
        View currentFocus = this.activity.getCurrentFocus();
        if (currentFocus == null) {
            return false;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        return true;
    }

    /* access modifiers changed from: private */
    public void possiblyResizeChildOfContent(boolean z) {
        int computeUsableHeight = z ? computeUsableHeight() : -1;
        if (this.usableHeightPrevious != computeUsableHeight) {
            this.frameLayoutParams.height = computeUsableHeight;
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        WindowInsetsCompat rootWindowInsets;
        int i;
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        if (!shouldApplyEdgeToEdgeAdjustments() || (rootWindowInsets = ViewCompat.getRootWindowInsets(this.rootView)) == null || (i = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom) <= 0) {
            return isOverlays() ? rect.bottom : rect.height();
        }
        return rect.bottom + i;
    }

    private boolean shouldApplyEdgeToEdgeAdjustments() {
        Bridge bridge2 = this.bridge;
        String adjustMarginsForEdgeToEdge = bridge2 == null ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : bridge2.getConfig().adjustMarginsForEdgeToEdge();
        if (adjustMarginsForEdgeToEdge.equals("force")) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 35 || !adjustMarginsForEdgeToEdge.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            return false;
        }
        TypedValue typedValue = new TypedValue();
        if (this.activity.getTheme().resolveAttribute(16844442, typedValue, true) && typedValue.data != 0) {
            return false;
        }
        return true;
    }

    private boolean isOverlays() {
        return (this.activity.getWindow().getDecorView().getSystemUiVisibility() & 1024) == 1024;
    }
}
