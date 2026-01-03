package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import android.net.Uri;
import com.getcapacitor.JSObject;
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
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$rename$1", f = "FilesystemPlugin.kt", i = {}, l = {226}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$rename$1 extends SuspendLambda implements Function3<IONFILEUri.Resolved, IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ com.capacitorjs.plugins.filesystem.FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$rename$1(com.capacitorjs.plugins.filesystem.FilesystemPlugin filesystemPlugin, PluginCall pluginCall, Continuation<? super FilesystemPlugin$rename$1> continuation) {
        super(3, (Continuation<Object>) continuation );
        this.this$0 = filesystemPlugin;
        this.$call = pluginCall;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, IONFILEUri.Resolved resolved2, Continuation<? super Unit> continuation) {
        FilesystemPlugin$rename$1 filesystemPlugin$rename$1 = new FilesystemPlugin$rename$1(this.this$0, this.$call, (Continuation<? super FilesystemPlugin$rename$1>) continuation );
        filesystemPlugin$rename$1.L$0 = resolved;
        filesystemPlugin$rename$1.L$1 = resolved2;
        return filesystemPlugin$rename$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = null;
            this.label = 1;
            obj2 = this.this$0.getController().m159move0E7RQCE((IONFILEUri.Resolved) this.L$0, (IONFILEUri.Resolved) this.L$1, this);
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
            Uri uri = (Uri) obj2;
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, (JSObject) null, false, 3, (Object) null);
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
