package com.capacitorjs.plugins.geolocation;

import android.content.Context;
import android.os.Build;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import com.capacitorjs.plugins.geolocation.GeolocationErrors;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 52\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0017J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0007J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0003J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0003J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\fH\u0014J&\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\t2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0002J\u001e\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0002J\b\u0010\"\u001a\u00020\fH\u0016J\u001a\u0010#\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010&\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0017J\u0010\u0010'\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010(\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0007J\u001c\u0010)\u001a\u00020**\u00020\n2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020*H\u0002J\u0014\u0010-\u001a\u00020\f*\u00020\n2\u0006\u0010.\u001a\u00020/H\u0002J)\u00100\u001a\u00020\f*\u00020\n2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0002¢\u0006\u0002\u00104R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationPlugin;", "Lcom/getcapacitor/Plugin;", "()V", "controller", "Lio/ionic/libs/iongeolocationlib/controller/IONGLOCController;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "watchingCalls", "", "", "Lcom/getcapacitor/PluginCall;", "checkLocationState", "", "call", "onLocationEnabled", "Lkotlin/Function0;", "checkPermissions", "clearWatch", "completeCurrentPosition", "completeWatchPosition", "createOptions", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "getAlias", "getCurrentPosition", "getJSObjectForLocation", "Lcom/getcapacitor/JSObject;", "locationResult", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "getPosition", "handleOnDestroy", "handlePermissionRequest", "callbackName", "onPermissionGranted", "handlePermissionResult", "load", "onLocationError", "exception", "", "requestPermissions", "startWatch", "watchPosition", "getNumber", "", "name", "defaultValue", "sendError", "error", "Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "sendSuccess", "result", "keepCallback", "", "(Lcom/getcapacitor/PluginCall;Lcom/getcapacitor/JSObject;Ljava/lang/Boolean;)V", "Companion", "capacitor-geolocation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@CapacitorPlugin(name = "Geolocation", permissions = {@Permission(alias = "location", strings = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}), @Permission(alias = "coarseLocation", strings = {"android.permission.ACCESS_COARSE_LOCATION"})})
/* compiled from: GeolocationPlugin.kt */
public final class GeolocationPlugin extends Plugin {
    public static final String COARSE_LOCATION_ALIAS = "coarseLocation";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String LOCATION_ALIAS = "location";
    /* access modifiers changed from: private */
    public IONGLOCController controller;
    private CoroutineScope coroutineScope;
    private final Map<String, PluginCall> watchingCalls = new LinkedHashMap();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationPlugin$Companion;", "", "()V", "COARSE_LOCATION_ALIAS", "", "LOCATION_ALIAS", "capacitor-geolocation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: GeolocationPlugin.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void load() {
        super.load();
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
        ActivityResultLauncher registerForActivityResult = getActivity().registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new GeolocationPlugin$$ExternalSyntheticLambda0(this));
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(...)");
        this.controller = new IONGLOCController(fusedLocationProviderClient, registerForActivityResult, (IONGLOCServiceHelper) null, 4, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final void load$lambda$0(GeolocationPlugin geolocationPlugin, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(geolocationPlugin, "this$0");
        Intrinsics.checkNotNullParameter(activityResult, "result");
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), (CoroutineContext) null, (CoroutineStart) null, new GeolocationPlugin$load$activityLauncher$1$1(geolocationPlugin, activityResult, (Continuation<? super GeolocationPlugin$load$activityLauncher$1$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        super.handleOnDestroy();
        CoroutineScope coroutineScope2 = this.coroutineScope;
        if (coroutineScope2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope2 = null;
        }
        CoroutineScopeKt.cancel$default(coroutineScope2, (CancellationException) null, 1, (Object) null);
    }

    @PluginMethod
    public void checkPermissions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        checkLocationState(pluginCall, new GeolocationPlugin$checkPermissions$1(this, pluginCall));
    }

    @PluginMethod
    public void requestPermissions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        checkLocationState(pluginCall, new GeolocationPlugin$requestPermissions$1(this, pluginCall));
    }

