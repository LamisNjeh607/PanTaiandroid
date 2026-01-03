package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILEDeleteOptions;
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

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$deleteFile$1", f = "FilesystemPlugin.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$deleteFile$1 extends SuspendLambda implements Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ com.capacitorjs.plugins.filesystem.FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$deleteFile$1(com.capacitorjs.plugins.filesystem.FilesystemPlugin filesystemPlugin, PluginCall pluginCall, Continuation<? super FilesystemPlugin$deleteFile$1> continuation) {
        super(2, (Continuation<Object>) continuation );
        this.this$0 = filesystemPlugin;
        this.$call = pluginCall;
    }

    public final FilesystemPlugin$deleteFile$1 create(Object obj, Continuation<?> continuation) {
        FilesystemPlugin$deleteFile$1 filesystemPlugin$deleteFile$1 = new FilesystemPlugin$deleteFile$1(this.this$0, this.$call, continuation);
        filesystemPlugin$deleteFile$1.L$0 = obj;
        return filesystemPlugin$deleteFile$1;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$deleteFile$1) create(resolved, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj2 = this.this$0.getController().m155delete0E7RQCE((IONFILEUri.Resolved) this.L$0, new IONFILEDeleteOptions(false), this);
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
            Unit unit = (Unit) obj2;
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, (JSObject) null, false, 3, (Object) null);
        }
        PluginCall pluginCall2 = this.$call;
        Throwable r7 = Result.m193exceptionOrNullimpl(obj2);
        if (r7 != null) {
            String methodName = pluginCall2.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            com.capacitorjs.plugins.filesystem.PluginResultExtensionsKt.sendError(pluginCall2, FilesystemErrorsKt.toFilesystemError(r7, methodName));
        }
        return Unit.INSTANCE;
    }
}
