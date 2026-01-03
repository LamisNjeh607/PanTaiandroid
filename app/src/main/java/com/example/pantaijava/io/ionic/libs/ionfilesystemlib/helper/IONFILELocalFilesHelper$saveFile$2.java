package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$2", f = "IONFILELocalFilesHelper.kt", i = {0}, l = {141}, m = "invokeSuspend", n = {"file"}, s = {"L$1"})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$saveFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ String $fullPath;
    final /* synthetic */ IONFILESaveOptions $options;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ IONFILELocalFilesHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$saveFile$2(String str, IONFILELocalFilesHelper iONFILELocalFilesHelper, IONFILESaveOptions iONFILESaveOptions, Continuation<? super IONFILELocalFilesHelper$saveFile$2> continuation) {
        super(2, continuation);
        this.$fullPath = str;
        this.this$0 = iONFILELocalFilesHelper;
        this.$options = iONFILESaveOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILELocalFilesHelper$saveFile$2 iONFILELocalFilesHelper$saveFile$2 = new IONFILELocalFilesHelper$saveFile$2(this.$fullPath, this.this$0, this.$options, continuation);
        iONFILELocalFilesHelper$saveFile$2.L$0 = obj;
        return iONFILELocalFilesHelper$saveFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((IONFILELocalFilesHelper$saveFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ae, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ea, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ee, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f1, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r8, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f5, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006b A[Catch:{ all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c A[Catch:{ all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 != r2) goto L_0x001d
            java.lang.Object r0 = r7.L$1
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r1 = r7.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions r1 = (io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x00f6 }
            kotlin.Result r8 = (kotlin.Result) r8     // Catch:{ all -> 0x00f6 }
            java.lang.Object r8 = r8.m199unboximpl()     // Catch:{ all -> 0x00f6 }
            goto L_0x0057
        L_0x001d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            java.lang.String r8 = r7.$fullPath
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r1 = r7.this$0
            io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions r3 = r7.$options
            kotlin.Result$Companion r4 = kotlin.Result.Companion     // Catch:{ all -> 0x00f6 }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x00f6 }
            r4.<init>(r8)     // Catch:{ all -> 0x00f6 }
            boolean r5 = r4.exists()     // Catch:{ all -> 0x00f6 }
            if (r5 != 0) goto L_0x0061
            io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r5 = new io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions     // Catch:{ all -> 0x00f6 }
            boolean r6 = r3.getCreateFileRecursive()     // Catch:{ all -> 0x00f6 }
            r5.<init>(r6)     // Catch:{ all -> 0x00f6 }
            r7.L$0 = r3     // Catch:{ all -> 0x00f6 }
            r7.L$1 = r4     // Catch:{ all -> 0x00f6 }
            r7.label = r2     // Catch:{ all -> 0x00f6 }
            java.lang.Object r8 = r1.m174createFile0E7RQCE(r8, r5, r7)     // Catch:{ all -> 0x00f6 }
            if (r8 != r0) goto L_0x0055
            return r0
        L_0x0055:
            r1 = r3
            r0 = r4
        L_0x0057:
            java.lang.Throwable r8 = kotlin.Result.m193exceptionOrNullimpl(r8)     // Catch:{ all -> 0x00f6 }
            if (r8 != 0) goto L_0x0060
            r4 = r0
            r3 = r1
            goto L_0x0061
        L_0x0060:
            throw r8     // Catch:{ all -> 0x00f6 }
        L_0x0061:
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x00f6 }
            io.ionic.libs.ionfilesystemlib.model.IONFILESaveMode r0 = r3.getMode()     // Catch:{ all -> 0x00f6 }
            io.ionic.libs.ionfilesystemlib.model.IONFILESaveMode r1 = io.ionic.libs.ionfilesystemlib.model.IONFILESaveMode.APPEND     // Catch:{ all -> 0x00f6 }
            if (r0 != r1) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r2 = 0
        L_0x006d:
            r8.<init>(r4, r2)     // Catch:{ all -> 0x00f6 }
            java.io.Closeable r8 = (java.io.Closeable) r8     // Catch:{ all -> 0x00f6 }
            r0 = r8
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ all -> 0x00ef }
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r1 = r3.getEncoding()     // Catch:{ all -> 0x00ef }
            boolean r1 = r1 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset     // Catch:{ all -> 0x00ef }
            r2 = 0
            if (r1 == 0) goto L_0x00af
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ all -> 0x00ef }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00ef }
            java.io.OutputStream r0 = (java.io.OutputStream) r0     // Catch:{ all -> 0x00ef }
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r5 = r3.getEncoding()     // Catch:{ all -> 0x00ef }
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding$WithCharset r5 = (io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset) r5     // Catch:{ all -> 0x00ef }
            java.nio.charset.Charset r5 = r5.getCharset()     // Catch:{ all -> 0x00ef }
            r4.<init>(r0, r5)     // Catch:{ all -> 0x00ef }
            java.io.Writer r4 = (java.io.Writer) r4     // Catch:{ all -> 0x00ef }
            r1.<init>(r4)     // Catch:{ all -> 0x00ef }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ all -> 0x00ef }
            r0 = r1
            java.io.BufferedWriter r0 = (java.io.BufferedWriter) r0     // Catch:{ all -> 0x00a8 }
            java.lang.String r3 = r3.getData()     // Catch:{ all -> 0x00a8 }
            r0.write(r3)     // Catch:{ all -> 0x00a8 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00a8 }
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ all -> 0x00ef }
            goto L_0x00dc
        L_0x00a8:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ all -> 0x00ef }
            throw r2     // Catch:{ all -> 0x00ef }
        L_0x00af:
            java.lang.String r1 = r3.getData()     // Catch:{ all -> 0x00ef }
            java.lang.String r3 = ","
            r4 = 2
            java.lang.String r1 = kotlin.text.StringsKt.substringAfter$default((java.lang.String) r1, (java.lang.String) r3, (java.lang.String) r2, (int) r4, (java.lang.Object) r2)     // Catch:{ all -> 0x00ef }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x00ef }
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r1)     // Catch:{ all -> 0x00ef }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ef }
            byte[] r1 = android.util.Base64.decode(r1, r4)     // Catch:{ all -> 0x00ef }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00ef }
            java.io.OutputStream r0 = (java.io.OutputStream) r0     // Catch:{ all -> 0x00ef }
            r3.<init>(r0)     // Catch:{ all -> 0x00ef }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ all -> 0x00ef }
            r0 = r3
            java.io.BufferedOutputStream r0 = (java.io.BufferedOutputStream) r0     // Catch:{ all -> 0x00e8 }
            r0.write(r1)     // Catch:{ all -> 0x00e8 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00e8 }
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ all -> 0x00ef }
        L_0x00dc:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ef }
            kotlin.io.CloseableKt.closeFinally(r8, r2)     // Catch:{ all -> 0x00f6 }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f6 }
            java.lang.Object r8 = kotlin.Result.m190constructorimpl(r8)     // Catch:{ all -> 0x00f6 }
            goto L_0x0101
        L_0x00e8:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00ea }
        L_0x00ea:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r0)     // Catch:{ all -> 0x00ef }
            throw r1     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00f1 }
        L_0x00f1:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r8, r0)     // Catch:{ all -> 0x00f6 }
            throw r1     // Catch:{ all -> 0x00f6 }
        L_0x00f6:
            r8 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m190constructorimpl(r8)
        L_0x0101:
            kotlin.Result r8 = kotlin.Result.m189boximpl(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
