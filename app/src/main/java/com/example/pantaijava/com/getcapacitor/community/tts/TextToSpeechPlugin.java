package com.example.pantaijava.com.getcapacitor.community.tts;

import com.example.pantaijava.com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;


@CapacitorPlugin(name = "TextToSpeech")
public class TextToSpeechPlugin extends Plugin {
    public static final String ERROR_UNSUPPORTED_LANGUAGE = "This language is not supported.";
    public static final String ERROR_UTTERANCE = "Failed to read text.";
    public static final String LOG_TAG = "TextToSpeechPlugin";
    private TextToSpeech implementation;

    public void load() {
        this.implementation = new TextToSpeech(getContext());
    }

    @PluginMethod
    public void speak(final PluginCall pluginCall) {
        if (!this.implementation.isAvailable()) {
            pluginCall.unavailable("Not yet initialized or not available on this device.");
            return;
        }
        final String string = pluginCall.getString("text", "");
        String string2 = pluginCall.getString("lang", "en-US");
        float floatValue = pluginCall.getFloat("rate", Float.valueOf(1.0f)).floatValue();
        float floatValue2 = pluginCall.getFloat("pitch", Float.valueOf(1.0f)).floatValue();
        float floatValue3 = pluginCall.getFloat("volume", Float.valueOf(1.0f)).floatValue();
        int intValue = pluginCall.getInt("voice", -1).intValue();
        int intValue2 = pluginCall.getInt("queueStrategy", 0).intValue();
        if (!this.implementation.isLanguageSupported(string2)) {
            pluginCall.reject(ERROR_UNSUPPORTED_LANGUAGE);
            return;
        }
        try {
            this.implementation.speak(string, string2, floatValue, floatValue2, floatValue3, intValue, pluginCall.getCallbackId(), new SpeakResultCallback() {
                public void onDone() {
                    pluginCall.resolve( jSObject );
                }

                public void onError() {
                    pluginCall.reject(TextToSpeechPlugin.ERROR_UTTERANCE);
                }

                public void onRangeStart(int i, int i2) {
                    JSObject jSObject = new JSObject();
                    jSObject.put("start", i);
                    jSObject.put("end", i2);
                    jSObject.put("spokenWord", string.substring(i, i2));
                    TextToSpeechPlugin.this.notifyListeners("onRangeStart", jSObject);
                }
            }, intValue2);
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void stop(PluginCall pluginCall) {
        if (!this.implementation.isAvailable()) {
            pluginCall.unavailable("Not yet initialized or not available on this device.");
            return;
        }
        try {
            this.implementation.stop();
            pluginCall.resolve( jSObject );
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void getSupportedLanguages(PluginCall pluginCall) {
        try {
            JSArray supportedLanguages = this.implementation.getSupportedLanguages();
            JSObject jSObject = new JSObject();
            jSObject.put("languages", (Object) supportedLanguages);
            pluginCall.resolve(jSObject);
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void getSupportedVoices(PluginCall pluginCall) {
        try {
            JSArray supportedVoices = this.implementation.getSupportedVoices();
            JSObject jSObject = new JSObject();
            jSObject.put("voices", (Object) supportedVoices);
            pluginCall.resolve(jSObject);
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void isLanguageSupported(PluginCall pluginCall) {
        try {
            boolean isLanguageSupported = this.implementation.isLanguageSupported(pluginCall.getString("lang", ""));
            JSObject jSObject = new JSObject();
            jSObject.put("supported", isLanguageSupported);
            pluginCall.resolve(jSObject);
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void openInstall(PluginCall pluginCall) {
        try {
            this.implementation.openInstall();
            pluginCall.resolve( jSObject );
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        this.implementation.onDestroy();
    }
}
