package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzed implements OnTokenCanceledListener {
    private final /* synthetic */ ICancelToken zza;

    /* synthetic */ zzed(ICancelToken iCancelToken) {
        this.zza = iCancelToken;
    }

    public final /* synthetic */ void onCanceled() {
        int i = zzdz.zze;
        try {
            this.zza.cancel();
        } catch (RemoteException unused) {
        }
    }
}
