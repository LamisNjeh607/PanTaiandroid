package com.example.pantaijava.com.capacitor.safearea;

import android.util.Log;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;

public class SafeArea {
    private Bridge bridge;

    public void setBridge(Bridge bridge2) {
        this.bridge = bridge2;
    }

    public JSObject getSafeAreaInsets() {
        WindowInsets rootWindowInsets = this.bridge.getActivity().getWindow().getDecorView().getRootWindowInsets();
        if (rootWindowInsets == null) {
            Log.i(SafeAreaPlugin.class.toString(), "WindowInsets is not available.");
            return result(0, 0, 0, 0);
        }
        int stableInsetTop = rootWindowInsets.getStableInsetTop();
        int stableInsetLeft = rootWindowInsets.getStableInsetLeft();
        int stableInsetRight = rootWindowInsets.getStableInsetRight();
        int stableInsetBottom = rootWindowInsets.getStableInsetBottom();
        DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
        if (displayCutout != null) {
            stableInsetTop = Math.max(displayCutout.getSafeInsetTop(), stableInsetTop);
            stableInsetLeft = Math.max(displayCutout.getSafeInsetLeft(), stableInsetLeft);
            stableInsetRight = Math.max(displayCutout.getSafeInsetRight(), stableInsetRight);
            stableInsetBottom = Math.max(displayCutout.getSafeInsetBottom(), stableInsetBottom);
        }
        return result(stableInsetTop, stableInsetLeft, stableInsetRight, stableInsetBottom);
    }

    private Boolean getStatusBarVisible() {
        WindowInsets rootWindowInsets = this.bridge.getActivity().getWindow().getDecorView().getRootWindowInsets();
        return Boolean.valueOf(rootWindowInsets != null && rootWindowInsets.getSystemWindowInsetTop() == 0);
    }

    private Boolean tabBarIsVisible() {
        return Boolean.valueOf((this.bridge.getActivity().getWindow().getDecorView().getSystemUiVisibility() & 512) == 512);
    }

    public int getStatusBarHeight() {
        float density = getDensity();
        int identifier = this.bridge.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return Math.round(((float) (identifier > 0 ? this.bridge.getActivity().getResources().getDimensionPixelSize(identifier) : 0)) / density);
    }

    public JSObject result(int i, int i2, int i3, int i4) {
        JSObject jSObject = new JSObject();
        jSObject.put("top", dpToPixels(i));
        jSObject.put("left", dpToPixels(i2));
        jSObject.put("right", dpToPixels(i3));
        jSObject.put("bottom", dpToPixels(i4));
        return jSObject;
    }

    private int dpToPixels(int i) {
        return Math.round(((float) i) / getDensity());
    }

    private float getDensity() {
        return this.bridge.getActivity().getResources().getDisplayMetrics().density;
    }
}
