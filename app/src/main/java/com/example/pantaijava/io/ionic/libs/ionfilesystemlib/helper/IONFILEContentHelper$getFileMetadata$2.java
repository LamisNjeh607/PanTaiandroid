package io.ionic.libs.ionfilesystemlib.helper;

import android.database.Cursor;
import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.helper.common.IONFILECommonKt;
import io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEFileType;
import io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEContentHelper$getFileMetadata$2", f = "IONFILEContentHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEContentHelper.kt */
final class IONFILEContentHelper$getFileMetadata$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends IONFILEMetadataResult>>, Object> {
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONFILEContentHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEContentHelper$getFileMetadata$2(IONFILEContentHelper iONFILEContentHelper, Uri uri, Continuation<? super IONFILEContentHelper$getFileMetadata$2> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEContentHelper;
        this.$uri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEContentHelper$getFileMetadata$2 iONFILEContentHelper$getFileMetadata$2 = new IONFILEContentHelper$getFileMetadata$2(this.this$0, this.$uri, continuation);
        iONFILEContentHelper$getFileMetadata$2.L$0 = obj;
        return iONFILEContentHelper$getFileMetadata$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<IONFILEMetadataResult>> continuation) {
        return ((IONFILEContentHelper$getFileMetadata$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Throwable th;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IONFILEContentHelper iONFILEContentHelper = this.this$0;
            Uri uri = this.$uri;
            try {
                Result.Companion companion = Result.Companion;
                Cursor query = iONFILEContentHelper.contentResolver.query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    Intrinsics.checkNotNull(query);
                    Closeable closeable = query;
                    try {
                        Cursor cursor = (Cursor) closeable;
                        if (query.moveToFirst()) {
                            iONFILEContentHelper.contentResolver.getPersistedUriPermissions();
                            String access$getNameForContentUri = iONFILEContentHelper.getNameForContentUri(query);
                            long access$getSizeForContentUri = iONFILEContentHelper.getSizeForContentUri(query, uri);
                            long access$getLastModifiedTimestampForContentUri = iONFILEContentHelper.getLastModifiedTimestampForContentUri(query);
                            long access$getCreatedTimestampForContentUri = iONFILEContentHelper.getCreatedTimestampForContentUri(query);
                            String type = iONFILEContentHelper.contentResolver.getType(uri);
                            if (type == null) {
                                type = IONFILECommonKt.FILE_MIME_TYPE_FALLBACK;
                            }
                            Intrinsics.checkNotNull(type);
                            String path = uri.getPath();
                            if (path == null) {
                                path = "";
                            }
                            Intrinsics.checkNotNull(path);
                            IONFILEMetadataResult iONFILEMetadataResult = new IONFILEMetadataResult(path, access$getNameForContentUri, uri, access$getSizeForContentUri, new IONFILEFileType.File(type), Boxing.boxLong(access$getCreatedTimestampForContentUri), access$getLastModifiedTimestampForContentUri);
                            CloseableKt.closeFinally(closeable, (Throwable) null);
                            obj2 = Result.m190constructorimpl(iONFILEMetadataResult);
                            return Result.m189boximpl(iONFILEContentHelper.m163mapErrorKWTtemM(obj2, this.$uri));
                        }
                        String uri2 = uri.toString();
                        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                        throw new IONFILEExceptions.DoesNotExist(uri2, (Throwable) null, 2, (DefaultConstructorMarker) null);
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        CloseableKt.closeFinally(closeable, th);
                        throw th3;
                    }
                } else {
                    throw new IONFILEExceptions.UnknownError((Throwable) null, 1, (DefaultConstructorMarker) null);
                }
            } catch (Throwable th4) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m190constructorimpl(ResultKt.createFailure(th4));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
