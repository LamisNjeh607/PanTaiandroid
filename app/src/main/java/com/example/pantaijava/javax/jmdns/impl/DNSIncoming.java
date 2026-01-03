package javax.jmdns.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSLabel;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import kotlin.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DNSIncoming extends DNSMessage {
    public static boolean USE_DOMAIN_NAME_FORMAT_FOR_SRV_TARGET = true;
    private static final char[] _nibbleToHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static Logger logger = LoggerFactory.getLogger(DNSIncoming.class.getName());
    private final MessageInputStream _messageInputStream;
    private final DatagramPacket _packet;
    private final long _receivedTime;
    private int _senderUDPPayload;

    public static class MessageInputStream extends ByteArrayInputStream {
        private static Logger logger1 = LoggerFactory.getLogger(MessageInputStream.class.getName());
        final Map<Integer, String> _names;

        public MessageInputStream(byte[] bArr, int i) {
            this(bArr, 0, i);
        }

        public MessageInputStream(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
            this._names = new HashMap();
        }

        public int readByte() {
            return read();
        }

        public int readUnsignedByte() {
            return read() & 255;
        }

        public int readUnsignedShort() {
            return (readUnsignedByte() << 8) | readUnsignedByte();
        }

        public int readInt() {
            return (readUnsignedShort() << 16) | readUnsignedShort();
        }

        public byte[] readBytes(int i) {
            byte[] bArr = new byte[i];
            read(bArr, 0, i);
            return bArr;
        }

        public String readUTF(int i) {
            int i2;
            int readUnsignedByte;
            StringBuilder sb = new StringBuilder(i);
            int i3 = 0;
            while (i3 < i) {
                int readUnsignedByte2 = readUnsignedByte();
                switch (readUnsignedByte2 >> 4) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        break;
                    case 12:
                    case 13:
                        i2 = (readUnsignedByte2 & 31) << 6;
                        readUnsignedByte = readUnsignedByte() & 63;
                        break;
                    case 14:
                        readUnsignedByte2 = ((readUnsignedByte2 & 15) << 12) | ((readUnsignedByte() & 63) << 6) | (readUnsignedByte() & 63);
                        i3 += 2;
                        continue;
                    default:
                        i2 = (readUnsignedByte2 & 63) << 4;
                        readUnsignedByte = readUnsignedByte() & 15;
                        break;
                }
                readUnsignedByte2 = i2 | readUnsignedByte;
                i3++;
                sb.append((char) readUnsignedByte2);
                i3++;
            }
            return sb.toString();
        }

        /* access modifiers changed from: protected */
        public synchronized int peek() {
            return this.pos < this.count ? this.buf[this.pos] & UByte.MAX_VALUE : -1;
        }

        public String readName() {
            HashMap hashMap = new HashMap();
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            while (!z) {
                int readUnsignedByte = readUnsignedByte();
                if (readUnsignedByte == 0) {
                    break;
                }
                int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSLabel[DNSLabel.labelForByte(readUnsignedByte).ordinal()];
                if (i == 1) {
                    int i2 = this.pos - 1;
                    String str = readUTF(readUnsignedByte) + ".";
                    sb.append(str);
                    for (StringBuilder append : hashMap.values()) {
                        append.append(str);
                    }
                    hashMap.put(Integer.valueOf(i2), new StringBuilder(str));
                } else if (i == 2) {
                    int labelValue = (DNSLabel.labelValue(readUnsignedByte) << 8) | readUnsignedByte();
                    String str2 = this._names.get(Integer.valueOf(labelValue));
                    if (str2 == null) {
                        logger1.warn("Bad domain name: possible circular name detected. Bad offset: 0x{} at 0x{}", (Object) Integer.toHexString(labelValue), (Object) Integer.toHexString(this.pos - 2));
                        str2 = "";
                    }
                    sb.append(str2);
                    for (StringBuilder append2 : hashMap.values()) {
                        append2.append(str2);
                    }
                    z = true;
                } else if (i != 3) {
                    logger1.warn("Unsupported DNS label type: '{}'", (Object) Integer.toHexString(readUnsignedByte & 192));
                } else {
                    logger1.debug("Extended label are not currently supported.");
                }
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                this._names.put((Integer) entry.getKey(), ((StringBuilder) entry.getValue()).toString());
            }
            return sb.toString();
        }

        public String readNonNameString() {
            return readUTF(readUnsignedByte());
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DNSIncoming(DatagramPacket datagramPacket) throws IOException {
        super(0, 0, datagramPacket.getPort() == DNSConstants.MDNS_PORT);
        this._packet = datagramPacket;
        InetAddress address = datagramPacket.getAddress();
        MessageInputStream messageInputStream = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this._messageInputStream = messageInputStream;
        this._receivedTime = System.currentTimeMillis();
        this._senderUDPPayload = DNSConstants.MAX_MSG_TYPICAL;
        try {
            setId(messageInputStream.readUnsignedShort());
            setFlags(messageInputStream.readUnsignedShort());
            if (getOperationCode() <= 0) {
                int readUnsignedShort = messageInputStream.readUnsignedShort();
                int readUnsignedShort2 = messageInputStream.readUnsignedShort();
                int readUnsignedShort3 = messageInputStream.readUnsignedShort();
                int readUnsignedShort4 = messageInputStream.readUnsignedShort();
                logger.debug("DNSIncoming() questions:{} answers:{} authorities:{} additionals:{}", Integer.valueOf(readUnsignedShort), Integer.valueOf(readUnsignedShort2), Integer.valueOf(readUnsignedShort3), Integer.valueOf(readUnsignedShort4));
                if ((readUnsignedShort * 5) + ((readUnsignedShort2 + readUnsignedShort3 + readUnsignedShort4) * 11) <= datagramPacket.getLength()) {
                    if (readUnsignedShort > 0) {
                        for (int i = 0; i < readUnsignedShort; i++) {
                            this._questions.add(readQuestion());
                        }
                    }
                    if (readUnsignedShort2 > 0) {
                        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
                            DNSRecord readAnswer = readAnswer(address);
                            if (readAnswer != null) {
                                this._answers.add(readAnswer);
                            }
                        }
                    }
                    if (readUnsignedShort3 > 0) {
                        for (int i3 = 0; i3 < readUnsignedShort3; i3++) {
                            DNSRecord readAnswer2 = readAnswer(address);
                            if (readAnswer2 != null) {
                                this._authoritativeAnswers.add(readAnswer2);
                            }
                        }
                    }
                    if (readUnsignedShort4 > 0) {
                        for (int i4 = 0; i4 < readUnsignedShort4; i4++) {
                            DNSRecord readAnswer3 = readAnswer(address);
                            if (readAnswer3 != null) {
                                this._additionals.add(readAnswer3);
                            }
                        }
                    }
                    if (this._messageInputStream.available() <= 0) {
                        try {
                            this._messageInputStream.close();
                        } catch (Exception unused) {
                            logger.warn("MessageInputStream close error");
                        }
                    } else {
                        throw new IOException("Received a message with the wrong length.");
                    }
                } else {
                    throw new IOException("questions:" + readUnsignedShort + " answers:" + readUnsignedShort2 + " authorities:" + readUnsignedShort3 + " additionals:" + readUnsignedShort4);
                }
            } else {
                throw new IOException("Received a message with a non standard operation code. Currently unsupported in the specification.");
            }
        } catch (Exception e) {
            logger.warn("DNSIncoming() dump " + print(true) + "\n exception ", (Throwable) e);
            IOException iOException = new IOException("DNSIncoming corrupted message");
            iOException.initCause(e);
            throw iOException;
        } catch (Throwable th) {
            try {
                this._messageInputStream.close();
            } catch (Exception unused2) {
                logger.warn("MessageInputStream close error");
            }
            throw th;
        }
    }

    private DNSIncoming(int i, int i2, boolean z, DatagramPacket datagramPacket, long j) {
        super(i, i2, z);
        this._packet = datagramPacket;
        this._messageInputStream = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this._receivedTime = j;
    }

    public DNSIncoming clone() {
        DNSIncoming dNSIncoming = new DNSIncoming(getFlags(), getId(), isMulticast(), this._packet, this._receivedTime);
        dNSIncoming._senderUDPPayload = this._senderUDPPayload;
        dNSIncoming._questions.addAll(this._questions);
        dNSIncoming._answers.addAll(this._answers);
        dNSIncoming._authoritativeAnswers.addAll(this._authoritativeAnswers);
        dNSIncoming._additionals.addAll(this._additionals);
        return dNSIncoming;
    }

    private DNSQuestion readQuestion() {
        String readName = this._messageInputStream.readName();
        DNSRecordType typeForIndex = DNSRecordType.typeForIndex(this._messageInputStream.readUnsignedShort());
        if (typeForIndex == DNSRecordType.TYPE_IGNORE) {
            logger.warn("Could not find record type: {}", (Object) print(true));
        }
        int readUnsignedShort = this._messageInputStream.readUnsignedShort();
        DNSRecordClass classForIndex = DNSRecordClass.classForIndex(readUnsignedShort);
        return DNSQuestion.newQuestion(readName, typeForIndex, classForIndex, classForIndex.isUnique(readUnsignedShort));
    }

    /* JADX WARNING: type inference failed for: r10v1, types: [javax.jmdns.impl.DNSRecord] */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: type inference failed for: r10v8 */
    /* JADX WARNING: type inference failed for: r2v45, types: [javax.jmdns.impl.DNSRecord$IPv4Address] */
    /* JADX WARNING: type inference failed for: r2v46, types: [javax.jmdns.impl.DNSRecord$IPv6Address] */
    /* JADX WARNING: type inference failed for: r2v47, types: [javax.jmdns.impl.DNSRecord$Pointer] */
    /* JADX WARNING: type inference failed for: r2v48, types: [javax.jmdns.impl.DNSRecord$Text] */
    /* JADX WARNING: type inference failed for: r2v49, types: [javax.jmdns.impl.DNSRecord$HostInformation] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0089 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.jmdns.impl.DNSRecord readAnswer(java.net.InetAddress r25) {
        /*
            r24 = this;
            r0 = r24
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            java.lang.String r3 = r1.readName()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            int r1 = r1.readUnsignedShort()
            javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.typeForIndex(r1)
            javax.jmdns.impl.constants.DNSRecordType r2 = javax.jmdns.impl.constants.DNSRecordType.TYPE_IGNORE
            r4 = 1
            if (r1 != r2) goto L_0x0022
            org.slf4j.Logger r2 = logger
            java.lang.String r5 = "Could not find record type. domain: {}\n{}"
            java.lang.String r6 = r0.print(r4)
            r2.warn((java.lang.String) r5, (java.lang.Object) r3, (java.lang.Object) r6)
        L_0x0022:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            int r2 = r2.readUnsignedShort()
            javax.jmdns.impl.constants.DNSRecordType r5 = javax.jmdns.impl.constants.DNSRecordType.TYPE_OPT
            if (r1 != r5) goto L_0x002f
            javax.jmdns.impl.constants.DNSRecordClass r5 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_UNKNOWN
            goto L_0x0033
        L_0x002f:
            javax.jmdns.impl.constants.DNSRecordClass r5 = javax.jmdns.impl.constants.DNSRecordClass.classForIndex(r2)
        L_0x0033:
            javax.jmdns.impl.constants.DNSRecordClass r6 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_UNKNOWN
            if (r5 != r6) goto L_0x004a
            javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_OPT
            if (r1 == r6) goto L_0x004a
            org.slf4j.Logger r6 = logger
            java.lang.String r7 = r0.print(r4)
            java.lang.Object[] r7 = new java.lang.Object[]{r3, r1, r7}
            java.lang.String r8 = "Could not find record class. domain: {} type: {}\n{}"
            r6.warn((java.lang.String) r8, (java.lang.Object[]) r7)
        L_0x004a:
            boolean r6 = r5.isUnique(r2)
            javax.jmdns.impl.DNSIncoming$MessageInputStream r7 = r0._messageInputStream
            int r7 = r7.readInt()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r8 = r0._messageInputStream
            int r8 = r8.readUnsignedShort()
            int[] r9 = javax.jmdns.impl.DNSIncoming.AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType
            int r10 = r1.ordinal()
            r9 = r9[r10]
            java.lang.String r11 = ""
            r12 = 0
            switch(r9) {
                case 1: goto L_0x02de;
                case 2: goto L_0x02cd;
                case 3: goto L_0x02ae;
                case 4: goto L_0x02ae;
                case 5: goto L_0x029d;
                case 6: goto L_0x026c;
                case 7: goto L_0x0231;
                case 8: goto L_0x0077;
                default: goto L_0x0068;
            }
        L_0x0068:
            org.slf4j.Logger r2 = logger
            java.lang.String r3 = "DNSIncoming() unknown type: {}"
            r2.debug((java.lang.String) r3, (java.lang.Object) r1)
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            long r2 = (long) r8
            r1.skip(r2)
            goto L_0x02ef
        L_0x0077:
            int r1 = r24.getFlags()
            javax.jmdns.impl.constants.DNSResultCode r1 = javax.jmdns.impl.constants.DNSResultCode.resultCodeForFlags(r1, r7)
            r3 = 16711680(0xff0000, float:2.3418052E-38)
            r3 = r3 & r7
            r5 = 16
            int r3 = r3 >> r5
            if (r3 != 0) goto L_0x0224
            r0._senderUDPPayload = r2
        L_0x0089:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            int r1 = r1.available()
            if (r1 <= 0) goto L_0x02ef
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            int r1 = r1.available()
            java.lang.String r2 = "There was a problem reading the OPT record. Ignoring."
            r3 = 2
            if (r1 < r3) goto L_0x021d
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            int r1 = r1.readUnsignedShort()
            javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.resultCodeForFlags(r1)
            javax.jmdns.impl.DNSIncoming$MessageInputStream r7 = r0._messageInputStream
            int r7 = r7.available()
            if (r7 < r3) goto L_0x0216
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            int r2 = r2.readUnsignedShort()
            byte[] r7 = new byte[r12]
            javax.jmdns.impl.DNSIncoming$MessageInputStream r8 = r0._messageInputStream
            int r8 = r8.available()
            if (r8 < r2) goto L_0x00c4
            javax.jmdns.impl.DNSIncoming$MessageInputStream r7 = r0._messageInputStream
            byte[] r7 = r7.readBytes(r2)
        L_0x00c4:
            int[] r2 = javax.jmdns.impl.DNSIncoming.AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSOptionCode
            int r8 = r6.ordinal()
            r2 = r2[r8]
            r8 = 5
            r9 = 4
            r13 = 3
            if (r2 == r4) goto L_0x0119
            if (r2 == r3) goto L_0x0104
            if (r2 == r13) goto L_0x0104
            if (r2 == r9) goto L_0x0104
            if (r2 == r8) goto L_0x00da
            goto L_0x0089
        L_0x00da:
            r2 = 65001(0xfde9, float:9.1086E-41)
            if (r1 < r2) goto L_0x00f4
            r2 = 65534(0xfffe, float:9.1833E-41)
            if (r1 > r2) goto L_0x00f4
            org.slf4j.Logger r2 = logger
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = r0._hexString(r7)
            java.lang.String r6 = "There was an OPT answer using an experimental/local option code: {} data: {}"
            r2.debug((java.lang.String) r6, (java.lang.Object) r1, (java.lang.Object) r3)
            goto L_0x0089
        L_0x00f4:
            org.slf4j.Logger r2 = logger
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = r0._hexString(r7)
            java.lang.String r6 = "There was an OPT answer. Not currently handled. Option code: {} data: {}"
            r2.warn((java.lang.String) r6, (java.lang.Object) r1, (java.lang.Object) r3)
            goto L_0x0089
        L_0x0104:
            org.slf4j.Logger r1 = logger
            boolean r1 = r1.isDebugEnabled()
            if (r1 == 0) goto L_0x0089
            org.slf4j.Logger r1 = logger
            java.lang.String r2 = "There was an OPT answer. Option code: {} data: {}"
            java.lang.String r3 = r0._hexString(r7)
            r1.debug((java.lang.String) r2, (java.lang.Object) r6, (java.lang.Object) r3)
            goto L_0x0089
        L_0x0119:
            byte r1 = r7[r12]     // Catch:{ Exception -> 0x01bd }
            byte r2 = r7[r4]     // Catch:{ Exception -> 0x01bb }
            r6 = 6
            byte[] r14 = new byte[r6]     // Catch:{ Exception -> 0x01bf }
            byte r15 = r7[r3]     // Catch:{ Exception -> 0x01bf }
            r14[r12] = r15     // Catch:{ Exception -> 0x01bf }
            byte r15 = r7[r13]     // Catch:{ Exception -> 0x01bf }
            r14[r4] = r15     // Catch:{ Exception -> 0x01bf }
            byte r15 = r7[r9]     // Catch:{ Exception -> 0x01bf }
            r14[r3] = r15     // Catch:{ Exception -> 0x01bf }
            byte r15 = r7[r8]     // Catch:{ Exception -> 0x01bf }
            r14[r13] = r15     // Catch:{ Exception -> 0x01bf }
            byte r15 = r7[r6]     // Catch:{ Exception -> 0x01bf }
            r14[r9] = r15     // Catch:{ Exception -> 0x01bf }
            r15 = 7
            byte r16 = r7[r15]     // Catch:{ Exception -> 0x01bf }
            r14[r8] = r16     // Catch:{ Exception -> 0x01bf }
            int r10 = r7.length     // Catch:{ Exception -> 0x01b8 }
            r15 = 8
            if (r10 <= r15) goto L_0x0163
            byte[] r10 = new byte[r6]     // Catch:{ Exception -> 0x01b8 }
            byte r18 = r7[r15]     // Catch:{ Exception -> 0x01b8 }
            r10[r12] = r18     // Catch:{ Exception -> 0x01b8 }
            r18 = 9
            byte r18 = r7[r18]     // Catch:{ Exception -> 0x01b8 }
            r10[r4] = r18     // Catch:{ Exception -> 0x01b8 }
            r18 = 10
            byte r18 = r7[r18]     // Catch:{ Exception -> 0x01b8 }
            r10[r3] = r18     // Catch:{ Exception -> 0x01b8 }
            r18 = 11
            byte r18 = r7[r18]     // Catch:{ Exception -> 0x01b8 }
            r10[r13] = r18     // Catch:{ Exception -> 0x01b8 }
            r18 = 12
            byte r18 = r7[r18]     // Catch:{ Exception -> 0x01b8 }
            r10[r9] = r18     // Catch:{ Exception -> 0x01b8 }
            r18 = 13
            byte r18 = r7[r18]     // Catch:{ Exception -> 0x01b8 }
            r10[r8] = r18     // Catch:{ Exception -> 0x01b8 }
            goto L_0x0164
        L_0x0163:
            r10 = r14
        L_0x0164:
            int r6 = r7.length     // Catch:{ Exception -> 0x01b9 }
            r19 = 17
            r20 = 15
            r21 = 14
            r8 = 18
            if (r6 != r8) goto L_0x0182
            byte[] r6 = new byte[r9]     // Catch:{ Exception -> 0x01b9 }
            byte r23 = r7[r21]     // Catch:{ Exception -> 0x01b9 }
            r6[r12] = r23     // Catch:{ Exception -> 0x01b9 }
            byte r23 = r7[r20]     // Catch:{ Exception -> 0x01b9 }
            r6[r4] = r23     // Catch:{ Exception -> 0x01b9 }
            byte r23 = r7[r5]     // Catch:{ Exception -> 0x01b9 }
            r6[r3] = r23     // Catch:{ Exception -> 0x01b9 }
            byte r23 = r7[r19]     // Catch:{ Exception -> 0x01b9 }
            r6[r13] = r23     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0183
        L_0x0182:
            r6 = 0
        L_0x0183:
            int r9 = r7.length     // Catch:{ Exception -> 0x01c2 }
            r8 = 22
            if (r9 != r8) goto L_0x01cd
            byte[] r8 = new byte[r15]     // Catch:{ Exception -> 0x01c2 }
            byte r9 = r7[r21]     // Catch:{ Exception -> 0x01c2 }
            r8[r12] = r9     // Catch:{ Exception -> 0x01c2 }
            byte r9 = r7[r20]     // Catch:{ Exception -> 0x01c2 }
            r8[r4] = r9     // Catch:{ Exception -> 0x01c2 }
            byte r9 = r7[r5]     // Catch:{ Exception -> 0x01c2 }
            r8[r3] = r9     // Catch:{ Exception -> 0x01c2 }
            byte r3 = r7[r19]     // Catch:{ Exception -> 0x01c2 }
            r8[r13] = r3     // Catch:{ Exception -> 0x01c2 }
            r3 = 18
            byte r3 = r7[r3]     // Catch:{ Exception -> 0x01c2 }
            r9 = 4
            r8[r9] = r3     // Catch:{ Exception -> 0x01c2 }
            r3 = 19
            byte r3 = r7[r3]     // Catch:{ Exception -> 0x01c2 }
            r9 = 5
            r8[r9] = r3     // Catch:{ Exception -> 0x01c2 }
            r3 = 20
            byte r3 = r7[r3]     // Catch:{ Exception -> 0x01c2 }
            r9 = 6
            r8[r9] = r3     // Catch:{ Exception -> 0x01c2 }
            r3 = 21
            byte r3 = r7[r3]     // Catch:{ Exception -> 0x01c2 }
            r9 = 7
            r8[r9] = r3     // Catch:{ Exception -> 0x01c2 }
            r6 = r8
            goto L_0x01cd
        L_0x01b8:
            r10 = r14
        L_0x01b9:
            r6 = 0
            goto L_0x01c2
        L_0x01bb:
            r2 = r12
            goto L_0x01bf
        L_0x01bd:
            r1 = r12
            r2 = r1
        L_0x01bf:
            r6 = 0
            r10 = 0
            r14 = 0
        L_0x01c2:
            org.slf4j.Logger r3 = logger
            java.lang.String r8 = "Malformed OPT answer. Option code: Owner data: {}"
            java.lang.String r7 = r0._hexString(r7)
            r3.warn((java.lang.String) r8, (java.lang.Object) r7)
        L_0x01cd:
            org.slf4j.Logger r3 = logger
            boolean r3 = r3.isDebugEnabled()
            if (r3 == 0) goto L_0x0089
            org.slf4j.Logger r3 = logger
            java.lang.Integer r17 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r18 = java.lang.Integer.valueOf(r2)
            java.lang.String r19 = r0._hexString(r14)
            if (r10 == r14) goto L_0x01ea
            java.lang.String r1 = " wakeup MAC address: "
            r20 = r1
            goto L_0x01ec
        L_0x01ea:
            r20 = r11
        L_0x01ec:
            if (r10 == r14) goto L_0x01f5
            java.lang.String r1 = r0._hexString(r10)
            r21 = r1
            goto L_0x01f7
        L_0x01f5:
            r21 = r11
        L_0x01f7:
            if (r6 == 0) goto L_0x01fe
            java.lang.String r1 = " password: "
            r22 = r1
            goto L_0x0200
        L_0x01fe:
            r22 = r11
        L_0x0200:
            if (r6 == 0) goto L_0x0209
            java.lang.String r1 = r0._hexString(r6)
            r23 = r1
            goto L_0x020b
        L_0x0209:
            r23 = r11
        L_0x020b:
            java.lang.Object[] r1 = new java.lang.Object[]{r17, r18, r19, r20, r21, r22, r23}
            java.lang.String r2 = "Unhandled Owner OPT version: {} sequence: {} MAC address: {} {}{} {}{}"
            r3.debug((java.lang.String) r2, (java.lang.Object[]) r1)
            goto L_0x0089
        L_0x0216:
            org.slf4j.Logger r1 = logger
            r1.warn(r2)
            goto L_0x02ef
        L_0x021d:
            org.slf4j.Logger r1 = logger
            r1.warn(r2)
            goto L_0x02ef
        L_0x0224:
            org.slf4j.Logger r2 = logger
            java.lang.String r4 = "There was an OPT answer. Wrong version number: {} result code: {}"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.warn((java.lang.String) r4, (java.lang.Object) r3, (java.lang.Object) r1)
            goto L_0x02ef
        L_0x0231:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            java.lang.String r2 = r2.readUTF(r8)
            r1.append(r2)
            java.lang.String r2 = " "
            int r2 = r1.indexOf(r2)
            if (r2 <= 0) goto L_0x024c
            java.lang.String r8 = r1.substring(r12, r2)
            goto L_0x0250
        L_0x024c:
            java.lang.String r8 = r1.toString()
        L_0x0250:
            java.lang.String r8 = r8.trim()
            if (r2 <= 0) goto L_0x025b
            int r2 = r2 + r4
            java.lang.String r11 = r1.substring(r2)
        L_0x025b:
            java.lang.String r1 = r11.trim()
            javax.jmdns.impl.DNSRecord$HostInformation r10 = new javax.jmdns.impl.DNSRecord$HostInformation
            r2 = r10
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            goto L_0x02f0
        L_0x026c:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            int r1 = r1.readUnsignedShort()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            int r8 = r2.readUnsignedShort()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            int r9 = r2.readUnsignedShort()
            boolean r2 = USE_DOMAIN_NAME_FORMAT_FOR_SRV_TARGET
            if (r2 == 0) goto L_0x0289
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            java.lang.String r2 = r2.readName()
            goto L_0x028f
        L_0x0289:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0._messageInputStream
            java.lang.String r2 = r2.readNonNameString()
        L_0x028f:
            r10 = r2
            javax.jmdns.impl.DNSRecord$Service r11 = new javax.jmdns.impl.DNSRecord$Service
            r2 = r11
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            r10 = r11
            goto L_0x02f0
        L_0x029d:
            javax.jmdns.impl.DNSRecord$Text r10 = new javax.jmdns.impl.DNSRecord$Text
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            byte[] r1 = r1.readBytes(r8)
            r2 = r10
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x02f0
        L_0x02ae:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            java.lang.String r1 = r1.readName()
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x02c5
            javax.jmdns.impl.DNSRecord$Pointer r10 = new javax.jmdns.impl.DNSRecord$Pointer
            r2 = r10
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x02f0
        L_0x02c5:
            org.slf4j.Logger r1 = logger
            java.lang.String r2 = "PTR record of class: {}, there was a problem reading the service name of the answer for domain: {}"
            r1.warn((java.lang.String) r2, (java.lang.Object) r5, (java.lang.Object) r3)
            goto L_0x02ef
        L_0x02cd:
            javax.jmdns.impl.DNSRecord$IPv6Address r10 = new javax.jmdns.impl.DNSRecord$IPv6Address
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            byte[] r1 = r1.readBytes(r8)
            r2 = r10
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>((java.lang.String) r3, (javax.jmdns.impl.constants.DNSRecordClass) r4, (boolean) r5, (int) r6, (byte[]) r7)
            goto L_0x02f0
        L_0x02de:
            javax.jmdns.impl.DNSRecord$IPv4Address r10 = new javax.jmdns.impl.DNSRecord$IPv4Address
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0._messageInputStream
            byte[] r1 = r1.readBytes(r8)
            r2 = r10
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>((java.lang.String) r3, (javax.jmdns.impl.constants.DNSRecordClass) r4, (boolean) r5, (int) r6, (byte[]) r7)
            goto L_0x02f0
        L_0x02ef:
            r10 = 0
        L_0x02f0:
            if (r10 == 0) goto L_0x02f7
            r1 = r25
            r10.setRecordSource(r1)
        L_0x02f7:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSIncoming.readAnswer(java.net.InetAddress):javax.jmdns.impl.DNSRecord");
    }

    /* renamed from: javax.jmdns.impl.DNSIncoming$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSLabel;
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode;
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(41:0|1|2|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(42:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00be */
        static {
            /*
                javax.jmdns.impl.constants.DNSRecordType[] r0 = javax.jmdns.impl.constants.DNSRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = r0
                r1 = 1
                javax.jmdns.impl.constants.DNSRecordType r2 = javax.jmdns.impl.constants.DNSRecordType.TYPE_A     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.constants.DNSRecordType r3 = javax.jmdns.impl.constants.DNSRecordType.TYPE_AAAA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.jmdns.impl.constants.DNSRecordType r4 = javax.jmdns.impl.constants.DNSRecordType.TYPE_CNAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.jmdns.impl.constants.DNSRecordType r5 = javax.jmdns.impl.constants.DNSRecordType.TYPE_PTR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_TXT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0049 }
                javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_SRV     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r7 = 6
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0054 }
                javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_HINFO     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7 = 7
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0060 }
                javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_OPT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r7 = 8
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                javax.jmdns.impl.constants.DNSOptionCode[] r5 = javax.jmdns.impl.constants.DNSOptionCode.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode = r5
                javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.Owner     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode     // Catch:{ NoSuchFieldError -> 0x007b }
                javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.LLQ     // Catch:{ NoSuchFieldError -> 0x007b }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode     // Catch:{ NoSuchFieldError -> 0x0085 }
                javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.NSID     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode     // Catch:{ NoSuchFieldError -> 0x008f }
                javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.UL     // Catch:{ NoSuchFieldError -> 0x008f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r5[r6] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r5 = $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode     // Catch:{ NoSuchFieldError -> 0x0099 }
                javax.jmdns.impl.constants.DNSOptionCode r6 = javax.jmdns.impl.constants.DNSOptionCode.Unknown     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                javax.jmdns.impl.constants.DNSLabel[] r4 = javax.jmdns.impl.constants.DNSLabel.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$javax$jmdns$impl$constants$DNSLabel = r4
                javax.jmdns.impl.constants.DNSLabel r5 = javax.jmdns.impl.constants.DNSLabel.Standard     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                int[] r1 = $SwitchMap$javax$jmdns$impl$constants$DNSLabel     // Catch:{ NoSuchFieldError -> 0x00b4 }
                javax.jmdns.impl.constants.DNSLabel r4 = javax.jmdns.impl.constants.DNSLabel.Compressed     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSLabel     // Catch:{ NoSuchFieldError -> 0x00be }
                javax.jmdns.impl.constants.DNSLabel r1 = javax.jmdns.impl.constants.DNSLabel.Extended     // Catch:{ NoSuchFieldError -> 0x00be }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00be }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00be }
            L_0x00be:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSLabel     // Catch:{ NoSuchFieldError -> 0x00c8 }
                javax.jmdns.impl.constants.DNSLabel r1 = javax.jmdns.impl.constants.DNSLabel.Unknown     // Catch:{ NoSuchFieldError -> 0x00c8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c8 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00c8 }
            L_0x00c8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSIncoming.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public String print(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(print());
        if (z) {
            int length = this._packet.getLength();
            byte[] bArr = new byte[length];
            System.arraycopy(this._packet.getData(), 0, bArr, 0, length);
            sb.append(print(bArr));
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isQuery() ? "dns[query," : "dns[response,");
        if (this._packet.getAddress() != null) {
            sb.append(this._packet.getAddress().getHostAddress());
        }
        sb.append(':');
        sb.append(this._packet.getPort());
        sb.append(", length=");
        sb.append(this._packet.getLength());
        sb.append(", id=0x");
        sb.append(Integer.toHexString(getId()));
        if (getFlags() != 0) {
            sb.append(", flags=0x");
            sb.append(Integer.toHexString(getFlags()));
            if ((getFlags() & 32768) != 0) {
                sb.append(":r");
            }
            if ((getFlags() & 1024) != 0) {
                sb.append(":aa");
            }
            if ((getFlags() & 512) != 0) {
                sb.append(":tc");
            }
        }
        if (getNumberOfQuestions() > 0) {
            sb.append(", questions=");
            sb.append(getNumberOfQuestions());
        }
        if (getNumberOfAnswers() > 0) {
            sb.append(", answers=");
            sb.append(getNumberOfAnswers());
        }
        if (getNumberOfAuthorities() > 0) {
            sb.append(", authorities=");
            sb.append(getNumberOfAuthorities());
        }
        if (getNumberOfAdditionals() > 0) {
            sb.append(", additionals=");
            sb.append(getNumberOfAdditionals());
        }
        if (getNumberOfQuestions() > 0) {
            sb.append("\nquestions:");
            for (DNSQuestion append : this._questions) {
                sb.append("\n\t");
                sb.append(append);
            }
        }
        if (getNumberOfAnswers() > 0) {
            sb.append("\nanswers:");
            for (DNSRecord append2 : this._answers) {
                sb.append("\n\t");
                sb.append(append2);
            }
        }
        if (getNumberOfAuthorities() > 0) {
            sb.append("\nauthorities:");
            for (DNSRecord append3 : this._authoritativeAnswers) {
                sb.append("\n\t");
                sb.append(append3);
            }
        }
        if (getNumberOfAdditionals() > 0) {
            sb.append("\nadditionals:");
            for (DNSRecord append4 : this._additionals) {
                sb.append("\n\t");
                sb.append(append4);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void append(DNSIncoming dNSIncoming) {
        if (!isQuery() || !isTruncated() || !dNSIncoming.isQuery()) {
            throw new IllegalArgumentException();
        }
        this._questions.addAll(dNSIncoming.getQuestions());
        this._answers.addAll(dNSIncoming.getAnswers());
        this._authoritativeAnswers.addAll(dNSIncoming.getAuthorities());
        this._additionals.addAll(dNSIncoming.getAdditionals());
    }

    public int elapseSinceArrival() {
        return (int) (System.currentTimeMillis() - this._receivedTime);
    }

    public int getSenderUDPPayload() {
        return this._senderUDPPayload;
    }

    private String _hexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & UByte.MAX_VALUE;
            char[] cArr = _nibbleToHex;
            sb.append(cArr[b2 / 16]);
            sb.append(cArr[b2 % 16]);
        }
        return sb.toString();
    }
}
