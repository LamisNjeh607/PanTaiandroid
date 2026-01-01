package com.example.pantaijava.activity;

import android.window.OnBackInvokedCallback;

/**
 * Java equivalent of OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0
 * Creates an OnBackInvokedCallback that wraps a Runnable
 */
class Api33OnBackInvokedCallback {
    private final Runnable onBackInvoked;

    public Api33OnBackInvokedCallback(Runnable onBackInvoked) {
        this.onBackInvoked = onBackInvoked;
    }


    public void onBackInvoked() {
        if (this.onBackInvoked != null) {
            this.onBackInvoked.run();
        }
    }
}