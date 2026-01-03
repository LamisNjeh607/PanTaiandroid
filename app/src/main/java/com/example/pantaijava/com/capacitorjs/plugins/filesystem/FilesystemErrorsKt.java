package com.capacitorjs.plugins.filesystem;

import com.capacitorjs.plugins.filesystem.FilesystemErrors;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"toFilesystemError", "Lcom/capacitorjs/plugins/filesystem/FilesystemErrors$ErrorInfo;", "", "methodName", "", "capacitor-filesystem_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemErrors.kt */
public final class FilesystemErrorsKt {
    public static final FilesystemErrors.ErrorInfo toFilesystemError(Throwable th, String str) {
        boolean z;
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(str, "methodName");
        if (th instanceof IONFILEExceptions.UnresolvableUri) {
            return FilesystemErrors.INSTANCE.invalidPath(((IONFILEExceptions.UnresolvableUri) th).getUri());
        }
        if (th instanceof IONFILEExceptions.DoesNotExist) {
            return FilesystemErrors.INSTANCE.doesNotExist(str, ((IONFILEExceptions.DoesNotExist) th).getPath());
        }
        if (th instanceof IONFILEExceptions.NotSupportedForContentScheme) {
            return FilesystemErrors.INSTANCE.notAllowed(str, "content:// URIs");
        }
        if (th instanceof IONFILEExceptions.NotSupportedForDirectory) {
            return FilesystemErrors.INSTANCE.notAllowed(str, "directories");
        }
        if (th instanceof IONFILEExceptions.NotSupportedForFiles) {
            return FilesystemErrors.INSTANCE.notAllowed(str, "files, only directories are supported");
        }
        if (th instanceof IONFILEExceptions.CreateFailed.AlreadyExists) {
            return FilesystemErrors.INSTANCE.directoryCreationAlreadyExists(((IONFILEExceptions.CreateFailed.AlreadyExists) th).getPath());
        }
        if (th instanceof IONFILEExceptions.CreateFailed.NoParentDirectory) {
            return FilesystemErrors.INSTANCE.getMissingParentDirectories();
        }
        if (th instanceof IONFILEExceptions.DeleteFailed.CannotDeleteChildren) {
            return FilesystemErrors.INSTANCE.getCannotDeleteChildren();
        }
        boolean z2 = true;
        if (th instanceof IONFILEExceptions.CopyRenameFailed.MixingFilesAndDirectories) {
            z = true;
        } else {
            z = th instanceof IONFILEExceptions.CopyRenameFailed.LocalToContent;
        }
        if (!z) {
            z2 = th instanceof IONFILEExceptions.CopyRenameFailed.SourceAndDestinationContent;
        }
        if (z2) {
            return FilesystemErrors.INSTANCE.notAllowed(str, "the provided source and destinations");
        }
        if (th instanceof IONFILEExceptions.CopyRenameFailed.DestinationDirectoryExists) {
            return FilesystemErrors.INSTANCE.directoryCreationAlreadyExists(((IONFILEExceptions.CopyRenameFailed.DestinationDirectoryExists) th).getPath());
        }
        if (th instanceof IONFILEExceptions.CopyRenameFailed.NoParentDirectory) {
            return FilesystemErrors.INSTANCE.getMissingParentDirectories();
        }
        FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        return filesystemErrors.operationFailed(str, localizedMessage);
    }
}
