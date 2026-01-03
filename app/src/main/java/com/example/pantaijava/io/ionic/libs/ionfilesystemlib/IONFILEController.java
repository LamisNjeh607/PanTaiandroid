package io.ionic.libs.ionfilesystemlib;

import io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper;
import io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper;
import io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper;
import io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B'\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ,\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001bJ,\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020 H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"J$\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u000f2\u0006\u0010\u0017\u001a\u00020%H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J$\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u000f2\u0006\u0010\u0017\u001a\u00020\u0012H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+J*\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0-0\u000f2\u0006\u0010\u0017\u001a\u00020\u0012H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010+J,\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u0010\u0015J,\u00101\u001a\b\u0012\u0004\u0012\u0002020\u000f2\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u000203H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00105J\u001c\u00106\u001a\b\u0012\u0004\u0012\u000202072\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u000208J,\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020:H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u0010<R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"Lio/ionic/libs/ionfilesystemlib/IONFILEController;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "uriHelper", "Lio/ionic/libs/ionfilesystemlib/helper/IONFILEUriHelper;", "localFilesHelper", "Lio/ionic/libs/ionfilesystemlib/helper/IONFILELocalFilesHelper;", "directoriesHelper", "Lio/ionic/libs/ionfilesystemlib/helper/IONFILEDirectoriesHelper;", "contentResolverHelper", "Lio/ionic/libs/ionfilesystemlib/helper/IONFILEContentHelper;", "(Lio/ionic/libs/ionfilesystemlib/helper/IONFILEUriHelper;Lio/ionic/libs/ionfilesystemlib/helper/IONFILELocalFilesHelper;Lio/ionic/libs/ionfilesystemlib/helper/IONFILEDirectoriesHelper;Lio/ionic/libs/ionfilesystemlib/helper/IONFILEContentHelper;)V", "copy", "Lkotlin/Result;", "Landroid/net/Uri;", "source", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;", "destination", "copy-0E7RQCE", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createDirectory", "uri", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;", "createDirectory-0E7RQCE", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFile", "createFile-0E7RQCE", "delete", "", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;", "delete-0E7RQCE", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "getFileUri-gIAlu-s", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetadata", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "getMetadata-gIAlu-s", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listDirectory", "", "listDirectory-gIAlu-s", "move", "move-0E7RQCE", "readFile", "", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;", "readFile-0E7RQCE", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFileInChunks", "Lkotlinx/coroutines/flow/Flow;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "saveFile", "Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveOptions;", "saveFile-0E7RQCE", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEController.kt */
public final class IONFILEController {
    /* access modifiers changed from: private */
    public final IONFILEContentHelper contentResolverHelper;
    private final IONFILEDirectoriesHelper directoriesHelper;
    /* access modifiers changed from: private */
    public final IONFILELocalFilesHelper localFilesHelper;
    /* access modifiers changed from: private */
    public final IONFILEUriHelper uriHelper;

