package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzw;
import com.google.android.gms.location.zzy;
import com.google.android.gms.location.zzz;

@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzei extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzei> CREATOR = new zzej();
    private final int zza;
    private final zzeg zzb;
    private final zzz zzc;
    private final zzw zzd;
    private final PendingIntent zze;
    private final zzr zzf;
    private final String zzg;

    zzei(int i, zzeg zzeg, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, IBinder iBinder3, String str) {
        this.zza = i;
        this.zzb = zzeg;
        zzr zzr = null;
        this.zzc = iBinder != null ? zzy.zzb(iBinder) : null;
        this.zze = pendingIntent;
        this.zzd = iBinder2 != null ? zzv.zzb(iBinder2) : null;
        if (iBinder3 != null) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder3);
        }
        this.zzf = zzr;
        this.zzg = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        IBinder iBinder2;
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzz zzz = this.zzc;
        IBinder iBinder3 = null;
        if (zzz == null) {
            iBinder = null;
        } else {
            iBinder = zzz.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zze, i, false);
        zzw zzw = this.zzd;
        if (zzw == null) {
            iBinder2 = null;
        } else {
            iBinder2 = zzw.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, iBinder2, false);
        zzr zzr = this.zzf;
        if (zzr != null) {
            iBinder3 = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, iBinder3, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
