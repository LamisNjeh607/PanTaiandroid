package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController", f = "IONGLOCController.kt", i = {0}, l = {173, 182}, m = "checkLocationPreconditions-BWLJW6A", n = {"this"}, s = {"L$0"})
/* compiled from: IONGLOCController.kt */
final class IONGLOCController$checkLocationPreconditions$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONGLOCController$checkLocationPreconditions$1(IONGLOCController iONGLOCController, Continuation<? super IONGLOCController$checkLocationPreconditions$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r4 = this.this$0.m182checkLocationPreconditionsBWLJW6A((Activity) null, (IONGLOCLocationOptions) null, false, this);
        return r4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r4 : Result.m189boximpl(r4);
    }
}
