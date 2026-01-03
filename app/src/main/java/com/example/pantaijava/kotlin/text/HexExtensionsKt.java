package kotlin.text;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.HexFormat;

@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nH\u0002\u001a(\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0002\u001a@\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0000\u001a@\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0000\u001a \u0010\u001b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a5\u0010\u001c\u001a\u00020\r*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0007H\b\u001a$\u0010#\u001a\u00020$*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010&\u001a\u00020\rH\u0002\u001a\u001c\u0010'\u001a\u00020\r*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rH\u0002\u001a<\u0010(\u001a\u00020$*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020\rH\u0002\u001a\u0015\u0010+\u001a\u00020\r*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\rH\b\u001a,\u0010,\u001a\u00020\r*\u00020-2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\rH\u0002\u001a<\u0010,\u001a\u00020\r*\u00020-2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\rH\u0002\u001a*\u00104\u001a\u000205*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0003\u001a\u0016\u00104\u001a\u000205*\u00020\u00072\b\b\u0002\u00106\u001a\u000207H\u0007\u001a*\u00108\u001a\u00020-*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0003\u001a\u0016\u00108\u001a\u00020-*\u00020\u00072\b\b\u0002\u00106\u001a\u000207H\u0007\u001a&\u00109\u001a\u0004\u0018\u00010-*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0003\u001a&\u0010<\u001a\u0004\u0018\u00010-*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0003\u001a&\u0010=\u001a\u0004\u0018\u00010-*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0003\u001a$\u0010>\u001a\u00020-*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0003\u001a*\u0010?\u001a\u00020\r*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0003\u001a\u0016\u0010?\u001a\u00020\r*\u00020\u00072\b\b\u0002\u00106\u001a\u000207H\u0007\u001a,\u0010@\u001a\u00020\r*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u00106\u001a\u0002072\u0006\u0010&\u001a\u00020\rH\u0003\u001a*\u0010A\u001a\u00020\n*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0003\u001a\u0016\u0010A\u001a\u00020\n*\u00020\u00072\b\b\u0002\u00106\u001a\u000207H\u0007\u001a,\u0010B\u001a\u00020\n*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u00106\u001a\u0002072\u0006\u0010&\u001a\u00020\rH\u0003\u001a*\u0010C\u001a\u00020D*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0003\u001a\u0016\u0010C\u001a\u00020D*\u00020\u00072\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u0015\u0010E\u001a\u00020\n*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\rH\b\u001a\u0014\u0010F\u001a\u000205*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\rH\u0002\u001a\u001c\u0010G\u001a\u00020\r*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rH\u0002\u001a\u001c\u0010H\u001a\u00020\n*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rH\u0002\u001a\u0014\u0010I\u001a\u00020J*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\rH\u0002\u001a,\u0010K\u001a\u00020$*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010L\u001a\u00020!H\u0002\u001a,\u0010M\u001a\u00020$*\u00020\u00072\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0002\u001a,\u0010N\u001a\u00020$*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0002\u001a\u001c\u0010O\u001a\u00020\r*\u00020\u00072\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\rH\u0002\u001a\u0016\u0010P\u001a\u00020\u0007*\u0002052\b\b\u0002\u00106\u001a\u000207H\u0007\u001a*\u0010P\u001a\u00020\u0007*\u00020-2\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u0016\u0010P\u001a\u00020\u0007*\u00020-2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u0016\u0010P\u001a\u00020\u0007*\u00020\r2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u0016\u0010P\u001a\u00020\u0007*\u00020\n2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u0016\u0010P\u001a\u00020\u0007*\u00020D2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a$\u0010Q\u001a\u00020\u0007*\u00020\n2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00072\u0006\u0010U\u001a\u00020\rH\u0003\u001a,\u0010V\u001a\u00020\u0007*\u00020-2\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;2\u0006\u0010.\u001a\u00020\u0001H\u0003\u001a,\u0010W\u001a\u00020\u0007*\u00020-2\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;2\u0006\u0010.\u001a\u00020\u0001H\u0003\u001a,\u0010X\u001a\u00020\u0007*\u00020-2\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;2\u0006\u0010.\u001a\u00020\u0001H\u0003\u001a,\u0010Y\u001a\u00020\u0007*\u00020-2\u0006\u0010%\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010:\u001a\u00020;2\u0006\u0010.\u001a\u00020\u0001H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"BYTE_TO_LOWER_CASE_HEX_DIGITS", "", "BYTE_TO_UPPER_CASE_HEX_DIGITS", "HEX_DIGITS_TO_DECIMAL", "HEX_DIGITS_TO_LONG_DECIMAL", "", "LOWER_CASE_HEX_DIGITS", "", "UPPER_CASE_HEX_DIGITS", "charsPerSet", "", "charsPerElement", "elementsPerSet", "", "elementSeparatorLength", "checkFormatLength", "formatLength", "formattedStringLength", "numberOfBytes", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "parsedByteArrayMaxSize", "stringLength", "wholeElementsPerSet", "checkContainsAt", "index", "endIndex", "part", "ignoreCase", "", "partName", "checkMaxDigits", "", "startIndex", "maxDigits", "checkNewLineAt", "checkPrefixSuffixMaxDigits", "prefix", "suffix", "decimalFromHexDigitAt", "formatByteAt", "", "byteToDigits", "destination", "", "destinationOffset", "bytePrefix", "byteSuffix", "hexToByte", "", "format", "Lkotlin/text/HexFormat;", "hexToByteArray", "hexToByteArrayNoLineAndGroupSeparator", "bytesFormat", "Lkotlin/text/HexFormat$BytesHexFormat;", "hexToByteArrayNoLineAndGroupSeparatorSlowPath", "hexToByteArrayShortByteSeparatorNoPrefixAndSuffix", "hexToByteArraySlowPath", "hexToInt", "hexToIntImpl", "hexToLong", "hexToLongImpl", "hexToShort", "", "longDecimalFromHexDigitAt", "parseByteAt", "parseInt", "parseLong", "throwInvalidDigitAt", "", "throwInvalidNumberOfDigits", "requireMaxLength", "throwInvalidPrefixSuffix", "throwNotContainedAt", "toCharArrayIfNotEmpty", "toHexString", "toHexStringImpl", "numberFormat", "Lkotlin/text/HexFormat$NumberHexFormat;", "digits", "bits", "toHexStringNoLineAndGroupSeparator", "toHexStringNoLineAndGroupSeparatorSlowPath", "toHexStringShortByteSeparatorNoPrefixAndSuffix", "toHexStringSlowPath", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: HexExtensions.kt */
public final class HexExtensionsKt {
    private static final int[] BYTE_TO_LOWER_CASE_HEX_DIGITS;
    private static final int[] BYTE_TO_UPPER_CASE_HEX_DIGITS;
    private static final int[] HEX_DIGITS_TO_DECIMAL;
    private static final long[] HEX_DIGITS_TO_LONG_DECIMAL;
    private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
    private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    static {
        int[] iArr = new int[256];
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = LOWER_CASE_HEX_DIGITS.charAt(i2 & 15) | (LOWER_CASE_HEX_DIGITS.charAt(i2 >> 4) << 8);
        }
        BYTE_TO_LOWER_CASE_HEX_DIGITS = iArr;
        int[] iArr2 = new int[256];
        for (int i3 = 0; i3 < 256; i3++) {
            iArr2[i3] = UPPER_CASE_HEX_DIGITS.charAt(i3 & 15) | (UPPER_CASE_HEX_DIGITS.charAt(i3 >> 4) << 8);
        }
        BYTE_TO_UPPER_CASE_HEX_DIGITS = iArr2;
        int[] iArr3 = new int[256];
        for (int i4 = 0; i4 < 256; i4++) {
            iArr3[i4] = -1;
        }
        CharSequence charSequence = LOWER_CASE_HEX_DIGITS;
        int i5 = 0;
        int i6 = 0;
        while (i5 < charSequence.length()) {
            iArr3[charSequence.charAt(i5)] = i6;
            i5++;
            i6++;
        }
        CharSequence charSequence2 = UPPER_CASE_HEX_DIGITS;
        int i7 = 0;
        int i8 = 0;
        while (i7 < charSequence2.length()) {
            iArr3[charSequence2.charAt(i7)] = i8;
            i7++;
            i8++;
        }
        HEX_DIGITS_TO_DECIMAL = iArr3;
        long[] jArr = new long[256];
        for (int i9 = 0; i9 < 256; i9++) {
            jArr[i9] = -1;
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < r4.length()) {
            jArr[r4.charAt(i10)] = (long) i11;
            i10++;
            i11++;
        }
        int i12 = 0;
        while (i < r5.length()) {
            jArr[r5.charAt(i)] = (long) i12;
            i++;
            i12++;
        }
        HEX_DIGITS_TO_LONG_DECIMAL = jArr;
    }

    public static final String toHexString(byte[] bArr, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexString(bArr, 0, bArr.length, hexFormat);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, hexFormat);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, i, i2, hexFormat);
    }

    public static final String toHexString(byte[] bArr, int i, int i2, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, bArr.length);
        if (i == i2) {
            return "";
        }
        int[] iArr = hexFormat.getUpperCase() ? BYTE_TO_UPPER_CASE_HEX_DIGITS : BYTE_TO_LOWER_CASE_HEX_DIGITS;
        HexFormat.BytesHexFormat bytes = hexFormat.getBytes();
        if (bytes.getNoLineAndGroupSeparator$kotlin_stdlib()) {
            return toHexStringNoLineAndGroupSeparator(bArr, i, i2, bytes, iArr);
        }
        return toHexStringSlowPath(bArr, i, i2, bytes, iArr);
    }

    private static final String toHexStringNoLineAndGroupSeparator(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        if (bytesHexFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib()) {
            return toHexStringShortByteSeparatorNoPrefixAndSuffix(bArr, i, i2, bytesHexFormat, iArr);
        }
        return toHexStringNoLineAndGroupSeparatorSlowPath(bArr, i, i2, bytesHexFormat, iArr);
    }

    private static final String toHexStringShortByteSeparatorNoPrefixAndSuffix(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        int length = bytesHexFormat.getByteSeparator().length();
        if (length <= 1) {
            int i3 = i2 - i;
            int i4 = 0;
            if (length == 0) {
                char[] cArr = new char[checkFormatLength(((long) i3) * 2)];
                while (i < i2) {
                    i4 = formatByteAt(bArr, i, iArr, cArr, i4);
                    i++;
                }
                return StringsKt.concatToString(cArr);
            }
            char[] cArr2 = new char[checkFormatLength((((long) i3) * 3) - 1)];
            char charAt = bytesHexFormat.getByteSeparator().charAt(0);
            int formatByteAt = formatByteAt(bArr, i, iArr, cArr2, 0);
            for (int i5 = i + 1; i5 < i2; i5++) {
                cArr2[formatByteAt] = charAt;
                formatByteAt = formatByteAt(bArr, i5, iArr, cArr2, formatByteAt + 1);
            }
            return StringsKt.concatToString(cArr2);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final String toHexStringNoLineAndGroupSeparatorSlowPath(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        String bytePrefix = bytesHexFormat.getBytePrefix();
        String byteSuffix = bytesHexFormat.getByteSuffix();
        String byteSeparator = bytesHexFormat.getByteSeparator();
        char[] cArr = new char[formattedStringLength(i2 - i, byteSeparator.length(), bytePrefix.length(), byteSuffix.length())];
        int formatByteAt = formatByteAt(bArr, i, bytePrefix, byteSuffix, iArr, cArr, 0);
        while (true) {
            i++;
            if (i >= i2) {
                return StringsKt.concatToString(cArr);
            }
            formatByteAt = formatByteAt(bArr, i, bytePrefix, byteSuffix, iArr, cArr, toCharArrayIfNotEmpty(byteSeparator, cArr, formatByteAt));
        }
    }

    private static final String toHexStringSlowPath(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        int i3;
        int i4;
        int i5 = i2;
        int bytesPerLine = bytesHexFormat.getBytesPerLine();
        int bytesPerGroup = bytesHexFormat.getBytesPerGroup();
        String bytePrefix = bytesHexFormat.getBytePrefix();
        String byteSuffix = bytesHexFormat.getByteSuffix();
        String byteSeparator = bytesHexFormat.getByteSeparator();
        String groupSeparator = bytesHexFormat.getGroupSeparator();
        int formattedStringLength = formattedStringLength(i5 - i, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
        char[] cArr = new char[formattedStringLength];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < i5; i9++) {
            if (i7 == bytesPerLine) {
                cArr[i6] = 10;
                i6++;
                i4 = 0;
                i3 = 0;
            } else if (i8 == bytesPerGroup) {
                i6 = toCharArrayIfNotEmpty(groupSeparator, cArr, i6);
                i4 = i7;
                i3 = 0;
            } else {
                i4 = i7;
                i3 = i8;
            }
            if (i3 != 0) {
                i6 = toCharArrayIfNotEmpty(byteSeparator, cArr, i6);
            }
            i6 = formatByteAt(bArr, i9, bytePrefix, byteSuffix, iArr, cArr, i6);
            i8 = i3 + 1;
            i7 = i4 + 1;
        }
        if (i6 == formattedStringLength) {
            return StringsKt.concatToString(cArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private static final int formatByteAt(byte[] bArr, int i, String str, String str2, int[] iArr, char[] cArr, int i2) {
        return toCharArrayIfNotEmpty(str2, cArr, formatByteAt(bArr, i, iArr, cArr, toCharArrayIfNotEmpty(str, cArr, i2)));
    }

    private static final int formatByteAt(byte[] bArr, int i, int[] iArr, char[] cArr, int i2) {
        int i3 = iArr[bArr[i] & UByte.MAX_VALUE];
        cArr[i2] = (char) (i3 >> 8);
        cArr[i2 + 1] = (char) (i3 & 255);
        return i2 + 2;
    }

    private static final int formattedStringLength(int i, int i2, int i3, int i4) {
        if (i > 0) {
            long j = (long) i2;
            return checkFormatLength((((long) i) * (((((long) i3) + 2) + ((long) i4)) + j)) - j);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static final int formattedStringLength(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i > 0) {
            int i8 = i - 1;
            int i9 = i8 / i2;
            int i10 = (i2 - 1) / i3;
            int i11 = i % i2;
            if (i11 != 0) {
                i2 = i11;
            }
            int i12 = (i10 * i9) + ((i2 - 1) / i3);
            return checkFormatLength(((long) i9) + (((long) i12) * ((long) i4)) + (((long) ((i8 - i9) - i12)) * ((long) i5)) + (((long) i) * (((long) i6) + 2 + ((long) i7))));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final int checkFormatLength(long j) {
        if (0 <= j && j <= 2147483647L) {
            return (int) j;
        }
        throw new IllegalArgumentException("The resulting string length is too big: " + ULong.m410toStringimpl(ULong.m364constructorimpl(j)));
    }

    public static final byte[] hexToByteArray(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToByteArray(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ byte[] hexToByteArray$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, hexFormat);
    }

    static /* synthetic */ byte[] hexToByteArray$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, i, i2, hexFormat);
    }

    private static final byte[] hexToByteArray(String str, int i, int i2, HexFormat hexFormat) {
        byte[] hexToByteArrayNoLineAndGroupSeparator;
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        if (i == i2) {
            return new byte[0];
        }
        HexFormat.BytesHexFormat bytes = hexFormat.getBytes();
        if (!bytes.getNoLineAndGroupSeparator$kotlin_stdlib() || (hexToByteArrayNoLineAndGroupSeparator = hexToByteArrayNoLineAndGroupSeparator(str, i, i2, bytes)) == null) {
            return hexToByteArraySlowPath(str, i, i2, bytes);
        }
        return hexToByteArrayNoLineAndGroupSeparator;
    }

    private static final byte[] hexToByteArrayNoLineAndGroupSeparator(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        if (bytesHexFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib()) {
            return hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(str, i, i2, bytesHexFormat);
        }
        return hexToByteArrayNoLineAndGroupSeparatorSlowPath(str, i, i2, bytesHexFormat);
    }

    private static final byte[] hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        int length = bytesHexFormat.getByteSeparator().length();
        if (length <= 1) {
            int i3 = i2 - i;
            int i4 = 2;
            if (length == 0) {
                if ((i3 & 1) != 0) {
                    return null;
                }
                int i5 = i3 >> 1;
                byte[] bArr = new byte[i5];
                int i6 = 0;
                for (int i7 = 0; i7 < i5; i7++) {
                    bArr[i7] = parseByteAt(str, i6);
                    i6 += 2;
                }
                return bArr;
            } else if (i3 % 3 != 2) {
                return null;
            } else {
                int i8 = (i3 / 3) + 1;
                byte[] bArr2 = new byte[i8];
                char charAt = bytesHexFormat.getByteSeparator().charAt(0);
                bArr2[0] = parseByteAt(str, 0);
                for (int i9 = 1; i9 < i8; i9++) {
                    if (str.charAt(i4) != charAt) {
                        String byteSeparator = bytesHexFormat.getByteSeparator();
                        boolean ignoreCase$kotlin_stdlib = bytesHexFormat.getIgnoreCase$kotlin_stdlib();
                        CharSequence charSequence = byteSeparator;
                        if (charSequence.length() != 0) {
                            int length2 = charSequence.length();
                            for (int i10 = 0; i10 < length2; i10++) {
                                if (!CharsKt.equals(byteSeparator.charAt(i10), str.charAt(i4 + i10), ignoreCase$kotlin_stdlib)) {
                                    throwNotContainedAt(str, i4, i2, byteSeparator, "byte separator");
                                }
                            }
                            byteSeparator.length();
                        }
                    }
                    bArr2[i9] = parseByteAt(str, i4 + 1);
                    i4 += 3;
                }
                return bArr2;
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final byte[] hexToByteArrayNoLineAndGroupSeparatorSlowPath(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        String bytePrefix = bytesHexFormat.getBytePrefix();
        String byteSuffix = bytesHexFormat.getByteSuffix();
        String byteSeparator = bytesHexFormat.getByteSeparator();
        long length = (long) byteSeparator.length();
        long length2 = ((long) bytePrefix.length()) + 2 + ((long) byteSuffix.length()) + length;
        long j = (long) (i2 - i);
        int i3 = (int) ((j + length) / length2);
        if ((((long) i3) * length2) - length != j) {
            return null;
        }
        boolean ignoreCase$kotlin_stdlib = bytesHexFormat.getIgnoreCase$kotlin_stdlib();
        byte[] bArr = new byte[i3];
        CharSequence charSequence = bytePrefix;
        if (charSequence.length() != 0) {
            int length3 = charSequence.length();
            for (int i4 = 0; i4 < length3; i4++) {
                if (!CharsKt.equals(bytePrefix.charAt(i4), str.charAt(i + i4), ignoreCase$kotlin_stdlib)) {
                    throwNotContainedAt(str, i, i2, bytePrefix, "byte prefix");
                }
            }
            i += bytePrefix.length();
        }
        String str2 = byteSuffix + byteSeparator + bytePrefix;
        int i5 = i3 - 1;
        for (int i6 = 0; i6 < i5; i6++) {
            bArr[i6] = parseByteAt(str, r13);
            r13 += 2;
            CharSequence charSequence2 = str2;
            if (charSequence2.length() != 0) {
                int length4 = charSequence2.length();
                for (int i7 = 0; i7 < length4; i7++) {
                    if (!CharsKt.equals(str2.charAt(i7), str.charAt(r13 + i7), ignoreCase$kotlin_stdlib)) {
                        throwNotContainedAt(str, r13, i2, str2, "byte suffix + byte separator + byte prefix");
                    }
                }
                r13 += str2.length();
            }
        }
        bArr[i5] = parseByteAt(str, r13);
        int i8 = r13 + 2;
        CharSequence charSequence3 = byteSuffix;
        if (charSequence3.length() != 0) {
            int length5 = charSequence3.length();
            for (int i9 = 0; i9 < length5; i9++) {
                if (!CharsKt.equals(byteSuffix.charAt(i9), str.charAt(i8 + i9), ignoreCase$kotlin_stdlib)) {
                    throwNotContainedAt(str, i8, i2, byteSuffix, "byte suffix");
                }
            }
            byteSuffix.length();
        }
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final byte[] hexToByteArraySlowPath(java.lang.String r19, int r20, int r21, kotlin.text.HexFormat.BytesHexFormat r22) {
        /*
            r0 = r19
            r1 = r21
            int r9 = r22.getBytesPerLine()
            int r10 = r22.getBytesPerGroup()
            java.lang.String r11 = r22.getBytePrefix()
            java.lang.String r12 = r22.getByteSuffix()
            java.lang.String r13 = r22.getByteSeparator()
            java.lang.String r14 = r22.getGroupSeparator()
            boolean r15 = r22.getIgnoreCase$kotlin_stdlib()
            int r2 = r1 - r20
            int r5 = r14.length()
            int r6 = r13.length()
            int r7 = r11.length()
            int r8 = r12.length()
            r3 = r9
            r4 = r10
            int r2 = parsedByteArrayMaxSize(r2, r3, r4, r5, r6, r7, r8)
            byte[] r3 = new byte[r2]
            r5 = r20
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x003f:
            if (r5 >= r1) goto L_0x0164
            if (r7 != r9) goto L_0x004b
            int r5 = checkNewLineAt(r0, r5, r1)
            r7 = 0
        L_0x0048:
            r8 = 0
            goto L_0x00d1
        L_0x004b:
            if (r8 != r10) goto L_0x008a
            r8 = r14
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            int r16 = r8.length()
            if (r16 != 0) goto L_0x0059
            r16 = r7
            goto L_0x0087
        L_0x0059:
            int r8 = r8.length()
            r4 = 0
        L_0x005e:
            if (r4 >= r8) goto L_0x0080
            r16 = r7
            char r7 = r14.charAt(r4)
            r20 = r8
            int r8 = r5 + r4
            char r8 = r0.charAt(r8)
            boolean r7 = kotlin.text.CharsKt.equals(r7, r8, r15)
            if (r7 != 0) goto L_0x0079
            java.lang.String r7 = "group separator"
            throwNotContainedAt(r0, r5, r1, r14, r7)
        L_0x0079:
            int r4 = r4 + 1
            r8 = r20
            r7 = r16
            goto L_0x005e
        L_0x0080:
            r16 = r7
            int r4 = r14.length()
            int r5 = r5 + r4
        L_0x0087:
            r7 = r16
            goto L_0x0048
        L_0x008a:
            r16 = r7
            if (r8 == 0) goto L_0x00cd
            r4 = r13
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r7 = r4.length()
            if (r7 != 0) goto L_0x009a
            r17 = r8
            goto L_0x00c8
        L_0x009a:
            int r4 = r4.length()
            r7 = 0
        L_0x009f:
            if (r7 >= r4) goto L_0x00c1
            r20 = r4
            char r4 = r13.charAt(r7)
            r17 = r8
            int r8 = r5 + r7
            char r8 = r0.charAt(r8)
            boolean r4 = kotlin.text.CharsKt.equals(r4, r8, r15)
            if (r4 != 0) goto L_0x00ba
            java.lang.String r4 = "byte separator"
            throwNotContainedAt(r0, r5, r1, r13, r4)
        L_0x00ba:
            int r7 = r7 + 1
            r4 = r20
            r8 = r17
            goto L_0x009f
        L_0x00c1:
            r17 = r8
            int r4 = r13.length()
            int r5 = r5 + r4
        L_0x00c8:
            r7 = r16
            r8 = r17
            goto L_0x00d1
        L_0x00cd:
            r17 = r8
            r7 = r16
        L_0x00d1:
            r4 = 1
            int r7 = r7 + r4
            int r8 = r8 + r4
            r16 = r11
            java.lang.CharSequence r16 = (java.lang.CharSequence) r16
            int r17 = r16.length()
            if (r17 != 0) goto L_0x00e3
            r16 = r7
            r18 = r8
            goto L_0x0113
        L_0x00e3:
            int r4 = r16.length()
            r16 = r7
            r7 = 0
        L_0x00ea:
            if (r7 >= r4) goto L_0x010c
            r17 = r4
            char r4 = r11.charAt(r7)
            r18 = r8
            int r8 = r5 + r7
            char r8 = r0.charAt(r8)
            boolean r4 = kotlin.text.CharsKt.equals(r4, r8, r15)
            if (r4 != 0) goto L_0x0105
            java.lang.String r4 = "byte prefix"
            throwNotContainedAt(r0, r5, r1, r11, r4)
        L_0x0105:
            int r7 = r7 + 1
            r4 = r17
            r8 = r18
            goto L_0x00ea
        L_0x010c:
            r18 = r8
            int r4 = r11.length()
            int r5 = r5 + r4
        L_0x0113:
            int r4 = r1 + -2
            if (r4 >= r5) goto L_0x011c
            r4 = 2
            r7 = 1
            throwInvalidNumberOfDigits(r0, r5, r1, r4, r7)
        L_0x011c:
            int r4 = r6 + 1
            byte r7 = parseByteAt(r0, r5)
            r3[r6] = r7
            int r5 = r5 + 2
            r6 = r12
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r7 = r6.length()
            if (r7 != 0) goto L_0x0132
            r20 = r4
            goto L_0x015c
        L_0x0132:
            int r6 = r6.length()
            r7 = 0
        L_0x0137:
            if (r7 >= r6) goto L_0x0155
            char r8 = r12.charAt(r7)
            r20 = r4
            int r4 = r5 + r7
            char r4 = r0.charAt(r4)
            boolean r4 = kotlin.text.CharsKt.equals(r8, r4, r15)
            if (r4 != 0) goto L_0x0150
            java.lang.String r4 = "byte suffix"
            throwNotContainedAt(r0, r5, r1, r12, r4)
        L_0x0150:
            int r7 = r7 + 1
            r4 = r20
            goto L_0x0137
        L_0x0155:
            r20 = r4
            int r4 = r12.length()
            int r5 = r5 + r4
        L_0x015c:
            r6 = r20
            r7 = r16
            r8 = r18
            goto L_0x003f
        L_0x0164:
            if (r6 != r2) goto L_0x0167
            goto L_0x0170
        L_0x0167:
            byte[] r3 = java.util.Arrays.copyOf(r3, r6)
            java.lang.String r0 = "copyOf(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
        L_0x0170:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.HexExtensionsKt.hexToByteArraySlowPath(java.lang.String, int, int, kotlin.text.HexFormat$BytesHexFormat):byte[]");
    }

    public static final int parsedByteArrayMaxSize(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long j;
        int i8;
        int i9;
        int i10 = i;
        int i11 = i2;
        int i12 = i3;
        int i13 = i4;
        int i14 = i5;
        if (i10 > 0) {
            long j2 = ((long) i6) + 2 + ((long) i7);
            long charsPerSet = charsPerSet(j2, i12, i14);
            if (i11 <= i12) {
                j = charsPerSet(j2, i11, i14);
            } else {
                j = charsPerSet(charsPerSet, i11 / i12, i13);
                int i15 = i11 % i12;
                if (i15 != 0) {
                    j = j + ((long) i13) + charsPerSet(j2, i15, i14);
                }
            }
            long j3 = (long) i10;
            long wholeElementsPerSet = wholeElementsPerSet(j3, j, 1);
            long j4 = j3 - ((j + 1) * wholeElementsPerSet);
            long wholeElementsPerSet2 = wholeElementsPerSet(j4, charsPerSet, i13);
            long j5 = j4 - ((charsPerSet + ((long) i13)) * wholeElementsPerSet2);
            long wholeElementsPerSet3 = wholeElementsPerSet(j5, j2, i14);
            if (j5 - ((j2 + ((long) i14)) * wholeElementsPerSet3) > 0) {
                i8 = i2;
                i9 = 1;
            } else {
                i9 = 0;
                i8 = i2;
            }
            return (int) ((wholeElementsPerSet * ((long) i8)) + (wholeElementsPerSet2 * ((long) i12)) + wholeElementsPerSet3 + ((long) i9));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final long charsPerSet(long j, int i, int i2) {
        if (i > 0) {
            long j2 = (long) i;
            return (j * j2) + (((long) i2) * (j2 - 1));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final long wholeElementsPerSet(long j, long j2, int i) {
        if (j <= 0 || j2 <= 0) {
            return 0;
        }
        long j3 = (long) i;
        return (j + j3) / (j2 + j3);
    }

    private static final int checkNewLineAt(String str, int i, int i2) {
        if (str.charAt(i) == 13) {
            int i3 = i + 1;
            if (i3 >= i2 || str.charAt(i3) != 10) {
                return i3;
            }
            return i + 2;
        } else if (str.charAt(i) == 10) {
            return i + 1;
        } else {
            throw new NumberFormatException("Expected a new line at index " + i + ", but was " + str.charAt(i));
        }
    }

    public static /* synthetic */ String toHexString$default(byte b, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(b, hexFormat);
    }

    public static final String toHexString(byte b, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        String str = hexFormat.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (!number.isDigitsOnly$kotlin_stdlib()) {
            return toHexStringImpl((long) b, number, str, 8);
        }
        char[] cArr = {str.charAt((b >> 4) & 15), str.charAt(b & 15)};
        if (number.getRemoveLeadingZeros()) {
            return StringsKt.concatToString$default(cArr, RangesKt.coerceAtMost((Integer.numberOfLeadingZeros(b & UByte.MAX_VALUE) - 24) >> 2, 1), 0, 2, (Object) null);
        }
        return StringsKt.concatToString(cArr);
    }

    public static final byte hexToByte(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToByte(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ byte hexToByte$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, hexFormat);
    }

    static /* synthetic */ byte hexToByte$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, i, i2, hexFormat);
    }

    private static final byte hexToByte(String str, int i, int i2, HexFormat hexFormat) {
        return (byte) hexToIntImpl(str, i, i2, hexFormat, 2);
    }

    public static /* synthetic */ String toHexString$default(short s, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(s, hexFormat);
    }

    public static final String toHexString(short s, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        String str = hexFormat.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (!number.isDigitsOnly$kotlin_stdlib()) {
            return toHexStringImpl((long) s, number, str, 16);
        }
        char[] cArr = {str.charAt((s >> 12) & 15), str.charAt((s >> 8) & 15), str.charAt((s >> 4) & 15), str.charAt(s & 15)};
        if (number.getRemoveLeadingZeros()) {
            return StringsKt.concatToString$default(cArr, RangesKt.coerceAtMost((Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16) >> 2, 3), 0, 2, (Object) null);
        }
        return StringsKt.concatToString(cArr);
    }

    public static final short hexToShort(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToShort(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ short hexToShort$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, hexFormat);
    }

    static /* synthetic */ short hexToShort$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, i, i2, hexFormat);
    }

    private static final short hexToShort(String str, int i, int i2, HexFormat hexFormat) {
        return (short) hexToIntImpl(str, i, i2, hexFormat, 4);
    }

    public static /* synthetic */ String toHexString$default(int i, HexFormat hexFormat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(i, hexFormat);
    }

    public static final String toHexString(int i, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        String str = hexFormat.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (!number.isDigitsOnly$kotlin_stdlib()) {
            return toHexStringImpl((long) i, number, str, 32);
        }
        char[] cArr = {str.charAt((i >> 28) & 15), str.charAt((i >> 24) & 15), str.charAt((i >> 20) & 15), str.charAt((i >> 16) & 15), str.charAt((i >> 12) & 15), str.charAt((i >> 8) & 15), str.charAt((i >> 4) & 15), str.charAt(i & 15)};
        if (number.getRemoveLeadingZeros()) {
            return StringsKt.concatToString$default(cArr, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros(i) >> 2, 7), 0, 2, (Object) null);
        }
        return StringsKt.concatToString(cArr);
    }

    public static final int hexToInt(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToInt(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ int hexToInt$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, hexFormat);
    }

    static /* synthetic */ int hexToInt$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, i, i2, hexFormat);
    }

    private static final int hexToInt(String str, int i, int i2, HexFormat hexFormat) {
        return hexToIntImpl(str, i, i2, hexFormat, 8);
    }

    public static /* synthetic */ String toHexString$default(long j, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(j, hexFormat);
    }

    public static final String toHexString(long j, HexFormat hexFormat) {
        long j2 = j;
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        String str = hexFormat.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (!number.isDigitsOnly$kotlin_stdlib()) {
            return toHexStringImpl(j2, number, str, 64);
        }
        char[] cArr = {str.charAt((int) ((j2 >> 60) & 15)), str.charAt((int) ((j2 >> 56) & 15)), str.charAt((int) ((j2 >> 52) & 15)), str.charAt((int) ((j2 >> 48) & 15)), str.charAt((int) ((j2 >> 44) & 15)), str.charAt((int) ((j2 >> 40) & 15)), str.charAt((int) ((j2 >> 36) & 15)), str.charAt((int) ((j2 >> 32) & 15)), str.charAt((int) ((j2 >> 28) & 15)), str.charAt((int) ((j2 >> 24) & 15)), str.charAt((int) ((j2 >> 20) & 15)), str.charAt((int) ((j2 >> 16) & 15)), str.charAt((int) ((j2 >> 12) & 15)), str.charAt((int) ((j2 >> 8) & 15)), str.charAt((int) ((j2 >> 4) & 15)), str.charAt((int) (15 & j2))};
        if (number.getRemoveLeadingZeros()) {
            return StringsKt.concatToString$default(cArr, RangesKt.coerceAtMost(Long.numberOfLeadingZeros(j) >> 2, 15), 0, 2, (Object) null);
        }
        return StringsKt.concatToString(cArr);
    }

    public static final long hexToLong(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToLong(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ long hexToLong$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, hexFormat);
    }

    static /* synthetic */ long hexToLong$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, i, i2, hexFormat);
    }

    private static final long hexToLong(String str, int i, int i2, HexFormat hexFormat) {
        return hexToLongImpl(str, i, i2, hexFormat, 16);
    }

    private static final String toHexStringImpl(long j, HexFormat.NumberHexFormat numberHexFormat, String str, int i) {
        if ((i & 3) == 0) {
            int i2 = i >> 2;
            String prefix = numberHexFormat.getPrefix();
            String suffix = numberHexFormat.getSuffix();
            boolean removeLeadingZeros = numberHexFormat.getRemoveLeadingZeros();
            int checkFormatLength = checkFormatLength(((long) prefix.length()) + ((long) i2) + ((long) suffix.length()));
            char[] cArr = new char[checkFormatLength];
            boolean z = removeLeadingZeros;
            int charArrayIfNotEmpty = toCharArrayIfNotEmpty(prefix, cArr, 0);
            int i3 = i;
            for (int i4 = 0; i4 < i2; i4++) {
                i3 -= 4;
                int i5 = (int) ((j >> i3) & 15);
                z = z && i5 == 0 && i3 > 0;
                if (!z) {
                    cArr[charArrayIfNotEmpty] = str.charAt(i5);
                    charArrayIfNotEmpty++;
                } else {
                    String str2 = str;
                }
            }
            int charArrayIfNotEmpty2 = toCharArrayIfNotEmpty(suffix, cArr, charArrayIfNotEmpty);
            if (charArrayIfNotEmpty2 == checkFormatLength) {
                return StringsKt.concatToString(cArr);
            }
            return StringsKt.concatToString$default(cArr, 0, charArrayIfNotEmpty2, 1, (Object) null);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final int toCharArrayIfNotEmpty(String str, char[] cArr, int i) {
        int length = str.length();
        if (length != 0) {
            if (length != 1) {
                int length2 = str.length();
                Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                str.getChars(0, length2, cArr, i);
            } else {
                cArr[i] = str.charAt(0);
            }
        }
        return i + str.length();
    }

    private static final int hexToIntImpl(String str, int i, int i2, HexFormat hexFormat, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (number.isDigitsOnly$kotlin_stdlib()) {
            checkMaxDigits(str, i, i2, i3);
            return parseInt(str, i, i2);
        }
        String prefix = number.getPrefix();
        String suffix = number.getSuffix();
        checkPrefixSuffixMaxDigits(str, i, i2, prefix, suffix, number.getIgnoreCase$kotlin_stdlib(), i3);
        return parseInt(str, i + prefix.length(), i2 - suffix.length());
    }

    private static final long hexToLongImpl(String str, int i, int i2, HexFormat hexFormat, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        HexFormat.NumberHexFormat number = hexFormat.getNumber();
        if (number.isDigitsOnly$kotlin_stdlib()) {
            checkMaxDigits(str, i, i2, i3);
            return parseLong(str, i, i2);
        }
        String prefix = number.getPrefix();
        String suffix = number.getSuffix();
        checkPrefixSuffixMaxDigits(str, i, i2, prefix, suffix, number.getIgnoreCase$kotlin_stdlib(), i3);
        return parseLong(str, i + prefix.length(), i2 - suffix.length());
    }

    private static final void checkPrefixSuffixMaxDigits(String str, int i, int i2, String str2, String str3, boolean z, int i3) {
        if ((i2 - i) - str2.length() <= str3.length()) {
            throwInvalidPrefixSuffix(str, i, i2, str2, str3);
        }
        CharSequence charSequence = str2;
        if (charSequence.length() != 0) {
            int length = charSequence.length();
            for (int i4 = 0; i4 < length; i4++) {
                if (!CharsKt.equals(str2.charAt(i4), str.charAt(i + i4), z)) {
                    throwNotContainedAt(str, i, i2, str2, "prefix");
                }
            }
            i += str2.length();
        }
        int length2 = i2 - str3.length();
        CharSequence charSequence2 = str3;
        if (charSequence2.length() != 0) {
            int length3 = charSequence2.length();
            for (int i5 = 0; i5 < length3; i5++) {
                if (!CharsKt.equals(str3.charAt(i5), str.charAt(length2 + i5), z)) {
                    throwNotContainedAt(str, length2, i2, str3, "suffix");
                }
            }
            str3.length();
        }
        checkMaxDigits(str, i, length2, i3);
    }

    private static final void checkMaxDigits(String str, int i, int i2, int i3) {
        if (i >= i2 || i2 - i > i3) {
            throwInvalidNumberOfDigits(str, i, i2, i3, false);
        }
    }

    private static final int checkContainsAt(String str, int i, int i2, String str2, boolean z, String str3) {
        CharSequence charSequence = str2;
        if (charSequence.length() == 0) {
            return i;
        }
        int length = charSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (!CharsKt.equals(str2.charAt(i3), str.charAt(i + i3), z)) {
                throwNotContainedAt(str, i, i2, str2, str3);
            }
        }
        return i + str2.length();
    }

    private static final int decimalFromHexDigitAt(String str, int i) {
        int i2;
        char charAt = str.charAt(i);
        if ((charAt >>> 8) == 0 && (i2 = HEX_DIGITS_TO_DECIMAL[charAt]) >= 0) {
            return i2;
        }
        throwInvalidDigitAt(str, i);
        throw new KotlinNothingValueException();
    }

    private static final long longDecimalFromHexDigitAt(String str, int i) {
        char charAt = str.charAt(i);
        if ((charAt >>> 8) == 0) {
            long j = HEX_DIGITS_TO_LONG_DECIMAL[charAt];
            if (j >= 0) {
                return j;
            }
        }
        throwInvalidDigitAt(str, i);
        throw new KotlinNothingValueException();
    }

    private static final void throwInvalidNumberOfDigits(String str, int i, int i2, int i3, boolean z) {
        String str2 = z ? "exactly" : "at most";
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        throw new NumberFormatException("Expected " + str2 + ' ' + i3 + " hexadecimal digits at index " + i + ", but was " + substring + " of length " + (i2 - i));
    }

    private static final void throwNotContainedAt(String str, int i, int i2, String str2, String str3) {
        int coerceAtMost = RangesKt.coerceAtMost(str2.length() + i, i2);
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, coerceAtMost);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        throw new NumberFormatException("Expected " + str3 + " \"" + str2 + "\" at index " + i + ", but was " + substring);
    }

    private static final void throwInvalidPrefixSuffix(String str, int i, int i2, String str2, String str3) {
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        throw new NumberFormatException("Expected a hexadecimal number with prefix \"" + str2 + "\" and suffix \"" + str3 + "\", but was " + substring);
    }

    private static final Void throwInvalidDigitAt(String str, int i) {
        throw new NumberFormatException("Expected a hexadecimal digit at index " + i + ", but was " + str.charAt(i));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r1 = HEX_DIGITS_TO_DECIMAL;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final byte parseByteAt(java.lang.String r4, int r5) {
        /*
            char r0 = r4.charAt(r5)
            int r1 = r0 >>> 8
            if (r1 != 0) goto L_0x002a
            int[] r1 = HEX_DIGITS_TO_DECIMAL
            r0 = r1[r0]
            if (r0 < 0) goto L_0x002a
            int r5 = r5 + 1
            char r2 = r4.charAt(r5)
            int r3 = r2 >>> 8
            if (r3 != 0) goto L_0x0021
            r1 = r1[r2]
            if (r1 < 0) goto L_0x0021
            int r4 = r0 << 4
            r4 = r4 | r1
            byte r4 = (byte) r4
            return r4
        L_0x0021:
            throwInvalidDigitAt(r4, r5)
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        L_0x002a:
            throwInvalidDigitAt(r4, r5)
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.HexExtensionsKt.parseByteAt(java.lang.String, int):byte");
    }

    private static final int parseInt(String str, int i, int i2) {
        int i3;
        int i4 = 0;
        while (i < i2) {
            int i5 = i4 << 4;
            char charAt = str.charAt(i);
            if ((charAt >>> 8) != 0 || (i3 = HEX_DIGITS_TO_DECIMAL[charAt]) < 0) {
                throwInvalidDigitAt(str, i);
                throw new KotlinNothingValueException();
            }
            i4 = i5 | i3;
            i++;
        }
        return i4;
    }

    private static final long parseLong(String str, int i, int i2) {
        long j = 0;
        while (i < i2) {
            long j2 = j << 4;
            char charAt = str.charAt(i);
            if ((charAt >>> 8) == 0) {
                long j3 = HEX_DIGITS_TO_LONG_DECIMAL[charAt];
                if (j3 >= 0) {
                    j = j2 | j3;
                    i++;
                }
            }
            throwInvalidDigitAt(str, i);
            throw new KotlinNothingValueException();
        }
        return j;
    }
}
