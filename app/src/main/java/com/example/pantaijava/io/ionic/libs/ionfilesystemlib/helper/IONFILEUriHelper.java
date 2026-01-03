package io.ionic.libs.ionfilesystemlib.helper;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import io.ionic.libs.ionfilesystemlib.model.LocalUriType;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0002J,\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u000e2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0016H@¢\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u0004\u0018\u00010\u0006*\u0004\u0018\u00010'H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \t*\u0004\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"Lio/ionic/libs/ionfilesystemlib/helper/IONFILEUriHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "externalCacheDir", "Ljava/io/File;", "externalFilesDir", "internalCacheDir", "kotlin.jvm.PlatformType", "internalFilesDir", "convertSyntheticToContentUri", "Landroid/net/Uri;", "path", "", "getLocalFileObject", "parentFolderFileObject", "localPath", "getLocalUriType", "Lio/ionic/libs/ionfilesystemlib/model/LocalUriType;", "localFileObject", "isInExternalStorage", "", "localFilePath", "resolveAsContentUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Content;", "uri", "resolveAsLocalFile", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Local;", "assumeExternalStorage", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/Boolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveUri", "Lkotlin/Result;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "unresolvedUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "resolveUri-gIAlu-s", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFolderFileObject", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEUriHelper.kt */
public final class IONFILEUriHelper {
    private final File externalCacheDir;
    private final File externalFilesDir;
    private final File internalCacheDir;
    private final File internalFilesDir;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEUriHelper.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType[] r0 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.INTERNAL_CACHE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.INTERNAL_FILES     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.EXTERNAL_CACHE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.EXTERNAL_FILES     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.EXTERNAL_STORAGE     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.DOCUMENTS     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper.WhenMappings.<clinit>():void");
        }
    }

    public IONFILEUriHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.internalCacheDir = context.getCacheDir();
        this.internalFilesDir = context.getFilesDir();
        this.externalCacheDir = context.getExternalCacheDir();
        this.externalFilesDir = context.getExternalFilesDir((String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: resolveUri-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m180resolveUrigIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Unresolved r9, kotlin.coroutines.Continuation<? super kotlin.Result<? extends io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved>> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveUri$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveUri$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveUri$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveUri$1
            r0.<init>(r8, r10)
        L_0x0019:
            r5 = r0
            java.lang.Object r10 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 3
            r3 = 1
            r4 = 2
            if (r1 == 0) goto L_0x0044
            if (r1 == r3) goto L_0x003f
            if (r1 == r4) goto L_0x003a
            if (r1 != r2) goto L_0x0032
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0103 }
            goto L_0x00fb
        L_0x0032:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0103 }
            goto L_0x00d2
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0103 }
            goto L_0x00b6
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.Result$Companion r10 = kotlin.Result.Companion     // Catch:{ all -> 0x0103 }
            r10 = r8
            io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper r10 = (io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper) r10     // Catch:{ all -> 0x0103 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r10 = r9.getParentFolder()     // Catch:{ all -> 0x0103 }
            java.io.File r10 = r8.getFolderFileObject(r10)     // Catch:{ all -> 0x0103 }
            r1 = 0
            if (r10 != 0) goto L_0x00e0
            java.lang.String r10 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ all -> 0x0103 }
            java.lang.String r2 = r10.getScheme()     // Catch:{ all -> 0x0103 }
            java.lang.String r6 = "content"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r6)     // Catch:{ all -> 0x0103 }
            if (r2 == 0) goto L_0x0073
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Content r9 = r8.resolveAsContentUri(r10)     // Catch:{ all -> 0x0103 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9     // Catch:{ all -> 0x0103 }
            goto L_0x00fe
        L_0x0073:
            java.lang.String r2 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x0103 }
            java.lang.String r6 = "/synthetic/"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x0103 }
            r7 = 0
            boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (java.lang.CharSequence) r6, (boolean) r7, (int) r4, (java.lang.Object) r1)     // Catch:{ all -> 0x0103 }
            if (r1 == 0) goto L_0x0093
            java.lang.String r9 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            android.net.Uri r9 = r8.convertSyntheticToContentUri(r9)     // Catch:{ all -> 0x0103 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved$Content r9 = r8.resolveAsContentUri(r9)     // Catch:{ all -> 0x0103 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9     // Catch:{ all -> 0x0103 }
            goto L_0x00fe
        L_0x0093:
            java.lang.String r1 = r10.getScheme()     // Catch:{ all -> 0x0103 }
            java.lang.String r2 = "file"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0103 }
            if (r1 == 0) goto L_0x00ba
            java.lang.String r9 = r10.getPath()     // Catch:{ all -> 0x0103 }
            if (r9 != 0) goto L_0x00a7
            java.lang.String r9 = ""
        L_0x00a7:
            r5.label = r3     // Catch:{ all -> 0x0103 }
            r2 = 0
            r4 = 0
            r6 = 4
            r7 = 0
            r1 = r8
            r3 = r9
            java.lang.Object r10 = resolveAsLocalFile$default(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0103 }
            if (r10 != r0) goto L_0x00b6
            return r0
        L_0x00b6:
            r9 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9     // Catch:{ all -> 0x0103 }
            goto L_0x00fe
        L_0x00ba:
            java.lang.String r10 = r10.getScheme()     // Catch:{ all -> 0x0103 }
            if (r10 != 0) goto L_0x00d6
            java.lang.String r3 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            r5.label = r4     // Catch:{ all -> 0x0103 }
            r2 = 0
            r4 = 0
            r6 = 4
            r7 = 0
            r1 = r8
            java.lang.Object r10 = resolveAsLocalFile$default(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0103 }
            if (r10 != r0) goto L_0x00d2
            return r0
        L_0x00d2:
            r9 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9     // Catch:{ all -> 0x0103 }
            goto L_0x00fe
        L_0x00d6:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnresolvableUri r10 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnresolvableUri     // Catch:{ all -> 0x0103 }
            java.lang.String r9 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            r10.<init>(r9)     // Catch:{ all -> 0x0103 }
            throw r10     // Catch:{ all -> 0x0103 }
        L_0x00e0:
            java.lang.String r3 = r9.getUriPath()     // Catch:{ all -> 0x0103 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r9 = r9.getParentFolder()     // Catch:{ all -> 0x0103 }
            if (r9 == 0) goto L_0x00f2
            boolean r9 = r9.getInExternalStorage()     // Catch:{ all -> 0x0103 }
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)     // Catch:{ all -> 0x0103 }
        L_0x00f2:
            r5.label = r2     // Catch:{ all -> 0x0103 }
            java.lang.Object r10 = r8.resolveAsLocalFile(r10, r3, r1, r5)     // Catch:{ all -> 0x0103 }
            if (r10 != r0) goto L_0x00fb
            return r0
        L_0x00fb:
            r9 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r9 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r9     // Catch:{ all -> 0x0103 }
        L_0x00fe:
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)     // Catch:{ all -> 0x0103 }
            goto L_0x010e
        L_0x0103:
            r9 = move-exception
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)
        L_0x010e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper.m180resolveUrigIAlus(io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final IONFILEUri.Resolved.Content resolveAsContentUri(Uri uri) {
        return new IONFILEUri.Resolved.Content(uri);
    }

    static /* synthetic */ Object resolveAsLocalFile$default(IONFILEUriHelper iONFILEUriHelper, File file, String str, Boolean bool, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        return iONFILEUriHelper.resolveAsLocalFile(file, str, bool, continuation);
    }

    /* access modifiers changed from: private */
    public final Object resolveAsLocalFile(File file, String str, Boolean bool, Continuation<? super IONFILEUri.Resolved.Local> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IONFILEUriHelper$resolveAsLocalFile$2(this, file, str, bool, (Continuation<? super IONFILEUriHelper$resolveAsLocalFile$2>) null), continuation);
    }

    private final Uri convertSyntheticToContentUri(String str) {
        CharSequence charSequence = str;
        int lastIndexOf$default = StringsKt.lastIndexOf$default(charSequence, "/synthetic/", 0, false, 6, (Object) null) + 11;
        int lastIndexOf$default2 = StringsKt.lastIndexOf$default(charSequence, '.', 0, false, 6, (Object) null);
        if (lastIndexOf$default2 >= lastIndexOf$default) {
            String substring = str.substring(lastIndexOf$default, lastIndexOf$default2);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return Uri.parse("content://media/" + substring);
        }
        throw new IONFILEExceptions.UnresolvableUri(str);
    }

    /* access modifiers changed from: private */
    public final File getLocalFileObject(File file, String str) {
        File file2;
        if (file == null) {
            file2 = new File(str);
        }
        return file2;
    }

    /* access modifiers changed from: private */
    public final LocalUriType getLocalUriType(File file) {
        try {
            if (!file.exists()) {
                return LocalUriType.UNKNOWN;
            }
            if (file.isDirectory()) {
                return LocalUriType.DIRECTORY;
            }
            if (file.isFile()) {
                return LocalUriType.FILE;
            }
            return LocalUriType.UNKNOWN;
        } catch (SecurityException unused) {
            return LocalUriType.UNKNOWN;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isInExternalStorage(java.lang.String r8) {
        /*
            r7 = this;
            kotlin.enums.EnumEntries r0 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.getEntries()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0035
            java.lang.Object r1 = r0.next()
            r3 = r1
            io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r3 = (io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType) r3
            java.io.File r3 = r7.getFolderFileObject(r3)
            r4 = 0
            if (r3 == 0) goto L_0x0032
            r5 = r8
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.String r6 = "getAbsolutePath(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r6 = 2
            boolean r4 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (java.lang.CharSequence) r3, (boolean) r4, (int) r6, (java.lang.Object) r2)
        L_0x0032:
            if (r4 == 0) goto L_0x000a
            r2 = r1
        L_0x0035:
            io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r2 = (io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType) r2
            if (r2 == 0) goto L_0x003e
            boolean r8 = r2.getInExternalStorage()
            goto L_0x003f
        L_0x003e:
            r8 = 1
        L_0x003f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper.isInExternalStorage(java.lang.String):boolean");
    }

    private final File getFolderFileObject(IONFILEFolderType iONFILEFolderType) {
        switch (iONFILEFolderType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[iONFILEFolderType.ordinal()]) {
            case -1:
                return null;
            case 1:
                return this.internalCacheDir;
            case 2:
                return this.internalFilesDir;
            case 3:
                return this.externalCacheDir;
            case 4:
                return this.externalFilesDir;
            case 5:
                return Environment.getExternalStorageDirectory();
            case 6:
                return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
