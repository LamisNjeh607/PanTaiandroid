package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$2", f = "IONFILEContentHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$copyFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ String $destinationPath;
    final /* synthetic */ Uri $sourceUri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$copyFile$2(IONFILEContentHelper iONFILEContentHelper, Uri uri, String str, Continuation<? super IONFILEContentHelper$copyFile$2> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$sourceUri = uri;
        this.$destinationPath = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$copyFile$2 iONFILEContentHelper$copyFile$2 = new IONFILEContentHelper$copyFile$2(this.this$0, this.$sourceUri, this.$destinationPath, continuation);
        iONFILEContentHelper$copyFile$2.L$0 = obj;
        return iONFILEContentHelper$copyFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((IONFILEContentHelper$copyFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0075, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0080, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r10.label
            if (r0 != 0) goto L_0x00ad
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r11 = r10.this$0
            java.lang.String r0 = r10.$destinationPath
            android.net.Uri r1 = r10.$sourceUri
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x0097 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0097 }
            r2.<init>(r0)     // Catch:{ all -> 0x0097 }
            boolean r0 = r2.isDirectory()     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x0091
            java.io.File r0 = r2.getParentFile()     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0034
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x002e
            goto L_0x0034
        L_0x002e:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$NoParentDirectory r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$NoParentDirectory     // Catch:{ all -> 0x0097 }
            r0.<init>()     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x0034:
            android.content.ContentResolver r0 = r11.contentResolver     // Catch:{ all -> 0x0097 }
            java.io.InputStream r0 = r0.openInputStream(r1)     // Catch:{ all -> 0x0097 }
            r1 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0081
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ all -> 0x0097 }
            r4 = r0
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x007a }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x007a }
            r5.<init>(r2)     // Catch:{ all -> 0x007a }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ all -> 0x007a }
            r2 = r5
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch:{ all -> 0x0073 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0073 }
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x0073 }
            r6 = 0
            r7 = 2
            long r6 = kotlin.io.ByteStreamsKt.copyTo$default(r4, r2, r6, r7, r3)     // Catch:{ all -> 0x0073 }
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x006d
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0073 }
            kotlin.io.CloseableKt.closeFinally(r5, r3)     // Catch:{ all -> 0x007a }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007a }
            kotlin.io.CloseableKt.closeFinally(r0, r3)     // Catch:{ all -> 0x0097 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0097 }
            goto L_0x0082
        L_0x006d:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r2 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x0073 }
            r2.<init>(r3, r1, r3)     // Catch:{ all -> 0x0073 }
            throw r2     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r1)     // Catch:{ all -> 0x007a }
            throw r2     // Catch:{ all -> 0x007a }
        L_0x007a:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x007c }
        L_0x007c:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x0097 }
            throw r2     // Catch:{ all -> 0x0097 }
        L_0x0081:
            r0 = r3
        L_0x0082:
            if (r0 == 0) goto L_0x008b
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0097 }
            java.lang.Object r0 = kotlin.Result.m190constructorimpl(r0)     // Catch:{ all -> 0x0097 }
            goto L_0x00a2
        L_0x008b:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x0097 }
            r0.<init>(r3, r1, r3)     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x0091:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories     // Catch:{ all -> 0x0097 }
            r0.<init>()     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x0097:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m190constructorimpl(r0)
        L_0x00a2:
            android.net.Uri r1 = r10.$sourceUri
            java.lang.Object r11 = r11.m163mapErrorKWTtemM(r0, r1)
            kotlin.Result r11 = kotlin.Result.m189boximpl(r11)
            return r11
        L_0x00ad:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
