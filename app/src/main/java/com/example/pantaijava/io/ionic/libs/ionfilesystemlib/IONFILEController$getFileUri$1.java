package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.IONFILEController", f = "IONFILEController.kt", i = {}, l = {56}, m = "getFileUri-gIAlu-s", n = {}, s = {})
/* compiled from: IONFILEController.kt */
final class IONFILEController$getFileUri$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEController$getFileUri$1(IONFILEController iONFILEController, Continuation<? super IONFILEController$getFileUri$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m156getFileUrigIAlus((IONFILEUri.Unresolved) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
