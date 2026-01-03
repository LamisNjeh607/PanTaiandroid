package com.capacitorjs.plugins.filesystem;

import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "resolvedSourceUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$runWithPermission$2", f = "FilesystemPlugin.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$runWithPermission$2 extends SuspendLambda implements Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ Function3<IONFILEUri.Resolved, IONFILEUri.Resolved, Continuation<? super Unit>, Object> $onPermissionGranted;
    final /* synthetic */ IONFILEUri.Unresolved $toUri;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$runWithPermission$2(FilesystemPlugin filesystemPlugin, IONFILEUri.Unresolved unresolved, PluginCall pluginCall, Function3<? super IONFILEUri.Resolved, ? super IONFILEUri.Resolved, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super FilesystemPlugin$runWithPermission$2> continuation) {
        super(2, continuation);
        this.this$0 = filesystemPlugin;
        this.$toUri = unresolved;
        this.$call = pluginCall;
        this.$onPermissionGranted = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesystemPlugin$runWithPermission$2 filesystemPlugin$runWithPermission$2 = new FilesystemPlugin$runWithPermission$2(this.this$0, this.$toUri, this.$call, this.$onPermissionGranted, continuation);
        filesystemPlugin$runWithPermission$2.L$0 = obj;
        return filesystemPlugin$runWithPermission$2;
    }

    public final Object invoke(IONFILEUri.Resolved resolved, Continuation<? super Unit> continuation) {
        return ((FilesystemPlugin$runWithPermission$2) create(resolved, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "resolvedDestinationUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.capacitorjs.plugins.filesystem.FilesystemPlugin$runWithPermission$2$1", f = "FilesystemPlugin.kt", i = {}, l = {392}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.capacitorjs.plugins.filesystem.FilesystemPlugin$runWithPermission$2$1  reason: invalid class name */
    /* compiled from: FilesystemPlugin.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<IONFILEUri.Resolved, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(function3, resolved, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(IONFILEUri.Resolved resolved, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(resolved, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function3<IONFILEUri.Resolved, IONFILEUri.Resolved, Continuation<? super Unit>, Object> function3 = function3;
                IONFILEUri.Resolved resolved = resolved;
                this.label = 1;
                if (function3.invoke(resolved, (IONFILEUri.Resolved) this.L$0, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final IONFILEUri.Resolved resolved = (IONFILEUri.Resolved) this.L$0;
            FilesystemPlugin filesystemPlugin = this.this$0;
            IONFILEUri.Unresolved unresolved = this.$toUri;
            PluginCall pluginCall = this.$call;
            final Function3<IONFILEUri.Resolved, IONFILEUri.Resolved, Continuation<? super Unit>, Object> function3 = this.$onPermissionGranted;
            filesystemPlugin.runWithPermission(unresolved, pluginCall, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
