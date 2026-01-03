package io.ionic.libs.ionfilesystemlib.helper;

import io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEMetadataResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$2", f = "IONFILEDirectoriesHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEDirectoriesHelper.kt */
final class IONFILEDirectoriesHelper$listDirectory$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends IONFILEMetadataResult>>>, Object> {
    final /* synthetic */ String $fullPath;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEDirectoriesHelper$listDirectory$2(String str, Continuation<? super IONFILEDirectoriesHelper$listDirectory$2> continuation) {
        super(2, continuation);
        this.$fullPath = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONFILEDirectoriesHelper$listDirectory$2 iONFILEDirectoriesHelper$listDirectory$2 = new IONFILEDirectoriesHelper$listDirectory$2(this.$fullPath, continuation);
        iONFILEDirectoriesHelper$listDirectory$2.L$0 = obj;
        return iONFILEDirectoriesHelper$listDirectory$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends List<IONFILEMetadataResult>>> continuation) {
        return ((IONFILEDirectoriesHelper$listDirectory$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r4 == null) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r3.label
            if (r0 != 0) goto L_0x0082
            kotlin.ResultKt.throwOnFailure(r4)
            java.lang.Object r4 = r3.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            java.lang.String r4 = r3.$fullPath
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0072 }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0072 }
            r0.<init>(r4)     // Catch:{ all -> 0x0072 }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x006a
            boolean r1 = r0.isDirectory()     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x006a
            java.io.File[] r4 = r0.listFiles()     // Catch:{ all -> 0x0072 }
            if (r4 == 0) goto L_0x0032
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0072 }
            java.util.List r4 = kotlin.collections.ArraysKt.toList((T[]) r4)     // Catch:{ all -> 0x0072 }
            if (r4 != 0) goto L_0x0036
        L_0x0032:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x0072 }
        L_0x0036:
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x0072 }
            java.util.List r4 = kotlin.collections.CollectionsKt.filterNotNull(r4)     // Catch:{ all -> 0x0072 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x0072 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0072 }
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r1)     // Catch:{ all -> 0x0072 }
            r0.<init>(r1)     // Catch:{ all -> 0x0072 }
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ all -> 0x0072 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0072 }
        L_0x004f:
            boolean r1 = r4.hasNext()     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x0063
            java.lang.Object r1 = r4.next()     // Catch:{ all -> 0x0072 }
            java.io.File r1 = (java.io.File) r1     // Catch:{ all -> 0x0072 }
            io.ionic.libs.ionfilesystemlib.model.IONFILEMetadataResult r1 = io.ionic.libs.ionfilesystemlib.helper.common.IONFILECommonKt.getMetadata(r1)     // Catch:{ all -> 0x0072 }
            r0.add(r1)     // Catch:{ all -> 0x0072 }
            goto L_0x004f
        L_0x0063:
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0072 }
            java.lang.Object r4 = kotlin.Result.m190constructorimpl(r0)     // Catch:{ all -> 0x0072 }
            goto L_0x007d
        L_0x006a:
            io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist r0 = new io.ionic.libs.ionfilesystemlib.model.IONFILEExceptions$DoesNotExist     // Catch:{ all -> 0x0072 }
            r1 = 2
            r2 = 0
            r0.<init>(r4, r2, r1, r2)     // Catch:{ all -> 0x0072 }
            throw r0     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r4 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m190constructorimpl(r4)
        L_0x007d:
            kotlin.Result r4 = kotlin.Result.m189boximpl(r4)
            return r4
        L_0x0082:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.ionfilesystemlib.helper.IONFILEDirectoriesHelper$listDirectory$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
