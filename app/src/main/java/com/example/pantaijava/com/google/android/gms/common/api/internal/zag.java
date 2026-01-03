package com.example.pantaijava.com.google.android.gms.common.api.internal;

import static com.google.android.gms.common.server.response.FastParser.zai;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class zag extends zac {
    private final TaskApiCall zaa;
    private final TaskCompletionSource zab;
    private final StatusExceptionMapper zad;

    public zag(int i, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zab = taskCompletionSource;
        this.zaa = taskApiCall;
        this.zad = statusExceptionMapper;
        if (i == 2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    public boolean zaa(zabq zabq) {
        return this.zaa.shouldAutoResolveMissingFeatures();
    }

    public Feature[] zab(zabq zabq) {
        return this.zaa.zab();
    }

    public void zad(Status status) {
        this.zab.trySetException(this.zad.getException(status));
    }

    public void zae(Exception exc) {
        this.zab.trySetException(exc);
    }

    @Override
    public void zaf(com.google.android.gms.common.api.internal.zabq zabq) throws DeadObjectException {

    }

    @Override
    public void zag(com.google.android.gms.common.api.internal.zaad zaad, boolean z) {

    }

    public void zaf(zabq zabq) throws DeadObjectException {
        try {
            this.zaa.doExecute(zabq.zaf(), this.zab);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zad(zai.zah(e2));
        } catch (RuntimeException e3) {
            this.zab.trySetException(e3);
        }
    }

    public void zag(zaad zaad, boolean z) {
        zaad.zad(this.zab, z);
    }
}
