package com.example.pantaijava.com.capacitorjs.plugins.network;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import com.capacitorjs.plugins.network.NetworkStatus;

public class Network {
    private ConnectivityCallback connectivityCallback = new ConnectivityCallback();
    private ConnectivityManager connectivityManager;
    private Context context;
    private BroadcastReceiver receiver;
    /* access modifiers changed from: private */
    public NetworkStatusChangeListener statusChangeListener;

    interface NetworkStatusChangeListener {
        void onNetworkStatusChanged(boolean z);
    }

    class ConnectivityCallback extends ConnectivityManager.NetworkCallback {
        ConnectivityCallback() {
        }

        public void onLost(android.net.Network network) {
            super.onLost(network);
            Network.this.statusChangeListener.onNetworkStatusChanged(true);
        }

        public void onCapabilitiesChanged(android.net.Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Network.this.statusChangeListener.onNetworkStatusChanged(false);
        }
    }

    @SuppressLint("WrongConstant")
    public Network(Context context2) {
        this.context = context2;
        this.connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
    }

    public void setStatusChangeListener(NetworkStatusChangeListener networkStatusChangeListener) {
        this.statusChangeListener = networkStatusChangeListener;
    }

    public NetworkStatusChangeListener getStatusChangeListener() {
        return this.statusChangeListener;
    }

    @SuppressLint("WrongConstant")
    public NetworkStatus getNetworkStatus() {
        NetworkStatus networkStatus = new NetworkStatus();
        ConnectivityManager connectivityManager2 = this.connectivityManager;
        if (connectivityManager2 != null) {
            @SuppressLint("MissingPermission") android.net.Network activeNetwork = connectivityManager2.getActiveNetwork();
            ConnectivityManager connectivityManager3 = this.connectivityManager;
            @SuppressLint("MissingPermission") NetworkCapabilities networkCapabilities = connectivityManager3.getNetworkCapabilities(connectivityManager3.getActiveNetwork());
            if (!(activeNetwork == null || networkCapabilities == null)) {
                networkStatus.connected = networkCapabilities.hasCapability(16) && networkCapabilities.hasCapability(12);
                if (networkCapabilities.hasTransport(1)) {
                    networkStatus.connectionType = NetworkStatus.ConnectionType.WIFI;
                } else if (networkCapabilities.hasTransport(0)) {
                    networkStatus.connectionType = NetworkStatus.ConnectionType.CELLULAR;
                } else {
                    networkStatus.connectionType = NetworkStatus.ConnectionType.UNKNOWN;
                }
            }
        }
        return networkStatus;
    }

    private NetworkStatus getAndParseNetworkInfo() {
        NetworkStatus networkStatus = new NetworkStatus();
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            networkStatus.connected = activeNetworkInfo.isConnected();
            String typeName = activeNetworkInfo.getTypeName();
            if (typeName.equals("WIFI")) {
                networkStatus.connectionType = NetworkStatus.ConnectionType.WIFI;
            } else if (typeName.equals("MOBILE")) {
                networkStatus.connectionType = NetworkStatus.ConnectionType.CELLULAR;
            }
        }
        return networkStatus;
    }

    @SuppressLint("MissingPermission")
    public void startMonitoring() {
        this.connectivityManager.registerDefaultNetworkCallback(this.connectivityCallback);
    }

    public void startMonitoring(AppCompatActivity appCompatActivity) {
        appCompatActivity.registerReceiver(this.receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void stopMonitoring() {
        this.connectivityManager.unregisterNetworkCallback(this.connectivityCallback);
    }

    public void stopMonitoring(AppCompatActivity appCompatActivity) {
        appCompatActivity.unregisterReceiver(this.receiver);
    }
}
