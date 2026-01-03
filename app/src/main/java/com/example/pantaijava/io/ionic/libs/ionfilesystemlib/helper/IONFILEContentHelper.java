package io.ionic.libs.ionfilesystemlib.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000f\u001a\u00020\tH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00062\u0006\u0010\u000f\u001a\u00020\tH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0011J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\tH\u0002J,\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\"2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020#J!\u0010$\u001a\u0004\u0018\u00010%*\u00020\u00152\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0'H\u0002¢\u0006\u0002\u0010(J3\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u0006\"\u0004\b\u0000\u0010**\b\u0012\u0004\u0012\u0002H*0\u00062\u0006\u0010\u000f\u001a\u00020\tH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,J\u0014\u0010)\u001a\u00020-*\u00020-2\u0006\u0010\u000f\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Lio/ionic/libs/ionfilesystemlib/helper/IONFILEContentHelper;", "", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;)V", "copyFile", "Lkotlin/Result;", "", "sourceUri", "Landroid/net/Uri;", "destinationPath", "", "copyFile-0E7RQCE", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFile", "uri", "deleteFile-gIAlu-s", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCreatedTimestampForContentUri", "", "cursor", "Landroid/database/Cursor;", "getFileMetadata", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "getFileMetadata-gIAlu-s", "getLastModifiedTimestampForContentUri", "getNameForContentUri", "getSizeForContentUri", "readFile", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;", "readFile-0E7RQCE", "(Landroid/net/Uri;Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFileInChunks", "Lkotlinx/coroutines/flow/Flow;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "getColumnIndexForNames", "", "columnNames", "", "(Landroid/database/Cursor;Ljava/util/List;)Ljava/lang/Integer;", "mapError", "T", "mapError-KWTtemM", "(Ljava/lang/Object;Landroid/net/Uri;)Ljava/lang/Object;", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEContentHelper.kt */
public final class IONFILEContentHelper {
    /* access modifiers changed from: private */
    public final ContentResolver contentResolver;

