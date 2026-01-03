package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zze;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzaf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        WorkSource workSource = new WorkSource();
        zze zze = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        long j = -1;
        float f = 0.0f;
        int i3 = Integer.MAX_VALUE;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        long j5 = 600000;
        long j6 = 3600000;
        int i4 = 102;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    j6 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    j5 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 8:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 11:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 13:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 15:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 16:
                    workSource = (WorkSource) SafeParcelReader.createParcelable(parcel2, readHeader, WorkSource.CREATOR);
                    break;
                case 17:
                    zze = (zze) SafeParcelReader.createParcelable(parcel2, readHeader, zze.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new LocationRequest(i4, j6, j5, j4, j2, j3, i3, f, z, j, i, i2, z2, workSource, zze);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }
}
