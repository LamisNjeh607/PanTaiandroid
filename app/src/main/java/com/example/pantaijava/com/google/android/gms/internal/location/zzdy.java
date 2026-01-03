package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzy;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdy extends zzy {
    private final zzdr zza;

    zzdy(zzdr zzdr) {
        this.zza = zzdr;
    }

    /* access modifiers changed from: package-private */
    public final zzdy zzc(ListenerHolder listenerHolder) {
        this.zza.zzb(listenerHolder);
        return this;
    }

    public final void zzd(Location location) {
        this.zza.zza().notifyListener(new zzdw(this, location));
    }

    public final void zze() {
        this.zza.zza().notifyListener(new zzdx(this));
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zza.zza().clear();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdr zzg() {
        return this.zza;
    }
}
