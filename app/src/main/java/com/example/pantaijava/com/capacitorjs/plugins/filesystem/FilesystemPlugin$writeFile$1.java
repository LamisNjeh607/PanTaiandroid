package com.capacitorjs.plugins.filesystem;

import android.media.MediaScannerConnection;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$writeFile$1", f = "FilesystemPlugin.kt", i = {0}, l = {113}, m = "invokeSuspend", n = {"uri"}, s = {"L$0"})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$writeFile$1 extends SuspendLambda implements Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ WriteFileOptions $input;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$writeFile$1(FilesystemPlugin filesystemPlugin, WriteFileOptions writeFileOptions, PluginCall pluginCall, Continuation<? super FilesystemPlugin$writeFile$1> continuation) {
        super(2, continuation);
        this.this$0 = filesystemPlugin;
        this.$input = writeFileOptions;
        this.$call = pluginCall;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesystemPlugin$writeFile$1 filesystemPlugin$writeFile$1 = new FilesystemPlugin$writeFile$1(this.this$0, this.$input, this.$call, continuation);
        filesystemPlugin$writeFile$1.L$0 = obj;
        return filesystemPlugin$writeFile$1;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$writeFile$1) create(resolved, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IONFILEUri.Resolved resolved;
        String path;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            IONFILEUri.Resolved resolved2 = (IONFILEUri.Resolved) this.L$0;
            this.L$0 = resolved2;
            this.label = 1;
            Object r1 = this.this$0.getController().m161saveFile0E7RQCE(resolved2, this.$input.getOptions(), this);
            if (r1 == coroutine_suspended) {
                return coroutine_suspended;
            }
            resolved = resolved2;
            obj2 = r1;
        } else if (i == 1) {
            resolved = (IONFILEUri.Resolved) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = ((Result) obj).m199unboximpl();
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PluginCall pluginCall = this.$call;
        WriteFileOptions writeFileOptions = this.$input;
        FilesystemPlugin filesystemPlugin = this.this$0;
        if (Result.m197isSuccessimpl(obj2)) {
            Uri uri = (Uri) obj2;
            if (resolved.getInExternalStorage() && (path = uri.getPath()) != null) {
                MediaScannerConnection.scanFile(filesystemPlugin.getContext(), new String[]{path}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
            }
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, FilesystemMethodResultsKt.createWriteResultObject(uri, writeFileOptions.getOptions().getMode()), false, 2, (Object) null);
        }
        PluginCall pluginCall2 = this.$call;
        Throwable r9 = Result.m193exceptionOrNullimpl(obj2);
        if (r9 != null) {
            String methodName = pluginCall2.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall2, FilesystemErrorsKt.toFilesystemError(r9, methodName));
        }
        return Unit.INSTANCE;
    }
}
