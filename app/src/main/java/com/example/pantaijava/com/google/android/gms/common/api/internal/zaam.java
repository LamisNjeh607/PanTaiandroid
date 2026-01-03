package com.example.pantaijava.com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zaao;
import com.google.android.gms.common.api.internal.zabf;
import com.google.android.gms.common.api.internal.zabg;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaam extends zabg {
    final /* synthetic */ ConnectionResult zaa;
    final /* synthetic */ zaao zab;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaam(zaao zaao, zabf zabf, ConnectionResult connectionResult) {
        super(zabf);
        this.zab = zaao;
        this.zaa = connectionResult;
    }

    public final void zaa() {
        this.zab.zaa.zaD(this.zaa);
    }
}
