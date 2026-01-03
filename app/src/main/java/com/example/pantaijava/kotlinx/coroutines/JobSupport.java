package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause0Impl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\f\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\n¹\u0001º\u0001»\u0001¼\u0001½\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0004J\u001f\u0010\u001f\u001a\u00020 2\u0014\u0010!\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u001b0\"H\bJ\u001c\u0010'\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0002J \u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\u001c\u001a\u00020(2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0-H\u0002J\u001e\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0-H\u0002J\u001a\u00100\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u000eH\u0002J\u001a\u00103\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u000eH\u0002J\u0018\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020+H\u0002J\u0010\u00108\u001a\u00020\u00052\u0006\u00107\u001a\u00020+H\u0002J\u0016\u00109\u001a\u00020\u001b*\u0002062\b\u00107\u001a\u0004\u0018\u00010+H\u0002J/\u0010:\u001a\u00020\u001b2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010+2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00050\"H\bJ\u0006\u0010=\u001a\u00020\u0005J\u0012\u0010>\u001a\u00020?2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010@\u001a\u00020\u001bH\u0014J\u000f\u0010A\u001a\u00060Cj\u0002`B¢\u0006\u0002\u0010DJ!\u0010E\u001a\u00060Cj\u0002`B*\u00020+2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010GH\u0004¢\u0006\u0002\u0010HJ4\u0010N\u001a\u00020O2'\u0010P\u001a#\u0012\u0015\u0012\u0013\u0018\u00010+¢\u0006\f\bR\u0012\b\bS\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u001b0\"j\u0002`Q¢\u0006\u0002\u0010TJD\u0010N\u001a\u00020O2\u0006\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u00052'\u0010P\u001a#\u0012\u0015\u0012\u0013\u0018\u00010+¢\u0006\f\bR\u0012\b\bS\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u001b0\"j\u0002`Q¢\u0006\u0002\u0010WJ\u001d\u0010X\u001a\u00020O2\u0006\u0010V\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020<H\u0000¢\u0006\u0002\bZJ+\u0010[\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020<2\u0018\u0010\\\u001a\u0014\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00050]H\bJ\u0010\u0010^\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020_H\u0002J\u0010\u0010`\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020<H\u0002J\u000e\u0010a\u001a\u00020\u001bH@¢\u0006\u0002\u0010bJ\b\u0010c\u001a\u00020\u0005H\u0002J\u000e\u0010d\u001a\u00020\u001bH@¢\u0006\u0002\u0010bJ\u001e\u0010k\u001a\u00020\u001b2\n\u0010l\u001a\u0006\u0012\u0002\b\u00030m2\b\u0010n\u001a\u0004\u0018\u00010\u000eH\u0002J\u0015\u0010o\u001a\u00020\u001b2\u0006\u0010Y\u001a\u00020<H\u0000¢\u0006\u0002\bpJ\u001d\u0010s\u001a\u00020\u001b2\u000e\u00107\u001a\n\u0018\u00010Cj\u0004\u0018\u0001`BH\u0016¢\u0006\u0002\u0010tJ\b\u0010u\u001a\u00020GH\u0014J\u0012\u0010s\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010+H\u0017J\u0010\u0010v\u001a\u00020\u001b2\u0006\u00107\u001a\u00020+H\u0016J\u000e\u0010w\u001a\u00020\u001b2\u0006\u0010x\u001a\u00020\u0003J\u0010\u0010y\u001a\u00020\u00052\u0006\u00107\u001a\u00020+H\u0016J\u0010\u0010z\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010+J\u0017\u0010{\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0002\b|J\u0014\u0010}\u001a\u0004\u0018\u00010\u000e2\b\u00107\u001a\u0004\u0018\u00010\u000eH\u0002J'\u0010~\u001a\u000202\n\b\u0002\u0010F\u001a\u0004\u0018\u00010G2\n\b\u0002\u00107\u001a\u0004\u0018\u00010+H\b¢\u0006\u0003\b\u0001J\u0012\u0010\u0001\u001a\u00060Cj\u0002`BH\u0016¢\u0006\u0002\u0010DJ\u0013\u0010\u0001\u001a\u00020+2\b\u00107\u001a\u0004\u0018\u00010\u000eH\u0002J\u0015\u0010\u0001\u001a\u0004\u0018\u00010\u000e2\b\u00107\u001a\u0004\u0018\u00010\u000eH\u0002J\u0013\u0010\u0001\u001a\u0004\u0018\u0001062\u0006\u0010\u001c\u001a\u000201H\u0002J\u0019\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u0002012\u0006\u0010/\u001a\u00020+H\u0002J\u0019\u0010\u0001\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0003\b\u0001J\u001b\u0010\u0001\u001a\u0004\u0018\u00010\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0003\b\u0001J\u001f\u0010\u0001\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0002J\u001d\u0010\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u0002012\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0002J&\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020(2\b\u0010\u0001\u001a\u00030\u00012\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0010J%\u0010\u0001\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020(2\b\u0010\u0001\u001a\u00030\u00012\b\u0010)\u001a\u0004\u0018\u00010\u000eH\u0002J\u0011\u0010\u0001\u001a\u0005\u0018\u00010\u0001*\u00030\u0001H\u0002J\u0010\u0010\u0001\u001a\u00020\u00102\u0007\u0010\u0001\u001a\u00020\u0002J\u0018\u0010\u0001\u001a\u00020\u001b2\u0007\u0010\u0001\u001a\u00020+H\u0010¢\u0006\u0003\b\u0001J\u0012\u0010U\u001a\u00020\u001b2\b\u00107\u001a\u0004\u0018\u00010+H\u0014J\u0012\u0010¡\u0001\u001a\u00020\u00052\u0007\u0010\u0001\u001a\u00020+H\u0014J\u0013\u0010¢\u0001\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0014J\u0013\u0010£\u0001\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0014J\t\u0010¤\u0001\u001a\u00020GH\u0016J\t\u0010¥\u0001\u001a\u00020GH\u0007J\u000f\u0010¦\u0001\u001a\u00020GH\u0010¢\u0006\u0003\b§\u0001J\u0013\u0010¨\u0001\u001a\u00020G2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0002J\t\u0010¬\u0001\u001a\u0004\u0018\u00010+J\u0011\u0010­\u0001\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0003\b®\u0001J\u0011\u0010¯\u0001\u001a\u0004\u0018\u00010\u000eH@¢\u0006\u0002\u0010bJ\u0011\u0010°\u0001\u001a\u0004\u0018\u00010\u000eH@¢\u0006\u0002\u0010bJ\u001f\u0010¶\u0001\u001a\u00020\u001b2\n\u0010l\u001a\u0006\u0012\u0002\b\u00030m2\b\u0010n\u001a\u0004\u0018\u00010\u000eH\u0002J \u0010·\u0001\u001a\u0004\u0018\u00010\u000e2\b\u0010n\u001a\u0004\u0018\u00010\u000e2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u000eH\u0002R\u0015\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0004R\u0011\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0004R(\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00108@@@X\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u000e8@X\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010#\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b%\u0010$R\u0011\u0010&\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b&\u0010$R\u0016\u0010I\u001a\u0004\u0018\u00010+8DX\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020\u00058DX\u0004¢\u0006\u0006\u001a\u0004\bM\u0010$R\u0017\u0010e\u001a\u00020f8F¢\u0006\f\u0012\u0004\bg\u0010h\u001a\u0004\bi\u0010jR\u0014\u0010q\u001a\u00020\u00058PX\u0004¢\u0006\u0006\u001a\u0004\br\u0010$R\u001f\u0010\u0001\u001a\u0004\u0018\u00010+*\u0004\u0018\u00010\u000e8BX\u0004¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u001b\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010\u00018F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u0016\u0010\u0001\u001a\u00020\u00058TX\u0004¢\u0006\u0007\u001a\u0005\b\u0001\u0010$R\u0016\u0010\u0001\u001a\u00020\u00058PX\u0004¢\u0006\u0007\u001a\u0005\b \u0001\u0010$R\u001b\u0010©\u0001\u001a\u00020\u0005*\u0002018BX\u0004¢\u0006\b\u001a\u0006\b©\u0001\u0010ª\u0001R\u0013\u0010«\u0001\u001a\u00020\u00058F¢\u0006\u0007\u001a\u0005\b«\u0001\u0010$R#\u0010±\u0001\u001a\u0007\u0012\u0002\b\u00030²\u00018DX\u0004¢\u0006\u000f\u0012\u0005\b³\u0001\u0010h\u001a\u0006\b´\u0001\u0010µ\u0001¨\u0006¾\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "active", "", "<init>", "(Z)V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "_parentHandle", "Lkotlinx/coroutines/ChildHandle;", "value", "parentHandle", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parent", "getParent", "()Lkotlinx/coroutines/Job;", "initParentJob", "", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "loopOnState", "", "block", "Lkotlin/Function1;", "isActive", "()Z", "isCompleted", "isCancelled", "finalizeFinishingState", "Lkotlinx/coroutines/JobSupport$Finishing;", "proposedUpdate", "getFinalRootCause", "", "exceptions", "", "addSuppressedExceptions", "rootCause", "tryFinalizeSimpleState", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "notifyCancelling", "list", "Lkotlinx/coroutines/NodeList;", "cause", "cancelParent", "notifyCompletion", "notifyHandlers", "predicate", "Lkotlinx/coroutines/JobNode;", "start", "startInternal", "", "onStart", "getCancellationException", "Lkotlinx/coroutines/CancellationException;", "Ljava/util/concurrent/CancellationException;", "()Ljava/util/concurrent/CancellationException;", "toCancellationException", "message", "", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "completionCause", "getCompletionCause", "()Ljava/lang/Throwable;", "completionCauseHandled", "getCompletionCauseHandled", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlinx/coroutines/CompletionHandler;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletionInternal", "node", "invokeOnCompletionInternal$kotlinx_coroutines_core", "tryPutNodeIntoList", "tryAdd", "Lkotlin/Function2;", "promoteEmptyToNodeList", "Lkotlinx/coroutines/Empty;", "promoteSingleToNodeList", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinInternal", "joinSuspend", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin$annotations", "()V", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "registerSelectForOnJoin", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "removeNode", "removeNode$kotlinx_coroutines_core", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "cancellationExceptionMessage", "cancelInternal", "parentCancelled", "parentJob", "childCancelled", "cancelCoroutine", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "cancelMakeCompleting", "defaultCancellationException", "Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException$kotlinx_coroutines_core", "getChildJobCancellationCause", "createCauseException", "makeCancelling", "getOrPromoteCancellingList", "tryMakeCancelling", "makeCompleting", "makeCompleting$kotlinx_coroutines_core", "makeCompletingOnce", "makeCompletingOnce$kotlinx_coroutines_core", "tryMakeCompleting", "tryMakeCompletingSlowPath", "exceptionOrNull", "getExceptionOrNull", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "tryWaitForChild", "child", "Lkotlinx/coroutines/ChildHandleNode;", "continueCompleting", "lastChild", "nextChild", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "attachChild", "handleOnCompletionException", "exception", "handleOnCompletionException$kotlinx_coroutines_core", "isScopedCoroutine", "handlesException", "getHandlesException$kotlinx_coroutines_core", "handleJobException", "onCompletionInternal", "afterCompletion", "toString", "toDebugString", "nameString", "nameString$kotlinx_coroutines_core", "stateString", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "isCompletedExceptionally", "getCompletionExceptionOrNull", "getCompletedInternal", "getCompletedInternal$kotlinx_coroutines_core", "awaitInternal", "awaitSuspend", "onAwaitInternal", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwaitInternal$annotations", "getOnAwaitInternal", "()Lkotlinx/coroutines/selects/SelectClause1;", "onAwaitInternalRegFunc", "onAwaitInternalProcessResFunc", "result", "SelectOnJoinCompletionHandler", "Finishing", "ChildCompletion", "AwaitContinuation", "SelectOnAwaitCompletionHandler", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
/* compiled from: JobSupport.kt */
public class JobSupport implements Job, ChildJob, ParentJob {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _parentHandle$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$volatile$FU;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    static {
        Class<JobSupport> cls = JobSupport.class;
        _state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_state$volatile");
        _parentHandle$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_parentHandle$volatile");
    }

