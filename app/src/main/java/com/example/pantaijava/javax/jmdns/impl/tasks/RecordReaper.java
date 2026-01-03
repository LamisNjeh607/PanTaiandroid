package javax.jmdns.impl.tasks;

import com.google.android.gms.location.DeviceOrientationRequest;
import java.util.Timer;
import javax.jmdns.impl.JmDNSImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordReaper extends DNSTask {
    static Logger logger = LoggerFactory.getLogger(RecordReaper.class.getName());

    public RecordReaper(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String getName() {
        return "RecordReaper(" + (getDns() != null ? getDns().getName() : "") + ")";
    }

    public void start(Timer timer) {
        if (!getDns().isCanceling() && !getDns().isCanceled()) {
            timer.schedule(this, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
        }
    }

    public void run() {
        if (!getDns().isCanceling() && !getDns().isCanceled()) {
            logger.trace("{}.run() JmDNS reaping cache", (Object) getName());
            getDns().cleanCache();
        }
    }
}
