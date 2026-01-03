package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzal> CREATOR = new zzam();
    public final int zza;
    public final int zzb;
    public final long zzc;
    public final long zzd;

    zzal(int i, int i2, long j, long j2) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = j;
        this.zzd = j2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzal) {
            zzal zzal = (zzal) obj;
            if (this.zza == zzal.zza && this.zzb == zzal.zzb && this.zzc == zzal.zzc && this.zzd == zzal.zzd) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), Integer.valueOf(this.zza), Long.valueOf(this.zzd), Long.valueOf(this.zzc));
    }

    public final String toString() {
        int i = this.zza;
        int length = String.valueOf(i).length();
        int i2 = this.zzb;
        int length2 = String.valueOf(i2).length();
        long j = this.zzd;
        int length3 = String.valueOf(j).length();
        long j2 = this.zzc;
        StringBuilder sb = new StringBuilder(length + 50 + length2 + 18 + length3 + 17 + String.valueOf(j2).length());
        sb.append("NetworkLocationStatus: Wifi status: ");
        sb.append(i);
        sb.append(" Cell status: ");
        sb.append(i2);
        sb.append(" elapsed time NS: ");
        sb.append(j);
        sb.append(" system time ms: ");
        sb.append(j2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
