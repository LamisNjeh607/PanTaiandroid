package io.ionic.libs.ionfilesystemlib.model;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "", "()V", "Base64", "Companion", "WithCharset", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$Base64;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$WithCharset;", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEEncoding.kt */
public abstract class IONFILEEncoding {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Base64 Default = Base64.INSTANCE;
    /* access modifiers changed from: private */
    public static final WithCharset DefaultCharset = new WithCharset(Charsets.UTF_8);

    public /* synthetic */ IONFILEEncoding(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$Base64;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEEncoding.kt */
    public static final class Base64 extends IONFILEEncoding {
        public static final Base64 INSTANCE = new Base64();

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Base64)) {
                return false;
            }
            Base64 base64 = (Base64) obj;
            return true;
        }

        public int hashCode() {
            return -300323077;
        }

        public String toString() {
            return "Base64";
        }

        private Base64() {
            super((DefaultConstructorMarker) null);
        }
    }

    private IONFILEEncoding() {
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$WithCharset;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "charset", "Ljava/nio/charset/Charset;", "(Ljava/nio/charset/Charset;)V", "getCharset", "()Ljava/nio/charset/Charset;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEEncoding.kt */
    public static final class WithCharset extends IONFILEEncoding {
        private final Charset charset;

        public static /* synthetic */ WithCharset copy$default(WithCharset withCharset, Charset charset2, int i, Object obj) {
            if ((i & 1) != 0) {
                charset2 = withCharset.charset;
            }
            return withCharset.copy(charset2);
        }

        public final Charset component1() {
            return this.charset;
        }

        public final WithCharset copy(Charset charset2) {
            Intrinsics.checkNotNullParameter(charset2, "charset");
            return new WithCharset(charset2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WithCharset) && Intrinsics.areEqual((Object) this.charset, (Object) ((WithCharset) obj).charset);
        }

        public int hashCode() {
            return this.charset.hashCode();
        }

        public String toString() {
            return "WithCharset(charset=" + this.charset + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WithCharset(Charset charset2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(charset2, "charset");
            this.charset = charset2;
        }

        public final Charset getCharset() {
            return this.charset;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$Companion;", "", "()V", "Default", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$Base64;", "getDefault$IONFilesystemLib_release", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$Base64;", "DefaultCharset", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$WithCharset;", "getDefaultCharset$IONFilesystemLib_release", "()Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding$WithCharset;", "fromEncodingName", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEEncoding;", "encodingName", "", "IONFilesystemLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONFILEEncoding.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Base64 getDefault$IONFilesystemLib_release() {
            return IONFILEEncoding.Default;
        }

        public final WithCharset getDefaultCharset$IONFilesystemLib_release() {
            return IONFILEEncoding.DefaultCharset;
        }

        public final IONFILEEncoding fromEncodingName(String str) {
            WithCharset withCharset;
            CharSequence charSequence = str;
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                return getDefault$IONFilesystemLib_release();
            }
            try {
                Charset forName = Charset.forName(str);
                Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
                withCharset = new WithCharset(forName);
            } catch (Exception unused) {
                withCharset = getDefaultCharset$IONFilesystemLib_release();
            }
            return withCharset;
        }
    }
}
