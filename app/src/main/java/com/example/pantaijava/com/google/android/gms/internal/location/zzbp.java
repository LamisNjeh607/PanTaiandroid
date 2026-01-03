package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzbp implements RemoteCall {
    private final /* synthetic */ CurrentLocationRequest zza;
    private final /* synthetic */ CancellationToken zzb;

    /* synthetic */ zzbp(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken) {
        this.zza = currentLocationRequest;
        this.zzb = cancellationToken;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzr(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
