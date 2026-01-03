package io.ionic.libs.ionfilesystemlib.helper;

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
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$2", f = "IONFILELocalFilesHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$readFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends String>>, Object> {
    final /* synthetic */ String $fullPath;
    final /* synthetic */ IONFILEReadOptions $options;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$readFile$2(String str, IONFILEReadOptions iONFILEReadOptions, Continuation<? super IONFILELocalFilesHelper$readFile$2> continuation) {
        super(2, continuation);
        this.$fullPath = str;
        this.$options = iONFILEReadOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILELocalFilesHelper$readFile$2 iONFILELocalFilesHelper$readFile$2 = new IONFILELocalFilesHelper$readFile$2(this.$fullPath, this.$options, continuation);
        iONFILELocalFilesHelper$readFile$2.L$0 = obj;
        return iONFILELocalFilesHelper$readFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<String>> continuation) {
        return ((IONFILELocalFilesHelper$readFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.label
            if (r0 != 0) goto L_0x0056
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            java.lang.String r5 = r4.$fullPath
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r0 = r4.$options
            kotlin.Result$Companion r1 = kotlin.Result.Companion     // Catch:{ all -> 0x0046 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0046 }
            r1.<init>(r5)     // Catch:{ all -> 0x0046 }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x0046 }
            r3 = 0
            if (r2 == 0) goto L_0x003f
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ all -> 0x0046 }
            r5.<init>(r1)     // Catch:{ all -> 0x0046 }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ all -> 0x0046 }
            r1 = r5
            java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch:{ all -> 0x0038 }
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x0038 }
            java.lang.String r0 = io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readFull(r1, r0)     // Catch:{ all -> 0x0038 }
            kotlin.io.CloseableKt.closeFinally(r5, r3)     // Catch:{ all -> 0x0046 }
            java.lang.Object r5 = kotlin.Result.m190constructorimpl(r0)     // Catch:{ all -> 0x0046 }
            goto L_0x0051
        L_0x0038:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003a }
        L_0x003a:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r0)     // Catch:{ all -> 0x0046 }
            throw r1     // Catch:{ all -> 0x0046 }
        L_0x003f:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist     // Catch:{ all -> 0x0046 }
            r1 = 2
            r0.<init>(r5, r3, r1, r3)     // Catch:{ all -> 0x0046 }
            throw r0     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r5 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m190constructorimpl(r5)
        L_0x0051:
            kotlin.Result r5 = kotlin.Result.m189boximpl(r5)
            return r5
        L_0x0056:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
