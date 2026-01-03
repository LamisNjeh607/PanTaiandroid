package com.capacitorjs.plugins.network;

import android.util.Log;
import com.capacitorjs.plugins.network.Network;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Network")
public class NetworkPlugin extends Plugin {
    public static final String NETWORK_CHANGE_EVENT = "networkStatusChange";
    private Network implementation;
    private NetworkStatus prePauseNetworkStatus = null;

    public void load() {
        this.implementation = new Network(getContext());
        this.implementation.setStatusChangeListener(new NetworkPlugin$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(boolean z) {
        if (z) {
            JSObject jSObject = new JSObject();
            jSObject.put("connected", false);
            jSObject.put("connectionType", PluginMethod.RETURN_NONE);
            notifyListeners(NETWORK_CHANGE_EVENT, jSObject);
            return;
        }
        updateNetworkStatus();
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        this.implementation.setStatusChangeListener((Network.NetworkStatusChangeListener) null);
    }

    @PluginMethod
    public void getStatus(PluginCall pluginCall) {
        pluginCall.resolve(parseNetworkStatus(this.implementation.getNetworkStatus()));
    }

    /* access modifiers changed from: protected */
    public void handleOnResume() {
        this.implementation.startMonitoring();
        NetworkStatus networkStatus = this.implementation.getNetworkStatus();
        if (this.prePauseNetworkStatus != null && !networkStatus.connected && (this.prePauseNetworkStatus.connected || networkStatus.connectionType != this.prePauseNetworkStatus.connectionType)) {
            Log.d("Capacitor/NetworkPlugin", "Detected pre-pause and after-pause network status mismatch. Updating network status and notifying listeners.");
            updateNetworkStatus();
        }
        this.prePauseNetworkStatus = null;
    }

    /* access modifiers changed from: protected */
    public void handleOnPause() {
        this.prePauseNetworkStatus = this.implementation.getNetworkStatus();
        this.implementation.stopMonitoring();
    }

    private void updateNetworkStatus() {
        notifyListeners(NETWORK_CHANGE_EVENT, parseNetworkStatus(this.implementation.getNetworkStatus()));
    }

    private JSObject parseNetworkStatus(NetworkStatus networkStatus) {
        JSObject jSObject = new JSObject();
        jSObject.put("connected", networkStatus.connected);
        jSObject.put("connectionType", networkStatus.connectionType.getConnectionType());
        return jSObject;
    }
}
