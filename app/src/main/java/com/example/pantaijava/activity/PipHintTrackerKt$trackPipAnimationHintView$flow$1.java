package com.example.pantaijava.activity;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Complete Java implementation of PiP (Picture-in-Picture) hint tracker
 * that replaces the Kotlin coroutine version.
 */
public class PipHintTrackerKt$trackPipAnimationHintView$flow$1 {

    /**
     * Tracks a view for PiP animation hints and provides position updates.
     */
    public static PipHintTracker trackPipAnimationHintView(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("view cannot be null");
        }
        return new PipHintTracker(view);
    }

    /**
     * Gets the position of a view in window coordinates.
     */
    public static Rect trackPipAnimationHintView$positionInWindow(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("view cannot be null");
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    /**
     * The main tracker class that manages PiP hint position updates.
     */
    public static class PipHintTracker {
        private final View view;
        private final Executor backgroundExecutor;
        private final Handler mainHandler;
        private final AtomicBoolean isActive;

        // Listeners
        private ViewTreeObserver.OnScrollChangedListener scrollListener;
        private View.OnLayoutChangeListener layoutListener;
        private View.OnAttachStateChangeListener attachListener;
        private PipHintCallback callback;

        public PipHintTracker(@NonNull View view) {
            this.view = view;
            this.backgroundExecutor = Executors.newSingleThreadExecutor();
            this.mainHandler = new Handler(Looper.getMainLooper());
            this.isActive = new AtomicBoolean(false);
        }

        /**
         * Starts tracking the view for PiP hints.
         * @param callback The callback to receive position updates
         */
        public void start(@NonNull PipHintCallback callback) {
            if (isActive.getAndSet(true)) {
                return; // Already started
            }

            this.callback = callback;

            // Create scroll listener
            scrollListener = new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    notifyPositionChanged();
                }
            };

            // Create layout change listener
            layoutListener = new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (left != oldLeft || top != oldTop || right != oldRight || bottom != oldBottom) {
                        notifyPositionChanged();
                    }
                }
            };

            // Create attach state change listener
            attachListener = new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    // Add listeners when attached
                    mainHandler.post(() -> {
                        if (isActive.get()) {
                            view.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
                            view.addOnLayoutChangeListener(layoutListener);
                            notifyPositionChanged();
                        }
                    });
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    // Remove listeners when detached
                    mainHandler.post(() -> {
                        if (isActive.get()) {
                            view.getViewTreeObserver().removeOnScrollChangedListener(scrollListener);
                            view.removeOnLayoutChangeListener(layoutListener);
                        }
                    });
                }
            };

            // Setup initial state on main thread
            mainHandler.post(() -> {
                if (isActive.get()) {
                    // Add attach listener
                    view.addOnAttachStateChangeListener(attachListener);

                    // If already attached, setup other listeners
                    if (view.isAttachedToWindow()) {
                        view.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
                        view.addOnLayoutChangeListener(layoutListener);
                        notifyPositionChanged();
                    }
                }
            });
        }

        /**
         * Stops tracking the view.
         */
        public void stop() {
            if (!isActive.getAndSet(false)) {
                return; // Already stopped
            }

            mainHandler.post(() -> {
                // Remove all listeners
                if (scrollListener != null) {
                    view.getViewTreeObserver().removeOnScrollChangedListener(scrollListener);
                    scrollListener = null;
                }

                if (layoutListener != null) {
                    view.removeOnLayoutChangeListener(layoutListener);
                    layoutListener = null;
                }

                if (attachListener != null) {
                    view.removeOnAttachStateChangeListener(attachListener);
                    attachListener = null;
                }

                callback = null;
            });
        }

        /**
         * Checks if the tracker is active.
         */
        public boolean isActive() {
            return isActive.get();
        }

        /**
         * Gets the current view position.
         */
        public Rect getCurrentPosition() {
            return trackPipAnimationHintView$positionInWindow(view);
        }

        /**
         * Notifies callback of position change (runs on background thread).
         */
        private void notifyPositionChanged() {
            if (!isActive.get() || callback == null) {
                return;
            }

            backgroundExecutor.execute(() -> {
                if (isActive.get() && callback != null) {
                    Rect position = trackPipAnimationHintView$positionInWindow(view);
                    callback.onPositionChanged(position);
                }
            });
        }

        /**
         * Builder pattern for creating trackers with configuration.
         */
        public static class Builder {
            private final View view;
            private PipHintCallback callback;
            private boolean autoStart = true;

            public Builder(@NonNull View view) {
                this.view = view;
            }

            public Builder withCallback(@NonNull PipHintCallback callback) {
                this.callback = callback;
                return this;
            }

            public Builder autoStart(boolean autoStart) {
                this.autoStart = autoStart;
                return this;
            }

            public PipHintTracker build() {
                PipHintTracker tracker = new PipHintTracker(view);
                if (autoStart && callback != null) {
                    tracker.start(callback);
                }
                return tracker;
            }
        }
    }

    /**
     * Callback interface for receiving PiP hint position updates.
     */
    public interface PipHintCallback {
        void onPositionChanged(Rect position);
    }

    /**
     * Simple implementation for tracking with Runnable callbacks.
     */
    public static class SimplePipHintCallback implements PipHintCallback {
        private final OnPositionChangedListener listener;

        public SimplePipHintCallback(@NonNull OnPositionChangedListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPositionChanged(Rect position) {
            listener.onPositionChanged(position);
        }

        public interface OnPositionChangedListener {
            void onPositionChanged(Rect position);
        }
    }

    /**
     * Utility class for managing multiple trackers.
     */
    public static class PipHintManager {
        private final PipHintTracker tracker;

        public PipHintManager(@NonNull View view, @NonNull PipHintCallback callback) {
            this.tracker = trackPipAnimationHintView(view);
            this.tracker.start(callback);
        }

        public void stop() {
            tracker.stop();
        }

        public boolean isActive() {
            return tracker.isActive();
        }

        public Rect getCurrentPosition() {
            return tracker.getCurrentPosition();
        }
    }

    /**
     * Static helper methods for common PiP tracking scenarios.
     */
    public static class Helper {
        /**
         * Tracks a view and automatically enters PiP mode when bounds change significantly.
         */
        public static PipHintTracker trackForPipTransition(@NonNull View view,
                                                           @NonNull PipTransitionListener listener) {
            PipHintTracker tracker = trackPipAnimationHintView(view);
            tracker.start(new PipHintCallback() {
                private Rect lastBounds;
                private static final int MIN_CHANGE_THRESHOLD = 20; // pixels

                @Override
                public void onPositionChanged(Rect position) {
                    if (lastBounds == null || boundsChangedSignificantly(lastBounds, position)) {
                        lastBounds = position;
                        listener.onBoundsChangedForPip(position);
                    }
                }

                private boolean boundsChangedSignificantly(Rect oldBounds, Rect newBounds) {
                    return Math.abs(oldBounds.left - newBounds.left) > MIN_CHANGE_THRESHOLD ||
                            Math.abs(oldBounds.top - newBounds.top) > MIN_CHANGE_THRESHOLD ||
                            Math.abs(oldBounds.width() - newBounds.width()) > MIN_CHANGE_THRESHOLD ||
                            Math.abs(oldBounds.height() - newBounds.height()) > MIN_CHANGE_THRESHOLD;
                }
            });
            return tracker;
        }

        public interface PipTransitionListener {
            void onBoundsChangedForPip(Rect bounds);
        }
    }
}