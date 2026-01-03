package com.capacitorjs.plugins.filesystem;

import android.media.MediaScannerConnection;
import com.capacitorjs.plugins.filesystem.LegacyFilesystemImplementation;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/capacitorjs/plugins/filesystem/FilesystemPlugin$downloadFile$1", "Lcom/capacitorjs/plugins/filesystem/LegacyFilesystemImplementation$FilesystemDownloadCallback;", "onError", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "result", "Lcom/getcapacitor/JSObject;", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemPlugin.kt */
public final class FilesystemPlugin$downloadFile$1 implements LegacyFilesystemImplementation.FilesystemDownloadCallback {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ String $directory;
    final /* synthetic */ FilesystemPlugin this$0;

    FilesystemPlugin$downloadFile$1(FilesystemPlugin filesystemPlugin, String str, PluginCall pluginCall) {
        this.this$0 = filesystemPlugin;
        this.$directory = str;
        this.$call = pluginCall;
    }

    public void onSuccess(JSObject jSObject) {
        Intrinsics.checkNotNullParameter(jSObject, "result");
        LegacyFilesystemImplementation access$getLegacyImplementation$p = this.this$0.legacyImplementation;
        if (access$getLegacyImplementation$p != null && access$getLegacyImplementation$p.isPublicDirectory(this.$directory)) {
            MediaScannerConnection.scanFile(this.this$0.getContext(), new String[]{jSObject.getString("path")}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
        }
        this.$call.resolve(jSObject);
    }

    public void onError(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "error");
        this.$call.reject("Error downloading file: " + exc.getLocalizedMessage(), exc);
    }
}
