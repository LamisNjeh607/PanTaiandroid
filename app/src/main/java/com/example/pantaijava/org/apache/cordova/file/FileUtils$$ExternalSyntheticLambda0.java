package org.apache.cordova.file;

import android.webkit.WebResourceResponse;
import androidx.webkit.WebViewAssetLoader;
import org.apache.cordova.CordovaResourceApi;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FileUtils$$ExternalSyntheticLambda0 implements WebViewAssetLoader.PathHandler {
    public final /* synthetic */ FileUtils f$0;
    public final /* synthetic */ CordovaResourceApi f$1;

    public /* synthetic */ FileUtils$$ExternalSyntheticLambda0(FileUtils fileUtils, CordovaResourceApi cordovaResourceApi) {
        this.f$0 = fileUtils;
        this.f$1 = cordovaResourceApi;
    }

    public final WebResourceResponse handle(String str) {
        return this.f$0.lambda$getPathHandler$0(this.f$1, str);
    }
}
