package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzac extends zzae {
    final /* synthetic */ long zza;
    final /* synthetic */ PendingIntent zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(zzaf zzaf, GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zza = j;
        this.zzb = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        PendingIntent pendingIntent = this.zzb;
        zzg zzg = (zzg) anyClient;
        Preconditions.checkNotNull(pendingIntent);
        long j = this.zza;
        Preconditions.checkArgument(j >= 0, "detectionIntervalMillis must be >= 0");
        ((zzv) zzg.getService()).zzh(j, true, pendingIntent);
        setResult(Status.RESULT_SUCCESS);
    }
}
