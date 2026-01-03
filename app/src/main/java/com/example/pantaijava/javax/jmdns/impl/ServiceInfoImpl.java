package javax.jmdns.impl;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import javax.jmdns.impl.util.ByteWrangler;
import kotlin.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceInfoImpl extends ServiceInfo implements DNSListener, DNSStatefulObject {
    private static Logger logger = LoggerFactory.getLogger(ServiceInfoImpl.class.getName());
    private String _application;
    private Delegate _delegate;
    private String _domain;
    private final Set<Inet4Address> _ipv4Addresses;
    private final Set<Inet6Address> _ipv6Addresses;
    private transient String _key;
    private String _name;
    private boolean _needTextAnnouncing;
    private boolean _persistent;
    private int _port;
    private int _priority;
    private Map<String, byte[]> _props;
    private String _protocol;
    private String _server;
    private final ServiceInfoState _state;
    private String _subtype;
    private byte[] _text;
    private int _weight;

    public interface Delegate {
        void textValueUpdated(ServiceInfo serviceInfo, byte[] bArr);
    }

    private static final class ServiceInfoState extends DNSStatefulObject.DefaultImplementation {
        private static final long serialVersionUID = 1104131034952196820L;
        private final ServiceInfoImpl _info;

        public ServiceInfoState(ServiceInfoImpl serviceInfoImpl) {
            this._info = serviceInfoImpl;
        }

        /* access modifiers changed from: protected */
        public void setTask(DNSTask dNSTask) {
            super.setTask(dNSTask);
            if (this._task == null && this._info.needTextAnnouncing()) {
                lock();
                try {
                    if (this._task == null && this._info.needTextAnnouncing()) {
                        if (this._state.isAnnounced()) {
                            setState(DNSState.ANNOUNCING_1);
                            if (getDns() != null) {
                                getDns().startAnnouncer();
                            }
                        }
                        this._info.setNeedTextAnnouncing(false);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public void setDns(JmDNSImpl jmDNSImpl) {
            super.setDns(jmDNSImpl);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ServiceInfoImpl(java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, int r12, int r13, boolean r14, java.lang.String r15) {
        /*
            r7 = this;
            java.util.Map r1 = decodeQualifiedNameMap(r8, r9, r10)
            r6 = 0
            r8 = r6
            byte[] r8 = (byte[]) r8
            r0 = r7
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r0.<init>((java.util.Map<javax.jmdns.ServiceInfo.Fields, java.lang.String>) r1, (int) r2, (int) r3, (int) r4, (boolean) r5, (byte[]) r6)
            byte[] r8 = javax.jmdns.impl.util.ByteWrangler.encodeText(r15)     // Catch:{ IOException -> 0x0019 }
            r7._text = r8     // Catch:{ IOException -> 0x0019 }
            r7._server = r15
            return
        L_0x0019:
            r8 = move-exception
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Unexpected exception: "
            r10.<init>(r11)
            java.lang.StringBuilder r8 = r10.append(r8)
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.<init>(java.lang.String, java.lang.String, java.lang.String, int, int, int, boolean, java.lang.String):void");
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, Map<String, ?> map) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, ByteWrangler.textFromProperties(map));
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, byte[] bArr) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, bArr);
    }

    public ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, Map<String, ?> map2) {
        this(map, i, i2, i3, z, ByteWrangler.textFromProperties(map2));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, String str) {
        this(map, i, i2, i3, z, (byte[]) null);
        byte[] bArr = null;
        try {
            this._text = ByteWrangler.encodeText(str);
            this._server = str;
        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception: " + e);
        }
    }

    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, byte[] bArr) {
        Map<ServiceInfo.Fields, String> checkQualifiedNameMap = checkQualifiedNameMap(map);
        this._domain = checkQualifiedNameMap.get(ServiceInfo.Fields.Domain);
        this._protocol = checkQualifiedNameMap.get(ServiceInfo.Fields.Protocol);
        this._application = checkQualifiedNameMap.get(ServiceInfo.Fields.Application);
        this._name = checkQualifiedNameMap.get(ServiceInfo.Fields.Instance);
        this._subtype = checkQualifiedNameMap.get(ServiceInfo.Fields.Subtype);
        this._port = i;
        this._weight = i2;
        this._priority = i3;
        this._text = bArr;
        setNeedTextAnnouncing(false);
        this._state = new ServiceInfoState(this);
        this._persistent = z;
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
    }

    ServiceInfoImpl(ServiceInfo serviceInfo) {
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
        if (serviceInfo != null) {
            this._domain = serviceInfo.getDomain();
            this._protocol = serviceInfo.getProtocol();
            this._application = serviceInfo.getApplication();
            this._name = serviceInfo.getName();
            this._subtype = serviceInfo.getSubtype();
            this._port = serviceInfo.getPort();
            this._weight = serviceInfo.getWeight();
            this._priority = serviceInfo.getPriority();
            this._text = serviceInfo.getTextBytes();
            this._persistent = serviceInfo.isPersistent();
            for (Inet6Address add : serviceInfo.getInet6Addresses()) {
                this._ipv6Addresses.add(add);
            }
            for (Inet4Address add2 : serviceInfo.getInet4Addresses()) {
                this._ipv4Addresses.add(add2);
            }
        }
        this._state = new ServiceInfoState(this);
    }

    public static Map<ServiceInfo.Fields, String> decodeQualifiedNameMap(String str, String str2, String str3) {
        Map<ServiceInfo.Fields, String> decodeQualifiedNameMapForType = decodeQualifiedNameMapForType(str);
        decodeQualifiedNameMapForType.put(ServiceInfo.Fields.Instance, str2);
        decodeQualifiedNameMapForType.put(ServiceInfo.Fields.Subtype, str3);
        return checkQualifiedNameMap(decodeQualifiedNameMapForType);
    }

    public static Map<ServiceInfo.Fields, String> decodeQualifiedNameMapForType(String str) {
        String str2;
        String str3;
        String str4;
        String substring;
        String str5;
        String str6;
        String str7;
        int indexOf;
        String lowerCase = str.toLowerCase();
        String str8 = "";
        if (lowerCase.contains("in-addr.arpa") || lowerCase.contains("ip6.arpa")) {
            int indexOf2 = lowerCase.contains("in-addr.arpa") ? lowerCase.indexOf("in-addr.arpa") : lowerCase.indexOf("ip6.arpa");
            str4 = removeSeparators(str.substring(0, indexOf2));
            substring = str.substring(indexOf2);
        } else if (lowerCase.contains("_") || !lowerCase.contains(".")) {
            if ((!lowerCase.startsWith("_") || lowerCase.startsWith("_services")) && (indexOf = lowerCase.indexOf("._")) > 0) {
                str6 = str.substring(0, indexOf);
                int i = indexOf + 1;
                if (i < lowerCase.length()) {
                    str5 = lowerCase.substring(i);
                    str = str.substring(i);
                } else {
                    str5 = lowerCase;
                }
            } else {
                str5 = lowerCase;
                str6 = str8;
            }
            int lastIndexOf = str5.lastIndexOf("._");
            if (lastIndexOf > 0) {
                int i2 = lastIndexOf + 2;
                str7 = str.substring(i2, str5.indexOf(46, i2));
            } else {
                str7 = str8;
            }
            if (str7.length() > 0) {
                int indexOf3 = str5.indexOf("_" + str7.toLowerCase() + ".");
                int length = str7.length() + indexOf3 + 2;
                int length2 = str5.length() - (str5.endsWith(".") ? 1 : 0);
                String substring2 = length2 > length ? str.substring(length, length2) : str8;
                if (indexOf3 > 0) {
                    lowerCase = str.substring(0, indexOf3 - 1);
                    str2 = substring2;
                } else {
                    str2 = substring2;
                    lowerCase = str8;
                }
            } else {
                str2 = str8;
            }
            int indexOf4 = lowerCase.toLowerCase().indexOf("._sub");
            if (indexOf4 > 0) {
                str8 = removeSeparators(lowerCase.substring(0, indexOf4));
                lowerCase = lowerCase.substring(indexOf4 + 5);
            }
            str4 = str6;
            String str9 = str8;
            str8 = str7;
            str3 = str9;
            HashMap hashMap = new HashMap(5);
            hashMap.put(ServiceInfo.Fields.Domain, removeSeparators(str2));
            hashMap.put(ServiceInfo.Fields.Protocol, str8);
            hashMap.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
            hashMap.put(ServiceInfo.Fields.Instance, str4);
            hashMap.put(ServiceInfo.Fields.Subtype, str3);
            return hashMap;
        } else {
            int indexOf5 = lowerCase.indexOf(46);
            str4 = removeSeparators(str.substring(0, indexOf5));
            substring = removeSeparators(str.substring(indexOf5));
        }
        lowerCase = str8;
        str3 = lowerCase;
        HashMap hashMap2 = new HashMap(5);
        hashMap2.put(ServiceInfo.Fields.Domain, removeSeparators(str2));
        hashMap2.put(ServiceInfo.Fields.Protocol, str8);
        hashMap2.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
        hashMap2.put(ServiceInfo.Fields.Instance, str4);
        hashMap2.put(ServiceInfo.Fields.Subtype, str3);
        return hashMap2;
    }

    protected static Map<ServiceInfo.Fields, String> checkQualifiedNameMap(Map<ServiceInfo.Fields, String> map) {
        HashMap hashMap = new HashMap(5);
        boolean containsKey = map.containsKey(ServiceInfo.Fields.Domain);
        String str = ImagesContract.LOCAL;
        String str2 = containsKey ? map.get(ServiceInfo.Fields.Domain) : str;
        if (!(str2 == null || str2.length() == 0)) {
            str = str2;
        }
        hashMap.put(ServiceInfo.Fields.Domain, removeSeparators(str));
        String str3 = "tcp";
        String str4 = map.containsKey(ServiceInfo.Fields.Protocol) ? map.get(ServiceInfo.Fields.Protocol) : str3;
        if (!(str4 == null || str4.length() == 0)) {
            str3 = str4;
        }
        hashMap.put(ServiceInfo.Fields.Protocol, removeSeparators(str3));
        String str5 = "";
        String str6 = map.containsKey(ServiceInfo.Fields.Application) ? map.get(ServiceInfo.Fields.Application) : str5;
        if (str6 == null || str6.length() == 0) {
            str6 = str5;
        }
        hashMap.put(ServiceInfo.Fields.Application, removeSeparators(str6));
        String str7 = map.containsKey(ServiceInfo.Fields.Instance) ? map.get(ServiceInfo.Fields.Instance) : str5;
        if (str7 == null || str7.length() == 0) {
            str7 = str5;
        }
        hashMap.put(ServiceInfo.Fields.Instance, removeSeparators(str7));
        String str8 = map.containsKey(ServiceInfo.Fields.Subtype) ? map.get(ServiceInfo.Fields.Subtype) : str5;
        if (!(str8 == null || str8.length() == 0)) {
            str5 = str8;
        }
        hashMap.put(ServiceInfo.Fields.Subtype, removeSeparators(str5));
        return hashMap;
    }

    private static String removeSeparators(String str) {
        if (str == null) {
            return "";
        }
        String trim = str.trim();
        if (trim.startsWith(".")) {
            trim = trim.substring(1);
        }
        if (trim.startsWith("_")) {
            trim = trim.substring(1);
        }
        return trim.endsWith(".") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public String getType() {
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        String str = "";
        StringBuilder append = new StringBuilder().append(application.length() > 0 ? "_" + application + "." : str);
        if (protocol.length() > 0) {
            str = "_" + protocol + ".";
        }
        return append.append(str).append(domain).append(".").toString();
    }

    public String getTypeWithSubtype() {
        String subtype = getSubtype();
        return (subtype.length() > 0 ? "_" + subtype + "._sub." : "") + getType();
    }

    public String getName() {
        String str = this._name;
        return str != null ? str : "";
    }

    public String getKey() {
        if (this._key == null) {
            this._key = getQualifiedName().toLowerCase();
        }
        return this._key;
    }

    /* access modifiers changed from: package-private */
    public void setName(String str) {
        this._name = str;
        this._key = null;
    }

    public String getQualifiedName() {
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        String name = getName();
        String str = "";
        StringBuilder append = new StringBuilder().append(name.length() > 0 ? name + "." : str).append(application.length() > 0 ? "_" + application + "." : str);
        if (protocol.length() > 0) {
            str = "_" + protocol + ".";
        }
        return append.append(str).append(domain).append(".").toString();
    }

    public String getServer() {
        String str = this._server;
        return str != null ? str : "";
    }

    /* access modifiers changed from: package-private */
    public void setServer(String str) {
        this._server = str;
    }

    @Deprecated
    public String getHostAddress() {
        String[] hostAddresses = getHostAddresses();
        return hostAddresses.length > 0 ? hostAddresses[0] : "";
    }

    public String[] getHostAddresses() {
        Inet4Address[] inet4Addresses = getInet4Addresses();
        Inet6Address[] inet6Addresses = getInet6Addresses();
        String[] strArr = new String[(inet4Addresses.length + inet6Addresses.length)];
        for (int i = 0; i < inet4Addresses.length; i++) {
            strArr[i] = inet4Addresses[i].getHostAddress();
        }
        for (int i2 = 0; i2 < inet6Addresses.length; i2++) {
            strArr[inet4Addresses.length + i2] = "[" + inet6Addresses[i2].getHostAddress() + "]";
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public void addAddress(Inet4Address inet4Address) {
        this._ipv4Addresses.add(inet4Address);
    }

    /* access modifiers changed from: package-private */
    public void addAddress(Inet6Address inet6Address) {
        this._ipv6Addresses.add(inet6Address);
    }

    @Deprecated
    public InetAddress getAddress() {
        return getInetAddress();
    }

    @Deprecated
    public InetAddress getInetAddress() {
        InetAddress[] inetAddresses = getInetAddresses();
        if (inetAddresses.length > 0) {
            return inetAddresses[0];
        }
        return null;
    }

    @Deprecated
    public Inet4Address getInet4Address() {
        Inet4Address[] inet4Addresses = getInet4Addresses();
        if (inet4Addresses.length > 0) {
            return inet4Addresses[0];
        }
        return null;
    }

    @Deprecated
    public Inet6Address getInet6Address() {
        Inet6Address[] inet6Addresses = getInet6Addresses();
        if (inet6Addresses.length > 0) {
            return inet6Addresses[0];
        }
        return null;
    }

    public InetAddress[] getInetAddresses() {
        ArrayList arrayList = new ArrayList(this._ipv4Addresses.size() + this._ipv6Addresses.size());
        arrayList.addAll(this._ipv4Addresses);
        arrayList.addAll(this._ipv6Addresses);
        return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
    }

    public Inet4Address[] getInet4Addresses() {
        Set<Inet4Address> set = this._ipv4Addresses;
        return (Inet4Address[]) set.toArray(new Inet4Address[set.size()]);
    }

    public Inet6Address[] getInet6Addresses() {
        Set<Inet6Address> set = this._ipv6Addresses;
        return (Inet6Address[]) set.toArray(new Inet6Address[set.size()]);
    }

    public int getPort() {
        return this._port;
    }

    public int getPriority() {
        return this._priority;
    }

    public int getWeight() {
        return this._weight;
    }

    public byte[] getTextBytes() {
        byte[] bArr = this._text;
        return (bArr == null || bArr.length <= 0) ? ByteWrangler.EMPTY_TXT : bArr;
    }

    @Deprecated
    public String getTextString() {
        Iterator<Map.Entry<String, byte[]>> it = getProperties().entrySet().iterator();
        if (!it.hasNext()) {
            return "";
        }
        Map.Entry next = it.next();
        byte[] bArr = (byte[]) next.getValue();
        if (bArr == null || bArr.length <= 0) {
            return (String) next.getKey();
        }
        return ((String) next.getKey()) + "=" + ByteWrangler.readUTF(bArr);
    }

    @Deprecated
    public String getURL() {
        return getURL("http");
    }

    public String[] getURLs() {
        return getURLs("http");
    }

    @Deprecated
    public String getURL(String str) {
        String[] uRLs = getURLs(str);
        return uRLs.length > 0 ? uRLs[0] : str + "://null:" + getPort();
    }

    public String[] getURLs(String str) {
        InetAddress[] inetAddresses = getInetAddresses();
        ArrayList arrayList = new ArrayList(inetAddresses.length);
        for (InetAddress inetAddress : inetAddresses) {
            String hostAddress = inetAddress.getHostAddress();
            if (inetAddress instanceof Inet6Address) {
                hostAddress = "[" + hostAddress + "]";
            }
            String str2 = str + "://" + hostAddress + ":" + getPort();
            String propertyString = getPropertyString("path");
            if (propertyString != null) {
                if (propertyString.indexOf("://") >= 0) {
                    str2 = propertyString;
                } else {
                    StringBuilder append = new StringBuilder().append(str2);
                    if (!propertyString.startsWith("/")) {
                        propertyString = "/" + propertyString;
                    }
                    str2 = append.append(propertyString).toString();
                }
            }
            arrayList.add(str2);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public synchronized byte[] getPropertyBytes(String str) {
        return getProperties().get(str);
    }

    public synchronized String getPropertyString(String str) {
        byte[] bArr = getProperties().get(str);
        if (bArr == null) {
            return null;
        }
        if (bArr == ByteWrangler.NO_VALUE) {
            return "true";
        }
        return ByteWrangler.readUTF(bArr, 0, bArr.length);
    }

    public Enumeration<String> getPropertyNames() {
        Map<String, byte[]> properties = getProperties();
        return new Vector(properties != null ? properties.keySet() : Collections.emptySet()).elements();
    }

    public String getApplication() {
        String str = this._application;
        return str != null ? str : "";
    }

    public String getDomain() {
        String str = this._domain;
        return str != null ? str : ImagesContract.LOCAL;
    }

    public String getProtocol() {
        String str = this._protocol;
        return str != null ? str : "tcp";
    }

    public String getSubtype() {
        String str = this._subtype;
        return str != null ? str : "";
    }

    public Map<ServiceInfo.Fields, String> getQualifiedNameMap() {
        HashMap hashMap = new HashMap(5);
        hashMap.put(ServiceInfo.Fields.Domain, getDomain());
        hashMap.put(ServiceInfo.Fields.Protocol, getProtocol());
        hashMap.put(ServiceInfo.Fields.Application, getApplication());
        hashMap.put(ServiceInfo.Fields.Instance, getName());
        hashMap.put(ServiceInfo.Fields.Subtype, getSubtype());
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public synchronized Map<String, byte[]> getProperties() {
        Map<String, byte[]> map;
        if (this._props == null && getTextBytes() != null) {
            Hashtable hashtable = new Hashtable();
            try {
                ByteWrangler.readProperties(hashtable, getTextBytes());
            } catch (Exception e) {
                logger.warn("Malformed TXT Field ", (Throwable) e);
            }
            this._props = hashtable;
        }
        map = this._props;
        if (map == null) {
            map = Collections.emptyMap();
        }
        return map;
    }

    public void updateRecord(DNSCache dNSCache, long j, DNSEntry dNSEntry) {
        boolean z;
        String str;
        if (!(dNSEntry instanceof DNSRecord)) {
            Logger logger2 = logger;
            if (dNSEntry == null) {
                str = "null";
            } else {
                str = dNSEntry.getClass().getSimpleName();
            }
            logger2.trace("DNSEntry is not of type 'DNSRecord' but of type {}", (Object) str);
            return;
        }
        DNSRecord dNSRecord = (DNSRecord) dNSEntry;
        if (dNSRecord.isExpired(j)) {
            z = handleExpiredRecord(dNSRecord);
        } else {
            z = handleUpdateRecord(dNSCache, j, dNSRecord);
        }
        if (z) {
            JmDNSImpl dns = getDns();
            if (dns == null) {
                logger.debug("JmDNS not available.");
            } else if (hasData()) {
                dns.handleServiceResolved(new ServiceEventImpl(dns, getType(), getName(), clone()));
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    /* renamed from: javax.jmdns.impl.ServiceInfoImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                javax.jmdns.impl.constants.DNSRecordType[] r0 = javax.jmdns.impl.constants.DNSRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = r0
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_A     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_AAAA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_SRV     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_TXT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_PTR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean handleExpiredRecord(DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i != 1 && i != 2) {
            logger.trace("Unhandled expired record: {}", (Object) dNSRecord);
            return false;
        } else if (!dNSRecord.getName().equalsIgnoreCase(getServer())) {
            return false;
        } else {
            DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
            if (DNSRecordType.TYPE_A.equals(dNSRecord.getRecordType())) {
                Inet4Address inet4Address = (Inet4Address) address.getAddress();
                if (this._ipv4Addresses.remove(inet4Address)) {
                    logger.debug("Removed expired IPv4: {}", (Object) inet4Address);
                    return true;
                }
                logger.debug("Expired IPv4 not in this service: {}", (Object) inet4Address);
                return false;
            }
            Inet6Address inet6Address = (Inet6Address) address.getAddress();
            if (this._ipv6Addresses.remove(inet6Address)) {
                logger.debug("Removed expired IPv6: {}", (Object) inet6Address);
                return true;
            }
            logger.debug("Expired IPv6 not in this service: {}", (Object) inet6Address);
            return false;
        }
    }

    private boolean handleUpdateRecord(DNSCache dNSCache, long j, DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5 && getSubtype().length() == 0 && dNSRecord.getSubtype().length() != 0) {
                            this._subtype = dNSRecord.getSubtype();
                            return true;
                        }
                    } else if (dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                        this._text = ((DNSRecord.Text) dNSRecord).getText();
                        this._props = null;
                        return true;
                    }
                } else if (dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                    DNSRecord.Service service = (DNSRecord.Service) dNSRecord;
                    String str = this._server;
                    boolean z = str == null || !str.equalsIgnoreCase(service.getServer());
                    this._server = service.getServer();
                    this._port = service.getPort();
                    this._weight = service.getWeight();
                    this._priority = service.getPriority();
                    if (!z) {
                        return true;
                    }
                    this._ipv4Addresses.clear();
                    this._ipv6Addresses.clear();
                    for (DNSEntry updateRecord : dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_A, DNSRecordClass.CLASS_IN)) {
                        updateRecord(dNSCache, j, updateRecord);
                    }
                    for (DNSEntry updateRecord2 : dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN)) {
                        updateRecord(dNSCache, j, updateRecord2);
                    }
                }
            } else if (dNSRecord.getName().equalsIgnoreCase(getServer())) {
                DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
                if (address.getAddress() instanceof Inet6Address) {
                    if (this._ipv6Addresses.add((Inet6Address) address.getAddress())) {
                        return true;
                    }
                }
            }
        } else if (dNSRecord.getName().equalsIgnoreCase(getServer())) {
            DNSRecord.Address address2 = (DNSRecord.Address) dNSRecord;
            if (address2.getAddress() instanceof Inet4Address) {
                if (this._ipv4Addresses.add((Inet4Address) address2.getAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized boolean hasData() {
        return getServer() != null && hasInetAddress() && getTextBytes() != null && getTextBytes().length > 0;
    }

    private final boolean hasInetAddress() {
        return this._ipv4Addresses.size() > 0 || this._ipv6Addresses.size() > 0;
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this._state.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this._state.revertState();
    }

    public boolean cancelState() {
        return this._state.cancelState();
    }

    public boolean closeState() {
        return this._state.closeState();
    }

    public boolean recoverState() {
        return this._state.recoverState();
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._state.removeAssociationWithTask(dNSTask);
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._state.associateWithTask(dNSTask, dNSState);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._state.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this._state.isProbing();
    }

    public boolean isAnnouncing() {
        return this._state.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this._state.isAnnounced();
    }

    public boolean isCanceling() {
        return this._state.isCanceling();
    }

    public boolean isCanceled() {
        return this._state.isCanceled();
    }

    public boolean isClosing() {
        return this._state.isClosing();
    }

    public boolean isClosed() {
        return this._state.isClosed();
    }

    public boolean waitForAnnounced(long j) {
        return this._state.waitForAnnounced(j);
    }

    public boolean waitForCanceled(long j) {
        return this._state.waitForCanceled(j);
    }

    public int hashCode() {
        return getQualifiedName().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServiceInfoImpl) && getQualifiedName().equals(((ServiceInfoImpl) obj).getQualifiedName());
    }

    public String getNiceTextString() {
        StringBuilder sb = new StringBuilder();
        int length = getTextBytes().length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (i >= 200) {
                sb.append("...");
                break;
            } else {
                byte b = getTextBytes()[i] & UByte.MAX_VALUE;
                if (b < 32 || b > Byte.MAX_VALUE) {
                    sb.append("\\0");
                    sb.append(Integer.toString(b, 8));
                } else {
                    sb.append((char) b);
                }
                i++;
            }
        }
        return sb.toString();
    }

    public ServiceInfoImpl clone() {
        ServiceInfoImpl serviceInfoImpl = new ServiceInfoImpl(getQualifiedNameMap(), this._port, this._weight, this._priority, this._persistent, this._text);
        for (Inet6Address add : getInet6Addresses()) {
            serviceInfoImpl._ipv6Addresses.add(add);
        }
        for (Inet4Address add2 : getInet4Addresses()) {
            serviceInfoImpl._ipv4Addresses.add(add2);
        }
        return serviceInfoImpl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getSimpleName()).append('@').append(System.identityHashCode(this));
        sb.append(" name: '");
        if (getName().length() > 0) {
            sb.append(getName()).append('.');
        }
        sb.append(getTypeWithSubtype());
        sb.append("' address: '");
        InetAddress[] inetAddresses = getInetAddresses();
        if (inetAddresses.length > 0) {
            for (InetAddress append : inetAddresses) {
                sb.append(append).append(':').append(getPort()).append(' ');
            }
        } else {
            sb.append("(null):").append(getPort());
        }
        sb.append("' status: '").append(this._state.toString());
        sb.append(isPersistent() ? "' is persistent," : "',");
        if (hasData()) {
            sb.append(" has data");
        } else {
            sb.append(" has NO data");
        }
        if (getTextBytes().length > 0) {
            Map<String, byte[]> properties = getProperties();
            if (!properties.isEmpty()) {
                for (Map.Entry next : properties.entrySet()) {
                    sb.append("\n\t").append((String) next.getKey()).append(": ").append(ByteWrangler.readUTF((byte[]) next.getValue()));
                }
            } else {
                sb.append(", empty");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public Collection<DNSRecord> answers(DNSRecordClass dNSRecordClass, boolean z, int i, HostInfo hostInfo) {
        DNSRecordClass dNSRecordClass2 = dNSRecordClass;
        ArrayList arrayList = new ArrayList();
        if (dNSRecordClass2 == DNSRecordClass.CLASS_ANY || dNSRecordClass2 == DNSRecordClass.CLASS_IN) {
            if (getSubtype().length() > 0) {
                arrayList.add(new DNSRecord.Pointer(getTypeWithSubtype(), DNSRecordClass.CLASS_IN, false, i, getQualifiedName()));
            }
            arrayList.add(new DNSRecord.Pointer(getType(), DNSRecordClass.CLASS_IN, false, i, getQualifiedName()));
            String qualifiedName = getQualifiedName();
            DNSRecordClass dNSRecordClass3 = DNSRecordClass.CLASS_IN;
            int i2 = this._priority;
            int i3 = this._weight;
            arrayList.add(new DNSRecord.Service(qualifiedName, dNSRecordClass3, z, i, i2, i3, this._port, hostInfo.getName()));
            arrayList.add(new DNSRecord.Text(getQualifiedName(), DNSRecordClass.CLASS_IN, z, i, getTextBytes()));
        }
        return arrayList;
    }

    public void setText(byte[] bArr) throws IllegalStateException {
        synchronized (this) {
            this._text = bArr;
            this._props = null;
            setNeedTextAnnouncing(true);
        }
    }

    public void setText(Map<String, ?> map) throws IllegalStateException {
        setText(ByteWrangler.textFromProperties(map));
    }

    /* access modifiers changed from: package-private */
    public void _setText(byte[] bArr) {
        this._text = bArr;
        this._props = null;
    }

    public void setDns(JmDNSImpl jmDNSImpl) {
        this._state.setDns(jmDNSImpl);
    }

    public JmDNSImpl getDns() {
        return this._state.getDns();
    }

    public boolean isPersistent() {
        return this._persistent;
    }

    public void setNeedTextAnnouncing(boolean z) {
        this._needTextAnnouncing = z;
        if (z) {
            this._state.setTask((DNSTask) null);
        }
    }

    public boolean needTextAnnouncing() {
        return this._needTextAnnouncing;
    }

    /* access modifiers changed from: package-private */
    public Delegate getDelegate() {
        return this._delegate;
    }

    /* access modifiers changed from: package-private */
    public void setDelegate(Delegate delegate) {
        this._delegate = delegate;
    }

    public boolean hasSameAddresses(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return false;
        }
        if (serviceInfo instanceof ServiceInfoImpl) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
            if (this._ipv4Addresses.size() != serviceInfoImpl._ipv4Addresses.size() || this._ipv6Addresses.size() != serviceInfoImpl._ipv6Addresses.size() || !this._ipv4Addresses.equals(serviceInfoImpl._ipv4Addresses) || !this._ipv6Addresses.equals(serviceInfoImpl._ipv6Addresses)) {
                return false;
            }
            return true;
        }
        InetAddress[] inetAddresses = getInetAddresses();
        InetAddress[] inetAddresses2 = serviceInfo.getInetAddresses();
        if (inetAddresses.length != inetAddresses2.length || !new HashSet(Arrays.asList(inetAddresses)).equals(new HashSet(Arrays.asList(inetAddresses2)))) {
            return false;
        }
        return true;
    }
}
