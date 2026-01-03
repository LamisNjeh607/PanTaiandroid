package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a2\u0010\u0005\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0007H\b¢\u0006\u0002\u0010\b\u001a2\u0010\t\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0007H\b¢\u0006\u0002\u0010\b\u001a-\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0007H\b\u001a6\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"MutableStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "T", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "updateAndGet", "function", "Lkotlin/Function1;", "(Lkotlinx/coroutines/flow/MutableStateFlow;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getAndUpdate", "update", "", "NONE", "Lkotlinx/coroutines/internal/Symbol;", "PENDING", "fuseStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/StateFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: StateFlow.kt */
public final class StateFlowKt {
    /* access modifiers changed from: private */
    public static final Symbol NONE = new Symbol("NONE");
    /* access modifiers changed from: private */
    public static final Symbol PENDING = new Symbol("PENDING");

    public static final <T> MutableStateFlow<T> MutableStateFlow(T t) {
        if (t == null) {
            t = NullSurrogateKt.NULL;
        }
        return new StateFlowImpl<>(t);
    }

    public static final <T> T updateAndGet(MutableStateFlow<T> mutableStateFlow, Function1<? super T, ? extends T> function1) {
        T value;
        T invoke;
        do {
            value = mutableStateFlow.getValue();
            invoke = function1.invoke(value);
        } while (!mutableStateFlow.compareAndSet(value, invoke));
        return invoke;
    }

    public static final <T> T getAndUpdate(MutableStateFlow<T> mutableStateFlow, Function1<? super T, ? extends T> function1) {
        T value;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, function1.invoke(value)));
        return value;
    }

    public static final <T> void update(MutableStateFlow<T> mutableStateFlow, Function1<? super T, ? extends T> function1) {
        T value;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, function1.invoke(value)));
    }

    public static final <T> Flow<T> fuseStateFlow(StateFlow<? extends T> stateFlow, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        if (DebugKt.getASSERTIONS_ENABLED() && i == -1) {
            throw new AssertionError();
        } else if (((i < 0 || i >= 2) && i != -2) || bufferOverflow != BufferOverflow.DROP_OLDEST) {
            return SharedFlowKt.fuseSharedFlow(stateFlow, coroutineContext, i, bufferOverflow);
        } else {
            return stateFlow;
        }
    }
}
