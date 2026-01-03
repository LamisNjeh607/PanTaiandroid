package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdx implements ListenerHolder.Notifier {
    final /* synthetic */ zzdy zza;

    zzdx(zzdy zzdy) {
        this.zza = zzdy;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        LocationListener locationListener = (LocationListener) obj;
        this.zza.zzg().zzc();
    }

    public final void onNotifyListenerFailed() {
    }
}
