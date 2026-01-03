package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzcl implements RemoteCall {
    private final /* synthetic */ ListenerHolder zza;
    private final /* synthetic */ DeviceOrientationRequest zzb;

    /* synthetic */ zzcl(ListenerHolder listenerHolder, DeviceOrientationRequest deviceOrientationRequest) {
        this.zza = listenerHolder;
        this.zzb = deviceOrientationRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        int i = zzci.zza;
        ((zzdz) obj).zzC(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
