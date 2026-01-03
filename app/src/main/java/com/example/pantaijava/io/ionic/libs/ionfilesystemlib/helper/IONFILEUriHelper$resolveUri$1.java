package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper", f = "IONFILEUriHelper.kt", i = {}, l = {47, 52, 60}, m = "resolveUri-gIAlu-s", n = {}, s = {})
/* compiled from: IONFILEUriHelper.kt */
final class IONFILEUriHelper$resolveUri$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEUriHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEUriHelper$resolveUri$1(IONFILEUriHelper iONFILEUriHelper, Continuation<? super IONFILEUriHelper$resolveUri$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEUriHelper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m180resolveUrigIAlus((IONFILEUri.Unresolved) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
