package io.ionic.libs.ionfilesystemlib.helper;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "chunk", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$1$1$1", f = "IONFILEContentHelper.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$readFileInChunks$1$1$1 extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector<String> $$this$flow;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$readFileInChunks$1$1$1(FlowCollector<? super String> flowCollector, Continuation<? super IONFILEContentHelper$readFileInChunks$1$1$1> continuation) {
        super(2, continuation);
        this.$$this$flow = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$readFileInChunks$1$1$1 iONFILEContentHelper$readFileInChunks$1$1$1 = new IONFILEContentHelper$readFileInChunks$1$1$1(this.$$this$flow, continuation);
        iONFILEContentHelper$readFileInChunks$1$1$1.L$0 = obj;
        return iONFILEContentHelper$readFileInChunks$1$1$1;
    }

    public final Object invoke(String str, Continuation<? super Unit> continuation) {
        return ((IONFILEContentHelper$readFileInChunks$1$1$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector<String> flowCollector = this.$$this$flow;
            this.label = 1;
            if (flowCollector.emit((String) this.L$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
