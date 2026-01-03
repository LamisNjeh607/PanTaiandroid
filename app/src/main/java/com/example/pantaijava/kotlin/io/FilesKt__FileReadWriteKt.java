package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\b\u001a!\u0010\u0013\u001a\u00020\u0014*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\b\u001aB\u0010\u0015\u001a\u00020\u0007*\u00020\b26\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00070\u0017\u001aJ\u0010\u0015\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u001c\u001a\u00020\u000326\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00070\u0017\u001a7\u0010\u001d\u001a\u00020\u0007*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00070\u001e\u001a\r\u0010 \u001a\u00020!*\u00020\bH\b\u001a\u0014\u0010\"\u001a\n #*\u0004\u0018\u00010\u00050\u0005*\u00020\u000fH\u0000\u001a\r\u0010$\u001a\u00020%*\u00020\bH\b\u001a\u0017\u0010&\u001a\u00020'*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\b\u001a\n\u0010(\u001a\u00020\n*\u00020\b\u001a\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0**\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a\u0014\u0010+\u001a\u00020\r*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a\u0017\u0010,\u001a\u00020-*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\b\u001aL\u0010.\u001a\u0002H/\"\u0004\b\u0000\u0010/*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0018\u00100\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r01\u0012\u0004\u0012\u0002H/0\u001eH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u00102\u001a\u0012\u00103\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u001c\u00104\u001a\u00020\u0007*\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a\u001c\u00105\u001a\u00020\u0007*\u0002062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a\u0017\u00107\u001a\u000208*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\b\u0002\u0007\n\u0005\b20\u0001¨\u00069"}, d2 = {"byteBufferForEncoding", "Ljava/nio/ByteBuffer;", "chunkSize", "", "encoder", "Ljava/nio/charset/CharsetEncoder;", "appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "newReplaceEncoder", "kotlin.jvm.PlatformType", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writeTextImpl", "Ljava/io/OutputStream;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/FilesKt")
/* compiled from: FileReadWrite.kt */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    static /* synthetic */ InputStreamReader reader$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    private static final InputStreamReader reader(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(File file, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i);
    }

    private static final BufferedReader bufferedReader(File file, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i);
    }

    static /* synthetic */ OutputStreamWriter writer$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    private static final OutputStreamWriter writer(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(File file, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i);
    }

    private static final BufferedWriter bufferedWriter(File file, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i);
    }

    static /* synthetic */ PrintWriter printWriter$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    private static final PrintWriter printWriter(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b6, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b9, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] readBytes(java.io.File r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r11)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch:{ all -> 0x00b3 }
            long r2 = r11.length()     // Catch:{ all -> 0x00b3 }
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            java.lang.String r5 = "File "
            if (r4 > 0) goto L_0x0090
            int r2 = (int) r2
            byte[] r3 = new byte[r2]     // Catch:{ all -> 0x00b3 }
            r4 = 0
            r6 = r2
            r7 = r4
        L_0x0022:
            if (r6 <= 0) goto L_0x002d
            int r8 = r1.read(r3, r7, r6)     // Catch:{ all -> 0x00b3 }
            if (r8 < 0) goto L_0x002d
            int r6 = r6 - r8
            int r7 = r7 + r8
            goto L_0x0022
        L_0x002d:
            java.lang.String r8 = "copyOf(...)"
            r9 = 0
            if (r6 <= 0) goto L_0x003a
            byte[] r3 = java.util.Arrays.copyOf(r3, r7)     // Catch:{ all -> 0x00b3 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r8)     // Catch:{ all -> 0x00b3 }
            goto L_0x006f
        L_0x003a:
            int r6 = r1.read()     // Catch:{ all -> 0x00b3 }
            r7 = -1
            if (r6 != r7) goto L_0x0042
            goto L_0x006f
        L_0x0042:
            kotlin.io.ExposingBufferByteArrayOutputStream r7 = new kotlin.io.ExposingBufferByteArrayOutputStream     // Catch:{ all -> 0x00b3 }
            r10 = 8193(0x2001, float:1.1481E-41)
            r7.<init>(r10)     // Catch:{ all -> 0x00b3 }
            r7.write(r6)     // Catch:{ all -> 0x00b3 }
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x00b3 }
            r6 = r7
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ all -> 0x00b3 }
            r10 = 2
            kotlin.io.ByteStreamsKt.copyTo$default(r1, r6, r4, r10, r9)     // Catch:{ all -> 0x00b3 }
            int r1 = r7.size()     // Catch:{ all -> 0x00b3 }
            int r1 = r1 + r2
            if (r1 < 0) goto L_0x0073
            byte[] r11 = r7.getBuffer()     // Catch:{ all -> 0x00b3 }
            byte[] r1 = java.util.Arrays.copyOf(r3, r1)     // Catch:{ all -> 0x00b3 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)     // Catch:{ all -> 0x00b3 }
            int r3 = r7.size()     // Catch:{ all -> 0x00b3 }
            byte[] r3 = kotlin.collections.ArraysKt.copyInto((byte[]) r11, (byte[]) r1, (int) r2, (int) r4, (int) r3)     // Catch:{ all -> 0x00b3 }
        L_0x006f:
            kotlin.io.CloseableKt.closeFinally(r0, r9)
            return r3
        L_0x0073:
            java.lang.OutOfMemoryError r1 = new java.lang.OutOfMemoryError     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r2.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r11 = r2.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = " is too big to fit in memory."
            java.lang.StringBuilder r11 = r11.append(r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00b3 }
            r1.<init>(r11)     // Catch:{ all -> 0x00b3 }
            throw r1     // Catch:{ all -> 0x00b3 }
        L_0x0090:
            java.lang.OutOfMemoryError r1 = new java.lang.OutOfMemoryError     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r4.<init>(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r11 = r4.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r4 = " is too big ("
            java.lang.StringBuilder r11 = r11.append(r4)     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r11 = r11.append(r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = " bytes) to fit in memory."
            java.lang.StringBuilder r11 = r11.append(r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00b3 }
            r1.<init>(r11)     // Catch:{ all -> 0x00b3 }
            throw r1     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00b5 }
        L_0x00b5:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r11)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeBytes(java.io.File r1, byte[] r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "array"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.FileOutputStream r1 = (java.io.FileOutputStream) r1     // Catch:{ all -> 0x001e }
            r1.write(r2)     // Catch:{ all -> 0x001e }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001e }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return
        L_0x001e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.writeBytes(java.io.File, byte[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendBytes(java.io.File r2, byte[] r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "array"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r1 = 1
            r0.<init>(r2, r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch:{ all -> 0x001f }
            r2.write(r3)     // Catch:{ all -> 0x001f }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001f }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            return
        L_0x001f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.appendBytes(java.io.File, byte[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readText(java.io.File r2, java.nio.charset.Charset r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r2)
            java.io.InputStream r1 = (java.io.InputStream) r1
            r0.<init>(r1, r3)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.InputStreamReader r2 = (java.io.InputStreamReader) r2     // Catch:{ all -> 0x0026 }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = kotlin.io.TextStreamsKt.readText(r2)     // Catch:{ all -> 0x0026 }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            return r2
        L_0x0026:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readText(java.io.File, java.nio.charset.Charset):java.lang.String");
    }

    public static /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, str, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeText(java.io.File r1, java.lang.String r2, java.nio.charset.Charset r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.FileOutputStream r1 = (java.io.FileOutputStream) r1     // Catch:{ all -> 0x0025 }
            java.io.OutputStream r1 = (java.io.OutputStream) r1     // Catch:{ all -> 0x0025 }
            kotlin.io.FilesKt.writeTextImpl(r1, r2, r3)     // Catch:{ all -> 0x0025 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0025 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return
        L_0x0025:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.writeText(java.io.File, java.lang.String, java.nio.charset.Charset):void");
    }

    public static /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, str, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendText(java.io.File r2, java.lang.String r3, java.nio.charset.Charset r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r1 = 1
            r0.<init>(r2, r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch:{ all -> 0x0026 }
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x0026 }
            kotlin.io.FilesKt.writeTextImpl(r2, r3, r4)     // Catch:{ all -> 0x0026 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0026 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            return
        L_0x0026:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.appendText(java.io.File, java.lang.String, java.nio.charset.Charset):void");
    }

    public static final void writeTextImpl(OutputStream outputStream, String str, Charset charset) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (str.length() < 16384) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            return;
        }
        CharsetEncoder newReplaceEncoder = FilesKt.newReplaceEncoder(charset);
        CharBuffer allocate = CharBuffer.allocate(8192);
        Intrinsics.checkNotNull(newReplaceEncoder);
        ByteBuffer byteBufferForEncoding = FilesKt.byteBufferForEncoding(8192, newReplaceEncoder);
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int min = Math.min(8192 - i2, str.length() - i);
            int i3 = i + min;
            char[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            str.getChars(i, i3, array, i2);
            allocate.limit(min + i2);
            i2 = 1;
            if (newReplaceEncoder.encode(allocate, byteBufferForEncoding, i3 == str.length()).isUnderflow()) {
                outputStream.write(byteBufferForEncoding.array(), 0, byteBufferForEncoding.position());
                if (allocate.position() != allocate.limit()) {
                    allocate.put(0, allocate.get());
                } else {
                    i2 = 0;
                }
                allocate.clear();
                byteBufferForEncoding.clear();
                i = i3;
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    public static final CharsetEncoder newReplaceEncoder(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "<this>");
        return charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    public static final ByteBuffer byteBufferForEncoding(int i, CharsetEncoder charsetEncoder) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "encoder");
        ByteBuffer allocate = ByteBuffer.allocate(i * ((int) ((float) Math.ceil((double) charsetEncoder.maxBytesPerChar()))));
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(...)");
        return allocate;
    }

    public static final void forEachBlock(File file, Function2<? super byte[], ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(function2, "action");
        FilesKt.forEachBlock(file, 4096, function2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void forEachBlock(java.io.File r2, int r3, kotlin.jvm.functions.Function2<? super byte[], ? super java.lang.Integer, kotlin.Unit> r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 512(0x200, float:7.175E-43)
            int r3 = kotlin.ranges.RangesKt.coerceAtLeast((int) r3, (int) r0)
            byte[] r3 = new byte[r3]
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.FileInputStream r2 = (java.io.FileInputStream) r2     // Catch:{ all -> 0x0031 }
        L_0x001c:
            int r1 = r2.read(r3)     // Catch:{ all -> 0x0031 }
            if (r1 > 0) goto L_0x0029
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0031 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            return
        L_0x0029:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0031 }
            r4.invoke(r3, r1)     // Catch:{ all -> 0x0031 }
            goto L_0x001c
        L_0x0031:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.forEachBlock(java.io.File, int, kotlin.jvm.functions.Function2):void");
    }

    public static /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    public static final void forEachLine(File file, Charset charset, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(function1, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(file), charset)), function1);
    }

    private static final FileInputStream inputStream(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new FileInputStream(file);
    }

    private static final FileOutputStream outputStream(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new FileOutputStream(file);
    }

    public static /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    public static final List<String> readLines(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ArrayList arrayList = new ArrayList();
        FilesKt.forEachLine(file, charset, new FilesKt__FileReadWriteKt$readLines$1(arrayList));
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0065, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Object useLines$default(java.io.File r1, java.nio.charset.Charset r2, kotlin.jvm.functions.Function1 r3, int r4, java.lang.Object r5) {
        /*
            r5 = 1
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0006
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8
        L_0x0006:
            java.lang.String r4 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.lang.String r4 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            java.io.InputStreamReader r4 = new java.io.InputStreamReader
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r1)
            java.io.InputStream r0 = (java.io.InputStream) r0
            r4.<init>(r0, r2)
            java.io.Reader r4 = (java.io.Reader) r4
            boolean r1 = r4 instanceof java.io.BufferedReader
            if (r1 == 0) goto L_0x002a
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4
            goto L_0x0032
        L_0x002a:
            java.io.BufferedReader r1 = new java.io.BufferedReader
            r2 = 8192(0x2000, float:1.14794E-41)
            r1.<init>(r4, r2)
            r4 = r1
        L_0x0032:
            java.io.Closeable r4 = (java.io.Closeable) r4
            r1 = 0
            r2 = r4
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ all -> 0x0055 }
            kotlin.sequences.Sequence r2 = kotlin.io.TextStreamsKt.lineSequence(r2)     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r3.invoke(r2)     // Catch:{ all -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            boolean r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r5, r5, r1)
            if (r1 == 0) goto L_0x004e
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r4, r1)
            goto L_0x0051
        L_0x004e:
            r4.close()
        L_0x0051:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r2
        L_0x0055:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r3 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            boolean r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r5, r5, r1)
            if (r1 != 0) goto L_0x0065
            r4.close()     // Catch:{ all -> 0x0068 }
            goto L_0x0068
        L_0x0065:
            kotlin.io.CloseableKt.closeFinally(r4, r2)
        L_0x0068:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines$default(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0060, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0063, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T useLines(java.io.File r2, java.nio.charset.Charset r3, kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r2)
            java.io.InputStream r1 = (java.io.InputStream) r1
            r0.<init>(r1, r3)
            java.io.Reader r0 = (java.io.Reader) r0
            boolean r2 = r0 instanceof java.io.BufferedReader
            if (r2 == 0) goto L_0x0024
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0
            goto L_0x002c
        L_0x0024:
            java.io.BufferedReader r2 = new java.io.BufferedReader
            r3 = 8192(0x2000, float:1.14794E-41)
            r2.<init>(r0, r3)
            r0 = r2
        L_0x002c:
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = 0
            r3 = 1
            r1 = r0
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x0050 }
            kotlin.sequences.Sequence r1 = kotlin.io.TextStreamsKt.lineSequence(r1)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r4.invoke(r1)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            boolean r2 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r3, r3, r2)
            if (r2 == 0) goto L_0x0049
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            goto L_0x004c
        L_0x0049:
            r0.close()
        L_0x004c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r4
        L_0x0050:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            boolean r2 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r3, r3, r2)
            if (r2 != 0) goto L_0x0060
            r0.close()     // Catch:{ all -> 0x0063 }
            goto L_0x0063
        L_0x0060:
            kotlin.io.CloseableKt.closeFinally(r0, r4)
        L_0x0063:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1):java.lang.Object");
    }
}
