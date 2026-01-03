package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzby implements RemoteCall {
    static final /* synthetic */ zzby zza = new zzby();

    private /* synthetic */ zzby() {
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        ((zzdz) obj).zzq(new LastLocationRequest.Builder().build(), (TaskCompletionSource) obj2);
    }
}
