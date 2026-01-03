package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper", f = "IONFILEDirectoriesHelper.kt", i = {}, l = {57}, m = "deleteDirectory-0E7RQCE", n = {}, s = {})
/* compiled from: IONFILEDirectoriesHelper.kt */
final class IONFILEDirectoriesHelper$deleteDirectory$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEDirectoriesHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEDirectoriesHelper$deleteDirectory$1(IONFILEDirectoriesHelper iONFILEDirectoriesHelper, Continuation<? super IONFILEDirectoriesHelper$deleteDirectory$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEDirectoriesHelper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m170deleteDirectory0E7RQCE((String) null, (IONFILEDeleteOptions) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
