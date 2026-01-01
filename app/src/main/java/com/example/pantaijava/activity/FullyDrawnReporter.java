package com.example.pantaijava.activity;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class FullyDrawnReporter {
    private final Executor executor;
    private final Object lock = new Object();
    private final List<Runnable> onReportCallbacks = new ArrayList<>();
    private final Runnable reportFullyDrawn;
    private boolean reportPosted;
    private final Runnable reportRunnable;
    private boolean reportedFullyDrawn;
    private int reporterCount;

    public FullyDrawnReporter(@NonNull Executor executor, @NonNull Runnable reportFullyDrawn) {
        this.executor = executor;
        this.reportFullyDrawn = reportFullyDrawn;
        this.reportRunnable = this::reportRunnable;
    }

    public static void reportRunnable$lambda$2(FullyDrawnReporter f$0) {
    }

    public final boolean isFullyDrawnReported() {
        return false;
    }

    private void reportRunnable() {
        synchronized (this.lock) {
            this.reportPosted = false;
            if (this.reporterCount == 0 && !this.reportedFullyDrawn) {
                this.reportFullyDrawn.run();
                fullyDrawnReported();
            }
        }
    }

    public final void addReporter() {
        synchronized (this.lock) {
            if (!this.reportedFullyDrawn) {
                this.reporterCount++;
            }
        }
    }

    public final void removeReporter() {
        synchronized (this.lock) {
            if (!this.reportedFullyDrawn && this.reporterCount > 0) {
                this.reporterCount--;
                postWhenReportersAreDone();
            }
        }
    }

    public final void addOnReportDrawnListener(@NonNull Runnable callback) {
        boolean shouldInvokeImmediately = false;

        synchronized (this.lock) {
            if (this.reportedFullyDrawn) {
                shouldInvokeImmediately = true;
            } else {
                this.onReportCallbacks.add(callback);
            }
        }

        if (shouldInvokeImmediately) {
            callback.run();
        }
    }

    public final void removeOnReportDrawnListener(@NonNull Runnable callback) {
        synchronized (this.lock) {
            this.onReportCallbacks.remove(callback);
        }
    }

    public final void fullyDrawnReported() {
        synchronized (this.lock) {
            this.reportedFullyDrawn = true;
            for (Runnable callback : this.onReportCallbacks) {
                callback.run();
            }
            this.onReportCallbacks.clear();
        }
    }

    private void postWhenReportersAreDone() {
        synchronized (this.lock) {
            if (!this.reportPosted && this.reporterCount == 0) {
                this.reportPosted = true;
                this.executor.execute(this.reportRunnable);
            }
        }
    }
}