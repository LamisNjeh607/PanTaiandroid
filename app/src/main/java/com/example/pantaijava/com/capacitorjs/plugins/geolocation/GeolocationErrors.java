package com.capacitorjs.plugins.geolocation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006¨\u0006 "}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationErrors;", "", "()V", "GET_LOCATION_TIMEOUT", "Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "getGET_LOCATION_TIMEOUT", "()Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "GOOGLE_SERVICES_ERROR", "getGOOGLE_SERVICES_ERROR", "GOOGLE_SERVICES_RESOLVABLE", "getGOOGLE_SERVICES_RESOLVABLE", "INVALID_TIMEOUT", "getINVALID_TIMEOUT", "LOCATION_DISABLED", "getLOCATION_DISABLED", "LOCATION_ENABLE_REQUEST_DENIED", "getLOCATION_ENABLE_REQUEST_DENIED", "LOCATION_PERMISSIONS_DENIED", "getLOCATION_PERMISSIONS_DENIED", "LOCATION_SETTINGS_ERROR", "getLOCATION_SETTINGS_ERROR", "POSITION_UNAVAILABLE", "getPOSITION_UNAVAILABLE", "WATCH_ID_NOT_FOUND", "getWATCH_ID_NOT_FOUND", "WATCH_ID_NOT_PROVIDED", "getWATCH_ID_NOT_PROVIDED", "formatErrorCode", "", "number", "", "ErrorInfo", "capacitor-geolocation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GeolocationErrors.kt */
public final class GeolocationErrors {
    private static final ErrorInfo GET_LOCATION_TIMEOUT;
    private static final ErrorInfo GOOGLE_SERVICES_ERROR;
    private static final ErrorInfo GOOGLE_SERVICES_RESOLVABLE;
    public static final GeolocationErrors INSTANCE;
    private static final ErrorInfo INVALID_TIMEOUT;
    private static final ErrorInfo LOCATION_DISABLED;
    private static final ErrorInfo LOCATION_ENABLE_REQUEST_DENIED;
    private static final ErrorInfo LOCATION_PERMISSIONS_DENIED;
    private static final ErrorInfo LOCATION_SETTINGS_ERROR;
    private static final ErrorInfo POSITION_UNAVAILABLE;
    private static final ErrorInfo WATCH_ID_NOT_FOUND;
    private static final ErrorInfo WATCH_ID_NOT_PROVIDED;

    private GeolocationErrors() {
    }

    private final String formatErrorCode(int i) {
        return "OS-PLUG-GLOC-" + StringsKt.padStart(String.valueOf(i), 4, '0');
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "", "code", "", "message", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "capacitor-geolocation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: GeolocationErrors.kt */
    public static final class ErrorInfo {
        private final String code;
        private final String message;

        public static /* synthetic */ ErrorInfo copy$default(ErrorInfo errorInfo, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorInfo.code;
            }
            if ((i & 2) != 0) {
                str2 = errorInfo.message;
            }
            return errorInfo.copy(str, str2);
        }

        public final String component1() {
            return this.code;
        }

        public final String component2() {
            return this.message;
        }

        public final ErrorInfo copy(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "code");
            Intrinsics.checkNotNullParameter(str2, "message");
            return new ErrorInfo(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorInfo)) {
                return false;
            }
            ErrorInfo errorInfo = (ErrorInfo) obj;
            return Intrinsics.areEqual((Object) this.code, (Object) errorInfo.code) && Intrinsics.areEqual((Object) this.message, (Object) errorInfo.message);
        }

        public int hashCode() {
            return (this.code.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            String str = this.code;
            return "ErrorInfo(code=" + str + ", message=" + this.message + ")";
        }

        public ErrorInfo(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "code");
            Intrinsics.checkNotNullParameter(str2, "message");
            this.code = str;
            this.message = str2;
        }

        public final String getCode() {
            return this.code;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    static {
        GeolocationErrors geolocationErrors = new GeolocationErrors();
        INSTANCE = geolocationErrors;
        POSITION_UNAVAILABLE = new ErrorInfo(geolocationErrors.formatErrorCode(2), "There was en error trying to obtain the location.");
        LOCATION_PERMISSIONS_DENIED = new ErrorInfo(geolocationErrors.formatErrorCode(3), "Location permission request was denied.");
        LOCATION_DISABLED = new ErrorInfo(geolocationErrors.formatErrorCode(7), "Location services are not enabled.");
        LOCATION_ENABLE_REQUEST_DENIED = new ErrorInfo(geolocationErrors.formatErrorCode(9), "Request to enable location was denied.");
        GET_LOCATION_TIMEOUT = new ErrorInfo(geolocationErrors.formatErrorCode(10), "Could not obtain location in time. Try with a higher timeout.");
        INVALID_TIMEOUT = new ErrorInfo(geolocationErrors.formatErrorCode(11), "Timeout needs to be a positive value.");
        WATCH_ID_NOT_FOUND = new ErrorInfo(geolocationErrors.formatErrorCode(12), "WatchId not found.");
        WATCH_ID_NOT_PROVIDED = new ErrorInfo(geolocationErrors.formatErrorCode(13), "WatchId needs to be provided.");
        GOOGLE_SERVICES_RESOLVABLE = new ErrorInfo(geolocationErrors.formatErrorCode(14), "Google Play Services error user resolvable.");
        GOOGLE_SERVICES_ERROR = new ErrorInfo(geolocationErrors.formatErrorCode(15), "Google Play Services error.");
        LOCATION_SETTINGS_ERROR = new ErrorInfo(geolocationErrors.formatErrorCode(16), "Location settings error.");
    }

    public final ErrorInfo getPOSITION_UNAVAILABLE() {
        return POSITION_UNAVAILABLE;
    }

    public final ErrorInfo getLOCATION_PERMISSIONS_DENIED() {
        return LOCATION_PERMISSIONS_DENIED;
    }

    public final ErrorInfo getLOCATION_DISABLED() {
        return LOCATION_DISABLED;
    }

    public final ErrorInfo getLOCATION_ENABLE_REQUEST_DENIED() {
        return LOCATION_ENABLE_REQUEST_DENIED;
    }

    public final ErrorInfo getGET_LOCATION_TIMEOUT() {
        return GET_LOCATION_TIMEOUT;
    }

    public final ErrorInfo getINVALID_TIMEOUT() {
        return INVALID_TIMEOUT;
    }

    public final ErrorInfo getWATCH_ID_NOT_FOUND() {
        return WATCH_ID_NOT_FOUND;
    }

    public final ErrorInfo getWATCH_ID_NOT_PROVIDED() {
        return WATCH_ID_NOT_PROVIDED;
    }

    public final ErrorInfo getGOOGLE_SERVICES_RESOLVABLE() {
        return GOOGLE_SERVICES_RESOLVABLE;
    }

    public final ErrorInfo getGOOGLE_SERVICES_ERROR() {
        return GOOGLE_SERVICES_ERROR;
    }

    public final ErrorInfo getLOCATION_SETTINGS_ERROR() {
        return LOCATION_SETTINGS_ERROR;
    }
}
