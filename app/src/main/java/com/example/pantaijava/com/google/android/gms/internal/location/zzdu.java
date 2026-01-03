package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdu implements ListenerHolder.Notifier {
    final /* synthetic */ zzdv zza;

    zzdu(zzdv zzdv) {
        this.zza = zzdv;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        LocationCallback locationCallback = (LocationCallback) obj;
        this.zza.zzh().zzc();
    }

    public final void onNotifyListenerFailed() {
    }
}
