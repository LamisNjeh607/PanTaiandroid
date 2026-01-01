package com.example.pantaijava.activity;

/**
 * Java equivalent of Kotlin synthetic function reference.
 * This is a callback that calls updateEnabledCallbacks() on an OnBackPressedDispatcher.
 */
class OnBackPressedDispatcher$addCancellableCallback$1 implements Runnable {
    private OnBackPressedDispatcher$addCancellableCallback$1 dispatcher;

    public void UpdateEnabledCallbacksCallback(OnBackPressedDispatcher$addCancellableCallback$1 dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        dispatcher.updateEnabledCallbacks();
    }

    private void updateEnabledCallbacks() {
    }
}