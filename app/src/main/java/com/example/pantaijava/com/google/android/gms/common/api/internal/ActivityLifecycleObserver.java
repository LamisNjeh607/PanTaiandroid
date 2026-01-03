package com.google.android.gms.common.api.internal;

import android.annotation.SuppressLint;
import android.app.Activity;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class ActivityLifecycleObserver {
    @SuppressLint("VisibleForTests")
    public static final ActivityLifecycleObserver of(Activity activity) {
        return new zab(zaa.zaa(activity));
    }

    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);
}
