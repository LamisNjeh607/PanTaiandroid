package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzam implements RemoteCall {
    private final /* synthetic */ PendingIntent zza;

    /* synthetic */ zzam(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzaj.zzb;
        zzai zzai = new zzai((TaskCompletionSource) obj2);
        Preconditions.checkNotNull(zzai, "ResultHolder not provided.");
        ((zzv) ((zzg) obj).getService()).zzk(this.zza, new StatusCallback(zzai));
    }
}
