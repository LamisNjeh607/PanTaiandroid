package com.example.pantaijava.activity;

/**
 * Java equivalent of OnBackPressedDispatcher$addCancellableCallback$1
 * This is a callback that calls updateEnabledCallbacks() on OnBackPressedDispatcher
 */
class UpdateEnabledCallbacksCallback implements Runnable {
    private final OnBackPressedDispatcher dispatcher;

    public UpdateEnabledCallbacksCallback(OnBackPressedDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        dispatcher.updateEnabledCallbacks();
    }
}