    protected static /* synthetic */ void getOnAwaitInternal$annotations() {
    }

    public static /* synthetic */ void getOnJoin$annotations() {
    }

    private final /* synthetic */ Object get_parentHandle$volatile() {
        return this._parentHandle$volatile;
    }

    private final /* synthetic */ Object get_state$volatile() {
        return this._state$volatile;
    }

    private final /* synthetic */ void set_parentHandle$volatile(Object obj) {
        this._parentHandle$volatile = obj;
    }

    private final /* synthetic */ void set_state$volatile(Object obj) {
        this._state$volatile = obj;
    }

    /* access modifiers changed from: protected */
    public void afterCompletion(Object obj) {
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean handleJobException(Throwable th) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isScopedCoroutine() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCancelling(Throwable th) {
    }

    /* access modifiers changed from: protected */
    public void onCompletionInternal(Object obj) {
    }

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return Job.DefaultImpls.fold(this, r, function2);
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return Job.DefaultImpls.get(this, key);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.minusKey(this, key);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return Job.DefaultImpls.plus((Job) this, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job job) {
        return Job.DefaultImpls.plus((Job) this, job);
    }

    public JobSupport(boolean z) {
        this._state$volatile = z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
    }

    public final CoroutineContext.Key<?> getKey() {
        return Job.Key;
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) _parentHandle$volatile$FU.get(this);
    }

    public final void setParentHandle$kotlinx_coroutines_core(ChildHandle childHandle) {
        _parentHandle$volatile$FU.set(this, childHandle);
    }

