package androidx.core.os;

import android.content.Context;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroid/os/ProfilingResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.core.os.Profiling$registerForAllProfilingResults$1", f = "Profiling.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Profiling.kt */
final class Profiling$registerForAllProfilingResults$1 extends SuspendLambda implements Function2<ProducerScope<? super ProfilingResult>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Profiling$registerForAllProfilingResults$1(Context context, Continuation<? super Profiling$registerForAllProfilingResults$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Profiling$registerForAllProfilingResults$1 profiling$registerForAllProfilingResults$1 = new Profiling$registerForAllProfilingResults$1(this.$context, continuation);
        profiling$registerForAllProfilingResults$1.L$0 = obj;
        return profiling$registerForAllProfilingResults$1;
    }

    public final Object invoke(ProducerScope<? super ProfilingResult> producerScope, Continuation<? super Unit> continuation) {
        return ((Profiling$registerForAllProfilingResults$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final Profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0 profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0 = new Profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0(producerScope);
            final ProfilingManager profilingManager = (ProfilingManager) this.$context.getSystemService(ProfilingManager.class);
            profilingManager.registerForAllProfilingResults(new Profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda1(), profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    profilingManager.unregisterForAllProfilingResults(profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(ProducerScope producerScope, ProfilingResult profilingResult) {
        Intrinsics.checkNotNullExpressionValue(profilingResult, "result");
        producerScope.m1748trySendJP2dKIU(profilingResult);
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(Runnable runnable) {
        runnable.run();
    }
}
