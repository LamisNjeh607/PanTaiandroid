package io.ionic.libs.ionfilesystemlib.model;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;", "", "()V", "Resolved", "Unresolved", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEUri.kt */
public abstract class IONFILEUri {
    public /* synthetic */ IONFILEUri(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private IONFILEUri() {
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;", "parentFolder", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;", "uriPath", "", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;Ljava/lang/String;)V", "getParentFolder", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;", "getUriPath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEUri.kt */
    public static final class Unresolved extends IONFILEUri {
        private final IONFILEFolderType parentFolder;
        private final String uriPath;

        public static /* synthetic */ Unresolved copy$default(Unresolved unresolved, IONFILEFolderType iONFILEFolderType, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                iONFILEFolderType = unresolved.parentFolder;
            }
            if ((i & 2) != 0) {
                str = unresolved.uriPath;
            }
            return unresolved.copy(iONFILEFolderType, str);
        }

        public final IONFILEFolderType component1() {
            return this.parentFolder;
        }

        public final String component2() {
            return this.uriPath;
        }

        public final Unresolved copy(IONFILEFolderType iONFILEFolderType, String str) {
            Intrinsics.checkNotNullParameter(str, "uriPath");
            return new Unresolved(iONFILEFolderType, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Unresolved)) {
                return false;
            }
            Unresolved unresolved = (Unresolved) obj;
            return this.parentFolder == unresolved.parentFolder && Intrinsics.areEqual((Object) this.uriPath, (Object) unresolved.uriPath);
        }

        public int hashCode() {
            IONFILEFolderType iONFILEFolderType = this.parentFolder;
            return ((iONFILEFolderType == null ? 0 : iONFILEFolderType.hashCode()) * 31) + this.uriPath.hashCode();
        }

        public String toString() {
            IONFILEFolderType iONFILEFolderType = this.parentFolder;
            return "Unresolved(parentFolder=" + iONFILEFolderType + ", uriPath=" + this.uriPath + ")";
        }

        public final IONFILEFolderType getParentFolder() {
            return this.parentFolder;
        }

