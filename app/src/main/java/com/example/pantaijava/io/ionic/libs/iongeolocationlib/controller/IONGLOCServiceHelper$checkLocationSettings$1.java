package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper", f = "IONGLOCServiceHelper.kt", i = {0}, l = {55}, m = "checkLocationSettings$IONGeolocationLib_release", n = {"this"}, s = {"L$0"})
/* compiled from: IONGLOCServiceHelper.kt */
final class IONGLOCServiceHelper$checkLocationSettings$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCServiceHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONGLOCServiceHelper$checkLocationSettings$1(IONGLOCServiceHelper iONGLOCServiceHelper, Continuation<? super IONGLOCServiceHelper$checkLocationSettings$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCServiceHelper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkLocationSettings$IONGeolocationLib_release((Activity) null, (IONGLOCLocationOptions) null, 0, this);
    }
}
