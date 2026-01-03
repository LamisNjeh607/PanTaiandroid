package javax.jmdns.impl.tasks.resolver;

import java.io.IOException;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class TypeResolver extends DNSResolverTask {
    public TypeResolver(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String getName() {
        return "TypeResolver(" + (getDns() != null ? getDns().getName() : "") + ")";
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing addAnswers(DNSOutgoing dNSOutgoing) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        for (JmDNSImpl.ServiceTypeEntry type : getDns().getServiceTypes().values()) {
            dNSOutgoing = addAnswer(dNSOutgoing, (DNSRecord) new DNSRecord.Pointer("_services._dns-sd._udp.local.", DNSRecordClass.CLASS_IN, false, DNSConstants.DNS_TTL, type.getType()), currentTimeMillis);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing addQuestions(DNSOutgoing dNSOutgoing) throws IOException {
        return addQuestion(dNSOutgoing, DNSQuestion.newQuestion("_services._dns-sd._udp.local.", DNSRecordType.TYPE_PTR, DNSRecordClass.CLASS_IN, false));
    }

    /* access modifiers changed from: protected */
    public String description() {
        return "querying type";
    }
}
