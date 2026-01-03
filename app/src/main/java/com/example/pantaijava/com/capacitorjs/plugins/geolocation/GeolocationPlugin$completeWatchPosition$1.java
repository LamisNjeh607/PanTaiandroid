package com.capacitorjs.plugins.geolocation;

import com.getcapacitor.PluginCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: GeolocationPlugin.kt */
final class GeolocationPlugin$completeWatchPosition$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PluginCall $call;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$completeWatchPosition$1(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        super(0);
        this.this$0 = geolocationPlugin;
        this.$call = pluginCall;
    }

    public final void invoke() {
        this.this$0.startWatch(this.$call);
    }
}
