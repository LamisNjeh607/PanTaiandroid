package io.ionic.libs.iongeolocationlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ8\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001d"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "", "timeout", "", "maximumAge", "enableHighAccuracy", "", "minUpdateInterval", "(JJZLjava/lang/Long;)V", "getEnableHighAccuracy", "()Z", "getMaximumAge", "()J", "getMinUpdateInterval", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTimeout", "component1", "component2", "component3", "component4", "copy", "(JJZLjava/lang/Long;)Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "equals", "other", "hashCode", "", "toString", "", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCLocationOptions.kt */
public final class IONGLOCLocationOptions {
    private final boolean enableHighAccuracy;
    private final long maximumAge;
    private final Long minUpdateInterval;
    private final long timeout;

    public static /* synthetic */ IONGLOCLocationOptions copy$default(IONGLOCLocationOptions iONGLOCLocationOptions, long j, long j2, boolean z, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            j = iONGLOCLocationOptions.timeout;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = iONGLOCLocationOptions.maximumAge;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            z = iONGLOCLocationOptions.enableHighAccuracy;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            l = iONGLOCLocationOptions.minUpdateInterval;
        }
        return iONGLOCLocationOptions.copy(j3, j4, z2, l);
    }

    public final long component1() {
        return this.timeout;
    }

    public final long component2() {
        return this.maximumAge;
    }

    public final boolean component3() {
        return this.enableHighAccuracy;
    }

    public final Long component4() {
        return this.minUpdateInterval;
    }

    public final IONGLOCLocationOptions copy(long j, long j2, boolean z, Long l) {
        return new IONGLOCLocationOptions(j, j2, z, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IONGLOCLocationOptions)) {
            return false;
        }
        IONGLOCLocationOptions iONGLOCLocationOptions = (IONGLOCLocationOptions) obj;
        return this.timeout == iONGLOCLocationOptions.timeout && this.maximumAge == iONGLOCLocationOptions.maximumAge && this.enableHighAccuracy == iONGLOCLocationOptions.enableHighAccuracy && Intrinsics.areEqual((Object) this.minUpdateInterval, (Object) iONGLOCLocationOptions.minUpdateInterval);
    }

    public int hashCode() {
        int hashCode = ((((Long.hashCode(this.timeout) * 31) + Long.hashCode(this.maximumAge)) * 31) + Boolean.hashCode(this.enableHighAccuracy)) * 31;
        Long l = this.minUpdateInterval;
        return hashCode + (l == null ? 0 : l.hashCode());
    }

    public String toString() {
        long j = this.timeout;
        long j2 = this.maximumAge;
        boolean z = this.enableHighAccuracy;
        return "IONGLOCLocationOptions(timeout=" + j + ", maximumAge=" + j2 + ", enableHighAccuracy=" + z + ", minUpdateInterval=" + this.minUpdateInterval + ")";
    }

    public IONGLOCLocationOptions(long j, long j2, boolean z, Long l) {
        this.timeout = j;
        this.maximumAge = j2;
        this.enableHighAccuracy = z;
        this.minUpdateInterval = l;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IONGLOCLocationOptions(long j, long j2, boolean z, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, z, (i & 8) != 0 ? null : l);
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final long getMaximumAge() {
        return this.maximumAge;
    }

    public final boolean getEnableHighAccuracy() {
        return this.enableHighAccuracy;
    }

    public final Long getMinUpdateInterval() {
        return this.minUpdateInterval;
    }
}
