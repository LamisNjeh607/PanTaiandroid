package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.IONFILEController", f = "IONFILEController.kt", i = {0, 0, 1, 1, 2, 3}, l = {295, 303, 284, 286}, m = "move-0E7RQCE", n = {"this", "destination", "this", "resolvedSource", "resolvedDestination", "resolvedDestination"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
/* compiled from: IONFILEController.kt */
final class IONFILEController$move$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEController$move$1(IONFILEController iONFILEController, Continuation<? super IONFILEController$move$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m159move0E7RQCE((IONFILEUri) null, (IONFILEUri) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
