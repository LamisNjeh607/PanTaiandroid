package com.example.pantaijava.com.capacitorjs.plugins.app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.pm.PackageInfoCompat;
import com.getcapacitor.App;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginResult;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.InternalUtils;
import com.google.android.gms.common.internal.ImagesContract;

@CapacitorPlugin(name = "App")
public class AppPlugin extends Plugin {
    private static final String EVENT_BACK_BUTTON = "backButton";
    private static final String EVENT_PAUSE = "pause";
    private static final String EVENT_RESTORED_RESULT = "appRestoredResult";
    private static final String EVENT_RESUME = "resume";
    private static final String EVENT_STATE_CHANGE = "appStateChange";
    private static final String EVENT_URL_OPEN = "appUrlOpen";
    private boolean hasPausedEver = false;

    public void load() {
        this.bridge.getApp().setStatusChangeListener(new AppPlugin$$ExternalSyntheticLambda0(this));
        this.bridge.getApp().setAppRestoredListener(new com.capacitorjs.plugins.app.AppPlugin$$ExternalSyntheticLambda1(this));
        getActivity().getOnBackPressedDispatcher().addCallback(getActivity(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (AppPlugin.this.hasListeners(AppPlugin.EVENT_BACK_BUTTON)) {
                    JSObject jSObject = new JSObject();
                    jSObject.put("canGoBack", AppPlugin.this.bridge.getWebView().canGoBack());
                    AppPlugin.this.notifyListeners(AppPlugin.EVENT_BACK_BUTTON, jSObject, true);
                    AppPlugin.this.bridge.triggerJSEvent("backbutton", "document");
                } else if (AppPlugin.this.bridge.getWebView().canGoBack()) {
                    AppPlugin.this.bridge.getWebView().goBack();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(Boolean bool) {
        Logger.debug(getLogTag(), "Firing change: " + bool);
        JSObject jSObject = new JSObject();
        jSObject.put("isActive", (Object) bool);
        notifyListeners(EVENT_STATE_CHANGE, jSObject, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$1(PluginResult pluginResult) {
        Logger.debug(getLogTag(), "Firing restored result");
        notifyListeners(EVENT_RESTORED_RESULT, pluginResult.getWrappedResult(), true);
    }

    @PluginMethod
    public void exitApp(PluginCall pluginCall) {
        unsetAppListeners();
        pluginCall.resolve( jSObject );
        getBridge().getActivity().finish();
    }

    @PluginMethod
    public void getInfo(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        try {
            PackageInfo packageInfo = InternalUtils.getPackageInfo(getContext().getPackageManager(), getContext().getPackageName());
            ApplicationInfo applicationInfo = getContext().getApplicationInfo();
            int i = applicationInfo.labelRes;
            jSObject.put("name", i == 0 ? applicationInfo.nonLocalizedLabel.toString() : getContext().getString(i));
            jSObject.put("id", packageInfo.packageName);
            jSObject.put("build", Integer.toString((int) PackageInfoCompat.getLongVersionCode(packageInfo)));
            jSObject.put("version", packageInfo.versionName);
            pluginCall.resolve(jSObject);
        } catch (Exception unused) {
            pluginCall.reject("Unable to get App Info");
        }
    }

    @PluginMethod
    public void getLaunchUrl(PluginCall pluginCall) {
        Uri intentUri = this.bridge.getIntentUri();
        if (intentUri != null) {
            JSObject jSObject = new JSObject();
            jSObject.put(ImagesContract.URL, intentUri.toString());
            pluginCall.resolve( jSObject );
            return;
        }
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void getState(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("isActive", this.bridge.getApp().isActive());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void minimizeApp(PluginCall pluginCall) {
        getActivity().moveTaskToBack(true);
        pluginCall.resolve( jSObject );
    }

    /* access modifiers changed from: protected */
    public void handleOnNewIntent(Intent intent) {
        super.handleOnNewIntent(intent);
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            JSObject jSObject = new JSObject();
            jSObject.put(ImagesContract.URL, data.toString());
            notifyListeners(EVENT_URL_OPEN, jSObject, true);
        }
    }

    /* access modifiers changed from: protected */
    public void handleOnPause() {
        super.handleOnPause();
        this.hasPausedEver = true;
        notifyListeners(EVENT_PAUSE, (JSObject) null);
    }

    /* access modifiers changed from: protected */
    public void handleOnResume() {
        super.handleOnResume();
        if (this.hasPausedEver) {
            notifyListeners(EVENT_RESUME, (JSObject) null);
        }
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        unsetAppListeners();
    }

    private void unsetAppListeners() {
        this.bridge.getApp().setStatusChangeListener((App.AppStatusChangeListener) null);
        this.bridge.getApp().setAppRestoredListener((App.AppRestoredListener) null);
    }
}
