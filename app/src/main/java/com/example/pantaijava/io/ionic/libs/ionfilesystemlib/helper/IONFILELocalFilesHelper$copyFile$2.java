package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILELocalFilesHelper$copyFile$2", f = "IONFILELocalFilesHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILELocalFilesHelper.kt */
final class IONFILELocalFilesHelper$copyFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ String $destinationPath;
    final /* synthetic */ String $sourcePath;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILELocalFilesHelper$copyFile$2(String str, String str2, Continuation<? super IONFILELocalFilesHelper$copyFile$2> continuation) {
        super(2, continuation);
        this.$sourcePath = str;
        this.$destinationPath = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILELocalFilesHelper$copyFile$2 iONFILELocalFilesHelper$copyFile$2 = new IONFILELocalFilesHelper$copyFile$2(this.$sourcePath, this.$destinationPath, continuation);
        iONFILELocalFilesHelper$copyFile$2.L$0 = obj;
        return iONFILELocalFilesHelper$copyFile$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((IONFILELocalFilesHelper$copyFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            String str = this.$sourcePath;
            String str2 = this.$destinationPath;
            try {
                Result.Companion companion = Result.Companion;
                File file = new File(str);
                File file2 = new File(str2);
                if (!Intrinsics.areEqual((Object) file, (Object) file2)) {
                    if (!file.exists()) {
                        throw new IONFILEExceptions.DoesNotExist(str, (Throwable) null, 2, (DefaultConstructorMarker) null);
                    } else if (file.isDirectory() || file2.isDirectory()) {
                        throw new IONFILEExceptions.CopyRenameFailed.MixingFilesAndDirectories();
                    } else {
                        File parentFile = file2.getParentFile();
                        if (parentFile != null) {
                            if (!parentFile.exists()) {
                                throw new IONFILEExceptions.CopyRenameFailed.NoParentDirectory();
                            }
                        }
                        if (!FilesKt.copyTo$default(file, file2, true, 0, 4, (Object) null).exists()) {
                            throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
                        }
                    }
                }
                obj2 = Result.m190constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m190constructorimpl(ResultKt.createFailure(th));
            }
            return Result.m189boximpl(obj2);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
