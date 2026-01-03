package com.example.pantaijava.androidx.activity;

import android.os.Build;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

import androidx.annotation.RequiresApi;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"androidx/activity/OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1", "Landroid/window/OnBackAnimationCallback;", "onBackCancelled", "", "onBackInvoked", "onBackProgressed", "backEvent", "Landroid/window/BackEvent;", "onBackStarted", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OnBackPressedDispatcher.kt */
public final class OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1 implements OnBackAnimationCallback {
    final /* synthetic */ Function0<Unit> $onBackCancelled;
    final /* synthetic */ Function0<Unit> $onBackInvoked;
    final /* synthetic */ Function1<BackEventCompat, Unit> $onBackProgressed;
    final /* synthetic */ Function1<BackEventCompat, Unit> $onBackStarted;

    public OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1(Function1<? super BackEventCompat, Unit> function1, Function1<? super BackEventCompat, Unit> function12, Function0<Unit> function0, Function0<Unit> function02) {
        this.$onBackStarted = (Function1<BackEventCompat, Unit>) function1;
        this.$onBackProgressed = (Function1<BackEventCompat, Unit>) function12;
        this.$onBackInvoked = function0;
        this.$onBackCancelled = function02;
    }

    public void onBackStarted(BackEvent backEvent) {
        Intrinsics.checkNotNullParameter(backEvent, "backEvent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            this.$onBackStarted.invoke(new BackEventCompat(backEvent));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void onBackProgressed(BackEvent backEvent) {
        Intrinsics.checkNotNullParameter(backEvent, "backEvent");
        this.$onBackProgressed.invoke(new BackEventCompat(backEvent));
    }

    public void onBackInvoked() {
        this.$onBackInvoked.invoke();
    }

    public void onBackCancelled() {
        this.$onBackCancelled.invoke();
    }
}
