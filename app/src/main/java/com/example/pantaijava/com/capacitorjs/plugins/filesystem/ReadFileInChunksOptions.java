package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/ReadFileInChunksOptions;", "", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;)V", "getOptions", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "getUri", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemMethodOptions.kt */
public final class ReadFileInChunksOptions {
    private final IONFILEReadInChunksOptions options;
    private final IONFILEUri.Unresolved uri;

    public static /* synthetic */ ReadFileInChunksOptions copy$default(ReadFileInChunksOptions readFileInChunksOptions, IONFILEUri.Unresolved unresolved, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            unresolved = readFileInChunksOptions.uri;
        }
        if ((i & 2) != 0) {
            iONFILEReadInChunksOptions = readFileInChunksOptions.options;
        }
        return readFileInChunksOptions.copy(unresolved, iONFILEReadInChunksOptions);
    }

    public final IONFILEUri.Unresolved component1() {
        return this.uri;
    }

    public final IONFILEReadInChunksOptions component2() {
        return this.options;
    }

    public final ReadFileInChunksOptions copy(IONFILEUri.Unresolved unresolved, IONFILEReadInChunksOptions iONFILEReadInChunksOptions) {
        Intrinsics.checkNotNullParameter(unresolved, "uri");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        return new ReadFileInChunksOptions(unresolved, iONFILEReadInChunksOptions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadFileInChunksOptions)) {
            return false;
        }
        ReadFileInChunksOptions readFileInChunksOptions = (ReadFileInChunksOptions) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) readFileInChunksOptions.uri) && Intrinsics.areEqual((Object) this.options, (Object) readFileInChunksOptions.options);
    }

    public int hashCode() {
        return (this.uri.hashCode() * 31) + this.options.hashCode();
    }

    public String toString() {
        IONFILEUri.Unresolved unresolved = this.uri;
        return "ReadFileInChunksOptions(uri=" + unresolved + ", options=" + this.options + ")";
    }

    public ReadFileInChunksOptions(IONFILEUri.Unresolved unresolved, IONFILEReadInChunksOptions iONFILEReadInChunksOptions) {
        Intrinsics.checkNotNullParameter(unresolved, "uri");
        Intrinsics.checkNotNullParameter(iONFILEReadInChunksOptions, "options");
        this.uri = unresolved;
        this.options = iONFILEReadInChunksOptions;
    }

    public final IONFILEUri.Unresolved getUri() {
        return this.uri;
    }

    public final IONFILEReadInChunksOptions getOptions() {
        return this.options;
    }
}
