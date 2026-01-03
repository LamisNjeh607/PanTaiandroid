package com.capacitorjs.plugins.filesystem;

import android.os.Handler;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.plugin.util.HttpRequestHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LegacyFilesystemImplementation.kt */
final class LegacyFilesystemImplementation$downloadFile$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Bridge $bridge;
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ LegacyFilesystemImplementation.FilesystemDownloadCallback $callback;
    final /* synthetic */ HttpRequestHandler.ProgressEmitter $emitter;
    final /* synthetic */ Handler $handler;
    final /* synthetic */ String $urlString;
    final /* synthetic */ LegacyFilesystemImplementation this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyFilesystemImplementation$downloadFile$1(LegacyFilesystemImplementation legacyFilesystemImplementation, String str, PluginCall pluginCall, Bridge bridge, HttpRequestHandler.ProgressEmitter progressEmitter, Handler handler, LegacyFilesystemImplementation.FilesystemDownloadCallback filesystemDownloadCallback) {
        super(0);
        this.this$0 = legacyFilesystemImplementation;
        this.$urlString = str;
        this.$call = pluginCall;
        this.$bridge = bridge;
        this.$emitter = progressEmitter;
        this.$handler = handler;
        this.$callback = filesystemDownloadCallback;
    }

    public final void invoke() {
        try {
            this.$handler.post(new LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda0(this.$callback, this.this$0.doDownloadInBackground(this.$urlString, this.$call, this.$bridge, this.$emitter)));
        } catch (Exception e) {
            this.$handler.post(new LegacyFilesystemImplementation$downloadFile$1$$ExternalSyntheticLambda1(this.$callback, e));
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(LegacyFilesystemImplementation.FilesystemDownloadCallback filesystemDownloadCallback, JSObject jSObject) {
        Intrinsics.checkNotNullParameter(filesystemDownloadCallback, "$callback");
        Intrinsics.checkNotNullParameter(jSObject, "$result");
        filesystemDownloadCallback.onSuccess(jSObject);
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$1(LegacyFilesystemImplementation.FilesystemDownloadCallback filesystemDownloadCallback, Exception exc) {
        Intrinsics.checkNotNullParameter(filesystemDownloadCallback, "$callback");
        Intrinsics.checkNotNullParameter(exc, "$error");
        filesystemDownloadCallback.onError(exc);
    }
}
