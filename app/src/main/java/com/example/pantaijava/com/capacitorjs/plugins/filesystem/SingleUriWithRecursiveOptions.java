package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/SingleUriWithRecursiveOptions;", "", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "recursive", "", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Z)V", "getRecursive", "()Z", "getUri", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemMethodOptions.kt */
public final class SingleUriWithRecursiveOptions {
    private final boolean recursive;
    private final IONFILEUri.Unresolved uri;

    public static /* synthetic */ SingleUriWithRecursiveOptions copy$default(SingleUriWithRecursiveOptions singleUriWithRecursiveOptions, IONFILEUri.Unresolved unresolved, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            unresolved = singleUriWithRecursiveOptions.uri;
        }
        if ((i & 2) != 0) {
            z = singleUriWithRecursiveOptions.recursive;
        }
        return singleUriWithRecursiveOptions.copy(unresolved, z);
    }

    public final IONFILEUri.Unresolved component1() {
        return this.uri;
    }

    public final boolean component2() {
        return this.recursive;
    }

    public final SingleUriWithRecursiveOptions copy(IONFILEUri.Unresolved unresolved, boolean z) {
        Intrinsics.checkNotNullParameter(unresolved, "uri");
        return new SingleUriWithRecursiveOptions(unresolved, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingleUriWithRecursiveOptions)) {
            return false;
        }
        SingleUriWithRecursiveOptions singleUriWithRecursiveOptions = (SingleUriWithRecursiveOptions) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) singleUriWithRecursiveOptions.uri) && this.recursive == singleUriWithRecursiveOptions.recursive;
    }

    public int hashCode() {
        return (this.uri.hashCode() * 31) + Boolean.hashCode(this.recursive);
    }

    public String toString() {
        IONFILEUri.Unresolved unresolved = this.uri;
        return "SingleUriWithRecursiveOptions(uri=" + unresolved + ", recursive=" + this.recursive + ")";
    }

    public SingleUriWithRecursiveOptions(IONFILEUri.Unresolved unresolved, boolean z) {
        Intrinsics.checkNotNullParameter(unresolved, "uri");
        this.uri = unresolved;
        this.recursive = z;
    }

    public final IONFILEUri.Unresolved getUri() {
        return this.uri;
    }

    public final boolean getRecursive() {
        return this.recursive;
    }
}
