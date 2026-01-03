package nz.co.soltius.cordova;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BluetoothClassicSerial extends CordovaPlugin {
    private static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    private static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String AVAILABLE = "available";
    private static final String BLUETOOTH_CONNECT = "android.permission.BLUETOOTH_CONNECT";
    private static final String BLUETOOTH_SCAN = "android.permission.BLUETOOTH_SCAN";
    private static final String CLEAR = "clear";
    private static final String CLEAR_DEVICE_DISCOVERED_LISTENER = "clearDeviceDiscoveredListener";
    private static int COMPILE_SDK_VERSION = -1;
    private static final String CONNECT = "connect";
    private static final String CONNECT_INSECURE = "connectInsecure";
    private static final boolean D = true;
    public static final String DEVICE_NAME = "device_name";
    private static final String DISCONNECT = "disconnect";
    private static final String DISCOVER_UNPAIRED = "discoverUnpaired";
    private static final String ENABLE = "enable";
    private static final String IS_CONNECTED = "isConnected";
    private static final String IS_ENABLED = "isEnabled";
    private static final String LIST = "list";
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_FAILED = 7;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_READ_RAW = 6;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_WRITE = 3;
    private static final String READ = "read";
    private static final String READ_UNTIL = "readUntil";
    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private static final int REQUEST_LIST_BONDED_DEVICES = 2;
    private static final int REQUEST_LIST_UNPAIRED_DEVICES = 3;
    private static final String SETTINGS = "showBluetoothSettings";
    private static final String SET_DEVICE_DISCOVERED_LISTENER = "setDeviceDiscoveredListener";
    private static final String SUBSCRIBE = "subscribe";
    private static final String SUBSCRIBE_RAW = "subscribeRaw";
    private static final String TAG = "BluetoothClassicSerial";
    public static final String TOAST = "toast";
    private static final String UNSUBSCRIBE = "unsubscribe";
    private static final String UNSUBSCRIBE_RAW = "unsubscribeRaw";
    private static final String WRITE = "write";
    private BluetoothAdapter bluetoothAdapter;
    private final HashMap<String, InterfaceContext> connections = new HashMap<>();
    private CallbackContext deviceDiscoveredCallback;
    private CallbackContext enableBluetoothCallback;
    private CallbackContext permissionCallback;

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        if (COMPILE_SDK_VERSION == -1) {
            COMPILE_SDK_VERSION = this.cordova.getContext().getApplicationContext().getApplicationInfo().targetSdkVersion;
        }
    }

    public boolean execute(String str, CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        LOG.d(TAG, "action = " + str);
        if (this.bluetoothAdapter == null) {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (str.equals(LIST)) {
            listBondedDevices(callbackContext);
            return true;
        } else if (str.equals(CONNECT)) {
            connect(cordovaArgs, true, callbackContext);
            return true;
        } else {
            int i = 0;
            if (str.equals(CONNECT_INSECURE)) {
                connect(cordovaArgs, false, callbackContext);
                return true;
            } else if (str.equals(DISCONNECT)) {
                disconnect(cordovaArgs, callbackContext);
                return true;
            } else if (str.equals(WRITE)) {
                String string = cordovaArgs.getString(0);
                byte[] arrayBuffer = cordovaArgs.getArrayBuffer(1);
                InterfaceContext interfaceContext = getInterfaceContext(string);
                if (interfaceContext != null) {
                    interfaceContext.bluetoothClassicSerialService.write(arrayBuffer);
                    callbackContext.success();
                    return true;
                }
                callbackContext.error("No Interface");
                return true;
            } else if (str.equals(AVAILABLE)) {
                InterfaceContext interfaceContext2 = getInterfaceContext(cordovaArgs.getString(0));
                if (interfaceContext2 != null) {
                    i = interfaceContext2.available();
                }
                callbackContext.success(i);
                return true;
            } else {
                String str2 = "";
                if (str.equals(READ)) {
                    InterfaceContext interfaceContext3 = getInterfaceContext(cordovaArgs.getString(0));
                    if (interfaceContext3 != null) {
                        str2 = interfaceContext3.read();
                    }
                    callbackContext.success(str2);
                    return true;
                } else if (str.equals(READ_UNTIL)) {
                    String string2 = cordovaArgs.getString(0);
                    String string3 = cordovaArgs.getString(1);
                    InterfaceContext interfaceContext4 = getInterfaceContext(string2);
                    if (interfaceContext4 != null) {
                        str2 = interfaceContext4.readUntil(string3);
                    }
                    callbackContext.success(str2);
                    return true;
                } else if (str.equals(SUBSCRIBE)) {
                    setContextSubscribe(cordovaArgs.getString(0), callbackContext, cordovaArgs.getString(1));
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                    pluginResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(pluginResult);
                    return true;
                } else if (str.equals(UNSUBSCRIBE)) {
                    setContextSubscribe(cordovaArgs.getString(0), (CallbackContext) null, (String) null);
                    callbackContext.success();
                    return true;
                } else if (str.equals(SUBSCRIBE_RAW)) {
                    setContextRawSubscribe(cordovaArgs.getString(0), callbackContext);
                    PluginResult pluginResult2 = new PluginResult(PluginResult.Status.NO_RESULT);
                    pluginResult2.setKeepCallback(true);
                    callbackContext.sendPluginResult(pluginResult2);
                    return true;
                } else if (str.equals(UNSUBSCRIBE_RAW)) {
                    setContextRawSubscribe(cordovaArgs.getString(0), (CallbackContext) null);
                    callbackContext.success();
                    return true;
                } else if (str.equals(IS_ENABLED)) {
                    isEnabled(callbackContext);
                    return true;
                } else if (str.equals(IS_CONNECTED)) {
                    isConnected(cordovaArgs, callbackContext);
                    return true;
                } else if (str.equals(CLEAR)) {
                    InterfaceContext interfaceContext5 = getInterfaceContext(cordovaArgs.getString(0));
                    if (interfaceContext5 != null) {
                        interfaceContext5.clearBuffer();
                    }
                    callbackContext.success();
                    return true;
                } else if (str.equals(SETTINGS)) {
                    this.cordova.getActivity().startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
                    callbackContext.success();
                    return true;
                } else if (str.equals(ENABLE)) {
                    this.enableBluetoothCallback = callbackContext;
                    this.cordova.startActivityForResult(this, new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
                    return true;
                } else if (str.equals(DISCOVER_UNPAIRED)) {
                    discoverUnpairedDevices(callbackContext);
                    return true;
                } else if (str.equals(SET_DEVICE_DISCOVERED_LISTENER)) {
                    this.deviceDiscoveredCallback = callbackContext;
                    return true;
                } else if (!str.equals(CLEAR_DEVICE_DISCOVERED_LISTENER)) {
                    return false;
                } else {
                    this.deviceDiscoveredCallback = null;
                    return true;
                }
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == -1) {
                Log.d(TAG, "User enabled Bluetooth");
                CallbackContext callbackContext = this.enableBluetoothCallback;
                if (callbackContext != null) {
                    callbackContext.success();
                }
            } else {
                Log.d(TAG, "User did *NOT* enable Bluetooth");
                CallbackContext callbackContext2 = this.enableBluetoothCallback;
                if (callbackContext2 != null) {
                    callbackContext2.error("User did not enable Bluetooth");
                }
            }
            this.enableBluetoothCallback = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) throws JSONException {
        int i2 = 0;
        while (i2 < strArr.length) {
            if (strArr[i2].equals(ACCESS_FINE_LOCATION) && iArr[i2] == -1) {
                LOG.d(TAG, "User *rejected* Fine Location Access");
                this.permissionCallback.error("Location permission not granted.");
                return;
            } else if (strArr[i2].equals(ACCESS_COARSE_LOCATION) && iArr[i2] == -1) {
                LOG.d(TAG, "User *rejected* Coarse Location Access");
                this.permissionCallback.error("Location permission not granted.");
                return;
            } else if (strArr[i2].equals(BLUETOOTH_SCAN) && iArr[i2] == -1) {
                LOG.d(TAG, "User *rejected* Bluetooth_Scan Access");
                this.permissionCallback.error("Bluetooth scan permission not granted.");
                return;
            } else if (!strArr[i2].equals(BLUETOOTH_CONNECT) || iArr[i2] != -1) {
                i2++;
            } else {
                LOG.d(TAG, "User *rejected* Bluetooth_Connect Access");
                this.permissionCallback.error("Bluetooth Connect permission not granted.");
                return;
            }
        }
        if (i == 2) {
            LOG.d(TAG, "User granted permission");
            listBondedDevices(this.permissionCallback);
            this.permissionCallback = null;
        } else if (i == 3) {
            LOG.d(TAG, "User granted permission");
            discoverUnpairedDevices(this.permissionCallback);
            this.permissionCallback = null;
        }
    }

    private List<String> getMissingPermissions() {
        ArrayList arrayList = new ArrayList();
        if (COMPILE_SDK_VERSION >= 31 && Build.VERSION.SDK_INT >= 31) {
            if (!PermissionHelper.hasPermission(this, BLUETOOTH_SCAN)) {
                arrayList.add(BLUETOOTH_SCAN);
            }
            if (!PermissionHelper.hasPermission(this, BLUETOOTH_CONNECT)) {
                arrayList.add(BLUETOOTH_CONNECT);
            }
        } else if (COMPILE_SDK_VERSION >= 29 && Build.VERSION.SDK_INT >= 29) {
            if (!PermissionHelper.hasPermission(this, ACCESS_FINE_LOCATION)) {
                arrayList.add(ACCESS_FINE_LOCATION);
            }
            if (this.preferences.getString("accessBackgroundLocation", "false") == "true" && !PermissionHelper.hasPermission(this, ACCESS_BACKGROUND_LOCATION)) {
                LOG.w(TAG, "ACCESS_BACKGROUND_LOCATION is being requested");
                arrayList.add(ACCESS_BACKGROUND_LOCATION);
            }
        } else if (!PermissionHelper.hasPermission(this, ACCESS_COARSE_LOCATION)) {
            arrayList.add(ACCESS_COARSE_LOCATION);
        }
        return arrayList;
    }

    private class InterfaceContext {
        public BluetoothClassicSerialService bluetoothClassicSerialService;
        public StringBuffer buffer;
        public CallbackContext connectCallback;
        public CallbackContext dataAvailableCallback;
        public String delimiter;
        /* access modifiers changed from: private */
        public boolean mConnected = false;
        private final Handler mHandler;
        public String macAddress;
        public CallbackContext rawDataAvailableCallback;

        public InterfaceContext(final BluetoothClassicSerial bluetoothClassicSerial, String str) {
            this.macAddress = str;
            AnonymousClass1 r0 = new Handler() {
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 1) {
                        Log.i(BluetoothClassicSerial.TAG, "MESSAGE_STATE_CHANGE: " + message.arg1);
                        int i2 = message.arg1;
                        if (i2 == 0) {
                            Log.i(BluetoothClassicSerial.TAG, "BluetoothClassicSerialService.STATE_NONE");
                        } else if (i2 == 1) {
                            Log.i(BluetoothClassicSerial.TAG, "BluetoothClassicSerialService.STATE_LISTEN");
                        } else if (i2 == 2) {
                            Log.i(BluetoothClassicSerial.TAG, "BluetoothClassicSerialService.STATE_CONNECTING");
                        } else if (i2 == 3) {
                            Log.i(BluetoothClassicSerial.TAG, "BluetoothClassicSerialService.STATE_CONNECTED");
                            if (!InterfaceContext.this.mConnected) {
                                InterfaceContext.this.mConnected = true;
                                InterfaceContext.this.notifyConnectionSuccess();
                            }
                        }
                    } else if (i == 2) {
                        String str = (String) message.obj;
                        if (InterfaceContext.this.buffer == null) {
                            InterfaceContext.this.buffer = new StringBuffer();
                        }
                        InterfaceContext.this.buffer.append(str);
                        if (InterfaceContext.this.dataAvailableCallback != null) {
                            InterfaceContext.this.sendDataToSubscriber();
                        }
                    } else if (i == 4) {
                        Log.i(BluetoothClassicSerial.TAG, message.getData().getString(BluetoothClassicSerial.DEVICE_NAME));
                    } else if (i == 5) {
                        String string = message.getData().getString(BluetoothClassicSerial.TOAST);
                        if (InterfaceContext.this.mConnected) {
                            InterfaceContext.this.mConnected = false;
                            InterfaceContext.this.notifyConnectionLost(string);
                        }
                    } else if (i != 6) {
                        if (i == 7) {
                            String string2 = message.getData().getString(BluetoothClassicSerial.TOAST);
                            if (InterfaceContext.this.connectCallback != null) {
                                if (InterfaceContext.this.mConnected) {
                                    InterfaceContext.this.mConnected = false;
                                }
                                new PluginResult(PluginResult.Status.ERROR, string2);
                                InterfaceContext.this.connectCallback.error(string2);
                                InterfaceContext.this.connectCallback = null;
                            }
                        }
                    } else if (InterfaceContext.this.rawDataAvailableCallback != null) {
                        InterfaceContext.this.sendRawDataToSubscriber((byte[]) message.obj);
                    }
                }
            };
            this.mHandler = r0;
            this.bluetoothClassicSerialService = new BluetoothClassicSerialService(r0);
            this.buffer = new StringBuffer();
            this.macAddress = str;
            this.dataAvailableCallback = null;
            this.delimiter = null;
        }

        public int available() {
            return this.buffer.length();
        }

        public void clearBuffer() {
            this.buffer.setLength(0);
        }

        public String read() {
            int length = this.buffer.length();
            String substring = this.buffer.substring(0, length);
            this.buffer.delete(0, length);
            return substring;
        }

        public String readUntil(String str) {
            int indexOf = this.buffer.indexOf(str, 0);
            if (indexOf <= -1) {
                return null;
            }
            String substring = this.buffer.substring(0, str.length() + indexOf);
            this.buffer.delete(0, indexOf + str.length());
            return substring;
        }

        /* access modifiers changed from: private */
        public void sendDataToSubscriber() {
            String readUntil = readUntil(this.delimiter);
            if (readUntil != null && readUntil.length() > 0) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, readUntil);
                pluginResult.setKeepCallback(true);
                this.dataAvailableCallback.sendPluginResult(pluginResult);
                sendDataToSubscriber();
            }
        }

        /* access modifiers changed from: private */
        public void sendRawDataToSubscriber(byte[] bArr) {
            if (bArr.length > 0) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, bArr);
                pluginResult.setKeepCallback(true);
                this.rawDataAvailableCallback.sendPluginResult(pluginResult);
            }
        }

        /* access modifiers changed from: private */
        public void notifyConnectionLost(String str) {
            CallbackContext callbackContext = this.connectCallback;
            if (callbackContext != null) {
                callbackContext.error(str);
                this.connectCallback = null;
            }
        }

        /* access modifiers changed from: private */
        public void notifyConnectionSuccess() {
            if (this.connectCallback != null) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
                pluginResult.setKeepCallback(true);
                this.connectCallback.sendPluginResult(pluginResult);
            }
        }
    }

    private void isEnabled(CallbackContext callbackContext) {
        if (this.bluetoothAdapter.isEnabled()) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
        } else {
            callbackContext.error("Bluetooth is disabled.");
        }
    }

    private void listBondedDevices(CallbackContext callbackContext) throws JSONException {
        List<String> missingPermissions = getMissingPermissions();
        if (missingPermissions.size() > 0) {
            this.permissionCallback = callbackContext;
            PermissionHelper.requestPermissions(this, 2, (String[]) missingPermissions.toArray(new String[0]));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (BluetoothDevice deviceToJSON : this.bluetoothAdapter.getBondedDevices()) {
            jSONArray.put(deviceToJSON(deviceToJSON));
        }
        callbackContext.success(jSONArray);
    }

    private void discoverUnpairedDevices(final CallbackContext callbackContext) throws JSONException {
        List<String> missingPermissions = getMissingPermissions();
        if (missingPermissions.size() > 0) {
            this.permissionCallback = callbackContext;
            PermissionHelper.requestPermissions(this, 3, (String[]) missingPermissions.toArray(new String[0]));
            return;
        }
        final CallbackContext callbackContext2 = this.deviceDiscoveredCallback;
        AnonymousClass1 r1 = new BroadcastReceiver() {
            private JSONArray unpairedDevices = new JSONArray();

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    try {
                        JSONObject r2 = BluetoothClassicSerial.this.deviceToJSON((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                        this.unpairedDevices.put(r2);
                        if (callbackContext2 != null) {
                            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, r2);
                            pluginResult.setKeepCallback(true);
                            callbackContext2.sendPluginResult(pluginResult);
                        }
                    } catch (JSONException e) {
                        Log.e(BluetoothClassicSerial.TAG, "Problem converting device to JSON", e);
                    }
                } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    callbackContext.success(this.unpairedDevices);
                    BluetoothClassicSerial.this.cordova.getActivity().unregisterReceiver(this);
                }
            }
        };
        AppCompatActivity activity = this.cordova.getActivity();
        activity.registerReceiver(r1, new IntentFilter("android.bluetooth.device.action.FOUND"));
        activity.registerReceiver(r1, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        this.bluetoothAdapter.startDiscovery();
    }

    /* access modifiers changed from: private */
    public JSONObject deviceToJSON(BluetoothDevice bluetoothDevice) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", bluetoothDevice.getName());
        jSONObject.put("address", bluetoothDevice.getAddress());
        jSONObject.put("id", bluetoothDevice.getAddress());
        if (bluetoothDevice.getBluetoothClass() != null) {
            jSONObject.put("class", bluetoothDevice.getBluetoothClass().getDeviceClass());
        }
        return jSONObject;
    }

    private void connect(CordovaArgs cordovaArgs, boolean z, CallbackContext callbackContext) throws JSONException {
        String string = cordovaArgs.getString(0);
        JSONArray jSONArray = cordovaArgs.getJSONArray(1);
        if (!this.bluetoothAdapter.isEnabled()) {
            callbackContext.error("Bluetooth is disabled.");
            return;
        }
        destroy(string);
        BluetoothDevice remoteDevice = this.bluetoothAdapter.getRemoteDevice(string);
        if (remoteDevice != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                UUID fromString = UUID.fromString(jSONArray.getString(i));
                InterfaceContext interfaceContext = this.connections.get(string);
                if (interfaceContext == null) {
                    interfaceContext = new InterfaceContext(this, string);
                    interfaceContext.connectCallback = callbackContext;
                    this.connections.put(string, interfaceContext);
                }
                if (interfaceContext != null) {
                    interfaceContext.bluetoothClassicSerialService.connect(remoteDevice, fromString, z);
                }
            }
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return;
        }
        callbackContext.error("Could not connect to " + string);
    }

    private void disconnect(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        if (cordovaArgs == null || cordovaArgs.getString(0) == null) {
            for (Map.Entry<String, InterfaceContext> value : this.connections.entrySet()) {
                InterfaceContext interfaceContext = (InterfaceContext) value.getValue();
                if (interfaceContext.bluetoothClassicSerialService != null) {
                    interfaceContext.bluetoothClassicSerialService.stop();
                }
            }
        } else {
            InterfaceContext interfaceContext2 = this.connections.get(cordovaArgs.getString(0));
            if (!(interfaceContext2 == null || interfaceContext2.bluetoothClassicSerialService == null)) {
                interfaceContext2.bluetoothClassicSerialService.stop();
            }
        }
        callbackContext.success();
    }

    private void destroy() {
        destroy((String) null);
    }

    private void destroy(String str) {
        Iterator<Map.Entry<String, InterfaceContext>> it = this.connections.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            InterfaceContext interfaceContext = (InterfaceContext) next.getValue();
            if (str == null || ((String) next.getKey()).equalsIgnoreCase(str)) {
                if (interfaceContext.bluetoothClassicSerialService != null) {
                    interfaceContext.bluetoothClassicSerialService.stop();
                }
                it.remove();
            }
        }
    }

    private void setContextSubscribe(String str, CallbackContext callbackContext, String str2) {
        InterfaceContext interfaceContext = getInterfaceContext(str);
        if (interfaceContext == null) {
            interfaceContext = new InterfaceContext(this, str);
            setInterfaceContext(str, interfaceContext);
        }
        if (interfaceContext != null) {
            interfaceContext.dataAvailableCallback = callbackContext;
            interfaceContext.delimiter = str2;
            setInterfaceContext(str, interfaceContext);
        }
    }

    private void setContextRawSubscribe(String str, CallbackContext callbackContext) {
        InterfaceContext interfaceContext = getInterfaceContext(str);
        if (interfaceContext == null) {
            interfaceContext = new InterfaceContext(this, str);
            setInterfaceContext(str, interfaceContext);
        }
        if (interfaceContext != null) {
            interfaceContext.rawDataAvailableCallback = callbackContext;
            setInterfaceContext(str, interfaceContext);
        }
    }

    private InterfaceContext getInterfaceContext(String str) {
        return this.connections.get(str);
    }

    private void setInterfaceContext(String str, InterfaceContext interfaceContext) {
        if (str != null) {
            if (interfaceContext == null) {
                interfaceContext = new InterfaceContext(this, str);
            }
            this.connections.put(str, interfaceContext);
        }
    }

    private void isConnected(CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        boolean z = false;
        InterfaceContext interfaceContext = this.connections.get(cordovaArgs.getString(0));
        if (!(interfaceContext == null || interfaceContext.bluetoothClassicSerialService == null || interfaceContext.bluetoothClassicSerialService.getState() != 3)) {
            z = true;
        }
        if (z) {
            callbackContext.success();
        } else {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, z));
        }
    }
}
