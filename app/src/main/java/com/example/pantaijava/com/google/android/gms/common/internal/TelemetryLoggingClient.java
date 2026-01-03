package com.example.pantaijava.com.google.android.gms.common.internal;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.DoNotMock;



/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public interface TelemetryLoggingClient extends HasApiKey<TelemetryLoggingOptions> {

    Task<Void> log(TelemetryData telemetryData);
}
