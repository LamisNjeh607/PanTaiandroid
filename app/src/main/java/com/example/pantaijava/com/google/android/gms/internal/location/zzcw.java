package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzcw implements RemoteCall {
    private final /* synthetic */ GeofencingRequest zza;
    private final /* synthetic */ PendingIntent zzb;

    /* synthetic */ zzcw(GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        this.zza = geofencingRequest;
        this.zzb = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        int i = zzct.zza;
        ((zzdz) obj).zzE(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
