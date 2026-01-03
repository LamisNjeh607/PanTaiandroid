package com.capacitorjs.plugins.filesystem;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.android.gms.common.internal.ImagesContract;
import io.ionic.libs.ionfilesystemlib.IONFILEController;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.json.JSONException;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0003J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007JP\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020\u001321\u0010)\u001a-\b\u0001\u0012\u0013\u0012\u00110+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110/\u0012\u0006\u0012\u0004\u0018\u0001000*H\u0002¢\u0006\u0002\u00101Jm\u0010&\u001a\u00020\u00112\u0006\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020\u00132F\u0010)\u001aB\b\u0001\u0012\u0013\u0012\u00110+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(6\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110/\u0012\u0006\u0012\u0004\u0018\u00010004H\u0002¢\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u00109\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/FilesystemPlugin;", "Lcom/getcapacitor/Plugin;", "()V", "controller", "Lio/ionic/libs/ionfilesystemlib/IONFILEController;", "getController", "()Lio/ionic/libs/ionfilesystemlib/IONFILEController;", "controller$delegate", "Lkotlin/Lazy;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "coroutineScope$delegate", "legacyImplementation", "Lcom/capacitorjs/plugins/filesystem/LegacyFilesystemImplementation;", "appendFile", "", "call", "Lcom/getcapacitor/PluginCall;", "checkPermissions", "copy", "deleteFile", "downloadFile", "getUri", "handleOnDestroy", "isStoragePermissionGranted", "", "shouldRequestAboveAndroid10", "load", "mkdir", "permissionCallback", "readFile", "readFileInChunks", "readdir", "rename", "requestPermissions", "rmdir", "runWithPermission", "uri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "onPermissionGranted", "Lkotlin/Function2;", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Resolved;", "Lkotlin/ParameterName;", "name", "resolvedUri", "Lkotlin/coroutines/Continuation;", "", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lcom/getcapacitor/PluginCall;Lkotlin/jvm/functions/Function2;)V", "fromUri", "toUri", "Lkotlin/Function3;", "resolvedSourceUri", "resolvedDestinationUri", "(Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;Lcom/getcapacitor/PluginCall;Lkotlin/jvm/functions/Function3;)V", "stat", "writeFile", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@CapacitorPlugin(name = "Filesystem", permissions = {@Permission(alias = "publicStorage", strings = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}), @Permission(alias = "publicStorageAboveAPI29", strings = {"android.permission.READ_EXTERNAL_STORAGE"})})
/* compiled from: FilesystemPlugin.kt */
public final class FilesystemPlugin extends Plugin {
    private final Lazy controller$delegate = LazyKt.lazy(new FilesystemPlugin$controller$2(this));
    private final Lazy coroutineScope$delegate = LazyKt.lazy(FilesystemPlugin$coroutineScope$2.INSTANCE);
    /* access modifiers changed from: private */
    public LegacyFilesystemImplementation legacyImplementation;

