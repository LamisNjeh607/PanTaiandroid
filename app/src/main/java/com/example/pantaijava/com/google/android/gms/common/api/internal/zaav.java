package com.example.pantaijava.com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.zaau;
import com.google.android.gms.common.api.internal.zaaw;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class zaav implements Runnable {
    final /* synthetic */ zaaw zab;

    /* synthetic */
    public zaav(zaaw zaaw, zaau zaau) {
        this.zab = zaaw;
    }

    public final void run() {
        this.zab.zab.lock();
        try {
            if (!Thread.interrupted()) {
                zaa();
            }
        } catch (RuntimeException e) {
            this.zab.zaa.zam(e);
        } catch (Throwable th) {
            this.zab.zab.unlock();
            throw th;
        }
        this.zab.zab.unlock();
    }

    /* access modifiers changed from: protected */
    public abstract void zaa();
}
