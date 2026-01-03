package javax.jmdns.impl.tasks.state;

import java.io.IOException;
import java.util.Timer;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSState;

public class Announcer extends DNSStateTask {
    public Announcer(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl, defaultTTL());
        setTaskState(DNSState.ANNOUNCING_1);
        associate(DNSState.ANNOUNCING_1);
    }

    public String getName() {
        return "Announcer(" + (getDns() != null ? getDns().getName() : "") + ")";
    }

    public String toString() {
        return super.toString() + " state: " + getTaskState();
    }

    public void start(Timer timer) {
        if (!getDns().isCanceling() && !getDns().isCanceled()) {
            timer.schedule(this, 1000, 1000);
        }
    }

    public boolean cancel() {
        removeAssociation();
        return super.cancel();
    }

    public String getTaskDescription() {
        return "announcing";
    }

    /* access modifiers changed from: protected */
    public boolean checkRunCondition() {
        return !getDns().isCanceling() && !getDns().isCanceled();
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing createOugoing() {
        return new DNSOutgoing(33792);
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing buildOutgoingForDNS(DNSOutgoing dNSOutgoing) throws IOException {
        for (DNSRecord addAnswer : getDns().getLocalHost().answers(DNSRecordClass.CLASS_ANY, true, getTTL())) {
            dNSOutgoing = addAnswer(dNSOutgoing, (DNSIncoming) null, addAnswer);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing buildOutgoingForInfo(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException {
        for (DNSRecord addAnswer : serviceInfoImpl.answers(DNSRecordClass.CLASS_ANY, true, getTTL(), getDns().getLocalHost())) {
            dNSOutgoing = addAnswer(dNSOutgoing, (DNSIncoming) null, addAnswer);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public void recoverTask(Throwable th) {
        getDns().recover();
    }

    /* access modifiers changed from: protected */
    public void advanceTask() {
        setTaskState(getTaskState().advance());
        if (!getTaskState().isAnnouncing()) {
            cancel();
            getDns().startRenewer();
        }
    }
}
