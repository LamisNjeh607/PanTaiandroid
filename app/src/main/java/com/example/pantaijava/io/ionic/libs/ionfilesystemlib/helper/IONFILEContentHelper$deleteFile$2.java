package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
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

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$deleteFile$2", f = "IONFILEContentHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$deleteFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$deleteFile$2(IONFILEContentHelper iONFILEContentHelper, Uri uri, Continuation<? super IONFILEContentHelper$deleteFile$2> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$uri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$deleteFile$2 iONFILEContentHelper$deleteFile$2 = new IONFILEContentHelper$deleteFile$2(this.this$0, this.$uri, continuation);
        iONFILEContentHelper$deleteFile$2.L$0 = obj;
        return iONFILEContentHelper$deleteFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((IONFILEContentHelper$deleteFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IONFILEContentHelper iONFILEContentHelper = this.this$0;
            Uri uri = this.$uri;
            try {
                Result.Companion companion = Result.Companion;
                if (iONFILEContentHelper.contentResolver.delete(uri, (String) null, (String[]) null) > 0) {
                    obj2 = Result.m190constructorimpl(Unit.INSTANCE);
                    return Result.m189boximpl(iONFILEContentHelper.m163mapErrorKWTtemM(obj2, this.$uri));
                }
                throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m190constructorimpl(ResultKt.createFailure(th));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
