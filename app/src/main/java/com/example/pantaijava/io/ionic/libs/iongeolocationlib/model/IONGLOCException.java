package io.ionic.libs.iongeolocationlib.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0005\b\t\n\u000b\fB\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "IONGLOCGoogleServicesException", "IONGLOCInvalidTimeoutException", "IONGLOCLocationRetrievalTimeoutException", "IONGLOCRequestDeniedException", "IONGLOCSettingsException", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCGoogleServicesException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCInvalidTimeoutException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCLocationRetrievalTimeoutException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCRequestDeniedException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCSettingsException;", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONGLOCException.kt */
public abstract class IONGLOCException extends Exception {
    public /* synthetic */ IONGLOCException(String str, Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, th);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCSettingsException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCException.kt */
    public static final class IONGLOCSettingsException extends IONGLOCException {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IONGLOCSettingsException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IONGLOCSettingsException(String str, Throwable th) {
            super(str, th, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }

    private IONGLOCException(String str, Throwable th) {
        super(str, th);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCRequestDeniedException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCException.kt */
    public static final class IONGLOCRequestDeniedException extends IONGLOCException {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IONGLOCRequestDeniedException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IONGLOCRequestDeniedException(String str, Throwable th) {
            super(str, th, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCGoogleServicesException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "resolvable", "", "message", "", "cause", "", "(ZLjava/lang/String;Ljava/lang/Throwable;)V", "getResolvable", "()Z", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCException.kt */
    public static final class IONGLOCGoogleServicesException extends IONGLOCException {
        private final boolean resolvable;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IONGLOCGoogleServicesException(boolean z, String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, (i & 4) != 0 ? null : th);
        }

        public final boolean getResolvable() {
            return this.resolvable;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IONGLOCGoogleServicesException(boolean z, String str, Throwable th) {
            super(str, th, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
            this.resolvable = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCInvalidTimeoutException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCException.kt */
    public static final class IONGLOCInvalidTimeoutException extends IONGLOCException {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IONGLOCInvalidTimeoutException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IONGLOCInvalidTimeoutException(String str, Throwable th) {
            super(str, th, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCException$IONGLOCLocationRetrievalTimeoutException;", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IONGLOCException.kt */
    public static final class IONGLOCLocationRetrievalTimeoutException extends IONGLOCException {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IONGLOCLocationRetrievalTimeoutException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IONGLOCLocationRetrievalTimeoutException(String str, Throwable th) {
            super(str, th, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }
}
