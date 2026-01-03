package com.example.pantaijava.com.capacitor.safearea;

import android.os.Build;
import android.view.OrientationEventListener;
import android.view.Window;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SafeArea")
public class SafeAreaPlugin extends Plugin {
    private static final String Bar_Height = "statusBarHeight";
    private static final String KEY_INSET = "insets";
    private boolean isListening = false;
    /* access modifiers changed from: private */
    public int lastOrientation = -1;
    private OrientationEventListener orientationEventListener;
    private final SafeArea safeAreaInsets = new SafeArea();

    public void load() {
        this.safeAreaInsets.setBridge(getBridge());
        startListeningForSafeAreaChanges();
    }

    private void startListeningForSafeAreaChanges() {
        if (!this.isListening) {
            AnonymousClass1 r0 = new OrientationEventListener(this.bridge.getActivity()) {
                public void onOrientationChanged(int i) {
                    int rotation = SafeAreaPlugin.this.bridge.getActivity().getWindowManager().getDefaultDisplay().getRotation();
                    if (rotation != SafeAreaPlugin.this.lastOrientation) {
                        SafeAreaPlugin.this.lastOrientation = rotation;
                        SafeAreaPlugin.this.detectSafeAreaChanges();
                    }
                }
            };
            this.orientationEventListener = r0;
            r0.enable();
            this.isListening = true;
        }
    }

    @PluginMethod
    public void setImmersiveNavigationBar(PluginCall pluginCall) {
        Window window = this.bridge.getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= 30) {
            this.bridge.getActivity().runOnUiThread(new SafeAreaPlugin$$ExternalSyntheticLambda0(window));
        } else {
            this.bridge.getActivity().runOnUiThread(new SafeAreaPlugin$$ExternalSyntheticLambda1(window));
        }
        pluginCall.resolve( jSObject );
    }

    static /* synthetic */ void lambda$setImmersiveNavigationBar$0(Window window) {
        window.setDecorFitsSystemWindows(false);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }

    static /* synthetic */ void lambda$setImmersiveNavigationBar$1(Window window) {
        window.getDecorView().setSystemUiVisibility(530);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }

    @PluginMethod
    public void startListeningForSafeAreaChanges(PluginCall pluginCall) {
        startListeningForSafeAreaChanges();
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void stopListeningForSafeAreaChanges(PluginCall pluginCall) {
        if (this.isListening) {
            this.orientationEventListener.disable();
            this.isListening = false;
        }
        pluginCall.resolve( jSObject );
    }

    /* access modifiers changed from: private */
    public void detectSafeAreaChanges() {
        JSObject jSObject = new JSObject();
        jSObject.put(KEY_INSET, (Object) this.safeAreaInsets.getSafeAreaInsets());
        notifyListeners("safeAreaChanged", jSObject);
    }

    @PluginMethod
    public void getSafeAreaInsets(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put(KEY_INSET, (Object) this.safeAreaInsets.getSafeAreaInsets());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getStatusBarHeight(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put(Bar_Height, this.safeAreaInsets.getStatusBarHeight());
        pluginCall.resolve(jSObject);
    }
}
