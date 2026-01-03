package com.capacitorjs.plugins.geolocation;

import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.PluginCall;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$startWatch$1", f = "GeolocationPlugin.kt", i = {}, l = {232}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: GeolocationPlugin.kt */
final class GeolocationPlugin$startWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$startWatch$1(PluginCall pluginCall, GeolocationPlugin geolocationPlugin, Continuation<? super GeolocationPlugin$startWatch$1> continuation) {
        super(2, continuation);
        this.$call = pluginCall;
        this.this$0 = geolocationPlugin;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$startWatch$1(this.$call, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$startWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String callbackId = this.$call.getCallbackId();
            IONGLOCLocationOptions access$createOptions = this.this$0.createOptions(this.$call);
            IONGLOCController access$getController$p = this.this$0.controller;
            if (access$getController$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                access$getController$p = null;
            }
            AppCompatActivity activity = this.this$0.getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            Intrinsics.checkNotNull(callbackId);
            Flow<Result<List<IONGLOCLocationResult>>> addWatch = access$getController$p.addWatch(activity, access$createOptions, callbackId);
            final GeolocationPlugin geolocationPlugin = this.this$0;
            final PluginCall pluginCall = this.$call;
            this.label = 1;
            if (addWatch.collect(new FlowCollector() {
                public final Object emit(Object obj, Continuation<? super Unit> continuation) {
                    Result result = (Result) obj;
                    Object r6 = result.m199unboximpl();
                    GeolocationPlugin geolocationPlugin = geolocationPlugin;
                    PluginCall pluginCall = pluginCall;
                    if (Result.m197isSuccessimpl(r6)) {
                        for (IONGLOCLocationResult access$getJSObjectForLocation : (List) r6) {
                            geolocationPlugin.sendSuccess(pluginCall, geolocationPlugin.getJSObjectForLocation(access$getJSObjectForLocation), Boxing.boxBoolean(true));
                        }
                    }
                    Object r5 = result.m199unboximpl();
                    GeolocationPlugin geolocationPlugin2 = geolocationPlugin;
                    PluginCall pluginCall2 = pluginCall;
                    Throwable r52 = Result.m193exceptionOrNullimpl(r5);
                    if (r52 != null) {
                        geolocationPlugin2.onLocationError(r52, pluginCall2);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
