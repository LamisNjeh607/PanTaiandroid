package javax.jmdns.impl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import kotlin.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteWrangler {
    public static final String CHARSET_NAME = "UTF-8";
    private static final Charset CHARSET_UTF_8 = Charset.forName(CHARSET_NAME);
    public static final byte[] EMPTY_TXT = {0};
    public static final int MAX_DATA_LENGTH = 256;
    public static final int MAX_VALUE_LENGTH = 255;
    public static final byte[] NO_VALUE = new byte[0];
    private static Logger logger = LoggerFactory.getLogger(ByteWrangler.class.getName());

    public static void writeUTF(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes(CHARSET_UTF_8));
    }

    public static String readUTF(byte[] bArr) {
        return readUTF(bArr, 0, bArr.length);
    }

    public static String readUTF(byte[] bArr, int i, int i2) {
        return new String(bArr, i, i2, CHARSET_UTF_8);
    }

    public static void readProperties(Map<String, byte[]> map, byte[] bArr) throws Exception {
        int i;
        if (bArr != null) {
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = i2 + 1;
                byte b = bArr[i2] & UByte.MAX_VALUE;
                if (b == 0 || (i = i3 + b) > bArr.length) {
                    map.clear();
                    return;
                }
                int i4 = 0;
                while (i4 < b && bArr[i3 + i4] != 61) {
                    i4++;
                }
                String readUTF = readUTF(bArr, i3, i4);
                if (readUTF == null) {
                    map.clear();
                    return;
                }
                if (i4 == b) {
                    map.put(readUTF, NO_VALUE);
                } else {
                    int i5 = i4 + 1;
                    int i6 = b - i5;
                    byte[] bArr2 = new byte[i6];
                    System.arraycopy(bArr, i3 + i5, bArr2, 0, i6);
                    map.put(readUTF, bArr2);
                }
                i2 = i;
            }
        }
    }

    public static byte[] textFromProperties(Map<String, ?> map) {
        byte[] bArr = null;
        if (map != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
                for (Map.Entry next : map.entrySet()) {
                    String str = (String) next.getKey();
                    Object value = next.getValue();
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(100);
                    writeUTF(byteArrayOutputStream2, str);
                    if (value != null) {
                        if (value instanceof String) {
                            byteArrayOutputStream2.write(61);
                            writeUTF(byteArrayOutputStream2, (String) value);
                        } else if (value instanceof byte[]) {
                            byte[] bArr2 = (byte[]) value;
                            if (bArr2.length > 0) {
                                byteArrayOutputStream2.write(61);
                                byteArrayOutputStream2.write(bArr2, 0, bArr2.length);
                            } else {
                                value = null;
                            }
                        } else {
                            throw new IllegalArgumentException("Invalid property value: " + value);
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    if (byteArray.length > 255) {
                        logger.warn("Cannot have individual values larger that 255 chars. Offending value: {}", (Object) str + (value == null ? "" : "=" + value));
                        return EMPTY_TXT;
                    }
                    byteArrayOutputStream.write((byte) byteArray.length);
                    byteArrayOutputStream.write(byteArray, 0, byteArray.length);
                }
                bArr = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("unexpected exception: " + e);
            }
        }
        return (bArr == null || bArr.length <= 0) ? EMPTY_TXT : bArr;
    }

    public static byte[] encodeText(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(100);
        writeUTF(byteArrayOutputStream2, str);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        if (byteArray.length > 255) {
            logger.warn("Cannot have individual values larger that 255 chars. Offending value: {}", (Object) str);
            return EMPTY_TXT;
        }
        byteArrayOutputStream.write((byte) byteArray.length);
        byteArrayOutputStream.write(byteArray, 0, byteArray.length);
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        return byteArray2.length > 0 ? byteArray2 : EMPTY_TXT;
    }
}
