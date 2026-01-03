package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import android.app.Dialog;
import android.os.Looper;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J#\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H@¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H@¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b J+\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "(Lcom/google/android/gms/location/FusedLocationProviderClient;Landroidx/activity/result/ActivityResultLauncher;)V", "checkGooglePlayServicesAvailable", "Lkotlin/Result;", "", "activity", "Landroid/app/Activity;", "checkGooglePlayServicesAvailable-IoAF18A$IONGeolocationLib_release", "(Landroid/app/Activity;)Ljava/lang/Object;", "checkLocationSettings", "", "options", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "interval", "", "checkLocationSettings$IONGeolocationLib_release", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentLocation", "Landroid/location/Location;", "getCurrentLocation$IONGeolocationLib_release", "(Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeLocationUpdates", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "removeLocationUpdates$IONGeolocationLib_release", "requestLocationUpdates", "requestLocationUpdates$IONGeolocationLib_release", "sendResultWithGoogleServicesException", "resolvable", "message", "", "sendResultWithGoogleServicesException-gIAlu-s", "(ZLjava/lang/String;)Ljava/lang/Object;", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCServiceHelper.kt */
public final class IONGLOCServiceHelper {
    private final ActivityResultLauncher<IntentSenderRequest> activityLauncher;
    private final FusedLocationProviderClient fusedLocationClient;

