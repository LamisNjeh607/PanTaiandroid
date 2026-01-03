package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$2", f = "IONFILEContentHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$readFileInChunks$2 extends SuspendLambda implements Function3<FlowCollector<? super String>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $uri;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$readFileInChunks$2(IONFILEContentHelper iONFILEContentHelper, Uri uri, Continuation<? super IONFILEContentHelper$readFileInChunks$2> continuation) {
        super(3, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$uri = uri;
    }

    public final Object invoke(FlowCollector<? super String> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        IONFILEContentHelper$readFileInChunks$2 iONFILEContentHelper$readFileInChunks$2 = new IONFILEContentHelper$readFileInChunks$2(this.this$0, this.$uri, continuation);
        iONFILEContentHelper$readFileInChunks$2.L$0 = th;
        return iONFILEContentHelper$readFileInChunks$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        throw this.this$0.mapError((Throwable) this.L$0, this.$uri);
    }
}
