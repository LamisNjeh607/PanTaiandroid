package org.apache.cordova.file;

import android.net.Uri;

public class LocalFilesystemURL {
    public static final String CDVFILE_KEYWORD = "__cdvfile_";
    public static final String FILESYSTEM_PROTOCOL = "cdvfile";
    public final String fsName;
    public final boolean isDirectory;
    public final String path;
    public final Uri uri;

    private LocalFilesystemURL(Uri uri2, String str, String str2, boolean z) {
        this.uri = uri2;
        this.fsName = str;
        this.path = str2;
        this.isDirectory = z;
    }

    public static LocalFilesystemURL parse(Uri uri2) {
        int indexOf;
        if (!uri2.toString().contains(CDVFILE_KEYWORD)) {
            return null;
        }
        String path2 = uri2.getPath();
        boolean z = true;
        if (path2.length() < 1 || (indexOf = path2.indexOf(47, 1)) < 0) {
            return null;
        }
        String substring = path2.substring(1, indexOf).substring(10);
        String substring2 = substring.substring(0, substring.length() - 2);
        String substring3 = path2.substring(indexOf);
        if (substring3.charAt(substring3.length() - 1) != '/') {
            z = false;
        }
        return new LocalFilesystemURL(uri2, substring2, substring3, z);
    }

    public static LocalFilesystemURL parse(String str) {
        return parse(Uri.parse(str));
    }

    public static String fsNameToCdvKeyword(String str) {
        return CDVFILE_KEYWORD + str + "__";
    }

    public String toString() {
        return this.uri.toString();
    }
}
