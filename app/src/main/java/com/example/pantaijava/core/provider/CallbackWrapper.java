package androidx.core.provider;

import android.graphics.Typeface;
import androidx.core.provider.FontRequestWorker;
import androidx.core.provider.FontsContractCompat;
import java.util.concurrent.Executor;

class CallbackWrapper {
    private final FontsContractCompat.FontRequestCallback mCallback;
    private final Executor mExecutor;

    CallbackWrapper(FontsContractCompat.FontRequestCallback fontRequestCallback, Executor executor) {
        this.mCallback = fontRequestCallback;
        this.mExecutor = executor;
    }

    CallbackWrapper(FontsContractCompat.FontRequestCallback fontRequestCallback) {
        this(fontRequestCallback, RequestExecutor.createHandlerExecutor(CalleeHandler.create()));
    }

    private void onTypefaceRetrieved(final Typeface typeface) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mExecutor.execute(new Runnable() {
            public void run() {
                fontRequestCallback.onTypefaceRetrieved(typeface);
            }
        });
    }

    private void onTypefaceRequestFailed(final int i) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mExecutor.execute(new Runnable() {
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(i);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onTypefaceResult(FontRequestWorker.TypefaceResult typefaceResult) {
        if (typefaceResult.isSuccess()) {
            onTypefaceRetrieved(typefaceResult.mTypeface);
        } else {
            onTypefaceRequestFailed(typefaceResult.mResult);
        }
    }
}
