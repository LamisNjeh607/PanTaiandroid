package com.example.pantaijava.com.getcapacitor;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BridgeActivity extends AppCompatActivity {
    protected int activityDepth = 0;
    protected Bridge bridge;
    protected final Bridge.Builder bridgeBuilder = new Bridge.Builder((AppCompatActivity) this);
    protected com.getcapacitor.CapConfig config;
    protected List<Class<? extends com.getcapacitor.Plugin>> initialPlugins = new ArrayList();
    protected boolean keepRunning = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.bridgeBuilder.setInstanceState(bundle);
        getApplication().setTheme(R.style.AppTheme_NoActionBar);
        setTheme(R.style.AppTheme_NoActionBar);
        try {
            setContentView(R.layout.capacitor_bridge_layout_main);
            this.bridgeBuilder.addPlugins(new com.getcapacitor.PluginManager(getAssets()).loadPluginClasses());
            load();
        } catch (Exception unused) {
            setContentView(R.layout.no_webview);
        }
    }

    /* access modifiers changed from: protected */
    public void load() {
        com.getcapacitor.Logger.debug("Starting BridgeActivity");
        Bridge create = this.bridgeBuilder.addPlugins(this.initialPlugins).setConfig(this.config).create();
        this.bridge = create;
        this.keepRunning = create.shouldKeepRunning();
        onNewIntent(getIntent());
    }

    public void registerPlugin(Class<? extends com.getcapacitor.Plugin> cls) {
        this.bridgeBuilder.addPlugin(cls);
    }

    public void registerPlugins(List<Class<? extends com.getcapacitor.Plugin>> list) {
        this.bridgeBuilder.addPlugins(list);
    }

    public Bridge getBridge() {
        return this.bridge;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.bridge.saveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        this.activityDepth++;
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            bridge2.onStart();
            com.getcapacitor.Logger.debug("App started");
        }
    }

    public void onRestart() {
        super.onRestart();
        this.bridge.onRestart();
        com.getcapacitor.Logger.debug("App restarted");
    }

    public void onResume() {
        super.onResume();
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            bridge2.getApp().fireStatusChange(true);
            this.bridge.onResume();
            com.getcapacitor.Logger.debug("App resumed");
        }
    }

    public void onPause() {
        super.onPause();
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            bridge2.onPause();
            com.getcapacitor.Logger.debug("App paused");
        }
    }

    public void onStop() {
        super.onStop();
        if (this.bridge != null) {
            int max = Math.max(0, this.activityDepth - 1);
            this.activityDepth = max;
            if (max == 0) {
                this.bridge.getApp().fireStatusChange(false);
            }
            this.bridge.onStop();
            com.getcapacitor.Logger.debug("App stopped");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            bridge2.onDestroy();
            com.getcapacitor.Logger.debug("App destroyed");
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.bridge.onDetachedFromWindow();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Bridge bridge2 = this.bridge;
        if (bridge2 != null && !bridge2.onRequestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Bridge bridge2 = this.bridge;
        if (bridge2 != null && !bridge2.onActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bridge bridge2 = this.bridge;
        if (bridge2 != null && intent != null) {
            bridge2.onNewIntent(intent);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Bridge bridge2 = this.bridge;
        if (bridge2 != null) {
            bridge2.onConfigurationChanged(configuration);
        }
    }
}
