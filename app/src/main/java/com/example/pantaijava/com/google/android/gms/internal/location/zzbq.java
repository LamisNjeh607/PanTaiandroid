package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzbq implements Continuation {
    private final /* synthetic */ TaskCompletionSource zza;

    /* synthetic */ zzbq(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final /* synthetic */ Object then(Task task) {
        Api api = zzbi.zzb;
        TaskCompletionSource taskCompletionSource = this.zza;
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult((Location) task.getResult());
            return null;
        }
        taskCompletionSource.trySetException((Exception) Objects.requireNonNull(task.getException()));
        return null;
    }
}
