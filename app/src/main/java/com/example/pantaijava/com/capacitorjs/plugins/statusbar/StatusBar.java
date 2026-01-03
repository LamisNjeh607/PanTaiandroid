package com.example.pantaijava.com.capacitorjs.plugins.statusbar;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;

import com.capacitorjs.plugins.statusbar.StatusBarInfo;

public class StatusBar {
    public static final String statusBarOverlayChanged = "statusBarOverlayChanged";
    public static final String statusBarVisibilityChanged = "statusBarVisibilityChanged";
    private final AppCompatActivity activity;
    private int currentStatusBarColor;
    private String currentStyle = "DEFAULT";
    private final ChangeListener listener;

    public interface ChangeListener {
        void onChange(String str, com.capacitorjs.plugins.statusbar.StatusBarInfo statusBarInfo);
    }

    public StatusBar(AppCompatActivity appCompatActivity, com.capacitorjs.plugins.statusbar.StatusBarConfig statusBarConfig, ChangeListener changeListener) {
        this.activity = appCompatActivity;
        this.currentStatusBarColor = appCompatActivity.getWindow().getStatusBarColor();
        this.listener = changeListener;
        setBackgroundColor(statusBarConfig.getBackgroundColor().intValue());
        setStyle(statusBarConfig.getStyle());
        setOverlaysWebView(Boolean.valueOf(statusBarConfig.isOverlaysWebView()));
        com.capacitorjs.plugins.statusbar.StatusBarInfo info = getInfo();
        info.setVisible(true);
        changeListener.onChange(statusBarOverlayChanged, info);
    }

    public void setStyle(String str) {
        Window window = this.activity.getWindow();
        View decorView = window.getDecorView();
        this.currentStyle = str;
        if (str.equals("DEFAULT")) {
            str = getStyleForTheme();
        }
        WindowCompat.getInsetsController(window, decorView).setAppearanceLightStatusBars(!str.equals("DARK"));
    }

    private String getStyleForTheme() {
        if ((this.activity.getResources().getConfiguration().uiMode & 48) != 32) {
            return "LIGHT";
        }
        return "DARK";
    }

    public void updateStyle() {
        setStyle(this.currentStyle);
    }

    public void setBackgroundColor(int i) {
        Window window = this.activity.getWindow();
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
        this.currentStatusBarColor = i;
    }

    public void hide() {
        WindowCompat.getInsetsController(this.activity.getWindow(), this.activity.getWindow().getDecorView()).hide(WindowInsetsCompat.Type.statusBars());
        com.capacitorjs.plugins.statusbar.StatusBarInfo info = getInfo();
        info.setVisible(false);
        this.listener.onChange(statusBarVisibilityChanged, info);
    }

    public void show() {
        WindowCompat.getInsetsController(this.activity.getWindow(), this.activity.getWindow().getDecorView()).show(WindowInsetsCompat.Type.statusBars());
        com.capacitorjs.plugins.statusbar.StatusBarInfo info = getInfo();
        info.setVisible(true);
        this.listener.onChange(statusBarVisibilityChanged, info);
    }

    public void setOverlaysWebView(Boolean bool) {
        View decorView = this.activity.getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (bool.booleanValue()) {
            decorView.setSystemUiVisibility(systemUiVisibility | 1280);
            this.currentStatusBarColor = this.activity.getWindow().getStatusBarColor();
            this.activity.getWindow().setStatusBarColor(0);
        } else {
            decorView.setSystemUiVisibility(systemUiVisibility & -1281);
            this.activity.getWindow().setStatusBarColor(this.currentStatusBarColor);
        }
        this.listener.onChange(statusBarOverlayChanged, getInfo());
    }

    private boolean getIsOverlaid() {
        return (this.activity.getWindow().getDecorView().getSystemUiVisibility() & 1024) == 1024;
    }

    public StatusBarInfo getInfo() {
        Window window = this.activity.getWindow();
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(window.getDecorView());
        boolean z = rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsetsCompat.Type.statusBars());
        StatusBarInfo statusBarInfo = new StatusBarInfo();
        statusBarInfo.setStyle(getStyle());
        statusBarInfo.setOverlays(getIsOverlaid());
        statusBarInfo.setVisible(z);
        statusBarInfo.setColor(String.format("#%06X", new Object[]{Integer.valueOf(window.getStatusBarColor() & ViewCompat.MEASURED_SIZE_MASK)}));
        statusBarInfo.setHeight(getStatusBarHeight());
        return statusBarInfo;
    }

    private String getStyle() {
        if (WindowCompat.getInsetsController(this.activity.getWindow(), this.activity.getWindow().getDecorView()).isAppearanceLightStatusBars()) {
            return "LIGHT";
        }
        return "DARK";
    }

    private int getStatusBarHeight() {
        float systemWindowInsetTop;
        float f;
        DisplayMetrics displayMetrics = this.activity.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= 30) {
            systemWindowInsetTop = (float) this.activity.getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.statusBars()).top;
            f = displayMetrics.density;
        } else {
            WindowInsets rootWindowInsets = this.activity.getWindow().getDecorView().getRootWindowInsets();
            if (rootWindowInsets == null) {
                return 0;
            }
            systemWindowInsetTop = (float) rootWindowInsets.getSystemWindowInsetTop();
            f = displayMetrics.density;
        }
        return (int) (systemWindowInsetTop / f);
    }
}
