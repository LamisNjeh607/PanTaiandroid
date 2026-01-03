package com.example.pantaijava.com.getcapacitor.community.tts;

public interface SpeakResultCallback {
    void onDone();

    void onError();

    void onRangeStart(int i, int i2);
}
