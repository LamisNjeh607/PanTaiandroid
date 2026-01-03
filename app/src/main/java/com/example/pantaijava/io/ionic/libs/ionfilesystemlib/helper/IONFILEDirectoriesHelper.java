package io.ionic.libs.ionfilesystemlib.helper;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0012H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J*\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00042\u0006\u0010\f\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\n\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/helper/IONFILEDirectoriesHelper;", "", "()V", "copyDirectory", "Lkotlin/Result;", "", "sourcePath", "", "destinationPath", "copyDirectory-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createDirectory", "fullPath", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;", "createDirectory-0E7RQCE", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDirectory", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;", "deleteDirectory-0E7RQCE", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listDirectory", "", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "listDirectory-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveDirectory", "moveDirectory-0E7RQCE", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEDirectoriesHelper.kt */
public final class IONFILEDirectoriesHelper {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: listDirectory-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m171listDirectorygIAlus(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult>>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$2
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper.m171listDirectorygIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: createDirectory-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m169createDirectory0E7RQCE(java.lang.String r6, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$createDirectory$2
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper.m169createDirectory0E7RQCE(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: deleteDirectory-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m170deleteDirectory0E7RQCE(java.lang.String r6, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$deleteDirectory$2
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper.m170deleteDirectory0E7RQCE(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: copyDirectory-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m168copyDirectory0E7RQCE(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$copyDirectory$2
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper.m168copyDirectory0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: moveDirectory-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m172moveDirectory0E7RQCE(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$moveDirectory$2
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper.m172moveDirectory0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
