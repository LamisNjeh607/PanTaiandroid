package com.capacitorjs.plugins.filesystem;

import com.capacitorjs.plugins.filesystem.LegacyFilesystemImplementation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LegacyFilesystemImplementation.FilesystemDownloadCallback f$0;
    public final /* synthetic */ Exception f$1;

    public /* synthetic */ LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda1(LegacyFilesystemImplementation.FilesystemDownloadCallback filesystemDownloadCallback, Exception exc) {
        this.f$0 = filesystemDownloadCallback;
        this.f$1 = exc;
    }

    public final void run() {
        LegacyFilesystemImplementation$downloadFile$1.invoke$lambda$1(this.f$0, this.f$1);
    }
}
