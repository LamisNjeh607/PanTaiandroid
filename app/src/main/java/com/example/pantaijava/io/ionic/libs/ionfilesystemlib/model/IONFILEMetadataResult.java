package io.ionic.libs.ionfilesystemlib.model;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010!\u001a\u00020\bHÆ\u0003JV\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "", "fullPath", "", "name", "uri", "Landroid/net/Uri;", "size", "", "type", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;", "createdTimestamp", "lastModifiedTimestamp", "(Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;JLio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;Ljava/lang/Long;J)V", "getCreatedTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFullPath", "()Ljava/lang/String;", "getLastModifiedTimestamp", "()J", "getName", "getSize", "getType", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;JLio/ionic/libs/ionfilesystemlib/model/IONFILEFileType;Ljava/lang/Long;J)Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "equals", "", "other", "hashCode", "", "toString", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEMetadataResult.kt */
public final class IONFILEMetadataResult {
    private final Long createdTimestamp;
    private final String fullPath;
    private final long lastModifiedTimestamp;
    private final String name;
    private final long size;
    private final IONFILEFileType type;
    private final Uri uri;

    public static /* synthetic */ IONFILEMetadataResult copy$default(IONFILEMetadataResult iONFILEMetadataResult, String str, String str2, Uri uri2, long j, IONFILEFileType iONFILEFileType, Long l, long j2, int i, Object obj) {
        IONFILEMetadataResult iONFILEMetadataResult2 = iONFILEMetadataResult;
        return iONFILEMetadataResult.copy((i & 1) != 0 ? iONFILEMetadataResult2.fullPath : str, (i & 2) != 0 ? iONFILEMetadataResult2.name : str2, (i & 4) != 0 ? iONFILEMetadataResult2.uri : uri2, (i & 8) != 0 ? iONFILEMetadataResult2.size : j, (i & 16) != 0 ? iONFILEMetadataResult2.type : iONFILEFileType, (i & 32) != 0 ? iONFILEMetadataResult2.createdTimestamp : l, (i & 64) != 0 ? iONFILEMetadataResult2.lastModifiedTimestamp : j2);
    }

    public final String component1() {
        return this.fullPath;
    }

    public final String component2() {
        return this.name;
    }

    public final Uri component3() {
        return this.uri;
    }

    public final long component4() {
        return this.size;
    }

    public final IONFILEFileType component5() {
        return this.type;
    }

    public final Long component6() {
        return this.createdTimestamp;
    }

    public final long component7() {
        return this.lastModifiedTimestamp;
    }

    public final IONFILEMetadataResult copy(String str, String str2, Uri uri2, long j, IONFILEFileType iONFILEFileType, Long l, long j2) {
        Intrinsics.checkNotNullParameter(str, "fullPath");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(uri2, "uri");
        IONFILEFileType iONFILEFileType2 = iONFILEFileType;
        Intrinsics.checkNotNullParameter(iONFILEFileType2, "type");
        return new IONFILEMetadataResult(str, str2, uri2, j, iONFILEFileType2, l, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IONFILEMetadataResult)) {
            return false;
        }
        IONFILEMetadataResult iONFILEMetadataResult = (IONFILEMetadataResult) obj;
        return Intrinsics.areEqual((Object) this.fullPath, (Object) iONFILEMetadataResult.fullPath) && Intrinsics.areEqual((Object) this.name, (Object) iONFILEMetadataResult.name) && Intrinsics.areEqual((Object) this.uri, (Object) iONFILEMetadataResult.uri) && this.size == iONFILEMetadataResult.size && Intrinsics.areEqual((Object) this.type, (Object) iONFILEMetadataResult.type) && Intrinsics.areEqual((Object) this.createdTimestamp, (Object) iONFILEMetadataResult.createdTimestamp) && this.lastModifiedTimestamp == iONFILEMetadataResult.lastModifiedTimestamp;
    }

    public int hashCode() {
        int hashCode = ((((((((this.fullPath.hashCode() * 31) + this.name.hashCode()) * 31) + this.uri.hashCode()) * 31) + Long.hashCode(this.size)) * 31) + this.type.hashCode()) * 31;
        Long l = this.createdTimestamp;
        return ((hashCode + (l == null ? 0 : l.hashCode())) * 31) + Long.hashCode(this.lastModifiedTimestamp);
    }

    public String toString() {
        String str = this.fullPath;
        String str2 = this.name;
        Uri uri2 = this.uri;
        long j = this.size;
        IONFILEFileType iONFILEFileType = this.type;
        Long l = this.createdTimestamp;
        return "IONFILEMetadataResult(fullPath=" + str + ", name=" + str2 + ", uri=" + uri2 + ", size=" + j + ", type=" + iONFILEFileType + ", createdTimestamp=" + l + ", lastModifiedTimestamp=" + this.lastModifiedTimestamp + ")";
    }

    public IONFILEMetadataResult(String str, String str2, Uri uri2, long j, IONFILEFileType iONFILEFileType, Long l, long j2) {
        Intrinsics.checkNotNullParameter(str, "fullPath");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(uri2, "uri");
        Intrinsics.checkNotNullParameter(iONFILEFileType, "type");
        this.fullPath = str;
        this.name = str2;
        this.uri = uri2;
        this.size = j;
        this.type = iONFILEFileType;
        this.createdTimestamp = l;
        this.lastModifiedTimestamp = j2;
    }

    public final String getFullPath() {
        return this.fullPath;
    }

    public final String getName() {
        return this.name;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final long getSize() {
        return this.size;
    }

    public final IONFILEFileType getType() {
        return this.type;
    }

    public final Long getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public final long getLastModifiedTimestamp() {
        return this.lastModifiedTimestamp;
    }
}
