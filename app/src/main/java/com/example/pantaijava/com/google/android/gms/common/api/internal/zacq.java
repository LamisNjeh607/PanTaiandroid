package com.example.pantaijava.com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zact;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zacq implements Runnable {
    final /* synthetic */ zact zaa;

    zacq(zact zact) {
        this.zaa = zact;
    }

    public final void run() {
        this.zaa.zah.zae(new ConnectionResult(4));
    }
}
