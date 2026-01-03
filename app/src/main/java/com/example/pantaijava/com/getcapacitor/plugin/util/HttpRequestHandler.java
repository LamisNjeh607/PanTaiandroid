package com.getcapacitor.plugin.util;

import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.JSValue;
import com.getcapacitor.PluginCall;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.jmdns.impl.util.ByteWrangler;
import org.json.JSONArray;
import org.json.JSONException;

public class HttpRequestHandler {

    @FunctionalInterface
    public interface ProgressEmitter {
        void emit(Integer num, Integer num2);
    }

    public enum ResponseType {
        ARRAY_BUFFER("arraybuffer"),
        BLOB("blob"),
        DOCUMENT("document"),
        JSON("json"),
        TEXT("text");
        
        static final ResponseType DEFAULT = null;
        private final String name;

        static {
            ResponseType responseType;
            DEFAULT = responseType;
        }

        private ResponseType(String str) {
            this.name = str;
        }

        public static ResponseType parse(String str) {
            for (ResponseType responseType : values()) {
                if (responseType.name.equalsIgnoreCase(str)) {
                    return responseType;
                }
            }
            return DEFAULT;
        }
    }

    public static class HttpURLConnectionBuilder {
        public Integer connectTimeout;
        public CapacitorHttpUrlConnection connection;
        public Boolean disableRedirects;
        public JSObject headers;
        public String method;
        public Integer readTimeout;
        public URL url;

        public HttpURLConnectionBuilder setConnectTimeout(Integer num) {
            this.connectTimeout = num;
            return this;
        }

        public HttpURLConnectionBuilder setReadTimeout(Integer num) {
            this.readTimeout = num;
            return this;
        }

        public HttpURLConnectionBuilder setDisableRedirects(Boolean bool) {
            this.disableRedirects = bool;
            return this;
        }

        public HttpURLConnectionBuilder setHeaders(JSObject jSObject) {
            this.headers = jSObject;
            return this;
        }

        public HttpURLConnectionBuilder setMethod(String str) {
            this.method = str;
            return this;
        }

        public HttpURLConnectionBuilder setUrl(URL url2) {
            this.url = url2;
            return this;
        }

        public HttpURLConnectionBuilder openConnection() throws IOException {
            CapacitorHttpUrlConnection capacitorHttpUrlConnection = new CapacitorHttpUrlConnection((HttpURLConnection) this.url.openConnection());
            this.connection = capacitorHttpUrlConnection;
            capacitorHttpUrlConnection.setAllowUserInteraction(false);
            this.connection.setRequestMethod(this.method);
            Integer num = this.connectTimeout;
            if (num != null) {
                this.connection.setConnectTimeout(num.intValue());
            }
            Integer num2 = this.readTimeout;
            if (num2 != null) {
                this.connection.setReadTimeout(num2.intValue());
            }
            Boolean bool = this.disableRedirects;
            if (bool != null) {
                this.connection.setDisableRedirects(bool.booleanValue());
            }
            this.connection.setRequestHeaders(this.headers);
            return this;
        }

        public HttpURLConnectionBuilder setUrlParams(JSObject jSObject) throws MalformedURLException, URISyntaxException, JSONException {
            return setUrlParams(jSObject, true);
        }

        public HttpURLConnectionBuilder setUrlParams(JSObject jSObject, boolean z) throws URISyntaxException, MalformedURLException {
            String query = this.url.getQuery();
            String str = "";
            if (query == null) {
                query = str;
            }
            Iterator keys = jSObject.keys();
            if (!keys.hasNext()) {
                return this;
            }
            StringBuilder sb = new StringBuilder(query);
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                try {
                    StringBuilder sb2 = new StringBuilder();
                    JSONArray jSONArray = jSObject.getJSONArray(str2);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        addUrlParam(sb2, str2, jSONArray.getString(i), z);
                        if (i != jSONArray.length() - 1) {
                            sb2.append("&");
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(sb2);
                } catch (JSONException unused) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    addUrlParam(sb, str2, jSObject.getString(str2), z);
                }
            }
            String sb3 = sb.toString();
            URI uri = this.url.toURI();
            StringBuilder append = new StringBuilder().append(uri.getScheme()).append("://").append(uri.getAuthority()).append(uri.getPath()).append(!sb3.equals(str) ? "?" + sb3 : str);
            if (uri.getFragment() != null) {
                str = uri.getFragment();
            }
            this.url = new URL(append.append(str).toString());
            return this;
        }

