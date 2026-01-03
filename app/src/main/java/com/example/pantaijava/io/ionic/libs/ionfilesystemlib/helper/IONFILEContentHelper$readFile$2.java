package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$2", f = "IONFILEContentHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$readFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends String>>, Object> {
    final /* synthetic */ IONFILEReadOptions $options;
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$readFile$2(IONFILEContentHelper iONFILEContentHelper, Uri uri, IONFILEReadOptions iONFILEReadOptions, Continuation<? super IONFILEContentHelper$readFile$2> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$uri = uri;
        this.$options = iONFILEReadOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$readFile$2 iONFILEContentHelper$readFile$2 = new IONFILEContentHelper$readFile$2(this.this$0, this.$uri, this.$options, continuation);
        iONFILEContentHelper$readFile$2.L$0 = obj;
        return iONFILEContentHelper$readFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<String>> continuation) {
        return ((IONFILEContentHelper$readFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.label
            if (r0 != 0) goto L_0x005b
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r5 = r4.this$0
            android.net.Uri r0 = r4.$uri
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r1 = r4.$options
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x0045 }
            android.content.ContentResolver r2 = r5.contentResolver     // Catch:{ all -> 0x0045 }
            java.io.InputStream r0 = r2.openInputStream(r0)     // Catch:{ all -> 0x0045 }
            r2 = 0
            if (r0 == 0) goto L_0x003e
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ all -> 0x0045 }
            r3 = r0
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x0037 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readFull(r3, r1)     // Catch:{ all -> 0x0037 }
            kotlin.io.CloseableKt.closeFinally(r0, r2)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x003e
            java.lang.Object r0 = kotlin.Result.m190constructorimpl(r1)     // Catch:{ all -> 0x0045 }
            goto L_0x0050
        L_0x0037:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x0045 }
            throw r2     // Catch:{ all -> 0x0045 }
        L_0x003e:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x0045 }
            r1 = 1
            r0.<init>(r2, r1, r2)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m190constructorimpl(r0)
        L_0x0050:
            android.net.Uri r1 = r4.$uri
            java.lang.Object r5 = r5.m163mapErrorKWTtemM(r0, r1)
            kotlin.Result r5 = kotlin.Result.m189boximpl(r5)
            return r5
        L_0x005b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
