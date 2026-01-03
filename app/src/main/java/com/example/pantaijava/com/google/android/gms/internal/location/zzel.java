package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzel implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = -1;
        int i2 = 0;
        short s = 0;
        int i3 = 0;
        long j = 0;
        float f = 0.0f;
        double d = 0.0d;
        double d2 = 0.0d;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    s = SafeParcelReader.readShort(parcel2, readHeader);
                    break;
                case 4:
                    d = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 5:
                    d2 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 8:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzek(str, i2, s, d, d2, f, j, i3, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzek[i];
    }
}
