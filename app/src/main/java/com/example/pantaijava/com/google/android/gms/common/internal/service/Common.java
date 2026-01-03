package com.example.pantaijava.com.google.android.gms.common.internal.service;

import com.example.pantaijava.com.google.android.gms.common.api.internal.zah;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zab;
import com.google.android.gms.common.images.zae;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zah> CLIENT_KEY;
    public static final zae zaa = new zae();
    private static final Api.AbstractClientBuilder zab;

    static {
        Api.ClientKey<zah> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zab2 = new zab();
        zab = zab2;
        API = new Api<>("Common.API", zab2, clientKey);
    }
}
