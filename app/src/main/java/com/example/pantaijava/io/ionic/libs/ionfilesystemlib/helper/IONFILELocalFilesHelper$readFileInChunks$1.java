package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.helper.common.IONFILEInputStreamExtensionsKt;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$readFileInChunks$1", f = "IONFILELocalFilesHelper.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$readFileInChunks$1 extends SuspendLambda implements Function2<FlowCollector<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $bufferSize;
    final /* synthetic */ String $fullPath;
    final /* synthetic */ IONFILEReadInChunksOptions $options;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$readFileInChunks$1(String str, IONFILEReadInChunksOptions iONFILEReadInChunksOptions, int i, Continuation<? super IONFILELocalFilesHelper$readFileInChunks$1> continuation) {
        super(2, continuation);
        this.$fullPath = str;
        this.$options = iONFILEReadInChunksOptions;
        this.$bufferSize = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILELocalFilesHelper$readFileInChunks$1 iONFILELocalFilesHelper$readFileInChunks$1 = new IONFILELocalFilesHelper$readFileInChunks$1(this.$fullPath, this.$options, this.$bufferSize, continuation);
        iONFILELocalFilesHelper$readFileInChunks$1.L$0 = obj;
        return iONFILELocalFilesHelper$readFileInChunks$1;
    }

    public final Object invoke(FlowCollector<? super String> flowCollector, Continuation<? super Unit> continuation) {
        return ((IONFILELocalFilesHelper$readFileInChunks$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Closeable closeable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            File file = new File(this.$fullPath);
            if (file.exists()) {
                Closeable fileInputStream = new FileInputStream(file);
                IONFILEReadInChunksOptions iONFILEReadInChunksOptions = this.$options;
                int i2 = this.$bufferSize;
                try {
                    this.L$0 = fileInputStream;
                    this.label = 1;
                    if (IONFILEInputStreamExtensionsKt.readByChunks((FileInputStream) fileInputStream, iONFILEReadInChunksOptions, i2, new IONFILELocalFilesHelper$readFileInChunks$1$1$1(flowCollector, (Continuation<? super IONFILELocalFilesHelper$readFileInChunks$1$1$1>) null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    closeable = fileInputStream;
                } catch (Throwable th) {
                    th = th;
                    closeable = fileInputStream;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(closeable, th);
                        throw th2;
                    }
                }
            } else {
                throw new IONFILEExceptions.DoesNotExist(this.$fullPath, (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        } else if (i == 1) {
            closeable = (Closeable) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(closeable, (Throwable) null);
        return Unit.INSTANCE;
    }
}
