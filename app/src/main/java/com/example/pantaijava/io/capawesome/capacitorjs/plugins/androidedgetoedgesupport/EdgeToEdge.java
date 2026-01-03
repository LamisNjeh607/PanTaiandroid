package io.capawesome.capacitorjs.plugins.androidedgetoedgesupport;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EdgeToEdge {
    private final EdgeToEdgeConfig config;
    private final EdgeToEdgePlugin plugin;

    public EdgeToEdge(EdgeToEdgePlugin edgeToEdgePlugin, EdgeToEdgeConfig edgeToEdgeConfig) {
        this.config = edgeToEdgeConfig;
        this.plugin = edgeToEdgePlugin;
        setBackgroundColor(edgeToEdgeConfig.getBackgroundColor());
        applyInsets();
    }

    public void enable() {
        applyInsets();
    }

    public void disable() {
        removeInsets();
    }

    public ViewGroup.MarginLayoutParams getInsets() {
        return (ViewGroup.MarginLayoutParams) this.plugin.getBridge().getWebView().getLayoutParams();
    }

    public void setBackgroundColor(String str) {
        setBackgroundColor(Color.parseColor(str));
    }

    private void applyInsets() {
        WebView webView = this.plugin.getBridge().getWebView();
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(webView);
        if (rootWindowInsets != null) {
            Insets insets = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets insets2 = rootWindowInsets.getInsets(WindowInsetsCompat.Type.ime());
            boolean isVisible = rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) webView.getLayoutParams();
            marginLayoutParams.bottomMargin = isVisible ? insets2.bottom : insets.bottom;
            marginLayoutParams.topMargin = insets.top;
            marginLayoutParams.leftMargin = insets.left;
            marginLayoutParams.rightMargin = insets.right;
            webView.setLayoutParams(marginLayoutParams);
        }
        ViewCompat.setOnApplyWindowInsetsListener(webView, new EdgeToEdge$$ExternalSyntheticLambda0());
    }

    static /* synthetic */ WindowInsetsCompat lambda$applyInsets$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        Insets insets2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime());
        boolean isVisible = windowInsetsCompat.isVisible(WindowInsetsCompat.Type.ime());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.bottomMargin = isVisible ? insets2.bottom : insets.bottom;
        marginLayoutParams.topMargin = insets.top;
        marginLayoutParams.leftMargin = insets.left;
        marginLayoutParams.rightMargin = insets.right;
        view.setLayoutParams(marginLayoutParams);
        return WindowInsetsCompat.CONSUMED;
    }

    private void removeInsets() {
        WebView webView = this.plugin.getBridge().getWebView();
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) webView.getLayoutParams();
        marginLayoutParams.topMargin = 0;
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
        marginLayoutParams.bottomMargin = 0;
        webView.setLayoutParams(marginLayoutParams);
        ViewCompat.setOnApplyWindowInsetsListener(webView, (OnApplyWindowInsetsListener) null);
    }

    private void setBackgroundColor(int i) {
        ((ViewGroup) this.plugin.getBridge().getWebView().getParent()).setBackgroundColor(i);
    }
}
