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
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController", f = "IONGLOCController.kt", i = {0, 0, 1}, l = {53, 59}, m = "getCurrentPosition-0E7RQCE", n = {"this", "options", "this"}, s = {"L$0", "L$1", "L$0"})
/* compiled from: IONGLOCController.kt */
final class IONGLOCController$getCurrentPosition$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONGLOCController$getCurrentPosition$1(IONGLOCController iONGLOCController, Continuation<? super IONGLOCController$getCurrentPosition$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m183getCurrentPosition0E7RQCE((Activity) null, (IONGLOCLocationOptions) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
