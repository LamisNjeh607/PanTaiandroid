package com.google.android.gms.internal.location;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzea implements OnCompleteListener {
    private final /* synthetic */ TaskCompletionSource zza;

    /* synthetic */ zzea(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final /* synthetic */ void onComplete(Task task) {
        int i = zzdz.zze;
        if (!task.isSuccessful()) {
            this.zza.trySetException((Exception) Objects.requireNonNull(task.getException()));
        }
    }
}
