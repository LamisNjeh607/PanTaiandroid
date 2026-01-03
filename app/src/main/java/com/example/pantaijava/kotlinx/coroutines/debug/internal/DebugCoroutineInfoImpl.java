package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B%\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ)\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0015\u001a\u00020\u00162\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u0013\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0000¢\u0006\u0002\b.J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\"\u0010/\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u0012002\b\u0010\u001e\u001a\u0004\u0018\u00010&HP¢\u0006\u0002\u00101J\b\u00102\u001a\u00020\u0016H\u0016R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\rX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168@X\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\r8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R(\u0010(\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010&8@@@X\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00063"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "sequenceNumber", "", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/debug/internal/StackTraceFrame;J)V", "getCreationStackBottom$kotlinx_coroutines_core", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "_context", "Ljava/lang/ref/WeakReference;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "state", "", "getState$kotlinx_coroutines_core", "()Ljava/lang/String;", "_state", "unmatchedResume", "", "updateState", "", "frame", "Lkotlin/coroutines/Continuation;", "shouldBeMatched", "", "updateState$kotlinx_coroutines_core", "lastObservedThread", "Ljava/lang/Thread;", "_lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "value", "lastObservedFrame", "getLastObservedFrame$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "setLastObservedFrame$kotlinx_coroutines_core", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "lastObservedStackTrace", "lastObservedStackTrace$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: DebugCoroutineInfoImpl.kt */
public final class DebugCoroutineInfoImpl {
    private final WeakReference<CoroutineContext> _context;
    public volatile WeakReference<CoroutineStackFrame> _lastObservedFrame;
    public volatile String _state = DebugCoroutineInfoImplKt.CREATED;
    private final StackTraceFrame creationStackBottom;
    public volatile Thread lastObservedThread;
    public final long sequenceNumber;
    private int unmatchedResume;

    public DebugCoroutineInfoImpl(CoroutineContext coroutineContext, StackTraceFrame stackTraceFrame, long j) {
        this.creationStackBottom = stackTraceFrame;
        this.sequenceNumber = j;
        this._context = new WeakReference<>(coroutineContext);
    }

    public final StackTraceFrame getCreationStackBottom$kotlinx_coroutines_core() {
        return this.creationStackBottom;
    }

    public final CoroutineContext getContext() {
        return (CoroutineContext) this._context.get();
    }

    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    public final String getState$kotlinx_coroutines_core() {
        return this._state;
    }

    public final synchronized void updateState$kotlinx_coroutines_core(String str, Continuation<?> continuation, boolean z) {
        if (Intrinsics.areEqual((Object) this._state, (Object) DebugCoroutineInfoImplKt.RUNNING) && Intrinsics.areEqual((Object) str, (Object) DebugCoroutineInfoImplKt.RUNNING) && z) {
            this.unmatchedResume++;
        } else if (this.unmatchedResume > 0 && Intrinsics.areEqual((Object) str, (Object) DebugCoroutineInfoImplKt.SUSPENDED)) {
            this.unmatchedResume--;
            return;
        }
        if (!Intrinsics.areEqual((Object) this._state, (Object) str) || !Intrinsics.areEqual((Object) str, (Object) DebugCoroutineInfoImplKt.SUSPENDED) || getLastObservedFrame$kotlinx_coroutines_core() == null) {
            this._state = str;
            Thread thread = null;
            setLastObservedFrame$kotlinx_coroutines_core(continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null);
            if (Intrinsics.areEqual((Object) str, (Object) DebugCoroutineInfoImplKt.RUNNING)) {
                thread = Thread.currentThread();
            }
            this.lastObservedThread = thread;
        }
    }

    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        WeakReference<CoroutineStackFrame> weakReference = this._lastObservedFrame;
        if (weakReference != null) {
            return (CoroutineStackFrame) weakReference.get();
        }
        return null;
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(CoroutineStackFrame coroutineStackFrame) {
        this._lastObservedFrame = coroutineStackFrame != null ? new WeakReference<>(coroutineStackFrame) : null;
    }

    public final List<StackTraceElement> lastObservedStackTrace$kotlinx_coroutines_core() {
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (lastObservedFrame$kotlinx_coroutines_core != null) {
            StackTraceElement stackTraceElement = lastObservedFrame$kotlinx_coroutines_core.getStackTraceElement();
            if (stackTraceElement != null) {
                arrayList.add(stackTraceElement);
            }
            lastObservedFrame$kotlinx_coroutines_core = lastObservedFrame$kotlinx_coroutines_core.getCallerFrame();
        }
        return arrayList;
    }

    private final List<StackTraceElement> creationStackTrace() {
        StackTraceFrame stackTraceFrame = this.creationStackBottom;
        if (stackTraceFrame == null) {
            return CollectionsKt.emptyList();
        }
        return SequencesKt.toList(SequencesKt.sequence(new DebugCoroutineInfoImpl$creationStackTrace$1(this, stackTraceFrame, (Continuation<? super DebugCoroutineInfoImpl$creationStackTrace$1>) null)));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object yieldFrames(kotlin.sequences.SequenceScope<? super java.lang.StackTraceElement> r5, kotlin.coroutines.jvm.internal.CoroutineStackFrame r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl r5 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl) r5
            java.lang.Object r6 = r0.L$1
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r6 = (kotlin.coroutines.jvm.internal.CoroutineStackFrame) r6
            java.lang.Object r2 = r0.L$0
            kotlin.sequences.SequenceScope r2 = (kotlin.sequences.SequenceScope) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0060
        L_0x0036:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r4
        L_0x0043:
            if (r7 != 0) goto L_0x0048
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0048:
            java.lang.StackTraceElement r2 = r7.getStackTraceElement()
            if (r2 == 0) goto L_0x0063
            r0.L$0 = r5
            r0.L$1 = r7
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r2 = r5.yield(r2, r0)
            if (r2 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r2 = r5
            r5 = r6
            r6 = r7
        L_0x0060:
            r7 = r6
            r6 = r5
            r5 = r2
        L_0x0063:
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r7 = r7.getCallerFrame()
            if (r7 == 0) goto L_0x006a
            goto L_0x0043
        L_0x006a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames(kotlin.sequences.SequenceScope, kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "DebugCoroutineInfo(state=" + getState$kotlinx_coroutines_core() + ",context=" + getContext() + ')';
    }
}
