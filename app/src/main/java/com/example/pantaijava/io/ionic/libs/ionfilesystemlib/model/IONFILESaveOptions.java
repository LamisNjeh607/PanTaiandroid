package io.ionic.libs.ionfilesystemlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveOptions;", "", "data", "", "encoding", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "mode", "Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveMode;", "createFileRecursive", "", "(Ljava/lang/String;Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveMode;Z)V", "getCreateFileRecursive", "()Z", "getData", "()Ljava/lang/String;", "getEncoding", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "getMode", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILESaveMode;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILESaveOptions.kt */
public final class IONFILESaveOptions {
    private final boolean createFileRecursive;
    private final String data;
    private final IONFILEEncoding encoding;
    private final IONFILESaveMode mode;

    public static /* synthetic */ IONFILESaveOptions copy$default(IONFILESaveOptions iONFILESaveOptions, String str, IONFILEEncoding iONFILEEncoding, IONFILESaveMode iONFILESaveMode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = iONFILESaveOptions.data;
        }
        if ((i & 2) != 0) {
            iONFILEEncoding = iONFILESaveOptions.encoding;
        }
        if ((i & 4) != 0) {
            iONFILESaveMode = iONFILESaveOptions.mode;
        }
        if ((i & 8) != 0) {
            z = iONFILESaveOptions.createFileRecursive;
        }
        return iONFILESaveOptions.copy(str, iONFILEEncoding, iONFILESaveMode, z);
    }

    public final String component1() {
        return this.data;
    }

    public final IONFILEEncoding component2() {
        return this.encoding;
    }

    public final IONFILESaveMode component3() {
        return this.mode;
    }

    public final boolean component4() {
        return this.createFileRecursive;
    }

    public final IONFILESaveOptions copy(String str, IONFILEEncoding iONFILEEncoding, IONFILESaveMode iONFILESaveMode, boolean z) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        Intrinsics.checkNotNullParameter(iONFILESaveMode, "mode");
        return new IONFILESaveOptions(str, iONFILEEncoding, iONFILESaveMode, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IONFILESaveOptions)) {
            return false;
        }
        IONFILESaveOptions iONFILESaveOptions = (IONFILESaveOptions) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) iONFILESaveOptions.data) && Intrinsics.areEqual((Object) this.encoding, (Object) iONFILESaveOptions.encoding) && this.mode == iONFILESaveOptions.mode && this.createFileRecursive == iONFILESaveOptions.createFileRecursive;
    }

    public int hashCode() {
        return (((((this.data.hashCode() * 31) + this.encoding.hashCode()) * 31) + this.mode.hashCode()) * 31) + Boolean.hashCode(this.createFileRecursive);
    }

    public String toString() {
        String str = this.data;
        IONFILEEncoding iONFILEEncoding = this.encoding;
        IONFILESaveMode iONFILESaveMode = this.mode;
        return "IONFILESaveOptions(data=" + str + ", encoding=" + iONFILEEncoding + ", mode=" + iONFILESaveMode + ", createFileRecursive=" + this.createFileRecursive + ")";
    }

    public IONFILESaveOptions(String str, IONFILEEncoding iONFILEEncoding, IONFILESaveMode iONFILESaveMode, boolean z) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        Intrinsics.checkNotNullParameter(iONFILESaveMode, "mode");
        this.data = str;
        this.encoding = iONFILEEncoding;
        this.mode = iONFILESaveMode;
        this.createFileRecursive = z;
    }

    public final String getData() {
        return this.data;
    }

    public final IONFILEEncoding getEncoding() {
        return this.encoding;
    }

    public final IONFILESaveMode getMode() {
        return this.mode;
    }

    public final boolean getCreateFileRecursive() {
        return this.createFileRecursive;
    }
}
