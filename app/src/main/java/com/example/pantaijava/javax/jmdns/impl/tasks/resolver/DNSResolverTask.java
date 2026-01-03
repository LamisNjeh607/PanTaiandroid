package javax.jmdns.impl.tasks.resolver;

import java.io.IOException;
import java.util.Timer;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DNSResolverTask extends DNSTask {
    private static Logger logger = LoggerFactory.getLogger(DNSResolverTask.class.getName());
    protected int _count = 0;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing addAnswers(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing addQuestions(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String description();

    public DNSResolverTask(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String toString() {
        return super.toString() + " count: " + this._count;
    }

    public void start(Timer timer) {
        if (!getDns().isCanceling() && !getDns().isCanceled()) {
            timer.schedule(this, 225, 225);
        }
    }

    public void run() {
        try {
            if (!getDns().isCanceling()) {
                if (!getDns().isCanceled()) {
                    int i = this._count;
                    this._count = i + 1;
                    if (i < 3) {
                        logger.debug("{}.run() JmDNS {}", (Object) getName(), (Object) description());
                        DNSOutgoing addQuestions = addQuestions(new DNSOutgoing(0));
                        if (getDns().isAnnounced()) {
                            addQuestions = addAnswers(addQuestions);
                        }
                        if (!addQuestions.isEmpty()) {
                            getDns().send(addQuestions);
                            return;
                        }
                        return;
                    }
                    cancel();
                    return;
                }
            }
            cancel();
        } catch (Throwable th) {
            logger.warn(getName() + ".run() exception ", th);
            getDns().recover();
        }
    }
}
