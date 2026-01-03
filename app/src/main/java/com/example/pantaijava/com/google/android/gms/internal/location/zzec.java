package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzec implements OnTokenCanceledListener {
    private final /* synthetic */ zzdz zza;
    private final /* synthetic */ ListenerHolder.ListenerKey zzb;

    /* synthetic */ zzec(zzdz zzdz, ListenerHolder.ListenerKey listenerKey) {
        this.zza = zzdz;
        this.zzb = listenerKey;
    }

    public final /* synthetic */ void onCanceled() {
        try {
            this.zza.zzw(this.zzb, true, new TaskCompletionSource());
        } catch (RemoteException unused) {
        }
    }
}
