package io.ionic.libs.ionfilesystemlib.model;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B!\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;", "", "inExternalStorage", "", "alternateNames", "", "", "(Ljava/lang/String;IZLjava/util/List;)V", "getAlternateNames$IONFilesystemLib_release", "()Ljava/util/List;", "getInExternalStorage", "()Z", "INTERNAL_CACHE", "INTERNAL_FILES", "EXTERNAL_CACHE", "EXTERNAL_FILES", "EXTERNAL_STORAGE", "DOCUMENTS", "Companion", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEFolderType.kt */
public enum IONFILEFolderType {
    INTERNAL_CACHE(false, CollectionsKt.listOf("CACHE", "TEMPORARY"), 1, (List) null),
    INTERNAL_FILES(false, CollectionsKt.listOf("DATA", "LIBRARY", "FILES", "LIBRARY_NO_CLOUD"), 1, (List) null),
    EXTERNAL_CACHE(false, CollectionsKt.listOf("CACHE_EXTERNAL"), 1, (List) null),
    EXTERNAL_FILES(false, CollectionsKt.listOf("EXTERNAL", "FILES_EXTERNAL"), 1, (List) null),
    EXTERNAL_STORAGE(true, CollectionsKt.listOf("sdcard")),
    DOCUMENTS(true, (int) null, 2, (List) null);
    
    public static final Companion Companion = null;
    private final List<String> alternateNames;
    private final boolean inExternalStorage;

    public static EnumEntries<IONFILEFolderType> getEntries() {
        return $ENTRIES;
    }

    private IONFILEFolderType(boolean z, List<String> list) {
        this.inExternalStorage = z;
        this.alternateNames = list;
    }

    public final boolean getInExternalStorage() {
        return this.inExternalStorage;
    }

    public final List<String> getAlternateNames$IONFilesystemLib_release() {
        return this.alternateNames;
    }

    static {
        IONFILEFolderType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\f"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType$Companion;", "", "()V", "aliasMatches", "", "alias", "", "compare", "coerceFolderAlias", "coerceFolderAlias$IONFilesystemLib_release", "fromStringAlias", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEFolderType;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEFolderType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType} */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
            r1 = r2;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType fromStringAlias(java.lang.String r7) {
            /*
                r6 = this;
                r0 = r7
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                r1 = 0
                if (r0 == 0) goto L_0x005f
                boolean r0 = kotlin.text.StringsKt.isBlank(r0)
                if (r0 == 0) goto L_0x000d
                goto L_0x005f
            L_0x000d:
                kotlin.enums.EnumEntries r0 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.getEntries()
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.Iterator r0 = r0.iterator()
            L_0x0017:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x005d
                java.lang.Object r2 = r0.next()
                r3 = r2
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r3 = (io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType) r3
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType$Companion r4 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.Companion
                java.lang.String r5 = r3.name()
                boolean r4 = r4.aliasMatches(r7, r5)
                if (r4 != 0) goto L_0x005c
                java.util.List r3 = r3.getAlternateNames$IONFilesystemLib_release()
                java.lang.Iterable r3 = (java.lang.Iterable) r3
                boolean r4 = r3 instanceof java.util.Collection
                if (r4 == 0) goto L_0x0044
                r4 = r3
                java.util.Collection r4 = (java.util.Collection) r4
                boolean r4 = r4.isEmpty()
                if (r4 == 0) goto L_0x0044
                goto L_0x0017
            L_0x0044:
                java.util.Iterator r3 = r3.iterator()
            L_0x0048:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x0017
                java.lang.Object r4 = r3.next()
                java.lang.String r4 = (java.lang.String) r4
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType$Companion r5 = io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.Companion
                boolean r4 = r5.aliasMatches(r7, r4)
                if (r4 == 0) goto L_0x0048
            L_0x005c:
                r1 = r2
            L_0x005d:
                io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r1 = (io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType) r1
            L_0x005f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType.Companion.fromStringAlias(java.lang.String):io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType");
        }

        private final boolean aliasMatches(String str, String str2) {
            return Intrinsics.areEqual((Object) coerceFolderAlias$IONFilesystemLib_release(str), (Object) coerceFolderAlias$IONFilesystemLib_release(str2));
        }

        public final String coerceFolderAlias$IONFilesystemLib_release(String str) {
            Intrinsics.checkNotNullParameter(str, "alias");
            CharSequence charSequence = str;
            Appendable sb = new StringBuilder();
            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                char charAt = charSequence.charAt(i);
                if (Character.isLetter(charAt)) {
                    sb.append(charAt);
                }
            }
            String sb2 = ((StringBuilder) sb).toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            String lowerCase = sb2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        }
    }
}
