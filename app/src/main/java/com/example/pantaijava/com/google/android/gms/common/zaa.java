package com.example.pantaijava.com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.pantaijava.com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final /* synthetic */ class zaa implements SuccessContinuation, Parcelable.Creator<ClientIdentity> {
    public static final /* synthetic */ zaa zaa = new zaa();

    public/* synthetic */ zaa() {
    }

    public final Task then(Object obj) {
        Map map = (Map) obj;
        int i = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return Tasks.forResult(null);
    }

    @Override
    public ClientIdentity createFromParcel(Parcel source) {
        return null;
    }

    @Override
    public ClientIdentity[] newArray(int size) {
        return new ClientIdentity[0];
    }
}
