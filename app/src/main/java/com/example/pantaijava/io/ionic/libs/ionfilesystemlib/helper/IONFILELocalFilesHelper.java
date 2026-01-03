package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00042\u0006\u0010\f\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0013J,\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0018H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001c2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u001dJ+\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001c2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b J,\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\nJ,\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020$H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/helper/IONFILELocalFilesHelper;", "", "()V", "copyFile", "Lkotlin/Result;", "", "sourcePath", "", "destinationPath", "copyFile-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFile", "fullPath", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;", "createFile-0E7RQCE", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFile", "deleteFile-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileMetadata", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "getFileMetadata-gIAlu-s", "readFile", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;", "readFile-0E7RQCE", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFileInChunks", "Lkotlinx/coroutines/flow/Flow;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "bufferSize", "", "readFileInChunks$IONFilesystemLib_release", "renameFile", "renameFile-0E7RQCE", "saveFile", "Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveOptions;", "saveFile-0E7RQCE", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILELocalFilesHelper.kt */
public final class IONFILELocalFilesHelper {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: readFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m177readFile0E7RQCE(java.lang.String r6, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFile$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r6 = r8.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m177readFile0E7RQCE(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<String> readFileInChunks(String str, IONFILEReadInChunksOptions iONFILEReadInChunksOptions) {
        Intrinsics.checkNotNullParameter(str, "fullPath");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        return readFileInChunks$IONFilesystemLib_release(str, iONFILEReadInChunksOptions, 8192);
    }

    public final Flow<String> readFileInChunks$IONFilesystemLib_release(String str, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, int i) {
        Intrinsics.checkNotNullParameter(str, "fullPath");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        return FlowKt.flowOn(FlowKt.flow(new IONFILELocalFilesHelper$readFileInChunks$1(str, iONFILEReadInChunksOptions, i, (Continuation<? super IONFILELocalFilesHelper$readFileInChunks$1>) null)), Dispatchers.getIO());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: getFileMetadata-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m176getFileMetadatagIAlus(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Result<io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$2
            r4 = 0
            r2.<init>(r6, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r6 = r7.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m176getFileMetadatagIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: createFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m174createFile0E7RQCE(java.lang.String r6, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$createFile$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r6 = r8.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m174createFile0E7RQCE(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: saveFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m179saveFile0E7RQCE(java.lang.String r6, io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$saveFile$2
            r4 = 0
            r2.<init>(r6, r5, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r6 = r8.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m179saveFile0E7RQCE(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: deleteFile-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m175deleteFilegIAlus(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$deleteFile$2
            r4 = 0
            r2.<init>(r6, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r6 = r7.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m175deleteFilegIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: copyFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m173copyFile0E7RQCE(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r6 = r8.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m173copyFile0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: renameFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m178renameFile0E7RQCE(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$renameFile$2
            r4 = 0
            r2.<init>(r6, r7, r5, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r6 = r8.m199unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper.m178renameFile0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
