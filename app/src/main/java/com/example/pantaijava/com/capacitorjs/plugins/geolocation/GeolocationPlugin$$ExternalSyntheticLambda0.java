package com.capacitorjs.plugins.geolocation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GeolocationPlugin$$ExternalSyntheticLambda0 implements ActivityResultCallback {
    public final /* synthetic */ GeolocationPlugin f$0;

    public /* synthetic */ GeolocationPlugin$$ExternalSyntheticLambda0(GeolocationPlugin geolocationPlugin) {
        this.f$0 = geolocationPlugin;
    }

    public final void onActivityResult(Object obj) {
        GeolocationPlugin.load$lambda$0(this.f$0, (ActivityResult) obj);
    }
}
