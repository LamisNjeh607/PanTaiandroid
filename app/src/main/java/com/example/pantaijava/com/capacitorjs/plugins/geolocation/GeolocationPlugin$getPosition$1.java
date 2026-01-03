package com.capacitorjs.plugins.geolocation;

import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.PluginCall;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
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
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$getPosition$1", f = "GeolocationPlugin.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: GeolocationPlugin.kt */
final class GeolocationPlugin$getPosition$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$getPosition$1(GeolocationPlugin geolocationPlugin, PluginCall pluginCall, Continuation<? super GeolocationPlugin$getPosition$1> continuation) {
        super(2, continuation);
        this.this$0 = geolocationPlugin;
        this.$call = pluginCall;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$getPosition$1(this.this$0, this.$call, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$getPosition$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            IONGLOCLocationOptions access$createOptions = this.this$0.createOptions(this.$call);
            IONGLOCController access$getController$p = this.this$0.controller;
            if (access$getController$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                access$getController$p = null;
            }
            AppCompatActivity activity = this.this$0.getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            this.label = 1;
            obj2 = access$getController$p.m183getCurrentPosition0E7RQCE(activity, access$createOptions, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            obj2 = ((Result) obj).m199unboximpl();
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        GeolocationPlugin geolocationPlugin = this.this$0;
        PluginCall pluginCall = this.$call;
        if (Result.m197isSuccessimpl(obj2)) {
            GeolocationPlugin.sendSuccess$default(geolocationPlugin, pluginCall, geolocationPlugin.getJSObjectForLocation((IONGLOCLocationResult) obj2), (Boolean) null, 2, (Object) null);
        }
        GeolocationPlugin geolocationPlugin2 = this.this$0;
        PluginCall pluginCall2 = this.$call;
        Throwable r7 = Result.m193exceptionOrNullimpl(obj2);
        if (r7 != null) {
            geolocationPlugin2.onLocationError(r7, pluginCall2);
        }
        return Unit.INSTANCE;
    }
}
