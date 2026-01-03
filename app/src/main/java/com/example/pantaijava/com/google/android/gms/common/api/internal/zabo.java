package com.example.pantaijava.com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.api.internal.zabq;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabo implements Runnable {
    final /* synthetic */ zabp zaa;

    zabo(zabp zabp) {
        this.zaa = zabp;
    }

    public final void run() {
        zabq zabq = this.zaa.zaa;
        zabq.zac.disconnect(String.valueOf(zabq.zac.getClass().getName()).concat(" disconnecting because it was signed out."));
    }
}
