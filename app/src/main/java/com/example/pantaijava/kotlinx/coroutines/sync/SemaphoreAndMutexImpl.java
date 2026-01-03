package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001a\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u001eH\u0005Jb\u0010\u001a\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u001f2\u0006\u0010\u001d\u001a\u0002H\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00190!2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00160!H\b¢\u0006\u0002\u0010%J\u001e\u0010&\u001a\u00020\u00162\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0004J\b\u0010*\u001a\u00020\u0003H\u0002J\u0006\u0010+\u001a\u00020\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0019H\u0002J\f\u00100\u001a\u00020\u0019*\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004R\t\u0010\n\u001a\u00020\u000bX\u0004R\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004R\t\u0010\r\u001a\u00020\u000bX\u0004R\t\u0010\u000e\u001a\u00020\u000fX\u0004R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00160\u0014X\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreAndMutexImpl;", "", "permits", "", "acquiredPermits", "<init>", "(II)V", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "tail", "enqIdx", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "onCancellationRelease", "Lkotlin/Function3;", "", "", "Lkotlin/coroutines/CoroutineContext;", "tryAcquire", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "waiter", "Lkotlinx/coroutines/CancellableContinuation;", "W", "suspend", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onAcquired", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onAcquireRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "decPermits", "release", "coerceAvailablePermitsAtMaximum", "addAcquireToQueue", "Lkotlinx/coroutines/Waiter;", "tryResumeNextFromQueue", "tryResumeAcquire", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Semaphore.kt */
public class SemaphoreAndMutexImpl {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater deqIdx$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater enqIdx$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater head$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater tail$volatile$FU;
    private volatile /* synthetic */ int _availablePermits$volatile;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private final Function3<Throwable, Unit, CoroutineContext, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail$volatile;

