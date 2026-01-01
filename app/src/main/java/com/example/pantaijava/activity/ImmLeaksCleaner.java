package com.example.pantaijava.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.lang.reflect.Field;

public class ImmLeaksCleaner implements LifecycleEventObserver {
    private final Activity activity;

    public ImmLeaksCleaner(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        Cleaner cleaner = Companion.getCleaner();
        Object lock = cleaner.getLock(imm);
        if (lock == null) {
            return;
        }

        synchronized (lock) {
            View servedView = cleaner.getServedView(imm);
            if (servedView == null) {
                return;
            }

            if (servedView.isAttachedToWindow()) {
                return;
            }

            if (cleaner.clearNextServedView(imm)) {
                imm.isActive();
            }
        }
    }

    // Cleaner abstraction
    private static abstract class Cleaner {
        public abstract Object getLock(InputMethodManager imm);
        public abstract View getServedView(InputMethodManager imm);
        public abstract boolean clearNextServedView(InputMethodManager imm);
    }

    // Failed initialization case
    private static class FailedInitialization extends Cleaner {
        public static final FailedInitialization INSTANCE = new FailedInitialization();

        @Override
        public Object getLock(InputMethodManager imm) {
            return null;
        }

        @Override
        public View getServedView(InputMethodManager imm) {
            return null;
        }

        @Override
        public boolean clearNextServedView(InputMethodManager imm) {
            return false;
        }
    }

    // Valid cleaner implementation using reflection
    private static class ValidCleaner extends Cleaner {
        private final Field hField;
        private final Field servedViewField;
        private final Field nextServedViewField;

        public ValidCleaner(Field hField, Field servedViewField, Field nextServedViewField) {
            this.hField = hField;
            this.servedViewField = servedViewField;
            this.nextServedViewField = nextServedViewField;

            // Make fields accessible
            if (hField != null) {
                hField.setAccessible(true);
            }
            if (servedViewField != null) {
                servedViewField.setAccessible(true);
            }
            if (nextServedViewField != null) {
                nextServedViewField.setAccessible(true);
            }
        }

        @Override
        public Object getLock(InputMethodManager imm) {
            try {
                return hField != null ? hField.get(imm) : null;
            } catch (IllegalAccessException e) {
                return null;
            }
        }

        @Override
        public View getServedView(InputMethodManager imm) {
            try {
                return servedViewField != null ? (View) servedViewField.get(imm) : null;
            } catch (IllegalAccessException | ClassCastException e) {
                return null;
            }
        }

        @Override
        public boolean clearNextServedView(InputMethodManager imm) {
            try {
                if (nextServedViewField != null) {
                    nextServedViewField.set(imm, null);
                    return true;
                }
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
        }
    }

    // Companion to provide the appropriate cleaner
    private static class Companion {
        private static Cleaner cleaner;

        public static Cleaner getCleaner() {
            if (cleaner == null) {
                synchronized (Companion.class) {
                    if (cleaner == null) {
                        cleaner = createCleaner();
                    }
                }
            }
            return cleaner;
        }

        private static Cleaner createCleaner() {
            try {
                // Try to get the required fields using reflection
                Class<?> immClass = InputMethodManager.class;

                Field hField = getField(immClass, "mH");
                Field servedViewField = getField(immClass, "mServedView");
                Field nextServedViewField = getField(immClass, "mNextServedView");

                if (hField != null && servedViewField != null && nextServedViewField != null) {
                    return new ValidCleaner(hField, servedViewField, nextServedViewField);
                }
            } catch (Exception e) {
                // Fall through to failed initialization
            }

            return FailedInitialization.INSTANCE;
        }

        private static Field getField(Class<?> clazz, String fieldName) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                return null;
            }
        }
    }
}