    public IONFILEContentHelper(ContentResolver contentResolver2) {
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        this.contentResolver = contentResolver2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: readFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m167readFile0E7RQCE(android.net.Uri r6, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$readFile$2
            r4 = 0
            r2.<init>(r5, r6, r7, r4)
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper.m167readFile0E7RQCE(android.net.Uri, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<String> readFileInChunks(Uri uri, IONFILEReadInChunksOptions iONFILEReadInChunksOptions) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        return FlowKt.m1750catch(FlowKt.flowOn(FlowKt.flow(new IONFILEContentHelper$readFileInChunks$1(this, uri, iONFILEReadInChunksOptions, (Continuation<? super IONFILEContentHelper$readFileInChunks$1>) null)), Dispatchers.getIO()), new IONFILEContentHelper$readFileInChunks$2(this, uri, (Continuation<? super IONFILEContentHelper$readFileInChunks$2>) null));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: getFileMetadata-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m166getFileMetadatagIAlus(android.net.Uri r6, kotlin.coroutines.Continuation<? super kotlin.Result<io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$2
            r4 = 0
            r2.<init>(r5, r6, r4)
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper.m166getFileMetadatagIAlus(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: deleteFile-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m165deleteFilegIAlus(android.net.Uri r6, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$2
            r4 = 0
            r2.<init>(r5, r6, r4)
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper.m165deleteFilegIAlus(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: copyFile-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m164copyFile0E7RQCE(android.net.Uri r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$1
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
            io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$2 r2 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$copyFile$2
            r4 = 0
            r2.<init>(r5, r6, r7, r4)
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
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper.m164copyFile0E7RQCE(android.net.Uri, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final String getNameForContentUri(Cursor cursor) {
        String str;
        Integer columnIndexForNames = getColumnIndexForNames(cursor, CollectionsKt.listOf("_display_name", "_display_name", "_display_name"));
        if (columnIndexForNames != null) {
            columnIndexForNames.intValue();
            str = cursor.getString(columnIndexForNames.intValue());
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long getSizeForContentUri(android.database.Cursor r5, android.net.Uri r6) {
        /*
            r4 = this;
            java.lang.String r0 = "_size"
            int r0 = r5.getColumnIndex(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r1 = 0
            if (r0 < 0) goto L_0x0021
            java.lang.String r5 = r5.getString(r0)
            java.lang.String r0 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            java.lang.Long r5 = kotlin.text.StringsKt.toLongOrNull(r5)
            goto L_0x0022
        L_0x0021:
            r5 = r1
        L_0x0022:
            if (r5 == 0) goto L_0x0029
            long r5 = r5.longValue()
            goto L_0x0055
        L_0x0029:
            android.content.ContentResolver r5 = r4.contentResolver
            java.lang.String r0 = "r"
            android.content.res.AssetFileDescriptor r5 = r5.openAssetFileDescriptor(r6, r0)
            if (r5 == 0) goto L_0x004c
            java.io.Closeable r5 = (java.io.Closeable) r5
            r6 = r5
            android.content.res.AssetFileDescriptor r6 = (android.content.res.AssetFileDescriptor) r6     // Catch:{ all -> 0x0045 }
            long r2 = r6.getLength()     // Catch:{ all -> 0x0045 }
            java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0045 }
            kotlin.io.CloseableKt.closeFinally(r5, r1)
            r1 = r6
            goto L_0x004c
        L_0x0045:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r6)
            throw r0
        L_0x004c:
            if (r1 == 0) goto L_0x0053
            long r5 = r1.longValue()
            goto L_0x0055
        L_0x0053:
            r5 = 0
        L_0x0055:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper.getSizeForContentUri(android.database.Cursor, android.net.Uri):long");
    }

    /* access modifiers changed from: private */
    public final long getLastModifiedTimestampForContentUri(Cursor cursor) {
        Integer columnIndexForNames = getColumnIndexForNames(cursor, CollectionsKt.listOf("date_modified", "last_modified"));
        if (columnIndexForNames != null) {
            columnIndexForNames.intValue();
            String string = cursor.getString(columnIndexForNames.intValue());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Long longOrNull = StringsKt.toLongOrNull(string);
            if (longOrNull != null) {
                return longOrNull.longValue();
            }
        }
        return getCreatedTimestampForContentUri(cursor);
    }

    /* access modifiers changed from: private */
    public final long getCreatedTimestampForContentUri(Cursor cursor) {
        Integer columnIndexForNames = getColumnIndexForNames(cursor, CollectionsKt.listOf("datetaken", "date_added"));
        if (columnIndexForNames != null) {
            columnIndexForNames.intValue();
            String string = cursor.getString(columnIndexForNames.intValue());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Long longOrNull = StringsKt.toLongOrNull(string);
            if (longOrNull != null) {
                return longOrNull.longValue();
            }
        }
        return 0;
    }

    private final Integer getColumnIndexForNames(Cursor cursor, List<String> list) {
        Integer num;
        Iterator it = list.iterator();
        do {
            num = null;
            if (!it.hasNext()) {
                break;
            }
            Integer valueOf = Integer.valueOf(cursor.getColumnIndex((String) it.next()));
            if (valueOf.intValue() >= 0) {
                num = valueOf;
                continue;
            }
        } while (num == null);
        return num;
    }

    /* access modifiers changed from: private */
    /* renamed from: mapError-KWTtemM  reason: not valid java name */
    public final <T> Object m163mapErrorKWTtemM(Object obj, Uri uri) {
        Throwable r0 = Result.m193exceptionOrNullimpl(obj);
        if (r0 == null) {
            return obj;
        }
        Result.Companion companion = Result.Companion;
        return Result.m190constructorimpl(ResultKt.createFailure(mapError(r0, uri)));
    }

    /* access modifiers changed from: private */
    public final Throwable mapError(Throwable th, Uri uri) {
        if (!(th instanceof FileNotFoundException)) {
            return th instanceof UnsupportedOperationException ? new IONFILEExceptions.UnknownError(th) : th;
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return new IONFILEExceptions.DoesNotExist(uri2, th);
    }
}