        private static void addUrlParam(StringBuilder sb, String str, String str2, boolean z) {
            if (z) {
                try {
                    str = URLEncoder.encode(str, ByteWrangler.CHARSET_NAME);
                    str2 = URLEncoder.encode(str2, ByteWrangler.CHARSET_NAME);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getCause());
                }
            }
            sb.append(str).append("=").append(str2);
        }

        public CapacitorHttpUrlConnection build() {
            return this.connection;
        }
    }

    public static JSObject buildResponse(CapacitorHttpUrlConnection capacitorHttpUrlConnection) throws IOException, JSONException {
        return buildResponse(capacitorHttpUrlConnection, ResponseType.DEFAULT);
    }

    public static JSObject buildResponse(CapacitorHttpUrlConnection capacitorHttpUrlConnection, ResponseType responseType) throws IOException, JSONException {
        int responseCode = capacitorHttpUrlConnection.getResponseCode();
        JSObject jSObject = new JSObject();
        jSObject.put(NotificationCompat.CATEGORY_STATUS, responseCode);
        jSObject.put("headers", (Object) buildResponseHeaders(capacitorHttpUrlConnection));
        jSObject.put(ImagesContract.URL, (Object) capacitorHttpUrlConnection.getURL());
        jSObject.put("data", readData(capacitorHttpUrlConnection, responseType));
        if (capacitorHttpUrlConnection.getErrorStream() != null) {
            jSObject.put("error", true);
        }
        return jSObject;
    }

    public static Object readData(ICapacitorHttpUrlConnection iCapacitorHttpUrlConnection, ResponseType responseType) throws IOException, JSONException {
        InputStream errorStream = iCapacitorHttpUrlConnection.getErrorStream();
        String headerField = iCapacitorHttpUrlConnection.getHeaderField("Content-Type");
        if (errorStream != null) {
            if (isOneOf(headerField, MimeType.APPLICATION_JSON, MimeType.APPLICATION_VND_API_JSON)) {
                return parseJSON(readStreamAsString(errorStream));
            }
            return readStreamAsString(errorStream);
        } else if (headerField != null && headerField.contains(MimeType.APPLICATION_JSON.getValue())) {
            return parseJSON(readStreamAsString(iCapacitorHttpUrlConnection.getInputStream()));
        } else {
            InputStream inputStream = iCapacitorHttpUrlConnection.getInputStream();
            int ordinal = responseType.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                return readStreamAsBase64(inputStream);
            }
            if (ordinal != 3) {
                return readStreamAsString(inputStream);
            }
            return parseJSON(readStreamAsString(inputStream));
        }
    }

    public static boolean isOneOf(String str, MimeType... mimeTypeArr) {
        if (str != null) {
            for (MimeType value : mimeTypeArr) {
                if (str.contains(value.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static JSObject buildResponseHeaders(CapacitorHttpUrlConnection capacitorHttpUrlConnection) {
        JSObject jSObject = new JSObject();
        for (Map.Entry next : capacitorHttpUrlConnection.getHeaderFields().entrySet()) {
            jSObject.put((String) next.getKey(), TextUtils.join(", ", (Iterable) next.getValue()));
        }
        return jSObject;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:32|33|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        return new com.getcapacitor.JSArray(r3);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x009b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseJSON(java.lang.String r3) throws org.json.JSONException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r0 = "null"
            java.lang.String r1 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x00a1 }
            if (r0 == 0) goto L_0x0014
            java.lang.Object r3 = org.json.JSONObject.NULL     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x0014:
            java.lang.String r0 = "true"
            java.lang.String r1 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x00a1 }
            r1 = 1
            if (r0 == 0) goto L_0x0026
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x0026:
            java.lang.String r0 = "false"
            java.lang.String r2 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            boolean r0 = r0.equals(r2)     // Catch:{ JSONException -> 0x00a1 }
            if (r0 == 0) goto L_0x0038
            r0 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x0038:
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x00a1 }
            if (r0 > 0) goto L_0x0045
            java.lang.String r3 = ""
            return r3
        L_0x0045:
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r2 = "^\".*\"$"
            boolean r0 = r0.matches(r2)     // Catch:{ JSONException -> 0x00a1 }
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r2 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            int r2 = r2.length()     // Catch:{ JSONException -> 0x00a1 }
            int r2 = r2 - r1
            java.lang.String r3 = r0.substring(r1, r2)     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x0063:
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r1 = "^-?\\d+$"
            boolean r0 = r0.matches(r1)     // Catch:{ JSONException -> 0x00a1 }
            if (r0 == 0) goto L_0x007c
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ JSONException -> 0x00a1 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x007c:
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r1 = "^-?\\d+(\\.\\d+)?$"
            boolean r0 = r0.matches(r1)     // Catch:{ JSONException -> 0x00a1 }
            if (r0 == 0) goto L_0x0095
            java.lang.String r0 = r3.trim()     // Catch:{ JSONException -> 0x00a1 }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ JSONException -> 0x00a1 }
            java.lang.Double r3 = java.lang.Double.valueOf(r0)     // Catch:{ JSONException -> 0x00a1 }
            return r3
        L_0x0095:
            com.getcapacitor.JSObject r0 = new com.getcapacitor.JSObject     // Catch:{ JSONException -> 0x009b }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x009b }
            return r0
        L_0x009b:
            com.getcapacitor.JSArray r0 = new com.getcapacitor.JSArray     // Catch:{ JSONException -> 0x00a1 }
            r0.<init>((java.lang.String) r3)     // Catch:{ JSONException -> 0x00a1 }
            return r0
        L_0x00a1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getcapacitor.plugin.util.HttpRequestHandler.parseJSON(java.lang.String):java.lang.Object");
    }

    public static String readStreamAsBase64(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encodeToString = Base64.encodeToString(byteArray, 0, byteArray.length, 0);
                    byteArrayOutputStream.close();
                    return encodeToString;
                }
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static String readStreamAsString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                sb.append(readLine);
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(System.getProperty("line.separator"));
                }
            }
            String sb2 = sb.toString();
            bufferedReader.close();
            return sb2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static JSObject request(PluginCall pluginCall, String str, Bridge bridge) throws IOException, URISyntaxException, JSONException {
        PluginCall pluginCall2 = pluginCall;
        String str2 = str;
        Bridge bridge2 = bridge;
        String string = pluginCall2.getString(ImagesContract.URL, "");
        JSObject object = pluginCall2.getObject("headers", new JSObject());
        JSObject object2 = pluginCall2.getObject("params", new JSObject());
        Integer num = pluginCall2.getInt("connectTimeout");
        Integer num2 = pluginCall2.getInt("readTimeout");
        Boolean bool = pluginCall2.getBoolean("disableRedirects");
        Boolean bool2 = pluginCall2.getBoolean("shouldEncodeUrlParams", true);
        ResponseType parse = ResponseType.parse(pluginCall2.getString("responseType"));
        String string2 = pluginCall2.getString("dataType");
        if (str2 == null) {
            str2 = pluginCall2.getString("method", "GET");
        }
        String upperCase = str2.toUpperCase(Locale.ROOT);
        boolean z = upperCase.equals("DELETE") || upperCase.equals("PATCH") || upperCase.equals("POST") || upperCase.equals("PUT");
        String string3 = object.getString("x-cap-user-agent");
        if (string3 != null) {
            object.put("User-Agent", string3);
        }
        object.remove("x-cap-user-agent");
        if (!object.has("User-Agent") && !object.has("user-agent")) {
            object.put("User-Agent", bridge.getConfig().getOverriddenUserAgentString());
        }
        URL url = new URL(string);
        CapacitorHttpUrlConnection build = new HttpURLConnectionBuilder().setUrl(url).setMethod(upperCase).setHeaders(object).setUrlParams(object2, bool2.booleanValue()).setConnectTimeout(num).setReadTimeout(num2).setDisableRedirects(bool).openConnection().build();
        if (bridge2 != null && !isDomainExcludedFromSSL(bridge2, url).booleanValue()) {
            build.setSSLSocketFactory(bridge2);
        }
        if (z) {
            JSValue jSValue = new JSValue(pluginCall2, "data");
            if (jSValue.getValue() != null) {
                build.setDoOutput(true);
                build.setRequestBody(pluginCall2, jSValue, string2);
            }
        }
        pluginCall.getData().put("activeCapacitorHttpUrlConnection", (Object) build);
        build.connect();
        JSObject buildResponse = buildResponse(build, parse);
        build.disconnect();
        pluginCall.getData().remove("activeCapacitorHttpUrlConnection");
        return buildResponse;
    }

    public static Boolean isDomainExcludedFromSSL(Bridge bridge, URL url) {
        try {
            Class<?> cls = Class.forName("io.ionic.sslpinning.SSLPinning");
            return (Boolean) cls.getDeclaredMethod("isDomainExcluded", new Class[]{Bridge.class, URL.class}).invoke(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), new Object[]{bridge, url});
        } catch (Exception unused) {
            return false;
        }
    }
}
