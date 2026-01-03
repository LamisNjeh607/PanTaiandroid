package io.ionic.libs.ionfilesystemlib.helper;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$2", f = "IONFILEDirectoriesHelper.kt", i = {}, l = {102, 105}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEDirectoriesHelper.kt */
final class IONFILEDirectoriesHelper$moveDirectory$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ String $destinationPath;
    final /* synthetic */ String $sourcePath;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ IONFILEDirectoriesHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEDirectoriesHelper$moveDirectory$2(String str, String str2, IONFILEDirectoriesHelper iONFILEDirectoriesHelper, Continuation<? super IONFILEDirectoriesHelper$moveDirectory$2> continuation) {
        super(2, continuation);
        this.$sourcePath = str;
        this.$destinationPath = str2;
        this.this$0 = iONFILEDirectoriesHelper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEDirectoriesHelper$moveDirectory$2 iONFILEDirectoriesHelper$moveDirectory$2 = new IONFILEDirectoriesHelper$moveDirectory$2(this.$sourcePath, this.$destinationPath, this.this$0, continuation);
        iONFILEDirectoriesHelper$moveDirectory$2.L$0 = obj;
        return iONFILEDirectoriesHelper$moveDirectory$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((IONFILEDirectoriesHelper$moveDirectory$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00af A[Catch:{ all -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b0 A[Catch:{ all -> 0x00d5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0034
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x00d5 }
            kotlin.Result r10 = (kotlin.Result) r10     // Catch:{ all -> 0x00d5 }
            java.lang.Object r10 = r10.m199unboximpl()     // Catch:{ all -> 0x00d5 }
            goto L_0x00a9
        L_0x001a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0022:
            java.lang.Object r1 = r9.L$1
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r1 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper) r1
            java.lang.Object r5 = r9.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x00d5 }
            kotlin.Result r10 = (kotlin.Result) r10     // Catch:{ all -> 0x00d5 }
            java.lang.Object r10 = r10.m199unboximpl()     // Catch:{ all -> 0x00d5 }
            goto L_0x0091
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            java.lang.String r5 = r9.$sourcePath
            java.lang.String r10 = r9.$destinationPath
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r1 = r9.this$0
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x00d5 }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x00d5 }
            r6.<init>(r5)     // Catch:{ all -> 0x00d5 }
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x00d5 }
            r7.<init>(r10)     // Catch:{ all -> 0x00d5 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x00d5 }
            if (r8 != 0) goto L_0x00ce
            boolean r8 = r6.exists()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x00c8
            boolean r8 = r6.isFile()     // Catch:{ all -> 0x00d5 }
            if (r8 != 0) goto L_0x00c2
            boolean r8 = r7.isFile()     // Catch:{ all -> 0x00d5 }
            if (r8 != 0) goto L_0x00c2
            java.io.File r8 = r7.getParentFile()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x0078
            boolean r8 = r8.exists()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$NoParentDirectory r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$NoParentDirectory     // Catch:{ all -> 0x00d5 }
            r10.<init>()     // Catch:{ all -> 0x00d5 }
            throw r10     // Catch:{ all -> 0x00d5 }
        L_0x0078:
            boolean r8 = r7.isDirectory()     // Catch:{ all -> 0x00d5 }
            if (r8 != 0) goto L_0x00bc
            boolean r6 = r6.renameTo(r7)     // Catch:{ all -> 0x00d5 }
            if (r6 != 0) goto L_0x00ce
            r9.L$0 = r5     // Catch:{ all -> 0x00d5 }
            r9.L$1 = r1     // Catch:{ all -> 0x00d5 }
            r9.label = r3     // Catch:{ all -> 0x00d5 }
            java.lang.Object r10 = r1.m168copyDirectory0E7RQCE(r5, r10, r9)     // Catch:{ all -> 0x00d5 }
            if (r10 != r0) goto L_0x0091
            return r0
        L_0x0091:
            java.lang.Throwable r10 = kotlin.Result.m193exceptionOrNullimpl(r10)     // Catch:{ all -> 0x00d5 }
            if (r10 != 0) goto L_0x00b6
            io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions     // Catch:{ all -> 0x00d5 }
            r10.<init>(r3)     // Catch:{ all -> 0x00d5 }
            r9.L$0 = r4     // Catch:{ all -> 0x00d5 }
            r9.L$1 = r4     // Catch:{ all -> 0x00d5 }
            r9.label = r2     // Catch:{ all -> 0x00d5 }
            java.lang.Object r10 = r1.m170deleteDirectory0E7RQCE(r5, r10, r9)     // Catch:{ all -> 0x00d5 }
            if (r10 != r0) goto L_0x00a9
            return r0
        L_0x00a9:
            java.lang.Throwable r10 = kotlin.Result.m193exceptionOrNullimpl(r10)     // Catch:{ all -> 0x00d5 }
            if (r10 != 0) goto L_0x00b0
            goto L_0x00ce
        L_0x00b0:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x00d5 }
            r0.<init>(r10)     // Catch:{ all -> 0x00d5 }
            throw r0     // Catch:{ all -> 0x00d5 }
        L_0x00b6:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x00d5 }
            r0.<init>(r10)     // Catch:{ all -> 0x00d5 }
            throw r0     // Catch:{ all -> 0x00d5 }
        L_0x00bc:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$DestinationDirectoryExists r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$DestinationDirectoryExists     // Catch:{ all -> 0x00d5 }
            r0.<init>(r10)     // Catch:{ all -> 0x00d5 }
            throw r0     // Catch:{ all -> 0x00d5 }
        L_0x00c2:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories     // Catch:{ all -> 0x00d5 }
            r10.<init>()     // Catch:{ all -> 0x00d5 }
            throw r10     // Catch:{ all -> 0x00d5 }
        L_0x00c8:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist     // Catch:{ all -> 0x00d5 }
            r10.<init>(r5, r4, r2, r4)     // Catch:{ all -> 0x00d5 }
            throw r10     // Catch:{ all -> 0x00d5 }
        L_0x00ce:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d5 }
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)     // Catch:{ all -> 0x00d5 }
            goto L_0x00e0
        L_0x00d5:
            r10 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
        L_0x00e0:
            kotlin.Result r10 = kotlin.Result.m189boximpl(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
