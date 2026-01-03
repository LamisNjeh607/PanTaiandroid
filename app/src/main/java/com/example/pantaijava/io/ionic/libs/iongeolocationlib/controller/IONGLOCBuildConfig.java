package io.ionic.libs.iongeolocationlib.controller;

import android.os.Build;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCBuildConfig;", "", "()V", "getAndroidSdkVersionCode", "", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCBuildConfig.kt */
public final class IONGLOCBuildConfig {
    public static final IONGLOCBuildConfig INSTANCE = new IONGLOCBuildConfig();

    private IONGLOCBuildConfig() {
    }

    public final int getAndroidSdkVersionCode() {
        return Build.VERSION.SDK_INT;
    }
}
