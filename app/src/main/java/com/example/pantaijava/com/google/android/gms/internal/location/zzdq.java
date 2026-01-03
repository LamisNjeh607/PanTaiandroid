package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.DeviceOrientation;
import com.google.android.gms.location.zzs;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdq extends zzs {
    private ListenerHolder zza;

    zzdq(ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzc(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.zza;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.zza = listenerHolder;
        }
    }

    public final void zzd(DeviceOrientation deviceOrientation) {
        ListenerHolder listenerHolder;
        synchronized (this) {
            listenerHolder = this.zza;
        }
        listenerHolder.notifyListener(new zzdp(this, deviceOrientation));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zze() {
        this.zza.clear();
    }
}
