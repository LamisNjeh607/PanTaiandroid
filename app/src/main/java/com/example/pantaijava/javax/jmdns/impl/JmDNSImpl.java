package javax.jmdns.impl;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSTaskStarter;
import javax.jmdns.impl.ListenerStatus;
import javax.jmdns.impl.NameRegister;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import javax.jmdns.impl.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmDNSImpl extends JmDNS implements DNSStatefulObject, DNSTaskStarter {
    private static final Random _random = new Random();
    private static Logger logger = LoggerFactory.getLogger(JmDNSImpl.class.getName());
    private final DNSCache _cache;
    private volatile JmDNS.Delegate _delegate;
    private final ExecutorService _executor = Executors.newSingleThreadExecutor(new NamedThreadFactory("JmDNS"));
    private volatile InetAddress _group;
    private Thread _incomingListener;
    private final ReentrantLock _ioLock = new ReentrantLock();
    private long _lastThrottleIncrement;
    private final List<DNSListener> _listeners;
    private HostInfo _localHost;
    private final String _name;
    private DNSIncoming _plannedAnswer;
    private final Object _recoverLock = new Object();
    private final ConcurrentMap<String, ServiceCollector> _serviceCollectors;
    final ConcurrentMap<String, List<ListenerStatus.ServiceListenerStatus>> _serviceListeners;
    private final ConcurrentMap<String, ServiceTypeEntry> _serviceTypes;
    private final ConcurrentMap<String, ServiceInfo> _services;
    protected Thread _shutdown;
    private volatile MulticastSocket _socket;
    private int _throttle;
    private final Set<ListenerStatus.ServiceTypeListenerStatus> _typeListeners;

    public enum Operation {
        Remove,
        Update,
        Add,
        RegisterServiceType,
        Noop
    }

    public JmDNSImpl getDns() {
        return this;
    }

    public static class ServiceTypeEntry extends AbstractMap<String, String> implements Cloneable {
        private final Set<Map.Entry<String, String>> _entrySet = new HashSet();
        private final String _type;

        private static class SubTypeEntry implements Map.Entry<String, String>, Serializable, Cloneable {
            private static final long serialVersionUID = 9188503522395855322L;
            private final String _key;
            private final String _value;

            public SubTypeEntry clone() {
                return this;
            }

            public SubTypeEntry(String str) {
                str = str == null ? "" : str;
                this._value = str;
                this._key = str.toLowerCase();
            }

            public String getKey() {
                return this._key;
            }

            public String getValue() {
                return this._value;
            }

            public String setValue(String str) {
                throw new UnsupportedOperationException();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!getKey().equals(entry.getKey()) || !getValue().equals(entry.getValue())) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                String str = this._key;
                int i = 0;
                int hashCode = str == null ? 0 : str.hashCode();
                String str2 = this._value;
                if (str2 != null) {
                    i = str2.hashCode();
                }
                return hashCode ^ i;
            }

            public String toString() {
                return this._key + "=" + this._value;
            }
        }

        public ServiceTypeEntry(String str) {
            this._type = str;
        }

        public String getType() {
            return this._type;
        }

        public Set<Map.Entry<String, String>> entrySet() {
            return this._entrySet;
        }

        public boolean contains(String str) {
            return str != null && containsKey(str.toLowerCase());
        }

        public boolean add(String str) {
            if (str == null || contains(str)) {
                return false;
            }
            this._entrySet.add(new SubTypeEntry(str));
            return true;
        }

        public Iterator<String> iterator() {
            return keySet().iterator();
        }

        public ServiceTypeEntry clone() {
            ServiceTypeEntry serviceTypeEntry = new ServiceTypeEntry(getType());
            for (Map.Entry<String, String> value : entrySet()) {
                serviceTypeEntry.add((String) value.getValue());
            }
            return serviceTypeEntry;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(200);
            if (isEmpty()) {
                sb.append("empty");
            } else {
                for (String append : values()) {
                    sb.append(append);
                    sb.append(", ");
                }
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }
    }

    public static void main(String[] strArr) {
        String str;
        try {
            Properties properties = new Properties();
            properties.load(JmDNSImpl.class.getResourceAsStream("/META-INF/maven/javax.jmdns/jmdns/pom.properties"));
            str = properties.getProperty("version");
        } catch (Exception unused) {
            str = "RUNNING.IN.IDE.FULL";
        }
        System.out.println("JmDNS version \"" + str + "\"");
        System.out.println(" ");
        System.out.println("Running on java version \"" + System.getProperty("java.version") + "\" (build " + System.getProperty("java.runtime.version") + ") from " + System.getProperty("java.vendor"));
        System.out.println("Operating environment \"" + System.getProperty("os.name") + "\" version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
        System.out.println("For more information on JmDNS please visit http://jmdns.org");
    }

    public JmDNSImpl(InetAddress inetAddress, String str) throws IOException {
        logger.debug("JmDNS instance created");
        this._cache = new DNSCache(100);
        this._listeners = Collections.synchronizedList(new ArrayList());
        this._serviceListeners = new ConcurrentHashMap();
        this._typeListeners = Collections.synchronizedSet(new HashSet());
        this._serviceCollectors = new ConcurrentHashMap();
        this._services = new ConcurrentHashMap(20);
        this._serviceTypes = new ConcurrentHashMap(20);
        HostInfo newHostInfo = HostInfo.newHostInfo(inetAddress, this, str);
        this._localHost = newHostInfo;
        this._name = str == null ? newHostInfo.getName() : str;
        openMulticastSocket(getLocalHost());
        start(getServices().values());
        startReaper();
    }

    private void start(Collection<? extends ServiceInfo> collection) {
        if (this._incomingListener == null) {
            SocketListener socketListener = new SocketListener(this);
            this._incomingListener = socketListener;
            socketListener.start();
        }
        startProber();
        for (ServiceInfo serviceInfoImpl : collection) {
            try {
                registerService(new ServiceInfoImpl(serviceInfoImpl));
            } catch (Exception e) {
                logger.warn("start() Registration exception ", (Throwable) e);
            }
        }
    }

    private void openMulticastSocket(HostInfo hostInfo) throws IOException {
        if (this._group == null) {
            if (hostInfo.getInetAddress() instanceof Inet6Address) {
                this._group = InetAddress.getByName(DNSConstants.MDNS_GROUP_IPV6);
            } else {
                this._group = InetAddress.getByName(DNSConstants.MDNS_GROUP);
            }
        }
        if (this._socket != null) {
            closeMulticastSocket();
        }
        this._socket = new MulticastSocket(DNSConstants.MDNS_PORT);
        if (hostInfo == null || hostInfo.getInterface() == null) {
            logger.trace("Trying to joinGroup({})", (Object) this._group);
            this._socket.joinGroup(this._group);
        } else {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this._group, DNSConstants.MDNS_PORT);
            this._socket.setNetworkInterface(hostInfo.getInterface());
            logger.trace("Trying to joinGroup({}, {})", (Object) inetSocketAddress, (Object) hostInfo.getInterface());
            this._socket.joinGroup(inetSocketAddress, hostInfo.getInterface());
        }
        this._socket.setTimeToLive(255);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:2|3|4|6|7|(2:8|(1:26)(2:25|22))|26|28|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0046, code lost:
        logger.warn("closeMulticastSocket() Close socket exception ", (java.lang.Throwable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x003f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[Catch:{ Exception -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0043 A[Catch:{ Exception -> 0x0014 }, EDGE_INSN: B:29:0x0043->B:26:0x0043 ?: BREAK  
    EDGE_INSN: B:30:0x0043->B:26:0x0043 ?: BREAK  ] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void closeMulticastSocket() {
        /*
            r4 = this;
            org.slf4j.Logger r0 = logger
            java.lang.String r1 = "closeMulticastSocket()"
            r0.debug(r1)
            java.net.MulticastSocket r0 = r4._socket
            if (r0 == 0) goto L_0x004f
            r0 = 0
            java.net.MulticastSocket r1 = r4._socket     // Catch:{ SocketException -> 0x0016 }
            java.net.InetAddress r2 = r4._group     // Catch:{ SocketException -> 0x0016 }
            r1.leaveGroup(r2)     // Catch:{ SocketException -> 0x0016 }
            goto L_0x0016
        L_0x0014:
            r1 = move-exception
            goto L_0x0046
        L_0x0016:
            java.net.MulticastSocket r1 = r4._socket     // Catch:{ Exception -> 0x0014 }
            r1.close()     // Catch:{ Exception -> 0x0014 }
        L_0x001b:
            java.lang.Thread r1 = r4._incomingListener     // Catch:{ Exception -> 0x0014 }
            if (r1 == 0) goto L_0x0043
            boolean r1 = r1.isAlive()     // Catch:{ Exception -> 0x0014 }
            if (r1 == 0) goto L_0x0043
            monitor-enter(r4)     // Catch:{ Exception -> 0x0014 }
            java.lang.Thread r1 = r4._incomingListener     // Catch:{ InterruptedException -> 0x003f }
            if (r1 == 0) goto L_0x003f
            boolean r1 = r1.isAlive()     // Catch:{ InterruptedException -> 0x003f }
            if (r1 == 0) goto L_0x003f
            org.slf4j.Logger r1 = logger     // Catch:{ InterruptedException -> 0x003f }
            java.lang.String r2 = "closeMulticastSocket(): waiting for jmDNS monitor"
            r1.debug(r2)     // Catch:{ InterruptedException -> 0x003f }
            r1 = 1000(0x3e8, double:4.94E-321)
            r4.wait(r1)     // Catch:{ InterruptedException -> 0x003f }
            goto L_0x003f
        L_0x003d:
            r1 = move-exception
            goto L_0x0041
        L_0x003f:
            monitor-exit(r4)     // Catch:{ all -> 0x003d }
            goto L_0x001b
        L_0x0041:
            monitor-exit(r4)     // Catch:{ all -> 0x003d }
            throw r1     // Catch:{ Exception -> 0x0014 }
        L_0x0043:
            r4._incomingListener = r0     // Catch:{ Exception -> 0x0014 }
            goto L_0x004d
        L_0x0046:
            org.slf4j.Logger r2 = logger
            java.lang.String r3 = "closeMulticastSocket() Close socket exception "
            r2.warn((java.lang.String) r3, (java.lang.Throwable) r1)
        L_0x004d:
            r4._socket = r0
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.JmDNSImpl.closeMulticastSocket():void");
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this._localHost.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this._localHost.revertState();
    }

    public boolean cancelState() {
        return this._localHost.cancelState();
    }

    public boolean closeState() {
        return this._localHost.closeState();
    }

    public boolean recoverState() {
        return this._localHost.recoverState();
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._localHost.associateWithTask(dNSTask, dNSState);
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._localHost.removeAssociationWithTask(dNSTask);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._localHost.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this._localHost.isProbing();
    }

    public boolean isAnnouncing() {
        return this._localHost.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this._localHost.isAnnounced();
    }

    public boolean isCanceling() {
        return this._localHost.isCanceling();
    }

    public boolean isCanceled() {
        return this._localHost.isCanceled();
    }

    public boolean isClosing() {
        return this._localHost.isClosing();
    }

    public boolean isClosed() {
        return this._localHost.isClosed();
    }

    public boolean waitForAnnounced(long j) {
        return this._localHost.waitForAnnounced(j);
    }

    public boolean waitForCanceled(long j) {
        return this._localHost.waitForCanceled(j);
    }

    public DNSCache getCache() {
        return this._cache;
    }

    public String getName() {
        return this._name;
    }

    public String getHostName() {
        return this._localHost.getName();
    }

    public HostInfo getLocalHost() {
        return this._localHost;
    }

    public InetAddress getInetAddress() throws IOException {
        return this._localHost.getInetAddress();
    }

    @Deprecated
    public InetAddress getInterface() throws IOException {
        return this._socket.getInterface();
    }

    public ServiceInfo getServiceInfo(String str, String str2) {
        return getServiceInfo(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo getServiceInfo(String str, String str2, long j) {
        return getServiceInfo(str, str2, false, j);
    }

    public ServiceInfo getServiceInfo(String str, String str2, boolean z) {
        return getServiceInfo(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo getServiceInfo(String str, String str2, boolean z, long j) {
        ServiceInfoImpl resolveServiceInfo = resolveServiceInfo(str, str2, "", z);
        waitForInfoData(resolveServiceInfo, j);
        if (resolveServiceInfo.hasData()) {
            return resolveServiceInfo;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ServiceInfoImpl resolveServiceInfo(String str, String str2, String str3, boolean z) {
        cleanCache();
        String lowerCase = str.toLowerCase();
        registerServiceType(str);
        if (this._serviceCollectors.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
            addServiceListener(lowerCase, (ServiceListener) this._serviceCollectors.get(lowerCase), true);
        }
        ServiceInfoImpl serviceInfoFromCache = getServiceInfoFromCache(str, str2, str3, z);
        startServiceInfoResolver(serviceInfoFromCache);
        return serviceInfoFromCache;
    }

    /* access modifiers changed from: package-private */
    public ServiceInfoImpl getServiceInfoFromCache(String str, String str2, String str3, boolean z) {
        ServiceInfoImpl serviceInfoImpl;
        byte[] bArr;
        String str4;
        ServiceInfo serviceInfo;
        ServiceInfo serviceInfo2;
        ServiceInfo serviceInfo3;
        ServiceInfo serviceInfo4;
        byte[] bArr2 = null;
        String str5 = str;
        ServiceInfoImpl serviceInfoImpl2 = new ServiceInfoImpl(str5, str2, str3, 0, 0, 0, z, (byte[]) null);
        DNSEntry dNSEntry = getCache().getDNSEntry(new DNSRecord.Pointer(str5, DNSRecordClass.CLASS_ANY, false, 0, serviceInfoImpl2.getQualifiedName()));
        if (!(dNSEntry instanceof DNSRecord) || (serviceInfoImpl = (ServiceInfoImpl) ((DNSRecord) dNSEntry).getServiceInfo(z)) == null) {
            return serviceInfoImpl2;
        }
        Map<ServiceInfo.Fields, String> qualifiedNameMap = serviceInfoImpl.getQualifiedNameMap();
        DNSEntry dNSEntry2 = getCache().getDNSEntry(serviceInfoImpl2.getQualifiedName(), DNSRecordType.TYPE_SRV, DNSRecordClass.CLASS_ANY);
        if (!(dNSEntry2 instanceof DNSRecord) || (serviceInfo4 = ((DNSRecord) dNSEntry2).getServiceInfo(z)) == null) {
            bArr = null;
            str4 = "";
        } else {
            serviceInfoImpl = new ServiceInfoImpl(qualifiedNameMap, serviceInfo4.getPort(), serviceInfo4.getWeight(), serviceInfo4.getPriority(), z, (byte[]) null);
            bArr = serviceInfo4.getTextBytes();
            str4 = serviceInfo4.getServer();
        }
        Iterator<? extends DNSEntry> it = getCache().getDNSEntryList(str4, DNSRecordType.TYPE_A, DNSRecordClass.CLASS_ANY).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DNSEntry dNSEntry3 = (DNSEntry) it.next();
            if ((dNSEntry3 instanceof DNSRecord) && (serviceInfo3 = ((DNSRecord) dNSEntry3).getServiceInfo(z)) != null) {
                for (Inet4Address addAddress : serviceInfo3.getInet4Addresses()) {
                    serviceInfoImpl.addAddress(addAddress);
                }
                serviceInfoImpl._setText(serviceInfo3.getTextBytes());
            }
        }
        for (DNSEntry dNSEntry4 : getCache().getDNSEntryList(str4, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_ANY)) {
            if ((dNSEntry4 instanceof DNSRecord) && (serviceInfo2 = ((DNSRecord) dNSEntry4).getServiceInfo(z)) != null) {
                for (Inet6Address addAddress2 : serviceInfo2.getInet6Addresses()) {
                    serviceInfoImpl.addAddress(addAddress2);
                }
                serviceInfoImpl._setText(serviceInfo2.getTextBytes());
            }
        }
        DNSEntry dNSEntry5 = getCache().getDNSEntry(serviceInfoImpl.getQualifiedName(), DNSRecordType.TYPE_TXT, DNSRecordClass.CLASS_ANY);
        if ((dNSEntry5 instanceof DNSRecord) && (serviceInfo = ((DNSRecord) dNSEntry5).getServiceInfo(z)) != null) {
            serviceInfoImpl._setText(serviceInfo.getTextBytes());
        }
        if (serviceInfoImpl.getTextBytes().length == 0) {
            serviceInfoImpl._setText(bArr);
        }
        return serviceInfoImpl.hasData() ? serviceInfoImpl : serviceInfoImpl2;
    }

    private void waitForInfoData(ServiceInfo serviceInfo, long j) {
        synchronized (serviceInfo) {
            long j2 = j / 200;
            if (j2 < 1) {
                j2 = 1;
            }
            for (int i = 0; ((long) i) < j2 && !serviceInfo.hasData(); i++) {
                try {
                    serviceInfo.wait(200);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public void requestServiceInfo(String str, String str2) {
        requestServiceInfo(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public void requestServiceInfo(String str, String str2, boolean z) {
        requestServiceInfo(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public void requestServiceInfo(String str, String str2, long j) {
        requestServiceInfo(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public void requestServiceInfo(String str, String str2, boolean z, long j) {
        waitForInfoData(resolveServiceInfo(str, str2, "", z), j);
    }

    /* access modifiers changed from: package-private */
    public void handleServiceResolved(final ServiceEvent serviceEvent) {
        ArrayList<ListenerStatus.ServiceListenerStatus> arrayList;
        List list = (List) this._serviceListeners.get(serviceEvent.getType().toLowerCase());
        if (list != null && !list.isEmpty() && serviceEvent.getInfo() != null && serviceEvent.getInfo().hasData()) {
            synchronized (list) {
                arrayList = new ArrayList<>(list);
            }
            for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : arrayList) {
                this._executor.submit(new Runnable() {
                    public void run() {
                        serviceListenerStatus.serviceResolved(serviceEvent);
                    }
                });
            }
        }
    }

    public void addServiceTypeListener(ServiceTypeListener serviceTypeListener) throws IOException {
        ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus = new ListenerStatus.ServiceTypeListenerStatus(serviceTypeListener, false);
        this._typeListeners.add(serviceTypeListenerStatus);
        for (String serviceEventImpl : this._serviceTypes.keySet()) {
            serviceTypeListenerStatus.serviceTypeAdded(new ServiceEventImpl(this, serviceEventImpl, "", (ServiceInfo) null));
        }
        startTypeResolver();
    }

    public void removeServiceTypeListener(ServiceTypeListener serviceTypeListener) {
        this._typeListeners.remove(new ListenerStatus.ServiceTypeListenerStatus(serviceTypeListener, false));
    }

    public void addServiceListener(String str, ServiceListener serviceListener) {
        addServiceListener(str, serviceListener, false);
    }

    private void addServiceListener(String str, ServiceListener serviceListener, boolean z) {
        ListenerStatus.ServiceListenerStatus serviceListenerStatus = new ListenerStatus.ServiceListenerStatus(serviceListener, z);
        String lowerCase = str.toLowerCase();
        List list = (List) this._serviceListeners.get(lowerCase);
        if (list == null) {
            if (this._serviceListeners.putIfAbsent(lowerCase, new LinkedList()) == null && this._serviceCollectors.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
                addServiceListener(lowerCase, (ServiceListener) this._serviceCollectors.get(lowerCase), true);
            }
            list = (List) this._serviceListeners.get(lowerCase);
        }
        if (list != null) {
            synchronized (list) {
                if (!list.contains(serviceListenerStatus)) {
                    list.add(serviceListenerStatus);
                }
            }
        }
        ArrayList<ServiceEvent> arrayList = new ArrayList<>();
        Iterator<DNSEntry> it = getCache().allValues().iterator();
        while (it.hasNext()) {
            DNSRecord dNSRecord = (DNSRecord) it.next();
            if (dNSRecord.getRecordType() == DNSRecordType.TYPE_SRV && dNSRecord.getKey().endsWith(lowerCase)) {
                arrayList.add(new ServiceEventImpl(this, dNSRecord.getType(), toUnqualifiedName(dNSRecord.getType(), dNSRecord.getName()), dNSRecord.getServiceInfo()));
            }
        }
        for (ServiceEvent serviceAdded : arrayList) {
            serviceListenerStatus.serviceAdded(serviceAdded);
        }
        startServiceResolver(str);
    }

    public void removeServiceListener(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List list = (List) this._serviceListeners.get(lowerCase);
        if (list != null) {
            synchronized (list) {
                list.remove(new ListenerStatus.ServiceListenerStatus(serviceListener, false));
                if (list.isEmpty()) {
                    this._serviceListeners.remove(lowerCase, list);
                }
            }
        }
    }

    public void registerService(ServiceInfo serviceInfo) throws IOException {
        if (isClosing() || isClosed()) {
            throw new IllegalStateException("This DNS is closed.");
        }
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
        if (serviceInfoImpl.getDns() != null) {
            if (serviceInfoImpl.getDns() != this) {
                throw new IllegalStateException("A service information can only be registered with a single instamce of JmDNS.");
            } else if (this._services.get(serviceInfoImpl.getKey()) != null) {
                throw new IllegalStateException("A service information can only be registered once.");
            }
        }
        serviceInfoImpl.setDns(this);
        registerServiceType(serviceInfoImpl.getTypeWithSubtype());
        serviceInfoImpl.recoverState();
        serviceInfoImpl.setServer(this._localHost.getName());
        serviceInfoImpl.addAddress(this._localHost.getInet4Address());
        serviceInfoImpl.addAddress(this._localHost.getInet6Address());
        makeServiceNameUnique(serviceInfoImpl);
        while (this._services.putIfAbsent(serviceInfoImpl.getKey(), serviceInfoImpl) != null) {
            makeServiceNameUnique(serviceInfoImpl);
        }
        startProber();
        logger.debug("registerService() JmDNS registered service as {}", (Object) serviceInfoImpl);
    }

    public void unregisterService(ServiceInfo serviceInfo) {
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) this._services.get(serviceInfo.getKey());
        if (serviceInfoImpl != null) {
            serviceInfoImpl.cancelState();
            startCanceler();
            serviceInfoImpl.waitForCanceled(5000);
            this._services.remove(serviceInfoImpl.getKey(), serviceInfoImpl);
            logger.debug("unregisterService() JmDNS {} unregistered service as {}", (Object) getName(), (Object) serviceInfoImpl);
            return;
        }
        logger.warn("{} removing unregistered service info: {}", (Object) getName(), (Object) serviceInfo.getKey());
    }

    public void unregisterAllServices() {
        logger.debug("unregisterAllServices()");
        for (ServiceInfo serviceInfo : this._services.values()) {
            if (serviceInfo != null) {
                logger.debug("Cancelling service info: {}", (Object) serviceInfo);
                ((ServiceInfoImpl) serviceInfo).cancelState();
            }
        }
        startCanceler();
        for (Map.Entry entry : this._services.entrySet()) {
            ServiceInfo serviceInfo2 = (ServiceInfo) entry.getValue();
            if (serviceInfo2 != null) {
                logger.debug("Wait for service info cancel: {}", (Object) serviceInfo2);
                ((ServiceInfoImpl) serviceInfo2).waitForCanceled(5000);
                this._services.remove((String) entry.getKey(), serviceInfo2);
            }
        }
    }

    public boolean registerServiceType(String str) {
        boolean z;
        ServiceTypeEntry serviceTypeEntry;
        Map<ServiceInfo.Fields, String> decodeQualifiedNameMapForType = ServiceInfoImpl.decodeQualifiedNameMapForType(str);
        String str2 = decodeQualifiedNameMapForType.get(ServiceInfo.Fields.Domain);
        String str3 = decodeQualifiedNameMapForType.get(ServiceInfo.Fields.Protocol);
        String str4 = decodeQualifiedNameMapForType.get(ServiceInfo.Fields.Application);
        String str5 = decodeQualifiedNameMapForType.get(ServiceInfo.Fields.Subtype);
        String str6 = (str4.length() > 0 ? "_" + str4 + "." : "") + (str3.length() > 0 ? "_" + str3 + "." : "") + str2 + ".";
        String lowerCase = str6.toLowerCase();
        logger.debug("{} registering service type: {} as: {}{}{}", getName(), str, str6, str5.length() > 0 ? " subtype: " : "", str5.length() > 0 ? str5 : "");
        boolean z2 = true;
        if (this._serviceTypes.containsKey(lowerCase) || str4.toLowerCase().equals("dns-sd") || str2.toLowerCase().endsWith("in-addr.arpa") || str2.toLowerCase().endsWith("ip6.arpa")) {
            z = false;
        } else {
            z = this._serviceTypes.putIfAbsent(lowerCase, new ServiceTypeEntry(str6)) == null;
            if (z) {
                Set<ListenerStatus.ServiceTypeListenerStatus> set = this._typeListeners;
                ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr = (ListenerStatus.ServiceTypeListenerStatus[]) set.toArray(new ListenerStatus.ServiceTypeListenerStatus[set.size()]);
                final ServiceEventImpl serviceEventImpl = new ServiceEventImpl(this, str6, "", (ServiceInfo) null);
                for (final ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus : serviceTypeListenerStatusArr) {
                    this._executor.submit(new Runnable() {
                        public void run() {
                            serviceTypeListenerStatus.serviceTypeAdded(serviceEventImpl);
                        }
                    });
                }
            }
        }
        if (str5.length() <= 0 || (serviceTypeEntry = (ServiceTypeEntry) this._serviceTypes.get(lowerCase)) == null || serviceTypeEntry.contains(str5)) {
            return z;
        }
        synchronized (serviceTypeEntry) {
            if (!serviceTypeEntry.contains(str5)) {
                serviceTypeEntry.add(str5);
                Set<ListenerStatus.ServiceTypeListenerStatus> set2 = this._typeListeners;
                ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr2 = (ListenerStatus.ServiceTypeListenerStatus[]) set2.toArray(new ListenerStatus.ServiceTypeListenerStatus[set2.size()]);
                final ServiceEventImpl serviceEventImpl2 = new ServiceEventImpl(this, "_" + str5 + "._sub." + str6, "", (ServiceInfo) null);
                for (final ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus2 : serviceTypeListenerStatusArr2) {
                    this._executor.submit(new Runnable() {
                        public void run() {
                            serviceTypeListenerStatus2.subTypeForServiceTypeAdded(serviceEventImpl2);
                        }
                    });
                }
            } else {
                z2 = z;
            }
        }
        return z2;
    }

    private boolean makeServiceNameUnique(ServiceInfoImpl serviceInfoImpl) {
        boolean z;
        String key = serviceInfoImpl.getKey();
        long currentTimeMillis = System.currentTimeMillis();
        do {
            Iterator<? extends DNSEntry> it = getCache().getDNSEntryList(serviceInfoImpl.getKey()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                DNSEntry dNSEntry = (DNSEntry) it.next();
                if (DNSRecordType.TYPE_SRV.equals(dNSEntry.getRecordType()) && !dNSEntry.isExpired(currentTimeMillis)) {
                    DNSRecord.Service service = (DNSRecord.Service) dNSEntry;
                    if (service.getPort() != serviceInfoImpl.getPort() || !service.getServer().equals(this._localHost.getName())) {
                        logger.debug("makeServiceNameUnique() JmDNS.makeServiceNameUnique srv collision:{} s.server={} {} equals:{}", dNSEntry, service.getServer(), this._localHost.getName(), Boolean.valueOf(service.getServer().equals(this._localHost.getName())));
                        serviceInfoImpl.setName(NameRegister.Factory.getRegistry().incrementName(this._localHost.getInetAddress(), serviceInfoImpl.getName(), NameRegister.NameType.SERVICE));
                        z = true;
                    }
                }
            }
            ServiceInfo serviceInfo = (ServiceInfo) this._services.get(serviceInfoImpl.getKey());
            if (!(serviceInfo == null || serviceInfo == serviceInfoImpl)) {
                serviceInfoImpl.setName(NameRegister.Factory.getRegistry().incrementName(this._localHost.getInetAddress(), serviceInfoImpl.getName(), NameRegister.NameType.SERVICE));
                z = true;
                continue;
            }
        } while (z);
        return !key.equals(serviceInfoImpl.getKey());
    }

    public void addListener(DNSListener dNSListener, DNSQuestion dNSQuestion) {
        long currentTimeMillis = System.currentTimeMillis();
        this._listeners.add(dNSListener);
        if (dNSQuestion != null) {
            for (DNSEntry dNSEntry : getCache().getDNSEntryList(dNSQuestion.getName().toLowerCase())) {
                if (dNSQuestion.answeredBy(dNSEntry) && !dNSEntry.isExpired(currentTimeMillis)) {
                    dNSListener.updateRecord(getCache(), currentTimeMillis, dNSEntry);
                }
            }
        }
    }

    public void removeListener(DNSListener dNSListener) {
        this._listeners.remove(dNSListener);
    }

    public void renewServiceCollector(String str) {
        if (this._serviceCollectors.containsKey(str.toLowerCase())) {
            startServiceResolver(str);
        }
    }

    public void updateRecord(long j, DNSRecord dNSRecord, Operation operation) {
        ArrayList<DNSListener> arrayList;
        List<ListenerStatus.ServiceListenerStatus> list;
        synchronized (this._listeners) {
            arrayList = new ArrayList<>(this._listeners);
        }
        for (DNSListener updateRecord : arrayList) {
            updateRecord.updateRecord(getCache(), j, dNSRecord);
        }
        if (DNSRecordType.TYPE_PTR.equals(dNSRecord.getRecordType()) || (DNSRecordType.TYPE_SRV.equals(dNSRecord.getRecordType()) && Operation.Remove.equals(operation))) {
            final ServiceEvent serviceEvent = dNSRecord.getServiceEvent(this);
            if (serviceEvent.getInfo() == null || !serviceEvent.getInfo().hasData()) {
                ServiceInfoImpl serviceInfoFromCache = getServiceInfoFromCache(serviceEvent.getType(), serviceEvent.getName(), "", false);
                if (serviceInfoFromCache.hasData()) {
                    serviceEvent = new ServiceEventImpl(this, serviceEvent.getType(), serviceEvent.getName(), serviceInfoFromCache);
                }
            }
            List list2 = (List) this._serviceListeners.get(serviceEvent.getType().toLowerCase());
            if (list2 != null) {
                synchronized (list2) {
                    list = new ArrayList<>(list2);
                }
            } else {
                list = Collections.emptyList();
            }
            logger.trace("{}.updating record for event: {} list {} operation: {}", getName(), serviceEvent, list, operation);
            if (!list.isEmpty()) {
                int i = AnonymousClass7.$SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation[operation.ordinal()];
                if (i == 1) {
                    for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : list) {
                        if (serviceListenerStatus.isSynchronous()) {
                            serviceListenerStatus.serviceAdded(serviceEvent);
                        } else {
                            this._executor.submit(new Runnable() {
                                public void run() {
                                    serviceListenerStatus.serviceAdded(serviceEvent);
                                }
                            });
                        }
                    }
                } else if (i == 2) {
                    for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus2 : list) {
                        if (serviceListenerStatus2.isSynchronous()) {
                            serviceListenerStatus2.serviceRemoved(serviceEvent);
                        } else {
                            this._executor.submit(new Runnable() {
                                public void run() {
                                    serviceListenerStatus2.serviceRemoved(serviceEvent);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /* renamed from: javax.jmdns.impl.JmDNSImpl$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                javax.jmdns.impl.JmDNSImpl$Operation[] r0 = javax.jmdns.impl.JmDNSImpl.Operation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation = r0
                javax.jmdns.impl.JmDNSImpl$Operation r1 = javax.jmdns.impl.JmDNSImpl.Operation.Add     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.JmDNSImpl$Operation r1 = javax.jmdns.impl.JmDNSImpl.Operation.Remove     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.JmDNSImpl.AnonymousClass7.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void handleRecord(DNSRecord dNSRecord, long j) {
        Operation operation = Operation.Noop;
        boolean isExpired = dNSRecord.isExpired(j);
        logger.debug("{} handle response: {}", (Object) getName(), (Object) dNSRecord);
        if (!dNSRecord.isServicesDiscoveryMetaQuery() && !dNSRecord.isDomainDiscoveryQuery()) {
            boolean isUnique = dNSRecord.isUnique();
            DNSRecord dNSRecord2 = (DNSRecord) getCache().getDNSEntry(dNSRecord);
            logger.debug("{} handle response cached record: {}", (Object) getName(), (Object) dNSRecord2);
            if (isUnique) {
                for (DNSEntry dNSEntry : getCache().getDNSEntryList(dNSRecord.getKey())) {
                    if (dNSRecord.getRecordType().equals(dNSEntry.getRecordType()) && dNSRecord.getRecordClass().equals(dNSEntry.getRecordClass())) {
                        DNSRecord dNSRecord3 = (DNSRecord) dNSEntry;
                        if (isOlderThanOneSecond(dNSRecord3, j)) {
                            logger.trace("setWillExpireSoon() on: {}", (Object) dNSEntry);
                            dNSRecord3.setWillExpireSoon(j);
                        }
                    }
                }
            }
            if (dNSRecord2 != null) {
                if (isExpired) {
                    if (dNSRecord.getTTL() == 0) {
                        operation = Operation.Noop;
                        logger.trace("Record is expired - setWillExpireSoon() on:\n\t{}", (Object) dNSRecord2);
                        dNSRecord2.setWillExpireSoon(j);
                    } else {
                        operation = Operation.Remove;
                        logger.trace("Record is expired - removeDNSEntry() on:\n\t{}", (Object) dNSRecord2);
                        getCache().removeDNSEntry(dNSRecord2);
                    }
                } else if (dNSRecord.sameValue(dNSRecord2) && (dNSRecord.sameSubtype(dNSRecord2) || dNSRecord.getSubtype().length() <= 0)) {
                    dNSRecord2.resetTTL(dNSRecord);
                    dNSRecord = dNSRecord2;
                } else if (dNSRecord.isSingleValued()) {
                    operation = Operation.Update;
                    logger.trace("Record (singleValued) has changed - replaceDNSEntry() on:\n\t{}\n\t{}", (Object) dNSRecord, (Object) dNSRecord2);
                    getCache().replaceDNSEntry(dNSRecord, dNSRecord2);
                } else {
                    operation = Operation.Add;
                    logger.trace("Record (multiValue) has changed - addDNSEntry on:\n\t{}", (Object) dNSRecord);
                    getCache().addDNSEntry(dNSRecord);
                }
            } else if (!isExpired) {
                operation = Operation.Add;
                logger.trace("Record not cached - addDNSEntry on:\n\t{}", (Object) dNSRecord);
                getCache().addDNSEntry(dNSRecord);
            }
        }
        if (dNSRecord.getRecordType() == DNSRecordType.TYPE_PTR) {
            if (dNSRecord.isServicesDiscoveryMetaQuery()) {
                if (!isExpired) {
                    registerServiceType(((DNSRecord.Pointer) dNSRecord).getAlias());
                    return;
                }
                return;
            } else if (registerServiceType(dNSRecord.getName()) && operation == Operation.Noop) {
                operation = Operation.RegisterServiceType;
            }
        }
        if (operation != Operation.Noop) {
            updateRecord(j, dNSRecord, operation);
        }
    }

    private boolean isOlderThanOneSecond(DNSRecord dNSRecord, long j) {
        return dNSRecord.getCreated() < j - 1000;
    }

    /* access modifiers changed from: package-private */
    public void handleResponse(DNSIncoming dNSIncoming) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        boolean z2 = false;
        for (DNSRecord next : aRecordsLast(dNSIncoming.getAllAnswers())) {
            handleRecord(next, currentTimeMillis);
            if (DNSRecordType.TYPE_A.equals(next.getRecordType()) || DNSRecordType.TYPE_AAAA.equals(next.getRecordType())) {
                z |= next.handleResponse(this);
            } else {
                z2 |= next.handleResponse(this);
            }
        }
        if (z || z2) {
            startProber();
        }
    }

    private List<DNSRecord> aRecordsLast(List<DNSRecord> list) {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList();
        for (DNSRecord next : list) {
            if (next.getRecordType().equals(DNSRecordType.TYPE_A) || next.getRecordType().equals(DNSRecordType.TYPE_AAAA)) {
                arrayList2.add(next);
            } else {
                arrayList.add(next);
            }
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void handleQuery(DNSIncoming dNSIncoming, InetAddress inetAddress, int i) throws IOException {
        logger.debug("{} handle query: {}", (Object) getName(), (Object) dNSIncoming);
        long currentTimeMillis = System.currentTimeMillis() + 120;
        boolean z = false;
        for (DNSRecord handleQuery : dNSIncoming.getAllAnswers()) {
            z |= handleQuery.handleQuery(this, currentTimeMillis);
        }
        ioLock();
        try {
            DNSIncoming dNSIncoming2 = this._plannedAnswer;
            if (dNSIncoming2 != null) {
                dNSIncoming2.append(dNSIncoming);
            } else {
                DNSIncoming clone = dNSIncoming.clone();
                if (dNSIncoming.isTruncated()) {
                    this._plannedAnswer = clone;
                }
                startResponder(clone, inetAddress, i);
            }
            ioUnlock();
            long currentTimeMillis2 = System.currentTimeMillis();
            for (DNSRecord handleRecord : dNSIncoming.getAnswers()) {
                handleRecord(handleRecord, currentTimeMillis2);
            }
            if (z) {
                startProber();
            }
        } catch (Throwable th) {
            ioUnlock();
            throw th;
        }
    }

    public void respondToQuery(DNSIncoming dNSIncoming) {
        ioLock();
        try {
            if (this._plannedAnswer == dNSIncoming) {
                this._plannedAnswer = null;
            }
        } finally {
            ioUnlock();
        }
    }

    public DNSOutgoing addAnswer(DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing, DNSRecord dNSRecord) throws IOException {
        if (dNSOutgoing == null) {
            dNSOutgoing = new DNSOutgoing(33792, false, dNSIncoming.getSenderUDPPayload());
        }
        try {
            dNSOutgoing.addAnswer(dNSIncoming, dNSRecord);
            return dNSOutgoing;
        } catch (IOException unused) {
            dNSOutgoing.setFlags(dNSOutgoing.getFlags() | 512);
            dNSOutgoing.setId(dNSIncoming.getId());
            send(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(33792, false, dNSIncoming.getSenderUDPPayload());
            dNSOutgoing2.addAnswer(dNSIncoming, dNSRecord);
            return dNSOutgoing2;
        }
    }

    public void send(DNSOutgoing dNSOutgoing) throws IOException {
        int i;
        InetAddress inetAddress;
        if (!dNSOutgoing.isEmpty()) {
            if (dNSOutgoing.getDestination() != null) {
                inetAddress = dNSOutgoing.getDestination().getAddress();
                i = dNSOutgoing.getDestination().getPort();
            } else {
                inetAddress = this._group;
                i = DNSConstants.MDNS_PORT;
            }
            byte[] data = dNSOutgoing.data();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, i);
            if (logger.isTraceEnabled()) {
                try {
                    DNSIncoming dNSIncoming = new DNSIncoming(datagramPacket);
                    if (logger.isTraceEnabled()) {
                        logger.trace("send({}) JmDNS out:{}", (Object) getName(), (Object) dNSIncoming.print(true));
                    }
                } catch (IOException e) {
                    logger.debug(getClass().toString(), (Object) ".send(" + getName() + ") - JmDNS can not parse what it sends!!!", (Object) e);
                }
            }
            MulticastSocket multicastSocket = this._socket;
            if (multicastSocket != null && !multicastSocket.isClosed()) {
                multicastSocket.send(datagramPacket);
            }
        }
    }

    public void purgeTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).purgeTimer();
    }

    public void purgeStateTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).purgeStateTimer();
    }

    public void cancelTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).cancelTimer();
    }

    public void cancelStateTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).cancelStateTimer();
    }

    public void startProber() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startProber();
    }

    public void startAnnouncer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startAnnouncer();
    }

    public void startRenewer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startRenewer();
    }

    public void startCanceler() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startCanceler();
    }

    public void startReaper() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startReaper();
    }

    public void startServiceInfoResolver(ServiceInfoImpl serviceInfoImpl) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startServiceInfoResolver(serviceInfoImpl);
    }

    public void startTypeResolver() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startTypeResolver();
    }

    public void startServiceResolver(String str) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startServiceResolver(str);
    }

    public void startResponder(DNSIncoming dNSIncoming, InetAddress inetAddress, int i) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startResponder(dNSIncoming, inetAddress, i);
    }

    protected class Shutdown implements Runnable {
        protected Shutdown() {
        }

        public void run() {
            try {
                JmDNSImpl.this._shutdown = null;
                JmDNSImpl.this.close();
            } catch (Throwable th) {
                System.err.println("Error while shuting down. " + th);
            }
        }
    }

    public void recover() {
        logger.debug("{}.recover()", (Object) getName());
        if (!isClosing() && !isClosed() && !isCanceling() && !isCanceled()) {
            synchronized (this._recoverLock) {
                if (cancelState()) {
                    String str = getName() + ".recover()";
                    logger.debug("{} thread {}", (Object) str, (Object) Thread.currentThread().getName());
                    new Thread(str) {
                        public void run() {
                            JmDNSImpl.this.__recover();
                        }
                    }.start();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void __recover() {
        logger.debug("{}.recover() Cleanning up", (Object) getName());
        logger.warn("RECOVERING");
        purgeTimer();
        ArrayList<ServiceInfo> arrayList = new ArrayList<>(getServices().values());
        unregisterAllServices();
        disposeServiceCollectors();
        waitForCanceled(5000);
        purgeStateTimer();
        closeMulticastSocket();
        getCache().clear();
        logger.debug("{}.recover() All is clean", (Object) getName());
        if (isCanceled()) {
            for (ServiceInfo serviceInfo : arrayList) {
                ((ServiceInfoImpl) serviceInfo).recoverState();
            }
            recoverState();
            try {
                openMulticastSocket(getLocalHost());
                start(arrayList);
            } catch (Exception e) {
                logger.warn(getName() + ".recover() Start services exception ", (Throwable) e);
            }
            logger.warn("{}.recover() We are back!", (Object) getName());
            return;
        }
        logger.warn("{}.recover() Could not recover we are Down!", (Object) getName());
        if (getDelegate() != null) {
            getDelegate().cannotRecoverFromIOError(getDns(), arrayList);
        }
    }

    public void cleanCache() {
        getCache().logCachedContent();
        long currentTimeMillis = System.currentTimeMillis();
        HashSet hashSet = new HashSet();
        for (DNSEntry next : getCache().allValues()) {
            try {
                DNSRecord dNSRecord = (DNSRecord) next;
                if (dNSRecord.isExpired(currentTimeMillis)) {
                    updateRecord(currentTimeMillis, dNSRecord, Operation.Remove);
                    logger.trace("Removing DNSEntry from cache: {}", (Object) next);
                    getCache().removeDNSEntry(dNSRecord);
                } else if (dNSRecord.isStaleAndShouldBeRefreshed(currentTimeMillis)) {
                    dNSRecord.incrementRefreshPercentage();
                    String lowerCase = dNSRecord.getServiceInfo().getType().toLowerCase();
                    if (hashSet.add(lowerCase)) {
                        renewServiceCollector(lowerCase);
                    }
                }
            } catch (Exception e) {
                logger.warn(getName() + ".Error while reaping records: " + next, (Throwable) e);
                logger.warn(toString());
            }
        }
    }

    public void close() {
        if (!isClosing()) {
            logger.debug("Cancelling JmDNS: {}", (Object) this);
            if (cancelState()) {
                logger.debug("Canceling the timer");
                cancelTimer();
                unregisterAllServices();
                disposeServiceCollectors();
                logger.debug("Wait for JmDNS cancel: {}", (Object) this);
                waitForCanceled(5000);
                logger.debug("Canceling the state timer");
                cancelStateTimer();
                this._executor.shutdown();
                closeMulticastSocket();
                if (this._shutdown != null) {
                    Runtime.getRuntime().removeShutdownHook(this._shutdown);
                }
                DNSTaskStarter.Factory.getInstance().disposeStarter(getDns());
                logger.debug("JmDNS closed.");
            }
            advanceState((DNSTask) null);
        }
    }

    @Deprecated
    public void printServices() {
        System.err.println(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("\n\t---- Local Host -----\n\t");
        sb.append(this._localHost);
        sb.append("\n\t---- Services -----");
        for (Map.Entry entry : this._services.entrySet()) {
            sb.append("\n\t\tService: ");
            sb.append((String) entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
        }
        sb.append("\n\t---- Types ----");
        for (ServiceTypeEntry serviceTypeEntry : this._serviceTypes.values()) {
            sb.append("\n\t\tType: ");
            sb.append(serviceTypeEntry.getType());
            sb.append(": ");
            sb.append(serviceTypeEntry.isEmpty() ? "no subtypes" : serviceTypeEntry);
        }
        sb.append("\n");
        sb.append(this._cache.toString());
        sb.append("\n\t---- Service Collectors ----");
        for (Map.Entry entry2 : this._serviceCollectors.entrySet()) {
            sb.append("\n\t\tService Collector: ");
            sb.append((String) entry2.getKey());
            sb.append(": ");
            sb.append(entry2.getValue());
        }
        sb.append("\n\t---- Service Listeners ----");
        for (Map.Entry entry3 : this._serviceListeners.entrySet()) {
            sb.append("\n\t\tService Listener: ");
            sb.append((String) entry3.getKey());
            sb.append(": ");
            sb.append(entry3.getValue());
        }
        return sb.toString();
    }

    public ServiceInfo[] list(String str) {
        return list(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo[] list(String str, long j) {
        cleanCache();
        String lowerCase = str.toLowerCase();
        if (isCanceling() || isCanceled()) {
            return new ServiceInfo[0];
        }
        ServiceCollector serviceCollector = (ServiceCollector) this._serviceCollectors.get(lowerCase);
        if (serviceCollector == null) {
            boolean z = this._serviceCollectors.putIfAbsent(lowerCase, new ServiceCollector(str)) == null;
            ServiceCollector serviceCollector2 = (ServiceCollector) this._serviceCollectors.get(lowerCase);
            if (z) {
                addServiceListener(str, serviceCollector2, true);
            }
            serviceCollector = serviceCollector2;
        }
        logger.debug("{}-collector: {}", (Object) getName(), (Object) serviceCollector);
        if (serviceCollector != null) {
            return serviceCollector.list(j);
        }
        return new ServiceInfo[0];
    }

    public Map<String, ServiceInfo[]> listBySubtype(String str) {
        return listBySubtype(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public Map<String, ServiceInfo[]> listBySubtype(String str, long j) {
        HashMap hashMap = new HashMap(5);
        for (ServiceInfo serviceInfo : list(str, j)) {
            String lowerCase = serviceInfo.getSubtype().toLowerCase();
            if (!hashMap.containsKey(lowerCase)) {
                hashMap.put(lowerCase, new ArrayList(10));
            }
            ((List) hashMap.get(lowerCase)).add(serviceInfo);
        }
        HashMap hashMap2 = new HashMap(hashMap.size());
        for (Map.Entry entry : hashMap.entrySet()) {
            List list = (List) entry.getValue();
            hashMap2.put((String) entry.getKey(), list.toArray(new ServiceInfo[list.size()]));
        }
        return hashMap2;
    }

    private void disposeServiceCollectors() {
        logger.debug("disposeServiceCollectors()");
        for (Map.Entry entry : this._serviceCollectors.entrySet()) {
            ServiceCollector serviceCollector = (ServiceCollector) entry.getValue();
            if (serviceCollector != null) {
                String str = (String) entry.getKey();
                removeServiceListener(str, serviceCollector);
                this._serviceCollectors.remove(str, serviceCollector);
            }
        }
    }

    private static class ServiceCollector implements ServiceListener {
        private final ConcurrentMap<String, ServiceEvent> _events = new ConcurrentHashMap();
        private final ConcurrentMap<String, ServiceInfo> _infos = new ConcurrentHashMap();
        private volatile boolean _needToWaitForInfos;
        private final String _type;

        public ServiceCollector(String str) {
            this._type = str;
            this._needToWaitForInfos = true;
        }

        public void serviceAdded(ServiceEvent serviceEvent) {
            synchronized (this) {
                ServiceInfo info = serviceEvent.getInfo();
                if (info == null || !info.hasData()) {
                    ServiceInfoImpl resolveServiceInfo = ((JmDNSImpl) serviceEvent.getDNS()).resolveServiceInfo(serviceEvent.getType(), serviceEvent.getName(), info != null ? info.getSubtype() : "", true);
                    if (resolveServiceInfo != null) {
                        this._infos.put(serviceEvent.getName(), resolveServiceInfo);
                    } else {
                        this._events.put(serviceEvent.getName(), serviceEvent);
                    }
                } else {
                    this._infos.put(serviceEvent.getName(), info);
                }
            }
        }

        public void serviceRemoved(ServiceEvent serviceEvent) {
            synchronized (this) {
                this._infos.remove(serviceEvent.getName());
                this._events.remove(serviceEvent.getName());
            }
        }

        public void serviceResolved(ServiceEvent serviceEvent) {
            synchronized (this) {
                this._infos.put(serviceEvent.getName(), serviceEvent.getInfo());
                this._events.remove(serviceEvent.getName());
            }
        }

        public ServiceInfo[] list(long j) {
            if (this._infos.isEmpty() || !this._events.isEmpty() || this._needToWaitForInfos) {
                long j2 = j / 200;
                if (j2 < 1) {
                    j2 = 1;
                }
                for (int i = 0; ((long) i) < j2; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException unused) {
                    }
                    if (this._events.isEmpty() && !this._infos.isEmpty() && !this._needToWaitForInfos) {
                        break;
                    }
                }
            }
            this._needToWaitForInfos = false;
            return (ServiceInfo[]) this._infos.values().toArray(new ServiceInfo[this._infos.size()]);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("\n\tType: ");
            sb.append(this._type);
            if (this._infos.isEmpty()) {
                sb.append("\n\tNo services collected.");
            } else {
                sb.append("\n\tServices");
                for (Map.Entry entry : this._infos.entrySet()) {
                    sb.append("\n\t\tService: ");
                    sb.append((String) entry.getKey());
                    sb.append(": ");
                    sb.append(entry.getValue());
                }
            }
            if (this._events.isEmpty()) {
                sb.append("\n\tNo event queued.");
            } else {
                sb.append("\n\tEvents");
                for (Map.Entry entry2 : this._events.entrySet()) {
                    sb.append("\n\t\tEvent: ");
                    sb.append((String) entry2.getKey());
                    sb.append(": ");
                    sb.append(entry2.getValue());
                }
            }
            return sb.toString();
        }
    }

    static String toUnqualifiedName(String str, String str2) {
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        return (!lowerCase2.endsWith(lowerCase) || lowerCase2.equals(lowerCase)) ? str2 : str2.substring(0, (str2.length() - str.length()) - 1);
    }

    public Map<String, ServiceInfo> getServices() {
        return this._services;
    }

    public void setLastThrottleIncrement(long j) {
        this._lastThrottleIncrement = j;
    }

    public long getLastThrottleIncrement() {
        return this._lastThrottleIncrement;
    }

    public void setThrottle(int i) {
        this._throttle = i;
    }

    public int getThrottle() {
        return this._throttle;
    }

    public static Random getRandom() {
        return _random;
    }

    public void ioLock() {
        this._ioLock.lock();
    }

    public void ioUnlock() {
        this._ioLock.unlock();
    }

    public void setPlannedAnswer(DNSIncoming dNSIncoming) {
        this._plannedAnswer = dNSIncoming;
    }

    public DNSIncoming getPlannedAnswer() {
        return this._plannedAnswer;
    }

    /* access modifiers changed from: package-private */
    public void setLocalHost(HostInfo hostInfo) {
        this._localHost = hostInfo;
    }

    public Map<String, ServiceTypeEntry> getServiceTypes() {
        return this._serviceTypes;
    }

    public MulticastSocket getSocket() {
        return this._socket;
    }

    public InetAddress getGroup() {
        return this._group;
    }

    public JmDNS.Delegate getDelegate() {
        return this._delegate;
    }

    public JmDNS.Delegate setDelegate(JmDNS.Delegate delegate) {
        JmDNS.Delegate delegate2 = this._delegate;
        this._delegate = delegate;
        return delegate2;
    }
}
