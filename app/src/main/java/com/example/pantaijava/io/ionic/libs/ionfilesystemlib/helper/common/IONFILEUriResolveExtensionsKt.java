package io.ionic.libs.ionfilesystemlib.helper.common;

import io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import io.ionic.libs.ionfilesystemlib.model.LocalUriType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007HH¢\u0006\u0002\u0010\t\u001aB\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007HH¢\u0006\u0002\u0010\t\u001aB\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007HH¢\u0006\u0002\u0010\t\u001aB\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007HH¢\u0006\u0002\u0010\t\u001aB\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007HH¢\u0006\u0002\u0010\t¨\u0006\u000f"}, d2 = {"useUriIfResolved", "Lkotlin/Result;", "T", "Lio/ionic/libs/ionfilesystemlib/helper/IONFILEUriHelper;", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;", "onResolved", "Lkotlin/Function1;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "(Lio/ionic/libs/ionfilesystemlib/helper/IONFILEUriHelper;Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "useUriIfResolvedAsLocal", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Local;", "useUriIfResolvedAsLocalDirectory", "useUriIfResolvedAsLocalFile", "useUriIfResolvedAsNonDirectory", "IONFilesystemLib_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: IONFILEUriResolveExtensions.kt */
public final class IONFILEUriResolveExtensionsKt {
    public static final /* synthetic */ <T> Object useUriIfResolved(IONFILEUriHelper iONFILEUriHelper, IONFILEUri iONFILEUri, Function1<? super IONFILEUri.Resolved, ? extends Result<? extends T>> function1, Continuation<? super Result<? extends T>> continuation) {
        IONFILEUri.Resolved resolved;
        if (iONFILEUri instanceof IONFILEUri.Resolved) {
            resolved = (IONFILEUri.Resolved) iONFILEUri;
        } else if (iONFILEUri instanceof IONFILEUri.Unresolved) {
            InlineMarker.mark(0);
            Object r1 = iONFILEUriHelper.m180resolveUrigIAlus((IONFILEUri.Unresolved) iONFILEUri, continuation);
            InlineMarker.mark(1);
            InlineMarker.mark(8);
            Object r12 = ((Result) r1).m199unboximpl();
            Object obj = r12;
            InlineMarker.mark(9);
            Object obj2 = r12;
            Throwable r2 = Result.m193exceptionOrNullimpl(r12);
            if (r2 == null) {
                resolved = (IONFILEUri.Resolved) r12;
            } else {
                Throwable th = r2;
                Result.Companion companion = Result.Companion;
                return Result.m190constructorimpl(ResultKt.createFailure(r2));
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return ((Result) function1.invoke(resolved)).m199unboximpl();
    }

    public static final /* synthetic */ <T> Object useUriIfResolvedAsLocalDirectory(IONFILEUriHelper iONFILEUriHelper, IONFILEUri iONFILEUri, Function1<? super IONFILEUri.Resolved.Local, ? extends Result<? extends T>> function1, Continuation<? super Result<? extends T>> continuation) {
        Object obj;
        IONFILEUri.Resolved resolved;
        Object obj2;
        Object obj3;
        if (iONFILEUri instanceof IONFILEUri.Resolved) {
            resolved = (IONFILEUri.Resolved) iONFILEUri;
        } else if (iONFILEUri instanceof IONFILEUri.Unresolved) {
            InlineMarker.mark(0);
            Object r1 = iONFILEUriHelper.m180resolveUrigIAlus((IONFILEUri.Unresolved) iONFILEUri, continuation);
            InlineMarker.mark(1);
            InlineMarker.mark(8);
            Object r12 = ((Result) r1).m199unboximpl();
            Object obj4 = r12;
            InlineMarker.mark(9);
            Object obj5 = r12;
            Throwable r2 = Result.m193exceptionOrNullimpl(r12);
            if (r2 == null) {
                resolved = r12;
            } else {
                Throwable th = r2;
                Result.Companion companion = Result.Companion;
                obj = Result.m190constructorimpl(ResultKt.createFailure(r2));
                Object obj6 = obj;
                Object obj7 = obj;
                return obj;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        IONFILEUri.Resolved resolved2 = resolved;
        if (resolved instanceof IONFILEUri.Resolved.Local) {
            IONFILEUri.Resolved.Local local = (IONFILEUri.Resolved.Local) resolved;
            if (local.getType() != LocalUriType.FILE) {
                obj3 = ((Result) function1.invoke(local)).m199unboximpl();
            } else {
                Result.Companion companion2 = Result.Companion;
                obj3 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForFiles()));
            }
            Result r13 = Result.m189boximpl(obj3);
            Result result = r13;
            obj2 = r13.m199unboximpl();
        } else {
            Result.Companion companion3 = Result.Companion;
            obj2 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForContentScheme()));
        }
        Result r14 = Result.m189boximpl(obj2);
        Result result2 = r14;
        obj = r14.m199unboximpl();
        Object obj62 = obj;
        Object obj72 = obj;
        return obj;
    }

    public static final /* synthetic */ <T> Object useUriIfResolvedAsLocalFile(IONFILEUriHelper iONFILEUriHelper, IONFILEUri iONFILEUri, Function1<? super IONFILEUri.Resolved.Local, ? extends Result<? extends T>> function1, Continuation<? super Result<? extends T>> continuation) {
        Object obj;
        IONFILEUri.Resolved resolved;
        Object obj2;
        Object obj3;
        if (iONFILEUri instanceof IONFILEUri.Resolved) {
            resolved = (IONFILEUri.Resolved) iONFILEUri;
        } else if (iONFILEUri instanceof IONFILEUri.Unresolved) {
            InlineMarker.mark(0);
            Object r1 = iONFILEUriHelper.m180resolveUrigIAlus((IONFILEUri.Unresolved) iONFILEUri, continuation);
            InlineMarker.mark(1);
            InlineMarker.mark(8);
            Object r12 = ((Result) r1).m199unboximpl();
            Object obj4 = r12;
            InlineMarker.mark(9);
            Object obj5 = r12;
            Throwable r2 = Result.m193exceptionOrNullimpl(r12);
            if (r2 == null) {
                resolved = r12;
            } else {
                Throwable th = r2;
                Result.Companion companion = Result.Companion;
                obj = Result.m190constructorimpl(ResultKt.createFailure(r2));
                Object obj6 = obj;
                Object obj7 = obj;
                return obj;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        IONFILEUri.Resolved resolved2 = resolved;
        if (resolved instanceof IONFILEUri.Resolved.Local) {
            IONFILEUri.Resolved.Local local = (IONFILEUri.Resolved.Local) resolved;
            if (local.getType() != LocalUriType.DIRECTORY) {
                obj3 = ((Result) function1.invoke(local)).m199unboximpl();
            } else {
                Result.Companion companion2 = Result.Companion;
                obj3 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForDirectory()));
            }
            Result r13 = Result.m189boximpl(obj3);
            Result result = r13;
            obj2 = r13.m199unboximpl();
        } else {
            Result.Companion companion3 = Result.Companion;
            obj2 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForContentScheme()));
        }
        Result r14 = Result.m189boximpl(obj2);
        Result result2 = r14;
        obj = r14.m199unboximpl();
        Object obj62 = obj;
        Object obj72 = obj;
        return obj;
    }

    public static final /* synthetic */ <T> Object useUriIfResolvedAsLocal(IONFILEUriHelper iONFILEUriHelper, IONFILEUri iONFILEUri, Function1<? super IONFILEUri.Resolved.Local, ? extends Result<? extends T>> function1, Continuation<? super Result<? extends T>> continuation) {
        Object obj;
        IONFILEUri.Resolved resolved;
        Object obj2;
        if (iONFILEUri instanceof IONFILEUri.Resolved) {
            resolved = (IONFILEUri.Resolved) iONFILEUri;
        } else if (iONFILEUri instanceof IONFILEUri.Unresolved) {
            InlineMarker.mark(0);
            Object r1 = iONFILEUriHelper.m180resolveUrigIAlus((IONFILEUri.Unresolved) iONFILEUri, continuation);
            InlineMarker.mark(1);
            InlineMarker.mark(8);
            Object r12 = ((Result) r1).m199unboximpl();
            Object obj3 = r12;
            InlineMarker.mark(9);
            Object obj4 = r12;
            Throwable r2 = Result.m193exceptionOrNullimpl(r12);
            if (r2 == null) {
                resolved = (IONFILEUri.Resolved) r12;
            } else {
                Throwable th = r2;
                Result.Companion companion = Result.Companion;
                obj = Result.m190constructorimpl(ResultKt.createFailure(r2));
                Object obj5 = obj;
                return obj;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        IONFILEUri.Resolved resolved2 = resolved;
        if (resolved instanceof IONFILEUri.Resolved.Local) {
            obj2 = ((Result) function1.invoke(resolved)).m199unboximpl();
        } else {
            Result.Companion companion2 = Result.Companion;
            obj2 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForContentScheme()));
        }
        Result r13 = Result.m189boximpl(obj2);
        Result result = r13;
        obj = r13.m199unboximpl();
        Object obj52 = obj;
        return obj;
    }

    public static final /* synthetic */ <T> Object useUriIfResolvedAsNonDirectory(IONFILEUriHelper iONFILEUriHelper, IONFILEUri iONFILEUri, Function1<? super IONFILEUri.Resolved, ? extends Result<? extends T>> function1, Continuation<? super Result<? extends T>> continuation) {
        Object obj;
        IONFILEUri.Resolved resolved;
        Object obj2;
        if (iONFILEUri instanceof IONFILEUri.Resolved) {
            resolved = (IONFILEUri.Resolved) iONFILEUri;
        } else if (iONFILEUri instanceof IONFILEUri.Unresolved) {
            InlineMarker.mark(0);
            Object r1 = iONFILEUriHelper.m180resolveUrigIAlus((IONFILEUri.Unresolved) iONFILEUri, continuation);
            InlineMarker.mark(1);
            InlineMarker.mark(8);
            Object r12 = ((Result) r1).m199unboximpl();
            Object obj3 = r12;
            InlineMarker.mark(9);
            Object obj4 = r12;
            Throwable r2 = Result.m193exceptionOrNullimpl(r12);
            if (r2 == null) {
                resolved = (IONFILEUri.Resolved) r12;
            } else {
                Throwable th = r2;
                Result.Companion companion = Result.Companion;
                obj = Result.m190constructorimpl(ResultKt.createFailure(r2));
                Object obj5 = obj;
                return obj;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        IONFILEUri.Resolved resolved2 = resolved;
        if (!(resolved instanceof IONFILEUri.Resolved.Local) || ((IONFILEUri.Resolved.Local) resolved).getType() != LocalUriType.DIRECTORY) {
            obj2 = ((Result) function1.invoke(resolved)).m199unboximpl();
        } else {
            Result.Companion companion2 = Result.Companion;
            obj2 = Result.m190constructorimpl(ResultKt.createFailure(new IONFILEExceptions.NotSupportedForDirectory()));
        }
        Result r13 = Result.m189boximpl(obj2);
        Result result = r13;
        obj = r13.m199unboximpl();
        Object obj52 = obj;
        return obj;
    }
}
