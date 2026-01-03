package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdg extends zzs {
    final /* synthetic */ TaskCompletionSource zza;

    zzdg(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }

    public final void zzc(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }

    public final void zzd(int i, PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }
}
