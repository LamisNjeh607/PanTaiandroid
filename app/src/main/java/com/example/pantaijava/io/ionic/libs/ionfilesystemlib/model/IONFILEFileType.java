package io.ionic.libs.ionfilesystemlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;", "", "()V", "Directory", "File", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType$Directory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType$File;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEMetadataResult.kt */
public abstract class IONFILEFileType {
    public /* synthetic */ IONFILEFileType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType$Directory;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEMetadataResult.kt */
    public static final class Directory extends IONFILEFileType {
        public static final Directory INSTANCE = new Directory();

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Directory)) {
                return false;
            }
            Directory directory = (Directory) obj;
            return true;
        }

        public int hashCode() {
            return 1673249540;
        }

        public String toString() {
            return "Directory";
        }

        private Directory() {
            super((DefaultConstructorMarker) null);
        }
    }

    private IONFILEFileType() {
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType$File;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;", "mimeType", "", "(Ljava/lang/String;)V", "getMimeType", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEMetadataResult.kt */
    public static final class File extends IONFILEFileType {
        private final String mimeType;

        public static /* synthetic */ File copy$default(File file, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = file.mimeType;
            }
            return file.copy(str);
        }

        public final String component1() {
            return this.mimeType;
        }

        public final File copy(String str) {
            Intrinsics.checkNotNullParameter(str, "mimeType");
            return new File(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof File) && Intrinsics.areEqual((Object) this.mimeType, (Object) ((File) obj).mimeType);
        }

        public int hashCode() {
            return this.mimeType.hashCode();
        }

        public String toString() {
            return "File(mimeType=" + this.mimeType + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public File(String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "mimeType");
            this.mimeType = str;
        }

        public final String getMimeType() {
            return this.mimeType;
        }
    }
}
