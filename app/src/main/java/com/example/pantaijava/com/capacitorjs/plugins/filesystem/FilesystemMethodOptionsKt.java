package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import com.getcapacitor.PluginCall;
import io.ionic.libs.ionfilesystemlib.model.IONFILEEncoding;
import io.ionic.libs.ionfilesystemlib.model.IONFILEFolderType;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadInChunksOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEReadOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILESaveMode;
import io.ionic.libs.ionfilesystemlib.model.IONFILESaveOptions;
import io.ionic.libs.ionfilesystemlib.model.IONFILEUri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0012H\u0000\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0012H\u0000\u001a\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0012H\u0000\u001a\u000e\u0010\u0017\u001a\u0004\u0018\u00010\r*\u00020\u0012H\u0000\u001a\u000e\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u0012H\u0000\u001a\u000e\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u0012H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"INPUT_APPEND", "", "INPUT_CHUNK_SIZE", "INPUT_DATA", "INPUT_DIRECTORY", "INPUT_ENCODING", "INPUT_FROM", "INPUT_FROM_DIRECTORY", "INPUT_PATH", "INPUT_RECURSIVE", "INPUT_TO", "INPUT_TO_DIRECTORY", "unresolvedUri", "Lio/ionic/libs/ionfilesystemlib/model/IONFILEUri$Unresolved;", "path", "directoryAlias", "getDoubleIONFILEUri", "Lcom/capacitorjs/plugins/filesystem/DoubleUri;", "Lcom/getcapacitor/PluginCall;", "getReadFileInChunksOptions", "Lcom/capacitorjs/plugins/filesystem/ReadFileInChunksOptions;", "getReadFileOptions", "Lcom/capacitorjs/plugins/filesystem/ReadFileOptions;", "getSingleIONFILEUri", "getSingleUriWithRecursiveOptions", "Lcom/capacitorjs/plugins/filesystem/SingleUriWithRecursiveOptions;", "getWriteFileOptions", "Lcom/capacitorjs/plugins/filesystem/WriteFileOptions;", "capacitor-filesystem_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemMethodOptions.kt */
public final class FilesystemMethodOptionsKt {
    public static final String INPUT_APPEND = "append";
    private static final String INPUT_CHUNK_SIZE = "chunkSize";
    private static final String INPUT_DATA = "data";
    private static final String INPUT_DIRECTORY = "directory";
    private static final String INPUT_ENCODING = "encoding";
    private static final String INPUT_FROM = "from";
    private static final String INPUT_FROM_DIRECTORY = "directory";
    private static final String INPUT_PATH = "path";
    private static final String INPUT_RECURSIVE = "recursive";
    private static final String INPUT_TO = "to";
    private static final String INPUT_TO_DIRECTORY = "toDirectory";

    public static final com.capacitorjs.plugins.filesystem.ReadFileOptions getReadFileOptions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        IONFILEUri.Unresolved singleIONFILEUri = getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            return null;
        }
        return new com.capacitorjs.plugins.filesystem.ReadFileOptions(singleIONFILEUri, new IONFILEReadOptions(IONFILEEncoding.Companion.fromEncodingName(pluginCall.getString(INPUT_ENCODING))));
    }

    public static final com.capacitorjs.plugins.filesystem.ReadFileInChunksOptions getReadFileInChunksOptions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        IONFILEUri.Unresolved singleIONFILEUri = getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            return null;
        }
        IONFILEEncoding fromEncodingName = IONFILEEncoding.Companion.fromEncodingName(pluginCall.getString(INPUT_ENCODING));
        Integer num = pluginCall.getInt(INPUT_CHUNK_SIZE);
        if (num == null) {
            return null;
        }
        if (num.intValue() <= 0) {
            num = null;
        }
        if (num == null) {
            return null;
        }
        return new com.capacitorjs.plugins.filesystem.ReadFileInChunksOptions(singleIONFILEUri, new IONFILEReadInChunksOptions(fromEncodingName, num.intValue()));
    }

    public static final com.capacitorjs.plugins.filesystem.WriteFileOptions getWriteFileOptions(PluginCall pluginCall) {
        String string;
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        IONFILEUri.Unresolved singleIONFILEUri = getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null || (string = pluginCall.getString(INPUT_DATA)) == null) {
            return null;
        }
        Boolean bool = pluginCall.getBoolean(INPUT_RECURSIVE);
        if (bool == null) {
            bool = false;
        }
        boolean booleanValue = bool.booleanValue();
        Boolean bool2 = pluginCall.getBoolean(INPUT_APPEND);
        if (bool2 == null) {
            bool2 = false;
        }
        return new com.capacitorjs.plugins.filesystem.WriteFileOptions(singleIONFILEUri, new IONFILESaveOptions(string, IONFILEEncoding.Companion.fromEncodingName(pluginCall.getString(INPUT_ENCODING)), bool2.booleanValue() ? IONFILESaveMode.APPEND : IONFILESaveMode.WRITE, booleanValue));
    }

    public static final com.capacitorjs.plugins.filesystem.SingleUriWithRecursiveOptions getSingleUriWithRecursiveOptions(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        IONFILEUri.Unresolved singleIONFILEUri = getSingleIONFILEUri(pluginCall);
        if (singleIONFILEUri == null) {
            return null;
        }
        Boolean bool = pluginCall.getBoolean(INPUT_RECURSIVE);
        if (bool == null) {
            bool = false;
        }
        return new com.capacitorjs.plugins.filesystem.SingleUriWithRecursiveOptions(singleIONFILEUri, bool.booleanValue());
    }

    public static final DoubleUri getDoubleIONFILEUri(PluginCall pluginCall) {
        IONFILEFolderType iONFILEFolderType;
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        String string = pluginCall.getString(INPUT_FROM);
        if (string == null) {
            return null;
        }
        IONFILEFolderType fromStringAlias = IONFILEFolderType.Companion.fromStringAlias(pluginCall.getString("directory"));
        String string2 = pluginCall.getString(INPUT_TO);
        if (string2 == null) {
            return null;
        }
        String string3 = pluginCall.getString(INPUT_TO_DIRECTORY);
        if (string3 == null || (iONFILEFolderType = IONFILEFolderType.Companion.fromStringAlias(string3)) == null) {
            iONFILEFolderType = fromStringAlias;
        }
        return new DoubleUri(new IONFILEUri.Unresolved(fromStringAlias, string), new IONFILEUri.Unresolved(iONFILEFolderType, string2));
    }

    public static final IONFILEUri.Unresolved getSingleIONFILEUri(PluginCall pluginCall) {
        Intrinsics.checkNotNullParameter(pluginCall, "<this>");
        String string = pluginCall.getString(INPUT_PATH);
        if (string == null) {
            return null;
        }
        return unresolvedUri(string, pluginCall.getString("directory"));
    }

    private static final IONFILEUri.Unresolved unresolvedUri(String str, String str2) {
        return new IONFILEUri.Unresolved(IONFILEFolderType.Companion.fromStringAlias(str2), str);
    }
}
