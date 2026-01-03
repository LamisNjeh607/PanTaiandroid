package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aO\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0000¢\u0006\u0002\u0010\t\u001aV\u0010\n\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\u0007\u001a\u0002H\u00022'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\rH\u0000¢\u0006\u0002\u0010\u000e\u001aV\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\u0007\u001a\u0002H\u00022'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\rH\u0000¢\u0006\u0002\u0010\u000e\u001a?\u0010\u0010\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0016H\b¨\u0006\u0017"}, d2 = {"startCoroutineUndispatched", "", "R", "T", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "receiver", "completion", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startUndispatchedOrReturn", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/internal/ScopeCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startUndispatchedOrReturnIgnoreTimeout", "undispatchedResult", "shouldThrow", "Lkotlin/Function1;", "", "", "startBlock", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: Undispatched.kt */
public final class UndispatchedKt {
    public static final <T, R> Object startUndispatchedOrReturn(ScopeCoroutine<? super T> scopeCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object obj;
        try {
            obj = !(function2 instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl(function2, r, scopeCoroutine) : ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, scopeCoroutine);
        } catch (Throwable th) {
            obj = new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null);
        }
        if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(obj);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (!(makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core;
        Throwable th2 = completedExceptionally.cause;
        Throwable th3 = completedExceptionally.cause;
        Continuation<T> continuation = scopeCoroutine.uCont;
        if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
            th3 = StackTraceRecoveryKt.recoverFromStackFrame(th3, (CoroutineStackFrame) continuation);
        }
        throw th3;
    }

    public static final <T, R> Object startUndispatchedOrReturnIgnoreTimeout(ScopeCoroutine<? super T> scopeCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object obj;
        try {
            obj = !(function2 instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl(function2, r, scopeCoroutine) : ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, scopeCoroutine);
        } catch (Throwable th) {
            obj = new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null);
        }
        if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(obj);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core;
            Throwable th2 = completedExceptionally.cause;
            if (!(th2 instanceof TimeoutCancellationException) || ((TimeoutCancellationException) th2).coroutine != scopeCoroutine) {
                Throwable th3 = completedExceptionally.cause;
                Continuation<T> continuation = scopeCoroutine.uCont;
                if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                    th3 = StackTraceRecoveryKt.recoverFromStackFrame(th3, (CoroutineStackFrame) continuation);
                }
                throw th3;
            } else if (obj instanceof CompletedExceptionally) {
                Throwable th4 = ((CompletedExceptionally) obj).cause;
                Continuation<T> continuation2 = scopeCoroutine.uCont;
                if (DebugKt.getRECOVER_STACK_TRACES() && (continuation2 instanceof CoroutineStackFrame)) {
                    th4 = StackTraceRecoveryKt.recoverFromStackFrame(th4, (CoroutineStackFrame) continuation2);
                }
                throw th4;
            }
        } else {
            obj = JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
        }
        return obj;
    }

    private static final <T> Object undispatchedResult(ScopeCoroutine<? super T> scopeCoroutine, Function1<? super Throwable, Boolean> function1, Function0<? extends Object> function0) {
        Object obj;
        try {
            obj = function0.invoke();
        } catch (Throwable th) {
            obj = new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null);
        }
        if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(obj);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (!(makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core;
        if (function1.invoke(completedExceptionally.cause).booleanValue()) {
            Throwable th2 = completedExceptionally.cause;
            Continuation<T> continuation = scopeCoroutine.uCont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                th2 = StackTraceRecoveryKt.recoverFromStackFrame(th2, (CoroutineStackFrame) continuation);
            }
            throw th2;
        } else if (!(obj instanceof CompletedExceptionally)) {
            return obj;
        } else {
            Throwable th3 = ((CompletedExceptionally) obj).cause;
            Continuation<T> continuation2 = scopeCoroutine.uCont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation2 instanceof CoroutineStackFrame)) {
                th3 = StackTraceRecoveryKt.recoverFromStackFrame(th3, (CoroutineStackFrame) continuation2);
            }
            throw th3;
        }
    }

    public static final <R, T> void startCoroutineUndispatched(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation) {
        CoroutineContext context;
        Object updateThreadContext;
        Continuation<? super T> probeCoroutineCreated = DebugProbesKt.probeCoroutineCreated(continuation);
        try {
            context = probeCoroutineCreated.getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, (Object) null);
            DebugProbesKt.probeCoroutineResumed(probeCoroutineCreated);
            Object wrapWithContinuationImpl = !(function2 instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl(function2, r, probeCoroutineCreated) : ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, probeCoroutineCreated);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            if (wrapWithContinuationImpl != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                Result.Companion companion = Result.Companion;
                probeCoroutineCreated.resumeWith(Result.m190constructorimpl(wrapWithContinuationImpl));
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            probeCoroutineCreated.resumeWith(Result.m190constructorimpl(ResultKt.createFailure(th)));
        }
    }
}
