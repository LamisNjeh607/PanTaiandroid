package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzbl implements RemoteCall {
    private final /* synthetic */ Location zza;

    /* synthetic */ zzbl(Location location) {
        this.zza = location;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzA(this.zza, (TaskCompletionSource) obj2);
    }
}
