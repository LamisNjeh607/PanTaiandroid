package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzau extends zzba {
    final /* synthetic */ LocationListener zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzau(zzbb zzbb, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.zza = locationListener;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        Class<LocationListener> cls = LocationListener.class;
        ((zzdz) anyClient).zzv(ListenerHolders.createListenerKey(this.zza, "LocationListener"), true, zzbb.zza(this));
    }
}
