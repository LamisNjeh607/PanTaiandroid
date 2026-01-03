package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzcv implements RemoteCall {
    private final /* synthetic */ List zza;

    /* synthetic */ zzcv(List list) {
        this.zza = list;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        int i = zzct.zza;
        ((zzdz) obj).zzF(zzem.zza(this.zza), (TaskCompletionSource) obj2);
    }
}
