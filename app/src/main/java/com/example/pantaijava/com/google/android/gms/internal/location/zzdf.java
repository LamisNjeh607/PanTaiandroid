package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationSettingsResult;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdf extends zzaa {
    final /* synthetic */ BaseImplementation.ResultHolder zza;

    zzdf(BaseImplementation.ResultHolder resultHolder) {
        this.zza = resultHolder;
    }

    public final void zzb(LocationSettingsResult locationSettingsResult) {
        this.zza.setResult(locationSettingsResult);
    }
}
