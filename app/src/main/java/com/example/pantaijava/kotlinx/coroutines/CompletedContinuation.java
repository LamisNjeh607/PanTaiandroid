package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0001\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012O\b\u0002\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0019\u001a\u00020\u000f2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003JP\u0010\u001f\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0001\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052O\b\u0002\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001R\u0012\u0010\u0003\u001a\u00028\u00008\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000RW\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "R", "", "result", "cancelHandler", "Lkotlinx/coroutines/CancelHandler;", "onCancellation", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "cause", "value", "Lkotlin/coroutines/CoroutineContext;", "context", "", "idempotentResume", "cancelCause", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)V", "Ljava/lang/Object;", "cancelled", "", "getCancelled", "()Z", "invokeHandlers", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "component1", "()Ljava/lang/Object;", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)Lkotlinx/coroutines/CompletedContinuation;", "equals", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: CancellableContinuationImpl.kt */
final class CompletedContinuation<R> {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function3<Throwable, R, CoroutineContext, Unit> onCancellation;
    public final R result;

    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation completedContinuation, R r, CancelHandler cancelHandler2, Function3<Throwable, R, CoroutineContext, Unit> function3, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 1) != 0) {
            r = completedContinuation.result;
        }
        if ((i & 2) != 0) {
            cancelHandler2 = completedContinuation.cancelHandler;
        }
        CancelHandler cancelHandler3 = cancelHandler2;
        if ((i & 4) != 0) {
            function3 = completedContinuation.onCancellation;
        }
        Function3<Throwable, R, CoroutineContext, Unit> function32 = function3;
        if ((i & 8) != 0) {
            obj = completedContinuation.idempotentResume;
        }
        Object obj3 = obj;
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return completedContinuation.copy(r, cancelHandler3, function32, obj3, th);
    }

    public final R component1() {
        return this.result;
    }

    public final CancelHandler component2() {
        return this.cancelHandler;
    }

    public final Function3<Throwable, R, CoroutineContext, Unit> component3() {
        return this.onCancellation;
    }

    public final Object component4() {
        return this.idempotentResume;
    }

    public final Throwable component5() {
        return this.cancelCause;
    }

    public final CompletedContinuation<R> copy(R r, CancelHandler cancelHandler2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, Object obj, Throwable th) {
        return new CompletedContinuation(r, cancelHandler2, function3, obj, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return Intrinsics.areEqual((Object) this.result, (Object) completedContinuation.result) && Intrinsics.areEqual((Object) this.cancelHandler, (Object) completedContinuation.cancelHandler) && Intrinsics.areEqual((Object) this.onCancellation, (Object) completedContinuation.onCancellation) && Intrinsics.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && Intrinsics.areEqual((Object) this.cancelCause, (Object) completedContinuation.cancelCause);
    }

    public int hashCode() {
        R r = this.result;
        int i = 0;
        int hashCode = (r == null ? 0 : r.hashCode()) * 31;
        CancelHandler cancelHandler2 = this.cancelHandler;
        int hashCode2 = (hashCode + (cancelHandler2 == null ? 0 : cancelHandler2.hashCode())) * 31;
        Function3<Throwable, R, CoroutineContext, Unit> function3 = this.onCancellation;
        int hashCode3 = (hashCode2 + (function3 == null ? 0 : function3.hashCode())) * 31;
        Object obj = this.idempotentResume;
        int hashCode4 = (hashCode3 + (obj == null ? 0 : obj.hashCode())) * 31;
        Throwable th = this.cancelCause;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }

    public CompletedContinuation(R r, CancelHandler cancelHandler2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, Object obj, Throwable th) {
        this.result = r;
        this.cancelHandler = cancelHandler2;
        this.onCancellation = function3;
        this.idempotentResume = obj;
        this.cancelCause = th;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler2, Function3 function3, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? null : cancelHandler2, (i & 4) != 0 ? null : function3, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    public final void invokeHandlers(CancellableContinuationImpl<?> cancellableContinuationImpl, Throwable th) {
        CancelHandler cancelHandler2 = this.cancelHandler;
        if (cancelHandler2 != null) {
            cancellableContinuationImpl.callCancelHandler(cancelHandler2, th);
        }
        Function3<Throwable, R, CoroutineContext, Unit> function3 = this.onCancellation;
        if (function3 != null) {
            cancellableContinuationImpl.callOnCancellation(function3, th, this.result);
        }
    }
}
