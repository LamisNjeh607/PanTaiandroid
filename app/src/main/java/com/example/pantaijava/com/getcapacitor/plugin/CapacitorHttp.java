package com.getcapacitor.plugin;

import android.webkit.JavascriptInterface;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.plugin.util.CapacitorHttpUrlConnection;
import com.getcapacitor.plugin.util.HttpRequestHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CapacitorPlugin(permissions = {@Permission(alias = "HttpWrite", strings = {"android.permission.WRITE_EXTERNAL_STORAGE"}), @Permission(alias = "HttpRead", strings = {"android.permission.READ_EXTERNAL_STORAGE"})})
public class CapacitorHttp extends Plugin {
    /* access modifiers changed from: private */
    public final Map<Runnable, PluginCall> activeRequests = new ConcurrentHashMap();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void load() {
        this.bridge.getWebView().addJavascriptInterface(this, "CapacitorHttpAndroidInterface");
        super.load();
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        super.handleOnDestroy();
        for (Map.Entry next : this.activeRequests.entrySet()) {
            Runnable runnable = (Runnable) next.getKey();
            PluginCall pluginCall = (PluginCall) next.getValue();
            if (pluginCall.getData().has("activeCapacitorHttpUrlConnection")) {
                try {
                    ((CapacitorHttpUrlConnection) pluginCall.getData().get("activeCapacitorHttpUrlConnection")).disconnect();
                    pluginCall.getData().remove("activeCapacitorHttpUrlConnection");
                } catch (Exception unused) {
                }
            }
            getBridge().releaseCall(pluginCall);
        }
        this.activeRequests.clear();
        this.executor.shutdownNow();
    }

    private void http(final PluginCall pluginCall, final String str) {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                try {
                    pluginCall.resolve(HttpRequestHandler.request(pluginCall, str, CapacitorHttp.this.getBridge()));
                } catch (Exception e) {
                    pluginCall.reject(e.getLocalizedMessage(), e.getClass().getSimpleName(), e);
                } catch (Throwable th) {
                    CapacitorHttp.this.activeRequests.remove(this);
                    throw th;
                }
                CapacitorHttp.this.activeRequests.remove(this);
            }
        };
        if (!this.executor.isShutdown()) {
            this.activeRequests.put(r0, pluginCall);
            this.executor.submit(r0);
            return;
        }
        pluginCall.reject("Failed to execute request - Http Plugin was shutdown");
    }

    @JavascriptInterface
    public boolean isEnabled() {
        return getBridge().getConfig().getPluginConfiguration("CapacitorHttp").getBoolean("enabled", false);
    }

    @PluginMethod
    public void request(PluginCall pluginCall) {
        http(pluginCall, (String) null);
    }

    @PluginMethod
    public void get(PluginCall pluginCall) {
        http(pluginCall, "GET");
    }

    @PluginMethod
    public void post(PluginCall pluginCall) {
        http(pluginCall, "POST");
    }

    @PluginMethod
    public void put(PluginCall pluginCall) {
        http(pluginCall, "PUT");
    }

    @PluginMethod
    public void patch(PluginCall pluginCall) {
        http(pluginCall, "PATCH");
    }

    @PluginMethod
    public void delete(PluginCall pluginCall) {
        http(pluginCall, "DELETE");
    }
}
