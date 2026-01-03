package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.IONFILEController", f = "IONFILEController.kt", i = {0}, l = {294, 165, 167}, m = "getMetadata-gIAlu-s", n = {"this"}, s = {"L$0"})
/* compiled from: IONFILEController.kt */
final class IONFILEController$getMetadata$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEController$getMetadata$1(IONFILEController iONFILEController, Continuation<? super IONFILEController$getMetadata$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m157getMetadatagIAlus((IONFILEUri) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
