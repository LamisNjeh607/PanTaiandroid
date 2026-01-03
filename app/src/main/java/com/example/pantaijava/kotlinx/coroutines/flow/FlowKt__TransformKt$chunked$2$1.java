package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: Transform.kt */
final class FlowKt__TransformKt$chunked$2$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<ArrayList<T>> $result;
    final /* synthetic */ int $size;
    final /* synthetic */ FlowCollector<List<? extends T>> $this_flow;

    FlowKt__TransformKt$chunked$2$1(Ref.ObjectRef<ArrayList<T>> objectRef, int i, FlowCollector<? super List<? extends T>> flowCollector) {
        this.$result = objectRef;
        this.$size = i;
        this.$this_flow = flowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object emit(T r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1 r5 = (kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0065
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r6 = r4.$result
            T r6 = r6.element
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            if (r6 != 0) goto L_0x004c
            java.util.ArrayList r6 = new java.util.ArrayList
            int r2 = r4.$size
            r6.<init>(r2)
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r2 = r4.$result
            r2.element = r6
        L_0x004c:
            r6.add(r5)
            int r5 = r6.size()
            int r2 = r4.$size
            if (r5 != r2) goto L_0x006a
            kotlinx.coroutines.flow.FlowCollector<java.util.List<? extends T>> r5 = r4.$this_flow
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.emit(r6, r0)
            if (r5 != r1) goto L_0x0064
            return r1
        L_0x0064:
            r5 = r4
        L_0x0065:
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r5 = r5.$result
            r6 = 0
            r5.element = r6
        L_0x006a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
