package io.ionic.libs.ionfilesystemlib.helper.common;

import io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001aF\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\tH@¢\u0006\u0002\u0010\r\u001a\u001c\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0012H\u0000¨\u0006\u0013"}, d2 = {"calculateChunkSizeToUse", "", "Ljava/io/InputStream;", "options", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;", "bufferSize", "readByChunks", "", "onChunkRead", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "", "(Ljava/io/InputStream;Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadInChunksOptions;ILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readChunk", "byteArray", "", "readFull", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEReadOptions;", "IONFilesystemLib_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEInputStreamExtensions.kt */
public final class IONFILEInputStreamExtensionsKt {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readFull(java.io.InputStream r1, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r0 = r2.getEncoding()
            boolean r0 = r0 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset
            if (r0 == 0) goto L_0x0038
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r2 = r2.getEncoding()
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding$WithCharset r2 = (io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset) r2
            java.nio.charset.Charset r2 = r2.getCharset()
            r0.<init>(r1, r2)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.InputStreamReader r1 = (java.io.InputStreamReader) r1     // Catch:{ all -> 0x0031 }
            java.io.Reader r1 = (java.io.Reader) r1     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = kotlin.io.TextStreamsKt.readText(r1)     // Catch:{ all -> 0x0031 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            goto L_0x0044
        L_0x0031:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        L_0x0038:
            byte[] r1 = kotlin.io.ByteStreamsKt.readBytes(r1)
            r2 = 2
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
        L_0x0044:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readFull(java.io.InputStream, io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions):java.lang.String");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a5  */
    public static final java.lang.Object readByChunks(java.io.InputStream r8, io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions r9, int r10, kotlin.jvm.functions.Function2<? super java.lang.String, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt$readByChunks$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt$readByChunks$1 r0 = (io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt$readByChunks$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt$readByChunks$1 r0 = new io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt$readByChunks$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r2 = r0.L$1
            io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions r2 = (io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions) r2
            java.lang.Object r4 = r0.L$0
            java.io.InputStream r4 = (java.io.InputStream) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x009d
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r12)
            int r12 = calculateChunkSizeToUse(r8, r9, r10)
            r7 = r10
            r10 = r9
            r9 = r12
            r12 = r11
            r11 = r7
        L_0x0050:
            byte[] r2 = new byte[r9]
            int r4 = readChunk(r8, r2, r11)
            if (r4 <= 0) goto L_0x00a3
            java.util.List r2 = kotlin.collections.ArraysKt.take((byte[]) r2, (int) r4)
            java.util.Collection r2 = (java.util.Collection) r2
            byte[] r2 = kotlin.collections.CollectionsKt.toByteArray(r2)
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r5 = r10.getEncoding()
            boolean r5 = r5 instanceof io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset
            if (r5 == 0) goto L_0x007a
            java.lang.String r5 = new java.lang.String
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding r6 = r10.getEncoding()
            io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding$WithCharset r6 = (io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding.WithCharset) r6
            java.nio.charset.Charset r6 = r6.getCharset()
            r5.<init>(r2, r6)
            goto L_0x007f
        L_0x007a:
            r5 = 2
            java.lang.String r5 = android.util.Base64.encodeToString(r2, r5)
        L_0x007f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r0.L$0 = r8
            r0.L$1 = r10
            r0.L$2 = r12
            r0.I$0 = r11
            r0.I$1 = r9
            r0.I$2 = r4
            r0.label = r3
            java.lang.Object r2 = r12.invoke(r5, r0)
            if (r2 != r1) goto L_0x0097
            return r1
        L_0x0097:
            r2 = r10
            r10 = r11
            r11 = r12
            r7 = r4
            r4 = r8
            r8 = r7
        L_0x009d:
            r12 = r11
            r11 = r10
            r10 = r2
            r7 = r4
            r4 = r8
            r8 = r7
        L_0x00a3:
            if (r4 > 0) goto L_0x0050
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readByChunks(java.io.InputStream, io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readChunk(java.io.InputStream r2, byte[] r3, int r4) {
        /*
            r0 = 0
        L_0x0001:
            int r1 = r3.length
            int r1 = r1 - r0
            int r1 = java.lang.Math.min(r1, r4)
            int r1 = r2.read(r3, r0, r1)
            if (r1 <= 0) goto L_0x000e
            int r0 = r0 + r1
        L_0x000e:
            if (r1 <= 0) goto L_0x0013
            int r1 = r3.length
            if (r0 < r1) goto L_0x0001
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt.readChunk(java.io.InputStream, byte[], int):int");
    }

    private static final int calculateChunkSizeToUse(InputStream inputStream, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, int i) {
        int coerceAtLeast = RangesKt.coerceAtLeast(Math.min(iONFILEReadInChunksOptions.getChunkSize(), inputStream.available()), i);
        return Intrinsics.areEqual((Object) iONFILEReadInChunksOptions.getEncoding(), (Object) IONFILEEncoding.Base64.INSTANCE) ? (coerceAtLeast - (coerceAtLeast % 3)) + 3 : coerceAtLeast;
    }
}
