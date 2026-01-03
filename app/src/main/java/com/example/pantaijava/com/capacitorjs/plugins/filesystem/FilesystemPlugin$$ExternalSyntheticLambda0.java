package com.capacitorjs.plugins.filesystem;

import com.getcapacitor.PluginCall;
import com.getcapacitor.plugin.util.HttpRequestHandler;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FilesystemPlugin$$ExternalSyntheticLambda0 implements HttpRequestHandler.ProgressEmitter {
    public final /* synthetic */ PluginCall f$0;
    public final /* synthetic */ FilesystemPlugin f$1;

    public /* synthetic */ FilesystemPlugin$$ExternalSyntheticLambda0(PluginCall pluginCall, FilesystemPlugin filesystemPlugin) {
        this.f$0 = pluginCall;
        this.f$1 = filesystemPlugin;
    }

    public final void emit(Integer num, Integer num2) {
        FilesystemPlugin.downloadFile$lambda$11(this.f$0, this.f$1, num, num2);
    }
}
