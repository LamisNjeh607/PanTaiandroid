package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdi implements zzdr {
    final /* synthetic */ ListenerHolder zza;
    final /* synthetic */ TaskCompletionSource zzb;

    zzdi(zzdz zzdz, ListenerHolder listenerHolder, TaskCompletionSource taskCompletionSource) {
        this.zza = listenerHolder;
        this.zzb = taskCompletionSource;
    }

    public final ListenerHolder zza() {
        return this.zza;
    }

    public final void zzb(ListenerHolder listenerHolder) {
        throw new IllegalStateException();
    }

    public final void zzc() {
        this.zzb.trySetResult(null);
    }
}
