package io.ionic.libs.ionfilesystemlib.helper;

import android.net.Uri;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved$Local;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "io.ionic.libs.ionfilesystemlib.helper.IONFILEUriHelper$resolveAsLocalFile$2", f = "IONFILEUriHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IONFILEUriHelper.kt */
final class IONFILEUriHelper$resolveAsLocalFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IONFILEUri.Resolved.Local>, Object> {
    final /* synthetic */ Boolean $assumeExternalStorage;
    final /* synthetic */ String $localPath;
    final /* synthetic */ File $parentFolderFileObject;
    int label;
    final /* synthetic */ IONFILEUriHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IONFILEUriHelper$resolveAsLocalFile$2(IONFILEUriHelper iONFILEUriHelper, File file, String str, Boolean bool, Continuation<? super IONFILEUriHelper$resolveAsLocalFile$2> continuation) {
        super(2, continuation);
        this.this$0 = iONFILEUriHelper;
        this.$parentFolderFileObject = file;
        this.$localPath = str;
        this.$assumeExternalStorage = bool;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IONFILEUriHelper$resolveAsLocalFile$2(this.this$0, this.$parentFolderFileObject, this.$localPath, this.$assumeExternalStorage, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IONFILEUri.Resolved.Local> continuation) {
        return ((IONFILEUriHelper$resolveAsLocalFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            File access$getLocalFileObject = this.this$0.getLocalFileObject(this.$parentFolderFileObject, this.$localPath);
            Uri fromFile = Uri.fromFile(access$getLocalFileObject);
            Boolean bool = this.$assumeExternalStorage;
            if (bool != null) {
                z = bool.booleanValue();
            } else {
                IONFILEUriHelper iONFILEUriHelper = this.this$0;
                String absolutePath = access$getLocalFileObject.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                z = iONFILEUriHelper.isInExternalStorage(absolutePath);
            }
            String path = access$getLocalFileObject.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            String str2 = File.separator;
            Intrinsics.checkNotNullExpressionValue(str2, "separator");
            if ((StringsKt.endsWith$default(path, str2, false, 2, (Object) null) || !access$getLocalFileObject.isDirectory()) && (access$getLocalFileObject.exists() || !StringsKt.isBlank(FilesKt.getExtension(access$getLocalFileObject)))) {
                str = "";
            } else {
                str = File.separator;
                Intrinsics.checkNotNullExpressionValue(str, "separator");
            }
            String str3 = access$getLocalFileObject.getPath() + str;
            Uri build = fromFile.buildUpon().path(fromFile.getPath() + str).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return new IONFILEUri.Resolved.Local(str3, build, this.this$0.getLocalUriType(access$getLocalFileObject), z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
