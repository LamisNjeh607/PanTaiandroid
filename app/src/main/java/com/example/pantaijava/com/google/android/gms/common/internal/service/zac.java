package com.example.pantaijava.com.google.android.gms.common.internal.service;

import android.os.RemoteException;

import com.example.pantaijava.com.google.android.gms.common.api.internal.zah;
import com.example.pantaijava.com.google.android.gms.common.api.zad;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zal;
import com.google.android.gms.common.images.zae;
import com.google.android.gms.common.images.zaf;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zac extends zaf {
    zac(zae zae, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zal) ((zah) anyClient).getService()).zae(new zad(this));
    }
}
