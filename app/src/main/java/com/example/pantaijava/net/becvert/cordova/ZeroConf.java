package net.becvert.cordova;

import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ZeroConf extends CordovaPlugin {
    public static final String ACTION_CLOSE = "close";
    public static final String ACTION_GET_HOSTNAME = "getHostname";
    public static final String ACTION_REGISTER = "register";
    public static final String ACTION_REINIT = "reInit";
    public static final String ACTION_STOP = "stop";
    public static final String ACTION_UNREGISTER = "unregister";
    public static final String ACTION_UNWATCH = "unwatch";
    public static final String ACTION_WATCH = "watch";
    private static final String TAG = "ZeroConf";
    /* access modifiers changed from: private */
    public List<InetAddress> addresses;
    /* access modifiers changed from: private */
    public BrowserManager browserManager;
    /* access modifiers changed from: private */
    public String hostname;
    /* access modifiers changed from: private */
    public List<InetAddress> ipv4Addresses;
    /* access modifiers changed from: private */
    public List<InetAddress> ipv6Addresses;
    WifiManager.MulticastLock lock;
    /* access modifiers changed from: private */
    public RegistrationManager registrationManager;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        WifiManager.MulticastLock createMulticastLock = ((WifiManager) this.cordova.getActivity().getApplicationContext().getSystemService("wifi")).createMulticastLock("ZeroConfPluginLock");
        this.lock = createMulticastLock;
        createMulticastLock.setReferenceCounted(false);
        try {
            this.addresses = new CopyOnWriteArrayList();
            this.ipv6Addresses = new CopyOnWriteArrayList();
            this.ipv4Addresses = new CopyOnWriteArrayList();
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.supportsMulticast()) {
                    for (T t2 : Collections.list(t.getInetAddresses())) {
                        if (!t2.isLoopbackAddress()) {
                            if (t2 instanceof Inet6Address) {
                                this.addresses.add(t2);
                                this.ipv6Addresses.add(t2);
                            } else if (t2 instanceof Inet4Address) {
                                this.addresses.add(t2);
                                this.ipv4Addresses.add(t2);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        Log.d(TAG, "Addresses " + this.addresses);
        try {
            this.hostname = getHostName(cordovaInterface);
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
        }
        Log.d(TAG, "Hostname " + this.hostname);
        Log.v(TAG, "Initialized");
    }

    public void onDestroy() {
        super.onDestroy();
        RegistrationManager registrationManager2 = this.registrationManager;
        if (registrationManager2 != null) {
            try {
                registrationManager2.stop();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            } catch (Throwable th) {
                this.registrationManager = null;
                throw th;
            }
            this.registrationManager = null;
        }
        BrowserManager browserManager2 = this.browserManager;
        if (browserManager2 != null) {
            try {
                browserManager2.close();
            } catch (IOException e2) {
                Log.e(TAG, e2.getMessage(), e2);
            } catch (Throwable th2) {
                this.browserManager = null;
                throw th2;
            }
            this.browserManager = null;
        }
        WifiManager.MulticastLock multicastLock = this.lock;
        if (multicastLock != null) {
            multicastLock.release();
            this.lock = null;
        }
        Log.v(TAG, "Destroyed");
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        String str2 = str;
        JSONArray jSONArray2 = jSONArray;
        final CallbackContext callbackContext2 = callbackContext;
        if (ACTION_GET_HOSTNAME.equals(str)) {
            if (this.hostname != null) {
                Log.d(TAG, "Hostname: " + this.hostname);
                callbackContext2.success(this.hostname);
            } else {
                callbackContext2.error("Error: undefined hostname");
                return false;
            }
        } else if (ACTION_REGISTER.equals(str)) {
            final String optString = jSONArray2.optString(0);
            String optString2 = jSONArray2.optString(1);
            final String optString3 = jSONArray2.optString(2);
            int optInt = jSONArray2.optInt(3);
            JSONObject optJSONObject = jSONArray2.optJSONObject(4);
            final String optString4 = jSONArray2.optString(5);
            Log.d(TAG, "Register " + optString + optString2);
            final String str3 = optString2;
            final int i = optInt;
            final JSONObject jSONObject = optJSONObject;
            final CallbackContext callbackContext3 = callbackContext;
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        if (ZeroConf.this.registrationManager == null) {
                            List r3 = ZeroConf.this.addresses;
                            if ("ipv6".equalsIgnoreCase(optString4)) {
                                r3 = ZeroConf.this.ipv6Addresses;
                            } else if ("ipv4".equalsIgnoreCase(optString4)) {
                                r3 = ZeroConf.this.ipv4Addresses;
                            }
                            ZeroConf zeroConf = ZeroConf.this;
                            ZeroConf zeroConf2 = ZeroConf.this;
                            zeroConf.registrationManager = new RegistrationManager(zeroConf2, r3, zeroConf2.hostname);
                        }
                        ServiceInfo register = ZeroConf.this.registrationManager.register(optString, str3, optString3, i, jSONObject);
                        if (register == null) {
                            callbackContext3.error("Failed to register");
                            return;
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("action", "registered");
                        jSONObject.put(NotificationCompat.CATEGORY_SERVICE, ZeroConf.jsonifyService(register));
                        Log.d(ZeroConf.TAG, "Sending result: " + jSONObject.toString());
                        callbackContext3.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
                    } catch (JSONException e) {
                        Log.e(ZeroConf.TAG, e.getMessage(), e);
                        callbackContext3.error("Error: " + e.getMessage());
                    } catch (IOException e2) {
                        Log.e(ZeroConf.TAG, e2.getMessage(), e2);
                        callbackContext3.error("Error: " + e2.getMessage());
                    } catch (RuntimeException e3) {
                        Log.e(ZeroConf.TAG, e3.getMessage(), e3);
                        callbackContext3.error("Error: " + e3.getMessage());
                    }
                }
            });
        } else if (ACTION_UNREGISTER.equals(str)) {
            final String optString5 = jSONArray2.optString(0);
            String optString6 = jSONArray2.optString(1);
            final String optString7 = jSONArray2.optString(2);
            Log.d(TAG, "Unregister " + optString5 + optString6);
            final RegistrationManager registrationManager2 = this.registrationManager;
            if (registrationManager2 != null) {
                final String str4 = optString6;
                final CallbackContext callbackContext4 = callbackContext;
                this.cordova.getThreadPool().execute(new Runnable(this) {
                    public void run() {
                        registrationManager2.unregister(optString5, str4, optString7);
                        callbackContext4.success();
                    }
                });
            } else {
                callbackContext.success();
            }
        } else if (ACTION_STOP.equals(str)) {
            Log.d(TAG, "Stop");
            final RegistrationManager registrationManager3 = this.registrationManager;
            if (registrationManager3 != null) {
                this.registrationManager = null;
                this.cordova.getThreadPool().execute(new Runnable(this) {
                    public void run() {
                        try {
                            registrationManager3.stop();
                            callbackContext2.success();
                        } catch (IOException e) {
                            Log.e(ZeroConf.TAG, e.getMessage(), e);
                            callbackContext2.error("Error: " + e.getMessage());
                        }
                    }
                });
            } else {
                callbackContext.success();
            }
        } else if (ACTION_WATCH.equals(str)) {
            final String optString8 = jSONArray2.optString(0);
            String optString9 = jSONArray2.optString(1);
            final String optString10 = jSONArray2.optString(2);
            Log.d(TAG, "Watch " + optString8 + optString9);
            final String str5 = optString9;
            final CallbackContext callbackContext5 = callbackContext;
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        if (ZeroConf.this.browserManager == null) {
                            List r2 = ZeroConf.this.addresses;
                            if ("ipv6".equalsIgnoreCase(optString10)) {
                                r2 = ZeroConf.this.ipv6Addresses;
                            } else if ("ipv4".equalsIgnoreCase(optString10)) {
                                r2 = ZeroConf.this.ipv4Addresses;
                            }
                            ZeroConf zeroConf = ZeroConf.this;
                            ZeroConf zeroConf2 = ZeroConf.this;
                            zeroConf.browserManager = new BrowserManager(r2, zeroConf2.hostname);
                        }
                        ZeroConf.this.browserManager.watch(optString8, str5, callbackContext5);
                    } catch (IOException e) {
                        Log.e(ZeroConf.TAG, e.getMessage(), e);
                        callbackContext5.error("Error: " + e.getMessage());
                    } catch (RuntimeException e2) {
                        Log.e(ZeroConf.TAG, e2.getMessage(), e2);
                        callbackContext5.error("Error: " + e2.getMessage());
                    }
                }
            });
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            callbackContext2.sendPluginResult(pluginResult);
        } else if (ACTION_UNWATCH.equals(str)) {
            final String optString11 = jSONArray2.optString(0);
            String optString12 = jSONArray2.optString(1);
            Log.d(TAG, "Unwatch " + optString11 + optString12);
            final BrowserManager browserManager2 = this.browserManager;
            if (browserManager2 != null) {
                final String str6 = optString12;
                final CallbackContext callbackContext6 = callbackContext;
                this.cordova.getThreadPool().execute(new Runnable(this) {
                    public void run() {
                        browserManager2.unwatch(optString11, str6);
                        callbackContext6.success();
                    }
                });
            } else {
                callbackContext.success();
            }
        } else if (ACTION_CLOSE.equals(str)) {
            Log.d(TAG, "Close");
            final BrowserManager browserManager3 = this.browserManager;
            if (browserManager3 != null) {
                this.browserManager = null;
                this.cordova.getThreadPool().execute(new Runnable(this) {
                    public void run() {
                        try {
                            browserManager3.close();
                            callbackContext2.success();
                        } catch (IOException e) {
                            Log.e(ZeroConf.TAG, e.getMessage(), e);
                            callbackContext2.error("Error: " + e.getMessage());
                        }
                    }
                });
            } else {
                callbackContext.success();
            }
        } else if (ACTION_REINIT.equals(str)) {
            Log.e(TAG, "Re-Initializing");
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    ZeroConf.this.onDestroy();
                    ZeroConf zeroConf = ZeroConf.this;
                    zeroConf.initialize(zeroConf.cordova, ZeroConf.this.webView);
                    callbackContext2.success();
                    Log.e(ZeroConf.TAG, "Re-Initialization complete");
                }
            });
        } else {
            Log.e(TAG, "Invalid action: " + str);
            callbackContext2.error("Invalid action: " + str);
            return false;
        }
        return true;
    }

    private class RegistrationManager {
        private List<JmDNS> publishers = new ArrayList();

        public RegistrationManager(ZeroConf zeroConf, List<InetAddress> list, String str) throws IOException {
            if (list == null || list.size() == 0) {
                this.publishers.add(JmDNS.create((InetAddress) null, str));
                return;
            }
            for (InetAddress create : list) {
                this.publishers.add(JmDNS.create(create, str));
            }
        }

        public ServiceInfo register(String str, String str2, String str3, int i, JSONObject jSONObject) throws JSONException, IOException {
            HashMap hashMap = new HashMap();
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            }
            Iterator<JmDNS> it = this.publishers.iterator();
            ServiceInfo serviceInfo = null;
            while (true) {
                ServiceInfo serviceInfo2 = serviceInfo;
                while (true) {
                    if (!it.hasNext()) {
                        return serviceInfo2;
                    }
                    JmDNS next2 = it.next();
                    serviceInfo = ServiceInfo.create(str + str2, str3, i, 0, 0, (Map<String, ?>) hashMap);
                    try {
                        next2.registerService(serviceInfo);
                        break;
                    } catch (IOException e) {
                        Log.e(ZeroConf.TAG, e.getMessage(), e);
                    }
                }
            }
        }

        public void unregister(String str, String str2, String str3) {
            for (JmDNS next : this.publishers) {
                ServiceInfo serviceInfo = next.getServiceInfo(str + str2, str3, 5000);
                if (serviceInfo != null) {
                    next.unregisterService(serviceInfo);
                }
            }
        }

        public void stop() throws IOException {
            for (JmDNS close : this.publishers) {
                close.close();
            }
        }
    }

    private class BrowserManager implements ServiceListener {
        private List<JmDNS> browsers = new ArrayList();
        private Map<String, CallbackContext> callbacks = new HashMap();

        public BrowserManager(List<InetAddress> list, String str) throws IOException {
            ZeroConf.this.lock.acquire();
            if (list == null || list.size() == 0) {
                this.browsers.add(JmDNS.create((InetAddress) null, str));
                return;
            }
            for (InetAddress create : list) {
                this.browsers.add(JmDNS.create(create, str));
            }
        }

        /* access modifiers changed from: private */
        public void watch(String str, String str2, CallbackContext callbackContext) {
            this.callbacks.put(str + str2, callbackContext);
            for (JmDNS addServiceListener : this.browsers) {
                addServiceListener.addServiceListener(str + str2, this);
            }
        }

        /* access modifiers changed from: private */
        public void unwatch(String str, String str2) {
            this.callbacks.remove(str + str2);
            for (JmDNS removeServiceListener : this.browsers) {
                removeServiceListener.removeServiceListener(str + str2, this);
            }
        }

        /* access modifiers changed from: private */
        public void close() throws IOException {
            ZeroConf.this.lock.release();
            this.callbacks.clear();
            for (JmDNS close : this.browsers) {
                close.close();
            }
        }

        public void serviceResolved(ServiceEvent serviceEvent) {
            Log.d(ZeroConf.TAG, "Resolved");
            sendCallback("resolved", serviceEvent.getInfo());
        }

        public void serviceRemoved(ServiceEvent serviceEvent) {
            Log.d(ZeroConf.TAG, "Removed");
            sendCallback("removed", serviceEvent.getInfo());
        }

        public void serviceAdded(ServiceEvent serviceEvent) {
            Log.d(ZeroConf.TAG, "Added");
            sendCallback("added", serviceEvent.getInfo());
        }

        public void sendCallback(String str, ServiceInfo serviceInfo) {
            CallbackContext callbackContext = this.callbacks.get(serviceInfo.getType());
            if (callbackContext != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", str);
                    jSONObject.put(NotificationCompat.CATEGORY_SERVICE, ZeroConf.jsonifyService(serviceInfo));
                    Log.d(ZeroConf.TAG, "Sending result: " + jSONObject.toString());
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject);
                    pluginResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(pluginResult);
                } catch (JSONException e) {
                    Log.e(ZeroConf.TAG, e.getMessage(), e);
                    callbackContext.error("Error: " + e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject jsonifyService(ServiceInfo serviceInfo) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = serviceInfo.getDomain() + ".";
        jSONObject.put("domain", str);
        jSONObject.put("type", serviceInfo.getType().replace(str, ""));
        jSONObject.put("name", serviceInfo.getName());
        jSONObject.put("port", serviceInfo.getPort());
        jSONObject.put("hostname", serviceInfo.getServer());
        JSONArray jSONArray = new JSONArray();
        Inet4Address[] inet4Addresses = serviceInfo.getInet4Addresses();
        for (Inet4Address inet4Address : inet4Addresses) {
            if (inet4Address != null) {
                jSONArray.put(inet4Address.getHostAddress());
            }
        }
        jSONObject.put("ipv4Addresses", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        Inet6Address[] inet6Addresses = serviceInfo.getInet6Addresses();
        for (Inet6Address inet6Address : inet6Addresses) {
            if (inet6Address != null) {
                jSONArray2.put(inet6Address.getHostAddress());
            }
        }
        jSONObject.put("ipv6Addresses", jSONArray2);
        JSONObject jSONObject2 = new JSONObject();
        Enumeration<String> propertyNames = serviceInfo.getPropertyNames();
        while (propertyNames.hasMoreElements()) {
            String nextElement = propertyNames.nextElement();
            jSONObject2.put(nextElement, serviceInfo.getPropertyString(nextElement));
        }
        jSONObject.put("txtRecord", jSONObject2);
        return jSONObject;
    }

    public static String getHostName(CordovaInterface cordovaInterface) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = Build.class.getDeclaredMethod("getString", new Class[]{String.class});
        declaredMethod.setAccessible(true);
        String obj = declaredMethod.invoke((Object) null, new Object[]{"net.hostname"}).toString();
        if (!TextUtils.isEmpty(obj)) {
            return obj;
        }
        return "android-" + Settings.Secure.getString(cordovaInterface.getActivity().getContentResolver(), "android_id");
    }
}
