package com.capacitorjs.plugins.filesystem;

import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/DoubleUri;", "", "fromUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "toUri", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;)V", "getFromUri", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "getToUri", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemMethodOptions.kt */
public final class DoubleUri {
    private final IONFILEUri.Unresolved fromUri;
    private final IONFILEUri.Unresolved toUri;

    public static /* synthetic */ DoubleUri copy$default(DoubleUri doubleUri, IONFILEUri.Unresolved unresolved, IONFILEUri.Unresolved unresolved2, int i, Object obj) {
        if ((i & 1) != 0) {
            unresolved = doubleUri.fromUri;
        }
        if ((i & 2) != 0) {
            unresolved2 = doubleUri.toUri;
        }
        return doubleUri.copy(unresolved, unresolved2);
    }

    public final IONFILEUri.Unresolved component1() {
        return this.fromUri;
    }

    public final IONFILEUri.Unresolved component2() {
        return this.toUri;
    }

    public final DoubleUri copy(IONFILEUri.Unresolved unresolved, IONFILEUri.Unresolved unresolved2) {
        Intrinsics.checkNotNullParameter(unresolved, "fromUri");
        Intrinsics.checkNotNullParameter(unresolved2, "toUri");
        return new DoubleUri(unresolved, unresolved2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleUri)) {
            return false;
        }
        DoubleUri doubleUri = (DoubleUri) obj;
        return Intrinsics.areEqual((Object) this.fromUri, (Object) doubleUri.fromUri) && Intrinsics.areEqual((Object) this.toUri, (Object) doubleUri.toUri);
    }

    public int hashCode() {
        return (this.fromUri.hashCode() * 31) + this.toUri.hashCode();
    }

    public String toString() {
        IONFILEUri.Unresolved unresolved = this.fromUri;
        return "DoubleUri(fromUri=" + unresolved + ", toUri=" + this.toUri + ")";
    }

    public DoubleUri(IONFILEUri.Unresolved unresolved, IONFILEUri.Unresolved unresolved2) {
        Intrinsics.checkNotNullParameter(unresolved, "fromUri");
        Intrinsics.checkNotNullParameter(unresolved2, "toUri");
        this.fromUri = unresolved;
        this.toUri = unresolved2;
    }

    public final IONFILEUri.Unresolved getFromUri() {
        return this.fromUri;
    }

    public final IONFILEUri.Unresolved getToUri() {
        return this.toUri;
    }
}
