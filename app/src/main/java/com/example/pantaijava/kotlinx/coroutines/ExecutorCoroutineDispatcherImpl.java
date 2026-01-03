package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Delay;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000fj\u0002`\u000eH\u0016¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016J)\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\r\u001a\u00060\u000fj\u0002`\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0018J3\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a*\u00020\u001b2\n\u0010\r\u001a\u00060\u000fj\u0002`\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\nH\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\b\u0010'\u001a\u00020(H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006)"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherImpl;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "executor", "Ljava/util/concurrent/Executor;", "<init>", "(Ljava/util/concurrent/Executor;)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlinx/coroutines/Runnable;", "Ljava/lang/Runnable;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "scheduleResumeAfterDelay", "timeMillis", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "(JLjava/lang/Runnable;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/DisposableHandle;", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "Ljava/util/concurrent/ScheduledExecutorService;", "(Ljava/util/concurrent/ScheduledExecutorService;Ljava/lang/Runnable;Lkotlin/coroutines/CoroutineContext;J)Ljava/util/concurrent/ScheduledFuture;", "cancelJobOnRejection", "exception", "Ljava/util/concurrent/RejectedExecutionException;", "close", "toString", "", "equals", "", "other", "", "hashCode", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Executors.kt */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor executor2) {
        this.executor = executor2;
        if (getExecutor() instanceof ScheduledThreadPoolExecutor) {
            ((ScheduledThreadPoolExecutor) getExecutor()).setRemoveOnCancelPolicy(true);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long j, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable runnable2;
        try {
            Executor executor2 = getExecutor();
            AbstractTimeSource access$getTimeSource$p = AbstractTimeSourceKt.timeSource;
            if (access$getTimeSource$p == null || (runnable2 = access$getTimeSource$p.wrapTask(runnable)) == null) {
                runnable2 = runnable;
            }
            executor2.execute(runnable2);
        } catch (RejectedExecutionException e) {
            AbstractTimeSource access$getTimeSource$p2 = AbstractTimeSourceKt.timeSource;
            if (access$getTimeSource$p2 != null) {
                access$getTimeSource$p2.unTrackTask();
            }
            cancelJobOnRejection(coroutineContext, e);
            Dispatchers.getIO().dispatch(coroutineContext, runnable);
        }
    }

    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        Executor executor2 = getExecutor();
        ScheduledFuture<?> scheduledFuture = null;
        ScheduledExecutorService scheduledExecutorService = executor2 instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor2 : null;
        if (scheduledExecutorService != null) {
            scheduledFuture = scheduleBlock(scheduledExecutorService, new ResumeUndispatchedRunnable(this, cancellableContinuation), cancellableContinuation.getContext(), j);
        }
        if (scheduledFuture != null) {
            CancellableContinuationKt.invokeOnCancellation(cancellableContinuation, new CancelFutureOnCancel(scheduledFuture));
        } else {
            DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(j, cancellableContinuation);
        }
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        Executor executor2 = getExecutor();
        ScheduledFuture<?> scheduledFuture = null;
        ScheduledExecutorService scheduledExecutorService = executor2 instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor2 : null;
        if (scheduledExecutorService != null) {
            scheduledFuture = scheduleBlock(scheduledExecutorService, runnable, coroutineContext, j);
        }
        if (scheduledFuture != null) {
            return new DisposableFutureHandle(scheduledFuture);
        }
        return DefaultExecutor.INSTANCE.invokeOnTimeout(j, runnable, coroutineContext);
    }

    private final ScheduledFuture<?> scheduleBlock(ScheduledExecutorService scheduledExecutorService, Runnable runnable, CoroutineContext coroutineContext, long j) {
        try {
            return scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection(coroutineContext, e);
            return null;
        }
    }

    private final void cancelJobOnRejection(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        JobKt.cancel(coroutineContext, ExceptionsKt.CancellationException("The task was rejected", rejectedExecutionException));
    }

    public void close() {
        Executor executor2 = getExecutor();
        ExecutorService executorService = executor2 instanceof ExecutorService ? (ExecutorService) executor2 : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public String toString() {
        return getExecutor().toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).getExecutor() == getExecutor();
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }
}
