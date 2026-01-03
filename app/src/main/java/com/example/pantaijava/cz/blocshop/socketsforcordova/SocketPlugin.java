package cz.blocshop.socketsforcordova;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.becvert.cordova.ZeroConf;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SocketPlugin extends CordovaPlugin {
    Map<String, SocketAdapter> socketAdapters = new HashMap();

    public boolean execute(String str, CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        if (str.equals("open")) {
            open(cordovaArgs, callbackContext);
            return true;
        } else if (str.equals("write")) {
            write(cordovaArgs, callbackContext);
            return true;
        } else if (str.equals("shutdownWrite")) {
            shutdownWrite(cordovaArgs, callbackContext);
            return true;
        } else if (str.equals(ZeroConf.ACTION_CLOSE)) {
            close(cordovaArgs, callbackContext);
            return true;
        } else if (str.equals("setOptions")) {
            setOptions(cordovaArgs, callbackContext);
            return true;
        } else {
            callbackContext.error(String.format("SocketPlugin - invalid action:", new Object[]{str}));
            return false;
        }
    }

    private void open(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        String string = cordovaArgs.getString(0);
        String string2 = cordovaArgs.getString(1);
        int i = cordovaArgs.getInt(2);
        SocketAdapterImpl socketAdapterImpl = new SocketAdapterImpl();
        socketAdapterImpl.setCloseEventHandler(new CloseEventHandler(string));
        socketAdapterImpl.setDataConsumer(new DataConsumer(string));
        socketAdapterImpl.setErrorEventHandler(new ErrorEventHandler(string));
        socketAdapterImpl.setOpenErrorEventHandler(new OpenErrorEventHandler(this, callbackContext));
        socketAdapterImpl.setOpenEventHandler(new OpenEventHandler(string, socketAdapterImpl, callbackContext));
        socketAdapterImpl.open(string2, i);
    }

    private void write(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        String string = cordovaArgs.getString(0);
        JSONArray jSONArray = cordovaArgs.getJSONArray(1);
        int length = jSONArray.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) jSONArray.getInt(i);
        }
        try {
            getSocketAdapter(string).write(bArr);
            callbackContext.success();
        } catch (IOException e) {
            callbackContext.error(e.toString());
        }
    }

    private void shutdownWrite(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        try {
            getSocketAdapter(cordovaArgs.getString(0)).shutdownWrite();
            callbackContext.success();
        } catch (IOException e) {
            callbackContext.error(e.toString());
        }
    }

    private void close(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        try {
            getSocketAdapter(cordovaArgs.getString(0)).close();
            callbackContext.success();
        } catch (IOException e) {
            callbackContext.error(e.toString());
        }
    }

    private void setOptions(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        String string = cordovaArgs.getString(0);
        JSONObject jSONObject = cordovaArgs.getJSONObject(1);
        SocketAdapter socketAdapter = getSocketAdapter(string);
        SocketAdapterOptions socketAdapterOptions = new SocketAdapterOptions();
        socketAdapterOptions.setKeepAlive(getBooleanPropertyFromJSON(jSONObject, "keepAlive"));
        socketAdapterOptions.setOobInline(getBooleanPropertyFromJSON(jSONObject, "oobInline"));
        socketAdapterOptions.setReceiveBufferSize(getIntegerPropertyFromJSON(jSONObject, "receiveBufferSize"));
        socketAdapterOptions.setSendBufferSize(getIntegerPropertyFromJSON(jSONObject, "sendBufferSize"));
        socketAdapterOptions.setSoLinger(getIntegerPropertyFromJSON(jSONObject, "soLinger"));
        socketAdapterOptions.setSoTimeout(getIntegerPropertyFromJSON(jSONObject, "soTimeout"));
        socketAdapterOptions.setTrafficClass(getIntegerPropertyFromJSON(jSONObject, "trafficClass"));
        try {
            socketAdapter.close();
            callbackContext.success();
        } catch (IOException e) {
            callbackContext.error(e.toString());
        }
    }

    private Boolean getBooleanPropertyFromJSON(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        return null;
    }

    private Integer getIntegerPropertyFromJSON(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        return null;
    }

    private SocketAdapter getSocketAdapter(String str) {
        if (this.socketAdapters.containsKey(str)) {
            return this.socketAdapters.get(str);
        }
        throw new IllegalStateException("Socket isn't connected.");
    }

    /* access modifiers changed from: private */
    public void dispatchEvent(JSONObject jSONObject) {
        this.webView.sendJavascript(String.format("window.Socket.dispatchEvent(%s);", new Object[]{jSONObject.toString()}));
    }

    private class CloseEventHandler implements Consumer<Boolean> {
        private String socketKey;

        public CloseEventHandler(String str) {
            this.socketKey = str;
        }

        public void accept(Boolean bool) {
            SocketPlugin.this.socketAdapters.remove(this.socketKey);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "Close");
                jSONObject.put("hasError", bool.booleanValue());
                jSONObject.put("socketKey", this.socketKey);
                SocketPlugin.this.dispatchEvent(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class DataConsumer implements Consumer<byte[]> {
        private String socketKey;

        public DataConsumer(String str) {
            this.socketKey = str;
        }

        public void accept(byte[] bArr) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "DataReceived");
                jSONObject.put("data", new JSONArray(toByteList(bArr)));
                jSONObject.put("socketKey", this.socketKey);
                SocketPlugin.this.dispatchEvent(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private List<Byte> toByteList(byte[] bArr) {
            ArrayList arrayList = new ArrayList(bArr.length);
            for (byte valueOf : bArr) {
                arrayList.add(Byte.valueOf(valueOf));
            }
            return arrayList;
        }
    }

    private class ErrorEventHandler implements Consumer<String> {
        private String socketKey;

        public ErrorEventHandler(String str) {
            this.socketKey = str;
        }

        public void accept(String str) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "Error");
                jSONObject.put("errorMessage", str);
                jSONObject.put("socketKey", this.socketKey);
                SocketPlugin.this.dispatchEvent(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class OpenErrorEventHandler implements Consumer<String> {
        private CallbackContext openCallbackContext;

        public OpenErrorEventHandler(SocketPlugin socketPlugin, CallbackContext callbackContext) {
            this.openCallbackContext = callbackContext;
        }

        public void accept(String str) {
            this.openCallbackContext.error(str);
        }
    }

    private class OpenEventHandler implements Consumer<Void> {
        private CallbackContext openCallbackContext;
        private SocketAdapter socketAdapter;
        private String socketKey;

        public OpenEventHandler(String str, SocketAdapter socketAdapter2, CallbackContext callbackContext) {
            this.socketKey = str;
            this.socketAdapter = socketAdapter2;
            this.openCallbackContext = callbackContext;
        }

        public void accept(Void voidR) {
            SocketPlugin.this.socketAdapters.put(this.socketKey, this.socketAdapter);
            this.openCallbackContext.success();
        }
    }
}
