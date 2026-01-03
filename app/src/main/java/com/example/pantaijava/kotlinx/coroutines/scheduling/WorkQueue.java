package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002J'\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0005j\u0002`\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001e¢\u0006\u0002\u0010\u001fJ\u001b\u0010 \u001a\u0004\u0018\u00010\f2\n\u0010\u001b\u001a\u00060\u0005j\u0002`\u001cH\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u0004\u0018\u00010\fJ\b\u0010#\u001a\u0004\u0018\u00010\fJ\u0012\u0010$\u001a\u0004\u0018\u00010\f2\u0006\u0010%\u001a\u00020\u0017H\u0002J\u001a\u0010&\u001a\u0004\u0018\u00010\f2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0017H\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+J)\u0010,\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0005j\u0002`\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020+H\u0002J\n\u0010/\u001a\u0004\u0018\u00010\fH\u0002J\u000e\u00100\u001a\u00020)*\u0004\u0018\u00010\fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eX\u0004R\t\u0010\u000f\u001a\u00020\u0010X\u0004R\t\u0010\u0011\u001a\u00020\u0010X\u0004R\t\u0010\u0012\u001a\u00020\u0010X\u0004¨\u00061"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "<init>", "()V", "bufferSize", "", "getBufferSize", "()I", "size", "getSize$kotlinx_coroutines_core", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "Lkotlinx/atomicfu/AtomicInt;", "consumerIndex", "blockingTasksInBuffer", "poll", "add", "task", "fair", "", "addLast", "trySteal", "", "stealingMode", "Lkotlinx/coroutines/scheduling/StealingMode;", "stolenTaskRef", "Lkotlin/jvm/internal/Ref$ObjectRef;", "(ILkotlin/jvm/internal/Ref$ObjectRef;)J", "stealWithExclusiveMode", "(I)Lkotlinx/coroutines/scheduling/Task;", "pollBlocking", "pollCpu", "pollWithExclusiveMode", "onlyBlocking", "tryExtractFromTheMiddle", "index", "offloadAllWorkTo", "", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "tryStealLastScheduled", "pollTo", "queue", "pollBuffer", "decrementIfBlocking", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: WorkQueue.kt */
public final class WorkQueue {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater blockingTasksInBuffer$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater consumerIndex$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater lastScheduledTask$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater producerIndex$volatile$FU;
    private volatile /* synthetic */ int blockingTasksInBuffer$volatile;
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    private volatile /* synthetic */ int consumerIndex$volatile;
    private volatile /* synthetic */ Object lastScheduledTask$volatile;
    private volatile /* synthetic */ int producerIndex$volatile;

