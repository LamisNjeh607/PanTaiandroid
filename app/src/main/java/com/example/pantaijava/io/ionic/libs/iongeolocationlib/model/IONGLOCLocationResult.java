package io.ionic.libs.iongeolocationlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J`\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006,"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "", "latitude", "", "longitude", "altitude", "accuracy", "", "altitudeAccuracy", "heading", "speed", "timestamp", "", "(DDDFLjava/lang/Float;FFJ)V", "getAccuracy", "()F", "getAltitude", "()D", "getAltitudeAccuracy", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getHeading", "getLatitude", "getLongitude", "getSpeed", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(DDDFLjava/lang/Float;FFJ)Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "equals", "", "other", "hashCode", "", "toString", "", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCLocationResult.kt */
public final class IONGLOCLocationResult {
    private final float accuracy;
    private final double altitude;
    private final Float altitudeAccuracy;
    private final float heading;
    private final double latitude;
    private final double longitude;
    private final float speed;
    private final long timestamp;

    public static /* synthetic */ IONGLOCLocationResult copy$default(IONGLOCLocationResult iONGLOCLocationResult, double d, double d2, double d3, float f, Float f2, float f3, float f4, long j, int i, Object obj) {
        IONGLOCLocationResult iONGLOCLocationResult2 = iONGLOCLocationResult;
        int i2 = i;
        return iONGLOCLocationResult.copy((i2 & 1) != 0 ? iONGLOCLocationResult2.latitude : d, (i2 & 2) != 0 ? iONGLOCLocationResult2.longitude : d2, (i2 & 4) != 0 ? iONGLOCLocationResult2.altitude : d3, (i2 & 8) != 0 ? iONGLOCLocationResult2.accuracy : f, (i2 & 16) != 0 ? iONGLOCLocationResult2.altitudeAccuracy : f2, (i2 & 32) != 0 ? iONGLOCLocationResult2.heading : f3, (i2 & 64) != 0 ? iONGLOCLocationResult2.speed : f4, (i2 & 128) != 0 ? iONGLOCLocationResult2.timestamp : j);
    }

    public final double component1() {
        return this.latitude;
    }

    public final double component2() {
        return this.longitude;
    }

    public final double component3() {
        return this.altitude;
    }

    public final float component4() {
        return this.accuracy;
    }

    public final Float component5() {
        return this.altitudeAccuracy;
    }

    public final float component6() {
        return this.heading;
    }

    public final float component7() {
        return this.speed;
    }

    public final long component8() {
        return this.timestamp;
    }

    public final IONGLOCLocationResult copy(double d, double d2, double d3, float f, Float f2, float f3, float f4, long j) {
        return new IONGLOCLocationResult(d, d2, d3, f, f2, f3, f4, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IONGLOCLocationResult)) {
            return false;
        }
        IONGLOCLocationResult iONGLOCLocationResult = (IONGLOCLocationResult) obj;
        return Double.compare(this.latitude, iONGLOCLocationResult.latitude) == 0 && Double.compare(this.longitude, iONGLOCLocationResult.longitude) == 0 && Double.compare(this.altitude, iONGLOCLocationResult.altitude) == 0 && Float.compare(this.accuracy, iONGLOCLocationResult.accuracy) == 0 && Intrinsics.areEqual((Object) this.altitudeAccuracy, (Object) iONGLOCLocationResult.altitudeAccuracy) && Float.compare(this.heading, iONGLOCLocationResult.heading) == 0 && Float.compare(this.speed, iONGLOCLocationResult.speed) == 0 && this.timestamp == iONGLOCLocationResult.timestamp;
    }

    public int hashCode() {
        int hashCode = ((((((Double.hashCode(this.latitude) * 31) + Double.hashCode(this.longitude)) * 31) + Double.hashCode(this.altitude)) * 31) + Float.hashCode(this.accuracy)) * 31;
        Float f = this.altitudeAccuracy;
        return ((((((hashCode + (f == null ? 0 : f.hashCode())) * 31) + Float.hashCode(this.heading)) * 31) + Float.hashCode(this.speed)) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        double d = this.latitude;
        double d2 = this.longitude;
        double d3 = this.altitude;
        float f = this.accuracy;
        Float f2 = this.altitudeAccuracy;
        float f3 = this.heading;
        float f4 = this.speed;
        return "IONGLOCLocationResult(latitude=" + d + ", longitude=" + d2 + ", altitude=" + d3 + ", accuracy=" + f + ", altitudeAccuracy=" + f2 + ", heading=" + f3 + ", speed=" + f4 + ", timestamp=" + this.timestamp + ")";
    }

    public IONGLOCLocationResult(double d, double d2, double d3, float f, Float f2, float f3, float f4, long j) {
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.accuracy = f;
        this.altitudeAccuracy = f2;
        this.heading = f3;
        this.speed = f4;
        this.timestamp = j;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IONGLOCLocationResult(double d, double d2, double d3, float f, Float f2, float f3, float f4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, d2, d3, f, (i & 16) != 0 ? null : f2, f3, f4, j);
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final double getAltitude() {
        return this.altitude;
    }

    public final float getAccuracy() {
        return this.accuracy;
    }

    public final Float getAltitudeAccuracy() {
        return this.altitudeAccuracy;
    }

    public final float getHeading() {
        return this.heading;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
