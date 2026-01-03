package com.capacitorjs.plugins.filesystem;

import android.content.Context;
import io.ionic.libs.ionfilesystemlib.IONFILEController;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ionic/libs/ionfilesystemlib/IONFILEController;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$controller$2 extends Lambda implements Function0<IONFILEController> {
    final /* synthetic */ FilesystemPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesystemPlugin$controller$2(FilesystemPlugin filesystemPlugin) {
        super(0);
        this.this$0 = filesystemPlugin;
    }

    public final void invoke() {
        Context applicationContext = this.this$0.getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return new IONFILEController(applicationContext);
    }
}
