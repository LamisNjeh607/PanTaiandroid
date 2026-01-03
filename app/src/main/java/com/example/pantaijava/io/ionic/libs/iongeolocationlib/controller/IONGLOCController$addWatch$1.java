package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/Result;", "", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1", f = "IONGLOCController.kt", i = {0}, l = {110, 135}, m = "invokeSuspend", n = {"$this$callbackFlow"}, s = {"L$0"})
/* compiled from: IONGLOCController.kt */
final class IONGLOCController$addWatch$1 extends SuspendLambda implements Function2<ProducerScope<? super Result<? extends List<? extends IONGLOCLocationResult>>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ IONGLOCLocationOptions $options;
    final /* synthetic */ String $watchId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONGLOCController$addWatch$1(IONGLOCController iONGLOCController, Activity activity, IONGLOCLocationOptions iONGLOCLocationOptions, String str, Continuation<? super IONGLOCController$addWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = iONGLOCController;
        this.$activity = activity;
        this.$options = iONGLOCLocationOptions;
        this.$watchId = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONGLOCController$addWatch$1 iONGLOCController$addWatch$1 = new IONGLOCController$addWatch$1(this.this$0, this.$activity, this.$options, this.$watchId, continuation);
        iONGLOCController$addWatch$1.L$0 = obj;
        return iONGLOCController$addWatch$1;
    }

    public final Object invoke(ProducerScope<? super Result<? extends List<IONGLOCLocationResult>>> producerScope, Continuation<? super Unit> continuation) {
        return ((IONGLOCController$addWatch$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0029
            if (r1 == r3) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00d3
        L_0x0013:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001b:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ Exception -> 0x008d }
            kotlin.Result r9 = (kotlin.Result) r9     // Catch:{ Exception -> 0x008d }
            java.lang.Object r9 = r9.m199unboximpl()     // Catch:{ Exception -> 0x008d }
            goto L_0x0045
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            r1 = r9
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r9 = r8.this$0     // Catch:{ Exception -> 0x008d }
            android.app.Activity r4 = r8.$activity     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r5 = r8.$options     // Catch:{ Exception -> 0x008d }
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ Exception -> 0x008d }
            r8.L$0 = r1     // Catch:{ Exception -> 0x008d }
            r8.label = r3     // Catch:{ Exception -> 0x008d }
            java.lang.Object r9 = r9.m182checkLocationPreconditionsBWLJW6A(r4, r5, r3, r6)     // Catch:{ Exception -> 0x008d }
            if (r9 != r0) goto L_0x0045
            return r0
        L_0x0045:
            boolean r3 = kotlin.Result.m196isFailureimpl(r9)     // Catch:{ Exception -> 0x008d }
            if (r3 == 0) goto L_0x006a
            kotlin.Result$Companion r3 = kotlin.Result.Companion     // Catch:{ Exception -> 0x008d }
            java.lang.Throwable r9 = kotlin.Result.m193exceptionOrNullimpl(r9)     // Catch:{ Exception -> 0x008d }
            if (r9 != 0) goto L_0x005a
            java.lang.NullPointerException r9 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x008d }
            r9.<init>()     // Catch:{ Exception -> 0x008d }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ Exception -> 0x008d }
        L_0x005a:
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)     // Catch:{ Exception -> 0x008d }
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)     // Catch:{ Exception -> 0x008d }
            kotlin.Result r9 = kotlin.Result.m189boximpl(r9)     // Catch:{ Exception -> 0x008d }
            r1.m1748trySendJP2dKIU(r9)     // Catch:{ Exception -> 0x008d }
            goto L_0x00b9
        L_0x006a:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r9 = r8.this$0     // Catch:{ Exception -> 0x008d }
            java.util.Map r9 = r9.locationCallbacks     // Catch:{ Exception -> 0x008d }
            java.lang.String r3 = r8.$watchId     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1$1 r4 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1$1     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r5 = r8.this$0     // Catch:{ Exception -> 0x008d }
            java.lang.String r6 = r8.$watchId     // Catch:{ Exception -> 0x008d }
            r4.<init>(r5, r6, r1)     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r5 = r8.this$0     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r6 = r8.$options     // Catch:{ Exception -> 0x008d }
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r5 = r5.helper     // Catch:{ Exception -> 0x008d }
            r7 = r4
            com.google.android.gms.location.LocationCallback r7 = (com.google.android.gms.location.LocationCallback) r7     // Catch:{ Exception -> 0x008d }
            r5.requestLocationUpdates$IONGeolocationLib_release(r6, r7)     // Catch:{ Exception -> 0x008d }
            r9.put(r3, r4)     // Catch:{ Exception -> 0x008d }
            goto L_0x00b9
        L_0x008d:
            r9 = move-exception
            java.lang.String r3 = r9.getMessage()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Error requesting location updates: "
            r4.<init>(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "IONGeolocationController"
            android.util.Log.d(r4, r3)
            kotlin.Result$Companion r3 = kotlin.Result.Companion
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m190constructorimpl(r9)
            kotlin.Result r9 = kotlin.Result.m189boximpl(r9)
            r1.m1748trySendJP2dKIU(r9)
        L_0x00b9:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1$3 r9 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1$3
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r3 = r8.this$0
            java.lang.String r4 = r8.$watchId
            r9.<init>(r3, r4)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            r3 = r8
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 0
            r8.L$0 = r4
            r8.label = r2
            java.lang.Object r9 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r1, r9, r3)
            if (r9 != r0) goto L_0x00d3
            return r0
        L_0x00d3:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
