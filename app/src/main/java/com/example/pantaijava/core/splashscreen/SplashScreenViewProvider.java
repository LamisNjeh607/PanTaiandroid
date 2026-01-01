package androidx.core.splashscreen;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.window.SplashScreenView;
import androidx.core.splashscreen.ThemeUtils;
import java.time.Duration;
import java.time.Instant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B\u0017\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0016\u001a\u00020\u0017R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u001a"}, d2 = {"Landroidx/core/splashscreen/SplashScreenViewProvider;", "", "platformView", "Landroid/window/SplashScreenView;", "ctx", "Landroid/app/Activity;", "(Landroid/window/SplashScreenView;Landroid/app/Activity;)V", "(Landroid/app/Activity;)V", "iconAnimationDurationMillis", "", "getIconAnimationDurationMillis", "()J", "iconAnimationStartMillis", "getIconAnimationStartMillis", "iconView", "Landroid/view/View;", "getIconView", "()Landroid/view/View;", "impl", "Landroidx/core/splashscreen/SplashScreenViewProvider$ViewImpl;", "view", "getView", "remove", "", "ViewImpl", "ViewImpl31", "core-splashscreen_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: SplashScreenViewProvider.kt */
public final class SplashScreenViewProvider {
    private final ViewImpl impl;

    public SplashScreenViewProvider(Activity activity) {
        ViewImpl viewImpl;
        Intrinsics.checkNotNullParameter(activity, "ctx");
        if (Build.VERSION.SDK_INT >= 31) {
            viewImpl = new ViewImpl31(activity);
        } else {
            viewImpl = new ViewImpl(activity);
        }
        viewImpl.createSplashScreenView();
        this.impl = viewImpl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SplashScreenViewProvider(SplashScreenView splashScreenView, Activity activity) {
        this(activity);
        Intrinsics.checkNotNullParameter(splashScreenView, "platformView");
        Intrinsics.checkNotNullParameter(activity, "ctx");
        ((ViewImpl31) this.impl).setPlatformView(splashScreenView);
    }

    public final View getView() {
        return this.impl.getSplashScreenView();
    }

    public final View getIconView() {
        return this.impl.getIconView();
    }

    public final long getIconAnimationStartMillis() {
        return this.impl.getIconAnimationStartMillis();
    }

    public final long getIconAnimationDurationMillis() {
        return this.impl.getIconAnimationDurationMillis();
    }

    public final void remove() {
        this.impl.remove();
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/core/splashscreen/SplashScreenViewProvider$ViewImpl;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "_splashScreenView", "Landroid/view/ViewGroup;", "get_splashScreenView", "()Landroid/view/ViewGroup;", "_splashScreenView$delegate", "Lkotlin/Lazy;", "getActivity", "()Landroid/app/Activity;", "iconAnimationDurationMillis", "", "getIconAnimationDurationMillis", "()J", "iconAnimationStartMillis", "getIconAnimationStartMillis", "iconView", "Landroid/view/View;", "getIconView", "()Landroid/view/View;", "splashScreenView", "getSplashScreenView", "createSplashScreenView", "", "remove", "core-splashscreen_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: SplashScreenViewProvider.kt */
    private static class ViewImpl {
        private final Lazy _splashScreenView$delegate = LazyKt.lazy(new SplashScreenViewProvider$ViewImpl$_splashScreenView$2(this));
        private final Activity activity;

        public long getIconAnimationDurationMillis() {
            return 0;
        }

        public long getIconAnimationStartMillis() {
            return 0;
        }

        public ViewImpl(Activity activity2) {
            Intrinsics.checkNotNullParameter(activity2, "activity");
            this.activity = activity2;
        }

        public final Activity getActivity() {
            return this.activity;
        }

        private final ViewGroup get_splashScreenView() {
            return (ViewGroup) this._splashScreenView$delegate.getValue();
        }

        public void createSplashScreenView() {
            View rootView = ((ViewGroup) this.activity.findViewById(16908290)).getRootView();
            ViewGroup viewGroup = rootView instanceof ViewGroup ? (ViewGroup) rootView : null;
            if (viewGroup != null) {
                viewGroup.addView(get_splashScreenView());
            }
        }

        public ViewGroup getSplashScreenView() {
            return get_splashScreenView();
        }

        public View getIconView() {
            View findViewById = getSplashScreenView().findViewById(R.id.splashscreen_icon_view);
            Intrinsics.checkNotNullExpressionValue(findViewById, "splashScreenView.findVie…d.splashscreen_icon_view)");
            return findViewById;
        }

        public void remove() {
            ViewParent parent = getSplashScreenView().getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(getSplashScreenView());
            }
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u001a"}, d2 = {"Landroidx/core/splashscreen/SplashScreenViewProvider$ViewImpl31;", "Landroidx/core/splashscreen/SplashScreenViewProvider$ViewImpl;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "iconAnimationDurationMillis", "", "getIconAnimationDurationMillis", "()J", "iconAnimationStartMillis", "getIconAnimationStartMillis", "iconView", "Landroid/view/View;", "getIconView", "()Landroid/view/View;", "platformView", "Landroid/window/SplashScreenView;", "getPlatformView", "()Landroid/window/SplashScreenView;", "setPlatformView", "(Landroid/window/SplashScreenView;)V", "splashScreenView", "getSplashScreenView", "createSplashScreenView", "", "remove", "core-splashscreen_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: SplashScreenViewProvider.kt */
    private static final class ViewImpl31 extends ViewImpl {
        public SplashScreenView platformView;

        public void createSplashScreenView() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewImpl31(Activity activity) {
            super(activity);
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public final SplashScreenView getPlatformView() {
            SplashScreenView splashScreenView = this.platformView;
            if (splashScreenView != null) {
                return splashScreenView;
            }
            Intrinsics.throwUninitializedPropertyAccessException("platformView");
            return null;
        }

        public final void setPlatformView(SplashScreenView splashScreenView) {
            Intrinsics.checkNotNullParameter(splashScreenView, "<set-?>");
            this.platformView = splashScreenView;
        }

        public SplashScreenView getSplashScreenView() {
            return getPlatformView();
        }

        public View getIconView() {
            View iconView = getPlatformView().getIconView();
            Intrinsics.checkNotNull(iconView);
            return iconView;
        }

        public long getIconAnimationStartMillis() {
            Instant iconAnimationStart = getPlatformView().getIconAnimationStart();
            if (iconAnimationStart != null) {
                return iconAnimationStart.toEpochMilli();
            }
            return 0;
        }

        public long getIconAnimationDurationMillis() {
            Duration iconAnimationDuration = getPlatformView().getIconAnimationDuration();
            if (iconAnimationDuration != null) {
                return iconAnimationDuration.toMillis();
            }
            return 0;
        }

        public void remove() {
            getPlatformView().remove();
            Resources.Theme theme = getActivity().getTheme();
            Intrinsics.checkNotNullExpressionValue(theme, "activity.theme");
            View decorView = getActivity().getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "activity.window.decorView");
            ThemeUtils.Api31.applyThemesSystemBarAppearance$default(theme, decorView, (TypedValue) null, 4, (Object) null);
        }
    }
}
