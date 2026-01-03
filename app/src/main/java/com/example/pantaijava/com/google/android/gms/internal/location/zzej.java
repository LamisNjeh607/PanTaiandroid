package com.google.android.gms.internal.location;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzej implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r1 = 0
            r2 = 1
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r4 = r2
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x004f
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0040;
                case 3: goto L_0x003b;
                case 4: goto L_0x0031;
                case 5: goto L_0x002c;
                case 6: goto L_0x0027;
                case 7: goto L_0x001e;
                case 8: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r12, r1)
            goto L_0x000d
        L_0x0022:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x0027:
            android.os.IBinder r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r12, r1)
            goto L_0x000d
        L_0x002c:
            android.os.IBinder r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r12, r1)
            goto L_0x000d
        L_0x0031:
            android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r8 = r1
            android.app.PendingIntent r8 = (android.app.PendingIntent) r8
            goto L_0x000d
        L_0x003b:
            android.os.IBinder r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r12, r1)
            goto L_0x000d
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.internal.location.zzeg> r2 = com.google.android.gms.internal.location.zzeg.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r5 = r1
            com.google.android.gms.internal.location.zzeg r5 = (com.google.android.gms.internal.location.zzeg) r5
            goto L_0x000d
        L_0x004a:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r12, r1)
            goto L_0x000d
        L_0x004f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r12, r0)
            com.google.android.gms.internal.location.zzei r12 = new com.google.android.gms.internal.location.zzei
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzej.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzei[i];
    }
}
