package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdv extends zzv {
    private final zzdr zza;

    zzdv(zzdr zzdr) {
        this.zza = zzdr;
    }

    /* access modifiers changed from: package-private */
    public final zzdv zzc(ListenerHolder listenerHolder) {
        this.zza.zzb(listenerHolder);
        return this;
    }

    public final void zzd(LocationResult locationResult) throws RemoteException {
        this.zza.zza().notifyListener(new zzds(this, locationResult));
    }

    public final void zze(LocationAvailability locationAvailability) throws RemoteException {
        this.zza.zza().notifyListener(new zzdt(this, locationAvailability));
    }

    public final void zzf() {
        this.zza.zza().notifyListener(new zzdu(this));
    }

    /* access modifiers changed from: package-private */
    public final void zzg() {
        this.zza.zza().clear();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdr zzh() {
        return this.zza;
    }
}
