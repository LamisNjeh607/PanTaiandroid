package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.helper.common.IONFILECommonKt;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$getFileMetadata$2", f = "IONFILELocalFilesHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$getFileMetadata$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends IONFILEMetadataResult>>, Object> {
    final /* synthetic */ String $fullPath;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$getFileMetadata$2(String str, Continuation<? super IONFILELocalFilesHelper$getFileMetadata$2> continuation) {
        super(2, continuation);
        this.$fullPath = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILELocalFilesHelper$getFileMetadata$2 iONFILELocalFilesHelper$getFileMetadata$2 = new IONFILELocalFilesHelper$getFileMetadata$2(this.$fullPath, continuation);
        iONFILELocalFilesHelper$getFileMetadata$2.L$0 = obj;
        return iONFILELocalFilesHelper$getFileMetadata$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<IONFILEMetadataResult>> continuation) {
        return ((IONFILELocalFilesHelper$getFileMetadata$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            String str = this.$fullPath;
            try {
                Result.Companion companion = Result.Companion;
                File file = new File(str);
                if (file.exists()) {
                    obj2 = Result.m190constructorimpl(IONFILECommonKt.getMetadata(file));
                    return Result.m189boximpl(obj2);
                }
                throw new IONFILEExceptions.DoesNotExist(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m190constructorimpl(ResultKt.createFailure(th));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
