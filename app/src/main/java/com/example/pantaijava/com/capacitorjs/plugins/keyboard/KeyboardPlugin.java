package com.capacitorjs.plugins.keyboard;

import android.os.Handler;
import android.os.Looper;
import com.capacitorjs.plugins.keyboard.Keyboard;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Keyboard")
public class KeyboardPlugin extends Plugin {
    private Keyboard implementation;

    public void load() {
        execute(new KeyboardPlugin$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0() {
        Keyboard keyboard = new Keyboard(getBridge(), getConfig().getBoolean("resizeOnFullScreen", false));
        this.implementation = keyboard;
        keyboard.setKeyboardEventListener(new KeyboardPlugin$$ExternalSyntheticLambda1(this));
    }

    @PluginMethod
    public void show(PluginCall pluginCall) {
        execute(new KeyboardPlugin$$ExternalSyntheticLambda0(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$2(PluginCall pluginCall) {
        new Handler(Looper.getMainLooper()).postDelayed(new KeyboardPlugin$$ExternalSyntheticLambda4(this, pluginCall), 350);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$1(PluginCall pluginCall) {
        this.implementation.show();
        pluginCall.resolve();
    }

    @PluginMethod
    public void hide(PluginCall pluginCall) {
        execute(new KeyboardPlugin$$ExternalSyntheticLambda3(this, pluginCall));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$3(PluginCall pluginCall) {
        if (!this.implementation.hide()) {
            pluginCall.reject("Can't close keyboard, not currently focused");
        } else {
            pluginCall.resolve();
        }
    }

    @PluginMethod
    public void setAccessoryBarVisible(PluginCall pluginCall) {
        pluginCall.unimplemented();
    }

    @PluginMethod
    public void setStyle(PluginCall pluginCall) {
        pluginCall.unimplemented();
    }

    @PluginMethod
    public void setResizeMode(PluginCall pluginCall) {
        pluginCall.unimplemented();
    }

    @PluginMethod
    public void getResizeMode(PluginCall pluginCall) {
        pluginCall.unimplemented();
    }

    @PluginMethod
    public void setScroll(PluginCall pluginCall) {
        pluginCall.unimplemented();
    }

    /* access modifiers changed from: package-private */
    public void onKeyboardEvent(String str, int i) {
        JSObject jSObject = new JSObject();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -662060934:
                if (str.equals("keyboardDidHide")) {
                    c = 0;
                    break;
                }
                break;
            case -661733835:
                if (str.equals("keyboardDidShow")) {
                    c = 1;
                    break;
                }
                break;
            case -34092741:
                if (str.equals("keyboardWillHide")) {
                    c = 2;
                    break;
                }
                break;
            case -33765642:
                if (str.equals("keyboardWillShow")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
                this.bridge.triggerWindowJSEvent(str);
                notifyListeners(str, jSObject);
                return;
            case 1:
            case 3:
                this.bridge.triggerWindowJSEvent(str, "{ 'keyboardHeight': " + i + " }");
                jSObject.put("keyboardHeight", i);
                notifyListeners(str, jSObject);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        this.implementation.setKeyboardEventListener((Keyboard.KeyboardEventListener) null);
    }
}
