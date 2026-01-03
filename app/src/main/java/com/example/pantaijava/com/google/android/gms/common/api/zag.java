package com.example.pantaijava.com.google.android.gms.common.api;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zag<R extends Result> extends BasePendingResult<R> {
    private final Result zae;

    public zag(GoogleApiClient googleApiClient, Result result) {
        super(googleApiClient);
        this.zae = result;
    }

    /* access modifiers changed from: protected */
    public final R createFailedResult(Status status) {
        return this.zae;
    }

    @NonNull
    @Override
    protected R createFailedResult(@NonNull com.google.android.gms.common.api.Status status) {
        return null;
    }
}
