package com.example.pantaijava.com.getcapacitor;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;

import com.getcapacitor.PluginResult;

import org.apache.cordova.PluginManager;

public class MessageHandler {
    private Bridge bridge;
    private PluginManager cordovaPluginManager;
    private JavaScriptReplyProxy javaScriptReplyProxy;
    private WebView webView;

    public MessageHandler(Bridge bridge2, WebView webView2, PluginManager pluginManager) {
        this.bridge = bridge2;
        this.webView = webView2;
        this.cordovaPluginManager = pluginManager;
        if (!WebViewFeature.isFeatureSupported( WebViewFeature.WEB_MESSAGE_LISTENER) || bridge2.getConfig().isUsingLegacyBridge()) {
            webView2.addJavascriptInterface(this, "androidBridge");
            return;
        }
        try {
            WebViewCompat.addWebMessageListener(webView2, "androidBridge", bridge2.getAllowedOriginRules(), new com.getcapacitor.MessageHandler$$ExternalSyntheticLambda2(this));
        } catch (Exception unused) {
            webView2.addJavascriptInterface(this, "androidBridge");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(WebView webView2, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy2) {
        if (z) {
            postMessage(webMessageCompat.getData());
            this.javaScriptReplyProxy = javaScriptReplyProxy2;
            return;
        }
        Logger.warn("Plugin execution is allowed in Main Frame only");
    }

    @JavascriptInterface
    public void postMessage(String str) {
        try {
            JSObject jSObject = new JSObject(str);
            String string = jSObject.getString("type");
            boolean z = string != null;
            boolean z2 = z && string.equals("cordova");
            boolean z3 = z && string.equals("js.error");
            String string2 = jSObject.getString("callbackId");
            if (z2) {
                String string3 = jSObject.getString(NotificationCompat.CATEGORY_SERVICE);
                String string4 = jSObject.getString("action");
                String string5 = jSObject.getString("actionArgs");
                Logger.verbose(Logger.tags("Plugin"), "To native (Cordova plugin): callbackId: " + string2 + ", service: " + string3 + ", action: " + string4 + ", actionArgs: " + string5);
                callCordovaPluginMethod(string2, string3, string4, string5);
            } else if (z3) {
                Logger.error("JavaScript Error: " + str);
            } else {
                String string6 = jSObject.getString("pluginId");
                String string7 = jSObject.getString("methodName");
                JSObject jSObject2 = jSObject.getJSObject("options", new JSObject());
                Logger.verbose(Logger.tags("Plugin"), "To native (Capacitor plugin): callbackId: " + string2 + ", pluginId: " + string6 + ", methodName: " + string7);
                callPluginMethod(string2, string6, string7, jSObject2);
            }
        } catch (Exception e) {
            Logger.error("Post message error:", e);
        }
    }

    @SuppressLint("WrongConstant")
    public void sendResponseMessage(PluginCall pluginCall, PluginResult pluginResult, PluginResult pluginResult2) {
        JavaScriptReplyProxy javaScriptReplyProxy2;
        try {
            PluginResult pluginResult3 = new PluginResult();
            pluginResult3.put("save", pluginCall.isKeptAlive());
            pluginResult3.put("callbackId", (Object) pluginCall.getCallbackId());
            pluginResult3.put("pluginId", (Object) pluginCall.getPluginId());
            pluginResult3.put("methodName", (Object) pluginCall.getMethodName());
            if (pluginResult2 != null) {
                pluginResult3.put("success", false);
                pluginResult3.put("error", pluginResult2);
                Logger.debug("Sending plugin error: " + pluginResult3.toString());
            } else {
                pluginResult3.put("success", true);
                if (pluginResult != null) {
                    pluginResult3.put("data", pluginResult);
                }
            }
            if (!(!pluginCall.getCallbackId().equals( com.getcapacitor.PluginCall.CALLBACK_ID_DANGLING))) {
                this.bridge.getApp().fireRestoredResult(pluginResult3);
            } else if (this.bridge.getConfig().isUsingLegacyBridge()) {
                legacySendResponseMessage(pluginResult3);
            } else if (!WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER") || (javaScriptReplyProxy2 = this.javaScriptReplyProxy) == null) {
                legacySendResponseMessage(pluginResult3);
            } else {
                javaScriptReplyProxy2.postMessage(pluginResult3.toString());
            }
        } catch (Exception e) {
            Logger.error("sendResponseMessage: error: " + e);
        }
        if (!pluginCall.isKeptAlive()) {
            pluginCall.release(this.bridge);
        }
    }

    private void legacySendResponseMessage(PluginResult pluginResult) {
        WebView webView2 = this.webView;
        webView2.post(new com.getcapacitor.MessageHandler$$ExternalSyntheticLambda0(webView2, "window.Capacitor.fromNative(" + pluginResult.toString() + ")"));
    }

    private void callPluginMethod(String str, String str2, String str3, JSObject jSObject) {
        this.bridge.callPluginMethod(str2, str3, new com.getcapacitor.PluginCall(this, str2, str, str3, jSObject));
    }

    private void callCordovaPluginMethod(String str, String str2, String str3, String str4) {
        this.bridge.execute(new com.getcapacitor.MessageHandler$$ExternalSyntheticLambda1(this, str2, str3, str, str4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$callCordovaPluginMethod$2(String str, String str2, String str3, String str4) {
        this.cordovaPluginManager.exec(str, str2, str3, str4);
    }
}
