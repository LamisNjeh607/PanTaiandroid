package com.example.pantaijava.com.getcapacitor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class JSInjector {
    private String bridgeJS;
    private String cordovaJS;
    private String cordovaPluginsFileJS;
    private String cordovaPluginsJS;
    private String globalJS;
    private String localUrlJS;
    private String miscJS;
    private String pluginJS;

    public JSInjector(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, (String) null);
    }

    public JSInjector(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.globalJS = str;
        this.bridgeJS = str2;
        this.pluginJS = str3;
        this.cordovaJS = str4;
        this.cordovaPluginsJS = str5;
        this.cordovaPluginsFileJS = str6;
        this.localUrlJS = str7;
        this.miscJS = str8;
    }

    public String getScriptString() {
        String str = this.globalJS + "\n\n" + this.localUrlJS + "\n\n" + this.bridgeJS + "\n\n" + this.pluginJS + "\n\n" + this.cordovaJS + "\n\n" + this.cordovaPluginsFileJS + "\n\n" + this.cordovaPluginsJS;
        return this.miscJS != null ? str + "\n\n" + this.miscJS : str;
    }

    public InputStream getInjectedStream(InputStream inputStream) {
        String str = "<script type=\"text/javascript\">" + getScriptString() + "</script>";
        String readAssetStream = readAssetStream(inputStream);
        StringBuilder sb = new StringBuilder(readAssetStream);
        if (readAssetStream.contains("<head>")) {
            sb.insert(readAssetStream.indexOf("<head>") + 6, "\n" + str + "\n");
            readAssetStream = sb.toString();
        } else if (readAssetStream.contains("</head>")) {
            sb.insert(readAssetStream.indexOf("</head>"), "\n" + str + "\n");
            readAssetStream = sb.toString();
        } else {
            com.getcapacitor.Logger.error("Unable to inject Capacitor, Plugins won't work");
        }
        return new ByteArrayInputStream(readAssetStream.getBytes(StandardCharsets.UTF_8));
    }

    private String readAssetStream(InputStream inputStream) {
        try {
            char[] cArr = new char[1024];
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            while (true) {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read < 0) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } catch (Exception e) {
            com.getcapacitor.Logger.error("Unable to process HTML asset file. This is a fatal error", e);
            return "";
        }
    }
}
