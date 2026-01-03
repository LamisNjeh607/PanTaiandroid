package com.capacitorjs.plugins.screenorientation;

import android.content.res.Configuration;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ScreenOrientation")
public class ScreenOrientationPlugin extends Plugin {
    private ScreenOrientation implementation;

    public void load() {
        this.implementation = new ScreenOrientation(getActivity());
    }

    @PluginMethod
    public void orientation(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("type", this.implementation.getCurrentOrientationType());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void lock(PluginCall pluginCall) {
        String string = pluginCall.getString("orientation");
        if (string == null) {
            pluginCall.reject("Input option 'orientation' must be provided.");
            return;
        }
        this.implementation.lock(string);
        pluginCall.resolve();
    }

    @PluginMethod
    public void unlock(PluginCall pluginCall) {
        this.implementation.unlock();
        pluginCall.resolve();
    }

    public void handleOnConfigurationChanged(Configuration configuration) {
        super.handleOnConfigurationChanged(configuration);
        if (this.implementation.hasOrientationChanged(configuration.orientation)) {
            onOrientationChanged();
        }
    }

    private void onOrientationChanged() {
        JSObject jSObject = new JSObject();
        jSObject.put("type", this.implementation.getCurrentOrientationType());
        notifyListeners("screenOrientationChange", jSObject);
    }
}
