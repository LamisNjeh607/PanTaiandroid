package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper", f = "IONFILEContentHelper.kt", i = {}, l = {39}, m = "readFile-0E7RQCE", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$readFile$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$readFile$1(IONFILEContentHelper iONFILEContentHelper, Continuation<? super IONFILEContentHelper$readFile$1> continuation) {
        super(continuation);
        this.this$0 = iONFILEContentHelper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r3 = this.this$0.m167readFile0E7RQCE((Uri) null, (IONFILEReadOptions) null, this);
        return r3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r3 : Result.m189boximpl(r3);
    }
}
