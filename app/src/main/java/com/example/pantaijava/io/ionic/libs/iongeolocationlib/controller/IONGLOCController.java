package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.location.LocationManagerCompat;
import com.capacitorjs.plugins.geolocation.GeolocationPlugin;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 22\u00020\u0001:\u00012B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ0\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00100\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J4\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001eH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\fJ\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u001eH\u0002J,\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+J\u0016\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.H@¢\u0006\u0002\u0010/J\f\u00100\u001a\u00020\u0017*\u000201H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCController;", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "helper", "Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;", "(Lcom/google/android/gms/location/FusedLocationProviderClient;Landroidx/activity/result/ActivityResultLauncher;Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;)V", "locationCallbacks", "", "", "Lcom/google/android/gms/location/LocationCallback;", "resolveLocationSettingsResultFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/Result;", "", "watchIdsBlacklist", "", "addWatch", "Lkotlinx/coroutines/flow/Flow;", "", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "activity", "Landroid/app/Activity;", "options", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "watchId", "areLocationServicesEnabled", "", "context", "Landroid/content/Context;", "checkLocationPreconditions", "isSingleLocationRequest", "checkLocationPreconditions-BWLJW6A", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkWatchInBlackList", "clearWatch", "id", "addToBlackList", "getCurrentPosition", "getCurrentPosition-0E7RQCE", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResolvableExceptionResult", "resultCode", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOSLocationResult", "Landroid/location/Location;", "Companion", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCController.kt */
public final class IONGLOCController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String LOG_TAG = "IONGeolocationController";
    /* access modifiers changed from: private */
    public final IONGLOCServiceHelper helper;
    /* access modifiers changed from: private */
    public final Map<String, LocationCallback> locationCallbacks;
    private MutableSharedFlow<Result<Unit>> resolveLocationSettingsResultFlow;
    private final List<String> watchIdsBlacklist;

    public IONGLOCController(FusedLocationProviderClient fusedLocationProviderClient, ActivityResultLauncher<IntentSenderRequest> activityResultLauncher, IONGLOCServiceHelper iONGLOCServiceHelper) {
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "fusedLocationClient");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityLauncher");
        Intrinsics.checkNotNullParameter(iONGLOCServiceHelper, "helper");
        this.helper = iONGLOCServiceHelper;
        this.locationCallbacks = new LinkedHashMap();
        this.watchIdsBlacklist = new ArrayList();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IONGLOCController(FusedLocationProviderClient fusedLocationProviderClient, ActivityResultLauncher activityResultLauncher, IONGLOCServiceHelper iONGLOCServiceHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fusedLocationProviderClient, activityResultLauncher, (i & 4) != 0 ? new IONGLOCServiceHelper(fusedLocationProviderClient, activityResultLauncher) : iONGLOCServiceHelper);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007b A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: getCurrentPosition-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m183getCurrentPosition0E7RQCE(android.app.Activity r6, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004c
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r6 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x0098 }
            goto L_0x008b
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r7 = (io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions) r7
            java.lang.Object r6 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r6 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x0098 }
            kotlin.Result r8 = (kotlin.Result) r8     // Catch:{ Exception -> 0x0098 }
            java.lang.Object r8 = r8.m199unboximpl()     // Catch:{ Exception -> 0x0098 }
            goto L_0x005d
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5     // Catch:{ Exception -> 0x0098 }
            r0.L$1 = r7     // Catch:{ Exception -> 0x0098 }
            r0.label = r4     // Catch:{ Exception -> 0x0098 }
            java.lang.Object r8 = r5.m182checkLocationPreconditionsBWLJW6A(r6, r7, r4, r0)     // Catch:{ Exception -> 0x0098 }
            if (r8 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r6 = r5
        L_0x005d:
            boolean r2 = kotlin.Result.m196isFailureimpl(r8)     // Catch:{ Exception -> 0x0098 }
            if (r2 == 0) goto L_0x007b
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ Exception -> 0x0098 }
            java.lang.Throwable r6 = kotlin.Result.m193exceptionOrNullimpl(r8)     // Catch:{ Exception -> 0x0098 }
            if (r6 != 0) goto L_0x0072
            java.lang.NullPointerException r6 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0098 }
            r6.<init>()     // Catch:{ Exception -> 0x0098 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ Exception -> 0x0098 }
        L_0x0072:
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)     // Catch:{ Exception -> 0x0098 }
            java.lang.Object r6 = kotlin.Result.m190constructorimpl(r6)     // Catch:{ Exception -> 0x0098 }
            return r6
        L_0x007b:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r8 = r6.helper     // Catch:{ Exception -> 0x0098 }
            r0.L$0 = r6     // Catch:{ Exception -> 0x0098 }
            r2 = 0
            r0.L$1 = r2     // Catch:{ Exception -> 0x0098 }
            r0.label = r3     // Catch:{ Exception -> 0x0098 }
            java.lang.Object r8 = r8.getCurrentLocation$IONGeolocationLib_release(r7, r0)     // Catch:{ Exception -> 0x0098 }
            if (r8 != r1) goto L_0x008b
            return r1
        L_0x008b:
            android.location.Location r8 = (android.location.Location) r8     // Catch:{ Exception -> 0x0098 }
            kotlin.Result$Companion r7 = kotlin.Result.Companion     // Catch:{ Exception -> 0x0098 }
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult r6 = r6.toOSLocationResult(r8)     // Catch:{ Exception -> 0x0098 }
            java.lang.Object r6 = kotlin.Result.m190constructorimpl(r6)     // Catch:{ Exception -> 0x0098 }
            return r6
        L_0x0098:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Error fetching location: "
            r8.<init>(r0)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "IONGeolocationController"
            android.util.Log.d(r8, r7)
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m190constructorimpl(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCController.m183getCurrentPosition0E7RQCE(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object onResolvableExceptionResult(int i, Continuation<? super Unit> continuation) {
        Object obj;
        MutableSharedFlow<Result<Unit>> mutableSharedFlow = this.resolveLocationSettingsResultFlow;
        if (mutableSharedFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolveLocationSettingsResultFlow");
            mutableSharedFlow = null;
        }
        if (i == -1) {
            Result.Companion companion = Result.Companion;
            obj = Result.m190constructorimpl(Unit.INSTANCE);
        } else {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m190constructorimpl(ResultKt.createFailure(new IONGLOCException.IONGLOCRequestDeniedException("Request to enable location denied.", (Throwable) null, 2, (DefaultConstructorMarker) null)));
        }
        Object emit = mutableSharedFlow.emit(Result.m189boximpl(obj), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    public final boolean areLocationServicesEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(GeolocationPlugin.LOCATION_ALIAS);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        return LocationManagerCompat.isLocationEnabled((LocationManager) systemService);
    }

    public final Flow<Result<List<IONGLOCLocationResult>>> addWatch(Activity activity, IONGLOCLocationOptions iONGLOCLocationOptions, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(iONGLOCLocationOptions, "options");
        Intrinsics.checkNotNullParameter(str, "watchId");
        return FlowKt.callbackFlow(new IONGLOCController$addWatch$1(this, activity, iONGLOCLocationOptions, str, (Continuation<? super IONGLOCController$addWatch$1>) null));
    }

    public final boolean clearWatch(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        return clearWatch(str, true);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: checkLocationPreconditions-BWLJW6A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m182checkLocationPreconditionsBWLJW6A(android.app.Activity r11, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r12, boolean r13, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1
            r0.<init>(r10, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r2 = 1
            r8 = 2
            r9 = 0
            if (r1 == 0) goto L_0x003f
            if (r1 == r2) goto L_0x0037
            if (r1 != r8) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00ce
        L_0x002f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0037:
            java.lang.Object r11 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r11 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00a6
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r14)
            long r3 = r12.getTimeout()
            r5 = 0
            int r14 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r14 > 0) goto L_0x0060
            kotlin.Result$Companion r11 = kotlin.Result.Companion
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCInvalidTimeoutException r11 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCInvalidTimeoutException
            java.lang.String r12 = "Timeout needs to be a positive value."
            r11.<init>(r12, r9, r8, r9)
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m190constructorimpl(r11)
            return r11
        L_0x0060:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r14 = r10.helper
            java.lang.Object r14 = r14.m185checkGooglePlayServicesAvailableIoAF18A$IONGeolocationLib_release(r11)
            boolean r1 = kotlin.Result.m196isFailureimpl(r14)
            if (r1 == 0) goto L_0x0084
            kotlin.Result$Companion r11 = kotlin.Result.Companion
            java.lang.Throwable r11 = kotlin.Result.m193exceptionOrNullimpl(r14)
            if (r11 != 0) goto L_0x007b
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            r11.<init>()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
        L_0x007b:
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m190constructorimpl(r11)
            return r11
        L_0x0084:
            r14 = 7
            r1 = 0
            kotlinx.coroutines.flow.MutableSharedFlow r14 = kotlinx.coroutines.flow.SharedFlowKt.MutableSharedFlow$default(r1, r1, r9, r14, r9)
            r10.resolveLocationSettingsResultFlow = r14
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r1 = r10.helper
            if (r13 == 0) goto L_0x0092
            r4 = r5
            goto L_0x0097
        L_0x0092:
            long r13 = r12.getTimeout()
            r4 = r13
        L_0x0097:
            r0.L$0 = r10
            r0.label = r2
            r2 = r11
            r3 = r12
            r6 = r0
            java.lang.Object r14 = r1.checkLocationSettings$IONGeolocationLib_release(r2, r3, r4, r6)
            if (r14 != r7) goto L_0x00a5
            return r7
        L_0x00a5:
            r11 = r10
        L_0x00a6:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r12 = r14.booleanValue()
            if (r12 == 0) goto L_0x00b7
            kotlin.Result$Companion r11 = kotlin.Result.Companion
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            java.lang.Object r11 = kotlin.Result.m190constructorimpl(r11)
            return r11
        L_0x00b7:
            kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Result<kotlin.Unit>> r11 = r11.resolveLocationSettingsResultFlow
            if (r11 != 0) goto L_0x00c1
            java.lang.String r11 = "resolveLocationSettingsResultFlow"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r11 = r9
        L_0x00c1:
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
            r0.L$0 = r9
            r0.label = r8
            java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt.first(r11, r0)
            if (r14 != r7) goto L_0x00ce
            return r7
        L_0x00ce:
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r11 = r14.m199unboximpl()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCController.m182checkLocationPreconditionsBWLJW6A(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean clearWatch(String str, boolean z) {
        LocationCallback remove = this.locationCallbacks.remove(str);
        if (remove != null) {
            this.helper.removeLocationUpdates$IONGeolocationLib_release(remove);
            return true;
        }
        if (z) {
            this.watchIdsBlacklist.add(str);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final boolean checkWatchInBlackList(String str) {
        if (!this.watchIdsBlacklist.contains(str)) {
            return false;
        }
        if (!clearWatch(str, false)) {
            return true;
        }
        this.watchIdsBlacklist.remove(str);
        return true;
    }

    /* access modifiers changed from: private */
    public final IONGLOCLocationResult toOSLocationResult(Location location) {
        return new IONGLOCLocationResult(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), IONGLOCBuildConfig.INSTANCE.getAndroidSdkVersionCode() >= 26 ? Float.valueOf(location.getVerticalAccuracyMeters()) : null, location.getBearing(), location.getSpeed(), location.getTime());
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCController$Companion;", "", "()V", "LOG_TAG", "", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCController.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
