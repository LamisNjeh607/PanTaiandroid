package io.ionic.libs.ionfilesystemlib.helper.common;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEFileType;
import io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a+\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\u0012\u001a#\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0014H\u0000¢\u0006\u0002\u0010\u0015\u001a\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0001\u001a\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\tH\u0002\u001a\\\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u000726\u0010\u001e\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\t¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\r0\u001fH\bø\u0001\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006$"}, d2 = {"FILE_3GA_EXTENSION", "", "FILE_3GA_MIME_TYPE", "FILE_JAVASCRIPT_EXTENSION", "FILE_JAVASCRIPT_MIME_TYPE", "FILE_MIME_TYPE_FALLBACK", "checkParentDirectory", "", "file", "Ljava/io/File;", "create", "createDirOrFile", "Lkotlin/Result;", "", "fullPath", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;", "isDirectory", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILECreateOptions;Z)Ljava/lang/Object;", "deleteDirOrFile", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILEDeleteOptions;)Ljava/lang/Object;", "getMetadata", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "fileObject", "getMimeType", "prepareForCopyOrRename", "sourcePath", "destinationPath", "forDirectories", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "sourceFileObj", "destinationFileObj", "IONFilesystemLib_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILECommon.kt */
public final class IONFILECommonKt {
    private static final String FILE_3GA_EXTENSION = "3ga";
    private static final String FILE_3GA_MIME_TYPE = "audio/3gpp";
    private static final String FILE_JAVASCRIPT_EXTENSION = "js";
    private static final String FILE_JAVASCRIPT_MIME_TYPE = "text/javascript";
    public static final String FILE_MIME_TYPE_FALLBACK = "application/octet-binary";