    public Job getParent() {
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            return parentHandle$kotlinx_coroutines_core.getParent();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void initParentJob(Job job) {
        if (DebugKt.getASSERTIONS_ENABLED() && getParentHandle$kotlinx_coroutines_core() != null) {
            throw new AssertionError();
        } else if (job == null) {
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        } else {
            job.start();
            ChildHandle attachChild = job.attachChild(this);
            setParentHandle$kotlinx_coroutines_core(attachChild);
            if (isCompleted()) {
                attachChild.dispose();
                setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
            }
        }
    }

    public final Object getState$kotlinx_coroutines_core() {
        return _state$volatile$FU.get(this);
    }

    private final Void loopOnState(Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(getState$kotlinx_coroutines_core());
        }
    }

    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive();
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) || ((state$kotlinx_coroutines_core instanceof Finishing) && ((Finishing) state$kotlinx_coroutines_core).isCancelling());
    }

    private final Object finalizeFinishingState(Finishing finishing, Object obj) {
        boolean isCancelling;
        Throwable finalRootCause;
        if (DebugKt.getASSERTIONS_ENABLED() && getState$kotlinx_coroutines_core() != finishing) {
            throw new AssertionError();
        } else if (DebugKt.getASSERTIONS_ENABLED() && !(!finishing.isSealed())) {
            throw new AssertionError();
        } else if (!DebugKt.getASSERTIONS_ENABLED() || finishing.isCompleting()) {
            CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
            Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
            synchronized (finishing) {
                isCancelling = finishing.isCancelling();
                List<Throwable> sealLocked = finishing.sealLocked(th);
                finalRootCause = getFinalRootCause(finishing, sealLocked);
                if (finalRootCause != null) {
                    addSuppressedExceptions(finalRootCause, sealLocked);
                }
            }
            if (!(finalRootCause == null || finalRootCause == th)) {
                obj = new CompletedExceptionally(finalRootCause, false, 2, (DefaultConstructorMarker) null);
            }
            if (finalRootCause != null && (cancelParent(finalRootCause) || handleJobException(finalRootCause))) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((CompletedExceptionally) obj).makeHandled();
            }
            if (!isCancelling) {
                onCancelling(finalRootCause);
            }
            onCompletionInternal(obj);
            boolean m = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, finishing, JobSupportKt.boxIncomplete(obj));
            if (!DebugKt.getASSERTIONS_ENABLED() || m) {
                completeStateFinalization(finishing, obj);
                return obj;
            }
            throw new AssertionError();
        } else {
            throw new AssertionError();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Throwable getFinalRootCause(kotlinx.coroutines.JobSupport.Finishing r5, java.util.List<? extends java.lang.Throwable> r6) {
        /*
            r4 = this;
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x001d
            boolean r5 = r5.isCancelling()
            if (r5 == 0) goto L_0x001c
            kotlinx.coroutines.JobCancellationException r5 = new kotlinx.coroutines.JobCancellationException
            java.lang.String r6 = r4.cancellationExceptionMessage()
            r0 = r4
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            r5.<init>(r6, r1, r0)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            return r5
        L_0x001c:
            return r1
        L_0x001d:
            r5 = r6
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r0 = r5.iterator()
        L_0x0024:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0038
            java.lang.Object r2 = r0.next()
            r3 = r2
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            boolean r3 = r3 instanceof java.util.concurrent.CancellationException
            r3 = r3 ^ 1
            if (r3 == 0) goto L_0x0024
            goto L_0x0039
        L_0x0038:
            r2 = r1
        L_0x0039:
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            if (r2 == 0) goto L_0x003e
            return r2
        L_0x003e:
            r0 = 0
            java.lang.Object r6 = r6.get(r0)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            boolean r0 = r6 instanceof kotlinx.coroutines.TimeoutCancellationException
            if (r0 == 0) goto L_0x0066
            java.util.Iterator r5 = r5.iterator()
        L_0x004d:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0061
            java.lang.Object r0 = r5.next()
            r2 = r0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            if (r2 == r6) goto L_0x004d
            boolean r2 = r2 instanceof kotlinx.coroutines.TimeoutCancellationException
            if (r2 == 0) goto L_0x004d
            r1 = r0
        L_0x0061:
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            if (r1 == 0) goto L_0x0066
            return r1
        L_0x0066:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.getFinalRootCause(kotlinx.coroutines.JobSupport$Finishing, java.util.List):java.lang.Throwable");
    }

    private final void addSuppressedExceptions(Throwable th, List<? extends Throwable> list) {
        if (list.size() > 1) {
            Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
            Throwable unwrapImpl = !DebugKt.getRECOVER_STACK_TRACES() ? th : StackTraceRecoveryKt.unwrapImpl(th);
            for (Throwable th2 : list) {
                if (DebugKt.getRECOVER_STACK_TRACES()) {
                    th2 = StackTraceRecoveryKt.unwrapImpl(th2);
                }
                if (th2 != th && th2 != unwrapImpl && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                    ExceptionsKt.addSuppressed(th, th2);
                }
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete incomplete, Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED() && !(incomplete instanceof Empty) && !(incomplete instanceof JobNode)) {
            throw new AssertionError();
        } else if (DebugKt.getASSERTIONS_ENABLED() && !(!(obj instanceof CompletedExceptionally))) {
            throw new AssertionError();
        } else if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, incomplete, JobSupportKt.boxIncomplete(obj))) {
            return false;
        } else {
            onCancelling((Throwable) null);
            onCompletionInternal(obj);
            completeStateFinalization(incomplete, obj);
            return true;
        }
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj) {
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            parentHandle$kotlinx_coroutines_core.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
        Throwable th = null;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
            }
        } else {
            NodeList list = incomplete.getList();
            if (list != null) {
                notifyCompletion(list, th);
            }
        }
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        onCancelling(th);
        nodeList.close(4);
        LockFreeLinkedListHead lockFreeLinkedListHead = nodeList;
        Object next = lockFreeLinkedListHead.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if ((lockFreeLinkedListNode instanceof JobNode) && ((JobNode) lockFreeLinkedListNode).getOnCancelling()) {
                try {
                    ((JobNode) lockFreeLinkedListNode).invoke(th);
                } catch (Throwable th2) {
                    Throwable th3 = completionHandlerException;
                    if (th3 != null) {
                        ExceptionsKt.addSuppressed(th3, th2);
                        if (th3 != null) {
                        }
                    }
                    completionHandlerException = new CompletionHandlerException("Exception in completion handler " + lockFreeLinkedListNode + " for " + this, th2);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable th4 = completionHandlerException;
        if (th4 != null) {
            handleOnCompletionException$kotlinx_coroutines_core(th4);
        }
        cancelParent(th);
    }

    private final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core == null || parentHandle$kotlinx_coroutines_core == NonDisposableHandle.INSTANCE) {
            return z;
        }
        if (parentHandle$kotlinx_coroutines_core.childCancelled(th) || z) {
            return true;
        }
        return false;
    }

    private final void notifyCompletion(NodeList nodeList, Throwable th) {
        nodeList.close(1);
        LockFreeLinkedListHead lockFreeLinkedListHead = nodeList;
        Object next = lockFreeLinkedListHead.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    ((JobNode) lockFreeLinkedListNode).invoke(th);
                } catch (Throwable th2) {
                    Throwable th3 = completionHandlerException;
                    if (th3 != null) {
                        ExceptionsKt.addSuppressed(th3, th2);
                        if (th3 != null) {
                        }
                    }
                    completionHandlerException = new CompletionHandlerException("Exception in completion handler " + lockFreeLinkedListNode + " for " + this, th2);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable th4 = completionHandlerException;
        if (th4 != null) {
            handleOnCompletionException$kotlinx_coroutines_core(th4);
        }
    }

    private final void notifyHandlers(NodeList nodeList, Throwable th, Function1<? super JobNode, Boolean> function1) {
        LockFreeLinkedListHead lockFreeLinkedListHead = nodeList;
        Object next = lockFreeLinkedListHead.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if ((lockFreeLinkedListNode instanceof JobNode) && function1.invoke(lockFreeLinkedListNode).booleanValue()) {
                try {
                    ((JobNode) lockFreeLinkedListNode).invoke(th);
                } catch (Throwable th2) {
                    Throwable th3 = completionHandlerException;
                    if (th3 != null) {
                        ExceptionsKt.addSuppressed(th3, th2);
                        if (th3 != null) {
                        }
                    }
                    completionHandlerException = new CompletionHandlerException("Exception in completion handler " + lockFreeLinkedListNode + " for " + this, th2);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable th4 = completionHandlerException;
        if (th4 != null) {
            handleOnCompletionException$kotlinx_coroutines_core(th4);
        }
    }

    private final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive()) {
                return 0;
            }
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStart();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, obj, ((InactiveNodeList) obj).getList())) {
                return -1;
            }
            onStart();
            return 1;
        }
    }

    public final CancellationException getCancellationException() {
        CancellationException cancellationException;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null && (cancellationException = toCancellationException(rootCause, DebugStringsKt.getClassSimpleName(this) + " is cancelling")) != null) {
                return cancellationException;
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return toCancellationException$default(this, ((CompletedExceptionally) state$kotlinx_coroutines_core).cause, (String) null, 1, (Object) null);
        } else {
            return new JobCancellationException(DebugStringsKt.getClassSimpleName(this) + " has completed normally", (Throwable) null, this);
        }
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            return jobSupport.toCancellationException(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    /* access modifiers changed from: protected */
    public final CancellationException toCancellationException(Throwable th, String str) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (str == null) {
            str = cancellationExceptionMessage();
        }
        return new JobCancellationException(str, th, this);
    }

    /* access modifiers changed from: protected */
    public final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null) {
                return rootCause;
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean getCompletionCauseHandled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) && ((CompletedExceptionally) state$kotlinx_coroutines_core).getHandled();
    }

    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> function1) {
        return invokeOnCompletionInternal$kotlinx_coroutines_core(true, new InvokeOnCompletion(function1));
    }

    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1) {
        JobNode jobNode;
        if (z) {
            jobNode = new InvokeOnCancelling(function1);
        } else {
            jobNode = new InvokeOnCompletion(function1);
        }
        return invokeOnCompletionInternal$kotlinx_coroutines_core(z2, jobNode);
    }

    public final DisposableHandle invokeOnCompletionInternal$kotlinx_coroutines_core(boolean z, JobNode jobNode) {
        boolean z2;
        jobNode.setJob(this);
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (!empty.isActive()) {
                    promoteEmptyToNodeList(empty);
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, state$kotlinx_coroutines_core, jobNode)) {
                    break;
                }
            } else {
                Throwable th = null;
                if (state$kotlinx_coroutines_core instanceof Incomplete) {
                    Incomplete incomplete = (Incomplete) state$kotlinx_coroutines_core;
                    NodeList list = incomplete.getList();
                    if (list == null) {
                        Intrinsics.checkNotNull(state$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                        promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                    } else {
                        if (jobNode.getOnCancelling()) {
                            Finishing finishing = incomplete instanceof Finishing ? (Finishing) incomplete : null;
                            if (finishing != null) {
                                th = finishing.getRootCause();
                            }
                            if (th == null) {
                                z2 = list.addLast(jobNode, 5);
                            } else {
                                if (z) {
                                    jobNode.invoke(th);
                                }
                                return NonDisposableHandle.INSTANCE;
                            }
                        } else {
                            z2 = list.addLast(jobNode, 1);
                        }
                        if (z2) {
                            break;
                        }
                    }
                } else {
                    if (z) {
                        Object state$kotlinx_coroutines_core2 = getState$kotlinx_coroutines_core();
                        CompletedExceptionally completedExceptionally = state$kotlinx_coroutines_core2 instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core2 : null;
                        if (completedExceptionally != null) {
                            th = completedExceptionally.cause;
                        }
                        jobNode.invoke(th);
                    }
                    return NonDisposableHandle.INSTANCE;
                }
            }
        }
        return jobNode;
    }

    private final void promoteEmptyToNodeList(Empty empty) {
        NodeList nodeList = new NodeList();
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, empty, empty.isActive() ? nodeList : new InactiveNodeList(nodeList));
    }

    private final void promoteSingleToNodeList(JobNode jobNode) {
        jobNode.addOneIfEmpty(new NodeList());
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, jobNode, jobNode.getNextNode());
    }

    public final Object join(Continuation<? super Unit> continuation) {
        if (!joinInternal()) {
            JobKt.ensureActive(continuation.getContext());
            return Unit.INSTANCE;
        }
        Object joinSuspend = joinSuspend(continuation);
        return joinSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinSuspend : Unit.INSTANCE;
    }

    public final SelectClause0 getOnJoin() {
        JobSupport$onJoin$1 jobSupport$onJoin$1 = JobSupport$onJoin$1.INSTANCE;
        Intrinsics.checkNotNull(jobSupport$onJoin$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        return new SelectClause0Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(jobSupport$onJoin$1, 3), (Function3) null, 4, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public final void registerSelectForOnJoin(SelectInstance<?> selectInstance, Object obj) {
        if (!joinInternal()) {
            selectInstance.selectInRegistrationPhase(Unit.INSTANCE);
        } else {
            selectInstance.disposeOnCompletion(JobKt__JobKt.invokeOnCompletion$default(this, false, new SelectOnJoinCompletionHandler(selectInstance), 1, (Object) null));
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnJoinCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "onCancelling", "", "getOnCancelling", "()Z", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: JobSupport.kt */
    private final class SelectOnJoinCompletionHandler extends JobNode {
        private final SelectInstance<?> select;

        public boolean getOnCancelling() {
            return false;
        }

        public SelectOnJoinCompletionHandler(SelectInstance<?> selectInstance) {
            this.select = selectInstance;
        }

        public void invoke(Throwable th) {
            this.select.trySelect(JobSupport.this, Unit.INSTANCE);
        }
    }

    /* access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable th) {
        CancellationException cancellationException;
        if (th == null || (cancellationException = toCancellationException$default(this, th, (String) null, 1, (Object) null)) == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), (Throwable) null, this);
        }
        cancelInternal(cancellationException);
        return true;
    }

    public void cancelInternal(Throwable th) {
        cancelImpl$kotlinx_coroutines_core(th);
    }

    public final void parentCancelled(ParentJob parentJob) {
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        if (!cancelImpl$kotlinx_coroutines_core(th) || !getHandlesException$kotlinx_coroutines_core()) {
            return false;
        }
        return true;
    }

    public final boolean cancelCoroutine(Throwable th) {
        return cancelImpl$kotlinx_coroutines_core(th);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object obj) {
        Object access$getCOMPLETING_ALREADY$p = JobSupportKt.COMPLETING_ALREADY;
        if (getOnCancelComplete$kotlinx_coroutines_core() && (access$getCOMPLETING_ALREADY$p = cancelMakeCompleting(obj)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.COMPLETING_ALREADY) {
            access$getCOMPLETING_ALREADY$p = makeCancelling(obj);
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.COMPLETING_ALREADY || access$getCOMPLETING_ALREADY$p == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.TOO_LATE_TO_CANCEL) {
            return false;
        }
        afterCompletion(access$getCOMPLETING_ALREADY$p);
        return true;
    }

    public static /* synthetic */ JobCancellationException defaultCancellationException$kotlinx_coroutines_core$default(JobSupport jobSupport, String str, Throwable th, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                th = null;
            }
            if (str == null) {
                str = jobSupport.cancellationExceptionMessage();
            }
            return new JobCancellationException(str, th, jobSupport);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
    }

    public final JobCancellationException defaultCancellationException$kotlinx_coroutines_core(String str, Throwable th) {
        if (str == null) {
            str = cancellationExceptionMessage();
        }
        return new JobCancellationException(str, th, this);
    }

    public CancellationException getChildJobCancellationCause() {
        Throwable th;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        CancellationException cancellationException = null;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            th = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            th = null;
        } else {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$kotlinx_coroutines_core).toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        }
        return cancellationException == null ? new JobCancellationException("Parent job is " + stateString(state$kotlinx_coroutines_core), th, this) : cancellationException;
    }

    private final Throwable createCauseException(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new JobCancellationException(cancellationExceptionMessage(), (Throwable) null, this) : th;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((ParentJob) obj).getChildJobCancellationCause();
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + incomplete).toString());
    }

    private final boolean tryMakeCancelling(Incomplete incomplete, Throwable th) {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(incomplete instanceof Finishing))) {
            throw new AssertionError();
        } else if (!DebugKt.getASSERTIONS_ENABLED() || incomplete.isActive()) {
            NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
            if (orPromoteCancellingList == null) {
                return false;
            }
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, incomplete, new Finishing(orPromoteCancellingList, false, th))) {
                return false;
            }
            notifyCancelling(orPromoteCancellingList, th);
            return true;
        } else {
            throw new AssertionError();
        }
    }

    private final Object tryMakeCompleting(Object obj, Object obj2) {
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.COMPLETING_ALREADY;
        }
        if ((!(obj instanceof Empty) && !(obj instanceof JobNode)) || (obj instanceof ChildHandleNode) || (obj2 instanceof CompletedExceptionally)) {
            return tryMakeCompletingSlowPath((Incomplete) obj, obj2);
        }
        if (tryFinalizeSimpleState((Incomplete) obj, obj2)) {
            return obj2;
        }
        return JobSupportKt.COMPLETING_RETRY;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0082, code lost:
        r7 = (java.lang.Throwable) r3.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0086, code lost:
        if (r7 == null) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0088, code lost:
        notifyCancelling(r0, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x008b, code lost:
        r7 = r0;
        r2 = nextChild(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0092, code lost:
        if (r2 == null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0098, code lost:
        if (tryWaitForChild(r1, r2, r8) == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x009c, code lost:
        return kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009d, code lost:
        r0.close(2);
        r7 = nextChild(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a5, code lost:
        if (r7 == null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ab, code lost:
        if (tryWaitForChild(r1, r7, r8) == false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00af, code lost:
        return kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00b4, code lost:
        return finalizeFinishingState(r1, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object tryMakeCompletingSlowPath(kotlinx.coroutines.Incomplete r7, java.lang.Object r8) {
        /*
            r6 = this;
            kotlinx.coroutines.NodeList r0 = r6.getOrPromoteCancellingList(r7)
            if (r0 != 0) goto L_0x000b
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_RETRY
            return r7
        L_0x000b:
            boolean r1 = r7 instanceof kotlinx.coroutines.JobSupport.Finishing
            r2 = 0
            if (r1 == 0) goto L_0x0014
            r1 = r7
            kotlinx.coroutines.JobSupport$Finishing r1 = (kotlinx.coroutines.JobSupport.Finishing) r1
            goto L_0x0015
        L_0x0014:
            r1 = r2
        L_0x0015:
            r3 = 0
            if (r1 != 0) goto L_0x001d
            kotlinx.coroutines.JobSupport$Finishing r1 = new kotlinx.coroutines.JobSupport$Finishing
            r1.<init>(r0, r3, r2)
        L_0x001d:
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            monitor-enter(r1)
            boolean r4 = r1.isCompleting()     // Catch:{ all -> 0x00b5 }
            if (r4 == 0) goto L_0x002f
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY     // Catch:{ all -> 0x00b5 }
            monitor-exit(r1)
            return r7
        L_0x002f:
            r4 = 1
            r1.setCompleting(r4)     // Catch:{ all -> 0x00b5 }
            if (r1 == r7) goto L_0x0045
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = _state$volatile$FU     // Catch:{ all -> 0x00b5 }
            boolean r7 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r5, r6, r7, r1)     // Catch:{ all -> 0x00b5 }
            if (r7 != 0) goto L_0x0045
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_RETRY     // Catch:{ all -> 0x00b5 }
            monitor-exit(r1)
            return r7
        L_0x0045:
            boolean r7 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch:{ all -> 0x00b5 }
            if (r7 == 0) goto L_0x0059
            boolean r7 = r1.isSealed()     // Catch:{ all -> 0x00b5 }
            r7 = r7 ^ r4
            if (r7 == 0) goto L_0x0053
            goto L_0x0059
        L_0x0053:
            java.lang.AssertionError r7 = new java.lang.AssertionError     // Catch:{ all -> 0x00b5 }
            r7.<init>()     // Catch:{ all -> 0x00b5 }
            throw r7     // Catch:{ all -> 0x00b5 }
        L_0x0059:
            boolean r7 = r1.isCancelling()     // Catch:{ all -> 0x00b5 }
            boolean r5 = r8 instanceof kotlinx.coroutines.CompletedExceptionally     // Catch:{ all -> 0x00b5 }
            if (r5 == 0) goto L_0x0065
            r5 = r8
            kotlinx.coroutines.CompletedExceptionally r5 = (kotlinx.coroutines.CompletedExceptionally) r5     // Catch:{ all -> 0x00b5 }
            goto L_0x0066
        L_0x0065:
            r5 = r2
        L_0x0066:
            if (r5 == 0) goto L_0x006d
            java.lang.Throwable r5 = r5.cause     // Catch:{ all -> 0x00b5 }
            r1.addExceptionLocked(r5)     // Catch:{ all -> 0x00b5 }
        L_0x006d:
            java.lang.Throwable r5 = r1.getRootCause()     // Catch:{ all -> 0x00b5 }
            r7 = r7 ^ r4
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ all -> 0x00b5 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x00b5 }
            if (r7 == 0) goto L_0x007d
            r2 = r5
        L_0x007d:
            r3.element = r2     // Catch:{ all -> 0x00b5 }
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00b5 }
            monitor-exit(r1)
            T r7 = r3.element
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            if (r7 == 0) goto L_0x008b
            r6.notifyCancelling(r0, r7)
        L_0x008b:
            r7 = r0
            kotlinx.coroutines.internal.LockFreeLinkedListNode r7 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r7
            kotlinx.coroutines.ChildHandleNode r2 = r6.nextChild(r7)
            if (r2 == 0) goto L_0x009d
            boolean r2 = r6.tryWaitForChild(r1, r2, r8)
            if (r2 == 0) goto L_0x009d
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN
            return r7
        L_0x009d:
            r2 = 2
            r0.close(r2)
            kotlinx.coroutines.ChildHandleNode r7 = r6.nextChild(r7)
            if (r7 == 0) goto L_0x00b0
            boolean r7 = r6.tryWaitForChild(r1, r7, r8)
            if (r7 == 0) goto L_0x00b0
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN
            return r7
        L_0x00b0:
            java.lang.Object r7 = r6.finalizeFinishingState(r1, r8)
            return r7
        L_0x00b5:
            r7 = move-exception
            monitor-exit(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.tryMakeCompletingSlowPath(kotlinx.coroutines.Incomplete, java.lang.Object):java.lang.Object");
    }

    private final Throwable getExceptionOrNull(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (JobKt.invokeOnCompletion(childHandleNode.childJob, false, new ChildCompletion(this, finishing, childHandleNode, obj)) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final void continueCompleting(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        if (!DebugKt.getASSERTIONS_ENABLED() || getState$kotlinx_coroutines_core() == finishing) {
            LockFreeLinkedListNode lockFreeLinkedListNode = childHandleNode;
            ChildHandleNode nextChild = nextChild(lockFreeLinkedListNode);
            if (nextChild == null || !tryWaitForChild(finishing, nextChild, obj)) {
                finishing.getList().close(2);
                ChildHandleNode nextChild2 = nextChild(lockFreeLinkedListNode);
                if (nextChild2 == null || !tryWaitForChild(finishing, nextChild2, obj)) {
                    afterCompletion(finalizeFinishingState(finishing, obj));
                    return;
                }
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, (Continuation<? super JobSupport$children$1>) null));
    }

    public final ChildHandle attachChild(ChildJob childJob) {
        ChildHandleNode childHandleNode = new ChildHandleNode(childJob);
        childHandleNode.setJob(this);
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (!empty.isActive()) {
                    promoteEmptyToNodeList(empty);
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, state$kotlinx_coroutines_core, childHandleNode)) {
                    break;
                }
            } else {
                Throwable th = null;
                if (state$kotlinx_coroutines_core instanceof Incomplete) {
                    NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                    if (list == null) {
                        Intrinsics.checkNotNull(state$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                        promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                    } else {
                        LockFreeLinkedListNode lockFreeLinkedListNode = childHandleNode;
                        if (!list.addLast(lockFreeLinkedListNode, 7)) {
                            boolean addLast = list.addLast(lockFreeLinkedListNode, 3);
                            Object state$kotlinx_coroutines_core2 = getState$kotlinx_coroutines_core();
                            if (state$kotlinx_coroutines_core2 instanceof Finishing) {
                                th = ((Finishing) state$kotlinx_coroutines_core2).getRootCause();
                            } else if (!DebugKt.getASSERTIONS_ENABLED() || (!(state$kotlinx_coroutines_core2 instanceof Incomplete))) {
                                CompletedExceptionally completedExceptionally = state$kotlinx_coroutines_core2 instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core2 : null;
                                if (completedExceptionally != null) {
                                    th = completedExceptionally.cause;
                                }
                            } else {
                                throw new AssertionError();
                            }
                            childHandleNode.invoke(th);
                            if (!addLast) {
                                return NonDisposableHandle.INSTANCE;
                            }
                            if (DebugKt.getASSERTIONS_ENABLED() && th == null) {
                                throw new AssertionError();
                            }
                        }
                    }
                } else {
                    Object state$kotlinx_coroutines_core3 = getState$kotlinx_coroutines_core();
                    CompletedExceptionally completedExceptionally2 = state$kotlinx_coroutines_core3 instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core3 : null;
                    if (completedExceptionally2 != null) {
                        th = completedExceptionally2.cause;
                    }
                    childHandleNode.invoke(th);
                    return NonDisposableHandle.INSTANCE;
                }
            }
        }
        return childHandleNode;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        throw th;
    }

    public String toString() {
        return toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }

    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    private final String stateString(Object obj) {
        if (obj instanceof Finishing) {
            Finishing finishing = (Finishing) obj;
            if (finishing.isCancelling()) {
                return "Cancelling";
            }
            if (finishing.isCompleting()) {
                return "Completing";
            }
            return "Active";
        } else if (obj instanceof Incomplete) {
            if (((Incomplete) obj).isActive()) {
                return "Active";
            }
            return "New";
        } else if (obj instanceof CompletedExceptionally) {
            return "Cancelled";
        } else {
            return "Completed";
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0002j\u0002`\u00012\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0$2\b\u0010%\u001a\u0004\u0018\u00010\tJ\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\tJ\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0*H\u0002J\b\u0010+\u001a\u00020,H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\t\u0010\u000e\u001a\u00020\u000fX\u0004R$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00078F@FX\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015X\u0004R(\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0015X\u0004R(\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00028B@BX\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b \u0010\u0011R\u0011\u0010!\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010\u0011R\u0014\u0010\"\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011¨\u0006-"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/internal/SynchronizedObject;", "", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "<init>", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "getList", "()Lkotlinx/coroutines/NodeList;", "_isCompleting", "Lkotlinx/atomicfu/AtomicBoolean;", "value", "()Z", "setCompleting", "(Z)V", "_rootCause", "Lkotlinx/atomicfu/AtomicRef;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "(Ljava/lang/Throwable;)V", "_exceptionsHolder", "exceptionsHolder", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "isSealed", "isCancelling", "isActive", "sealLocked", "", "proposedException", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: JobSupport.kt */
    private static final class Finishing implements Incomplete {
        /* access modifiers changed from: private */
        public static final /* synthetic */ AtomicReferenceFieldUpdater _exceptionsHolder$volatile$FU;
        /* access modifiers changed from: private */
        public static final /* synthetic */ AtomicIntegerFieldUpdater _isCompleting$volatile$FU;
        /* access modifiers changed from: private */
        public static final /* synthetic */ AtomicReferenceFieldUpdater _rootCause$volatile$FU;
        private volatile /* synthetic */ Object _exceptionsHolder$volatile;
        private volatile /* synthetic */ int _isCompleting$volatile;
        private volatile /* synthetic */ Object _rootCause$volatile;
        private final NodeList list;

        static {
            Class<Finishing> cls = Finishing.class;
            _isCompleting$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_isCompleting$volatile");
            _rootCause$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_rootCause$volatile");
            _exceptionsHolder$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_exceptionsHolder$volatile");
        }

        private final /* synthetic */ Object get_exceptionsHolder$volatile() {
            return this._exceptionsHolder$volatile;
        }

        private final /* synthetic */ int get_isCompleting$volatile() {
            return this._isCompleting$volatile;
        }

        private final /* synthetic */ Object get_rootCause$volatile() {
            return this._rootCause$volatile;
        }

        private final /* synthetic */ void set_exceptionsHolder$volatile(Object obj) {
            this._exceptionsHolder$volatile = obj;
        }

        private final /* synthetic */ void set_isCompleting$volatile(int i) {
            this._isCompleting$volatile = i;
        }

        private final /* synthetic */ void set_rootCause$volatile(Object obj) {
            this._rootCause$volatile = obj;
        }

        public NodeList getList() {
            return this.list;
        }

        public Finishing(NodeList nodeList, boolean z, Throwable th) {
            this.list = nodeList;
            this._isCompleting$volatile = z ? 1 : 0;
            this._rootCause$volatile = th;
        }

        public final boolean isCompleting() {
            return _isCompleting$volatile$FU.get(this) != 0;
        }

        public final void setCompleting(boolean z) {
            _isCompleting$volatile$FU.set(this, z ? 1 : 0);
        }

        public final Throwable getRootCause() {
            return (Throwable) _rootCause$volatile$FU.get(this);
        }

        public final void setRootCause(Throwable th) {
            _rootCause$volatile$FU.set(this, th);
        }

        private final Object getExceptionsHolder() {
            return _exceptionsHolder$volatile$FU.get(this);
        }

        private final void setExceptionsHolder(Object obj) {
            _exceptionsHolder$volatile$FU.set(this, obj);
        }

        public final boolean isSealed() {
            return getExceptionsHolder() == JobSupportKt.SEALED;
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        public boolean isActive() {
            return getRootCause() == null;
        }

        public final List<Throwable> sealLocked(Throwable th) {
            ArrayList<Throwable> arrayList;
            Object exceptionsHolder = getExceptionsHolder();
            if (exceptionsHolder == null) {
                arrayList = allocateList();
            } else if (exceptionsHolder instanceof Throwable) {
                ArrayList<Throwable> allocateList = allocateList();
                allocateList.add(exceptionsHolder);
                arrayList = allocateList;
            } else if (exceptionsHolder instanceof ArrayList) {
                arrayList = (ArrayList) exceptionsHolder;
            } else {
                throw new IllegalStateException(("State is " + exceptionsHolder).toString());
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (th != null && !Intrinsics.areEqual((Object) th, (Object) rootCause)) {
                arrayList.add(th);
            }
            setExceptionsHolder(JobSupportKt.SEALED);
            return arrayList;
        }

        public final void addExceptionLocked(Throwable th) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                setRootCause(th);
            } else if (th != rootCause) {
                Object exceptionsHolder = getExceptionsHolder();
                if (exceptionsHolder == null) {
                    setExceptionsHolder(th);
                } else if (exceptionsHolder instanceof Throwable) {
                    if (th != exceptionsHolder) {
                        ArrayList<Throwable> allocateList = allocateList();
                        allocateList.add(exceptionsHolder);
                        allocateList.add(th);
                        setExceptionsHolder(allocateList);
                    }
                } else if (exceptionsHolder instanceof ArrayList) {
                    ((ArrayList) exceptionsHolder).add(th);
                } else {
                    throw new IllegalStateException(("State is " + exceptionsHolder).toString());
                }
            }
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + getExceptionsHolder() + ", list=" + getList() + ']';
        }
    }

    private final boolean isCancelling(Incomplete incomplete) {
        return (incomplete instanceof Finishing) && ((Finishing) incomplete).isCancelling();
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "onCancelling", "", "getOnCancelling", "()Z", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: JobSupport.kt */
    private static final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public boolean getOnCancelling() {
            return false;
        }

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        public void invoke(Throwable th) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: JobSupport.kt */
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        public AwaitContinuation(Continuation<? super T> continuation, JobSupport jobSupport) {
            super(continuation, 1);
            this.job = jobSupport;
        }

        public Throwable getContinuationCancellationCause(Job job2) {
            Throwable rootCause;
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            if ((state$kotlinx_coroutines_core instanceof Finishing) && (rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause()) != null) {
                return rootCause;
            }
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
            return job2.getCancellationException();
        }

        /* access modifiers changed from: protected */
        public String nameString() {
            return "AwaitContinuation";
        }
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            return getExceptionOrNull(state$kotlinx_coroutines_core);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        } else if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        } else {
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        }
    }

    /* access modifiers changed from: protected */
    public final Object awaitInternal(Continuation<Object> continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                    return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
                }
                Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
                if (!DebugKt.getRECOVER_STACK_TRACES()) {
                    throw th;
                } else if (!(continuation instanceof CoroutineStackFrame)) {
                    throw th;
                } else {
                    throw StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
                }
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(continuation);
    }

    /* access modifiers changed from: private */
    public final Object awaitSuspend(Continuation<Object> continuation) {
        AwaitContinuation awaitContinuation = new AwaitContinuation(IntrinsicsKt.intercepted(continuation), this);
        awaitContinuation.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(awaitContinuation, JobKt__JobKt.invokeOnCompletion$default(this, false, new ResumeAwaitOnCompletion(awaitContinuation), 1, (Object) null));
        Object result = awaitContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final SelectClause1<?> getOnAwaitInternal() {
        JobSupport$onAwaitInternal$1 jobSupport$onAwaitInternal$1 = JobSupport$onAwaitInternal$1.INSTANCE;
        Intrinsics.checkNotNull(jobSupport$onAwaitInternal$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        JobSupport$onAwaitInternal$2 jobSupport$onAwaitInternal$2 = JobSupport$onAwaitInternal$2.INSTANCE;
        Intrinsics.checkNotNull(jobSupport$onAwaitInternal$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl<>(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(jobSupport$onAwaitInternal$1, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(jobSupport$onAwaitInternal$2, 3), (Function3) null, 8, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public final void onAwaitInternalRegFunc(SelectInstance<?> selectInstance, Object obj) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                    state$kotlinx_coroutines_core = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
                }
                selectInstance.selectInRegistrationPhase(state$kotlinx_coroutines_core);
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        selectInstance.disposeOnCompletion(JobKt__JobKt.invokeOnCompletion$default(this, false, new SelectOnAwaitCompletionHandler(selectInstance), 1, (Object) null));
    }

    /* access modifiers changed from: private */
    public final Object onAwaitInternalProcessResFunc(Object obj, Object obj2) {
        if (!(obj2 instanceof CompletedExceptionally)) {
            return obj2;
        }
        throw ((CompletedExceptionally) obj2).cause;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnAwaitCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "onCancelling", "", "getOnCancelling", "()Z", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: JobSupport.kt */
    private final class SelectOnAwaitCompletionHandler extends JobNode {
        private final SelectInstance<?> select;

        public boolean getOnCancelling() {
            return false;
        }

        public SelectOnAwaitCompletionHandler(SelectInstance<?> selectInstance) {
            this.select = selectInstance;
        }

        public void invoke(Throwable th) {
            Object state$kotlinx_coroutines_core = JobSupport.this.getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                state$kotlinx_coroutines_core = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            }
            this.select.trySelect(JobSupport.this, state$kotlinx_coroutines_core);
        }
    }

    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    private final boolean tryPutNodeIntoList(JobNode jobNode, Function2<? super Incomplete, ? super NodeList, Boolean> function2) {
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (!empty.isActive()) {
                    promoteEmptyToNodeList(empty);
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$volatile$FU, this, state$kotlinx_coroutines_core, jobNode)) {
                    return true;
                }
            } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            } else {
                NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                if (list == null) {
                    Intrinsics.checkNotNull(state$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                } else if (function2.invoke(state$kotlinx_coroutines_core, list).booleanValue()) {
                    return true;
                }
            }
        }
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    /* access modifiers changed from: private */
    public final Object joinSuspend(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, JobKt__JobKt.invokeOnCompletion$default(this, false, new ResumeOnCompletion(cancellableContinuation), 1, (Object) null));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return result;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void removeNode$kotlinx_coroutines_core(kotlinx.coroutines.JobNode r4) {
        /*
            r3 = this;
        L_0x0000:
            java.lang.Object r0 = r3.getState$kotlinx_coroutines_core()
            boolean r1 = r0 instanceof kotlinx.coroutines.JobNode
            if (r1 == 0) goto L_0x001a
            if (r0 == r4) goto L_0x000b
            return
        L_0x000b:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = _state$volatile$FU
            kotlinx.coroutines.Empty r2 = kotlinx.coroutines.JobSupportKt.EMPTY_ACTIVE
            boolean r0 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r1, r3, r0, r2)
            if (r0 == 0) goto L_0x0000
            return
        L_0x001a:
            boolean r1 = r0 instanceof kotlinx.coroutines.Incomplete
            if (r1 == 0) goto L_0x0029
            kotlinx.coroutines.Incomplete r0 = (kotlinx.coroutines.Incomplete) r0
            kotlinx.coroutines.NodeList r0 = r0.getList()
            if (r0 == 0) goto L_0x0029
            r4.remove()
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.removeNode$kotlinx_coroutines_core(kotlinx.coroutines.JobNode):void");
    }

    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), (Throwable) null, this);
        }
        cancelInternal(cancellationException);
    }

    private final Object cancelMakeCompleting(Object obj) {
        Object tryMakeCompleting;
        do {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((state$kotlinx_coroutines_core instanceof Finishing) && ((Finishing) state$kotlinx_coroutines_core).isCompleting())) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new CompletedExceptionally(createCauseException(obj), false, 2, (DefaultConstructorMarker) null));
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        if (r0 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
        notifyCancelling(((kotlinx.coroutines.JobSupport.Finishing) r2).getList(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
        return kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object makeCancelling(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            java.lang.Object r2 = r6.getState$kotlinx_coroutines_core()
            boolean r3 = r2 instanceof kotlinx.coroutines.JobSupport.Finishing
            if (r3 == 0) goto L_0x0051
            monitor-enter(r2)
            r3 = r2
            kotlinx.coroutines.JobSupport$Finishing r3 = (kotlinx.coroutines.JobSupport.Finishing) r3     // Catch:{ all -> 0x004e }
            boolean r3 = r3.isSealed()     // Catch:{ all -> 0x004e }
            if (r3 == 0) goto L_0x001a
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.TOO_LATE_TO_CANCEL     // Catch:{ all -> 0x004e }
            monitor-exit(r2)
            return r7
        L_0x001a:
            r3 = r2
            kotlinx.coroutines.JobSupport$Finishing r3 = (kotlinx.coroutines.JobSupport.Finishing) r3     // Catch:{ all -> 0x004e }
            boolean r3 = r3.isCancelling()     // Catch:{ all -> 0x004e }
            if (r7 != 0) goto L_0x0025
            if (r3 != 0) goto L_0x0031
        L_0x0025:
            if (r1 != 0) goto L_0x002b
            java.lang.Throwable r1 = r6.createCauseException(r7)     // Catch:{ all -> 0x004e }
        L_0x002b:
            r7 = r2
            kotlinx.coroutines.JobSupport$Finishing r7 = (kotlinx.coroutines.JobSupport.Finishing) r7     // Catch:{ all -> 0x004e }
            r7.addExceptionLocked(r1)     // Catch:{ all -> 0x004e }
        L_0x0031:
            r7 = r2
            kotlinx.coroutines.JobSupport$Finishing r7 = (kotlinx.coroutines.JobSupport.Finishing) r7     // Catch:{ all -> 0x004e }
            java.lang.Throwable r7 = r7.getRootCause()     // Catch:{ all -> 0x004e }
            r1 = r3 ^ 1
            if (r1 == 0) goto L_0x003d
            r0 = r7
        L_0x003d:
            monitor-exit(r2)
            if (r0 == 0) goto L_0x0049
            kotlinx.coroutines.JobSupport$Finishing r2 = (kotlinx.coroutines.JobSupport.Finishing) r2
            kotlinx.coroutines.NodeList r7 = r2.getList()
            r6.notifyCancelling(r7, r0)
        L_0x0049:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY
            return r7
        L_0x004e:
            r7 = move-exception
            monitor-exit(r2)
            throw r7
        L_0x0051:
            boolean r3 = r2 instanceof kotlinx.coroutines.Incomplete
            if (r3 == 0) goto L_0x00a0
            if (r1 != 0) goto L_0x005b
            java.lang.Throwable r1 = r6.createCauseException(r7)
        L_0x005b:
            r3 = r2
            kotlinx.coroutines.Incomplete r3 = (kotlinx.coroutines.Incomplete) r3
            boolean r4 = r3.isActive()
            if (r4 == 0) goto L_0x006f
            boolean r2 = r6.tryMakeCancelling(r3, r1)
            if (r2 == 0) goto L_0x0002
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY
            return r7
        L_0x006f:
            kotlinx.coroutines.CompletedExceptionally r3 = new kotlinx.coroutines.CompletedExceptionally
            r4 = 0
            r5 = 2
            r3.<init>(r1, r4, r5, r0)
            java.lang.Object r3 = r6.tryMakeCompleting(r2, r3)
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY
            if (r3 == r4) goto L_0x0087
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.JobSupportKt.COMPLETING_RETRY
            if (r3 == r2) goto L_0x0002
            return r3
        L_0x0087:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot happen in "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x00a0:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobSupportKt.TOO_LATE_TO_CANCEL
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCancelling(java.lang.Object):java.lang.Object");
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                return false;
            }
            if (tryMakeCompleting == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        afterCompletion(tryMakeCompleting);
        return true;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, getExceptionOrNull(obj));
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }
}
