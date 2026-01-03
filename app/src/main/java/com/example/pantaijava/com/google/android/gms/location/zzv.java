package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzv extends zzb implements zzw {
    public zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzw zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof zzw ? (zzw) queryLocalInterface : new zzu(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc.zzd(parcel);
            zzd((LocationResult) zzc.zza(parcel, LocationResult.CREATOR));
        } else if (i == 2) {
            zzc.zzd(parcel);
            zze((LocationAvailability) zzc.zza(parcel, LocationAvailability.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            zzf();
        }
        return true;
    }
}
