package com.google.android.gms.internal.location;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzf implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r4 = r2
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0056
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1
            if (r2 == r3) goto L_0x0051
            r3 = 3
            if (r2 == r3) goto L_0x004c
            r3 = 4
            if (r2 == r3) goto L_0x0047
            r3 = 6
            if (r2 == r3) goto L_0x0042
            r3 = 7
            if (r2 == r3) goto L_0x0038
            r3 = 8
            if (r2 == r3) goto L_0x0031
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x000c
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.common.Feature> r2 = com.google.android.gms.common.Feature.CREATOR
            java.util.ArrayList r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r11, r1, r2)
            goto L_0x000c
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.internal.location.zze> r2 = com.google.android.gms.internal.location.zze.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r9 = r1
            com.google.android.gms.internal.location.zze r9 = (com.google.android.gms.internal.location.zze) r9
            goto L_0x000c
        L_0x0042:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0047:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x004c:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0051:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r1)
            goto L_0x000c
        L_0x0056:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r0)
            com.google.android.gms.internal.location.zze r11 = new com.google.android.gms.internal.location.zze
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zze[i];
    }
}
