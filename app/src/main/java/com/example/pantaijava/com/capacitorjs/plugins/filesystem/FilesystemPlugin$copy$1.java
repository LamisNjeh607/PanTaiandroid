package com.capacitorjs.plugins.filesystem;

import android.net.Uri;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "source", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "destination"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$copy$1", f = "FilesystemPlugin.kt", i = {}, l = {239}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$copy$1 extends SuspendLambda implements Function3<IONFILEUri.Resolved, IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$copy$1(FilesystemPlugin filesystemPlugin, PluginCall pluginCall, Continuation<? super FilesystemPlugin$copy$1> continuation) {
        super(3, continuation);
        this.this$0 = filesystemPlugin;
        this.$call = pluginCall;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, IONFILEUri.Resolved resolved2, Continuation<? super Unit> continuation) {
        FilesystemPlugin$copy$1 filesystemPlugin$copy$1 = new FilesystemPlugin$copy$1(this.this$0, this.$call, continuation);
        filesystemPlugin$copy$1.L$0 = resolved;
        filesystemPlugin$copy$1.L$1 = resolved2;
        return filesystemPlugin$copy$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = null;
            this.label = 1;
            obj2 = this.this$0.getController().m152copy0E7RQCE((IONFILEUri.Resolved) this.L$0, (IONFILEUri.Resolved) this.L$1, this);
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
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, FilesystemMethodResultsKt.createUriResultObject((Uri) obj2), false, 2, (Object) null);
        }
        PluginCall pluginCall2 = this.$call;
        Throwable r7 = Result.m193exceptionOrNullimpl(obj2);
        if (r7 != null) {
            String methodName = pluginCall2.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall2, FilesystemErrorsKt.toFilesystemError(r7, methodName));
        }
        return Unit.INSTANCE;
    }
}
