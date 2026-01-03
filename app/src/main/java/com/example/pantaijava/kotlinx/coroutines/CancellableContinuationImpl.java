package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0005j\u0002`\u00042\u00020\u0006B\u001d\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020!H\u0002J\b\u0010,\u001a\u00020!H\u0001J\u0015\u00100\u001a\n\u0018\u000102j\u0004\u0018\u0001`1H\u0016¢\u0006\u0002\u00103J\u000f\u00104\u001a\u0004\u0018\u00010\u0017H\u0010¢\u0006\u0002\b5J\u001f\u00106\u001a\u00020*2\b\u00107\u001a\u0004\u0018\u00010\u00172\u0006\u00108\u001a\u000209H\u0010¢\u0006\u0002\b:J\u0010\u0010;\u001a\u00020!2\u0006\u00108\u001a\u000209H\u0002J\u0012\u0010<\u001a\u00020!2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0015\u0010=\u001a\u00020*2\u0006\u00108\u001a\u000209H\u0000¢\u0006\u0002\b>J\u0017\u0010?\u001a\u00020*2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020*0AH\bJ\u0018\u0010B\u001a\u00020*2\u0006\u0010C\u001a\u00020D2\b\u00108\u001a\u0004\u0018\u000109J\u001e\u0010E\u001a\u00020*2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030G2\b\u00108\u001a\u0004\u0018\u000109H\u0002Jn\u0010H\u001a\u00020*\"\u0004\b\u0001\u0010I2K\u0010J\u001aG\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*0K2\u0006\u00108\u001a\u0002092\u0006\u0010N\u001a\u0002HI¢\u0006\u0002\u0010OJ\u0010\u0010P\u001a\u0002092\u0006\u0010Q\u001a\u00020RH\u0016J\b\u0010S\u001a\u00020!H\u0002J\b\u0010T\u001a\u00020!H\u0002J\n\u0010U\u001a\u0004\u0018\u00010\u0017H\u0001J\n\u0010V\u001a\u0004\u0018\u00010\u0019H\u0002J\r\u0010W\u001a\u00020*H\u0000¢\u0006\u0002\bXJ\u001b\u0010Y\u001a\u00020*2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000[H\u0016¢\u0006\u0002\u0010\\J:\u0010]\u001a\u00020*2\u0006\u0010N\u001a\u00028\u00002#\u0010J\u001a\u001f\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020*\u0018\u00010^H\u0016¢\u0006\u0002\u0010_Jn\u0010]\u001a\u00020*\"\b\b\u0001\u0010I*\u00028\u00002\u0006\u0010N\u001a\u0002HI2M\u0010J\u001aI\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*\u0018\u00010KH\u0016¢\u0006\u0002\u0010`J\u001c\u0010a\u001a\u00020*2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030G2\u0006\u0010b\u001a\u00020\nH\u0016J6\u0010a\u001a\u00020*2'\u0010C\u001a#\u0012\u0015\u0012\u0013\u0018\u000109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020*0^j\u0002`cH\u0016¢\u0006\u0002\u0010dJ\u0015\u0010e\u001a\u00020*2\u0006\u0010C\u001a\u00020DH\u0000¢\u0006\u0002\bfJ\u0010\u0010g\u001a\u00020*2\u0006\u0010C\u001a\u00020\u0017H\u0002J\u001a\u0010h\u001a\u00020*2\u0006\u0010C\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010i\u001a\u00020*2\u0006\u0010j\u001a\u00020\nH\u0002J\u0001\u0010k\u001a\u0004\u0018\u00010\u0017\"\u0004\b\u0001\u0010I2\u0006\u0010\u001d\u001a\u00020l2\u0006\u0010m\u001a\u0002HI2\u0006\u0010\t\u001a\u00020\n2M\u0010J\u001aI\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*\u0018\u00010K2\b\u0010n\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010oJv\u0010p\u001a\u00020*\"\u0004\b\u0001\u0010I2\u0006\u0010m\u001a\u0002HI2\u0006\u0010\t\u001a\u00020\n2O\b\u0002\u0010J\u001aI\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*\u0018\u00010KH\u0000¢\u0006\u0004\bq\u0010rJv\u0010s\u001a\u0004\u0018\u00010t\"\u0004\b\u0001\u0010I2\u0006\u0010m\u001a\u0002HI2\b\u0010n\u001a\u0004\u0018\u00010\u00172M\u0010J\u001aI\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*\u0018\u00010KH\u0002¢\u0006\u0002\u0010uJ\u0012\u0010v\u001a\u00020w2\b\u0010m\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010x\u001a\u00020*H\u0002J\r\u0010y\u001a\u00020*H\u0000¢\u0006\u0002\bzJ!\u0010T\u001a\u0004\u0018\u00010\u00172\u0006\u0010N\u001a\u00028\u00002\b\u0010n\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010{Jz\u0010T\u001a\u0004\u0018\u00010\u0017\"\b\b\u0001\u0010I*\u00028\u00002\u0006\u0010N\u001a\u0002HI2\b\u0010n\u001a\u0004\u0018\u00010\u00172M\u0010J\u001aI\u0012\u0013\u0012\u001109¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011HI¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020*\u0018\u00010KH\u0016¢\u0006\u0002\u0010|J\u0012\u0010}\u001a\u0004\u0018\u00010\u00172\u0006\u0010~\u001a\u000209H\u0016J\u0011\u0010\u001a\u00020*2\u0007\u0010\u0001\u001a\u00020\u0017H\u0016J\u001c\u0010\u0001\u001a\u00020**\u00030\u00012\u0006\u0010N\u001a\u00028\u0000H\u0016¢\u0006\u0003\u0010\u0001J\u0016\u0010\u0001\u001a\u00020**\u00030\u00012\u0006\u0010~\u001a\u000209H\u0016J\"\u0010\u0001\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0010¢\u0006\u0006\b\u0001\u0010\u0001J\u001b\u0010\u0001\u001a\u0004\u0018\u0001092\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0010¢\u0006\u0003\b\u0001J\t\u0010\u0001\u001a\u00020&H\u0016J\t\u0010\u0001\u001a\u00020&H\u0014R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\t\u0010\u0013\u001a\u00020\u0014X\u0004R\u0011\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016X\u0004R\u0011\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016X\u0004R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00198BX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00178@X\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\"R\u0014\u0010#\u001a\u00020!8VX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\"R\u0014\u0010$\u001a\u00020!8VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0014\u0010%\u001a\u00020&8BX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001c\u0010-\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u0006\u0001"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/Waiter;", "delegate", "Lkotlin/coroutines/Continuation;", "resumeMode", "", "<init>", "(Lkotlin/coroutines/Continuation;I)V", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "_decisionAndIndex", "Lkotlinx/atomicfu/AtomicInt;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "_parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "parentHandle", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "isActive", "", "()Z", "isCompleted", "isCancelled", "stateDebugRepresentation", "", "getStateDebugRepresentation", "()Ljava/lang/String;", "initCancellability", "", "isReusable", "resetStateReusable", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getStackTraceElement", "Lkotlinx/coroutines/internal/StackTraceElement;", "Ljava/lang/StackTraceElement;", "()Ljava/lang/StackTraceElement;", "takeState", "takeState$kotlinx_coroutines_core", "cancelCompletedResult", "takenState", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "cancelLater", "cancel", "parentCancelled", "parentCancelled$kotlinx_coroutines_core", "callCancelHandlerSafely", "block", "Lkotlin/Function0;", "callCancelHandler", "handler", "Lkotlinx/coroutines/CancelHandler;", "callSegmentOnCancellation", "segment", "Lkotlinx/coroutines/internal/Segment;", "callOnCancellation", "R", "onCancellation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "value", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Throwable;Ljava/lang/Object;)V", "getContinuationCancellationCause", "parent", "Lkotlinx/coroutines/Job;", "trySuspend", "tryResume", "getResult", "installParentHandle", "releaseClaimedReusableContinuation", "releaseClaimedReusableContinuation$kotlinx_coroutines_core", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resume", "Lkotlin/Function1;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "invokeOnCancellation", "index", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCancellationInternal", "invokeOnCancellationInternal$kotlinx_coroutines_core", "invokeOnCancellationImpl", "multipleHandlersError", "dispatchResume", "mode", "resumedState", "Lkotlinx/coroutines/NotCompleted;", "proposedUpdate", "idempotent", "(Lkotlinx/coroutines/NotCompleted;Ljava/lang/Object;ILkotlin/jvm/functions/Function3;Ljava/lang/Object;)Ljava/lang/Object;", "resumeImpl", "resumeImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;ILkotlin/jvm/functions/Function3;)V", "tryResumeImpl", "Lkotlinx/coroutines/internal/Symbol;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/internal/Symbol;", "alreadyResumedError", "", "detachChildIfNonResuable", "detachChild", "detachChild$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "tryResumeWithException", "exception", "completeResume", "token", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getExceptionalResult", "getExceptionalResult$kotlinx_coroutines_core", "toString", "nameString", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: CancellableContinuationImpl.kt */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame, Waiter {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater _decisionAndIndex$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _parentHandle$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$volatile$FU;
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;
    private final CoroutineContext context;
    private final Continuation<T> delegate;

    static {
        Class<CancellableContinuationImpl> cls = CancellableContinuationImpl.class;
        _decisionAndIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_decisionAndIndex$volatile");
        _state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_state$volatile");
        _parentHandle$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_parentHandle$volatile");
    }

    private final /* synthetic */ int get_decisionAndIndex$volatile() {
        return this._decisionAndIndex$volatile;
    }

    private final /* synthetic */ Object get_parentHandle$volatile() {
        return this._parentHandle$volatile;
    }

    private final /* synthetic */ Object get_state$volatile() {
        return this._state$volatile;
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater, Function1<? super Integer, Unit> function1) {
        while (true) {
            function1.invoke(Integer.valueOf(atomicIntegerFieldUpdater.get(obj)));
        }
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    private final /* synthetic */ void set_decisionAndIndex$volatile(int i) {
        this._decisionAndIndex$volatile = i;
    }

    private final /* synthetic */ void set_parentHandle$volatile(Object obj) {
        this._parentHandle$volatile = obj;
    }

    private final /* synthetic */ void set_state$volatile(Object obj) {
        this._state$volatile = obj;
    }

    private final /* synthetic */ void update$atomicfu(Object obj, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater, Function1<? super Integer, Integer> function1) {
        int i;
        do {
            i = atomicIntegerFieldUpdater.get(obj);
        } while (!atomicIntegerFieldUpdater.compareAndSet(obj, i, function1.invoke(Integer.valueOf(i)).intValue()));
    }

    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    public CancellableContinuationImpl(Continuation<? super T> continuation, int i) {
        super(i);
        this.delegate = continuation;
        if (!DebugKt.getASSERTIONS_ENABLED() || i != -1) {
            this.context = continuation.getContext();
            this._decisionAndIndex$volatile = 536870911;
            this._state$volatile = Active.INSTANCE;
            return;
        }
        throw new AssertionError();
    }

    public CoroutineContext getContext() {
        return this.context;
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) _parentHandle$volatile$FU.get(this);
    }

    public final Object getState$kotlinx_coroutines_core() {
        return _state$volatile$FU.get(this);
    }

    public boolean isActive() {
        return getState$kotlinx_coroutines_core() instanceof NotCompleted;
    }

    public boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    public boolean isCancelled() {
        return getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
    }

    private final String getStateDebugRepresentation() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof NotCompleted) {
            return "Active";
        }
        if (state$kotlinx_coroutines_core instanceof CancelledContinuation) {
            return "Cancelled";
        }
        return "Completed";
    }

    public void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && isCompleted()) {
            installParentHandle.dispose();
            _parentHandle$volatile$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    private final boolean isReusable() {
        if (DispatchedTaskKt.isReusableMode(this.resumeMode)) {
            Continuation<T> continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).isReusable$kotlinx_coroutines_core()) {
                return true;
            }
        }
        return false;
    }

    public final boolean resetStateReusable() {
        if (DebugKt.getASSERTIONS_ENABLED() && this.resumeMode != 2) {
            throw new AssertionError();
        } else if (!DebugKt.getASSERTIONS_ENABLED() || getParentHandle() != NonDisposableHandle.INSTANCE) {
            Object obj = _state$volatile$FU.get(this);
            if (DebugKt.getASSERTIONS_ENABLED() && !(!(obj instanceof NotCompleted))) {
                throw new AssertionError();
            } else if (!(obj instanceof CompletedContinuation) || ((CompletedContinuation) obj).idempotentResume == null) {
                _decisionAndIndex$volatile$FU.set(this, 536870911);
                _state$volatile$FU.set(this, Active.INSTANCE);
                return true;
            } else {
                detachChild$kotlinx_coroutines_core();
                return false;
            }
        } else {
            throw new AssertionError();
        }
    }

    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    public Object takeState$kotlinx_coroutines_core() {
        return getState$kotlinx_coroutines_core();
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$volatile$FU;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            } else if (!(obj2 instanceof CompletedExceptionally)) {
                if (obj2 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                    if (!completedContinuation.getCancelled()) {
                        Throwable th2 = th;
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj2, CompletedContinuation.copy$default(completedContinuation, (Object) null, (CancelHandler) null, (Function3) null, (Object) null, th, 15, (Object) null))) {
                            completedContinuation.invokeHandlers(this, th2);
                            return;
                        }
                    } else {
                        throw new IllegalStateException("Must be called at most once".toString());
                    }
                } else {
                    Throwable th3 = th;
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj2, new CompletedContinuation(obj2, (CancelHandler) null, (Function3) null, (Object) null, th, 14, (DefaultConstructorMarker) null))) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private final boolean cancelLater(Throwable th) {
        if (!isReusable()) {
            return false;
        }
        Continuation<T> continuation = this.delegate;
        Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return ((DispatchedContinuation) continuation).postponeCancellation$kotlinx_coroutines_core(th);
    }

    public boolean cancel(Throwable th) {
        Object obj;
        boolean z;
        Continuation continuation;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$volatile$FU;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            z = false;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            continuation = this;
            if ((obj instanceof CancelHandler) || (obj instanceof Segment)) {
                z = true;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj, new CancelledContinuation(continuation, th, z)));
        NotCompleted notCompleted = (NotCompleted) obj;
        if (notCompleted instanceof CancelHandler) {
            callCancelHandler((CancelHandler) obj, th);
        } else if (notCompleted instanceof Segment) {
            callSegmentOnCancellation((Segment) obj, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    public final void parentCancelled$kotlinx_coroutines_core(Throwable th) {
        if (!cancelLater(th)) {
            cancel(th);
            detachChildIfNonResuable();
        }
    }

    private final void callCancelHandlerSafely(Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Throwable th) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th));
        }
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    private final void callSegmentOnCancellation(Segment<?> segment, Throwable th) {
        int i = _decisionAndIndex$volatile$FU.get(this) & 536870911;
        if (i != 536870911) {
            try {
                segment.onCancellation(i, th, getContext());
            } catch (Throwable th2) {
                CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
            }
        } else {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
        }
    }

    public final <R> void callOnCancellation(Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, Throwable th, R r) {
        try {
            function3.invoke(th, r, getContext());
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public Throwable getContinuationCancellationCause(Job job) {
        return job.getCancellationException();
    }

    private final boolean trySuspend() {
        int i;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$volatile$FU;
        do {
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!_decisionAndIndex$volatile$FU.compareAndSet(this, i, 536870912 + (536870911 & i)));
        return true;
    }

    private final boolean tryResume() {
        int i;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$volatile$FU;
        do {
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!_decisionAndIndex$volatile$FU.compareAndSet(this, i, 1073741824 + (536870911 & i)));
        return true;
    }

    public final Object getResult() {
        Job job;
        boolean isReusable = isReusable();
        if (trySuspend()) {
            if (getParentHandle() == null) {
                installParentHandle();
            }
            if (isReusable) {
                releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            }
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                Continuation continuation = this;
                if (continuation instanceof CoroutineStackFrame) {
                    th = StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
                }
            }
            throw th;
        } else if (!DispatchedTaskKt.isCancellableMode(this.resumeMode) || (job = (Job) getContext().get(Job.Key)) == null || job.isActive()) {
            return getSuccessfulResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core);
        } else {
            Throwable cancellationException = job.getCancellationException();
            cancelCompletedResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core, cancellationException);
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                Continuation continuation2 = this;
                if (continuation2 instanceof CoroutineStackFrame) {
                    cancellationException = StackTraceRecoveryKt.recoverFromStackFrame(cancellationException, (CoroutineStackFrame) continuation2);
                }
            }
            throw cancellationException;
        }
    }

    private final DisposableHandle installParentHandle() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = JobKt__JobKt.invokeOnCompletion$default(job, false, new ChildContinuation(this), 1, (Object) null);
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_parentHandle$volatile$FU, this, (Object) null, invokeOnCompletion$default);
        return invokeOnCompletion$default;
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        Throwable tryReleaseClaimedContinuation$kotlinx_coroutines_core;
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation != null && (tryReleaseClaimedContinuation$kotlinx_coroutines_core = dispatchedContinuation.tryReleaseClaimedContinuation$kotlinx_coroutines_core(this)) != null) {
            detachChild$kotlinx_coroutines_core();
            cancel(tryReleaseClaimedContinuation$kotlinx_coroutines_core);
        }
    }

    public void resumeWith(Object obj) {
        resumeImpl$kotlinx_coroutines_core$default(this, CompletionStateKt.toState(obj, this), this.resumeMode, (Function3) null, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit resume$lambda$13$lambda$12(Function1 function1, Throwable th, Object obj, CoroutineContext coroutineContext) {
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public void resume(T t, Function1<? super Throwable, Unit> function1) {
        resumeImpl$kotlinx_coroutines_core(t, this.resumeMode, function1 != null ? new CancellableContinuationImpl$$ExternalSyntheticLambda0(function1) : null);
    }

    public <R extends T> void resume(R r, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3) {
        resumeImpl$kotlinx_coroutines_core(r, this.resumeMode, function3);
    }

    public void invokeOnCancellation(Segment<?> segment, int i) {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$volatile$FU;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, ((i2 >> 29) << 29) + i));
        invokeOnCancellationImpl(segment);
    }

    public void invokeOnCancellation(Function1<? super Throwable, Unit> function1) {
        CancellableContinuationKt.invokeOnCancellation(this, new CancelHandler.UserSupplied(function1));
    }

    public final void invokeOnCancellationInternal$kotlinx_coroutines_core(CancelHandler cancelHandler) {
        invokeOnCancellationImpl(cancelHandler);
    }

    private final void invokeOnCancellationImpl(Object obj) {
        Object obj2 = obj;
        if (!DebugKt.getASSERTIONS_ENABLED() || (obj2 instanceof CancelHandler) || (obj2 instanceof Segment)) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$volatile$FU;
            while (true) {
                Object obj3 = atomicReferenceFieldUpdater.get(this);
                if (obj3 instanceof Active) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj3, obj2)) {
                        return;
                    }
                } else if ((obj3 instanceof CancelHandler) || (obj3 instanceof Segment)) {
                    multipleHandlersError(obj2, obj3);
                } else {
                    boolean z = obj3 instanceof CompletedExceptionally;
                    if (z) {
                        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj3;
                        if (!completedExceptionally.makeHandled()) {
                            multipleHandlersError(obj2, obj3);
                        }
                        if (obj3 instanceof CancelledContinuation) {
                            Throwable th = null;
                            if (!z) {
                                completedExceptionally = null;
                            }
                            if (completedExceptionally != null) {
                                th = completedExceptionally.cause;
                            }
                            if (obj2 instanceof CancelHandler) {
                                callCancelHandler((CancelHandler) obj2, th);
                                return;
                            }
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                            callSegmentOnCancellation((Segment) obj2, th);
                            return;
                        }
                        return;
                    } else if (obj3 instanceof CompletedContinuation) {
                        CompletedContinuation completedContinuation = (CompletedContinuation) obj3;
                        if (completedContinuation.cancelHandler != null) {
                            multipleHandlersError(obj2, obj3);
                        }
                        if (!(obj2 instanceof Segment)) {
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                            CancelHandler cancelHandler = (CancelHandler) obj2;
                            if (completedContinuation.getCancelled()) {
                                callCancelHandler(cancelHandler, completedContinuation.cancelCause);
                                return;
                            }
                            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj3, CompletedContinuation.copy$default(completedContinuation, (Object) null, cancelHandler, (Function3) null, (Object) null, (Throwable) null, 29, (Object) null))) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else if (!(obj2 instanceof Segment)) {
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj3, new CompletedContinuation(obj3, (CancelHandler) obj2, (Function3) null, (Object) null, (Throwable) null, 28, (DefaultConstructorMarker) null))) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    private final void multipleHandlersError(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    private final void dispatchResume(int i) {
        if (!tryResume()) {
            DispatchedTaskKt.dispatch(this, i);
        }
    }

    private final <R> Object resumedState(NotCompleted notCompleted, R r, int i, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, Object obj) {
        if (r instanceof CompletedExceptionally) {
            if (DebugKt.getASSERTIONS_ENABLED() && obj != null) {
                throw new AssertionError();
            } else if (!DebugKt.getASSERTIONS_ENABLED() || function3 == null) {
                return r;
            } else {
                throw new AssertionError();
            }
        } else if (!DispatchedTaskKt.isCancellableMode(i) && obj == null) {
            return r;
        } else {
            if (function3 == null && !(notCompleted instanceof CancelHandler) && obj == null) {
                return r;
            }
            return new CompletedContinuation(r, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function3, obj, (Throwable) null, 16, (DefaultConstructorMarker) null);
        }
    }

    public static /* synthetic */ void resumeImpl$kotlinx_coroutines_core$default(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i, Function3 function3, int i2, Object obj2) {
        if (obj2 == null) {
            if ((i2 & 4) != 0) {
                function3 = null;
            }
            cancellableContinuationImpl.resumeImpl$kotlinx_coroutines_core(obj, i, function3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
    }

    public final <R> void resumeImpl$kotlinx_coroutines_core(R r, int i, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3) {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$volatile$FU;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof NotCompleted) {
            } else {
                if (obj instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj;
                    if (cancelledContinuation.makeResumed()) {
                        if (function3 != null) {
                            callOnCancellation(function3, cancelledContinuation.cause, r);
                            return;
                        }
                        return;
                    }
                }
                alreadyResumedError(r);
                throw new KotlinNothingValueException();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj, resumedState((NotCompleted) obj, r, i, function3, (Object) null)));
        detachChildIfNonResuable();
        dispatchResume(i);
    }

    private final <R> Symbol tryResumeImpl(R r, Object obj, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$volatile$FU;
        do {
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
            } else if (!(obj2 instanceof CompletedContinuation) || obj == null) {
                return null;
            } else {
                CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                if (completedContinuation.idempotentResume != obj) {
                    return null;
                }
                if (!DebugKt.getASSERTIONS_ENABLED() || Intrinsics.areEqual((Object) completedContinuation.result, (Object) r)) {
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
                throw new AssertionError();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj2, resumedState((NotCompleted) obj2, r, this.resumeMode, function3, obj)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    private final Void alreadyResumedError(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$kotlinx_coroutines_core();
        }
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
            _parentHandle$volatile$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    public Object tryResume(T t, Object obj) {
        return tryResumeImpl(t, obj, (Function3) null);
    }

    public <R extends T> Object tryResume(R r, Object obj, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3) {
        return tryResumeImpl(r, obj, function3);
    }

    public Object tryResumeWithException(Throwable th) {
        return tryResumeImpl(new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null), (Object) null, (Function3) null);
    }

    public void completeResume(Object obj) {
        if (!DebugKt.getASSERTIONS_ENABLED() || obj == CancellableContinuationImplKt.RESUME_TOKEN) {
            dispatchResume(this.resumeMode);
            return;
        }
        throw new AssertionError();
    }

    public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl$kotlinx_coroutines_core$default(this, t, coroutineDispatcher2 == coroutineDispatcher ? 4 : this.resumeMode, (Function3) null, 4, (Object) null);
    }

    public void resumeUndispatchedWithException(CoroutineDispatcher coroutineDispatcher, Throwable th) {
        Continuation<T> continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        CompletedExceptionally completedExceptionally = new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null);
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl$kotlinx_coroutines_core$default(this, completedExceptionally, coroutineDispatcher2 == coroutineDispatcher ? 4 : this.resumeMode, (Function3) null, 4, (Object) null);
    }

    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj instanceof CompletedContinuation ? ((CompletedContinuation) obj).result : obj;
    }

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core == null) {
            return null;
        }
        Continuation<T> continuation = this.delegate;
        return (!DebugKt.getRECOVER_STACK_TRACES() || !(continuation instanceof CoroutineStackFrame)) ? exceptionalResult$kotlinx_coroutines_core : StackTraceRecoveryKt.recoverFromStackFrame(exceptionalResult$kotlinx_coroutines_core, (CoroutineStackFrame) continuation);
    }

    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + getStateDebugRepresentation() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    /* access modifiers changed from: protected */
    public String nameString() {
        return "CancellableContinuation";
    }
}
