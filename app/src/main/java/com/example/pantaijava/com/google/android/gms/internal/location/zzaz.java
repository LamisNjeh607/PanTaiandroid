package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzaz implements zzdr {
    private ListenerHolder zza;

    zzaz(ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    public final synchronized ListenerHolder zza() {
        return this.zza;
    }

    public final synchronized void zzb(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.zza;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.zza = listenerHolder;
        }
    }

    public final void zzc() {
    }
}
