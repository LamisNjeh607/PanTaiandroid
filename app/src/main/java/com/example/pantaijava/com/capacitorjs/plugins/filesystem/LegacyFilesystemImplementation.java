package com.capacitorjs.plugins.filesystem;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.plugin.util.HttpRequestHandler;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import kotlin.Metadata;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0002J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\b\u0010\u0015\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/LegacyFilesystemImplementation;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "doDownloadInBackground", "Lcom/getcapacitor/JSObject;", "urlString", "", "call", "Lcom/getcapacitor/PluginCall;", "bridge", "Lcom/getcapacitor/Bridge;", "emitter", "Lcom/getcapacitor/plugin/util/HttpRequestHandler$ProgressEmitter;", "downloadFile", "", "callback", "Lcom/capacitorjs/plugins/filesystem/LegacyFilesystemImplementation$FilesystemDownloadCallback;", "getDirectory", "Ljava/io/File;", "directory", "getFileObject", "path", "isPublicDirectory", "", "FilesystemDownloadCallback", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LegacyFilesystemImplementation.kt */
public final class LegacyFilesystemImplementation {
    private final Context context;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/capacitorjs/plugins/filesystem/LegacyFilesystemImplementation$FilesystemDownloadCallback;", "", "onError", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "result", "Lcom/getcapacitor/JSObject;", "capacitor-filesystem_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LegacyFilesystemImplementation.kt */
    public interface FilesystemDownloadCallback {
        void onError(Exception exc);

        void onSuccess(JSObject jSObject);
    }

    public LegacyFilesystemImplementation(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final void downloadFile(PluginCall pluginCall, Bridge bridge, HttpRequestHandler.ProgressEmitter progressEmitter, FilesystemDownloadCallback filesystemDownloadCallback) {
        PluginCall pluginCall2 = pluginCall;
        Intrinsics.checkNotNullParameter(pluginCall2, NotificationCompat.CATEGORY_CALL);
        Bridge bridge2 = bridge;
        Intrinsics.checkNotNullParameter(bridge2, "bridge");
        FilesystemDownloadCallback filesystemDownloadCallback2 = filesystemDownloadCallback;
        Intrinsics.checkNotNullParameter(filesystemDownloadCallback2, PluginMethod.RETURN_CALLBACK);
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new LegacyFilesystemImplementation$downloadFile$1(this, pluginCall2.getString(ImagesContract.URL, ""), pluginCall2, bridge2, progressEmitter, new Handler(Looper.getMainLooper()), filesystemDownloadCallback2), 31, (Object) null);
    }