    private final void checkLocationState(PluginCall pluginCall, Function0<Unit> function0) {
        IONGLOCController iONGLOCController = this.controller;
        if (iONGLOCController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
            iONGLOCController = null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (iONGLOCController.areLocationServicesEnabled(context)) {
            function0.invoke();
        } else {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getLOCATION_DISABLED());
        }
    }

    @PluginMethod
    public final void getCurrentPosition(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        handlePermissionRequest(pluginCall, "completeCurrentPosition", new GeolocationPlugin$getCurrentPosition$1(this, pluginCall));
    }

    @PluginMethod(returnType = "callback")
    public final void watchPosition(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        handlePermissionRequest(pluginCall, "completeWatchPosition", new GeolocationPlugin$watchPosition$1(this, pluginCall));
    }

    private final void handlePermissionRequest(PluginCall pluginCall, String str, Function0<Unit> function0) {
        String alias = getAlias(pluginCall);
        if (getPermissionState(alias) != PermissionState.GRANTED) {
            requestPermissionForAlias(alias, pluginCall, str);
        } else {
            function0.invoke();
        }
    }

    @PermissionCallback
    private final void completeCurrentPosition(PluginCall pluginCall) {
        handlePermissionResult(pluginCall, new GeolocationPlugin$completeCurrentPosition$1(this, pluginCall));
    }

    @PermissionCallback
    private final void completeWatchPosition(PluginCall pluginCall) {
        handlePermissionResult(pluginCall, new GeolocationPlugin$completeWatchPosition$1(this, pluginCall));
    }

