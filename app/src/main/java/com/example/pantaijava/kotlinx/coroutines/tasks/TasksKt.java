package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a&\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a(\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002\u001a\u001e\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H@¢\u0006\u0002\u0010\t\u001a&\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H@¢\u0006\u0002\u0010\n\u001a(\u0010\u000b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"asTask", "Lcom/google/android/gms/tasks/Task;", "T", "Lkotlinx/coroutines/Deferred;", "asDeferred", "cancellationTokenSource", "Lcom/google/android/gms/tasks/CancellationTokenSource;", "asDeferredImpl", "await", "(Lcom/google/android/gms/tasks/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/google/android/gms/tasks/Task;Lcom/google/android/gms/tasks/CancellationTokenSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitImpl", "kotlinx-coroutines-play-services"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: Tasks.kt */
public final class TasksKt {
    public static final <T> Task<T> asTask(Deferred<? extends T> deferred) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        deferred.invokeOnCompletion(new TasksKt$$ExternalSyntheticLambda2(cancellationTokenSource, deferred, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public static final Unit asTask$lambda$0(CancellationTokenSource cancellationTokenSource, Deferred deferred, TaskCompletionSource taskCompletionSource, Throwable th) {
        if (th instanceof CancellationException) {
            cancellationTokenSource.cancel();
            return Unit.INSTANCE;
        }
        Throwable completionExceptionOrNull = deferred.getCompletionExceptionOrNull();
        if (completionExceptionOrNull == null) {
            taskCompletionSource.setResult(deferred.getCompleted());
        } else {
            Exception exc = completionExceptionOrNull instanceof Exception ? (Exception) completionExceptionOrNull : null;
            if (exc == null) {
                exc = new RuntimeExecutionException(completionExceptionOrNull);
            }
            taskCompletionSource.setException(exc);
        }
        return Unit.INSTANCE;
    }

    public static final <T> Deferred<T> asDeferred(Task<T> task) {
        return asDeferredImpl(task, (CancellationTokenSource) null);
    }

    public static final <T> Deferred<T> asDeferred(Task<T> task, CancellationTokenSource cancellationTokenSource) {
        return asDeferredImpl(task, cancellationTokenSource);
    }

    private static final <T> Deferred<T> asDeferredImpl(Task<T> task, CancellationTokenSource cancellationTokenSource) {
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        if (task.isComplete()) {
            Exception exception = task.getException();
            if (exception != null) {
                CompletableDeferred$default.completeExceptionally(exception);
            } else if (task.isCanceled()) {
                Job.DefaultImpls.cancel$default((Job) CompletableDeferred$default, (CancellationException) null, 1, (Object) null);
            } else {
                CompletableDeferred$default.complete(task.getResult());
            }
        } else {
            task.addOnCompleteListener((Executor) DirectExecutor.INSTANCE, (OnCompleteListener<T>) new TasksKt$$ExternalSyntheticLambda0(CompletableDeferred$default));
        }
        if (cancellationTokenSource != null) {
            CompletableDeferred$default.invokeOnCompletion(new TasksKt$$ExternalSyntheticLambda1(cancellationTokenSource));
        }
        return new TasksKt$asDeferredImpl$3(CompletableDeferred$default);
    }

    /* access modifiers changed from: private */
    public static final void asDeferredImpl$lambda$1(CompletableDeferred completableDeferred, Task task) {
        Exception exception = task.getException();
        if (exception != null) {
            completableDeferred.completeExceptionally(exception);
        } else if (task.isCanceled()) {
            Job.DefaultImpls.cancel$default((Job) completableDeferred, (CancellationException) null, 1, (Object) null);
        } else {
            completableDeferred.complete(task.getResult());
        }
    }

    /* access modifiers changed from: private */
    public static final Unit asDeferredImpl$lambda$2(CancellationTokenSource cancellationTokenSource, Throwable th) {
        cancellationTokenSource.cancel();
        return Unit.INSTANCE;
    }

    public static final <T> Object await(Task<T> task, Continuation<? super T> continuation) {
        return awaitImpl(task, (CancellationTokenSource) null, continuation);
    }

    public static final <T> Object await(Task<T> task, CancellationTokenSource cancellationTokenSource, Continuation<? super T> continuation) {
        return awaitImpl(task, cancellationTokenSource, continuation);
    }

    /* access modifiers changed from: private */
    public static final <T> Object awaitImpl(Task<T> task, CancellationTokenSource cancellationTokenSource, Continuation<? super T> continuation) {
        if (task.isComplete()) {
            Exception exception = task.getException();
            if (exception != null) {
                throw exception;
            } else if (!task.isCanceled()) {
                return task.getResult();
            } else {
                throw new CancellationException("Task " + task + " was cancelled normally.");
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            task.addOnCompleteListener((Executor) DirectExecutor.INSTANCE, (OnCompleteListener<T>) new TasksKt$awaitImpl$2$1(cancellableContinuation));
            if (cancellationTokenSource != null) {
                cancellableContinuation.invokeOnCancellation(new TasksKt$awaitImpl$2$2(cancellationTokenSource));
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }
}
