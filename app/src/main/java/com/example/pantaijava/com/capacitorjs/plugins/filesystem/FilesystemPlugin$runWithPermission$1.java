package com.capacitorjs.plugins.filesystem;

import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$runWithPermission$1", f = "FilesystemPlugin.kt", i = {}, l = {356, 365}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$runWithPermission$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> $onPermissionGranted;
    final /* synthetic */ IONFILEUri.Unresolved $uri;
    Object L$0;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$runWithPermission$1(FilesystemPlugin filesystemPlugin, IONFILEUri.Unresolved unresolved, PluginCall pluginCall, Function2<? super IONFILEUri.Resolved, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super FilesystemPlugin$runWithPermission$1> continuation) {
        super(2, continuation);
        this.this$0 = filesystemPlugin;
        this.$uri = unresolved;
        this.$call = pluginCall;
        this.$onPermissionGranted = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesystemPlugin$runWithPermission$1(this.this$0, this.$uri, this.$call, this.$onPermissionGranted, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$runWithPermission$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            java.lang.Object r0 = r9.L$0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0074
        L_0x0014:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.Result r10 = (kotlin.Result) r10
            java.lang.Object r10 = r10.m199unboximpl()
            goto L_0x003d
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r10)
            com.capacitorjs.plugins.filesystem.FilesystemPlugin r10 = r9.this$0
            io.ionic.libs.ionfilesystemlib.IONFILEController r10 = r10.getController()
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r1 = r9.$uri
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.label = r3
            java.lang.Object r10 = r10.m156getFileUrigIAlus(r1, r4)
            if (r10 != r0) goto L_0x003d
            return r0
        L_0x003d:
            com.capacitorjs.plugins.filesystem.FilesystemPlugin r1 = r9.this$0
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Unresolved r4 = r9.$uri
            com.getcapacitor.PluginCall r5 = r9.$call
            kotlin.jvm.functions.Function2<io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r6 = r9.$onPermissionGranted
            boolean r7 = kotlin.Result.m197isSuccessimpl(r10)
            if (r7 == 0) goto L_0x0075
            r7 = r10
            io.ionic.libs.ionfilesystemlib.model.IONFILEUri$Resolved r7 = (io.ionic.libs.ionfilesystemlib.model.IONFILEUri.Resolved) r7
            boolean r8 = r7.getInExternalStorage()
            if (r8 == 0) goto L_0x0068
            io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType r4 = r4.getParentFolder()
            if (r4 != 0) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            r3 = 0
        L_0x005c:
            boolean r3 = r1.isStoragePermissionGranted(r3)
            if (r3 != 0) goto L_0x0068
            java.lang.String r0 = "permissionCallback"
            r1.requestAllPermissions(r5, r0)
            goto L_0x0075
        L_0x0068:
            r9.L$0 = r10
            r9.label = r2
            java.lang.Object r1 = r6.invoke(r7, r9)
            if (r1 != r0) goto L_0x0073
            return r0
        L_0x0073:
            r0 = r10
        L_0x0074:
            r10 = r0
        L_0x0075:
            com.getcapacitor.PluginCall r0 = r9.$call
            java.lang.Throwable r10 = kotlin.Result.m193exceptionOrNullimpl(r10)
            if (r10 == 0) goto L_0x008d
            java.lang.String r1 = r0.getMethodName()
            java.lang.String r2 = "getMethodName(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.capacitorjs.plugins.filesystem.FilesystemErrors$ErrorInfo r10 = com.capacitorjs.plugins.filesystem.FilesystemErrorsKt.toFilesystemError(r10, r1)
            com.capacitorjs.plugins.filesystem.PluginResultExtensionsKt.sendError(r0, r10)
        L_0x008d:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.capacitorjs.plugins.filesystem.FilesystemPlugin$runWithPermission$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
