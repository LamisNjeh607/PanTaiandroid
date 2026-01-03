package io.ionic.libs.ionfilesystemlib.helper.common;

import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt", f = "IONFILEInputStreamExtensions.kt", i = {0, 0, 0, 0, 0, 0}, l = {55}, m = "readByChunks", n = {"$this$readByChunks", "options", "onChunkRead", "bufferSize", "chunkSize", "bytesRead"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "I$2"})
/* compiled from: IONFILEInputStreamExtensions.kt */
final class IONFILEInputStreamExtensionsKt$readByChunks$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    IONFILEInputStreamExtensionsKt$readByChunks$1(Continuation<? super IONFILEInputStreamExtensionsKt$readByChunks$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return IONFILEInputStreamExtensionsKt.readByChunks((InputStream) null, (IONFILEReadInChunksOptions) null, 0, (Function2<? super String, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
