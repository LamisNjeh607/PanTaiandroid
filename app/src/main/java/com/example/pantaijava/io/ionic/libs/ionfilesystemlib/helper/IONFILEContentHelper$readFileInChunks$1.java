package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$1", f = "IONFILEContentHelper.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$readFileInChunks$1 extends SuspendLambda implements Function2<FlowCollector<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ IONFILEReadInChunksOptions $options;
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$readFileInChunks$1(IONFILEContentHelper iONFILEContentHelper, Uri uri, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, Continuation<? super IONFILEContentHelper$readFileInChunks$1> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$uri = uri;
        this.$options = iONFILEReadInChunksOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$readFileInChunks$1 iONFILEContentHelper$readFileInChunks$1 = new IONFILEContentHelper$readFileInChunks$1(this.this$0, this.$uri, this.$options, continuation);
        iONFILEContentHelper$readFileInChunks$1.L$0 = obj;
        return iONFILEContentHelper$readFileInChunks$1;
    }

    public final Object invoke(FlowCollector<? super String> flowCollector, Continuation<? super Unit> continuation) {
        return ((IONFILEContentHelper$readFileInChunks$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            java.lang.Object r0 = r7.L$0
            java.io.Closeable r0 = (java.io.Closeable) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0014 }
            goto L_0x004f
        L_0x0014:
            r8 = move-exception
            goto L_0x0059
        L_0x0016:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r1 = r7.this$0
            android.content.ContentResolver r1 = r1.contentResolver
            android.net.Uri r4 = r7.$uri
            java.io.InputStream r1 = r1.openInputStream(r4)
            if (r1 == 0) goto L_0x005f
            java.io.Closeable r1 = (java.io.Closeable) r1
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions r4 = r7.$options
            r5 = r1
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch:{ all -> 0x0057 }
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$1$1$1 r6 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$1$1$1     // Catch:{ all -> 0x0057 }
            r6.<init>(r8, r3)     // Catch:{ all -> 0x0057 }
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ all -> 0x0057 }
            r7.L$0 = r1     // Catch:{ all -> 0x0057 }
            r7.label = r2     // Catch:{ all -> 0x0057 }
            r8 = 8192(0x2000, float:1.14794E-41)
            java.lang.Object r8 = io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readByChunks(r5, r4, r8, r6, r7)     // Catch:{ all -> 0x0057 }
            if (r8 != r0) goto L_0x004e
            return r0
        L_0x004e:
            r0 = r1
        L_0x004f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0014 }
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            goto L_0x0060
        L_0x0057:
            r8 = move-exception
            r0 = r1
        L_0x0059:
            throw r8     // Catch:{ all -> 0x005a }
        L_0x005a:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r8)
            throw r1
        L_0x005f:
            r8 = r3
        L_0x0060:
            if (r8 == 0) goto L_0x0065
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0065:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r8 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError
            r8.<init>(r3, r2, r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFileInChunks$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