    static {
        Class<WorkQueue> cls = WorkQueue.class;
        lastScheduledTask$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "lastScheduledTask$volatile");
        producerIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "producerIndex$volatile");
        consumerIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "consumerIndex$volatile");
        blockingTasksInBuffer$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "blockingTasksInBuffer$volatile");
    }

    private final /* synthetic */ int getBlockingTasksInBuffer$volatile() {
        return this.blockingTasksInBuffer$volatile;
    }

    private final /* synthetic */ int getConsumerIndex$volatile() {
        return this.consumerIndex$volatile;
    }

    private final /* synthetic */ Object getLastScheduledTask$volatile() {
        return this.lastScheduledTask$volatile;
    }

    private final /* synthetic */ int getProducerIndex$volatile() {
        return this.producerIndex$volatile;
    }

    private final /* synthetic */ void setBlockingTasksInBuffer$volatile(int i) {
        this.blockingTasksInBuffer$volatile = i;
    }

    private final /* synthetic */ void setConsumerIndex$volatile(int i) {
        this.consumerIndex$volatile = i;
    }

    private final /* synthetic */ void setLastScheduledTask$volatile(Object obj) {
        this.lastScheduledTask$volatile = obj;
    }

    private final /* synthetic */ void setProducerIndex$volatile(int i) {
        this.producerIndex$volatile = i;
    }

    private final int getBufferSize() {
        return producerIndex$volatile$FU.get(this) - consumerIndex$volatile$FU.get(this);
    }

    public final int getSize$kotlinx_coroutines_core() {
        return lastScheduledTask$volatile$FU.get(this) != null ? getBufferSize() + 1 : getBufferSize();
    }

    public final Task poll() {
        Task task = (Task) lastScheduledTask$volatile$FU.getAndSet(this, (Object) null);
        return task == null ? pollBuffer() : task;
    }

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    public final Task add(Task task, boolean z) {
        if (z) {
            return addLast(task);
        }
        Task task2 = (Task) lastScheduledTask$volatile$FU.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return addLast(task2);
    }

    private final Task addLast(Task task) {
        if (getBufferSize() == 127) {
            return task;
        }
        if (task.taskContext) {
            blockingTasksInBuffer$volatile$FU.incrementAndGet(this);
        }
        int i = producerIndex$volatile$FU.get(this) & WorkQueueKt.MASK;
        while (this.buffer.get(i) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(i, task);
        producerIndex$volatile$FU.incrementAndGet(this);
        return null;
    }

    public final long trySteal(int i, Ref.ObjectRef<Task> objectRef) {
        T t;
        if (i == 3) {
            t = pollBuffer();
        } else {
            t = stealWithExclusiveMode(i);
        }
        if (t == null) {
            return tryStealLastScheduled(i, objectRef);
        }
        objectRef.element = t;
        return -1;
    }

    private final Task stealWithExclusiveMode(int i) {
        int i2 = consumerIndex$volatile$FU.get(this);
        int i3 = producerIndex$volatile$FU.get(this);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        while (i2 != i3) {
            if (z && blockingTasksInBuffer$volatile$FU.get(this) == 0) {
                return null;
            }
            int i4 = i2 + 1;
            Task tryExtractFromTheMiddle = tryExtractFromTheMiddle(i2, z);
            if (tryExtractFromTheMiddle != null) {
                return tryExtractFromTheMiddle;
            }
            i2 = i4;
        }
        return null;
    }

    public final Task pollBlocking() {
        return pollWithExclusiveMode(true);
    }

    public final Task pollCpu() {
        return pollWithExclusiveMode(false);
    }

    private final Task pollWithExclusiveMode(boolean z) {
        Task task;
        do {
            task = (Task) lastScheduledTask$volatile$FU.get(this);
            if (task == null || task.taskContext != z) {
                int i = consumerIndex$volatile$FU.get(this);
                int i2 = producerIndex$volatile$FU.get(this);
                while (i != i2) {
                    if (z && blockingTasksInBuffer$volatile$FU.get(this) == 0) {
                        return null;
                    }
                    i2--;
                    Task tryExtractFromTheMiddle = tryExtractFromTheMiddle(i2, z);
                    if (tryExtractFromTheMiddle != null) {
                        return tryExtractFromTheMiddle;
                    }
                }
                return null;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(lastScheduledTask$volatile$FU, this, task, (Object) null));
        return task;
    }

    private final Task tryExtractFromTheMiddle(int i, boolean z) {
        int i2 = i & WorkQueueKt.MASK;
        Task task = this.buffer.get(i2);
        if (task == null || task.taskContext != z || !ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(this.buffer, i2, task, (Object) null)) {
            return null;
        }
        if (z) {
            blockingTasksInBuffer$volatile$FU.decrementAndGet(this);
        }
        return task;
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        Task task = (Task) lastScheduledTask$volatile$FU.getAndSet(this, (Object) null);
        if (task != null) {
            globalQueue.addLast(task);
        }
        do {
        } while (pollTo(globalQueue));
    }

    private final long tryStealLastScheduled(int i, Ref.ObjectRef<Task> objectRef) {
        T t;
        do {
            t = (Task) lastScheduledTask$volatile$FU.get(this);
            if (t == null) {
                return -2;
            }
            if (((t.taskContext ? 1 : 2) & i) == 0) {
                return -2;
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - t.submissionTime;
            if (nanoTime < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
                return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - nanoTime;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(lastScheduledTask$volatile$FU, this, t, (Object) null));
        objectRef.element = t;
        return -1;
    }

    private final boolean pollTo(GlobalQueue globalQueue) {
        Task pollBuffer = pollBuffer();
        if (pollBuffer == null) {
            return false;
        }
        globalQueue.addLast(pollBuffer);
        return true;
    }

    private final Task pollBuffer() {
        Task andSet;
        while (true) {
            int i = consumerIndex$volatile$FU.get(this);
            if (i - producerIndex$volatile$FU.get(this) == 0) {
                return null;
            }
            int i2 = i & WorkQueueKt.MASK;
            if (consumerIndex$volatile$FU.compareAndSet(this, i, i + 1) && (andSet = this.buffer.getAndSet(i2, (Object) null)) != null) {
                decrementIfBlocking(andSet);
                return andSet;
            }
        }
    }

    private final void decrementIfBlocking(Task task) {
        if (task != null && task.taskContext) {
            int decrementAndGet = blockingTasksInBuffer$volatile$FU.decrementAndGet(this);
            if (DebugKt.getASSERTIONS_ENABLED() && decrementAndGet < 0) {
                throw new AssertionError();
            }
        }
    }
}
