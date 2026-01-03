package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzm implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        byte b = 0;
        long j = 0;
        float[] fArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 4:
                        f = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 5:
                        f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 6:
                        j = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    case 7:
                        b = SafeParcelReader.readByte(parcel2, readHeader);
                        break;
                    case 8:
                        f3 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 9:
                        f4 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                fArr = SafeParcelReader.createFloatArray(parcel2, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new DeviceOrientation(fArr, f, f2, j, b, f3, f4);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DeviceOrientation[i];
    }
}
