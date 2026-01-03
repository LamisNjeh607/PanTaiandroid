package com.capacitorjs.plugins.filesystem;

import android.net.Uri;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILECreateOptions;
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
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$mkdir$1", f = "FilesystemPlugin.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$mkdir$1 extends SuspendLambda implements Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ SingleUriWithRecursiveOptions $input;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$mkdir$1(FilesystemPlugin filesystemPlugin, SingleUriWithRecursiveOptions singleUriWithRecursiveOptions, PluginCall pluginCall, Continuation<? super FilesystemPlugin$mkdir$1> continuation) {
        super(2, continuation);
        this.this$0 = filesystemPlugin;
        this.$input = singleUriWithRecursiveOptions;
        this.$call = pluginCall;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesystemPlugin$mkdir$1 filesystemPlugin$mkdir$1 = new FilesystemPlugin$mkdir$1(this.this$0, this.$input, this.$call, continuation);
        filesystemPlugin$mkdir$1.L$0 = obj;
        return filesystemPlugin$mkdir$1;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$mkdir$1) create(resolved, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj2 = this.this$0.getController().m153createDirectory0E7RQCE((IONFILEUri.Resolved) this.L$0, new IONFILECreateOptions(this.$input.getRecursive()), this);
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
        Throwable r6 = Result.m193exceptionOrNullimpl(obj2);
        if (r6 != null) {
            String methodName = pluginCall2.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall2, FilesystemErrorsKt.toFilesystemError(r6, methodName));
        }
        return Unit.INSTANCE;
    }
}
