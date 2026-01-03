package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lkotlin/text/HexFormat;", "", "upperCase", "", "bytes", "Lkotlin/text/HexFormat$BytesHexFormat;", "number", "Lkotlin/text/HexFormat$NumberHexFormat;", "(ZLkotlin/text/HexFormat$BytesHexFormat;Lkotlin/text/HexFormat$NumberHexFormat;)V", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat;", "getUpperCase", "()Z", "toString", "", "Builder", "BytesHexFormat", "Companion", "NumberHexFormat", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HexFormat.kt */
public final class HexFormat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final HexFormat Default = new HexFormat(false, BytesHexFormat.Companion.getDefault$kotlin_stdlib(), NumberHexFormat.Companion.getDefault$kotlin_stdlib());
    /* access modifiers changed from: private */
    public static final HexFormat UpperCase = new HexFormat(true, BytesHexFormat.Companion.getDefault$kotlin_stdlib(), NumberHexFormat.Companion.getDefault$kotlin_stdlib());
    private final BytesHexFormat bytes;
    private final NumberHexFormat number;
    private final boolean upperCase;

    public HexFormat(boolean z, BytesHexFormat bytesHexFormat, NumberHexFormat numberHexFormat) {
        Intrinsics.checkNotNullParameter(bytesHexFormat, "bytes");
        Intrinsics.checkNotNullParameter(numberHexFormat, "number");
        this.upperCase = z;
        this.bytes = bytesHexFormat;
        this.number = numberHexFormat;
    }

    public final boolean getUpperCase() {
        return this.upperCase;
    }

    public final BytesHexFormat getBytes() {
        return this.bytes;
    }

    public final NumberHexFormat getNumber() {
        return this.number;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder append = sb.append("HexFormat(");
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append.append(10), "append(...)");
        StringBuilder append2 = sb.append("    upperCase = ").append(this.upperCase);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        StringBuilder append3 = append2.append(",");
        Intrinsics.checkNotNullExpressionValue(append3, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append3.append(10), "append(...)");
        StringBuilder append4 = sb.append("    bytes = BytesHexFormat(");
        Intrinsics.checkNotNullExpressionValue(append4, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append4.append(10), "append(...)");
        Intrinsics.checkNotNullExpressionValue(this.bytes.appendOptionsTo$kotlin_stdlib(sb, "        ").append(10), "append(...)");
        StringBuilder append5 = sb.append("    ),");
        Intrinsics.checkNotNullExpressionValue(append5, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append5.append(10), "append(...)");
        StringBuilder append6 = sb.append("    number = NumberHexFormat(");
        Intrinsics.checkNotNullExpressionValue(append6, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append6.append(10), "append(...)");
        Intrinsics.checkNotNullExpressionValue(this.number.appendOptionsTo$kotlin_stdlib(sb, "        ").append(10), "append(...)");
        StringBuilder append7 = sb.append("    )");
        Intrinsics.checkNotNullExpressionValue(append7, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append7.append(10), "append(...)");
        sb.append(")");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0002\"#B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ%\u0010\u001b\u001a\u00060\u001cj\u0002`\u001d2\n\u0010\u001e\u001a\u00060\u001cj\u0002`\u001d2\u0006\u0010\u001f\u001a\u00020\u0006H\u0000¢\u0006\u0002\b J\b\u0010!\u001a\u00020\u0006H\u0016R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006$"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat;", "", "bytesPerLine", "", "bytesPerGroup", "groupSeparator", "", "byteSeparator", "bytePrefix", "byteSuffix", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBytePrefix", "()Ljava/lang/String;", "getByteSeparator", "getByteSuffix", "getBytesPerGroup", "()I", "getBytesPerLine", "getGroupSeparator", "ignoreCase", "", "getIgnoreCase$kotlin_stdlib", "()Z", "noLineAndGroupSeparator", "getNoLineAndGroupSeparator$kotlin_stdlib", "shortByteSeparatorNoPrefixAndSuffix", "getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "toString", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HexFormat.kt */
    public static final class BytesHexFormat {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final BytesHexFormat Default = new BytesHexFormat(Integer.MAX_VALUE, Integer.MAX_VALUE, "  ", "", "", "");
        private final String bytePrefix;
        private final String byteSeparator;
        private final String byteSuffix;
        private final int bytesPerGroup;
        private final int bytesPerLine;
        private final String groupSeparator;
        private final boolean ignoreCase;
        private final boolean noLineAndGroupSeparator;
        private final boolean shortByteSeparatorNoPrefixAndSuffix;

        public BytesHexFormat(int i, int i2, String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "groupSeparator");
            Intrinsics.checkNotNullParameter(str2, "byteSeparator");
            Intrinsics.checkNotNullParameter(str3, "bytePrefix");
            Intrinsics.checkNotNullParameter(str4, "byteSuffix");
            this.bytesPerLine = i;
            this.bytesPerGroup = i2;
            this.groupSeparator = str;
            this.byteSeparator = str2;
            this.bytePrefix = str3;
            this.byteSuffix = str4;
            boolean z = false;
            this.noLineAndGroupSeparator = i == Integer.MAX_VALUE && i2 == Integer.MAX_VALUE;
            this.shortByteSeparatorNoPrefixAndSuffix = str3.length() == 0 && str4.length() == 0 && str2.length() <= 1;
            this.ignoreCase = (HexFormatKt.isCaseSensitive(str) || HexFormatKt.isCaseSensitive(str2) || HexFormatKt.isCaseSensitive(str3) || HexFormatKt.isCaseSensitive(str4)) ? true : z;
        }

        public final int getBytesPerLine() {
            return this.bytesPerLine;
        }

        public final int getBytesPerGroup() {
            return this.bytesPerGroup;
        }

        public final String getGroupSeparator() {
            return this.groupSeparator;
        }

        public final String getByteSeparator() {
            return this.byteSeparator;
        }

        public final String getBytePrefix() {
            return this.bytePrefix;
        }

        public final String getByteSuffix() {
            return this.byteSuffix;
        }

        public final boolean getNoLineAndGroupSeparator$kotlin_stdlib() {
            return this.noLineAndGroupSeparator;
        }

        public final boolean getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() {
            return this.shortByteSeparatorNoPrefixAndSuffix;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append("BytesHexFormat(");
            Intrinsics.checkNotNullExpressionValue(append, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append.append(10), "append(...)");
            Intrinsics.checkNotNullExpressionValue(appendOptionsTo$kotlin_stdlib(sb, "    ").append(10), "append(...)");
            sb.append(")");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder sb, String str) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            Intrinsics.checkNotNullParameter(str, "indent");
            StringBuilder append = sb.append(str).append("bytesPerLine = ").append(this.bytesPerLine);
            Intrinsics.checkNotNullExpressionValue(append, "append(...)");
            StringBuilder append2 = append.append(",");
            Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append2.append(10), "append(...)");
            StringBuilder append3 = sb.append(str).append("bytesPerGroup = ").append(this.bytesPerGroup);
            Intrinsics.checkNotNullExpressionValue(append3, "append(...)");
            StringBuilder append4 = append3.append(",");
            Intrinsics.checkNotNullExpressionValue(append4, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append4.append(10), "append(...)");
            StringBuilder append5 = sb.append(str).append("groupSeparator = \"").append(this.groupSeparator);
            Intrinsics.checkNotNullExpressionValue(append5, "append(...)");
            StringBuilder append6 = append5.append("\",");
            Intrinsics.checkNotNullExpressionValue(append6, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append6.append(10), "append(...)");
            StringBuilder append7 = sb.append(str).append("byteSeparator = \"").append(this.byteSeparator);
            Intrinsics.checkNotNullExpressionValue(append7, "append(...)");
            StringBuilder append8 = append7.append("\",");
            Intrinsics.checkNotNullExpressionValue(append8, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append8.append(10), "append(...)");
            StringBuilder append9 = sb.append(str).append("bytePrefix = \"").append(this.bytePrefix);
            Intrinsics.checkNotNullExpressionValue(append9, "append(...)");
            StringBuilder append10 = append9.append("\",");
            Intrinsics.checkNotNullExpressionValue(append10, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append10.append(10), "append(...)");
            sb.append(str).append("byteSuffix = \"").append(this.byteSuffix).append("\"");
            return sb;
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\t¨\u0006\u001f"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "", "()V", "value", "", "bytePrefix", "getBytePrefix", "()Ljava/lang/String;", "setBytePrefix", "(Ljava/lang/String;)V", "byteSeparator", "getByteSeparator", "setByteSeparator", "byteSuffix", "getByteSuffix", "setByteSuffix", "", "bytesPerGroup", "getBytesPerGroup", "()I", "setBytesPerGroup", "(I)V", "bytesPerLine", "getBytesPerLine", "setBytesPerLine", "groupSeparator", "getGroupSeparator", "setGroupSeparator", "build", "Lkotlin/text/HexFormat$BytesHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: HexFormat.kt */
        public static final class Builder {
            private String bytePrefix = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytePrefix();
            private String byteSeparator = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSeparator();
            private String byteSuffix = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSuffix();
            private int bytesPerGroup = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerGroup();
            private int bytesPerLine = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerLine();
            private String groupSeparator = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getGroupSeparator();

            public final int getBytesPerLine() {
                return this.bytesPerLine;
            }

            public final void setBytesPerLine(int i) {
                if (i > 0) {
                    this.bytesPerLine = i;
                    return;
                }
                throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerLine, but was " + i);
            }

            public final int getBytesPerGroup() {
                return this.bytesPerGroup;
            }

            public final void setBytesPerGroup(int i) {
                if (i > 0) {
                    this.bytesPerGroup = i;
                    return;
                }
                throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerGroup, but was " + i);
            }

            public final String getGroupSeparator() {
                return this.groupSeparator;
            }

            public final void setGroupSeparator(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.groupSeparator = str;
            }

            public final String getByteSeparator() {
                return this.byteSeparator;
            }

            public final void setByteSeparator(String str) {
                Intrinsics.checkNotNullParameter(str, "value");
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, 10, false, 2, (Object) null) || StringsKt.contains$default(charSequence, 13, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSeparator, but was " + str);
                }
                this.byteSeparator = str;
            }

            public final String getBytePrefix() {
                return this.bytePrefix;
            }

            public final void setBytePrefix(String str) {
                Intrinsics.checkNotNullParameter(str, "value");
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, 10, false, 2, (Object) null) || StringsKt.contains$default(charSequence, 13, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in bytePrefix, but was " + str);
                }
                this.bytePrefix = str;
            }

            public final String getByteSuffix() {
                return this.byteSuffix;
            }

            public final void setByteSuffix(String str) {
                Intrinsics.checkNotNullParameter(str, "value");
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, 10, false, 2, (Object) null) || StringsKt.contains$default(charSequence, 13, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSuffix, but was " + str);
                }
                this.byteSuffix = str;
            }

            public final BytesHexFormat build$kotlin_stdlib() {
                return new BytesHexFormat(this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix);
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat$BytesHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$BytesHexFormat;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: HexFormat.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final BytesHexFormat getDefault$kotlin_stdlib() {
                return BytesHexFormat.Default;
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J%\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\n\u0010\u0014\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u0015\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u0014\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001a"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat;", "", "prefix", "", "suffix", "removeLeadingZeros", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "ignoreCase", "getIgnoreCase$kotlin_stdlib", "()Z", "isDigitsOnly", "isDigitsOnly$kotlin_stdlib", "getPrefix", "()Ljava/lang/String;", "getRemoveLeadingZeros", "getSuffix", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "toString", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HexFormat.kt */
    public static final class NumberHexFormat {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final NumberHexFormat Default = new NumberHexFormat("", "", false);
        private final boolean ignoreCase;
        private final boolean isDigitsOnly;
        private final String prefix;
        private final boolean removeLeadingZeros;
        private final String suffix;

        public NumberHexFormat(String str, String str2, boolean z) {
            Intrinsics.checkNotNullParameter(str, "prefix");
            Intrinsics.checkNotNullParameter(str2, "suffix");
            this.prefix = str;
            this.suffix = str2;
            this.removeLeadingZeros = z;
            boolean z2 = true;
            this.isDigitsOnly = str.length() == 0 && str2.length() == 0;
            if (!HexFormatKt.isCaseSensitive(str) && !HexFormatKt.isCaseSensitive(str2)) {
                z2 = false;
            }
            this.ignoreCase = z2;
        }

        public final String getPrefix() {
            return this.prefix;
        }

        public final String getSuffix() {
            return this.suffix;
        }

        public final boolean getRemoveLeadingZeros() {
            return this.removeLeadingZeros;
        }

        public final boolean isDigitsOnly$kotlin_stdlib() {
            return this.isDigitsOnly;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append("NumberHexFormat(");
            Intrinsics.checkNotNullExpressionValue(append, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append.append(10), "append(...)");
            Intrinsics.checkNotNullExpressionValue(appendOptionsTo$kotlin_stdlib(sb, "    ").append(10), "append(...)");
            sb.append(")");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder sb, String str) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            Intrinsics.checkNotNullParameter(str, "indent");
            StringBuilder append = sb.append(str).append("prefix = \"").append(this.prefix);
            Intrinsics.checkNotNullExpressionValue(append, "append(...)");
            StringBuilder append2 = append.append("\",");
            Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append2.append(10), "append(...)");
            StringBuilder append3 = sb.append(str).append("suffix = \"").append(this.suffix);
            Intrinsics.checkNotNullExpressionValue(append3, "append(...)");
            StringBuilder append4 = append3.append("\",");
            Intrinsics.checkNotNullExpressionValue(append4, "append(...)");
            Intrinsics.checkNotNullExpressionValue(append4.append(10), "append(...)");
            sb.append(str).append("removeLeadingZeros = ").append(this.removeLeadingZeros);
            return sb;
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\t¨\u0006\u0016"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "", "()V", "value", "", "prefix", "getPrefix", "()Ljava/lang/String;", "setPrefix", "(Ljava/lang/String;)V", "removeLeadingZeros", "", "getRemoveLeadingZeros", "()Z", "setRemoveLeadingZeros", "(Z)V", "suffix", "getSuffix", "setSuffix", "build", "Lkotlin/text/HexFormat$NumberHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: HexFormat.kt */
        public static final class Builder {
            private String prefix = NumberHexFormat.Companion.getDefault$kotlin_stdlib().getPrefix();
            private boolean removeLeadingZeros = NumberHexFormat.Companion.getDefault$kotlin_stdlib().getRemoveLeadingZeros();
            private String suffix = NumberHexFormat.Companion.getDefault$kotlin_stdlib().getSuffix();

            public final String getPrefix() {
                return this.prefix;
            }

            public final void setPrefix(String str) {
                Intrinsics.checkNotNullParameter(str, "value");
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, 10, false, 2, (Object) null) || StringsKt.contains$default(charSequence, 13, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was " + str);
                }
                this.prefix = str;
            }

            public final String getSuffix() {
                return this.suffix;
            }

            public final void setSuffix(String str) {
                Intrinsics.checkNotNullParameter(str, "value");
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, 10, false, 2, (Object) null) || StringsKt.contains$default(charSequence, 13, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in suffix, but was " + str);
                }
                this.suffix = str;
            }

            public final boolean getRemoveLeadingZeros() {
                return this.removeLeadingZeros;
            }

            public final void setRemoveLeadingZeros(boolean z) {
                this.removeLeadingZeros = z;
            }

            public final NumberHexFormat build$kotlin_stdlib() {
                return new NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros);
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat$NumberHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$NumberHexFormat;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: HexFormat.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final NumberHexFormat getDefault$kotlin_stdlib() {
                return NumberHexFormat.Default;
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0001¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0001J%\u0010\u0007\u001a\u00020\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\b\u0018H\bø\u0001\u0000J%\u0010\n\u001a\u00020\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\b\u0018H\bø\u0001\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0019"}, d2 = {"Lkotlin/text/HexFormat$Builder;", "", "()V", "_bytes", "Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "_number", "Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "bytes", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "number", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "upperCase", "", "getUpperCase", "()Z", "setUpperCase", "(Z)V", "build", "Lkotlin/text/HexFormat;", "", "builderAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HexFormat.kt */
    public static final class Builder {
        private BytesHexFormat.Builder _bytes;
        private NumberHexFormat.Builder _number;
        private boolean upperCase = HexFormat.Companion.getDefault().getUpperCase();

        public final boolean getUpperCase() {
            return this.upperCase;
        }

        public final void setUpperCase(boolean z) {
            this.upperCase = z;
        }

        public final BytesHexFormat.Builder getBytes() {
            if (this._bytes == null) {
                this._bytes = new BytesHexFormat.Builder();
            }
            BytesHexFormat.Builder builder = this._bytes;
            Intrinsics.checkNotNull(builder);
            return builder;
        }

        public final NumberHexFormat.Builder getNumber() {
            if (this._number == null) {
                this._number = new NumberHexFormat.Builder();
            }
            NumberHexFormat.Builder builder = this._number;
            Intrinsics.checkNotNull(builder);
            return builder;
        }

        private final void bytes(Function1<? super BytesHexFormat.Builder, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "builderAction");
            function1.invoke(getBytes());
        }

        private final void number(Function1<? super NumberHexFormat.Builder, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "builderAction");
            function1.invoke(getNumber());
        }

        public final HexFormat build() {
            BytesHexFormat bytesHexFormat;
            NumberHexFormat numberHexFormat;
            boolean z = this.upperCase;
            BytesHexFormat.Builder builder = this._bytes;
            if (builder == null || (bytesHexFormat = builder.build$kotlin_stdlib()) == null) {
                bytesHexFormat = BytesHexFormat.Companion.getDefault$kotlin_stdlib();
            }
            NumberHexFormat.Builder builder2 = this._number;
            if (builder2 == null || (numberHexFormat = builder2.build$kotlin_stdlib()) == null) {
                numberHexFormat = NumberHexFormat.Companion.getDefault$kotlin_stdlib();
            }
            return new HexFormat(z, bytesHexFormat, numberHexFormat);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/text/HexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat;", "getDefault", "()Lkotlin/text/HexFormat;", "UpperCase", "getUpperCase", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HexFormat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HexFormat getDefault() {
            return HexFormat.Default;
        }

        public final HexFormat getUpperCase() {
            return HexFormat.UpperCase;
        }
    }
}
