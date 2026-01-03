package com.example.pantaijava.com.capacitorjs.plugins.network;

import androidx.core.os.EnvironmentCompat;
import com.getcapacitor.PluginMethod;

public class NetworkStatus {
    public boolean connected = false;
    public ConnectionType connectionType = ConnectionType.NONE;

    public enum ConnectionType {
        WIFI("wifi"),
        CELLULAR("cellular"),
        NONE(PluginMethod.RETURN_NONE),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
        
        private String connectionType;

        private ConnectionType(String str) {
            this.connectionType = str;
        }

        public String getConnectionType() {
            return this.connectionType;
        }
    }
}