    public IONGLOCServiceHelper(FusedLocationProviderClient fusedLocationProviderClient, ActivityResultLauncher<IntentSenderRequest> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "fusedLocationClient");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityLauncher");
        this.fusedLocationClient = fusedLocationProviderClient;
        this.activityLauncher = activityResultLauncher;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
        throw new io.ionic.libs.iongeolocationlib.model.IONGLOCException.IONGLOCSettingsException("There is an error with the location settings.", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008f, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0090, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x002a, B:21:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e A[ExcHandler: Exception (r5v13 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:10:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object checkLocationSettings$IONGeolocationLib_release(android.app.Activity r5, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r6, long r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1
            r0.<init>(r4, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r5 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper) r5
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ ResolvableApiException -> 0x0030, Exception -> 0x002e }
            goto L_0x0080
        L_0x002e:
            r5 = move-exception
            goto L_0x0085
        L_0x0030:
            r6 = move-exception
            goto L_0x0091
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.gms.location.LocationRequest$Builder r9 = new com.google.android.gms.location.LocationRequest$Builder
            boolean r6 = r6.getEnableHighAccuracy()
            if (r6 == 0) goto L_0x0048
            r6 = 100
            goto L_0x004a
        L_0x0048:
            r6 = 102(0x66, float:1.43E-43)
        L_0x004a:
            r9.<init>(r6, r7)
            com.google.android.gms.location.LocationRequest r6 = r9.build()
            java.lang.String r7 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            com.google.android.gms.location.LocationSettingsRequest$Builder r7 = new com.google.android.gms.location.LocationSettingsRequest$Builder
            r7.<init>()
            r7.addLocationRequest(r6)
            com.google.android.gms.location.SettingsClient r5 = com.google.android.gms.location.LocationServices.getSettingsClient((android.app.Activity) r5)
            java.lang.String r6 = "getSettingsClient(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            com.google.android.gms.location.LocationSettingsRequest r6 = r7.build()     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            com.google.android.gms.tasks.Task r5 = r5.checkLocationSettings(r6)     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            java.lang.String r6 = "checkLocationSettings(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            r0.L$0 = r4     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            r0.label = r3     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            java.lang.Object r5 = kotlinx.coroutines.tasks.TasksKt.await(r5, r0)     // Catch:{ ResolvableApiException -> 0x008f, Exception -> 0x002e }
            if (r5 != r1) goto L_0x007f
            return r1
        L_0x007f:
            r5 = r4
        L_0x0080:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ ResolvableApiException -> 0x0030, Exception -> 0x002e }
            return r5
        L_0x0085:
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCSettingsException r6 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCSettingsException
            java.lang.String r7 = "There is an error with the location settings."
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r6.<init>(r7, r5)
            throw r6
        L_0x008f:
            r6 = move-exception
            r5 = r4
        L_0x0091:
            androidx.activity.result.IntentSenderRequest$Builder r7 = new androidx.activity.result.IntentSenderRequest$Builder
            android.app.PendingIntent r6 = r6.getResolution()
            java.lang.String r8 = "getResolution(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
            r7.<init>((android.app.PendingIntent) r6)
            androidx.activity.result.IntentSenderRequest r6 = r7.build()
            androidx.activity.result.ActivityResultLauncher<androidx.activity.result.IntentSenderRequest> r5 = r5.activityLauncher
            r5.launch(r6)
            r5 = 0
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper.checkLocationSettings$IONGeolocationLib_release(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: checkGooglePlayServicesAvailable-IoAF18A$IONGeolocationLib_release  reason: not valid java name */
    public final Object m185checkGooglePlayServicesAvailableIoAF18A$IONGeolocationLib_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            Result.Companion companion = Result.Companion;
            return Result.m190constructorimpl(Unit.INSTANCE);
        } else if (!instance.isUserResolvableError(isGooglePlayServicesAvailable)) {
            return m184sendResultWithGoogleServicesExceptiongIAlus(false, "Google Play Services error.");
        } else {
            Dialog errorDialog = instance.getErrorDialog(activity, isGooglePlayServicesAvailable, 1);
            if (errorDialog != null) {
                errorDialog.show();
            }
            return m184sendResultWithGoogleServicesExceptiongIAlus(true, "Google Play Services error user resolvable.");
        }
    }

    /* renamed from: sendResultWithGoogleServicesException-gIAlu-s  reason: not valid java name */
    private final Object m184sendResultWithGoogleServicesExceptiongIAlus(boolean z, String str) {
        Result.Companion companion = Result.Companion;
        return Result.m190constructorimpl(ResultKt.createFailure(new IONGLOCException.IONGLOCGoogleServicesException(z, str, (Throwable) null, 4, (DefaultConstructorMarker) null)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getCurrentLocation$IONGeolocationLib_release(io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r8, kotlin.coroutines.Continuation<? super android.location.Location> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0077
        L_0x002b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = new com.google.android.gms.location.CurrentLocationRequest$Builder
            r9.<init>()
            boolean r2 = r8.getEnableHighAccuracy()
            if (r2 == 0) goto L_0x0044
            r2 = 100
            goto L_0x0046
        L_0x0044:
            r2 = 102(0x66, float:1.43E-43)
        L_0x0046:
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = r9.setPriority(r2)
            long r5 = r8.getMaximumAge()
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = r9.setMaxUpdateAgeMillis(r5)
            long r5 = r8.getTimeout()
            com.google.android.gms.location.CurrentLocationRequest$Builder r8 = r9.setDurationMillis(r5)
            com.google.android.gms.location.CurrentLocationRequest r8 = r8.build()
            java.lang.String r9 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            com.google.android.gms.location.FusedLocationProviderClient r9 = r7.fusedLocationClient
            com.google.android.gms.tasks.Task r8 = r9.getCurrentLocation((com.google.android.gms.location.CurrentLocationRequest) r8, (com.google.android.gms.tasks.CancellationToken) r4)
            java.lang.String r9 = "getCurrentLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.tasks.TasksKt.await(r8, r0)
            if (r9 != r1) goto L_0x0077
            return r1
        L_0x0077:
            android.location.Location r9 = (android.location.Location) r9
            if (r9 == 0) goto L_0x007c
            return r9
        L_0x007c:
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCLocationRetrievalTimeoutException r8 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCLocationRetrievalTimeoutException
            java.lang.String r9 = "Location request timed out"
            r0 = 2
            r8.<init>(r9, r4, r0, r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper.getCurrentLocation$IONGeolocationLib_release(io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void requestLocationUpdates$IONGeolocationLib_release(IONGLOCLocationOptions iONGLOCLocationOptions, LocationCallback locationCallback) {
        Intrinsics.checkNotNullParameter(iONGLOCLocationOptions, "options");
        Intrinsics.checkNotNullParameter(locationCallback, "locationCallback");
        LocationRequest.Builder builder = new LocationRequest.Builder(iONGLOCLocationOptions.getTimeout());
        builder.setMaxUpdateAgeMillis(iONGLOCLocationOptions.getMaximumAge());
        builder.setPriority(iONGLOCLocationOptions.getEnableHighAccuracy() ? 100 : 102);
        if (iONGLOCLocationOptions.getMinUpdateInterval() != null) {
            builder.setMinUpdateIntervalMillis(iONGLOCLocationOptions.getMinUpdateInterval().longValue());
        }
        LocationRequest build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.fusedLocationClient.requestLocationUpdates(build, locationCallback, Looper.getMainLooper());
    }

    public final void removeLocationUpdates$IONGeolocationLib_release(LocationCallback locationCallback) {
        Intrinsics.checkNotNullParameter(locationCallback, "locationCallback");
        this.fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
