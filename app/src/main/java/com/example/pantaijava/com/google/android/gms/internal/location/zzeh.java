package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzeh implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = Long.MAX_VALUE;
        LocationRequest locationRequest = null;
        ArrayList<ClientIdentity> arrayList = null;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                locationRequest = (LocationRequest) SafeParcelReader.createParcelable(parcel2, readHeader, LocationRequest.CREATOR);
            } else if (fieldId == 5) {
                arrayList = SafeParcelReader.createTypedList(parcel2, readHeader, ClientIdentity.CREATOR);
            } else if (fieldId == 8) {
                z = SafeParcelReader.readBoolean(parcel2, readHeader);
            } else if (fieldId != 9) {
                switch (fieldId) {
                    case 11:
                        z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 12:
                        z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 13:
                        str = SafeParcelReader.createString(parcel2, readHeader);
                        break;
                    case 14:
                        j = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzeg(locationRequest, arrayList, z, z2, z3, z4, str, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzeg[i];
    }
}
