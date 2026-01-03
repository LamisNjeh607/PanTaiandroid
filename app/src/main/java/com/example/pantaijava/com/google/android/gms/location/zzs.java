package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzs extends zzb implements zzt {
    public zzs() {
        super("com.google.android.gms.location.IDeviceOrientationListener");
    }

    public static zzt zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.IDeviceOrientationListener");
        return queryLocalInterface instanceof zzt ? (zzt) queryLocalInterface : new zzr(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc.zzd(parcel);
        zzd((DeviceOrientation) zzc.zza(parcel, DeviceOrientation.CREATOR));
        return true;
    }
}
