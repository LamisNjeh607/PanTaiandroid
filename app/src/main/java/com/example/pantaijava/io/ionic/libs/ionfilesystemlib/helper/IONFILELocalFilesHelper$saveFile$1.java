package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper", f = "IONFILELocalFilesHelper.kt", i = {}, l = {137}, m = "saveFile-0E7RQCE", n = {}, s = {})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$saveFile$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILELocalFilesHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$saveFile$1(IONFILELocalFilesHelper iONFILELocalFilesHelper, Continuation<? super IONFILELocalFilesHelper$saveFile$1> continuation) {
        super(continuation);
        this.this$0 = iONFILELocalFilesHelper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m179saveFile0E7RQCE((String) null, (IONFILESaveOptions) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
