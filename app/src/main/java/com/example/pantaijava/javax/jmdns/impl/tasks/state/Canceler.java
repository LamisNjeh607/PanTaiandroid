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

public class Canceler extends DNSStateTask {
    /* access modifiers changed from: protected */
    public boolean checkRunCondition() {
        return true;
    }

    public Canceler(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl, 0);
        setTaskState(DNSState.CANCELING_1);
        associate(DNSState.CANCELING_1);
    }

    public String getName() {
        return "Canceler(" + (getDns() != null ? getDns().getName() : "") + ")";
    }

    public String toString() {
        return super.toString() + " state: " + getTaskState();
    }

    public void start(Timer timer) {
        timer.schedule(this, 0, 1000);
    }

    public boolean cancel() {
        removeAssociation();
        return super.cancel();
    }

    public String getTaskDescription() {
        return "canceling";
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
        if (!getTaskState().isCanceling()) {
            cancel();
        }
    }
}
