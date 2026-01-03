package io.ionic.libs.ionfilesystemlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;", "", "encoding", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;)V", "getEncoding", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEReadOptions.kt */
public final class IONFILEReadOptions {
    private final IONFILEEncoding encoding;

    public static /* synthetic */ IONFILEReadOptions copy$default(IONFILEReadOptions iONFILEReadOptions, IONFILEEncoding iONFILEEncoding, int i, Object obj) {
        if ((i & 1) != 0) {
            iONFILEEncoding = iONFILEReadOptions.encoding;
        }
        return iONFILEReadOptions.copy(iONFILEEncoding);
    }

    public final IONFILEEncoding component1() {
        return this.encoding;
    }

    public final IONFILEReadOptions copy(IONFILEEncoding iONFILEEncoding) {
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        return new IONFILEReadOptions(iONFILEEncoding);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IONFILEReadOptions) && Intrinsics.areEqual((Object) this.encoding, (Object) ((IONFILEReadOptions) obj).encoding);
    }

    public int hashCode() {
        return this.encoding.hashCode();
    }

    public String toString() {
        return "IONFILEReadOptions(encoding=" + this.encoding + ")";
    }

    public IONFILEReadOptions(IONFILEEncoding iONFILEEncoding) {
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        this.encoding = iONFILEEncoding;
    }

    public final IONFILEEncoding getEncoding() {
        return this.encoding;
    }
}