    public final boolean isPublicDirectory(String str) {
        return Intrinsics.areEqual((Object) "DOCUMENTS", (Object) str) || Intrinsics.areEqual((Object) "EXTERNAL_STORAGE", (Object) str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        if (r4.equals("DATA") == false) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        return r0.getFilesDir();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r4.equals("LIBRARY") == false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.io.File getDirectory(java.lang.String r4) {
        /*
            r3 = this;
            android.content.Context r0 = r3.context
            int r1 = r4.hashCode()
            r2 = 0
            switch(r1) {
                case -1038134325: goto L_0x004d;
                case -564829544: goto L_0x003e;
                case 2090922: goto L_0x0030;
                case 63879010: goto L_0x0022;
                case 884191387: goto L_0x0019;
                case 1013698023: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x005b
        L_0x000b:
            java.lang.String r0 = "EXTERNAL_STORAGE"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0014
            goto L_0x005b
        L_0x0014:
            java.io.File r4 = android.os.Environment.getExternalStorageDirectory()
            return r4
        L_0x0019:
            java.lang.String r1 = "LIBRARY"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0039
            goto L_0x005b
        L_0x0022:
            java.lang.String r1 = "CACHE"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x002b
            goto L_0x005b
        L_0x002b:
            java.io.File r4 = r0.getCacheDir()
            return r4
        L_0x0030:
            java.lang.String r1 = "DATA"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0039
            goto L_0x005b
        L_0x0039:
            java.io.File r4 = r0.getFilesDir()
            return r4
        L_0x003e:
            java.lang.String r0 = "DOCUMENTS"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x005b
            java.lang.String r4 = android.os.Environment.DIRECTORY_DOCUMENTS
            java.io.File r4 = android.os.Environment.getExternalStoragePublicDirectory(r4)
            return r4
        L_0x004d:
            java.lang.String r1 = "EXTERNAL"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0056
            goto L_0x005b
        L_0x0056:
            java.io.File r4 = r0.getExternalFilesDir(r2)
            return r4
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.capacitorjs.plugins.filesystem.LegacyFilesystemImplementation.getDirectory(java.lang.String):java.io.File");
    }

    private final File getFileObject(String str, String str2) {
        if (str2 == null) {
            Uri parse = Uri.parse(str);
            if (parse.getScheme() == null || Intrinsics.areEqual((Object) parse.getScheme(), (Object) "file")) {
                return new File(parse.getPath());
            }
        }
        Intrinsics.checkNotNull(str2);
        File directory = getDirectory(str2);
        if (directory == null) {
            return null;
        }
        if (!directory.exists()) {
            directory.mkdir();
        }
        return new File(directory, str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006a, code lost:
        if (r10 == null) goto L_0x006c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0105 A[EDGE_INSN: B:35:0x0105->B:29:0x0105 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.getcapacitor.JSObject doDownloadInBackground(java.lang.String r20, com.getcapacitor.PluginCall r21, com.getcapacitor.Bridge r22, com.getcapacitor.plugin.util.HttpRequestHandler.ProgressEmitter r23) throws java.io.IOException, java.net.URISyntaxException, org.json.JSONException {
        /*
            r19 = this;
            r0 = r21
            r1 = r23
            com.getcapacitor.JSObject r2 = new com.getcapacitor.JSObject
            r2.<init>()
            java.lang.String r3 = "headers"
            com.getcapacitor.JSObject r2 = r0.getObject(r3, r2)
            com.getcapacitor.JSObject r3 = new com.getcapacitor.JSObject
            r3.<init>()
            java.lang.String r4 = "params"
            com.getcapacitor.JSObject r3 = r0.getObject(r4, r3)
            java.lang.String r4 = "connectTimeout"
            java.lang.Integer r4 = r0.getInt(r4)
            java.lang.String r5 = "readTimeout"
            java.lang.Integer r5 = r0.getInt(r5)
            java.lang.String r6 = "disableRedirects"
            java.lang.Boolean r6 = r0.getBoolean(r6)
            r7 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r7)
            if (r6 != 0) goto L_0x0034
            r6 = r8
        L_0x0034:
            boolean r6 = r6.booleanValue()
            java.lang.String r9 = "shouldEncodeUrlParams"
            java.lang.Boolean r9 = r0.getBoolean(r9)
            if (r9 != 0) goto L_0x0045
            r9 = 1
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
        L_0x0045:
            boolean r9 = r9.booleanValue()
            java.lang.String r10 = "progress"
            java.lang.Boolean r10 = r0.getBoolean(r10)
            if (r10 != 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r8 = r10
        L_0x0053:
            boolean r8 = r8.booleanValue()
            java.lang.String r10 = "method"
            java.lang.String r10 = r0.getString(r10)
            if (r10 == 0) goto L_0x006c
            java.util.Locale r11 = java.util.Locale.ROOT
            java.lang.String r10 = r10.toUpperCase(r11)
            java.lang.String r11 = "toUpperCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
            if (r10 != 0) goto L_0x006e
        L_0x006c:
            java.lang.String r10 = "GET"
        L_0x006e:
            java.lang.String r11 = "path"
            java.lang.String r12 = r0.getString(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            java.lang.String r13 = "directory"
            java.lang.String r14 = android.os.Environment.DIRECTORY_DOWNLOADS
            java.lang.String r0 = r0.getString(r13, r14)
            java.net.URL r13 = new java.net.URL
            r14 = r20
            r13.<init>(r14)
            r14 = r19
            java.io.File r0 = r14.getFileObject(r12, r0)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r12 = new com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder
            r12.<init>()
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r12 = r12.setUrl(r13)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r10 = r12.setMethod(r10)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r10.setHeaders(r2)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r2.setUrlParams(r3, r9)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r2.setConnectTimeout(r4)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r2.setReadTimeout(r5)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r2.setDisableRedirects(r3)
            com.getcapacitor.plugin.util.HttpRequestHandler$HttpURLConnectionBuilder r2 = r2.openConnection()
            com.getcapacitor.plugin.util.CapacitorHttpUrlConnection r2 = r2.build()
            r3 = r22
            r2.setSSLSocketFactory(r3)
            java.io.InputStream r3 = r2.getInputStream()
            java.io.FileOutputStream r4 = new java.io.FileOutputStream
            r4.<init>(r0, r7)
            java.lang.String r5 = "content-length"
            java.lang.String r2 = r2.getHeaderField(r5)
            if (r2 == 0) goto L_0x00d4
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x00d4 }
            goto L_0x00d5
        L_0x00d4:
            r2 = r7
        L_0x00d5:
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]
            long r9 = java.lang.System.currentTimeMillis()
            r6 = r7
        L_0x00de:
            int r12 = r3.read(r5)
            if (r12 <= 0) goto L_0x0105
            r4.write(r5, r7, r12)
            int r6 = r6 + r12
            if (r8 == 0) goto L_0x00de
            if (r1 == 0) goto L_0x00de
            long r12 = java.lang.System.currentTimeMillis()
            long r15 = r12 - r9
            r17 = 100
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 <= 0) goto L_0x00de
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            r1.emit(r9, r10)
            r9 = r12
            goto L_0x00de
        L_0x0105:
            if (r8 == 0) goto L_0x0114
            if (r1 == 0) goto L_0x0114
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.emit(r5, r2)
        L_0x0114:
            r3.close()
            r4.close()
            com.getcapacitor.JSObject r1 = new com.getcapacitor.JSObject
            r1.<init>()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.String r0 = r0.getAbsolutePath()
            r1.put((java.lang.String) r11, (java.lang.String) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.capacitorjs.plugins.filesystem.LegacyFilesystemImplementation.doDownloadInBackground(java.lang.String, com.getcapacitor.PluginCall, com.getcapacitor.Bridge, com.getcapacitor.plugin.util.HttpRequestHandler$ProgressEmitter):com.getcapacitor.JSObject");
    }
}
