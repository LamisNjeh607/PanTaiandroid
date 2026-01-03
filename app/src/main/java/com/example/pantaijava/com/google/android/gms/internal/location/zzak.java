package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzak implements RemoteCall {
    private final /* synthetic */ PendingIntent zza;

    /* synthetic */ zzak(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzaj.zzb;
        ((zzg) obj).zzp(this.zza);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
