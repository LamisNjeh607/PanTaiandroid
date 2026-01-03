package io.capawesome.capacitorjs.plugins.androidedgetoedgesupport;

import android.graphics.Color;
import android.view.ViewGroup;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "EdgeToEdge")
public class EdgeToEdgePlugin extends Plugin {
    private static final String ERROR_COLOR_MISSING = "color must be provided.";
    private static final String TAG = "EdgeToEdge";
    private EdgeToEdge implementation;

    public void load() {
        this.implementation = new EdgeToEdge(this, getEdgeToEdgeConfig());
    }

    @PluginMethod
    public void enable(PluginCall pluginCall) {
        getActivity().runOnUiThread(new EdgeToEdgePlugin$$ExternalSyntheticLambda0(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$enable$0(PluginCall pluginCall) {
        try {
            this.implementation.enable();
            pluginCall.resolve( jSObject );
        } catch (Exception e) {
            pluginCall.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void disable(PluginCall pluginCall) {
        getActivity().runOnUiThread(new EdgeToEdgePlugin$$ExternalSyntheticLambda2(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disable$1(PluginCall pluginCall) {
        try {
            this.implementation.disable();
            pluginCall.resolve( jSObject );
        } catch (Exception e) {
            pluginCall.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void getInsets(PluginCall pluginCall) {
        try {
            ViewGroup.MarginLayoutParams insets = this.implementation.getInsets();
            JSObject jSObject = new JSObject();
            jSObject.put("bottom", insets.bottomMargin);
            jSObject.put("left", insets.leftMargin);
            jSObject.put("right", insets.rightMargin);
            jSObject.put("top", insets.topMargin);
            pluginCall.resolve(jSObject);
        } catch (Exception e) {
            pluginCall.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void setBackgroundColor(PluginCall pluginCall) {
        String string = pluginCall.getString("color");
        if (string == null) {
            pluginCall.reject(ERROR_COLOR_MISSING);
        } else {
            getActivity().runOnUiThread(new EdgeToEdgePlugin$$ExternalSyntheticLambda1(this, string, pluginCall));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBackgroundColor$2(String str, PluginCall pluginCall) {
        try {
            this.implementation.setBackgroundColor(str);
            pluginCall.resolve( jSObject );
        } catch (Exception e) {
            pluginCall.reject(e.getMessage());
        }
    }

    private EdgeToEdgeConfig getEdgeToEdgeConfig() {
        EdgeToEdgeConfig edgeToEdgeConfig = new EdgeToEdgeConfig();
        try {
            String string = getConfig().getString("backgroundColor");
            if (string != null) {
                edgeToEdgeConfig.setBackgroundColor(Color.parseColor(string));
            }
        } catch (Exception e) {
            Logger.error(TAG, "Set config failed.", e);
        }
        return edgeToEdgeConfig;
    }
}
