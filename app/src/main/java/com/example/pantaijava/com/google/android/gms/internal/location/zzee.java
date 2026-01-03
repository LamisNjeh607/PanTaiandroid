package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzee extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzee> CREATOR = new zzef();
    private final int zza;
    private final IBinder zzb;
    private final IBinder zzc;
    private final PendingIntent zzd;
    private final String zze;

    zzee(int i, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, String str) {
        this.zza = i;
        this.zzb = iBinder;
        this.zzc = iBinder2;
        this.zzd = pendingIntent;
        this.zze = str;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zza(android.os.IInterface r7, com.google.android.gms.location.zzz r8, java.lang.String r9) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            if (r7 != 0) goto L_0x0005
            r7 = 0
        L_0x0005:
            r2 = r7
            r1 = 1
            r4 = 0
            r0 = r6
            r3 = r8
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zza(android.os.IInterface, com.google.android.gms.location.zzz, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zzb(android.os.IInterface r7, com.google.android.gms.location.zzw r8, java.lang.String r9) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            if (r7 != 0) goto L_0x0005
            r7 = 0
        L_0x0005:
            r2 = r7
            r1 = 2
            r4 = 0
            r0 = r6
            r3 = r8
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzb(android.os.IInterface, com.google.android.gms.location.zzw, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    public static zzee zzc(PendingIntent pendingIntent) {
        return new zzee(3, (IBinder) null, (IBinder) null, pendingIntent, (String) null);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zzd(com.google.android.gms.internal.location.zzz r7) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            r4 = 0
            r5 = 0
            r1 = 4
            r2 = 0
            r0 = r6
            r3 = r7
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzd(com.google.android.gms.internal.location.zzz):com.google.android.gms.internal.location.zzee");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
