package com.capacitorjs.plugins.filesystem;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\rJ\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u001a"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/FilesystemErrors;", "", "()V", "cannotDeleteChildren", "Lcom/capacitorjs/plugins/filesystem/FilesystemErrors$ErrorInfo;", "getCannotDeleteChildren", "()Lcom/capacitorjs/plugins/filesystem/FilesystemErrors$ErrorInfo;", "filePermissionsDenied", "getFilePermissionsDenied", "missingParentDirectories", "getMissingParentDirectories", "directoryCreationAlreadyExists", "path", "", "doesNotExist", "methodName", "formatErrorCode", "number", "", "invalidInputMethod", "invalidPath", "notAllowed", "notAllowedFor", "operationFailed", "errorMessage", "ErrorInfo", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemErrors.kt */
public final class FilesystemErrors {
    public static final FilesystemErrors INSTANCE;
    private static final ErrorInfo cannotDeleteChildren;
    private static final ErrorInfo filePermissionsDenied;
    private static final ErrorInfo missingParentDirectories;

    private FilesystemErrors() {
    }

    private final String formatErrorCode(int i) {
        return "OS-PLUG-FILE-" + StringsKt.padStart(String.valueOf(i), 4, '0');
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/FilesystemErrors$ErrorInfo;", "", "code", "", "message", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FilesystemErrors.kt */
    public static final class ErrorInfo {
        private final String code;
        private final String message;

        public static /* synthetic */ ErrorInfo copy$default(ErrorInfo errorInfo, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorInfo.code;
            }
            if ((i & 2) != 0) {
                str2 = errorInfo.message;
            }
            return errorInfo.copy(str, str2);
        }

        public final String component1() {
            return this.code;
        }

        public final String component2() {
            return this.message;
        }

        public final ErrorInfo copy(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "code");
            Intrinsics.checkNotNullParameter(str2, "message");
            return new ErrorInfo(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorInfo)) {
                return false;
            }
            ErrorInfo errorInfo = (ErrorInfo) obj;
            return Intrinsics.areEqual((Object) this.code, (Object) errorInfo.code) && Intrinsics.areEqual((Object) this.message, (Object) errorInfo.message);
        }

        public int hashCode() {
            return (this.code.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            String str = this.code;
            return "ErrorInfo(code=" + str + ", message=" + this.message + ")";
        }

        public ErrorInfo(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "code");
            Intrinsics.checkNotNullParameter(str2, "message");
            this.code = str;
            this.message = str2;
        }

        public final String getCode() {
            return this.code;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    public final ErrorInfo invalidInputMethod(String str) {
        Intrinsics.checkNotNullParameter(str, "methodName");
        return new ErrorInfo(formatErrorCode(5), "The '" + str + "' input parameters aren't valid.");
    }

    public final ErrorInfo invalidPath(String str) {
        Intrinsics.checkNotNullParameter(str, "path");
        return new ErrorInfo(formatErrorCode(6), "Invalid " + (StringsKt.isBlank(str) ^ true ? "'" + str + "' " : "") + "path.");
    }

    static {
        FilesystemErrors filesystemErrors = new FilesystemErrors();
        INSTANCE = filesystemErrors;
        filePermissionsDenied = new ErrorInfo(filesystemErrors.formatErrorCode(7), "Unable to do file operation, user denied permission request.");
        missingParentDirectories = new ErrorInfo(filesystemErrors.formatErrorCode(11), "Missing parent directory – possibly recursive=false was passed or parent directory creation failed.");
        cannotDeleteChildren = new ErrorInfo(filesystemErrors.formatErrorCode(12), "Cannot delete directory with children; received recursive=false but directory has contents.");
    }

    public final ErrorInfo getFilePermissionsDenied() {
        return filePermissionsDenied;
    }

    public final ErrorInfo doesNotExist(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "methodName");
        Intrinsics.checkNotNullParameter(str2, "path");
        return new ErrorInfo(formatErrorCode(8), "'" + str + "' failed because file " + (StringsKt.isBlank(str2) ^ true ? "at '" + str2 + "' " : "") + "does not exist.");
    }

    public final ErrorInfo notAllowed(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "methodName");
        Intrinsics.checkNotNullParameter(str2, "notAllowedFor");
        return new ErrorInfo(formatErrorCode(9), "'" + str + "' not supported for " + str2 + ".");
    }

    public final ErrorInfo directoryCreationAlreadyExists(String str) {
        Intrinsics.checkNotNullParameter(str, "path");
        return new ErrorInfo(formatErrorCode(10), "Directory " + (StringsKt.isBlank(str) ^ true ? "at '" + str + "' " : "") + "already exists, cannot be overwritten.");
    }

    public final ErrorInfo getMissingParentDirectories() {
        return missingParentDirectories;
    }

    public final ErrorInfo getCannotDeleteChildren() {
        return cannotDeleteChildren;
    }

    public final ErrorInfo operationFailed(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "methodName");
        Intrinsics.checkNotNullParameter(str2, "errorMessage");
        return new ErrorInfo(formatErrorCode(13), "'" + str + "' failed with" + (StringsKt.isBlank(str2) ^ true ? ": " + str2 : "an unknown error."));
    }
}
