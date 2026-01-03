package javax.jmdns.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DNSStatefulObject {
    boolean advanceState(DNSTask dNSTask);

    void associateWithTask(DNSTask dNSTask, DNSState dNSState);

    boolean cancelState();

    boolean closeState();

    JmDNSImpl getDns();

    boolean isAnnounced();

    boolean isAnnouncing();

    boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState);

    boolean isCanceled();

    boolean isCanceling();

    boolean isClosed();

    boolean isClosing();

    boolean isProbing();

    boolean recoverState();

    void removeAssociationWithTask(DNSTask dNSTask);

    boolean revertState();

    boolean waitForAnnounced(long j);

    boolean waitForCanceled(long j);

    public static final class DNSStatefulObjectSemaphore {
        private static Logger logger = LoggerFactory.getLogger(DNSStatefulObjectSemaphore.class.getName());
        private final String _name;
        private final ConcurrentMap<Thread, Semaphore> _semaphores = new ConcurrentHashMap(50);

        public DNSStatefulObjectSemaphore(String str) {
            this._name = str;
        }

        public void waitForEvent(long j) {
            Thread currentThread = Thread.currentThread();
            if (((Semaphore) this._semaphores.get(currentThread)) == null) {
                Semaphore semaphore = new Semaphore(1, true);
                semaphore.drainPermits();
                this._semaphores.putIfAbsent(currentThread, semaphore);
            }
            try {
                ((Semaphore) this._semaphores.get(currentThread)).tryAcquire(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Exception ", (Throwable) e);
            }
        }

        public void signalEvent() {
            Collection<Semaphore> values = this._semaphores.values();
            for (Semaphore semaphore : values) {
                semaphore.release();
                values.remove(semaphore);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("Semaphore: ");
            sb.append(this._name);
            if (this._semaphores.size() == 0) {
                sb.append(" no semaphores.");
            } else {
                sb.append(" semaphores:\n");
                for (Map.Entry entry : this._semaphores.entrySet()) {
                    sb.append("\tThread: ");
                    sb.append(((Thread) entry.getKey()).getName());
                    sb.append(' ');
                    sb.append(entry.getValue());
                    sb.append(10);
                }
            }
            return sb.toString();
        }
    }

    public static class DefaultImplementation extends ReentrantLock implements DNSStatefulObject {
        private static Logger logger = LoggerFactory.getLogger(DefaultImplementation.class.getName());
        private static final long serialVersionUID = -3264781576883412227L;
        private final DNSStatefulObjectSemaphore _announcing = new DNSStatefulObjectSemaphore("Announce");
        private final DNSStatefulObjectSemaphore _canceling = new DNSStatefulObjectSemaphore("Cancel");
        private volatile JmDNSImpl _dns = null;
        protected volatile DNSState _state = DNSState.PROBING_1;
        protected volatile DNSTask _task = null;

        public JmDNSImpl getDns() {
            return this._dns;
        }

        /* access modifiers changed from: protected */
        public void setDns(JmDNSImpl jmDNSImpl) {
            this._dns = jmDNSImpl;
        }

        public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
            if (this._task == null && this._state == dNSState) {
                lock();
                try {
                    if (this._task == null && this._state == dNSState) {
                        setTask(dNSTask);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public void removeAssociationWithTask(DNSTask dNSTask) {
            if (this._task == dNSTask) {
                lock();
                try {
                    if (this._task == dNSTask) {
                        setTask((DNSTask) null);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
            lock();
            try {
                return this._task == dNSTask && this._state == dNSState;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: protected */
        public void setTask(DNSTask dNSTask) {
            this._task = dNSTask;
        }

        /* access modifiers changed from: protected */
        public void setState(DNSState dNSState) {
            lock();
            try {
                this._state = dNSState;
                if (isAnnounced()) {
                    this._announcing.signalEvent();
                }
                if (isCanceled()) {
                    this._canceling.signalEvent();
                    this._announcing.signalEvent();
                }
            } finally {
                unlock();
            }
        }

        public boolean advanceState(DNSTask dNSTask) {
            if (this._task != dNSTask) {
                return true;
            }
            lock();
            try {
                if (this._task == dNSTask) {
                    setState(this._state.advance());
                } else {
                    logger.warn("Trying to advance state whhen not the owner. owner: {} perpetrator: {}", (Object) this._task, (Object) dNSTask);
                }
                return true;
            } finally {
                unlock();
            }
        }

        public boolean revertState() {
            if (willCancel()) {
                return true;
            }
            lock();
            try {
                if (!willCancel()) {
                    setState(this._state.revert());
                    setTask((DNSTask) null);
                }
                return true;
            } finally {
                unlock();
            }
        }

        public boolean cancelState() {
            boolean z = false;
            if (!willCancel()) {
                lock();
                try {
                    if (!willCancel()) {
                        setState(DNSState.CANCELING_1);
                        setTask((DNSTask) null);
                        z = true;
                    }
                } finally {
                    unlock();
                }
            }
            return z;
        }

        public boolean closeState() {
            boolean z = false;
            if (!willClose()) {
                lock();
                try {
                    if (!willClose()) {
                        setState(DNSState.CLOSING);
                        setTask((DNSTask) null);
                        z = true;
                    }
                } finally {
                    unlock();
                }
            }
            return z;
        }

        /* JADX INFO: finally extract failed */
        public boolean recoverState() {
            lock();
            try {
                setState(DNSState.PROBING_1);
                setTask((DNSTask) null);
                unlock();
                return false;
            } catch (Throwable th) {
                unlock();
                throw th;
            }
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

        private boolean willCancel() {
            return this._state.isCanceled() || this._state.isCanceling();
        }

        private boolean willClose() {
            return this._state.isClosed() || this._state.isClosing();
        }

        public boolean waitForAnnounced(long j) {
            if (!isAnnounced() && !willCancel()) {
                this._announcing.waitForEvent(j + 10);
            }
            if (!isAnnounced()) {
                this._announcing.waitForEvent(10);
                if (!isAnnounced()) {
                    if (willCancel() || willClose()) {
                        logger.debug("Wait for announced cancelled: {}", (Object) this);
                    } else {
                        logger.warn("Wait for announced timed out: {}", (Object) this);
                    }
                }
            }
            return isAnnounced();
        }

        public boolean waitForCanceled(long j) {
            if (!isCanceled()) {
                this._canceling.waitForEvent(j);
            }
            if (!isCanceled()) {
                this._canceling.waitForEvent(10);
                if (!isCanceled() && !willClose()) {
                    logger.warn("Wait for canceled timed out: {}", (Object) this);
                }
            }
            return isCanceled();
        }

        public String toString() {
            String str = "NO DNS";
            try {
                return (this._dns != null ? "DNS: " + this._dns.getName() + " [" + this._dns.getInetAddress() + "]" : str) + " state: " + this._state + " task: " + this._task;
            } catch (IOException unused) {
                StringBuilder sb = new StringBuilder();
                if (this._dns != null) {
                    str = "DNS: " + this._dns.getName();
                }
                return sb.append(str).append(" state: ").append(this._state).append(" task: ").append(this._task).toString();
            }
        }
    }
}
