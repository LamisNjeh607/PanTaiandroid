package org.apache.cordova;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.becvert.cordova.ZeroConf;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaWebViewImpl implements CordovaWebView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TAG = "CordovaWebViewImpl";
    private CoreAndroid appPlugin;
    /* access modifiers changed from: private */
    public Set<Integer> boundKeyCodes = new HashSet();
    /* access modifiers changed from: private */
    public CordovaInterface cordova;
    protected final CordovaWebViewEngine engine;
    private EngineClient engineClient = new EngineClient();
    private boolean hasPausedEver;
    /* access modifiers changed from: private */
    public int loadUrlTimeout = 0;
    String loadedUrl;
    /* access modifiers changed from: private */
    public View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private NativeToJsMessageQueue nativeToJsMessageQueue;
    /* access modifiers changed from: private */
    public PluginManager pluginManager;
    private CordovaPreferences preferences;
    private CordovaResourceApi resourceApi;

    static /* synthetic */ int access$108(CordovaWebViewImpl cordovaWebViewImpl) {
        int i = cordovaWebViewImpl.loadUrlTimeout;
        cordovaWebViewImpl.loadUrlTimeout = i + 1;
        return i;
    }

    public static CordovaWebViewEngine createEngine(Context context, CordovaPreferences cordovaPreferences) {
        try {
            return (CordovaWebViewEngine) Class.forName(cordovaPreferences.getString("webview", SystemWebViewEngine.class.getCanonicalName())).getConstructor(new Class[]{Context.class, CordovaPreferences.class}).newInstance(new Object[]{context, cordovaPreferences});
        } catch (Exception e) {
            throw new RuntimeException("Failed to create webview. ", e);
        }
    }

    public CordovaWebViewImpl(CordovaWebViewEngine cordovaWebViewEngine) {
        this.engine = cordovaWebViewEngine;
    }

    public void init(CordovaInterface cordovaInterface) {
        init(cordovaInterface, new ArrayList(), new CordovaPreferences());
    }

    public void init(CordovaInterface cordovaInterface, List<PluginEntry> list, CordovaPreferences cordovaPreferences) {
        if (this.cordova == null) {
            this.cordova = cordovaInterface;
            this.preferences = cordovaPreferences;
            this.pluginManager = new PluginManager(this, this.cordova, list);
            this.resourceApi = new CordovaResourceApi(this.engine.getView().getContext(), this.pluginManager);
            NativeToJsMessageQueue nativeToJsMessageQueue2 = new NativeToJsMessageQueue();
            this.nativeToJsMessageQueue = nativeToJsMessageQueue2;
            nativeToJsMessageQueue2.addBridgeMode(new NativeToJsMessageQueue.NoOpBridgeMode());
            this.nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.LoadUrlBridgeMode(this.engine, cordovaInterface));
            if (cordovaPreferences.getBoolean("DisallowOverscroll", false)) {
                this.engine.getView().setOverScrollMode(2);
            }
            this.engine.init(this, cordovaInterface, this.engineClient, this.resourceApi, this.pluginManager, this.nativeToJsMessageQueue);
            this.pluginManager.addService(CoreAndroid.PLUGIN_NAME, "org.apache.cordova.CoreAndroid");
            this.pluginManager.init();
            return;
        }
        throw new IllegalStateException();
    }

    public boolean isInitialized() {
        return this.cordova != null;
    }

    public void loadUrlIntoView(final String str, boolean z) {
        LOG.d(TAG, ">>> loadUrl(" + str + ")");
        boolean z2 = false;
        if (str.equals("about:blank") || str.startsWith("javascript:")) {
            this.engine.loadUrl(str, false);
            return;
        }
        if (z || this.loadedUrl == null) {
            z2 = true;
        }
        final boolean z3 = z2;
        if (z3) {
            if (this.loadedUrl != null) {
                this.appPlugin = null;
                this.pluginManager.init();
            }
            this.loadedUrl = str;
        }
        final int i = this.loadUrlTimeout;
        final int integer = this.preferences.getInteger("LoadUrlTimeoutValue", AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
        final AnonymousClass1 r0 = new Runnable() {
            public void run() {
                CordovaWebViewImpl.this.stopLoading();
                LOG.e(CordovaWebViewImpl.TAG, "CordovaWebView: TIMEOUT ERROR!");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("errorCode", -6);
                    jSONObject.put("description", "The connection to the server was unsuccessful.");
                    jSONObject.put(ImagesContract.URL, str);
                } catch (JSONException unused) {
                }
                CordovaWebViewImpl.this.pluginManager.postMessage("onReceivedError", jSONObject);
            }
        };
        final AnonymousClass2 r6 = new Runnable() {
            public void run() {
                try {
                    synchronized (this) {
                        wait((long) integer);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (CordovaWebViewImpl.this.loadUrlTimeout == i && CordovaWebViewImpl.this.cordova.getActivity() != null) {
                    CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(r0);
                } else if (CordovaWebViewImpl.this.cordova.getActivity() == null) {
                    LOG.d(CordovaWebViewImpl.TAG, "Cordova activity does not exist.");
                }
            }
        };
        if (this.cordova.getActivity() != null) {
            final String str2 = str;
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (integer > 0) {
                        CordovaWebViewImpl.this.cordova.getThreadPool().execute(r6);
                    }
                    CordovaWebViewImpl.this.engine.loadUrl(str2, z3);
                }
            });
            return;
        }
        LOG.d(TAG, "Cordova activity does not exist.");
    }

    public void loadUrl(String str) {
        loadUrlIntoView(str, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b1, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c2, code lost:
        showWebPage(r3.getStringExtra("browser_fallback_url"), r9, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00dd, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00de, code lost:
        org.apache.cordova.LOG.e(TAG, "Error parsing url " + r8, (java.lang.Throwable) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dd A[ExcHandler: URISyntaxException (r9v1 'e' java.net.URISyntaxException A[CUSTOM_DECLARE]), Splitter:B:16:0x005e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showWebPage(java.lang.String r8, boolean r9, boolean r10, java.util.Map<java.lang.String, java.lang.Object> r11) {
        /*
            r7 = this;
            java.lang.String r0 = "intent://"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r10)
            java.lang.Object[] r1 = new java.lang.Object[]{r8, r1, r2}
            java.lang.String r2 = "CordovaWebViewImpl"
            java.lang.String r3 = "showWebPage(%s, %b, %b, HashMap)"
            org.apache.cordova.LOG.d((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r1)
            if (r10 == 0) goto L_0x001c
            org.apache.cordova.CordovaWebViewEngine r1 = r7.engine
            r1.clearHistory()
        L_0x001c:
            r1 = 1
            if (r9 != 0) goto L_0x003e
            org.apache.cordova.PluginManager r9 = r7.pluginManager
            boolean r9 = r9.shouldAllowNavigation(r8)
            if (r9 == 0) goto L_0x002b
            r7.loadUrlIntoView(r8, r1)
            return
        L_0x002b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "showWebPage: Refusing to load URL into webview since it is not in the <allow-navigation> allow list. URL="
            r9.<init>(r10)
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r8 = r8.toString()
            org.apache.cordova.LOG.w((java.lang.String) r2, (java.lang.String) r8)
            return
        L_0x003e:
            org.apache.cordova.PluginManager r3 = r7.pluginManager
            java.lang.Boolean r3 = r3.shouldOpenExternalUrl(r8)
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L_0x005d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "showWebPage: Refusing to send intent for URL since it is not in the <allow-intent> allow list. URL="
            r9.<init>(r10)
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r8 = r8.toString()
            org.apache.cordova.LOG.w((java.lang.String) r2, (java.lang.String) r8)
            return
        L_0x005d:
            r3 = 0
            boolean r4 = r8.startsWith(r0)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            if (r4 == 0) goto L_0x006a
            android.content.Intent r1 = android.content.Intent.parseUri(r8, r1)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
        L_0x0068:
            r3 = r1
            goto L_0x0094
        L_0x006a:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            java.lang.String r4 = "android.intent.action.VIEW"
            r1.<init>(r4)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            java.lang.String r3 = "android.intent.category.BROWSABLE"
            r1.addCategory(r3)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            android.net.Uri r3 = android.net.Uri.parse(r8)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            java.lang.String r4 = "file"
            java.lang.String r5 = r3.getScheme()     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            boolean r4 = r4.equals(r5)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            if (r4 == 0) goto L_0x0090
            org.apache.cordova.CordovaResourceApi r4 = r7.resourceApi     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            java.lang.String r4 = r4.getMimeType(r3)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            r1.setDataAndType(r3, r4)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            goto L_0x0068
        L_0x0090:
            r1.setData(r3)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00ac }
            goto L_0x0068
        L_0x0094:
            org.apache.cordova.CordovaInterface r1 = r7.cordova     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            androidx.appcompat.app.AppCompatActivity r1 = r1.getActivity()     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            if (r1 == 0) goto L_0x00a6
            org.apache.cordova.CordovaInterface r1 = r7.cordova     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            androidx.appcompat.app.AppCompatActivity r1 = r1.getActivity()     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            r1.startActivity(r3)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            goto L_0x00f0
        L_0x00a6:
            java.lang.String r1 = "Cordova activity does not exist."
            org.apache.cordova.LOG.d(r2, r1)     // Catch:{ URISyntaxException -> 0x00dd, ActivityNotFoundException -> 0x00b1 }
            goto L_0x00f0
        L_0x00ac:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x00b2
        L_0x00b1:
            r1 = move-exception
        L_0x00b2:
            boolean r0 = r8.startsWith(r0)
            if (r0 == 0) goto L_0x00ca
            if (r3 == 0) goto L_0x00ca
            java.lang.String r0 = "browser_fallback_url"
            java.lang.String r4 = r3.getStringExtra(r0)
            if (r4 == 0) goto L_0x00ca
            java.lang.String r8 = r3.getStringExtra(r0)
            r7.showWebPage(r8, r9, r10, r11)
            goto L_0x00f0
        L_0x00ca:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Error loading url "
            r9.<init>(r10)
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r8 = r8.toString()
            org.apache.cordova.LOG.e((java.lang.String) r2, (java.lang.String) r8, (java.lang.Throwable) r1)
            goto L_0x00f0
        L_0x00dd:
            r9 = move-exception
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Error parsing url "
            r10.<init>(r11)
            java.lang.StringBuilder r8 = r10.append(r8)
            java.lang.String r8 = r8.toString()
            org.apache.cordova.LOG.e((java.lang.String) r2, (java.lang.String) r8, (java.lang.Throwable) r9)
        L_0x00f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.CordovaWebViewImpl.showWebPage(java.lang.String, boolean, boolean, java.util.Map):void");
    }

    private static class WrapperView extends FrameLayout {
        private final CordovaWebViewEngine engine;

        public WrapperView(Context context, CordovaWebViewEngine cordovaWebViewEngine) {
            super(context);
            this.engine = cordovaWebViewEngine;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            boolean dispatchKeyEvent = this.engine.getView().dispatchKeyEvent(keyEvent);
            return !dispatchKeyEvent ? super.dispatchKeyEvent(keyEvent) : dispatchKeyEvent;
        }
    }

    @Deprecated
    public void showCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        LOG.d(TAG, "showing Custom View");
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        WrapperView wrapperView = new WrapperView(getContext(), this.engine);
        wrapperView.addView(view);
        this.mCustomView = wrapperView;
        this.mCustomViewCallback = customViewCallback;
        ViewGroup viewGroup = (ViewGroup) this.engine.getView().getParent();
        viewGroup.addView(wrapperView, new FrameLayout.LayoutParams(-1, -1, 17));
        this.engine.getView().setVisibility(8);
        viewGroup.setVisibility(0);
        viewGroup.bringToFront();
    }

    @Deprecated
    public void hideCustomView() {
        if (this.mCustomView != null) {
            LOG.d(TAG, "Hiding Custom View");
            this.mCustomView.setVisibility(8);
            ((ViewGroup) this.engine.getView().getParent()).removeView(this.mCustomView);
            this.mCustomView = null;
            this.mCustomViewCallback.onCustomViewHidden();
            this.engine.getView().setVisibility(0);
            this.engine.getView().requestFocus();
        }
    }

    @Deprecated
    public boolean isCustomViewShowing() {
        return this.mCustomView != null;
    }

    @Deprecated
    public void sendJavascript(String str) {
        this.nativeToJsMessageQueue.addJavaScript(str);
    }

    public void sendPluginResult(PluginResult pluginResult, String str) {
        this.nativeToJsMessageQueue.addPluginResult(pluginResult, str);
    }

    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    public CordovaPreferences getPreferences() {
        return this.preferences;
    }

    public ICordovaCookieManager getCookieManager() {
        return this.engine.getCookieManager();
    }

    public CordovaResourceApi getResourceApi() {
        return this.resourceApi;
    }

    public CordovaWebViewEngine getEngine() {
        return this.engine;
    }

    public View getView() {
        return this.engine.getView();
    }

    public Context getContext() {
        return this.engine.getView().getContext();
    }

    /* access modifiers changed from: private */
    public void sendJavascriptEvent(String str) {
        if (this.appPlugin == null) {
            this.appPlugin = (CoreAndroid) this.pluginManager.getPlugin(CoreAndroid.PLUGIN_NAME);
        }
        CoreAndroid coreAndroid = this.appPlugin;
        if (coreAndroid == null) {
            LOG.w(TAG, "Unable to fire event without existing plugin");
        } else {
            coreAndroid.fireJavascriptEvent(str);
        }
    }

    public void setButtonPlumbedToJs(int i, boolean z) {
        if (i != 4 && i != 82 && i != 24 && i != 25) {
            throw new IllegalArgumentException("Unsupported keycode: " + i);
        } else if (z) {
            this.boundKeyCodes.add(Integer.valueOf(i));
        } else {
            this.boundKeyCodes.remove(Integer.valueOf(i));
        }
    }

    public boolean isButtonPlumbedToJs(int i) {
        return this.boundKeyCodes.contains(Integer.valueOf(i));
    }

    public Object postMessage(String str, Object obj) {
        return this.pluginManager.postMessage(str, obj);
    }

    public String getUrl() {
        return this.engine.getUrl();
    }

    public void stopLoading() {
        this.loadUrlTimeout++;
    }

    public boolean canGoBack() {
        return this.engine.canGoBack();
    }

    public void clearCache() {
        this.engine.clearCache();
    }

    @Deprecated
    public void clearCache(boolean z) {
        this.engine.clearCache();
    }

    public void clearHistory() {
        this.engine.clearHistory();
    }

    public boolean backHistory() {
        return this.engine.goBack();
    }

    public void onNewIntent(Intent intent) {
        PluginManager pluginManager2 = this.pluginManager;
        if (pluginManager2 != null) {
            pluginManager2.onNewIntent(intent);
        }
    }

    public void handlePause(boolean z) {
        if (isInitialized()) {
            this.hasPausedEver = true;
            this.pluginManager.onPause(z);
            sendJavascriptEvent("pause");
            if (!z) {
                this.engine.setPaused(true);
            }
        }
    }

    public void handleResume(boolean z) {
        if (isInitialized()) {
            this.engine.setPaused(false);
            this.pluginManager.onResume(z);
            if (this.hasPausedEver) {
                sendJavascriptEvent("resume");
            }
        }
    }

    public void handleStart() {
        if (isInitialized()) {
            this.pluginManager.onStart();
        }
    }

    public void handleStop() {
        if (isInitialized()) {
            this.pluginManager.onStop();
        }
    }

    public void handleDestroy() {
        if (isInitialized()) {
            this.loadUrlTimeout++;
            this.pluginManager.onDestroy();
            loadUrl("about:blank");
            this.engine.destroy();
            hideCustomView();
        }
    }

    protected class EngineClient implements CordovaWebViewEngine.Client {
        protected EngineClient() {
        }

        public void clearLoadTimeoutTimer() {
            CordovaWebViewImpl.access$108(CordovaWebViewImpl.this);
        }

        public void onPageStarted(String str) {
            LOG.d(CordovaWebViewImpl.TAG, "onPageDidNavigate(" + str + ")");
            CordovaWebViewImpl.this.boundKeyCodes.clear();
            CordovaWebViewImpl.this.pluginManager.onReset();
            CordovaWebViewImpl.this.pluginManager.postMessage("onPageStarted", str);
        }

        public void onReceivedError(int i, String str, String str2) {
            clearLoadTimeoutTimer();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("errorCode", i);
                jSONObject.put("description", str);
                jSONObject.put(ImagesContract.URL, str2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CordovaWebViewImpl.this.pluginManager.postMessage("onReceivedError", jSONObject);
        }

        public void onPageFinishedLoading(String str) {
            LOG.d(CordovaWebViewImpl.TAG, "onPageFinished(" + str + ")");
            clearLoadTimeoutTimer();
            CordovaWebViewImpl.this.pluginManager.postMessage("onPageFinished", str);
            if (CordovaWebViewImpl.this.engine.getView().getVisibility() != 0) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            if (CordovaWebViewImpl.this.cordova.getActivity() != null) {
                                CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        CordovaWebViewImpl.this.pluginManager.postMessage("spinner", ZeroConf.ACTION_STOP);
                                    }
                                });
                            } else {
                                LOG.d(CordovaWebViewImpl.TAG, "Cordova activity does not exist.");
                            }
                        } catch (InterruptedException unused) {
                        }
                    }
                }).start();
            }
            if (str.equals("about:blank")) {
                CordovaWebViewImpl.this.pluginManager.postMessage("exit", (Object) null);
            }
        }

        public Boolean onDispatchKeyEvent(KeyEvent keyEvent) {
            String str;
            int keyCode = keyEvent.getKeyCode();
            boolean z = keyCode == 4;
            if (keyEvent.getAction() == 0) {
                if ((z && CordovaWebViewImpl.this.mCustomView != null) || CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(keyCode))) {
                    return true;
                }
                if (z) {
                    return Boolean.valueOf(CordovaWebViewImpl.this.engine.canGoBack());
                }
            } else if (keyEvent.getAction() == 1) {
                if (z && CordovaWebViewImpl.this.mCustomView != null) {
                    CordovaWebViewImpl.this.hideCustomView();
                    return true;
                } else if (CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(keyCode))) {
                    if (keyCode == 4) {
                        str = "backbutton";
                    } else if (keyCode == 82) {
                        str = "menubutton";
                    } else if (keyCode == 84) {
                        str = "searchbutton";
                    } else if (keyCode == 24) {
                        str = "volumeupbutton";
                    } else if (keyCode != 25) {
                        str = null;
                    } else {
                        str = "volumedownbutton";
                    }
                    if (str != null) {
                        CordovaWebViewImpl.this.sendJavascriptEvent(str);
                        return true;
                    }
                } else if (z) {
                    return Boolean.valueOf(CordovaWebViewImpl.this.engine.goBack());
                }
            }
            return null;
        }

        public boolean onNavigationAttempt(String str) {
            if (CordovaWebViewImpl.this.pluginManager.onOverrideUrlLoading(str)) {
                return true;
            }
            if (CordovaWebViewImpl.this.pluginManager.shouldAllowNavigation(str)) {
                return false;
            }
            if (CordovaWebViewImpl.this.pluginManager.shouldOpenExternalUrl(str).booleanValue()) {
                CordovaWebViewImpl.this.showWebPage(str, true, false, (Map<String, Object>) null);
                return true;
            }
            LOG.w(CordovaWebViewImpl.TAG, "Blocked (possibly sub-frame) navigation to non-allowed URL: " + str);
            return true;
        }
    }
}
