package javax.jmdns.impl;

import com.google.android.gms.location.DeviceOrientationRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.NetworkTopologyDiscovery;
import javax.jmdns.NetworkTopologyEvent;
import javax.jmdns.NetworkTopologyListener;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmmDNSImpl implements JmmDNS, NetworkTopologyListener, ServiceInfoImpl.Delegate {
    private static Logger logger = LoggerFactory.getLogger(JmmDNSImpl.class.getName());
    private final AtomicBoolean _closed;
    private final AtomicBoolean _isClosing;
    private final ExecutorService _jmDNSExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("JmmDNS"));
    private final ConcurrentMap<InetAddress, JmDNS> _knownMDNS = new ConcurrentHashMap();
    private final ExecutorService _listenerExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory("JmmDNS Listeners"));
    private final Set<NetworkTopologyListener> _networkListeners = Collections.synchronizedSet(new HashSet());
    private final ConcurrentMap<String, List<ServiceListener>> _serviceListeners;
    private final Set<String> _serviceTypes;
    private final ConcurrentMap<String, ServiceInfo> _services = new ConcurrentHashMap(20);
    private final Timer _timer;
    private final Set<ServiceTypeListener> _typeListeners;

    public JmmDNSImpl() {
        Timer timer = new Timer("Multihomed mDNS.Timer", true);
        this._timer = timer;
        this._serviceListeners = new ConcurrentHashMap();
        this._typeListeners = Collections.synchronizedSet(new HashSet());
        this._serviceTypes = Collections.synchronizedSet(new HashSet());
        new NetworkChecker(this, NetworkTopologyDiscovery.Factory.getInstance()).start(timer);
        this._isClosing = new AtomicBoolean(false);
        this._closed = new AtomicBoolean(false);
    }

    public void close() throws IOException {
        if (this._isClosing.compareAndSet(false, true)) {
            logger.debug("Cancelling JmmDNS: {}", (Object) this);
            this._timer.cancel();
            this._listenerExecutor.shutdown();
            this._jmDNSExecutor.shutdown();
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(new NamedThreadFactory("JmmDNS.close"));
            try {
                for (final JmDNS jmDNS : getDNS()) {
                    newCachedThreadPool.submit(new Runnable() {
                        public void run() {
                            try {
                                jmDNS.close();
                            } catch (IOException unused) {
                            }
                        }
                    });
                }
                try {
                    newCachedThreadPool.awaitTermination(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.warn("Exception ", (Throwable) e);
                }
                this._knownMDNS.clear();
                this._services.clear();
                this._serviceListeners.clear();
                this._typeListeners.clear();
                this._serviceTypes.clear();
                this._closed.set(true);
                JmmDNS.Factory.close();
            } finally {
                newCachedThreadPool.shutdown();
            }
        }
    }

    public String[] getNames() {
        HashSet hashSet = new HashSet();
        for (JmDNS name : getDNS()) {
            hashSet.add(name.getName());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public String[] getHostNames() {
        HashSet hashSet = new HashSet();
        for (JmDNS hostName : getDNS()) {
            hashSet.add(hostName.getHostName());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public InetAddress[] getInetAddresses() throws IOException {
        HashSet hashSet = new HashSet();
        for (JmDNS inetAddress : getDNS()) {
            hashSet.add(inetAddress.getInetAddress());
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    public JmDNS[] getDNS() {
        JmDNS[] jmDNSArr;
        synchronized (this._knownMDNS) {
            jmDNSArr = (JmDNS[]) this._knownMDNS.values().toArray(new JmDNS[this._knownMDNS.size()]);
        }
        return jmDNSArr;
    }

    @Deprecated
    public InetAddress[] getInterfaces() throws IOException {
        HashSet hashSet = new HashSet();
        for (JmDNS jmDNS : getDNS()) {
            hashSet.add(jmDNS.getInterface());
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    public ServiceInfo[] getServiceInfos(String str, String str2) {
        return getServiceInfos(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo[] getServiceInfos(String str, String str2, long j) {
        return getServiceInfos(str, str2, false, j);
    }

    public ServiceInfo[] getServiceInfos(String str, String str2, boolean z) {
        return getServiceInfos(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo[] getServiceInfos(String str, String str2, boolean z, long j) {
        List<Future> emptyList;
        JmDNS[] dns = getDNS();
        HashSet hashSet = new HashSet(dns.length);
        if (dns.length > 0) {
            ArrayList arrayList = new ArrayList(dns.length);
            for (final JmDNS jmDNS : dns) {
                final String str3 = str;
                final String str4 = str2;
                final boolean z2 = z;
                final long j2 = j;
                arrayList.add(new Callable<ServiceInfo>() {
                    public ServiceInfo call() throws Exception {
                        return jmDNS.getServiceInfo(str3, str4, z2, j2);
                    }
                });
            }
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(arrayList.size(), new NamedThreadFactory("JmmDNS.getServiceInfos"));
            try {
                emptyList = Collections.emptyList();
                emptyList = newFixedThreadPool.invokeAll(arrayList, j + 100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Interrupted ", (Throwable) e);
                Thread.currentThread().interrupt();
            } catch (Throwable th) {
                newFixedThreadPool.shutdown();
                throw th;
            }
            for (Future future : emptyList) {
                if (!future.isCancelled()) {
                    try {
                        ServiceInfo serviceInfo = (ServiceInfo) future.get();
                        if (serviceInfo != null) {
                            hashSet.add(serviceInfo);
                        }
                    } catch (InterruptedException e2) {
                        logger.debug("Interrupted ", (Throwable) e2);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e3) {
                        logger.warn("Exception ", (Throwable) e3);
                    }
                }
            }
            newFixedThreadPool.shutdown();
        }
        return (ServiceInfo[]) hashSet.toArray(new ServiceInfo[hashSet.size()]);
    }

    public void requestServiceInfo(String str, String str2) {
        requestServiceInfo(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public void requestServiceInfo(String str, String str2, boolean z) {
        requestServiceInfo(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public void requestServiceInfo(String str, String str2, long j) {
        requestServiceInfo(str, str2, false, j);
    }

    public void requestServiceInfo(String str, String str2, boolean z, long j) {
        for (final JmDNS jmDNS : getDNS()) {
            final String str3 = str;
            final String str4 = str2;
            final boolean z2 = z;
            final long j2 = j;
            this._jmDNSExecutor.submit(new Runnable() {
                public void run() {
                    jmDNS.requestServiceInfo(str3, str4, z2, j2);
                }
            });
        }
    }

    public void addServiceTypeListener(ServiceTypeListener serviceTypeListener) throws IOException {
        this._typeListeners.add(serviceTypeListener);
        for (JmDNS addServiceTypeListener : getDNS()) {
            addServiceTypeListener.addServiceTypeListener(serviceTypeListener);
        }
    }

    public void removeServiceTypeListener(ServiceTypeListener serviceTypeListener) {
        this._typeListeners.remove(serviceTypeListener);
        for (JmDNS removeServiceTypeListener : getDNS()) {
            removeServiceTypeListener.removeServiceTypeListener(serviceTypeListener);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addServiceListener(java.lang.String r5, javax.jmdns.ServiceListener r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toLowerCase()
            java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<javax.jmdns.ServiceListener>> r1 = r4._serviceListeners
            java.lang.Object r1 = r1.get(r0)
            java.util.List r1 = (java.util.List) r1
            if (r1 != 0) goto L_0x0021
            java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<javax.jmdns.ServiceListener>> r1 = r4._serviceListeners
            java.util.LinkedList r2 = new java.util.LinkedList
            r2.<init>()
            r1.putIfAbsent(r0, r2)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<javax.jmdns.ServiceListener>> r1 = r4._serviceListeners
            java.lang.Object r0 = r1.get(r0)
            r1 = r0
            java.util.List r1 = (java.util.List) r1
        L_0x0021:
            if (r1 == 0) goto L_0x0032
            monitor-enter(r1)
            boolean r0 = r1.contains(r6)     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x002d
            r1.add(r6)     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r1)     // Catch:{ all -> 0x002f }
            goto L_0x0032
        L_0x002f:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002f }
            throw r5
        L_0x0032:
            javax.jmdns.JmDNS[] r0 = r4.getDNS()
            int r1 = r0.length
            r2 = 0
        L_0x0038:
            if (r2 >= r1) goto L_0x0042
            r3 = r0[r2]
            r3.addServiceListener(r5, r6)
            int r2 = r2 + 1
            goto L_0x0038
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.JmmDNSImpl.addServiceListener(java.lang.String, javax.jmdns.ServiceListener):void");
    }

    public void removeServiceListener(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List list = (List) this._serviceListeners.get(lowerCase);
        if (list != null) {
            synchronized (list) {
                list.remove(serviceListener);
                if (list.isEmpty()) {
                    this._serviceListeners.remove(lowerCase, list);
                }
            }
        }
        for (JmDNS removeServiceListener : getDNS()) {
            removeServiceListener.removeServiceListener(str, serviceListener);
        }
    }

    public void textValueUpdated(ServiceInfo serviceInfo, byte[] bArr) {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            for (JmDNS jmDNS : dns) {
                ServiceInfo serviceInfo2 = ((JmDNSImpl) jmDNS).getServices().get(serviceInfo.getQualifiedName());
                if (serviceInfo2 != null) {
                    serviceInfo2.setText(bArr);
                } else {
                    logger.warn("We have a mDNS that does not know about the service info being updated.");
                }
            }
        }
    }

    public void registerService(ServiceInfo serviceInfo) throws IOException {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            for (JmDNS registerService : dns) {
                registerService.registerService(serviceInfo.clone());
            }
            ((ServiceInfoImpl) serviceInfo).setDelegate(this);
            this._services.put(serviceInfo.getQualifiedName(), serviceInfo);
        }
    }

    public void unregisterService(ServiceInfo serviceInfo) {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            this._services.remove(serviceInfo.getQualifiedName());
            for (JmDNS unregisterService : dns) {
                unregisterService.unregisterService(serviceInfo);
            }
            ((ServiceInfoImpl) serviceInfo).setDelegate((ServiceInfoImpl.Delegate) null);
        }
    }

    public void unregisterAllServices() {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            this._services.clear();
            for (JmDNS unregisterAllServices : dns) {
                unregisterAllServices.unregisterAllServices();
            }
        }
    }

    public void registerServiceType(String str) {
        this._serviceTypes.add(str);
        for (JmDNS registerServiceType : getDNS()) {
            registerServiceType.registerServiceType(str);
        }
    }

    public ServiceInfo[] list(String str) {
        return list(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public ServiceInfo[] list(String str, long j) {
        List<Future> emptyList;
        JmDNS[] dns = getDNS();
        HashSet hashSet = new HashSet(dns.length * 5);
        if (dns.length > 0) {
            ArrayList arrayList = new ArrayList(dns.length);
            for (final JmDNS jmDNS : dns) {
                final String str2 = str;
                final long j2 = j;
                arrayList.add(new Callable<List<ServiceInfo>>() {
                    public List<ServiceInfo> call() throws Exception {
                        return Arrays.asList(jmDNS.list(str2, j2));
                    }
                });
            }
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(arrayList.size(), new NamedThreadFactory("JmmDNS.list"));
            try {
                emptyList = Collections.emptyList();
                emptyList = newFixedThreadPool.invokeAll(arrayList, 100 + j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Interrupted ", (Throwable) e);
                Thread.currentThread().interrupt();
            } catch (Throwable th) {
                newFixedThreadPool.shutdown();
                throw th;
            }
            for (Future future : emptyList) {
                if (!future.isCancelled()) {
                    try {
                        hashSet.addAll((Collection) future.get());
                    } catch (InterruptedException e2) {
                        logger.debug("Interrupted ", (Throwable) e2);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e3) {
                        logger.warn("Exception ", (Throwable) e3);
                    }
                }
            }
            newFixedThreadPool.shutdown();
        }
        return (ServiceInfo[]) hashSet.toArray(new ServiceInfo[hashSet.size()]);
    }

    public Map<String, ServiceInfo[]> listBySubtype(String str) {
        return listBySubtype(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    public Map<String, ServiceInfo[]> listBySubtype(String str, long j) {
        HashMap hashMap = new HashMap(5);
        for (ServiceInfo serviceInfo : list(str, j)) {
            String subtype = serviceInfo.getSubtype();
            if (!hashMap.containsKey(subtype)) {
                hashMap.put(subtype, new ArrayList(10));
            }
            ((List) hashMap.get(subtype)).add(serviceInfo);
        }
        HashMap hashMap2 = new HashMap(hashMap.size());
        for (Map.Entry entry : hashMap.entrySet()) {
            List list = (List) entry.getValue();
            hashMap2.put((String) entry.getKey(), list.toArray(new ServiceInfo[list.size()]));
        }
        return hashMap2;
    }

    public void addNetworkTopologyListener(NetworkTopologyListener networkTopologyListener) {
        this._networkListeners.add(networkTopologyListener);
    }

    public void removeNetworkTopologyListener(NetworkTopologyListener networkTopologyListener) {
        this._networkListeners.remove(networkTopologyListener);
    }

    public NetworkTopologyListener[] networkListeners() {
        Set<NetworkTopologyListener> set = this._networkListeners;
        return (NetworkTopologyListener[]) set.toArray(new NetworkTopologyListener[set.size()]);
    }

    public void inetAddressAdded(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            if (!this._knownMDNS.containsKey(inetAddress)) {
                synchronized (this._knownMDNS) {
                    if (!this._knownMDNS.containsKey(inetAddress)) {
                        JmDNS create = JmDNS.create(inetAddress);
                        if (this._knownMDNS.putIfAbsent(inetAddress, create) == null) {
                            final Set<String> set = this._serviceTypes;
                            final Collection values = this._services.values();
                            final Set<ServiceTypeListener> set2 = this._typeListeners;
                            final ConcurrentMap<String, List<ServiceListener>> concurrentMap = this._serviceListeners;
                            final JmDNS jmDNS = create;
                            this._jmDNSExecutor.submit(new Runnable() {
                                public void run() {
                                    for (String registerServiceType : set) {
                                        jmDNS.registerServiceType(registerServiceType);
                                    }
                                    for (ServiceInfo clone : values) {
                                        try {
                                            jmDNS.registerService(clone.clone());
                                        } catch (IOException unused) {
                                        }
                                    }
                                    for (ServiceTypeListener addServiceTypeListener : set2) {
                                        try {
                                            jmDNS.addServiceTypeListener(addServiceTypeListener);
                                        } catch (IOException unused2) {
                                        }
                                    }
                                    for (Map.Entry entry : concurrentMap.entrySet()) {
                                        String str = (String) entry.getKey();
                                        List<ServiceListener> list = (List) entry.getValue();
                                        synchronized (list) {
                                            for (ServiceListener addServiceListener : list) {
                                                jmDNS.addServiceListener(str, addServiceListener);
                                            }
                                        }
                                    }
                                }
                            });
                            final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl(create, inetAddress);
                            for (final NetworkTopologyListener networkTopologyListener : networkListeners()) {
                                this._listenerExecutor.submit(new Runnable() {
                                    public void run() {
                                        networkTopologyListener.inetAddressAdded(networkTopologyEventImpl);
                                    }
                                });
                            }
                        } else {
                            create.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("Unexpected unhandled exception: " + e);
        }
    }

    public void inetAddressRemoved(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            if (this._knownMDNS.containsKey(inetAddress)) {
                synchronized (this._knownMDNS) {
                    if (this._knownMDNS.containsKey(inetAddress)) {
                        JmDNS jmDNS = (JmDNS) this._knownMDNS.remove(inetAddress);
                        jmDNS.close();
                        final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl(jmDNS, inetAddress);
                        for (final NetworkTopologyListener networkTopologyListener : networkListeners()) {
                            this._listenerExecutor.submit(new Runnable() {
                                public void run() {
                                    networkTopologyListener.inetAddressRemoved(networkTopologyEventImpl);
                                }
                            });
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("Unexpected unhandled exception: " + e);
        }
    }

    static class NetworkChecker extends TimerTask {
        private static Logger logger1 = LoggerFactory.getLogger(NetworkChecker.class.getName());
        private Set<InetAddress> _knownAddresses = Collections.synchronizedSet(new HashSet());
        private final NetworkTopologyListener _mmDNS;
        private final NetworkTopologyDiscovery _topology;

        public NetworkChecker(NetworkTopologyListener networkTopologyListener, NetworkTopologyDiscovery networkTopologyDiscovery) {
            this._mmDNS = networkTopologyListener;
            this._topology = networkTopologyDiscovery;
        }

        public void start(Timer timer) {
            run();
            timer.schedule(this, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
        }

        public void run() {
            try {
                InetAddress[] inetAddresses = this._topology.getInetAddresses();
                HashSet hashSet = new HashSet(inetAddresses.length);
                for (InetAddress inetAddress : inetAddresses) {
                    hashSet.add(inetAddress);
                    if (!this._knownAddresses.contains(inetAddress)) {
                        this._mmDNS.inetAddressAdded(new NetworkTopologyEventImpl(this._mmDNS, inetAddress));
                    }
                }
                for (InetAddress next : this._knownAddresses) {
                    if (!hashSet.contains(next)) {
                        this._mmDNS.inetAddressRemoved(new NetworkTopologyEventImpl(this._mmDNS, next));
                    }
                }
                this._knownAddresses = hashSet;
            } catch (Exception e) {
                logger1.warn("Unexpected unhandled exception: " + e);
            }
        }
    }
}
