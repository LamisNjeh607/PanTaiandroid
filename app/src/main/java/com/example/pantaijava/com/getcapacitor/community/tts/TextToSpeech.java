package com.example.pantaijava.com.getcapacitor.community.tts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.util.Log;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class TextToSpeech implements TextToSpeech.OnInitListener {
    public static final String LOG_TAG = "TextToSpeech";
    private Context context;
    private int initializationStatus;
    /* access modifiers changed from: private */
    public Map<String, SpeakResultCallback> requests = new HashMap();
    private JSObject[] supportedVoices = null;
    private android.speech.tts.TextToSpeech tts = null;

    TextToSpeech(Context context2) {
        this.context = context2;
        try {
            android.speech.tts.TextToSpeech textToSpeech = new android.speech.tts.TextToSpeech(context2, this);
            this.tts = textToSpeech;
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                public void onStart(String str) {
                }

                public void onDone(String str) {
                    SpeakResultCallback speakResultCallback = (SpeakResultCallback) TextToSpeech.this.requests.get(str);
                    if (speakResultCallback != null) {
                        speakResultCallback.onDone();
                        TextToSpeech.this.requests.remove(str);
                    }
                }

                public void onError(String str) {
                    SpeakResultCallback speakResultCallback = (SpeakResultCallback) TextToSpeech.this.requests.get(str);
                    if (speakResultCallback != null) {
                        speakResultCallback.onError();
                        TextToSpeech.this.requests.remove(str);
                    }
                }

                public void onRangeStart(String str, int i, int i2, int i3) {
                    SpeakResultCallback speakResultCallback = (SpeakResultCallback) TextToSpeech.this.requests.get(str);
                    if (speakResultCallback != null) {
                        speakResultCallback.onRangeStart(i, i2);
                    }
                }
            });
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getLocalizedMessage());
        }
    }

    public void onInit(int i) {
        this.initializationStatus = i;
    }

    public void speak(String str, String str2, float f, float f2, float f3, int i, String str3, SpeakResultCallback speakResultCallback) {
        speak(str, str2, f, f2, f3, i, str3, speakResultCallback, 0);
    }

    public void speak(String str, String str2, float f, float f2, float f3, int i, String str3, SpeakResultCallback speakResultCallback, int i2) {
        if (i2 != 1) {
            stop();
        }
        this.requests.put(str3, speakResultCallback);
        Locale forLanguageTag = Locale.forLanguageTag(str2);
        Bundle bundle = new Bundle();
        bundle.putSerializable("utteranceId", str3);
        bundle.putSerializable("volume", Float.valueOf(f3));
        this.tts.setLanguage(forLanguageTag);
        this.tts.setSpeechRate(f);
        this.tts.setPitch(f2);
        if (i >= 0) {
            ArrayList<Voice> supportedVoicesOrdered = getSupportedVoicesOrdered();
            if (i < supportedVoicesOrdered.size()) {
                this.tts.setVoice(supportedVoicesOrdered.get(i));
            }
        }
        this.tts.speak(str, i2, bundle, str3);
    }

    public void stop() {
        this.tts.stop();
        this.requests.clear();
    }

    public JSArray getSupportedLanguages() {
        ArrayList arrayList = new ArrayList();
        for (Locale languageTag : this.tts.getAvailableLanguages()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return JSArray.from(arrayList.toArray());
    }

    public ArrayList<Voice> getSupportedVoicesOrdered() {
        Set<Voice> voices = this.tts.getVoices();
        ArrayList<Voice> arrayList = new ArrayList<>();
        for (Voice add : voices) {
            arrayList.add(add);
        }
        Collections.sort(arrayList, new TextToSpeech$$ExternalSyntheticLambda0());
        return arrayList;
    }

    public JSArray getSupportedVoices() {
        ArrayList arrayList = new ArrayList();
        Iterator<Voice> it = getSupportedVoicesOrdered().iterator();
        while (it.hasNext()) {
            arrayList.add(convertVoiceToJSObject(it.next()));
        }
        return JSArray.from(arrayList.toArray());
    }

    public void openInstall() {
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.speech.tts.engine.CHECK_TTS_DATA");
        if (packageManager.resolveActivity(intent, 65536) != null) {
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }
    }

    public boolean isAvailable() {
        return this.tts != null && this.initializationStatus == 0;
    }

    public boolean isLanguageSupported(String str) {
        int isLanguageAvailable = this.tts.isLanguageAvailable(Locale.forLanguageTag(str));
        return isLanguageAvailable == 0 || isLanguageAvailable == 1 || isLanguageAvailable == 2;
    }

    public void onDestroy() {
        android.speech.tts.TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
            this.tts.shutdown();
        }
    }

    private JSObject convertVoiceToJSObject(Voice voice) {
        Locale locale = voice.getLocale();
        JSObject jSObject = new JSObject();
        jSObject.put("voiceURI", voice.getName());
        jSObject.put("name", locale.getDisplayLanguage() + " " + locale.getDisplayCountry());
        jSObject.put("lang", locale.toLanguageTag());
        jSObject.put("localService", !voice.isNetworkConnectionRequired());
        jSObject.put("default", false);
        return jSObject;
    }
}
