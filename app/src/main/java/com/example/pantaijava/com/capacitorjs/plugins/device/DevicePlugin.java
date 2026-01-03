package com.example.pantaijava.com.capacitorjs.plugins.device;

import android.os.Build;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.Locale;

@CapacitorPlugin(name = "Device")
public class DevicePlugin extends Plugin {
    private Device implementation;

    public void load() {
        this.implementation = new Device(getContext());
    }

    @PluginMethod
    public void getId(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("identifier", this.implementation.getUuid());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getInfo(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("memUsed", this.implementation.getMemUsed());
        jSObject.put("model", Build.MODEL);
        jSObject.put("operatingSystem", "android");
        jSObject.put("osVersion", Build.VERSION.RELEASE);
        jSObject.put("androidSDKVersion", Build.VERSION.SDK_INT);
        jSObject.put("platform", this.implementation.getPlatform());
        jSObject.put("manufacturer", Build.MANUFACTURER);
        jSObject.put("isVirtual", this.implementation.isVirtual());
        jSObject.put("name", this.implementation.getName());
        jSObject.put("webViewVersion", this.implementation.getWebViewVersion());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getBatteryInfo(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("batteryLevel", (double) this.implementation.getBatteryLevel());
        jSObject.put("isCharging", this.implementation.isCharging());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getLanguageCode(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", Locale.getDefault().getLanguage());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getLanguageTag(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", Locale.getDefault().toLanguageTag());
        pluginCall.resolve(jSObject);
    }
}