    public static final Object createDirOrFile(String str, IONFILECreateOptions iONFILECreateOptions, boolean z) {
        Intrinsics.checkNotNullParameter(str, "fullPath");
        Intrinsics.checkNotNullParameter(iONFILECreateOptions, "options");
        try {
            Result.Companion companion = Result.Companion;
            File file = new File(str);
            if (file.exists()) {
                throw new IONFILEExceptions.CreateFailed.AlreadyExists(str);
            } else if (checkParentDirectory(file, iONFILECreateOptions.getRecursive())) {
                if (z) {
                    if (!file.mkdir()) {
                        throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
                    }
                }
                if (!z) {
                    if (!file.createNewFile()) {
                        throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
                    }
                }
                return Result.m190constructorimpl(Unit.INSTANCE);
            } else {
                throw new IONFILEExceptions.CreateFailed.NoParentDirectory();
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            return Result.m190constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if ((r4.length == 0) != false) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object deleteDirOrFile(java.lang.String r4, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions r5) {
        /*
            java.lang.String r0 = "fullPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x005d }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x005d }
            r0.<init>(r4)     // Catch:{ all -> 0x005d }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x005d }
            r2 = 0
            if (r1 == 0) goto L_0x0056
            boolean r4 = r0.isDirectory()     // Catch:{ all -> 0x005d }
            r1 = 1
            if (r4 == 0) goto L_0x0043
            java.io.File[] r4 = r0.listFiles()     // Catch:{ all -> 0x005d }
            if (r4 == 0) goto L_0x002e
            int r4 = r4.length     // Catch:{ all -> 0x005d }
            r3 = 0
            if (r4 != 0) goto L_0x002b
            r4 = r1
            goto L_0x002c
        L_0x002b:
            r4 = r3
        L_0x002c:
            if (r4 == 0) goto L_0x002f
        L_0x002e:
            r3 = r1
        L_0x002f:
            if (r3 != 0) goto L_0x003e
            boolean r4 = r5.getRecursive()     // Catch:{ all -> 0x005d }
            if (r4 == 0) goto L_0x0038
            goto L_0x003e
        L_0x0038:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DeleteFailed$CannotDeleteChildren r4 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DeleteFailed$CannotDeleteChildren     // Catch:{ all -> 0x005d }
            r4.<init>()     // Catch:{ all -> 0x005d }
            throw r4     // Catch:{ all -> 0x005d }
        L_0x003e:
            boolean r4 = kotlin.io.FilesKt.deleteRecursively(r0)     // Catch:{ all -> 0x005d }
            goto L_0x0047
        L_0x0043:
            boolean r4 = r0.delete()     // Catch:{ all -> 0x005d }
        L_0x0047:
            if (r4 == 0) goto L_0x0050
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005d }
            java.lang.Object r4 = kotlin.Result.m190constructorimpl(r4)     // Catch:{ all -> 0x005d }
            goto L_0x0068
        L_0x0050:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError r4 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$UnknownError     // Catch:{ all -> 0x005d }
            r4.<init>(r2, r1, r2)     // Catch:{ all -> 0x005d }
            throw r4     // Catch:{ all -> 0x005d }
        L_0x0056:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist r5 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist     // Catch:{ all -> 0x005d }
            r0 = 2
            r5.<init>(r4, r2, r0, r2)     // Catch:{ all -> 0x005d }
            throw r5     // Catch:{ all -> 0x005d }
        L_0x005d:
            r4 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m190constructorimpl(r4)
        L_0x0068:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.common.IONFILECommonKt.deleteDirOrFile(java.lang.String, io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions):java.lang.Object");
    }

    public static final IONFILEMetadataResult getMetadata(File file) {
        IONFILEFileType iONFILEFileType;
        Long l;
        Intrinsics.checkNotNullParameter(file, "fileObject");
        String absolutePath = file.getAbsolutePath();
        String name = file.getName();
        long length = file.length();
        Uri fromFile = Uri.fromFile(file);
        if (file.isDirectory()) {
            iONFILEFileType = IONFILEFileType.Directory.INSTANCE;
        } else {
            iONFILEFileType = new IONFILEFileType.File(getMimeType(file));
        }
        IONFILEFileType iONFILEFileType2 = iONFILEFileType;
        if (IONFILEBuildConfig.INSTANCE.getAndroidSdkVersionCode() > 26) {
            BasicFileAttributes readAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]);
            l = Long.valueOf(Math.min(readAttributes.creationTime().toMillis(), readAttributes.lastAccessTime().toMillis()));
        } else {
            l = null;
        }
        long lastModified = file.lastModified();
        Intrinsics.checkNotNull(absolutePath);
        Intrinsics.checkNotNull(name);
        Intrinsics.checkNotNull(fromFile);
        return new IONFILEMetadataResult(absolutePath, name, fromFile, length, iONFILEFileType2, l, lastModified);
    }

    public static final void prepareForCopyOrRename(String str, String str2, boolean z, Function2<? super File, ? super File, Unit> function2) {
        Intrinsics.checkNotNullParameter(str, "sourcePath");
        Intrinsics.checkNotNullParameter(str2, "destinationPath");
        Intrinsics.checkNotNullParameter(function2, "block");
        File file = new File(str);
        File file2 = new File(str2);
        if (!Intrinsics.areEqual((Object) file, (Object) file2)) {
            if (!file.exists()) {
                throw new IONFILEExceptions.DoesNotExist(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
            } else if (!z && (file.isDirectory() || file2.isDirectory())) {
                throw new IONFILEExceptions.CopyRenameFailed.MixingFilesAndDirectories();
            } else if (!z || (!file.isFile() && !file2.isFile())) {
                File parentFile = file2.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    throw new IONFILEExceptions.CopyRenameFailed.NoParentDirectory();
                } else if (!z || !file2.isDirectory()) {
                    function2.invoke(file, file2);
                } else {
                    throw new IONFILEExceptions.CopyRenameFailed.DestinationDirectoryExists(str2);
                }
            } else {
                throw new IONFILEExceptions.CopyRenameFailed.MixingFilesAndDirectories();
            }
        }
    }

    private static final String getMimeType(File file) {
        CharSequence extension = FilesKt.getExtension(file);
        if (StringsKt.isBlank(extension)) {
            extension = file.getPath();
        }
        String str = (String) extension;
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        if (mimeTypeFromExtension != null) {
            return mimeTypeFromExtension;
        }
        if (Intrinsics.areEqual((Object) str, (Object) FILE_3GA_EXTENSION)) {
            return FILE_3GA_MIME_TYPE;
        }
        if (Intrinsics.areEqual((Object) str, (Object) FILE_JAVASCRIPT_EXTENSION)) {
            return FILE_JAVASCRIPT_MIME_TYPE;
        }
        return FILE_MIME_TYPE_FALLBACK;
    }

    private static final boolean checkParentDirectory(File file, boolean z) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        if (parentFile.exists() || (z && parentFile.mkdirs())) {
            return true;
        }
        return false;
    }
}
