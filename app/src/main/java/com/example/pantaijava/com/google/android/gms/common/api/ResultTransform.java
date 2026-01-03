package com.example.pantaijava.com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zacp;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class ResultTransform<R extends Result, S extends Result> {
    public final zacp createFailedResult(Status status) {
        return new zacp(status);
    }

    public Status onFailure(Status status) {
        return status;
    }

    public abstract PendingResult<S> onSuccess(R r);
}
