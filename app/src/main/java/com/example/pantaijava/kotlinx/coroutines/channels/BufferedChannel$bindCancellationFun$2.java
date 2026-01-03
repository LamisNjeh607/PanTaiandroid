package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
/* synthetic */ class BufferedChannel$bindCancellationFun$2 extends FunctionReferenceImpl implements Function3<Throwable, E, CoroutineContext, Unit> {
    BufferedChannel$bindCancellationFun$2(Object obj) {
        super(3, obj, BufferedChannel.class, "onCancellationImplDoNotCall", "onCancellationImplDoNotCall(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((Throwable) obj, obj2, (CoroutineContext) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th, E e, CoroutineContext coroutineContext) {
        ((BufferedChannel) this.receiver).onCancellationImplDoNotCall(th, e, coroutineContext);
    }
}
