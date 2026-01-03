package javax.jmdns.impl;

import java.util.EventListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerStatus<T extends EventListener> {
    public static final boolean ASYNCHRONOUS = false;
    public static final boolean SYNCHRONOUS = true;
    private final T _listener;
    private final boolean _synch;

    public static class ServiceListenerStatus extends ListenerStatus<ServiceListener> {
        private static Logger logger = LoggerFactory.getLogger(ServiceListenerStatus.class.getName());
        private final ConcurrentMap<String, ServiceInfo> _addedServices = new ConcurrentHashMap(32);

        public ServiceListenerStatus(ServiceListener serviceListener, boolean z) {
            super(serviceListener, z);
        }

        /* access modifiers changed from: package-private */
        public void serviceAdded(ServiceEvent serviceEvent) {
            if (this._addedServices.putIfAbsent(serviceEvent.getName() + "." + serviceEvent.getType(), serviceEvent.getInfo().clone()) == null) {
                ((ServiceListener) getListener()).serviceAdded(serviceEvent);
                ServiceInfo info = serviceEvent.getInfo();
                if (info != null && info.hasData()) {
                    ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                    return;
                }
                return;
            }
            logger.debug("Service Added called for a service already added: {}", (Object) serviceEvent);
        }

        /* access modifiers changed from: package-private */
        public void serviceRemoved(ServiceEvent serviceEvent) {
            String str = serviceEvent.getName() + "." + serviceEvent.getType();
            ConcurrentMap<String, ServiceInfo> concurrentMap = this._addedServices;
            if (concurrentMap.remove(str, concurrentMap.get(str))) {
                ((ServiceListener) getListener()).serviceRemoved(serviceEvent);
            } else {
                logger.debug("Service Removed called for a service already removed: {}", (Object) serviceEvent);
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void serviceResolved(ServiceEvent serviceEvent) {
            ServiceInfo info = serviceEvent.getInfo();
            if (info == null || !info.hasData()) {
                logger.warn("Service Resolved called for an unresolved event: {}", (Object) serviceEvent);
            } else {
                String str = serviceEvent.getName() + "." + serviceEvent.getType();
                ServiceInfo serviceInfo = (ServiceInfo) this._addedServices.get(str);
                if (_sameInfo(info, serviceInfo)) {
                    logger.debug("Service Resolved called for a service already resolved: {}", (Object) serviceEvent);
                } else if (serviceInfo == null) {
                    if (this._addedServices.putIfAbsent(str, info.clone()) == null) {
                        ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                    }
                } else if (this._addedServices.replace(str, serviceInfo, info.clone())) {
                    ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                }
            }
        }

        private static final boolean _sameInfo(ServiceInfo serviceInfo, ServiceInfo serviceInfo2) {
            if (serviceInfo == null || serviceInfo2 == null || !serviceInfo.equals(serviceInfo2)) {
                return false;
            }
            byte[] textBytes = serviceInfo.getTextBytes();
            byte[] textBytes2 = serviceInfo2.getTextBytes();
            if (textBytes.length != textBytes2.length) {
                return false;
            }
            for (int i = 0; i < textBytes.length; i++) {
                if (textBytes[i] != textBytes2[i]) {
                    return false;
                }
            }
            if (!serviceInfo.hasSameAddresses(serviceInfo2)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            sb.append("[Status for ");
            sb.append(((ServiceListener) getListener()).toString());
            if (this._addedServices.isEmpty()) {
                sb.append(" no type event ");
            } else {
                sb.append(" (");
                for (String str : this._addedServices.keySet()) {
                    sb.append(str + ", ");
                }
                sb.append(") ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static class ServiceTypeListenerStatus extends ListenerStatus<ServiceTypeListener> {
        private static Logger logger = LoggerFactory.getLogger(ServiceTypeListenerStatus.class.getName());
        private final ConcurrentMap<String, String> _addedTypes = new ConcurrentHashMap(32);

        public ServiceTypeListenerStatus(ServiceTypeListener serviceTypeListener, boolean z) {
            super(serviceTypeListener, z);
        }

        /* access modifiers changed from: package-private */
        public void serviceTypeAdded(ServiceEvent serviceEvent) {
            if (this._addedTypes.putIfAbsent(serviceEvent.getType(), serviceEvent.getType()) == null) {
                ((ServiceTypeListener) getListener()).serviceTypeAdded(serviceEvent);
            } else {
                logger.trace("Service Type Added called for a service type already added: {}", (Object) serviceEvent);
            }
        }

        /* access modifiers changed from: package-private */
        public void subTypeForServiceTypeAdded(ServiceEvent serviceEvent) {
            if (this._addedTypes.putIfAbsent(serviceEvent.getType(), serviceEvent.getType()) == null) {
                ((ServiceTypeListener) getListener()).subTypeForServiceTypeAdded(serviceEvent);
            } else {
                logger.trace("Service Sub Type Added called for a service sub type already added: {}", (Object) serviceEvent);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            sb.append("[Status for ");
            sb.append(((ServiceTypeListener) getListener()).toString());
            if (this._addedTypes.isEmpty()) {
                sb.append(" no type event ");
            } else {
                sb.append(" (");
                for (String str : this._addedTypes.keySet()) {
                    sb.append(str + ", ");
                }
                sb.append(") ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public ListenerStatus(T t, boolean z) {
        this._listener = t;
        this._synch = z;
    }

    public T getListener() {
        return this._listener;
    }

    public boolean isSynchronous() {
        return this._synch;
    }

    public int hashCode() {
        return getListener().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ListenerStatus) && getListener().equals(((ListenerStatus) obj).getListener());
    }

    public String toString() {
        return "[Status for " + getListener().toString() + "]";
    }
}
