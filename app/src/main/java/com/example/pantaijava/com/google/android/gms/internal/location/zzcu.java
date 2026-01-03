package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzcu implements RemoteCall {
    private final /* synthetic */ PendingIntent zza;

    /* synthetic */ zzcu(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        int i = zzct.zza;
        ((zzdz) obj).zzF(zzem.zzb(this.zza), (TaskCompletionSource) obj2);
    }
}