    private final void handlePermissionResult(PluginCall pluginCall, Function0<Unit> function0) {
        if (getPermissionState(COARSE_LOCATION_ALIAS) == PermissionState.GRANTED) {
            function0.invoke();
        } else {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getLOCATION_PERMISSIONS_DENIED());
        }
    }

    @PluginMethod
    public final void clearWatch(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        String string = pluginCall.getString("id");
        CharSequence charSequence = string;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getWATCH_ID_NOT_PROVIDED());
            return;
        }
        PluginCall remove = this.watchingCalls.remove(string);
        if (remove != null) {
            remove.release(this.bridge);
        }
        IONGLOCController iONGLOCController = this.controller;
        if (iONGLOCController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
            iONGLOCController = null;
        }
        if (iONGLOCController.clearWatch(string)) {
            sendSuccess$default(this, pluginCall, (JSObject) null, (Boolean) null, 3, (Object) null);
        } else {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getWATCH_ID_NOT_FOUND());
        }
    }

    private final String getAlias(PluginCall pluginCall) {
        if (Build.VERSION.SDK_INT >= 31) {
            Boolean bool = pluginCall.getBoolean("enableHighAccuracy");
            if (bool == null) {
                bool = false;
            }
            if (!bool.booleanValue()) {
                return COARSE_LOCATION_ALIAS;
            }
        }
        return LOCATION_ALIAS;
    }

    /* access modifiers changed from: private */
    public final void getPosition(PluginCall pluginCall) {
        CoroutineScope coroutineScope2;
        CoroutineScope coroutineScope3 = this.coroutineScope;
        if (coroutineScope3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope2 = null;
        } else {
            coroutineScope2 = coroutineScope3;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new GeolocationPlugin$getPosition$1(this, pluginCall, (Continuation<? super GeolocationPlugin$getPosition$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void startWatch(PluginCall pluginCall) {
        CoroutineScope coroutineScope2;
        CoroutineScope coroutineScope3 = this.coroutineScope;
        if (coroutineScope3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope2 = null;
        } else {
            coroutineScope2 = coroutineScope3;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new GeolocationPlugin$startWatch$1(pluginCall, this, (Continuation<? super GeolocationPlugin$startWatch$1>) null), 3, (Object) null);
        Map<String, PluginCall> map = this.watchingCalls;
        String callbackId = pluginCall.getCallbackId();
        Intrinsics.checkNotNullExpressionValue(callbackId, "getCallbackId(...)");
        map.put(callbackId, pluginCall);
    }

    /* access modifiers changed from: private */
    public final JSObject getJSObjectForLocation(IONGLOCLocationResult iONGLOCLocationResult) {
        JSObject jSObject = new JSObject();
        jSObject.put("latitude", iONGLOCLocationResult.getLatitude());
        jSObject.put("longitude", iONGLOCLocationResult.getLongitude());
        jSObject.put("accuracy", (Object) Float.valueOf(iONGLOCLocationResult.getAccuracy()));
        jSObject.put("altitude", iONGLOCLocationResult.getAltitude());
        Float altitudeAccuracy = iONGLOCLocationResult.getAltitudeAccuracy();
        if (altitudeAccuracy != null) {
            jSObject.put("altitudeAccuracy", (Object) Float.valueOf(altitudeAccuracy.floatValue()));
        }
        jSObject.put("speed", (Object) Float.valueOf(iONGLOCLocationResult.getSpeed()));
        jSObject.put("heading", (Object) Float.valueOf(iONGLOCLocationResult.getHeading()));
        JSObject jSObject2 = new JSObject();
        jSObject2.put("timestamp", iONGLOCLocationResult.getTimestamp());
        jSObject2.put("coords", (Object) jSObject);
        return jSObject2;
    }

    /* access modifiers changed from: private */
    public final void onLocationError(Throwable th, PluginCall pluginCall) {
        if (th instanceof IONGLOCException.IONGLOCRequestDeniedException) {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getLOCATION_ENABLE_REQUEST_DENIED());
        } else if (th instanceof IONGLOCException.IONGLOCSettingsException) {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getLOCATION_SETTINGS_ERROR());
        } else if (th instanceof IONGLOCException.IONGLOCInvalidTimeoutException) {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getINVALID_TIMEOUT());
        } else if (th instanceof IONGLOCException.IONGLOCGoogleServicesException) {
            if (((IONGLOCException.IONGLOCGoogleServicesException) th).getResolvable()) {
                sendError(pluginCall, GeolocationErrors.INSTANCE.getGOOGLE_SERVICES_RESOLVABLE());
            } else {
                sendError(pluginCall, GeolocationErrors.INSTANCE.getGOOGLE_SERVICES_ERROR());
            }
        } else if (th instanceof IONGLOCException.IONGLOCLocationRetrievalTimeoutException) {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getGET_LOCATION_TIMEOUT());
        } else {
            sendError(pluginCall, GeolocationErrors.INSTANCE.getPOSITION_UNAVAILABLE());
        }
    }

    static /* synthetic */ void sendSuccess$default(GeolocationPlugin geolocationPlugin, PluginCall pluginCall, JSObject jSObject, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            jSObject = null;
        }
        if ((i & 2) != 0) {
            bool = false;
        }
        geolocationPlugin.sendSuccess(pluginCall, jSObject, bool);
    }

    /* access modifiers changed from: private */
    public final void sendSuccess(PluginCall pluginCall, JSObject jSObject, Boolean bool) {
        pluginCall.setKeepAlive(bool);
        if (jSObject != null) {
            pluginCall.resolve(jSObject);
        } else {
            pluginCall.resolve();
        }
    }

    private final void sendError(PluginCall pluginCall, GeolocationErrors.ErrorInfo errorInfo) {
        pluginCall.reject(errorInfo.getMessage(), errorInfo.getCode());
    }

    /* access modifiers changed from: private */
    public final IONGLOCLocationOptions createOptions(PluginCall pluginCall) {
        long number = getNumber(pluginCall, "timeout", DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
        long number2 = getNumber(pluginCall, "maximumAge", 0);
        Boolean bool = false;
        Boolean bool2 = pluginCall.getBoolean("enableHighAccuracy", bool);
        if (bool2 != null) {
            bool = bool2;
        }
        return new IONGLOCLocationOptions(number, number2, bool.booleanValue(), Long.valueOf(getNumber(pluginCall, "minimumUpdateInterval", 5000)));
    }

    private final long getNumber(PluginCall pluginCall, String str, long j) {
        Long l = pluginCall.getLong(str);
        if (l == null) {
            Integer num = pluginCall.getInt(str);
            l = num != null ? Long.valueOf((long) num.intValue()) : null;
        }
        return l == null ? j : l.longValue();
    }
}
