package com.capacitorjs.plugins.statusbar;

import android.content.res.Configuration;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;
import java.util.Locale;

@CapacitorPlugin(name = "StatusBar")
public class StatusBarPlugin extends Plugin {
    private StatusBar implementation;

    public void load() {
        this.implementation = new StatusBar(getActivity(), getStatusBarConfig(), new StatusBarPlugin$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(String str, StatusBarInfo statusBarInfo) {
        notifyListeners(str, toJSObject(statusBarInfo), true);
    }

    private StatusBarConfig getStatusBarConfig() {
        StatusBarConfig statusBarConfig = new StatusBarConfig();
        String string = getConfig().getString("backgroundColor");
        if (string != null) {
            try {
                statusBarConfig.setBackgroundColor(Integer.valueOf(WebColor.parseColor(string)));
            } catch (IllegalArgumentException unused) {
                Logger.debug("Background color not applied");
            }
        }
        statusBarConfig.setStyle(styleFromConfig(getConfig().getString("style", statusBarConfig.getStyle())));
        statusBarConfig.setOverlaysWebView(getConfig().getBoolean("overlaysWebView", statusBarConfig.isOverlaysWebView()));
        return statusBarConfig;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String styleFromConfig(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r5 = r5.toLowerCase()
            int r0 = r5.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case 3075958: goto L_0x0037;
                case 102970646: goto L_0x002d;
                case 551041699: goto L_0x0023;
                case 1544803905: goto L_0x0019;
                case 1668813891: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0041
        L_0x000f:
            java.lang.String r0 = "darkcontent"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0041
            r5 = r2
            goto L_0x0042
        L_0x0019:
            java.lang.String r0 = "default"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0041
            r5 = 4
            goto L_0x0042
        L_0x0023:
            java.lang.String r0 = "lightcontent"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0041
            r5 = 0
            goto L_0x0042
        L_0x002d:
            java.lang.String r0 = "light"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0041
            r5 = r1
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "dark"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0041
            r5 = r3
            goto L_0x0042
        L_0x0041:
            r5 = -1
        L_0x0042:
            if (r5 == 0) goto L_0x0050
            if (r5 == r3) goto L_0x0050
            if (r5 == r2) goto L_0x004d
            if (r5 == r1) goto L_0x004d
            java.lang.String r5 = "DEFAULT"
            return r5
        L_0x004d:
            java.lang.String r5 = "LIGHT"
            return r5
        L_0x0050:
            java.lang.String r5 = "DARK"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.capacitorjs.plugins.statusbar.StatusBarPlugin.styleFromConfig(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void handleOnConfigurationChanged(Configuration configuration) {
        super.handleOnConfigurationChanged(configuration);
        this.implementation.updateStyle();
    }

    @PluginMethod
    public void setStyle(PluginCall pluginCall) {
        String string = pluginCall.getString("style");
        if (string == null) {
            pluginCall.reject("Style must be provided");
        } else {
            getBridge().executeOnMainThread(new StatusBarPlugin$$ExternalSyntheticLambda3(this, string, pluginCall));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setStyle$1(String str, PluginCall pluginCall) {
        this.implementation.setStyle(str);
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void setBackgroundColor(PluginCall pluginCall) {
        String string = pluginCall.getString("color");
        if (string == null) {
            pluginCall.reject("Color must be provided");
        } else {
            getBridge().executeOnMainThread(new StatusBarPlugin$$ExternalSyntheticLambda1(this, string, pluginCall));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBackgroundColor$2(String str, PluginCall pluginCall) {
        try {
            this.implementation.setBackgroundColor(WebColor.parseColor(str.toUpperCase(Locale.ROOT)));
            pluginCall.resolve( jSObject );
        } catch (IllegalArgumentException unused) {
            pluginCall.reject("Invalid color provided. Must be a hex string (ex: #ff0000");
        }
    }

    @PluginMethod
    public void hide(PluginCall pluginCall) {
        getBridge().executeOnMainThread(new StatusBarPlugin$$ExternalSyntheticLambda2(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$3(PluginCall pluginCall) {
        this.implementation.hide();
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void show(PluginCall pluginCall) {
        getBridge().executeOnMainThread(new StatusBarPlugin$$ExternalSyntheticLambda0(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$4(PluginCall pluginCall) {
        this.implementation.show();
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void getInfo(PluginCall pluginCall) {
        pluginCall.resolve(toJSObject(this.implementation.getInfo()));
    }

    @PluginMethod
    public void setOverlaysWebView(PluginCall pluginCall) {
        getBridge().executeOnMainThread(new StatusBarPlugin$$ExternalSyntheticLambda4(this, pluginCall.getBoolean("overlay", true), pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOverlaysWebView$5(Boolean bool, PluginCall pluginCall) {
        this.implementation.setOverlaysWebView(bool);
        pluginCall.resolve( jSObject );
    }

    private JSObject toJSObject(StatusBarInfo statusBarInfo) {
        JSObject jSObject = new JSObject();
        jSObject.put("visible", statusBarInfo.isVisible());
        jSObject.put("style", statusBarInfo.getStyle());
        jSObject.put("color", statusBarInfo.getColor());
        jSObject.put("overlays", statusBarInfo.isOverlays());
        jSObject.put("height", statusBarInfo.getHeight());
        return jSObject;
    }
}
