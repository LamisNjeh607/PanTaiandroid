package com.getcapacitor;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    private static String CapacitorFileScheme = "/_capacitor_file_";

    public enum Type {
        IMAGE("image");
        
        private String type;

        private Type(String str) {
            this.type = str;
        }
    }

    public static String getPortablePath(Context context, String str, Uri uri) {
        String fileUrlForUri = getFileUrlForUri(context, uri);
        if (fileUrlForUri.startsWith("file://")) {
            fileUrlForUri = fileUrlForUri.replace("file://", "");
        }
        return str + Bridge.CAPACITOR_FILE_START + fileUrlForUri;
    }

    public static String getFileUrlForUri(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                String[] split = documentId.split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return legacyPrimaryPath(split[1]);
                }
                int indexOf = documentId.indexOf(58, 1);
                String substring = documentId.substring(0, indexOf);
                String substring2 = documentId.substring(indexOf + 1);
                String pathToNonPrimaryVolume = getPathToNonPrimaryVolume(context, substring);
                if (pathToNonPrimaryVolume != null) {
                    String str = pathToNonPrimaryVolume + "/" + substring2;
                    File file = new File(str);
                    if (!file.exists() || !file.canRead()) {
                        return null;
                    }
                    return str;
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str2 = split2[0];
                if ("image".equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, (String) null, (String[]) null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static String legacyPrimaryPath(String str) {
        return Environment.getExternalStorageDirectory() + "/" + str;
    }

    static String readFileFromAssets(AssetManager assetManager, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str)));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine).append("\n");
                } else {
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    return sb2;
                }
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    static String readFileFromDisk(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine).append("\n");
                } else {
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    return sb2;
                }
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:11|10|19|20|(1:22)|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        r8 = th;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0046, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003a */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r7 = "_data"
            r3[r0] = r7
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x003a }
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x002b
            boolean r11 = r10.moveToFirst()     // Catch:{ IllegalArgumentException -> 0x0029, all -> 0x0026 }
            if (r11 == 0) goto L_0x002b
            int r11 = r10.getColumnIndexOrThrow(r7)     // Catch:{ IllegalArgumentException -> 0x0029, all -> 0x0026 }
            java.lang.String r0 = r10.getString(r11)     // Catch:{ IllegalArgumentException -> 0x0029, all -> 0x0026 }
            goto L_0x002b
        L_0x0026:
            r8 = move-exception
            r0 = r10
            goto L_0x0044
        L_0x0029:
            r0 = r10
            goto L_0x003a
        L_0x002b:
            if (r10 == 0) goto L_0x0030
            r10.close()
        L_0x0030:
            if (r0 != 0) goto L_0x0037
            java.lang.String r8 = getCopyFilePath(r9, r8)
            return r8
        L_0x0037:
            return r0
        L_0x0038:
            r8 = move-exception
            goto L_0x0044
        L_0x003a:
            java.lang.String r8 = getCopyFilePath(r9, r8)     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0043
            r0.close()
        L_0x0043:
            return r8
        L_0x0044:
            if (r0 == 0) goto L_0x0049
            r0.close()
        L_0x0049:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getcapacitor.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    private static String getCopyFilePath(Uri uri, Context context) {
        Cursor query = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        int columnIndex = query.getColumnIndex("_display_name");
        query.moveToFirst();
        File file = new File(context.getFilesDir(), sanitizeFilename(query.getString(columnIndex)));
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[Math.min(openInputStream.available(), 1048576)];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            openInputStream.close();
            fileOutputStream.close();
            if (query != null) {
                query.close();
            }
            return file.getPath();
        } catch (Exception unused) {
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Throwable th) {
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static String getPathToNonPrimaryVolume(Context context, String str) {
        String absolutePath;
        int indexOf;
        File[] externalCacheDirs = context.getExternalCacheDirs();
        if (externalCacheDirs == null) {
            return null;
        }
        for (File file : externalCacheDirs) {
            if (file != null && (absolutePath = file.getAbsolutePath()) != null && (indexOf = absolutePath.indexOf(str)) != -1) {
                return absolutePath.substring(0, indexOf) + str;
            }
        }
        return null;
    }

    private static String sanitizeFilename(String str) {
        String[] strArr = {"..", "/"};
        String[] split = str.split("/");
        String str2 = split[split.length - 1];
        for (int i = 0; i < 2; i++) {
            str2 = str2.replace(strArr[i], "_");
        }
        return str2;
    }
}
