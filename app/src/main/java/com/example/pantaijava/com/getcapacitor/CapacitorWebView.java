package com.getcapacitor;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import kotlinx.coroutines.DebugKt;

public class CapacitorWebView extends WebView {
    private Bridge bridge;
    private BaseInputConnection capInputConnection;

    public CapacitorWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setBridge(Bridge bridge2) {
        this.bridge = bridge2;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        CapConfig capConfig;
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            capConfig = bridge2.getConfig();
        } else {
            capConfig = CapConfig.loadDefault(getContext());
        }
        if (!capConfig.isInputCaptured()) {
            return super.onCreateInputConnection(editorInfo);
        }
        if (this.capInputConnection == null) {
            this.capInputConnection = new BaseInputConnection(this, false);
        }
        return this.capInputConnection;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 2) {
            return super.dispatchKeyEvent(keyEvent);
        }
        evaluateJavascript("document.activeElement.value = document.activeElement.value + '" + keyEvent.getCharacters() + "';", (ValueCallback) null);
        return false;
    }

    public void edgeToEdgeHandler(Bridge bridge2) {
        String adjustMarginsForEdgeToEdge = bridge2.getConfig().adjustMarginsForEdgeToEdge();
        if (!adjustMarginsForEdgeToEdge.equals("disable")) {
            boolean equals = adjustMarginsForEdgeToEdge.equals("force");
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 35 && adjustMarginsForEdgeToEdge.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                TypedValue typedValue = new TypedValue();
                boolean resolveAttribute = getContext().getTheme().resolveAttribute(16844442, typedValue, true);
                boolean z2 = typedValue.data != 0;
                if (!resolveAttribute || !z2) {
                    z = true;
                }
            }
            if (equals || z) {
                ViewCompat.setOnApplyWindowInsetsListener(this, new CapacitorWebView$$ExternalSyntheticLambda0());
            }
        }
    }

    static /* synthetic */ WindowInsetsCompat lambda$edgeToEdgeHandler$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.leftMargin = insets.left;
        marginLayoutParams.bottomMargin = insets.bottom;
        marginLayoutParams.rightMargin = insets.right;
        marginLayoutParams.topMargin = insets.top;
        view.setLayoutParams(marginLayoutParams);
        return WindowInsetsCompat.CONSUMED;
    }
}
