package javax.jmdns.impl.tasks.state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DNSStateTask extends DNSTask {
    private static int _defaultTTL = DNSConstants.DNS_TTL;
    static Logger logger = LoggerFactory.getLogger(DNSStateTask.class.getName());
    private DNSState _taskState = null;
    private final int _ttl;

    /* access modifiers changed from: protected */
    public abstract void advanceTask();

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing buildOutgoingForDNS(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing buildOutgoingForInfo(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract boolean checkRunCondition();

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing createOugoing();

    public abstract String getTaskDescription();

    /* access modifiers changed from: protected */
    public abstract void recoverTask(Throwable th);

    public static int defaultTTL() {
        return _defaultTTL;
    }

    public static void setDefaultTTL(int i) {
        _defaultTTL = i;
    }

    public DNSStateTask(JmDNSImpl jmDNSImpl, int i) {
        super(jmDNSImpl);
        this._ttl = i;
    }

    public int getTTL() {
        return this._ttl;
    }

    /* access modifiers changed from: protected */
    public void associate(DNSState dNSState) {
        synchronized (getDns()) {
            getDns().associateWithTask(this, dNSState);
        }
        Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).associateWithTask(this, dNSState);
        }
    }

    /* access modifiers changed from: protected */
    public void removeAssociation() {
        synchronized (getDns()) {
            getDns().removeAssociationWithTask(this);
        }
        Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).removeAssociationWithTask(this);
        }
    }

    public void run() {
        DNSOutgoing createOugoing = createOugoing();
        try {
            if (!checkRunCondition()) {
                cancel();
                return;
            }
            ArrayList arrayList = new ArrayList();
            synchronized (getDns()) {
                if (getDns().isAssociatedWithTask(this, getTaskState())) {
                    logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), getDns().getName());
                    arrayList.add(getDns());
                    createOugoing = buildOutgoingForDNS(createOugoing);
                }
            }
            Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
            while (it.hasNext()) {
                ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) it.next();
                synchronized (serviceInfoImpl) {
                    if (serviceInfoImpl.isAssociatedWithTask(this, getTaskState())) {
                        logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), serviceInfoImpl.getQualifiedName());
                        arrayList.add(serviceInfoImpl);
                        createOugoing = buildOutgoingForInfo(serviceInfoImpl, createOugoing);
                    }
                }
            }
            if (!createOugoing.isEmpty()) {
                logger.debug("{}.run() JmDNS {} #{}", getName(), getTaskDescription(), getTaskState());
                getDns().send(createOugoing);
                advanceObjectsState(arrayList);
                advanceTask();
                return;
            }
            advanceObjectsState(arrayList);
            cancel();
        } catch (Throwable th) {
            logger.warn(getName() + ".run() exception ", th);
            recoverTask(th);
        }
    }

    /* access modifiers changed from: protected */
    public void advanceObjectsState(List<DNSStatefulObject> list) {
        if (list != null) {
            for (DNSStatefulObject next : list) {
                synchronized (next) {
                    next.advanceState(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public DNSState getTaskState() {
        return this._taskState;
    }

    /* access modifiers changed from: protected */
    public void setTaskState(DNSState dNSState) {
        this._taskState = dNSState;
    }
}
