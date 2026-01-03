package com.capacitorjs.plugins.filesystem;

import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$getUri$1", f = "FilesystemPlugin.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$getUri$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ IONFILEUri.Unresolved $input;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$getUri$1(FilesystemPlugin filesystemPlugin, IONFILEUri.Unresolved unresolved, PluginCall pluginCall, Continuation<? super FilesystemPlugin$getUri$1> continuation) {
        super(2, continuation);
        this.this$0 = filesystemPlugin;
        this.$input = unresolved;
        this.$call = pluginCall;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesystemPlugin$getUri$1(this.this$0, this.$input, this.$call, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$getUri$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj2 = this.this$0.getController().m156getFileUrigIAlus(this.$input, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            obj2 = ((Result) obj).m199unboximpl();
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PluginCall pluginCall = this.$call;
        if (Result.m197isSuccessimpl(obj2)) {
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, FilesystemMethodResultsKt.toResultObject((IONFILEUri.Resolved) obj2), false, 2, (Object) null);
        }
        PluginCall pluginCall2 = this.$call;
        Throwable r6 = Result.m193exceptionOrNullimpl(obj2);
        if (r6 != null) {
            String methodName = pluginCall2.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall2, FilesystemErrorsKt.toFilesystemError(r6, methodName));
        }
        return Unit.INSTANCE;
    }
}
