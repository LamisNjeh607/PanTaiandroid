package io.ionic.libs.ionfilesystemlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "", "encoding", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "chunkSize", "", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;I)V", "getChunkSize", "()I", "getEncoding", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEReadInChunksOptions.kt */
public final class IONFILEReadInChunksOptions {
    private final int chunkSize;
    private final IONFILEEncoding encoding;

    public static /* synthetic */ IONFILEReadInChunksOptions copy$default(IONFILEReadInChunksOptions iONFILEReadInChunksOptions, IONFILEEncoding iONFILEEncoding, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            iONFILEEncoding = iONFILEReadInChunksOptions.encoding;
        }
        if ((i2 & 2) != 0) {
            i = iONFILEReadInChunksOptions.chunkSize;
        }
        return iONFILEReadInChunksOptions.copy(iONFILEEncoding, i);
    }

    public final IONFILEEncoding component1() {
        return this.encoding;
    }

    public final int component2() {
        return this.chunkSize;
    }

    public final IONFILEReadInChunksOptions copy(IONFILEEncoding iONFILEEncoding, int i) {
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        return new IONFILEReadInChunksOptions(iONFILEEncoding, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IONFILEReadInChunksOptions)) {
            return false;
        }
        IONFILEReadInChunksOptions iONFILEReadInChunksOptions = (IONFILEReadInChunksOptions) obj;
        return Intrinsics.areEqual((Object) this.encoding, (Object) iONFILEReadInChunksOptions.encoding) && this.chunkSize == iONFILEReadInChunksOptions.chunkSize;
    }

    public int hashCode() {
        return (this.encoding.hashCode() * 31) + Integer.hashCode(this.chunkSize);
    }

    public String toString() {
        IONFILEEncoding iONFILEEncoding = this.encoding;
        return "IONFILEReadInChunksOptions(encoding=" + iONFILEEncoding + ", chunkSize=" + this.chunkSize + ")";
    }

    public IONFILEReadInChunksOptions(IONFILEEncoding iONFILEEncoding, int i) {
        Intrinsics.checkNotNullParameter(iONFILEEncoding, "encoding");
        this.encoding = iONFILEEncoding;
        this.chunkSize = i;
    }

    public final IONFILEEncoding getEncoding() {
        return this.encoding;
    }

    public final int getChunkSize() {
        return this.chunkSize;
    }
}
