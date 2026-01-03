package com.example.pantaijava.androidx.activity;

import android.annotation.SuppressLint;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImmLeaksCleaner.kt */
public final class ImmLeaksCleaner$Companion$cleaner$2 implements Function0<ImmLeaksCleaner.Cleaner> {
    public static final Function0<? extends com.example.pantaijava.androidx.activity.ImmLeaksCleaner.Cleaner> INSTANCE = new ImmLeaksCleaner$Companion$cleaner$2();

    ImmLeaksCleaner$Companion$cleaner$2() {

    }

    public final CoroutineScope invoke() {
        Class<InputMethodManager> cls = InputMethodManager.class;
        try {
            Field declaredField = cls.getDeclaredField("mServedView");
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("mNextServedView");
            declaredField2.setAccessible(true);
            @SuppressLint("SoonBlockedPrivateApi") Field declaredField3 = cls.getDeclaredField("mH");
            declaredField3.setAccessible(true);
            Intrinsics.checkNotNullExpressionValue(declaredField3, "hField");
            Intrinsics.checkNotNullExpressionValue(declaredField, "servedViewField");
            Intrinsics.checkNotNullExpressionValue(declaredField2, "nextServedViewField");
            return new ImmLeaksCleaner.ValidCleaner(declaredField3, declaredField, declaredField2);
        } catch (NoSuchFieldException unused) {
            return ImmLeaksCleaner.FailedInitialization.INSTANCE;
        }
    }
}
