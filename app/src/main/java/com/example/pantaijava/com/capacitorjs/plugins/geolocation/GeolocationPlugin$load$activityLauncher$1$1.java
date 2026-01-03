package com.example.pantaijava.com.capacitorjs.plugins.geolocation;

import androidx.activity.result.ActivityResult;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import kotlin.Metadata;
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
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$load$activityLauncher$1$1", f = "GeolocationPlugin.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: GeolocationPlugin.kt */
public final class GeolocationPlugin$load$activityLauncher$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ActivityResult $result;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GeolocationPlugin$load$activityLauncher$1$1(GeolocationPlugin geolocationPlugin, ActivityResult activityResult, Continuation<? super GeolocationPlugin$load$activityLauncher$1$1> continuation) {
        super(2, continuation);
        this.this$0 = geolocationPlugin;
        this.$result = activityResult;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$load$activityLauncher$1$1(this.this$0, this.$result, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$load$activityLauncher$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            IONGLOCController access$getController$p = this.this$0.controller;
            if (access$getController$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                access$getController$p = null;
            }
            this.label = 1;
            if (access$getController$p.onResolvableExceptionResult(this.$result.getResultCode(), this) == coroutine_suspended) {
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