        public final String getUriPath() {
            return this.uriPath;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Unresolved(IONFILEFolderType iONFILEFolderType, String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "uriPath");
            this.parentFolder = iONFILEFolderType;
            this.uriPath = str;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;", "uri", "Landroid/net/Uri;", "inExternalStorage", "", "(Landroid/net/Uri;Z)V", "getInExternalStorage", "()Z", "getUri", "()Landroid/net/Uri;", "Content", "Local", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Content;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Local;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEUri.kt */
    public static abstract class Resolved extends IONFILEUri {
        private final boolean inExternalStorage;
        private final Uri uri;

        public /* synthetic */ Resolved(Uri uri2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(uri2, z);
        }

        private Resolved(Uri uri2, boolean z) {
            super((DefaultConstructorMarker) null);
            this.uri = uri2;
            this.inExternalStorage = z;
        }

        public boolean getInExternalStorage() {
            return this.inExternalStorage;
        }

        public Uri getUri() {
            return this.uri;
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Content;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "getUri", "()Landroid/net/Uri;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEUri.kt */
        public static final class Content extends Resolved {
            private final Uri uri;

            public static /* synthetic */ Content copy$default(Content content, Uri uri2, int i, Object obj) {
                if ((i & 1) != 0) {
                    uri2 = content.uri;
                }
                return content.copy(uri2);
            }

            public final Uri component1() {
                return this.uri;
            }

            public final Content copy(Uri uri2) {
                Intrinsics.checkNotNullParameter(uri2, "uri");
                return new Content(uri2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Content) && Intrinsics.areEqual((Object) this.uri, (Object) ((Content) obj).uri);
            }

            public int hashCode() {
                return this.uri.hashCode();
            }

            public String toString() {
                return "Content(uri=" + this.uri + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Content(Uri uri2) {
                super(uri2, false, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(uri2, "uri");
                this.uri = uri2;
            }

            public Uri getUri() {
                return this.uri;
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Local;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "fullPath", "", "(Ljava/lang/String;)V", "uri", "Landroid/net/Uri;", "type", "Lio/ionic/libs/ionfilesystemlib/model/LocalUriType;", "inExternalStorage", "", "(Ljava/lang/String;Landroid/net/Uri;Lio/ionic/libs/ionfilesystemlib/model/LocalUriType;Z)V", "getFullPath", "()Ljava/lang/String;", "getInExternalStorage", "()Z", "getType", "()Lio/ionic/libs/ionfilesystemlib/model/LocalUriType;", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: IONFILEUri.kt */
        public static final class Local extends Resolved {
            private final String fullPath;
            private final boolean inExternalStorage;
            private final LocalUriType type;
            private final Uri uri;

            public static /* synthetic */ Local copy$default(Local local, String str, Uri uri2, LocalUriType localUriType, boolean z, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = local.fullPath;
                }
                if ((i & 2) != 0) {
                    uri2 = local.uri;
                }
                if ((i & 4) != 0) {
                    localUriType = local.type;
                }
                if ((i & 8) != 0) {
                    z = local.inExternalStorage;
                }
                return local.copy(str, uri2, localUriType, z);
            }

            public final String component1() {
                return this.fullPath;
            }

            public final Uri component2() {
                return this.uri;
            }

            public final LocalUriType component3() {
                return this.type;
            }

            public final boolean component4() {
                return this.inExternalStorage;
            }

            public final Local copy(String str, Uri uri2, LocalUriType localUriType, boolean z) {
                Intrinsics.checkNotNullParameter(str, "fullPath");
                Intrinsics.checkNotNullParameter(uri2, "uri");
                Intrinsics.checkNotNullParameter(localUriType, "type");
                return new Local(str, uri2, localUriType, z);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Local)) {
                    return false;
                }
                Local local = (Local) obj;
                return Intrinsics.areEqual((Object) this.fullPath, (Object) local.fullPath) && Intrinsics.areEqual((Object) this.uri, (Object) local.uri) && this.type == local.type && this.inExternalStorage == local.inExternalStorage;
            }

            public int hashCode() {
                return (((((this.fullPath.hashCode() * 31) + this.uri.hashCode()) * 31) + this.type.hashCode()) * 31) + Boolean.hashCode(this.inExternalStorage);
            }

            public String toString() {
                String str = this.fullPath;
                Uri uri2 = this.uri;
                LocalUriType localUriType = this.type;
                return "Local(fullPath=" + str + ", uri=" + uri2 + ", type=" + localUriType + ", inExternalStorage=" + this.inExternalStorage + ")";
            }

            public final String getFullPath() {
                return this.fullPath;
            }

            public Uri getUri() {
                return this.uri;
            }

            public final LocalUriType getType() {
                return this.type;
            }

            public boolean getInExternalStorage() {
                return this.inExternalStorage;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Local(String str, Uri uri2, LocalUriType localUriType, boolean z) {
                super(uri2, z, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "fullPath");
                Intrinsics.checkNotNullParameter(uri2, "uri");
                Intrinsics.checkNotNullParameter(localUriType, "type");
                this.fullPath = str;
                this.uri = uri2;
                this.type = localUriType;
                this.inExternalStorage = z;
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public Local(java.lang.String r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "fullPath"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    java.io.File r0 = new java.io.File
                    r0.<init>(r4)
                    android.net.Uri r0 = android.net.Uri.fromFile(r0)
                    java.lang.String r1 = "fromFile(...)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    io.ionic.libs.ionfilesystemlib.model.LocalUriType r1 = io.ionic.libs.ionfilesystemlib.model.LocalUriType.UNKNOWN
                    r2 = 1
                    r3.<init>(r4, r0, r1, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved.Local.<init>(java.lang.String):void");
            }
        }
    }
}
