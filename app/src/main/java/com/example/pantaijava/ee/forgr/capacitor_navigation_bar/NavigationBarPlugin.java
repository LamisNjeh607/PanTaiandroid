package ee.forgr.capacitor_navigation_bar;

import android.os.Build;
import android.view.View;
import android.view.WindowInsetsController;
import androidx.core.view.ViewCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;

@CapacitorPlugin(name = "NavigationBar")
public class NavigationBarPlugin extends Plugin {
    @PluginMethod
    public void setNavigationBarColor(PluginCall pluginCall) {
        String string = pluginCall.getString("color");
        boolean equals = Boolean.TRUE.equals(pluginCall.getBoolean("darkButtons", true));
        if (string == null) {
            pluginCall.reject("Color must be provided");
        } else {
            getBridge().executeOnMainThread(new NavigationBarPlugin$$ExternalSyntheticLambda1(this, string, equals, pluginCall));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setNavigationBarColor$0(String str, boolean z, PluginCall pluginCall) {
        try {
            if ("transparent".equalsIgnoreCase(str)) {
                getActivity().getWindow().getDecorView().setSystemUiVisibility(getActivity().getWindow().getDecorView().getSystemUiVisibility() | 768);
                getActivity().getWindow().setNavigationBarColor(0);
            } else {
                int parseColor = WebColor.parseColor(str);
                View decorView = getActivity().getWindow().getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & -769);
                getActivity().getWindow().setNavigationBarColor(parseColor);
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowInsetsController insetsController = getActivity().getWindow().getInsetsController();
                if (insetsController != null) {
                    if (z) {
                        insetsController.setSystemBarsAppearance(16, 16);
                    } else {
                        insetsController.setSystemBarsAppearance(0, 16);
                    }
                }
            } else {
                int systemUiVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
                getActivity().getWindow().getDecorView().setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & -17);
            }
            pluginCall.resolve( jSObject );
        } catch (IllegalArgumentException unused) {
            pluginCall.reject("Invalid color provided. Must be a hex color (#RRGGBB) or 'transparent'");
        }
    }

    @PluginMethod
    public void getNavigationBarColor(PluginCall pluginCall) {
        getBridge().executeOnMainThread(new NavigationBarPlugin$$ExternalSyntheticLambda0(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getNavigationBarColor$1(PluginCall pluginCall) {
        try {
            JSObject jSObject = new JSObject();
            jSObject.put("color", String.format("#%06X", new Object[]{Integer.valueOf(getActivity().getWindow().getNavigationBarColor() & ViewCompat.MEASURED_SIZE_MASK)}));
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 30) {
                WindowInsetsController insetsController = getActivity().getWindow().getInsetsController();
                if (insetsController != null) {
                    if ((insetsController.getSystemBarsAppearance() & 16) != 0) {
                        z = true;
                    }
                    jSObject.put("darkButtons", !z);
                } else {
                    jSObject.put("darkButtons", true);
                }
            } else {
                if ((getActivity().getWindow().getDecorView().getSystemUiVisibility() & 16) != 0) {
                    z = true;
                }
                jSObject.put("darkButtons", !z);
            }
            pluginCall.resolve(jSObject);
        } catch (Exception e) {
            pluginCall.reject("Failed to get navigation bar color or button style", e);
        }
    }
}
