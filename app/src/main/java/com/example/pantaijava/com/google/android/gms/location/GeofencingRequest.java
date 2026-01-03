package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.location.zzek;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzp();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    private final List zza;
    private final int zzb;
    private final String zzc;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final List zza = new ArrayList();
        private int zzb = 5;

        public Builder addGeofence(Geofence geofence) {
            Preconditions.checkArgument(geofence instanceof zzek, "Geofence must be created using Geofence.Builder.");
            this.zza.add((zzek) geofence);
            return this;
        }

        public Builder addGeofences(List<? extends Geofence> list) {
            for (Geofence addGeofence : list) {
                addGeofence(addGeofence);
            }
            return this;
        }

        public GeofencingRequest build() {
            Preconditions.checkArgument(!this.zza.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(new ArrayList(this.zza), this.zzb, (String) null);
        }

        public Builder setInitialTrigger(int i) {
            this.zzb = i & 7;
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public @interface InitialTrigger {
    }

    GeofencingRequest(List list, int i, String str) {
        this.zza = list;
        this.zzb = i;
        this.zzc = str;
    }

    public List<Geofence> getGeofences() {
        return new ArrayList(this.zza);
    }

    public int getInitialTrigger() {
        return this.zzb;
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        int length = String.valueOf(valueOf).length();
        int i = this.zzb;
        StringBuilder sb = new StringBuilder(length + 45 + String.valueOf(i).length() + 1);
        sb.append("GeofencingRequest[geofences=");
        sb.append(valueOf);
        sb.append(", initialTrigger=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
