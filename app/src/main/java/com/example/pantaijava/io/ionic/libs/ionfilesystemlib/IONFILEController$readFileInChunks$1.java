package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.IONFILEController$readFileInChunks$1", f = "IONFILEController.kt", i = {0}, l = {295, 150}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
/* compiled from: IONFILEController.kt */
final class IONFILEController$readFileInChunks$1 extends SuspendLambda implements Function2<FlowCollector<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ IONFILEReadInChunksOptions $options;
    final /* synthetic */ IONFILEUri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEController$readFileInChunks$1(IONFILEController iONFILEController, IONFILEUri iONFILEUri, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, Continuation<? super IONFILEController$readFileInChunks$1> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEController;
        this.$uri = iONFILEUri;
        this.$options = iONFILEReadInChunksOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEController$readFileInChunks$1 iONFILEController$readFileInChunks$1 = new IONFILEController$readFileInChunks$1(this.this$0, this.$uri, this.$options, continuation);
        iONFILEController$readFileInChunks$1.L$0 = obj;
        return iONFILEController$readFileInChunks$1;
    }

    public final Object invoke(FlowCollector<? super String> flowCollector, Continuation<? super Unit> continuation) {
        return ((IONFILEController$readFileInChunks$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0029
            if (r1 == r3) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x00c7
        L_0x0013:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001b:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r7 = r7.m199unboximpl()
            goto L_0x0054
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            r1 = r7
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            io.ionic.libs.ionfilesystemlib.IONFILEController r7 = r6.this$0
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r7 = r7.uriHelper
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri r4 = r6.$uri
            boolean r5 = r4 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r5 == 0) goto L_0x0040
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r4 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r4
            goto L_0x005d
        L_0x0040:
            boolean r5 = r4 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r5 == 0) goto L_0x00cb
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r4 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r4
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.L$0 = r1
            r6.label = r3
            java.lang.Object r7 = r7.m180resolveUrigIAlus(r4, r5)
            if (r7 != r0) goto L_0x0054
            return r0
        L_0x0054:
            java.lang.Throwable r3 = kotlin.Result.m193exceptionOrNullimpl(r7)
            if (r3 != 0) goto L_0x0086
            r4 = r7
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r4 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r4
        L_0x005d:
            boolean r7 = r4 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r7 == 0) goto L_0x007f
            r7 = r4
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r7 = r7.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r3 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r7 == r3) goto L_0x006d
            goto L_0x007f
        L_0x006d:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x0090
        L_0x007f:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r4)
            goto L_0x0090
        L_0x0086:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r3)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
        L_0x0090:
            io.ionic.libs.ionfilesystemlib.IONFILEController r3 = r6.this$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions r4 = r6.$options
            java.lang.Throwable r5 = kotlin.Result.m193exceptionOrNullimpl(r7)
            if (r5 != 0) goto L_0x00ca
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            boolean r5 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r5 == 0) goto L_0x00af
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r3 = r3.localFilesHelper
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            java.lang.String r7 = r7.getFullPath()
            kotlinx.coroutines.flow.Flow r7 = r3.readFileInChunks(r7, r4)
            goto L_0x00bb
        L_0x00af:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r3 = r3.contentResolverHelper
            android.net.Uri r7 = r7.getUri()
            kotlinx.coroutines.flow.Flow r7 = r3.readFileInChunks(r7, r4)
        L_0x00bb:
            r3 = 0
            r6.L$0 = r3
            r6.label = r2
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.emitAll(r1, r7, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r6)
            if (r7 != r0) goto L_0x00c7
            return r0
        L_0x00c7:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x00ca:
            throw r5
        L_0x00cb:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController$readFileInChunks$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
