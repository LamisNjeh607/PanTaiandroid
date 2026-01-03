package com.google.android.gms.internal.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.DeviceOrientationListener;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzbi extends GoogleApi implements FusedLocationProviderClient {
    static final Api.ClientKey zza;
    public static final Api zzb;
    private static final Object zzc = new Object();
    private static Object zzd;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzb = new Api("LocationServices.API", new zzbf(), clientKey);
    }

    public zzbi(Activity activity) {
        super(activity, zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private final Task zza(LocationRequest locationRequest, ListenerHolder listenerHolder) {
        zzbh zzbh = new zzbh(this, listenerHolder, zzcd.zza);
        return doRegisterEventListener(RegistrationMethods.builder().register(new zzbt(zzbh, locationRequest)).unregister(zzbh).withHolder(listenerHolder).setMethodKey(2435).build());
    }

    private final Task zzb(LocationRequest locationRequest, ListenerHolder listenerHolder) {
        zzbh zzbh = new zzbh(this, listenerHolder, zzbz.zza);
        return doRegisterEventListener(RegistrationMethods.builder().register(new zzbu(zzbh, locationRequest)).unregister(zzbh).withHolder(listenerHolder).setMethodKey(2436).build());
    }

    private final Task zzc(DeviceOrientationRequest deviceOrientationRequest, ListenerHolder listenerHolder) {
        zzbm zzbm = new zzbm(listenerHolder, deviceOrientationRequest);
        return doRegisterEventListener(RegistrationMethods.builder().register(zzbm).unregister(new zzbn(listenerHolder)).withHolder(listenerHolder).setMethodKey(2434).build());
    }

    public final Task<Void> flushLocations() {
        return doWrite(TaskApiCall.builder().run(zzca.zza).setMethodKey(2422).build());
    }

    /* access modifiers changed from: protected */
    public final String getApiFallbackAttributionTag(Context context) {
        return null;
    }

    public final Task<Location> getCurrentLocation(int i, CancellationToken cancellationToken) {
        CurrentLocationRequest.Builder builder = new CurrentLocationRequest.Builder();
        builder.setPriority(i);
        CurrentLocationRequest build = builder.build();
        if (cancellationToken != null) {
            Preconditions.checkArgument(!cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task<Location> doRead = doRead(TaskApiCall.builder().run(new zzbp(build, cancellationToken)).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new zzbq(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final Task<Location> getLastLocation() {
        return doRead(TaskApiCall.builder().run(zzby.zza).setMethodKey(2414).build());
    }

    public final Task<LocationAvailability> getLocationAvailability() {
        return doRead(TaskApiCall.builder().run(zzbr.zza).setMethodKey(2416).build());
    }

    public final Task<Void> removeDeviceOrientationUpdates(DeviceOrientationListener deviceOrientationListener) {
        Class<DeviceOrientationListener> cls = DeviceOrientationListener.class;
        return doUnregisterEventListener(ListenerHolders.createListenerKey(deviceOrientationListener, "DeviceOrientationListener"), 2440).continueWith(zzcg.zza, zzbo.zza);
    }

    public final Task<Void> removeLocationUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzbx(pendingIntent)).setMethodKey(2418).build());
    }

    public final Task<Void> requestDeviceOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, DeviceOrientationListener deviceOrientationListener, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        Class<DeviceOrientationListener> cls = DeviceOrientationListener.class;
        return zzc(deviceOrientationRequest, ListenerHolders.createListenerHolder(deviceOrientationListener, looper, "DeviceOrientationListener"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzbs(pendingIntent, locationRequest)).setMethodKey(2417).build());
    }

    public final Task<Void> setMockLocation(Location location) {
        Preconditions.checkArgument(location != null);
        return doWrite(TaskApiCall.builder().run(new zzbl(location)).setMethodKey(2421).build());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        return com.google.android.gms.tasks.Tasks.forResult(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> setMockMode(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = zzc
            monitor-enter(r0)
            r1 = 2420(0x974, float:3.391E-42)
            r2 = 0
            if (r6 == 0) goto L_0x0043
            java.lang.Object r6 = zzd     // Catch:{ all -> 0x0067 }
            if (r6 != 0) goto L_0x0061
            java.lang.Object r6 = new java.lang.Object     // Catch:{ all -> 0x0067 }
            r6.<init>()     // Catch:{ all -> 0x0067 }
            zzd = r6     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r2 = com.google.android.gms.common.api.internal.RegistrationMethods.builder()     // Catch:{ all -> 0x0067 }
            com.google.android.gms.internal.location.zzcb r3 = com.google.android.gms.internal.location.zzcb.zza     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r2 = r2.register(r3)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.internal.location.zzcc r3 = com.google.android.gms.internal.location.zzcc.zza     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r2 = r2.unregister(r3)     // Catch:{ all -> 0x0067 }
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0067 }
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            java.lang.String r4 = r4.getSimpleName()     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.ListenerHolder r6 = com.google.android.gms.common.api.internal.ListenerHolders.createListenerHolder(r6, (android.os.Looper) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r6 = r2.withHolder(r6)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r6 = r6.setMethodKey(r1)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.RegistrationMethods r6 = r6.build()     // Catch:{ all -> 0x0067 }
            com.google.android.gms.tasks.Task r6 = r5.doRegisterEventListener(r6)     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return r6
        L_0x0043:
            java.lang.Object r6 = zzd     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x0061
            zzd = r2     // Catch:{ all -> 0x0067 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            java.lang.String r2 = r2.getSimpleName()     // Catch:{ all -> 0x0067 }
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r6 = com.google.android.gms.common.api.internal.ListenerHolders.createListenerKey(r6, r2)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.tasks.Task r6 = r5.doUnregisterEventListener(r6, r1)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.internal.location.zzcf r1 = com.google.android.gms.internal.location.zzcf.zza     // Catch:{ all -> 0x0067 }
            com.google.android.gms.internal.location.zzbk r2 = com.google.android.gms.internal.location.zzbk.zza     // Catch:{ all -> 0x0067 }
            com.google.android.gms.tasks.Task r6 = r6.continueWith(r1, r2)     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return r6
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.tasks.Task r6 = com.google.android.gms.tasks.Tasks.forResult(r2)
            return r6
        L_0x0067:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzbi.setMockMode(boolean):com.google.android.gms.tasks.Task");
    }

    public zzbi(Context context) {
        super(context, zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Location> getLastLocation(LastLocationRequest lastLocationRequest) {
        return doRead(TaskApiCall.builder().run(new zzbj(lastLocationRequest)).setMethodKey(2414).setFeatures(zzo.zzf).build());
    }

    public final Task<Void> removeLocationUpdates(LocationCallback locationCallback) {
        Class<LocationCallback> cls = LocationCallback.class;
        return doUnregisterEventListener(ListenerHolders.createListenerKey(locationCallback, "LocationCallback"), 2418).continueWith(zzce.zza, zzbw.zza);
    }

    public final Task<Void> requestDeviceOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, Executor executor, DeviceOrientationListener deviceOrientationListener) {
        Class<DeviceOrientationListener> cls = DeviceOrientationListener.class;
        return zzc(deviceOrientationRequest, ListenerHolders.createListenerHolder(deviceOrientationListener, executor, "DeviceOrientationListener"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        Class<LocationCallback> cls = LocationCallback.class;
        return zzb(locationRequest, ListenerHolders.createListenerHolder(locationCallback, looper, "LocationCallback"));
    }

    public final Task<Void> removeLocationUpdates(LocationListener locationListener) {
        Class<LocationListener> cls = LocationListener.class;
        return doUnregisterEventListener(ListenerHolders.createListenerKey(locationListener, "LocationListener"), 2418).continueWith(zzch.zza, zzbv.zza);
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        Class<LocationListener> cls = LocationListener.class;
        return zza(locationRequest, ListenerHolders.createListenerHolder(locationListener, looper, "LocationListener"));
    }

    public final Task<Location> getCurrentLocation(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken) {
        if (cancellationToken != null) {
            Preconditions.checkArgument(!cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task<Location> doRead = doRead(TaskApiCall.builder().run(new zzbp(currentLocationRequest, cancellationToken)).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new zzbq(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, Executor executor, LocationCallback locationCallback) {
        Class<LocationCallback> cls = LocationCallback.class;
        return zzb(locationRequest, ListenerHolders.createListenerHolder(locationCallback, executor, "LocationCallback"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, Executor executor, LocationListener locationListener) {
        Class<LocationListener> cls = LocationListener.class;
        return zza(locationRequest, ListenerHolders.createListenerHolder(locationListener, executor, "LocationListener"));
    }
}
