package com.example.pantaijava.androidx.activity;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.CoroutineScope;

public  class OnBackPressedDispatcher$addCallback$1 extends FunctionReferenceImpl implements Function0<Unit> {
    OnBackPressedDispatcher$addCallback$1(Object obj) {
        super(0, obj, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
    }

    public final CoroutineScope invoke() {
        ((OnBackPressedDispatcher) this.receiver).updateEnabledCallbacks();
        return null;
    }
}
