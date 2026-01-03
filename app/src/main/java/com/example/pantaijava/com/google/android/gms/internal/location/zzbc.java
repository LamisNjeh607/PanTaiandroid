package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzbc implements OnCompleteListener {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ CountDownLatch zzb;

    /* synthetic */ zzbc(AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.zza = atomicReference;
        this.zzb = countDownLatch;
    }

    public final /* synthetic */ void onComplete(Task task) {
        if (task.isSuccessful()) {
            this.zza.set((LocationAvailability) task.getResult());
        }
        this.zzb.countDown();
    }
}
