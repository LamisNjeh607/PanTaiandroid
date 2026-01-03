package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00040\u0005H\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0005H\u0007¢\u0006\u0002\b\n\u001a7\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00040\u0005H\u0002¢\u0006\u0002\b\r\u001a&\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0007\u001a\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0000\u001a-\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0016\u0010\t\u001a-\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0017\u0010\t\u001a-\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0019\u0010\t¨\u0006\u001a"}, d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", "T", "timeoutMillis", "", "Lkotlin/Function1;", "timeout", "Lkotlin/time/Duration;", "debounce-HG0u8IE", "(Lkotlinx/coroutines/flow/Flow;J)Lkotlinx/coroutines/flow/Flow;", "debounceDuration", "debounceInternal", "timeoutMillisSelector", "debounceInternal$FlowKt__DelayKt", "sample", "periodMillis", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "period", "sample-HG0u8IE", "timeout-HG0u8IE", "timeoutInternal", "timeoutInternal-HG0u8IE$FlowKt__DelayKt", "kotlinx-coroutines-core"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Delay.kt */
final /* synthetic */ class FlowKt__DelayKt {
    /* access modifiers changed from: private */
    public static final long debounce$lambda$1$FlowKt__DelayKt(long j, Object obj) {
        return j;
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, long j) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? flow : debounceInternal$FlowKt__DelayKt(flow, new FlowKt__DelayKt$$ExternalSyntheticLambda1(j));
        }
        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, Function1<? super T, Long> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, function1);
    }

    /* renamed from: debounce-HG0u8IE  reason: not valid java name */
    public static final <T> Flow<T> m1755debounceHG0u8IE(Flow<? extends T> flow, long j) {
        return FlowKt.debounce(flow, DelayKt.m1698toDelayMillisLRDsOJo(j));
    }

    public static final <T> Flow<T> debounceDuration(Flow<? extends T> flow, Function1<? super T, Duration> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, new FlowKt__DelayKt$$ExternalSyntheticLambda0(function1));
    }

    /* access modifiers changed from: private */
    public static final long debounce$lambda$2$FlowKt__DelayKt(Function1 function1, Object obj) {
        return DelayKt.m1698toDelayMillisLRDsOJo(((Duration) function1.invoke(obj)).m1577unboximpl());
    }

    private static final <T> Flow<T> debounceInternal$FlowKt__DelayKt(Flow<? extends T> flow, Function1<? super T, Long> function1) {
        return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$debounceInternal$1(function1, flow, (Continuation<? super FlowKt__DelayKt$debounceInternal$1>) null));
    }

    public static final <T> Flow<T> sample(Flow<? extends T> flow, long j) {
        if (j > 0) {
            return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$sample$2(j, flow, (Continuation<? super FlowKt__DelayKt$sample$2>) null));
        }
        throw new IllegalArgumentException("Sample period should be positive".toString());
    }

    public static final ReceiveChannel<Unit> fixedPeriodTicker(CoroutineScope coroutineScope, long j) {
        return ProduceKt.produce$default(coroutineScope, (CoroutineContext) null, 0, new FlowKt__DelayKt$fixedPeriodTicker$1(j, (Continuation<? super FlowKt__DelayKt$fixedPeriodTicker$1>) null), 1, (Object) null);
    }

    /* renamed from: sample-HG0u8IE  reason: not valid java name */
    public static final <T> Flow<T> m1756sampleHG0u8IE(Flow<? extends T> flow, long j) {
        return FlowKt.sample(flow, DelayKt.m1698toDelayMillisLRDsOJo(j));
    }

    /* renamed from: timeout-HG0u8IE  reason: not valid java name */
    public static final <T> Flow<T> m1757timeoutHG0u8IE(Flow<? extends T> flow, long j) {
        return m1758timeoutInternalHG0u8IE$FlowKt__DelayKt(flow, j);
    }

    /* renamed from: timeoutInternal-HG0u8IE$FlowKt__DelayKt  reason: not valid java name */
    private static final <T> Flow<T> m1758timeoutInternalHG0u8IE$FlowKt__DelayKt(Flow<? extends T> flow, long j) {
        return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$timeoutInternal$1(j, flow, (Continuation<? super FlowKt__DelayKt$timeoutInternal$1>) null));
    }
}
