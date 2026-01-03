package com.example.pantaijava.com.capacitorjs.plugins.haptics;


import android.Manifest;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import com.example.pantaijava.com.capacitorjs.plugins.haptics.arguments.HapticsImpactType;
import com.example.pantaijava.com.capacitorjs.plugins.haptics.arguments.HapticsNotificationType;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Haptics", permissions = null)
public class HapticsPlugin extends Plugin {
    private Haptics implementation;

    public void load() {
        this.implementation = new Haptics(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresPermission(Manifest.permission.VIBRATE)
    @PluginMethod
    public void vibrate(PluginCall pluginCall) {
        this.implementation.vibrate(pluginCall.getInt("duration", 300).intValue());
        com.getcapacitor.JSObject jSObject = new com.getcapacitor.JSObject();
        pluginCall.resolve( jSObject );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresPermission(Manifest.permission.VIBRATE)
    @PluginMethod
    public void impact(PluginCall pluginCall) {
        this.implementation.performHaptics( HapticsImpactType.fromString(pluginCall.getString("style")));
        pluginCall.resolve( jSObject );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresPermission(Manifest.permission.VIBRATE)
    @PluginMethod
    public void notification(PluginCall pluginCall) {
        this.implementation.performHaptics( HapticsNotificationType.fromString(pluginCall.getString("type")));
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void selectionStart(PluginCall pluginCall) {
        this.implementation.selectionStart();
        pluginCall.resolve( jSObject );
    }

    @RequiresPermission(Manifest.permission.VIBRATE)
    @PluginMethod
    public void selectionChanged(PluginCall pluginCall) {
        this.implementation.selectionChanged();
        pluginCall.resolve( jSObject );
    }

    @PluginMethod
    public void selectionEnd(PluginCall pluginCall) {
        this.implementation.selectionEnd();
        pluginCall.resolve( jSObject );
    }
}