    static {
        Class<SemaphoreAndMutexImpl> cls = SemaphoreAndMutexImpl.class;
        head$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "head$volatile");
        deqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(cls, "deqIdx$volatile");
        tail$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "tail$volatile");
        enqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(cls, "enqIdx$volatile");
        _availablePermits$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_availablePermits$volatile");
    }

    private final /* synthetic */ long getDeqIdx$volatile() {
        return this.deqIdx$volatile;
    }

    private final /* synthetic */ long getEnqIdx$volatile() {
        return this.enqIdx$volatile;
    }

    private final /* synthetic */ Object getHead$volatile() {
        return this.head$volatile;
    }

    private final /* synthetic */ Object getTail$volatile() {
        return this.tail$volatile;
    }

    private final /* synthetic */ int get_availablePermits$volatile() {
        return this._availablePermits$volatile;
    }

    private final /* synthetic */ void setDeqIdx$volatile(long j) {
        this.deqIdx$volatile = j;
    }

    private final /* synthetic */ void setEnqIdx$volatile(long j) {
        this.enqIdx$volatile = j;
    }

    private final /* synthetic */ void setHead$volatile(Object obj) {
        this.head$volatile = obj;
    }

    private final /* synthetic */ void setTail$volatile(Object obj) {
        this.tail$volatile = obj;
    }

    private final /* synthetic */ void set_availablePermits$volatile(int i) {
        this._availablePermits$volatile = i;
    }

    public SemaphoreAndMutexImpl(int i, int i2) {
        this.permits = i;
        if (i <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i).toString());
        } else if (i2 < 0 || i2 > i) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i).toString());
        } else {
            SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0, (SemaphoreSegment) null, 2);
            this.head$volatile = semaphoreSegment;
            this.tail$volatile = semaphoreSegment;
            this._availablePermits$volatile = i - i2;
            this.onCancellationRelease = new SemaphoreAndMutexImpl$$ExternalSyntheticLambda0(this);
        }
    }

    public final int getAvailablePermits() {
        return Math.max(_availablePermits$volatile$FU.get(this), 0);
    }

    /* access modifiers changed from: private */
    public static final Unit onCancellationRelease$lambda$2(SemaphoreAndMutexImpl semaphoreAndMutexImpl, Throwable th, Unit unit, CoroutineContext coroutineContext) {
        semaphoreAndMutexImpl.release();
        return Unit.INSTANCE;
    }

    public final boolean tryAcquire() {
        while (true) {
            int i = _availablePermits$volatile$FU.get(this);
            if (i > this.permits) {
                coerceAvailablePermitsAtMaximum();
            } else if (i <= 0) {
                return false;
            } else {
                if (_availablePermits$volatile$FU.compareAndSet(this, i, i - 1)) {
                    return true;
                }
            }
        }
    }

    public final Object acquire(Continuation<? super Unit> continuation) {
        if (decPermits() > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        return acquireSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? acquireSlowPath : Unit.INSTANCE;
    }

    private final <W> void acquire(W w, Function1<? super W, Boolean> function1, Function1<? super W, Unit> function12) {
        while (decPermits() <= 0) {
            if (function1.invoke(w).booleanValue()) {
                return;
            }
        }
        function12.invoke(w);
    }

    private final int decPermits() {
        int andDecrement;
        do {
            andDecrement = _availablePermits$volatile$FU.getAndDecrement(this);
        } while (andDecrement > this.permits);
        return andDecrement;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void release() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$volatile$FU
            int r0 = r0.getAndIncrement(r3)
            int r1 = r3.permits
            if (r0 >= r1) goto L_0x0016
            if (r0 < 0) goto L_0x000f
            return
        L_0x000f:
            boolean r0 = r3.tryResumeNextFromQueue()
            if (r0 == 0) goto L_0x0000
            return
        L_0x0016:
            r3.coerceAvailablePermitsAtMaximum()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "The number of released permits cannot be greater than "
            r1.<init>(r2)
            int r2 = r3.permits
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.release():void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private final void coerceAvailablePermitsAtMaximum() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$volatile$FU
            int r0 = r0.get(r3)
            int r1 = r3.permits
            if (r0 <= r1) goto L_0x0018
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = _availablePermits$volatile$FU
            int r2 = r3.permits
            boolean r0 = r1.compareAndSet(r3, r0, r2)
            if (r0 == 0) goto L_0x0000
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.coerceAvailablePermitsAtMaximum():void");
    }

    /* access modifiers changed from: private */
    public final boolean addAcquireToQueue(Waiter waiter) {
        Object findSegmentInternal;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) tail$volatile$FU.get(this);
        long andIncrement = enqIdx$volatile$FU.getAndIncrement(this);
        KFunction kFunction = SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater tail$volatile$FU2 = tail$volatile$FU;
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, access$getSEGMENT_SIZE$p, (Function2) kFunction);
            if (SegmentOrClosed.m1785isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r8 = SegmentOrClosed.m1783getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) tail$volatile$FU2.get(this);
                if (segment.id >= r8.id) {
                    break loop0;
                } else if (r8.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(tail$volatile$FU2, this, segment, r8)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r8.decPointers$kotlinx_coroutines_core()) {
                        r8.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1783getSegmentimpl(findSegmentInternal);
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, (Object) null, waiter)) {
            waiter.invokeOnCancellation(semaphoreSegment2, access$getSEGMENT_SIZE$p2);
            return true;
        }
        if (ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.TAKEN)) {
            if (waiter instanceof CancellableContinuation) {
                Intrinsics.checkNotNull(waiter, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
                ((CancellableContinuation) waiter).resume(Unit.INSTANCE, this.onCancellationRelease);
            } else if (waiter instanceof SelectInstance) {
                ((SelectInstance) waiter).selectInRegistrationPhase(Unit.INSTANCE);
            } else {
                throw new IllegalStateException(("unexpected: " + waiter).toString());
            }
            return true;
        } else if (!DebugKt.getASSERTIONS_ENABLED() || semaphoreSegment2.getAcquirers().get(access$getSEGMENT_SIZE$p2) == SemaphoreKt.BROKEN) {
            return false;
        } else {
            throw new AssertionError();
        }
    }

    private final boolean tryResumeNextFromQueue() {
        Object findSegmentInternal;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) head$volatile$FU.get(this);
        long andIncrement = deqIdx$volatile$FU.getAndIncrement(this);
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        KFunction kFunction = SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater head$volatile$FU2 = head$volatile$FU;
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, access$getSEGMENT_SIZE$p, (Function2) kFunction);
            if (SegmentOrClosed.m1785isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r8 = SegmentOrClosed.m1783getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) head$volatile$FU2.get(this);
                if (segment.id >= r8.id) {
                    break loop0;
                } else if (r8.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(head$volatile$FU2, this, segment, r8)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r8.decPointers$kotlinx_coroutines_core()) {
                        r8.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1783getSegmentimpl(findSegmentInternal);
        semaphoreSegment2.cleanPrev();
        if (semaphoreSegment2.id > access$getSEGMENT_SIZE$p) {
            return false;
        }
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object andSet = semaphoreSegment2.getAcquirers().getAndSet(access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT);
        if (andSet == null) {
            int access$getMAX_SPIN_CYCLES$p = SemaphoreKt.MAX_SPIN_CYCLES;
            for (int i = 0; i < access$getMAX_SPIN_CYCLES$p; i++) {
                if (semaphoreSegment2.getAcquirers().get(access$getSEGMENT_SIZE$p2) == SemaphoreKt.TAKEN) {
                    return true;
                }
            }
            return !ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
        } else if (andSet == SemaphoreKt.CANCELLED) {
            return false;
        } else {
            return tryResumeAcquire(andSet);
        }
    }

    private final boolean tryResumeAcquire(Object obj) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, (Object) null, this.onCancellationRelease);
            if (tryResume == null) {
                return false;
            }
            cancellableContinuation.completeResume(tryResume);
            return true;
        } else if (obj instanceof SelectInstance) {
            return ((SelectInstance) obj).trySelect(this, Unit.INSTANCE);
        } else {
            throw new IllegalStateException(("unexpected: " + obj).toString());
        }
    }

    /* access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl<? super Unit> orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        try {
            if (!addAcquireToQueue(orCreateCancellableContinuation)) {
                acquire((CancellableContinuation<? super Unit>) orCreateCancellableContinuation);
            }
            Object result = orCreateCancellableContinuation.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        } catch (Throwable th) {
            orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final void acquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        while (decPermits() <= 0) {
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) cancellableContinuation)) {
                return;
            }
        }
        cancellableContinuation.resume(Unit.INSTANCE, (Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit>) this.onCancellationRelease);
    }

    /* access modifiers changed from: protected */
    public final void onAcquireRegFunction(SelectInstance<?> selectInstance, Object obj) {
        while (decPermits() <= 0) {
            Intrinsics.checkNotNull(selectInstance, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) selectInstance)) {
                return;
            }
        }
        selectInstance.selectInRegistrationPhase(Unit.INSTANCE);
    }
}
