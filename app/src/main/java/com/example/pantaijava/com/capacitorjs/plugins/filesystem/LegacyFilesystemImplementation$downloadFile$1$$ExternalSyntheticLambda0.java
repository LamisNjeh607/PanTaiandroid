package com.capacitorjs.plugins.filesystem;

import com.capacitorjs.plugins.filesystem.LegacyFilesystemImplementation;
import com.getcapacitor.JSObject;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LegacyFilesystemImplementation.FilesystemDownloadCallback f$0;
    public final /* synthetic */ JSObject f$1;

    public /* synthetic */ LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda0(LegacyFilesystemImplementation.FilesystemDownloadCallback filesystemDownloadCallback, JSObject jSObject) {
        this.f$0 = filesystemDownloadCallback;
        this.f$1 = jSObject;
    }

    public final void run() {
        LegacyFilesystemImplementation$downloadFile$1.invoke$lambda$0(this.f$0, this.f$1);
    }
}