    /* access modifiers changed from: private */
    public final CoroutineScope getCoroutineScope() {
        return (CoroutineScope) this.coroutineScope$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final IONFILEController getController() {
        return (IONFILEController) this.controller$delegate.getValue();
    }

    public void load() {
        super.load();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.legacyImplementation = new LegacyFilesystemImplementation(context);
    }

    /* access modifiers changed from: protected */
    public void handleOnDestroy() {
        super.handleOnDestroy();
        CoroutineScopeKt.cancel$default(getCoroutineScope(), (CancellationException) null, 1, (Object) null);
    }

    @PluginMethod
    public final void readFile(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        ReadFileOptions readFileOptions = FilesystemMethodOptionsKt.getReadFileOptions(pluginCall);
        if (readFileOptions == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(readFileOptions.getUri(), pluginCall, new FilesystemPlugin$readFile$1(this, readFileOptions, pluginCall, (Continuation<? super FilesystemPlugin$readFile$1>) null));
    }

    @PluginMethod(returnType = "callback")
    public final void readFileInChunks(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        ReadFileInChunksOptions readFileInChunksOptions = FilesystemMethodOptionsKt.getReadFileInChunksOptions(pluginCall);
        if (readFileInChunksOptions == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(readFileInChunksOptions.getUri(), pluginCall, new FilesystemPlugin$readFileInChunks$1(this, readFileInChunksOptions, pluginCall, (Continuation<? super FilesystemPlugin$readFileInChunks$1>) null));
    }

    @PluginMethod
    public final void writeFile(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        WriteFileOptions writeFileOptions = FilesystemMethodOptionsKt.getWriteFileOptions(pluginCall);
        if (writeFileOptions == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(writeFileOptions.getUri(), pluginCall, new FilesystemPlugin$writeFile$1(this, writeFileOptions, pluginCall, (Continuation<? super FilesystemPlugin$writeFile$1>) null));
    }

    @PluginMethod
    public final void appendFile(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        try {
            pluginCall.getData().putOpt(FilesystemMethodOptionsKt.INPUT_APPEND, true);
            writeFile(pluginCall);
        } catch (JSONException e) {
            Log.e(getLogTag(), "Tried to set `append` in `PluginCall`, but got exception", e);
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            String localizedMessage = e.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "";
            }
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.operationFailed(methodName, localizedMessage));
        }
    }

    @PluginMethod
    public final void deleteFile(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        IONFILEUri.Unresolved singleIONFILEUri = FilesystemMethodOptionsKt.getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(singleIONFILEUri, pluginCall, new FilesystemPlugin$deleteFile$1(this, pluginCall, (Continuation<? super FilesystemPlugin$deleteFile$1>) null));
    }

    @PluginMethod
    public final void mkdir(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        SingleUriWithRecursiveOptions singleUriWithRecursiveOptions = FilesystemMethodOptionsKt.getSingleUriWithRecursiveOptions(pluginCall);
        if (singleUriWithRecursiveOptions == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(singleUriWithRecursiveOptions.getUri(), pluginCall, new FilesystemPlugin$mkdir$1(this, singleUriWithRecursiveOptions, pluginCall, (Continuation<? super FilesystemPlugin$mkdir$1>) null));
    }

    @PluginMethod
    public final void rmdir(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        SingleUriWithRecursiveOptions singleUriWithRecursiveOptions = FilesystemMethodOptionsKt.getSingleUriWithRecursiveOptions(pluginCall);
        if (singleUriWithRecursiveOptions == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(singleUriWithRecursiveOptions.getUri(), pluginCall, new FilesystemPlugin$rmdir$1(this, singleUriWithRecursiveOptions, pluginCall, (Continuation<? super FilesystemPlugin$rmdir$1>) null));
    }

    @PluginMethod
    public final void readdir(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        IONFILEUri.Unresolved singleIONFILEUri = FilesystemMethodOptionsKt.getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(singleIONFILEUri, pluginCall, new FilesystemPlugin$readdir$1(this, pluginCall, (Continuation<? super FilesystemPlugin$readdir$1>) null));
    }

    @PluginMethod
    public final void getUri(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        IONFILEUri.Unresolved singleIONFILEUri = FilesystemMethodOptionsKt.getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new FilesystemPlugin$getUri$1(this, singleIONFILEUri, pluginCall, (Continuation<? super FilesystemPlugin$getUri$1>) null), 3, (Object) null);
    }

    @PluginMethod
    public final void stat(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        IONFILEUri.Unresolved singleIONFILEUri = FilesystemMethodOptionsKt.getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(singleIONFILEUri, pluginCall, new FilesystemPlugin$stat$1(this, pluginCall, (Continuation<? super FilesystemPlugin$stat$1>) null));
    }

    @PluginMethod
    public final void rename(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        DoubleUri doubleIONFILEUri = FilesystemMethodOptionsKt.getDoubleIONFILEUri(pluginCall);
        if (doubleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(doubleIONFILEUri.getFromUri(), doubleIONFILEUri.getToUri(), pluginCall, new FilesystemPlugin$rename$1(this, pluginCall, (Continuation<? super FilesystemPlugin$rename$1>) null));
    }

    @PluginMethod
    public final void copy(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        DoubleUri doubleIONFILEUri = FilesystemMethodOptionsKt.getDoubleIONFILEUri(pluginCall);
        if (doubleIONFILEUri == null) {
            FilesystemPlugin filesystemPlugin = this;
            FilesystemErrors filesystemErrors = FilesystemErrors.INSTANCE;
            String methodName = pluginCall.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            PluginResultExtensionsKt.sendError(pluginCall, filesystemErrors.invalidInputMethod(methodName));
            return;
        }
        runWithPermission(doubleIONFILEUri.getFromUri(), doubleIONFILEUri.getToUri(), pluginCall, new FilesystemPlugin$copy$1(this, pluginCall, (Continuation<? super FilesystemPlugin$copy$1>) null));
    }

    @PluginMethod
    @Deprecated(message = "Use @capacitor/file-transfer plugin instead")
    public final void downloadFile(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        try {
            String string = pluginCall.getString("directory", Environment.DIRECTORY_DOWNLOADS);
            LegacyFilesystemImplementation legacyFilesystemImplementation = this.legacyImplementation;
            if (legacyFilesystemImplementation == null || !legacyFilesystemImplementation.isPublicDirectory(string) || isStoragePermissionGranted(false)) {
                FilesystemPlugin$$ExternalSyntheticLambda0 filesystemPlugin$$ExternalSyntheticLambda0 = new FilesystemPlugin$$ExternalSyntheticLambda0(pluginCall, this);
                LegacyFilesystemImplementation legacyFilesystemImplementation2 = this.legacyImplementation;
                if (legacyFilesystemImplementation2 != null) {
                    Bridge bridge = this.bridge;
                    Intrinsics.checkNotNullExpressionValue(bridge, "bridge");
                    legacyFilesystemImplementation2.downloadFile(pluginCall, bridge, filesystemPlugin$$ExternalSyntheticLambda0, new FilesystemPlugin$downloadFile$1(this, string, pluginCall));
                    return;
                }
                return;
            }
            requestAllPermissions(pluginCall, "permissionCallback");
        } catch (Exception e) {
            pluginCall.reject("Error downloading file: " + e.getLocalizedMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static final void downloadFile$lambda$11(PluginCall pluginCall, FilesystemPlugin filesystemPlugin, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(pluginCall, "$call");
        Intrinsics.checkNotNullParameter(filesystemPlugin, "this$0");
        JSObject jSObject = new JSObject();
        jSObject.put(ImagesContract.URL, pluginCall.getString(ImagesContract.URL));
        jSObject.put("bytes", (Object) num);
        jSObject.put("contentLength", (Object) num2);
        filesystemPlugin.notifyListeners(NotificationCompat.CATEGORY_PROGRESS, jSObject);
    }

    @PluginMethod
    public void checkPermissions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        if (isStoragePermissionGranted(false)) {
            JSObject jSObject = new JSObject();
            jSObject.put("publicStorage", "granted");
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, jSObject, false, 2, (Object) null);
            return;
        }
        super.checkPermissions(pluginCall);
    }

    @PluginMethod
    public void requestPermissions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, NotificationCompat.CATEGORY_CALL);
        if (isStoragePermissionGranted(false)) {
            JSObject jSObject = new JSObject();
            jSObject.put("publicStorage", "granted");
            PluginResultExtensionsKt.sendSuccess$default(pluginCall, jSObject, false, 2, (Object) null);
            return;
        }
        super.requestPermissions(pluginCall);
    }

    @PermissionCallback
    private final void permissionCallback(PluginCall pluginCall) {
        if (!isStoragePermissionGranted(true)) {
            Logger.debug(getLogTag(), "User denied storage permission");
            PluginResultExtensionsKt.sendError(pluginCall, FilesystemErrors.INSTANCE.getFilePermissionsDenied());
            return;
        }
        String methodName = pluginCall.getMethodName();
        if (methodName != null) {
            switch (methodName.hashCode()) {
                case -2139808842:
                    if (!methodName.equals("appendFile")) {
                        return;
                    }
                    break;
                case -1406748165:
                    if (!methodName.equals("writeFile")) {
                        return;
                    }
                    break;
                case -1249348042:
                    if (methodName.equals("getUri")) {
                        getUri(pluginCall);
                        return;
                    }
                    return;
                case -934594754:
                    if (methodName.equals("rename")) {
                        rename(pluginCall);
                        return;
                    }
                    return;
                case -867956686:
                    if (methodName.equals("readFile")) {
                        readFile(pluginCall);
                        return;
                    }
                    return;
                case 3059573:
                    if (methodName.equals("copy")) {
                        copy(pluginCall);
                        return;
                    }
                    return;
                case 3540564:
                    if (methodName.equals("stat")) {
                        stat(pluginCall);
                        return;
                    }
                    return;
                case 103950895:
                    if (methodName.equals("mkdir")) {
                        mkdir(pluginCall);
                        return;
                    }
                    return;
                case 108628082:
                    if (methodName.equals("rmdir")) {
                        rmdir(pluginCall);
                        return;
                    }
                    return;
                case 864161661:
                    if (methodName.equals("readFileInChunks")) {
                        readFileInChunks(pluginCall);
                        return;
                    }
                    return;
                case 1080408887:
                    if (methodName.equals("readdir")) {
                        readdir(pluginCall);
                        return;
                    }
                    return;
                case 1108651556:
                    if (methodName.equals("downloadFile")) {
                        downloadFile(pluginCall);
                        return;
                    }
                    return;
                case 1764172231:
                    if (methodName.equals("deleteFile")) {
                        deleteFile(pluginCall);
                        return;
                    }
                    return;
                default:
                    return;
            }
            writeFile(pluginCall);
        }
    }

    /* access modifiers changed from: private */
    public final void runWithPermission(IONFILEUri.Unresolved unresolved, PluginCall pluginCall, Function2<? super IONFILEUri.Resolved, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new FilesystemPlugin$runWithPermission$1(this, unresolved, pluginCall, function2, (Continuation<? super FilesystemPlugin$runWithPermission$1>) null), 3, (Object) null);
    }

    private final void runWithPermission(IONFILEUri.Unresolved unresolved, IONFILEUri.Unresolved unresolved2, PluginCall pluginCall, Function3<? super IONFILEUri.Resolved, ? super IONFILEUri.Resolved, ? super Continuation<? super Unit>, ? extends Object> function3) {
        runWithPermission(unresolved, pluginCall, new FilesystemPlugin$runWithPermission$2(this, unresolved2, pluginCall, function3, (Continuation<? super FilesystemPlugin$runWithPermission$2>) null));
    }

    /* access modifiers changed from: private */
    public final boolean isStoragePermissionGranted(boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            if (!z || getPermissionState("publicStorageAboveAPI29") == PermissionState.GRANTED) {
                return true;
            }
        } else if (getPermissionState("publicStorage") == PermissionState.GRANTED) {
            return true;
        }
        return false;
    }
}
