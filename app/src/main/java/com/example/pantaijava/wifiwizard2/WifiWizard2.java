package wifiwizard2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiWizard2 extends CordovaPlugin {
    private static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String ADD_NETWORK = "add";
    private static final int API_VERSION = Build.VERSION.SDK_INT;
    private static final String CAN_CONNECT_TO_INTERNET = "canConnectToInternet";
    private static final String CAN_CONNECT_TO_ROUTER = "canConnectToRouter";
    private static final String CAN_PING_WIFI_ROUTER = "canPingWifiRouter";
    private static final String CONNECT_NETWORK = "connect";
    private static final String DISABLE_NETWORK = "disable";
    private static final String DISCONNECT = "disconnect";
    private static final String DISCONNECT_NETWORK = "disconnectNetwork";
    private static final String ENABLE_NETWORK = "enable";
    private static final String GET_CONNECTED_BSSID = "getConnectedBSSID";
    private static final String GET_CONNECTED_NETWORKID = "getConnectedNetworkID";
    private static final String GET_CONNECTED_SSID = "getConnectedSSID";
    private static final String GET_SCAN_RESULTS = "getScanResults";
    private static final String GET_SSID_NET_ID = "getSSIDNetworkID";
    private static final String GET_WIFI_IP_ADDRESS = "getWifiIP";
    private static final String GET_WIFI_IP_INFO = "getWifiIPInfo";
    private static final String GET_WIFI_ROUTER_IP_ADDRESS = "getWifiRouterIP";
    private static final String IS_CONNECTED_TO_INTERNET = "isConnectedToInternet";
    private static final String IS_WIFI_ENABLED = "isWifiEnabled";
    private static int LAST_NET_ID = -1;
    private static final String LIST_NETWORKS = "listNetworks";
    private static final int LOCATION_REQUEST_CODE = 2;
    private static final IntentFilter NETWORK_STATE_CHANGED_FILTER;
    private static final String REASSOCIATE = "reassociate";
    private static final String RECONNECT = "reconnect";
    private static final String REMOVE_NETWORK = "remove";
    private static final String REQUEST_FINE_LOCATION = "requestFineLocation";
    private static final String SCAN = "scan";
    private static final int SCAN_CODE = 1;
    private static final int SCAN_RESULTS_CODE = 0;
    private static final String SET_WIFI_ENABLED = "setWifiEnabled";
    private static final String START_SCAN = "startScan";
    private static final String TAG = "WifiWizard2";
    private static final int WIFI_SERVICE_INFO_CODE = 3;
    private static boolean bssidRequested = false;
    private CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public AP desired;
    private ConnectivityManager.NetworkCallback networkCallback;
    private final BroadcastReceiver networkChangedReceiver = new NetworkChangedReceiver();
    private JSONArray passedData;
    private AP previous;
    /* access modifiers changed from: private */
    public WifiManager wifiManager;

    static {
        IntentFilter intentFilter = new IntentFilter();
        NETWORK_STATE_CHANGED_FILTER = intentFilter;
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
    }

    private static boolean getHexKey(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length != 10 && length != 26 && length != 58) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && (charAt < 'A' || charAt > 'F'))) {
                return false;
            }
        }
        return true;
    }

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.wifiManager = (WifiManager) cordovaInterface.getActivity().getApplicationContext().getSystemService("wifi");
        this.connectivityManager = (ConnectivityManager) cordovaInterface.getActivity().getApplicationContext().getSystemService("connectivity");
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        this.passedData = jSONArray;
        if (str.equals(IS_WIFI_ENABLED)) {
            isWifiEnabled(callbackContext2);
            return true;
        } else if (str.equals(SET_WIFI_ENABLED)) {
            setWifiEnabled(callbackContext2, jSONArray);
            return true;
        } else if (str.equals(REQUEST_FINE_LOCATION)) {
            requestLocationPermission(2);
            return true;
        } else if (str.equals(GET_WIFI_ROUTER_IP_ADDRESS)) {
            String wiFiRouterIP = getWiFiRouterIP();
            if (wiFiRouterIP == null || wiFiRouterIP.equals("0.0.0.0")) {
                callbackContext2.error("NO_VALID_ROUTER_IP_FOUND");
                return true;
            }
            callbackContext2.success(wiFiRouterIP);
            return true;
        } else if (str.equals(GET_WIFI_IP_ADDRESS) || str.equals(GET_WIFI_IP_INFO)) {
            String[] wiFiIPAddress = getWiFiIPAddress();
            String str2 = wiFiIPAddress[0];
            String str3 = wiFiIPAddress[1];
            if (str2 == null || str2.equals("0.0.0.0")) {
                callbackContext2.error("NO_VALID_IP_IDENTIFIED");
                return true;
            } else if (str.equals(GET_WIFI_IP_ADDRESS)) {
                callbackContext2.success(str2);
                return true;
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ip", str2);
                jSONObject.put("subnet", str3);
                callbackContext2.success(jSONObject);
                return true;
            }
        } else if (!verifyWifiEnabled()) {
            callbackContext2.error("WIFI_NOT_ENABLED");
            return true;
        } else {
            if (str.equals(ADD_NETWORK)) {
                add(callbackContext2, jSONArray);
            } else if (str.equals(IS_CONNECTED_TO_INTERNET)) {
                canConnectToInternet(callbackContext2, true);
            } else if (str.equals(CAN_CONNECT_TO_INTERNET)) {
                canConnectToInternet(callbackContext2, false);
            } else if (str.equals(CAN_PING_WIFI_ROUTER)) {
                canConnectToRouter(callbackContext2, true);
            } else if (str.equals(CAN_CONNECT_TO_ROUTER)) {
                canConnectToRouter(callbackContext2, false);
            } else if (str.equals(ENABLE_NETWORK)) {
                enable(callbackContext2, jSONArray);
            } else if (str.equals(DISABLE_NETWORK)) {
                disable(callbackContext2, jSONArray);
            } else if (str.equals(GET_SSID_NET_ID)) {
                getSSIDNetworkID(callbackContext2, jSONArray);
            } else if (str.equals(REASSOCIATE)) {
                reassociate(callbackContext2);
            } else if (str.equals(RECONNECT)) {
                reconnect(callbackContext2);
            } else if (str.equals(SCAN)) {
                scan(callbackContext2, jSONArray);
            } else if (str.equals(REMOVE_NETWORK)) {
                remove(callbackContext2, jSONArray);
            } else if (str.equals(CONNECT_NETWORK)) {
                connect(callbackContext2, jSONArray);
            } else if (str.equals(DISCONNECT_NETWORK)) {
                disconnectNetwork(callbackContext2, jSONArray);
            } else if (str.equals(LIST_NETWORKS)) {
                listNetworks(callbackContext2);
            } else if (str.equals(START_SCAN)) {
                startScan(callbackContext2);
            } else if (str.equals(GET_SCAN_RESULTS)) {
                getScanResults(callbackContext2, jSONArray);
            } else if (str.equals(DISCONNECT)) {
                disconnect(callbackContext2);
            } else if (str.equals(GET_CONNECTED_SSID)) {
                getConnectedSSID(callbackContext2);
            } else if (str.equals(GET_CONNECTED_BSSID)) {
                getConnectedBSSID(callbackContext2);
            } else if (str.equals(GET_CONNECTED_NETWORKID)) {
                getConnectedNetworkID(callbackContext2);
            } else {
                callbackContext2.error("Incorrect action parameter: " + str);
                return false;
            }
            return true;
        }
    }

    private boolean scan(final CallbackContext callbackContext2, final JSONArray jSONArray) {
        Log.v(TAG, "Entering startScan");
        final ScanSyncContext scanSyncContext = new ScanSyncContext();
        AnonymousClass1 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.v(WifiWizard2.TAG, "Entering onReceive");
                synchronized (scanSyncContext) {
                    if (scanSyncContext.finished) {
                        Log.v(WifiWizard2.TAG, "In onReceive, already finished");
                        return;
                    }
                    scanSyncContext.finished = true;
                    context.unregisterReceiver(this);
                    Log.v(WifiWizard2.TAG, "In onReceive, success");
                    boolean unused = WifiWizard2.this.getScanResults(callbackContext2, jSONArray);
                }
            }
        };
        Context applicationContext = this.cordova.getActivity().getApplicationContext();
        Log.v(TAG, "Submitting timeout to threadpool");
        final Context context = applicationContext;
        final AnonymousClass1 r6 = r0;
        final CallbackContext callbackContext3 = callbackContext2;
        this.cordova.getThreadPool().submit(new Runnable(this) {
            public void run() {
                Log.v(WifiWizard2.TAG, "Entering timeout");
                try {
                    Thread.sleep(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
                } catch (InterruptedException e) {
                    Log.e(WifiWizard2.TAG, "Received InterruptedException e, " + e);
                }
                Log.v(WifiWizard2.TAG, "Thread sleep done");
                synchronized (scanSyncContext) {
                    if (scanSyncContext.finished) {
                        Log.v(WifiWizard2.TAG, "In timeout, already finished");
                        return;
                    }
                    scanSyncContext.finished = true;
                    context.unregisterReceiver(r6);
                    Log.v(WifiWizard2.TAG, "In timeout, error");
                    callbackContext3.error("TIMEOUT_WAITING_FOR_SCAN");
                }
            }
        });
        Log.v(TAG, "Registering broadcastReceiver");
        applicationContext.registerReceiver(r0, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        if (!this.wifiManager.startScan()) {
            Log.v(TAG, "Scan failed");
            callbackContext2.error("SCAN_FAILED");
            return false;
        }
        Log.v(TAG, "Starting wifi scan");
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00f8 A[Catch:{ Exception -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0106 A[Catch:{ Exception -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0118 A[Catch:{ Exception -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x012b A[Catch:{ Exception -> 0x0131 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean add(org.apache.cordova.CallbackContext r11, org.json.JSONArray r12) {
        /*
            r10 = this;
            java.lang.String r0 = "\""
            java.lang.String r1 = "WifiWizard2: add entered."
            java.lang.String r2 = "WifiWizard2"
            android.util.Log.d(r2, r1)
            android.net.wifi.WifiConfiguration r1 = new android.net.wifi.WifiConfiguration
            r1.<init>()
            r3 = 0
            java.lang.String r4 = r12.getString(r3)     // Catch:{ Exception -> 0x0131 }
            r5 = 1
            java.lang.String r6 = r12.getString(r5)     // Catch:{ Exception -> 0x0131 }
            r7 = 2
            java.lang.String r8 = r12.getString(r7)     // Catch:{ Exception -> 0x0131 }
            r9 = 3
            boolean r12 = r12.getBoolean(r9)     // Catch:{ Exception -> 0x0131 }
            r1.hiddenSSID = r12     // Catch:{ Exception -> 0x0131 }
            java.lang.String r12 = "WPA"
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x0131 }
            if (r12 != 0) goto L_0x00c3
            java.lang.String r12 = "WPA2"
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x0131 }
            if (r12 == 0) goto L_0x0036
            goto L_0x00c3
        L_0x0036:
            java.lang.String r12 = "WEP"
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x0131 }
            if (r12 == 0) goto L_0x00a2
            r1.SSID = r4     // Catch:{ Exception -> 0x0131 }
            boolean r12 = getHexKey(r8)     // Catch:{ Exception -> 0x0131 }
            if (r12 == 0) goto L_0x004b
            java.lang.String[] r12 = r1.wepKeys     // Catch:{ Exception -> 0x0131 }
            r12[r3] = r8     // Catch:{ Exception -> 0x0131 }
            goto L_0x0060
        L_0x004b:
            java.lang.String[] r12 = r1.wepKeys     // Catch:{ Exception -> 0x0131 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0131 }
            r6.<init>(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x0131 }
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0131 }
            r12[r3] = r0     // Catch:{ Exception -> 0x0131 }
        L_0x0060:
            r1.wepTxKeyIndex = r3     // Catch:{ Exception -> 0x0131 }
            r1.status = r7     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r7)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r9)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedKeyManagement     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedAuthAlgorithms     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedAuthAlgorithms     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedPairwiseCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedPairwiseCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r7)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedProtocols     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedProtocols     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            int r12 = r10.ssidToNetworkId(r4)     // Catch:{ Exception -> 0x0131 }
            r1.networkId = r12     // Catch:{ Exception -> 0x0131 }
            goto L_0x00f2
        L_0x00a2:
            java.lang.String r12 = "NONE"
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x0131 }
            if (r12 == 0) goto L_0x00b8
            r1.SSID = r4     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedKeyManagement     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            int r12 = r10.ssidToNetworkId(r4)     // Catch:{ Exception -> 0x0131 }
            r1.networkId = r12     // Catch:{ Exception -> 0x0131 }
            goto L_0x00f2
        L_0x00b8:
            java.lang.String r12 = "Wifi Authentication Type Not Supported."
            android.util.Log.d(r2, r12)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r12 = "AUTH_TYPE_NOT_SUPPORTED"
            r11.error((java.lang.String) r12)     // Catch:{ Exception -> 0x0131 }
            return r3
        L_0x00c3:
            r1.SSID = r4     // Catch:{ Exception -> 0x0131 }
            r1.preSharedKey = r8     // Catch:{ Exception -> 0x0131 }
            r1.status = r7     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r7)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedGroupCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r9)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedKeyManagement     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedPairwiseCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedPairwiseCiphers     // Catch:{ Exception -> 0x0131 }
            r12.set(r7)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedProtocols     // Catch:{ Exception -> 0x0131 }
            r12.set(r5)     // Catch:{ Exception -> 0x0131 }
            java.util.BitSet r12 = r1.allowedProtocols     // Catch:{ Exception -> 0x0131 }
            r12.set(r3)     // Catch:{ Exception -> 0x0131 }
            int r12 = r10.ssidToNetworkId(r4)     // Catch:{ Exception -> 0x0131 }
            r1.networkId = r12     // Catch:{ Exception -> 0x0131 }
        L_0x00f2:
            int r12 = API_VERSION     // Catch:{ Exception -> 0x0131 }
            r0 = 26
            if (r12 >= r0) goto L_0x0101
            android.net.wifi.WifiManager r4 = r10.wifiManager     // Catch:{ Exception -> 0x0131 }
            int r4 = getMaxWifiPriority(r4)     // Catch:{ Exception -> 0x0131 }
            int r4 = r4 + r5
            r1.priority = r4     // Catch:{ Exception -> 0x0131 }
        L_0x0101:
            int r4 = r1.networkId     // Catch:{ Exception -> 0x0131 }
            r6 = -1
            if (r4 != r6) goto L_0x0118
            android.net.wifi.WifiManager r4 = r10.wifiManager     // Catch:{ Exception -> 0x0131 }
            int r1 = r4.addNetwork(r1)     // Catch:{ Exception -> 0x0131 }
            if (r1 <= r6) goto L_0x0112
            r11.success((int) r1)     // Catch:{ Exception -> 0x0131 }
            goto L_0x0129
        L_0x0112:
            java.lang.String r1 = "ERROR_ADDING_NETWORK"
            r11.error((java.lang.String) r1)     // Catch:{ Exception -> 0x0131 }
            goto L_0x0129
        L_0x0118:
            android.net.wifi.WifiManager r4 = r10.wifiManager     // Catch:{ Exception -> 0x0131 }
            int r1 = r4.updateNetwork(r1)     // Catch:{ Exception -> 0x0131 }
            if (r1 <= r6) goto L_0x0124
            r11.success((int) r1)     // Catch:{ Exception -> 0x0131 }
            goto L_0x0129
        L_0x0124:
            java.lang.String r1 = "ERROR_UPDATING_NETWORK"
            r11.error((java.lang.String) r1)     // Catch:{ Exception -> 0x0131 }
        L_0x0129:
            if (r12 >= r0) goto L_0x0130
            android.net.wifi.WifiManager r12 = r10.wifiManager     // Catch:{ Exception -> 0x0131 }
            r12.saveConfiguration()     // Catch:{ Exception -> 0x0131 }
        L_0x0130:
            return r5
        L_0x0131:
            r12 = move-exception
            java.lang.String r0 = r12.getMessage()
            r11.error((java.lang.String) r0)
            java.lang.String r11 = r12.getMessage()
            android.util.Log.d(r2, r11)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: wifiwizard2.WifiWizard2.add(org.apache.cordova.CallbackContext, org.json.JSONArray):boolean");
    }

    private void enable(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: enable entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("ENABLE_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: enable invalid data.");
            return;
        }
        try {
            String string = jSONArray.getString(0);
            String string2 = jSONArray.getString(1);
            String string3 = jSONArray.getString(2);
            int ssidToNetworkId = ssidToNetworkId(string);
            if (ssidToNetworkId > -1) {
                try {
                    Log.d(TAG, "Valid networkIdToEnable: attempting connection");
                    if (string2.equals("true")) {
                        registerBindALL(ssidToNetworkId);
                    }
                    if (!this.wifiManager.enableNetwork(ssidToNetworkId, true)) {
                        callbackContext2.error("ERROR_ENABLING_NETWORK");
                    } else if (string3.equals("true")) {
                        callbackContext2.success("NETWORK_ENABLED");
                    } else {
                        new ConnectAsync().execute(new Object[]{callbackContext2, Integer.valueOf(ssidToNetworkId)});
                    }
                } catch (Exception e) {
                    callbackContext2.error(e.getMessage());
                    Log.d(TAG, e.getMessage());
                }
            } else {
                callbackContext2.error("UNABLE_TO_ENABLE");
            }
        } catch (Exception e2) {
            callbackContext2.error(e2.getMessage());
            Log.d(TAG, e2.getMessage());
        }
    }

    private boolean disable(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: disable entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("DISABLE_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: disable invalid data");
            return false;
        }
        try {
            String string = jSONArray.getString(0);
            int ssidToNetworkId = ssidToNetworkId(string);
            if (ssidToNetworkId > 0) {
                try {
                    if (this.wifiManager.disableNetwork(ssidToNetworkId)) {
                        maybeResetBindALL();
                        callbackContext2.success("Network " + string + " disabled!");
                        return true;
                    }
                    callbackContext2.error("UNABLE_TO_DISABLE");
                    return true;
                } catch (Exception e) {
                    callbackContext2.error(e.getMessage());
                    Log.d(TAG, e.getMessage());
                    return false;
                }
            } else {
                callbackContext2.error("DISABLE_NETWORK_NOT_FOUND");
                Log.d(TAG, "WifiWizard2: Network not found to disable.");
                return false;
            }
        } catch (Exception e2) {
            callbackContext2.error(e2.getMessage());
            Log.d(TAG, e2.getMessage());
            return false;
        }
    }

    private boolean remove(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: remove entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("REMOVE_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: remove data invalid");
            return false;
        }
        try {
            int ssidToNetworkId = ssidToNetworkId(jSONArray.getString(0));
            if (ssidToNetworkId <= -1) {
                callbackContext2.error("REMOVE_NETWORK_NOT_FOUND");
                Log.d(TAG, "WifiWizard2: Network not found, can't remove.");
                return false;
            } else if (this.wifiManager.removeNetwork(ssidToNetworkId)) {
                if (API_VERSION < 26) {
                    this.wifiManager.saveConfiguration();
                }
                callbackContext2.success("NETWORK_REMOVED");
                return true;
            } else {
                callbackContext2.error("UNABLE_TO_REMOVE");
                return true;
            }
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    private void connect(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: connect entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("CONNECT_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: connect invalid data.");
            return;
        }
        try {
            String string = jSONArray.getString(0);
            String string2 = jSONArray.getString(1);
            int ssidToNetworkId = ssidToNetworkId(string);
            if (ssidToNetworkId > -1) {
                Log.d(TAG, "Valid networkIdToConnect: attempting connection");
                if (string2.equals("true")) {
                    registerBindALL(ssidToNetworkId);
                }
                if (API_VERSION < 26) {
                    this.wifiManager.disableNetwork(ssidToNetworkId);
                }
                this.wifiManager.enableNetwork(ssidToNetworkId, true);
                new ConnectAsync().execute(new Object[]{callbackContext2, Integer.valueOf(ssidToNetworkId)});
                return;
            }
            callbackContext2.error("INVALID_NETWORK_ID_TO_CONNECT");
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
        }
    }

    private class ConnectAsync extends AsyncTask<Object, Void, String[]> {
        CallbackContext callbackContext;

        private ConnectAsync() {
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String[] strArr) {
            String str = strArr[0];
            String str2 = strArr[1];
            if (str != null) {
                this.callbackContext.error(str);
            } else {
                this.callbackContext.success(str2);
            }
        }

        /* access modifiers changed from: protected */
        public String[] doInBackground(Object... objArr) {
            this.callbackContext = objArr[0];
            int intValue = objArr[1].intValue();
            int i = 0;
            while (i < 15) {
                WifiInfo connectionInfo = WifiWizard2.this.wifiManager.getConnectionInfo();
                NetworkInfo.DetailedState detailedStateOf = WifiInfo.getDetailedStateOf(connectionInfo.getSupplicantState());
                if (connectionInfo.getNetworkId() != intValue || (detailedStateOf != NetworkInfo.DetailedState.CONNECTED && (detailedStateOf != NetworkInfo.DetailedState.OBTAINING_IPADDR || connectionInfo.getIpAddress() == 0))) {
                    i++;
                    Log.d(WifiWizard2.TAG, "WifiWizard: Got " + detailedStateOf.name() + " on " + i + " out of 15");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(WifiWizard2.TAG, e.getMessage());
                        return new String[]{"INTERRUPT_EXCEPT_WHILE_CONNECTING", null};
                    }
                } else {
                    return new String[]{null, "NETWORK_CONNECTION_COMPLETED"};
                }
            }
            Log.d(WifiWizard2.TAG, "WifiWizard: Network failed to finish connecting within the timeout");
            return new String[]{"CONNECT_FAILED_TIMEOUT", null};
        }
    }

    private boolean disconnectNetwork(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: disconnectNetwork entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("DISCONNECT_NET_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: disconnectNetwork invalid data");
            return false;
        }
        try {
            String string = jSONArray.getString(0);
            int ssidToNetworkId = ssidToNetworkId(string);
            if (ssidToNetworkId <= 0) {
                callbackContext2.error("DISCONNECT_NET_ID_NOT_FOUND");
                Log.d(TAG, "WifiWizard2: Network not found to disconnect.");
                return false;
            } else if (this.wifiManager.disableNetwork(ssidToNetworkId)) {
                maybeResetBindALL();
                if (this.wifiManager.removeNetwork(ssidToNetworkId)) {
                    callbackContext2.success("Network " + string + " disconnected and removed!");
                    return true;
                }
                callbackContext2.error("DISCONNECT_NET_REMOVE_ERROR");
                Log.d(TAG, "WifiWizard2: Unable to remove network!");
                return false;
            } else {
                callbackContext2.error("DISCONNECT_NET_DISABLE_ERROR");
                Log.d(TAG, "WifiWizard2: Unable to disable network!");
                return false;
            }
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    private boolean disconnect(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: disconnect entered.");
        if (this.wifiManager.disconnect()) {
            maybeResetBindALL();
            callbackContext2.success("Disconnected from current network");
            return true;
        }
        callbackContext2.error("ERROR_DISCONNECT");
        return false;
    }

    private boolean reconnect(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: reconnect entered.");
        if (this.wifiManager.reconnect()) {
            callbackContext2.success("Reconnected network");
            return true;
        }
        callbackContext2.error("ERROR_RECONNECT");
        return false;
    }

    private boolean reassociate(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: reassociate entered.");
        if (this.wifiManager.reassociate()) {
            callbackContext2.success("Reassociated network");
            return true;
        }
        callbackContext2.error("ERROR_REASSOCIATE");
        return false;
    }

    private boolean listNetworks(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: listNetworks entered.");
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        JSONArray jSONArray = new JSONArray();
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            jSONArray.put(wifiConfiguration.SSID);
        }
        callbackContext2.success(jSONArray);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean getScanResults(CallbackContext callbackContext2, JSONArray jSONArray) {
        int i;
        if (this.cordova.hasPermission(ACCESS_FINE_LOCATION)) {
            List<ScanResult> scanResults = this.wifiManager.getScanResults();
            JSONArray jSONArray2 = new JSONArray();
            if (!validateData(jSONArray)) {
                callbackContext2.error("GET_SCAN_RESULTS_INVALID_DATA");
                Log.d(TAG, "WifiWizard2: getScanResults invalid data");
                return false;
            }
            Integer num = null;
            if (!jSONArray.isNull(0)) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    if (jSONObject.has("numLevels")) {
                        Integer valueOf = Integer.valueOf(jSONObject.optInt("numLevels"));
                        if (valueOf.intValue() > 0) {
                            num = valueOf;
                        } else if (jSONObject.optBoolean("numLevels", false)) {
                            num = 5;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callbackContext2.error(e.toString());
                    return false;
                }
            }
            for (ScanResult next : scanResults) {
                if (num == null) {
                    i = next.level;
                } else {
                    i = WifiManager.calculateSignalLevel(next.level, num.intValue());
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("level", i);
                    jSONObject2.put("SSID", next.SSID);
                    jSONObject2.put("BSSID", next.BSSID);
                    jSONObject2.put("frequency", next.frequency);
                    jSONObject2.put("capabilities", next.capabilities);
                    jSONObject2.put("timestamp", next.timestamp);
                    if (API_VERSION >= 23) {
                        jSONObject2.put("channelWidth", next.channelWidth);
                        jSONObject2.put("centerFreq0", next.centerFreq0);
                        jSONObject2.put("centerFreq1", next.centerFreq1);
                    } else {
                        jSONObject2.put("channelWidth", JSONObject.NULL);
                        jSONObject2.put("centerFreq0", JSONObject.NULL);
                        jSONObject2.put("centerFreq1", JSONObject.NULL);
                    }
                    jSONArray2.put(jSONObject2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    callbackContext2.error(e2.toString());
                    return false;
                }
            }
            callbackContext2.success(jSONArray2);
            return true;
        }
        requestLocationPermission(0);
        return true;
    }

    private boolean startScan(CallbackContext callbackContext2) {
        if (this.wifiManager.startScan()) {
            callbackContext2.success();
            return true;
        }
        callbackContext2.error("STARTSCAN_FAILED");
        return false;
    }

    private int getConnectedNetId() {
        WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            Log.d(TAG, "Unable to read wifi info");
            return -1;
        }
        int networkId = connectionInfo.getNetworkId();
        if (networkId == -1) {
            Log.d(TAG, "NO_CURRENT_NETWORK_FOUND");
        }
        return networkId;
    }

    private boolean getSSIDNetworkID(CallbackContext callbackContext2, JSONArray jSONArray) {
        Log.d(TAG, "WifiWizard2: getSSIDNetworkID entered.");
        if (!validateData(jSONArray)) {
            callbackContext2.error("GET_SSID_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: getSSIDNetworkID invalid data.");
            return false;
        }
        try {
            callbackContext2.success(ssidToNetworkId(jSONArray.getString(0)));
            return true;
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    private boolean getConnectedNetworkID(CallbackContext callbackContext2) {
        int connectedNetId = getConnectedNetId();
        if (connectedNetId == -1) {
            callbackContext2.error("GET_CONNECTED_NET_ID_ERROR");
            return false;
        }
        callbackContext2.success(connectedNetId);
        return true;
    }

    private boolean getConnectedSSID(CallbackContext callbackContext2) {
        return getWifiServiceInfo(callbackContext2, false);
    }

    private boolean getConnectedBSSID(CallbackContext callbackContext2) {
        return getWifiServiceInfo(callbackContext2, true);
    }

    private boolean getWifiServiceInfo(CallbackContext callbackContext2, boolean z) {
        String str;
        if (API_VERSION < 23 || this.cordova.hasPermission(ACCESS_FINE_LOCATION)) {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                callbackContext2.error("UNABLE_TO_READ_WIFI_INFO");
                return false;
            } else if (!connectionInfo.getSupplicantState().equals(SupplicantState.COMPLETED)) {
                callbackContext2.error("CONNECTION_NOT_COMPLETED");
                return false;
            } else {
                if (z) {
                    str = connectionInfo.getBSSID();
                } else {
                    str = connectionInfo.getSSID();
                }
                if (str == null || str.isEmpty() || str == "0x") {
                    callbackContext2.error("WIFI_INFORMATION_EMPTY");
                    return false;
                }
                if (str.startsWith("\"") && str.endsWith("\"")) {
                    str = str.substring(1, str.length() - 1);
                }
                callbackContext2.success(str);
                return true;
            }
        } else {
            requestLocationPermission(3);
            bssidRequested = z;
            return true;
        }
    }

    private boolean isWifiEnabled(CallbackContext callbackContext2) {
        boolean isWifiEnabled = this.wifiManager.isWifiEnabled();
        callbackContext2.success(isWifiEnabled ? "1" : "0");
        return isWifiEnabled;
    }

    private int ssidToNetworkId(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            Log.d(TAG, "ssidToNetworkId passed SSID is integer, probably a Network ID: " + str);
            return parseInt;
        } catch (NumberFormatException unused) {
            int i = -1;
            for (WifiConfiguration next : this.wifiManager.getConfiguredNetworks()) {
                if (next.SSID != null && next.SSID.equals(str)) {
                    i = next.networkId;
                }
            }
            return i;
        }
    }

    private boolean setWifiEnabled(CallbackContext callbackContext2, JSONArray jSONArray) {
        if (!validateData(jSONArray)) {
            callbackContext2.error("SETWIFIENABLED_INVALID_DATA");
            Log.d(TAG, "WifiWizard2: setWifiEnabled invalid data");
            return false;
        }
        try {
            if (this.wifiManager.setWifiEnabled(jSONArray.getString(0).equals("true"))) {
                callbackContext2.success();
                return true;
            }
            callbackContext2.error("ERROR_SETWIFIENABLED");
            return false;
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    private boolean verifyWifiEnabled() {
        Log.d(TAG, "WifiWizard2: verifyWifiEnabled entered.");
        if (this.wifiManager.isWifiEnabled()) {
            return true;
        }
        Log.i(TAG, "Enabling wi-fi...");
        if (this.wifiManager.setWifiEnabled(true)) {
            Log.i(TAG, "Wi-fi enabled");
            int i = 0;
            while (!this.wifiManager.isWifiEnabled()) {
                if (i >= 10) {
                    Log.i(TAG, "Took too long to enable wi-fi, quitting");
                    return false;
                }
                Log.i(TAG, "Still waiting for wi-fi to enable...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException unused) {
                }
                i++;
            }
            return true;
        }
        Log.e(TAG, "VERIFY_ERROR_ENABLE_WIFI");
        return false;
    }

    private String[] getWiFiIPAddress() {
        String str;
        String formatIP = formatIP(this.wifiManager.getConnectionInfo().getIpAddress());
        try {
            str = getIPv4Subnet(InetAddress.getByName(formatIP));
        } catch (Exception unused) {
            str = "";
        }
        return new String[]{formatIP, str};
    }

    private String getWiFiRouterIP() {
        return formatIP(this.wifiManager.getDhcpInfo().gateway);
    }

    private String formatIP(int i) {
        return String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(i & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 24) & 255)});
    }

    public static String getIPv4Subnet(InetAddress inetAddress) {
        try {
            for (InterfaceAddress next : NetworkInterface.getByInetAddress(inetAddress).getInterfaceAddresses()) {
                if (!next.getAddress().isLoopbackAddress() && (next.getAddress() instanceof Inet4Address)) {
                    return getIPv4SubnetFromNetPrefixLength(next.getNetworkPrefixLength()).getHostAddress().toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static InetAddress getIPv4SubnetFromNetPrefixLength(int i) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 = i - 1; i3 > 0; i3--) {
            i2 >>= 1;
        }
        try {
            return InetAddress.getByName(Integer.toString((i2 >> 24) & 255) + "." + Integer.toString((i2 >> 16) & 255) + "." + Integer.toString((i2 >> 8) & 255) + "." + Integer.toString(i2 & 255));
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean validateData(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.get(0) != null) {
                    return true;
                }
            } catch (Exception e) {
                this.callbackContext.error(e.getMessage());
                return false;
            }
        }
        this.callbackContext.error("DATA_IS_NULL");
        return false;
    }

    /* access modifiers changed from: protected */
    public void requestLocationPermission(int i) {
        this.cordova.requestPermission(this, i, ACCESS_FINE_LOCATION);
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        for (int i2 : iArr) {
            if (i2 == -1) {
                this.callbackContext.error("PERMISSION_DENIED");
                return;
            }
        }
        if (i == 0) {
            getScanResults(this.callbackContext, this.passedData);
        } else if (i == 1) {
            scan(this.callbackContext, this.passedData);
        } else if (i == 2) {
            this.callbackContext.success("PERMISSION_GRANTED");
        } else if (i == 3) {
            getWifiServiceInfo(this.callbackContext, bssidRequested);
        }
    }

    private static int getMaxWifiPriority(WifiManager wifiManager2) {
        int i = 0;
        for (WifiConfiguration next : wifiManager2.getConfiguredNetworks()) {
            if (next.priority > i) {
                i = next.priority;
            }
        }
        Log.d(TAG, "WifiWizard: Found max WiFi priority of " + i);
        return i;
    }

    private boolean canConnectToInternet(CallbackContext callbackContext2, boolean z) {
        try {
            if (hasInternetConnection(z)) {
                callbackContext2.success("1");
                return true;
            }
            callbackContext2.success("0");
            return false;
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    private boolean canConnectToRouter(CallbackContext callbackContext2, boolean z) {
        try {
            if (hasConnectionToRouter(z)) {
                callbackContext2.success("1");
                return true;
            }
            callbackContext2.success("0");
            return false;
        } catch (Exception e) {
            callbackContext2.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasInternetConnection(boolean z) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager2 = this.connectivityManager;
        if (connectivityManager2 == null || (activeNetworkInfo = connectivityManager2.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        if (z) {
            return pingCmd("8.8.8.8");
        }
        return isHTTPreachable("http://www.google.com/");
    }

    public boolean hasConnectionToRouter(boolean z) {
        ConnectivityManager connectivityManager2;
        NetworkInfo activeNetworkInfo;
        String wiFiRouterIP = getWiFiRouterIP();
        if (wiFiRouterIP == null || wiFiRouterIP.equals("0.0.0.0") || (connectivityManager2 = this.connectivityManager) == null || (activeNetworkInfo = connectivityManager2.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        if (z) {
            return pingCmd(wiFiRouterIP);
        }
        return isHTTPreachable("http://" + wiFiRouterIP + "/");
    }

    public static boolean isHTTPreachable(String str) {
        try {
            ((HttpURLConnection) new URL(str).openConnection()).getContent();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pingCmd(String str) {
        try {
            Process exec = Runtime.getRuntime().exec("ping  -c 1 -W 3 " + str);
            try {
                exec.waitFor();
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException error.", e);
            }
            int exitValue = exec.exitValue();
            Log.d(TAG, "pingCmd exitValue" + exitValue);
            if (exitValue == 0) {
                return true;
            }
            return false;
        } catch (UnknownHostException e2) {
            Log.d(TAG, "UnknownHostException: " + e2.getMessage());
            return false;
        } catch (Exception e3) {
            Log.d(TAG, e3.getMessage());
            return false;
        }
    }

    private class NetworkChangedReceiver extends BroadcastReceiver {
        private NetworkChangedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                Log.d(WifiWizard2.TAG, "NETWORK_STATE_CHANGED_ACTION");
                WifiInfo connectionInfo = WifiWizard2.this.wifiManager.getConnectionInfo();
                if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected() && connectionInfo.getNetworkId() > -1) {
                    String replaceAll = connectionInfo.getSSID().replaceAll("\"", "");
                    Log.d(WifiWizard2.TAG, "Connected to '" + replaceAll + "' @ " + connectionInfo.getBSSID());
                    if (WifiWizard2.this.desired != null && connectionInfo.getNetworkId() == WifiWizard2.this.desired.apId) {
                        WifiWizard2.this.onSuccessfulConnection();
                    }
                }
            }
        }
    }

    private void registerBindALL(int i) {
        if (API_VERSION > 21) {
            Log.d(TAG, "registerBindALL: registering net changed receiver");
            this.desired = new AP(i, (String) null, (String) null);
            this.cordova.getActivity().getApplicationContext().registerReceiver(this.networkChangedReceiver, NETWORK_STATE_CHANGED_FILTER);
            return;
        }
        Log.d(TAG, "registerBindALL: API older than 21, bindall ignored.");
    }

    private void maybeResetBindALL() {
        ConnectivityManager.NetworkCallback networkCallback2;
        Log.d(TAG, "maybeResetBindALL");
        if (this.desired != null) {
            if (API_VERSION > 21) {
                try {
                    this.cordova.getActivity().getApplicationContext().unregisterReceiver(this.networkChangedReceiver);
                } catch (Exception unused) {
                }
            }
            int i = API_VERSION;
            if (i >= 23) {
                this.connectivityManager.bindProcessToNetwork((Network) null);
            } else if (i >= 21 && i < 23) {
                ConnectivityManager.setProcessDefaultNetwork((Network) null);
            }
            if (i > 21 && (networkCallback2 = this.networkCallback) != null) {
                try {
                    this.connectivityManager.unregisterNetworkCallback(networkCallback2);
                } catch (Exception unused2) {
                }
            }
            this.networkCallback = null;
            this.previous = null;
            this.desired = null;
        }
    }

    private void resetBindAll(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: resetBindALL");
        try {
            maybeResetBindALL();
            callbackContext2.success("Successfully reset BindALL");
        } catch (Exception e) {
            Log.e(TAG, "InterruptedException error.", e);
            callbackContext2.error("ERROR_NO_BIND_ALL");
        }
    }

    private void setBindAll(CallbackContext callbackContext2) {
        Log.d(TAG, "WifiWizard2: setBindALL");
        try {
            registerBindALL(getConnectedNetId());
            callbackContext2.success("Successfully bindAll to network");
        } catch (Exception e) {
            Log.e(TAG, "InterruptedException error.", e);
            callbackContext2.error("ERROR_CANT_BIND_ALL");
        }
    }

    /* access modifiers changed from: private */
    public void onSuccessfulConnection() {
        int i = API_VERSION;
        if (i >= 23) {
            Log.d(TAG, "BindALL onSuccessfulConnection API >= 23");
            NetworkRequest build = new NetworkRequest.Builder().addTransportType(1).build();
            AnonymousClass3 r1 = new ConnectivityManager.NetworkCallback() {
                public void onAvailable(Network network) {
                    if (WifiWizard2.this.connectivityManager.bindProcessToNetwork(network)) {
                        Log.d(WifiWizard2.TAG, "bindProcessToNetwork TRUE onSuccessfulConnection");
                    } else {
                        Log.d(WifiWizard2.TAG, "bindProcessToNetwork FALSE onSuccessfulConnection");
                    }
                }
            };
            this.networkCallback = r1;
            this.connectivityManager.requestNetwork(build, r1);
        } else if (i < 21 || i >= 23) {
            Log.d(TAG, "BindALL onSuccessfulConnection API older than 21, no need to do any binding");
            this.networkCallback = null;
            this.previous = null;
            this.desired = null;
        } else {
            Log.d(TAG, "BindALL onSuccessfulConnection API >= 21 && < 23");
            NetworkRequest build2 = new NetworkRequest.Builder().addTransportType(1).build();
            AnonymousClass4 r12 = new ConnectivityManager.NetworkCallback() {
                public void onAvailable(Network network) {
                    ConnectivityManager unused = WifiWizard2.this.connectivityManager;
                    ConnectivityManager.setProcessDefaultNetwork(network);
                }
            };
            this.networkCallback = r12;
            this.connectivityManager.requestNetwork(build2, r12);
        }
    }

    private class ScanSyncContext {
        public boolean finished;

        private ScanSyncContext(WifiWizard2 wifiWizard2) {
            this.finished = false;
        }
    }

    private static class AP {
        final int apId;
        final String bssid;
        final String ssid;

        AP(int i, String str, String str2) {
            this.apId = i;
            this.ssid = str;
            this.bssid = str2;
        }
    }
}
