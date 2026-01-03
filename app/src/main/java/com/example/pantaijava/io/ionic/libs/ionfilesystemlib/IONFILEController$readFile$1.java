package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.IONFILEController", f = "IONFILEController.kt", i = {0, 0}, l = {295, 101, 103}, m = "readFile-0E7RQCE", n = {"this", "options"}, s = {"L$0", "L$1"})
/* compiled from: IONFILEController.kt */
final class IONFILEController$readFile$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEController$readFile$1(IONFILEController iONFILEController, Continuation<? super IONFILEController$readFile$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m160readFile0E7RQCE((IONFILEUri) null, (IONFILEReadOptions) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
