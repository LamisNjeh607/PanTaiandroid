package io.ionic.libs.ionfilesystemlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\t\b\t\n\u000b\f\r\u000e\u000f\u0010B\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u0001\t\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "CopyRenameFailed", "CreateFailed", "DeleteFailed", "DoesNotExist", "NotSupportedForContentScheme", "NotSupportedForDirectory", "NotSupportedForFiles", "UnknownError", "UnresolvableUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DeleteFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DoesNotExist;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForContentScheme;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForDirectory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForFiles;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$UnknownError;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$UnresolvableUri;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEExceptions.kt */
public abstract class IONFILEExceptions extends Exception {
    public /* synthetic */ IONFILEExceptions(String str, Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, th);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IONFILEExceptions(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th, (DefaultConstructorMarker) null);
    }

    private IONFILEExceptions(String str, Throwable th) {
        super(str, th);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$UnresolvableUri;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "uri", "", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class UnresolvableUri extends IONFILEExceptions {
        private final String uri;

        public final String getUri() {
            return this.uri;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnresolvableUri(String str) {
            super("Unable to resolve the provided uri=" + str, (Throwable) null, 2, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "uri");
            this.uri = str;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DoesNotExist;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "path", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getPath", "()Ljava/lang/String;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class DoesNotExist extends IONFILEExceptions {
        private final Throwable cause;
        private final String path;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ DoesNotExist(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        public Throwable getCause() {
            return this.cause;
        }

        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoesNotExist(String str, Throwable th) {
            super("The file/directory at " + str + " does not exist", (Throwable) null, 2, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "path");
            this.path = str;
            this.cause = th;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$UnknownError;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class UnknownError extends IONFILEExceptions {
        private final Throwable cause;

        public UnknownError() {
            this((Throwable) null, 1, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ UnknownError(Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : th);
        }

        public Throwable getCause() {
            return this.cause;
        }

        public UnknownError(Throwable th) {
            super("An unknown error occurred.", (Throwable) null, 2, (DefaultConstructorMarker) null);
            this.cause = th;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForContentScheme;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class NotSupportedForContentScheme extends IONFILEExceptions {
        public NotSupportedForContentScheme() {
            super("The requested operation is not supported on a content:// uri", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForDirectory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class NotSupportedForDirectory extends IONFILEExceptions {
        public NotSupportedForDirectory() {
            super("The request operation is not supported on a directory", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$NotSupportedForFiles;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static final class NotSupportedForFiles extends IONFILEExceptions {
        public NotSupportedForFiles() {
            super("The request operation is not supported on files, only directories", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0005\u0006B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "message", "", "(Ljava/lang/String;)V", "AlreadyExists", "NoParentDirectory", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed$AlreadyExists;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed$NoParentDirectory;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static abstract class CreateFailed extends IONFILEExceptions {
        public /* synthetic */ CreateFailed(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed$AlreadyExists;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed;", "path", "", "(Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class AlreadyExists extends CreateFailed {
            private final String path;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public AlreadyExists(String str) {
                super("The file/directory at " + str + " already exists", (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "path");
                this.path = str;
            }

            public final String getPath() {
                return this.path;
            }
        }

        private CreateFailed(String str) {
            super(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed$NoParentDirectory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CreateFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class NoParentDirectory extends CreateFailed {
            public NoParentDirectory() {
                super("Missing parent directories - either recursive=false was received or parent directory creation failed", (DefaultConstructorMarker) null);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0005B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0001\u0001\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DeleteFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "message", "", "(Ljava/lang/String;)V", "CannotDeleteChildren", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DeleteFailed$CannotDeleteChildren;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static abstract class DeleteFailed extends IONFILEExceptions {
        public /* synthetic */ DeleteFailed(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }

        private DeleteFailed(String str) {
            super(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DeleteFailed$CannotDeleteChildren;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$DeleteFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class CannotDeleteChildren extends DeleteFailed {
            public CannotDeleteChildren() {
                super("Received recursive=false but directory is not-empty", (DefaultConstructorMarker) null);
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0005\u0006\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0001\u0005\n\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions;", "message", "", "(Ljava/lang/String;)V", "DestinationDirectoryExists", "LocalToContent", "MixingFilesAndDirectories", "NoParentDirectory", "SourceAndDestinationContent", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$DestinationDirectoryExists;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$LocalToContent;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$NoParentDirectory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$SourceAndDestinationContent;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEExceptions.kt */
    public static abstract class CopyRenameFailed extends IONFILEExceptions {
        public /* synthetic */ CopyRenameFailed(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }

        private CopyRenameFailed(String str) {
            super(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$MixingFilesAndDirectories;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class MixingFilesAndDirectories extends CopyRenameFailed {
            public MixingFilesAndDirectories() {
                super("Copy and rename is only allowed either between files or between directories", (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$LocalToContent;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class LocalToContent extends CopyRenameFailed {
            public LocalToContent() {
                super("Copy is not allowed from local file to content:// file", (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$SourceAndDestinationContent;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class SourceAndDestinationContent extends CopyRenameFailed {
            public SourceAndDestinationContent() {
                super("Copy is not allowed from content:// to content://", (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$DestinationDirectoryExists;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "path", "", "(Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class DestinationDirectoryExists extends CopyRenameFailed {
            private final String path;

            public final String getPath() {
                return this.path;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DestinationDirectoryExists(String str) {
                super("Cannot copy/rename to an existing directory (" + str + ")", (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "path");
                this.path = str;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed$NoParentDirectory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEExceptions$CopyRenameFailed;", "()V", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEExceptions.kt */
        public static final class NoParentDirectory extends CopyRenameFailed {
            public NoParentDirectory() {
                super("Unable to copy/rename because the destination's parent directory does not exist", (DefaultConstructorMarker) null);
            }
        }
    }
}
