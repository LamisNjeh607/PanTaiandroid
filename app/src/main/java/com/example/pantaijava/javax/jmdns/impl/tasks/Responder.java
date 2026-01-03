package javax.jmdns.impl.tasks;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Timer;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Responder extends DNSTask {
    static Logger logger = LoggerFactory.getLogger(Responder.class.getName());
    private final InetAddress _addr;
    private final DNSIncoming _in;
    private final int _port;
    private final boolean _unicast;

    public Responder(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i) {
        super(jmDNSImpl);
        this._in = dNSIncoming;
        this._addr = inetAddress;
        this._port = i;
        this._unicast = i != DNSConstants.MDNS_PORT;
    }

    public String getName() {
        return "Responder(" + (getDns() != null ? getDns().getName() : "") + ")";
    }

    public String toString() {
        return super.toString() + " incomming: " + this._in;
    }

    public void start(Timer timer) {
        boolean z = true;
        for (DNSQuestion dNSQuestion : this._in.getQuestions()) {
            logger.trace("{}.start() question={}", (Object) getName(), (Object) dNSQuestion);
            z = dNSQuestion.iAmTheOnlyOne(getDns());
            if (!z) {
                break;
            }
        }
        int i = 0;
        int nextInt = (!z || this._in.isTruncated()) ? (JmDNSImpl.getRandom().nextInt(96) + 20) - this._in.elapseSinceArrival() : 0;
        if (nextInt >= 0) {
            i = nextInt;
        }
        logger.trace("{}.start() Responder chosen delay={}", (Object) getName(), (Object) Integer.valueOf(i));
        if (!getDns().isCanceling() && !getDns().isCanceled()) {
            timer.schedule(this, (long) i);
        }
    }

    public void run() {
        getDns().respondToQuery(this._in);
        HashSet<DNSQuestion> hashSet = new HashSet<>();
        HashSet<DNSRecord> hashSet2 = new HashSet<>();
        if (getDns().isAnnounced()) {
            try {
                for (DNSQuestion dNSQuestion : this._in.getQuestions()) {
                    logger.debug("{}.run() JmDNS responding to: {}", (Object) getName(), (Object) dNSQuestion);
                    if (this._unicast) {
                        hashSet.add(dNSQuestion);
                    }
                    dNSQuestion.addAnswers(getDns(), hashSet2);
                }
                long currentTimeMillis = System.currentTimeMillis();
                for (DNSRecord dNSRecord : this._in.getAnswers()) {
                    if (dNSRecord.isStale(currentTimeMillis)) {
                        hashSet2.remove(dNSRecord);
                        logger.debug("{} - JmDNS Responder Known Answer Removed", (Object) getName());
                    }
                }
                if (!hashSet2.isEmpty()) {
                    logger.debug("{}.run() JmDNS responding", (Object) getName());
                    DNSOutgoing dNSOutgoing = new DNSOutgoing(33792, !this._unicast, this._in.getSenderUDPPayload());
                    if (this._unicast) {
                        dNSOutgoing.setDestination(new InetSocketAddress(this._addr, this._port));
                    }
                    dNSOutgoing.setId(this._in.getId());
                    for (DNSQuestion dNSQuestion2 : hashSet) {
                        if (dNSQuestion2 != null) {
                            dNSOutgoing = addQuestion(dNSOutgoing, dNSQuestion2);
                        }
                    }
                    for (DNSRecord dNSRecord2 : hashSet2) {
                        if (dNSRecord2 != null) {
                            dNSOutgoing = addAnswer(dNSOutgoing, this._in, dNSRecord2);
                        }
                    }
                    if (!dNSOutgoing.isEmpty()) {
                        getDns().send(dNSOutgoing);
                    }
                }
            } catch (Throwable th) {
                logger.warn(getName() + "run() exception ", th);
                getDns().close();
            }
        }
    }
}
