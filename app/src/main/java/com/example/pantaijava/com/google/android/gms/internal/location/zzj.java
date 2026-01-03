package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzs;
import com.google.android.gms.location.zzt;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    final int zza;
    final zzh zzb;
    final zzt zzc;
    final zzr zzd;

    zzj(int i, zzh zzh, IBinder iBinder, IBinder iBinder2) {
        zzt zzt;
        this.zza = i;
        this.zzb = zzh;
        zzr zzr = null;
        if (iBinder == null) {
            zzt = null;
        } else {
            zzt = zzs.zzb(iBinder);
        }
        this.zzc = zzt;
        if (iBinder2 != null) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder2);
        }
        this.zzd = zzr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzt zzt = this.zzc;
        IBinder iBinder2 = null;
        if (zzt == null) {
            iBinder = null;
        } else {
            iBinder = zzt.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        zzr zzr = this.zzd;
        if (zzr != null) {
            iBinder2 = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
