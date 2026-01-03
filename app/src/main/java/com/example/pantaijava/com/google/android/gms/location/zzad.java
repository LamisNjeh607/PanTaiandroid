package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.location.zze;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    private final boolean zza;
    private final zze zzb;

    zzad(boolean z, zze zze) {
        this.zza = z;
        this.zzb = zze;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        if (this.zza != zzad.zza || !Objects.equal(this.zzb, zzad.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LocationAvailabilityRequest[");
        if (this.zza) {
            sb.append("bypass, ");
        }
        if (this.zzb != null) {
            sb.append("impersonation=");
            sb.append(this.zzb);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        boolean z = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, z);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