    public IONFILEController(IONFILEUriHelper iONFILEUriHelper, IONFILELocalFilesHelper iONFILELocalFilesHelper, IONFILEDirectoriesHelper iONFILEDirectoriesHelper, IONFILEContentHelper iONFILEContentHelper) {
        Intrinsics.checkNotNullParameter(iONFILEUriHelper, "uriHelper");
        Intrinsics.checkNotNullParameter(iONFILELocalFilesHelper, "localFilesHelper");
        Intrinsics.checkNotNullParameter(iONFILEDirectoriesHelper, "directoriesHelper");
        Intrinsics.checkNotNullParameter(iONFILEContentHelper, "contentResolverHelper");
        this.uriHelper = iONFILEUriHelper;
        this.localFilesHelper = iONFILELocalFilesHelper;
        this.directoriesHelper = iONFILEDirectoriesHelper;
        this.contentResolverHelper = iONFILEContentHelper;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IONFILEController(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper
            r0.<init>(r6)
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r1 = new io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper
            r1.<init>()
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper
            r2.<init>()
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r3 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper
            android.content.ContentResolver r6 = r6.getContentResolver()
            java.lang.String r4 = "getContentResolver(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            r3.<init>(r6)
            r5.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: getFileUri-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m156getFileUrigIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved r5, kotlin.coroutines.Continuation<? super kotlin.Result<? extends io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$getFileUri$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ionic.libs.ionfilesystemlib.IONFILEController$getFileUri$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$getFileUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$getFileUri$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$getFileUri$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r5 = r6.m199unboximpl()
            goto L_0x0046
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r6 = r4.uriHelper
            r0.label = r3
            java.lang.Object r5 = r6.m180resolveUrigIAlus(r5, r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m156getFileUrigIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: createFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m154createFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r7, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r8, kotlin.coroutines.Continuation<? super kotlin.Result<? extends android.net.Uri>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$createFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.ionfilesystemlib.IONFILEController$createFile$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$createFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$createFile$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$createFile$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r8 = r9.m199unboximpl()
            goto L_0x00a3
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0040:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions) r8
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r7 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r9 = r9.m199unboximpl()
            goto L_0x0075
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r9 = r6.uriHelper
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0061
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            r9 = r8
            r8 = r6
            goto L_0x0081
        L_0x0061:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00ea
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r7
            r0.L$0 = r6
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = r9.m180resolveUrigIAlus(r7, r0)
            if (r9 != r1) goto L_0x0074
            return r1
        L_0x0074:
            r7 = r6
        L_0x0075:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r9)
            if (r2 != 0) goto L_0x00df
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r5 = r8
            r8 = r7
            r7 = r9
            r9 = r5
        L_0x0081:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00cd
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r2 = r7.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r4 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r2 == r4) goto L_0x00bb
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r8 = r8.localFilesHelper
            java.lang.String r2 = r7.getFullPath()
            r0.L$0 = r7
            r4 = 0
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r8 = r8.m174createFile0E7RQCE(r2, r9, r0)
            if (r8 != r1) goto L_0x00a3
            return r1
        L_0x00a3:
            boolean r9 = kotlin.Result.m197isSuccessimpl(r8)
            if (r9 == 0) goto L_0x00b6
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            kotlin.Unit r8 = (kotlin.Unit) r8
            android.net.Uri r7 = r7.getUri()
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00b6:
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r8)
            goto L_0x00e9
        L_0x00bb:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00cd:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00df:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
        L_0x00e9:
            return r7
        L_0x00ea:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m154createFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: createDirectory-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m153createDirectory0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r7, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r8, kotlin.coroutines.Continuation<? super kotlin.Result<? extends android.net.Uri>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$createDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.ionfilesystemlib.IONFILEController$createDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$createDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$createDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$createDirectory$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r8 = r9.m199unboximpl()
            goto L_0x00a3
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0040:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions) r8
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r7 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r9 = r9.m199unboximpl()
            goto L_0x0075
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r9 = r6.uriHelper
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0061
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            r9 = r8
            r8 = r6
            goto L_0x0081
        L_0x0061:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00ea
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r7
            r0.L$0 = r6
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = r9.m180resolveUrigIAlus(r7, r0)
            if (r9 != r1) goto L_0x0074
            return r1
        L_0x0074:
            r7 = r6
        L_0x0075:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r9)
            if (r2 != 0) goto L_0x00df
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r5 = r8
            r8 = r7
            r7 = r9
            r9 = r5
        L_0x0081:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00cd
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r2 = r7.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r4 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.FILE
            if (r2 == r4) goto L_0x00bb
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r8 = r8.directoriesHelper
            java.lang.String r2 = r7.getFullPath()
            r0.L$0 = r7
            r4 = 0
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r8 = r8.m169createDirectory0E7RQCE(r2, r9, r0)
            if (r8 != r1) goto L_0x00a3
            return r1
        L_0x00a3:
            boolean r9 = kotlin.Result.m197isSuccessimpl(r8)
            if (r9 == 0) goto L_0x00b6
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            kotlin.Unit r8 = (kotlin.Unit) r8
            android.net.Uri r7 = r7.getUri()
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00b6:
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r8)
            goto L_0x00e9
        L_0x00bb:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForFiles r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForFiles
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00cd:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00df:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
        L_0x00e9:
            return r7
        L_0x00ea:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m153createDirectory0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: readFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m160readFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r9, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r10, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$readFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ionic.libs.ionfilesystemlib.IONFILEController$readFile$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$readFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$readFile$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$readFile$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r5) goto L_0x0040
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            goto L_0x0035
        L_0x002d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.Result r11 = (kotlin.Result) r11
            java.lang.Object r9 = r11.m199unboximpl()
            goto L_0x00d8
        L_0x0040:
            java.lang.Object r9 = r0.L$1
            r10 = r9
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions) r10
            java.lang.Object r9 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r9 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r9
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.Result r11 = (kotlin.Result) r11
            java.lang.Object r11 = r11.m199unboximpl()
            goto L_0x0075
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r11 = r8.uriHelper
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0061
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r11 = r10
            r10 = r8
            goto L_0x0081
        L_0x0061:
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00d9
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r9
            r0.L$0 = r8
            r0.L$1 = r10
            r0.label = r5
            java.lang.Object r11 = r11.m180resolveUrigIAlus(r9, r0)
            if (r11 != r1) goto L_0x0074
            return r1
        L_0x0074:
            r9 = r8
        L_0x0075:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r11)
            if (r2 != 0) goto L_0x00ce
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r11 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r11
            r7 = r10
            r10 = r9
            r9 = r11
            r11 = r7
        L_0x0081:
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00a3
            r5 = r9
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r5 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r5
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r5 = r5.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r6 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r5 == r6) goto L_0x0091
            goto L_0x00a3
        L_0x0091:
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory r9 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory
            r9.<init>()
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)
            goto L_0x00d8
        L_0x00a3:
            r5 = 0
            if (r2 == 0) goto L_0x00bb
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r10 = r10.localFilesHelper
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r9
            java.lang.String r9 = r9.getFullPath()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r9 = r10.m177readFile0E7RQCE(r9, r11, r0)
            if (r9 != r1) goto L_0x00d8
            return r1
        L_0x00bb:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r10 = r10.contentResolverHelper
            android.net.Uri r9 = r9.getUri()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r9 = r10.m167readFile0E7RQCE(r9, r11, r0)
            if (r9 != r1) goto L_0x00d8
            return r1
        L_0x00ce:
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)
        L_0x00d8:
            return r9
        L_0x00d9:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m160readFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<String> readFileInChunks(IONFILEUri iONFILEUri, IONFILEReadInChunksOptions iONFILEReadInChunksOptions) {
        Intrinsics.checkNotNullParameter(iONFILEUri, "uri");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        return FlowKt.flow(new IONFILEController$readFileInChunks$1(this, iONFILEUri, iONFILEReadInChunksOptions, (Continuation<? super IONFILEController$readFileInChunks$1>) null));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: getMetadata-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m157getMetadatagIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r8, kotlin.coroutines.Continuation<? super kotlin.Result<io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$getMetadata$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.ionfilesystemlib.IONFILEController$getMetadata$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$getMetadata$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$getMetadata$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$getMetadata$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004e
            if (r2 == r5) goto L_0x0040
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            goto L_0x0035
        L_0x002d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r8 = r9.m199unboximpl()
            goto L_0x00ab
        L_0x0040:
            java.lang.Object r8 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r8 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r8
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r9 = r9.m199unboximpl()
            goto L_0x006d
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r9 = r7.uriHelper
            boolean r2 = r8 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x005b
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r8
            r9 = r7
            goto L_0x0078
        L_0x005b:
            boolean r2 = r8 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00ac
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r8
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r9 = r9.m180resolveUrigIAlus(r8, r0)
            if (r9 != r1) goto L_0x006c
            return r1
        L_0x006c:
            r8 = r7
        L_0x006d:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r9)
            if (r2 != 0) goto L_0x00a1
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x0078:
            boolean r2 = r8 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            r5 = 0
            if (r2 == 0) goto L_0x0090
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r9 = r9.localFilesHelper
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r8
            java.lang.String r8 = r8.getFullPath()
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r8 = r9.m176getFileMetadatagIAlus(r8, r0)
            if (r8 != r1) goto L_0x00ab
            return r1
        L_0x0090:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r9 = r9.contentResolverHelper
            android.net.Uri r8 = r8.getUri()
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = r9.m166getFileMetadatagIAlus(r8, r0)
            if (r8 != r1) goto L_0x00ab
            return r1
        L_0x00a1:
            kotlin.Result$Companion r8 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r8 = kotlin.Result.m190constructorimpl(r8)
        L_0x00ab:
            return r8
        L_0x00ac:
            kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m157getMetadatagIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: saveFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m161saveFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r7, io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions r8, kotlin.coroutines.Continuation<? super kotlin.Result<? extends android.net.Uri>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$saveFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.ionfilesystemlib.IONFILEController$saveFile$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$saveFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$saveFile$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$saveFile$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r8 = r9.m199unboximpl()
            goto L_0x00a3
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0040:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions) r8
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r7 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r7
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result r9 = (kotlin.Result) r9
            java.lang.Object r9 = r9.m199unboximpl()
            goto L_0x0075
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r9 = r6.uriHelper
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0061
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            r9 = r8
            r8 = r6
            goto L_0x0081
        L_0x0061:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00ea
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r7
            r0.L$0 = r6
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = r9.m180resolveUrigIAlus(r7, r0)
            if (r9 != r1) goto L_0x0074
            return r1
        L_0x0074:
            r7 = r6
        L_0x0075:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r9)
            if (r2 != 0) goto L_0x00df
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r5 = r8
            r8 = r7
            r7 = r9
            r9 = r5
        L_0x0081:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00cd
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r2 = r7.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r4 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r2 == r4) goto L_0x00bb
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r8 = r8.localFilesHelper
            java.lang.String r2 = r7.getFullPath()
            r0.L$0 = r7
            r4 = 0
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r8 = r8.m179saveFile0E7RQCE(r2, r9, r0)
            if (r8 != r1) goto L_0x00a3
            return r1
        L_0x00a3:
            boolean r9 = kotlin.Result.m197isSuccessimpl(r8)
            if (r9 == 0) goto L_0x00b6
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            kotlin.Unit r8 = (kotlin.Unit) r8
            android.net.Uri r7 = r7.getUri()
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00b6:
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r8)
            goto L_0x00e9
        L_0x00bb:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForDirectory
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00cd:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00e9
        L_0x00df:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
        L_0x00e9:
            return r7
        L_0x00ea:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m161saveFile0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: listDirectory-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m158listDirectorygIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r7, kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult>>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$listDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.IONFILEController$listDirectory$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$listDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$listDirectory$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$listDirectory$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r7 = r8.m199unboximpl()
            goto L_0x00c2
        L_0x0034:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003c:
            java.lang.Object r7 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r7 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r7
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.Result r8 = (kotlin.Result) r8
            java.lang.Object r8 = r8.m199unboximpl()
            goto L_0x0069
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r8 = r6.uriHelper
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0057
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            r8 = r6
            goto L_0x0074
        L_0x0057:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00c3
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r7
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r8 = r8.m180resolveUrigIAlus(r7, r0)
            if (r8 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r7 = r6
        L_0x0069:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r8)
            if (r2 != 0) goto L_0x00b8
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r8 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r8
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0074:
            boolean r2 = r7 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00a6
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r7
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r2 = r7.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r4 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.FILE
            if (r2 == r4) goto L_0x0094
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r8 = r8.directoriesHelper
            java.lang.String r7 = r7.getFullPath()
            r2 = 0
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r8.m171listDirectorygIAlus(r7, r0)
            if (r7 != r1) goto L_0x00c2
            return r1
        L_0x0094:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForFiles r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForFiles
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00c2
        L_0x00a6:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r7 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r7.<init>()
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
            goto L_0x00c2
        L_0x00b8:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r7 = kotlin.Result.m190constructorimpl(r7)
        L_0x00c2:
            return r7
        L_0x00c3:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m158listDirectorygIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: delete-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m155delete0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r9, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions r10, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$delete$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ionic.libs.ionfilesystemlib.IONFILEController$delete$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$delete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$delete$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$delete$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r6) goto L_0x0043
            if (r2 == r5) goto L_0x0038
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            goto L_0x0038
        L_0x0030:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.Result r11 = (kotlin.Result) r11
            java.lang.Object r9 = r11.m199unboximpl()
            goto L_0x00d2
        L_0x0043:
            java.lang.Object r9 = r0.L$1
            r10 = r9
            io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions) r10
            java.lang.Object r9 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r9 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r9
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.Result r11 = (kotlin.Result) r11
            java.lang.Object r11 = r11.m199unboximpl()
            goto L_0x0078
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r11 = r8.uriHelper
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x0064
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9
            r11 = r10
            r10 = r8
            goto L_0x0084
        L_0x0064:
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x00d3
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r9
            r0.L$0 = r8
            r0.L$1 = r10
            r0.label = r6
            java.lang.Object r11 = r11.m180resolveUrigIAlus(r9, r0)
            if (r11 != r1) goto L_0x0077
            return r1
        L_0x0077:
            r9 = r8
        L_0x0078:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r11)
            if (r2 != 0) goto L_0x00c8
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r11 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r11
            r7 = r10
            r10 = r9
            r9 = r11
            r11 = r7
        L_0x0084:
            boolean r2 = r9 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            r6 = 0
            if (r2 == 0) goto L_0x00b5
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r9
            java.lang.String r2 = r9.getFullPath()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r9 = r9.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r3 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r9 != r3) goto L_0x00a6
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r9 = r10.directoriesHelper
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r5
            java.lang.Object r9 = r9.m170deleteDirectory0E7RQCE(r2, r11, r0)
            if (r9 != r1) goto L_0x00d2
            return r1
        L_0x00a6:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r9 = r10.localFilesHelper
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r9 = r9.m175deleteFilegIAlus(r2, r0)
            if (r9 != r1) goto L_0x00d2
            return r1
        L_0x00b5:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r10 = r10.contentResolverHelper
            android.net.Uri r9 = r9.getUri()
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r9 = r10.m165deleteFilegIAlus(r9, r0)
            if (r9 != r1) goto L_0x00d2
            return r1
        L_0x00c8:
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)
        L_0x00d2:
            return r9
        L_0x00d3:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m155delete0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEUri} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* renamed from: copy-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m152copy0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r10, io.ionic.libs.ionfilesystemlib.model.IONFILEUri r11, kotlin.coroutines.Continuation<? super kotlin.Result<? extends android.net.Uri>> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$copy$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ionic.libs.ionfilesystemlib.IONFILEController$copy$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$copy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$copy$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$copy$1
            r0.<init>(r9, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x006f
            if (r2 == r7) goto L_0x005c
            if (r2 == r6) goto L_0x0049
            if (r2 == r5) goto L_0x0032
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x0041
        L_0x0032:
            java.lang.Object r10 = r0.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r10
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r11 = r12.m199unboximpl()
            goto L_0x0152
        L_0x0041:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0049:
            java.lang.Object r10 = r0.L$1
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r10
            java.lang.Object r11 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r11 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r11
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r12 = r12.m199unboximpl()
            goto L_0x00bd
        L_0x005c:
            java.lang.Object r10 = r0.L$1
            r11 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri r11 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri) r11
            java.lang.Object r10 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r10 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r10
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r12 = r12.m199unboximpl()
            goto L_0x0091
        L_0x006f:
            kotlin.ResultKt.throwOnFailure(r12)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r12 = r9.uriHelper
            boolean r2 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x007d
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r10
            r12 = r11
            r11 = r9
            goto L_0x009d
        L_0x007d:
            boolean r2 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x0186
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r10
            r0.L$0 = r9
            r0.L$1 = r11
            r0.label = r7
            java.lang.Object r12 = r12.m180resolveUrigIAlus(r10, r0)
            if (r12 != r1) goto L_0x0090
            return r1
        L_0x0090:
            r10 = r9
        L_0x0091:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r12)
            if (r2 != 0) goto L_0x017b
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
        L_0x009d:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r2 = r11.uriHelper
            boolean r7 = r12 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r7 == 0) goto L_0x00aa
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
        L_0x00a5:
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
            goto L_0x00c6
        L_0x00aa:
            boolean r7 = r12 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r7 == 0) goto L_0x0175
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r12
            r0.L$0 = r11
            r0.L$1 = r10
            r0.label = r6
            java.lang.Object r12 = r2.m180resolveUrigIAlus(r12, r0)
            if (r12 != r1) goto L_0x00bd
            return r1
        L_0x00bd:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r12)
            if (r2 != 0) goto L_0x016a
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
            goto L_0x00a5
        L_0x00c6:
            boolean r2 = r11 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x00e0
            boolean r6 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Content
            if (r6 == 0) goto L_0x00e0
            kotlin.Result$Companion r11 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$LocalToContent r11 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$LocalToContent
            r11.<init>()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m190constructorimpl(r11)
            goto L_0x0152
        L_0x00e0:
            boolean r6 = r11 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Content
            if (r6 == 0) goto L_0x00fa
            boolean r6 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Content
            if (r6 == 0) goto L_0x00fa
            kotlin.Result$Companion r11 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$SourceAndDestinationContent r11 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$CopyRenameFailed$SourceAndDestinationContent
            r11.<init>()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m190constructorimpl(r11)
            goto L_0x0152
        L_0x00fa:
            java.lang.String r6 = "null cannot be cast to non-null type io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local"
            r7 = 0
            if (r2 == 0) goto L_0x0135
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r11 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r11
            java.lang.String r2 = r11.getFullPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r6)
            r3 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r3 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r3
            java.lang.String r3 = r3.getFullPath()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r11 = r11.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r6 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            if (r11 != r6) goto L_0x0126
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r11 = r12.directoriesHelper
            r0.L$0 = r10
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r11 = r11.m168copyDirectory0E7RQCE(r2, r3, r0)
            if (r11 != r1) goto L_0x0152
            return r1
        L_0x0126:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r11 = r12.localFilesHelper
            r0.L$0 = r10
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r11 = r11.m173copyFile0E7RQCE(r2, r3, r0)
            if (r11 != r1) goto L_0x0152
            return r1
        L_0x0135:
            android.net.Uri r11 = r11.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r6)
            r2 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r2 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r2
            java.lang.String r2 = r2.getFullPath()
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper r12 = r12.contentResolverHelper
            r0.L$0 = r10
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r11 = r12.m164copyFile0E7RQCE(r11, r2, r0)
            if (r11 != r1) goto L_0x0152
            return r1
        L_0x0152:
            boolean r12 = kotlin.Result.m197isSuccessimpl(r11)
            if (r12 == 0) goto L_0x0165
            kotlin.Result$Companion r12 = kotlin.Result.Companion
            kotlin.Unit r11 = (kotlin.Unit) r11
            android.net.Uri r10 = r10.getUri()
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0185
        L_0x0165:
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r11)
            goto L_0x0185
        L_0x016a:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0185
        L_0x0175:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        L_0x017b:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
        L_0x0185:
            return r10
        L_0x0186:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m152copy0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILEUri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v37, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEUri} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: move-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m159move0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri r10, io.ionic.libs.ionfilesystemlib.model.IONFILEUri r11, kotlin.coroutines.Continuation<? super kotlin.Result<? extends android.net.Uri>> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof io.ionic.libs.ionfilesystemlib.IONFILEController$move$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ionic.libs.ionfilesystemlib.IONFILEController$move$1 r0 = (io.ionic.libs.ionfilesystemlib.IONFILEController$move$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.IONFILEController$move$1 r0 = new io.ionic.libs.ionfilesystemlib.IONFILEController$move$1
            r0.<init>(r9, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x006c
            if (r2 == r6) goto L_0x0059
            if (r2 == r5) goto L_0x0046
            if (r2 == r4) goto L_0x002f
            if (r2 != r3) goto L_0x003e
        L_0x002f:
            java.lang.Object r10 = r0.L$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r10
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r11 = r12.m199unboximpl()
            goto L_0x00fc
        L_0x003e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0046:
            java.lang.Object r10 = r0.L$1
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r10
            java.lang.Object r11 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r11 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r11
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r12 = r12.m199unboximpl()
            goto L_0x00bc
        L_0x0059:
            java.lang.Object r10 = r0.L$1
            r11 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri r11 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri) r11
            java.lang.Object r10 = r0.L$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r10 = (io.ionic.libs.ionfilesystemlib.IONFILEController) r10
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r12 = r12.m199unboximpl()
            goto L_0x008e
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r12)
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r12 = r9.uriHelper
            boolean r2 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r2 == 0) goto L_0x007a
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r10
            r12 = r11
            r11 = r9
            goto L_0x009a
        L_0x007a:
            boolean r2 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r2 == 0) goto L_0x0154
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r10
            r0.L$0 = r9
            r0.L$1 = r11
            r0.label = r6
            java.lang.Object r12 = r12.m180resolveUrigIAlus(r10, r0)
            if (r12 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r10 = r9
        L_0x008e:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r12)
            if (r2 != 0) goto L_0x0149
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
        L_0x009a:
            boolean r2 = r10 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x0137
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r10 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r10
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r2 = r11.uriHelper
            boolean r6 = r12 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved
            if (r6 == 0) goto L_0x00a9
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
            goto L_0x00c4
        L_0x00a9:
            boolean r6 = r12 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved
            if (r6 == 0) goto L_0x0131
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved) r12
            r0.L$0 = r11
            r0.L$1 = r10
            r0.label = r5
            java.lang.Object r12 = r2.m180resolveUrigIAlus(r12, r0)
            if (r12 != r1) goto L_0x00bc
            return r1
        L_0x00bc:
            java.lang.Throwable r2 = kotlin.Result.m193exceptionOrNullimpl(r12)
            if (r2 != 0) goto L_0x0126
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r12
        L_0x00c4:
            boolean r2 = r12 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local
            if (r2 == 0) goto L_0x0114
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Local r12 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local) r12
            java.lang.String r2 = r10.getFullPath()
            java.lang.String r5 = r12.getFullPath()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r10 = r10.getType()
            io.ionic.libs.ionfilesystemlib.model.LocalUriType r6 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.DIRECTORY
            r7 = 0
            if (r10 != r6) goto L_0x00ec
            io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper r10 = r11.directoriesHelper
            r0.L$0 = r12
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r10 = r10.m172moveDirectory0E7RQCE(r2, r5, r0)
            if (r10 != r1) goto L_0x00ea
            return r1
        L_0x00ea:
            r11 = r10
            goto L_0x00fb
        L_0x00ec:
            io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper r10 = r11.localFilesHelper
            r0.L$0 = r12
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r11 = r10.m178renameFile0E7RQCE(r2, r5, r0)
            if (r11 != r1) goto L_0x00fb
            return r1
        L_0x00fb:
            r10 = r12
        L_0x00fc:
            boolean r12 = kotlin.Result.m197isSuccessimpl(r11)
            if (r12 == 0) goto L_0x010f
            kotlin.Result$Companion r12 = kotlin.Result.Companion
            kotlin.Unit r11 = (kotlin.Unit) r11
            android.net.Uri r10 = r10.getUri()
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0153
        L_0x010f:
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r11)
            goto L_0x0153
        L_0x0114:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r10.<init>()
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0153
        L_0x0126:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0153
        L_0x0131:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        L_0x0137:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$NotSupportedForContentScheme
            r10.<init>()
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
            goto L_0x0153
        L_0x0149:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r10 = kotlin.Result.m190constructorimpl(r10)
        L_0x0153:
            return r10
        L_0x0154:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.IONFILEController.m159move0E7RQCE(io.ionic.libs.ionfilesystemlib.model.IONFILEUri, io.ionic.libs.ionfilesystemlib.model.IONFILEUri, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
