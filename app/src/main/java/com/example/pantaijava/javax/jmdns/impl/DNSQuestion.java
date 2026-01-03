package javax.jmdns.impl;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNSQuestion extends DNSEntry {
    private static Logger logger = LoggerFactory.getLogger(DNSQuestion.class.getName());

    public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
    }

    public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
        return false;
    }

    public boolean isExpired(long j) {
        return false;
    }

    public boolean isStale(long j) {
        return false;
    }

    public void toString(StringBuilder sb) {
    }

    private static class DNS4Address extends DNSQuestion {
        DNS4Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            DNSRecord.Address dNSAddressRecord = jmDNSImpl.getLocalHost().getDNSAddressRecord(getRecordType(), true, DNSConstants.DNS_TTL);
            if (dNSAddressRecord != null) {
                set.add(dNSAddressRecord);
            }
        }

        public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
            String lowerCase = getName().toLowerCase();
            return jmDNSImpl.getLocalHost().getName().equals(lowerCase) || jmDNSImpl.getServices().containsKey(lowerCase);
        }
    }

    private static class DNS6Address extends DNSQuestion {
        DNS6Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            DNSRecord.Address dNSAddressRecord = jmDNSImpl.getLocalHost().getDNSAddressRecord(getRecordType(), true, DNSConstants.DNS_TTL);
            if (dNSAddressRecord != null) {
                set.add(dNSAddressRecord);
            }
        }

        public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
            String lowerCase = getName().toLowerCase();
            return jmDNSImpl.getLocalHost().getName().equals(lowerCase) || jmDNSImpl.getServices().containsKey(lowerCase);
        }
    }

    private static class HostInformation extends DNSQuestion {
        HostInformation(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }
    }

    private static class Pointer extends DNSQuestion {
        Pointer(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            Iterator<ServiceInfo> it = jmDNSImpl.getServices().values().iterator();
            while (it.hasNext()) {
                addAnswersForServiceInfo(jmDNSImpl, set, (ServiceInfoImpl) it.next());
            }
            if (isServicesDiscoveryMetaQuery()) {
                for (JmDNSImpl.ServiceTypeEntry type : jmDNSImpl.getServiceTypes().values()) {
                    set.add(new DNSRecord.Pointer("_services._dns-sd._udp.local.", DNSRecordClass.CLASS_IN, false, DNSConstants.DNS_TTL, type.getType()));
                }
            } else if (isReverseLookup()) {
                String str = getQualifiedNameMap().get(ServiceInfo.Fields.Instance);
                if (str != null && str.length() > 0) {
                    InetAddress inetAddress = jmDNSImpl.getLocalHost().getInetAddress();
                    if (str.equalsIgnoreCase(inetAddress != null ? inetAddress.getHostAddress() : "")) {
                        if (isV4ReverseLookup()) {
                            set.add(jmDNSImpl.getLocalHost().getDNSReverseAddressRecord(DNSRecordType.TYPE_A, false, DNSConstants.DNS_TTL));
                        }
                        if (isV6ReverseLookup()) {
                            set.add(jmDNSImpl.getLocalHost().getDNSReverseAddressRecord(DNSRecordType.TYPE_AAAA, false, DNSConstants.DNS_TTL));
                        }
                    }
                }
            } else {
                isDomainDiscoveryQuery();
            }
        }
    }

    private static class Service extends DNSQuestion {
        Service(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            String lowerCase = getName().toLowerCase();
            if (jmDNSImpl.getLocalHost().getName().equalsIgnoreCase(lowerCase)) {
                set.addAll(jmDNSImpl.getLocalHost().answers(getRecordClass(), isUnique(), DNSConstants.DNS_TTL));
            } else if (jmDNSImpl.getServiceTypes().containsKey(lowerCase)) {
                new Pointer(getName(), DNSRecordType.TYPE_PTR, getRecordClass(), isUnique()).addAnswers(jmDNSImpl, set);
            } else {
                addAnswersForServiceInfo(jmDNSImpl, set, (ServiceInfoImpl) jmDNSImpl.getServices().get(lowerCase));
            }
        }

        public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
            String lowerCase = getName().toLowerCase();
            return jmDNSImpl.getLocalHost().getName().equals(lowerCase) || jmDNSImpl.getServices().containsKey(lowerCase);
        }
    }

    private static class Text extends DNSQuestion {
        Text(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            addAnswersForServiceInfo(jmDNSImpl, set, (ServiceInfoImpl) jmDNSImpl.getServices().get(getName().toLowerCase()));
        }

        public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
            String lowerCase = getName().toLowerCase();
            return jmDNSImpl.getLocalHost().getName().equals(lowerCase) || jmDNSImpl.getServices().containsKey(lowerCase);
        }
    }

    private static class AllRecords extends DNSQuestion {
        public boolean isSameType(DNSEntry dNSEntry) {
            return dNSEntry != null;
        }

        AllRecords(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void addAnswers(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            String lowerCase = getName().toLowerCase();
            if (jmDNSImpl.getLocalHost().getName().equalsIgnoreCase(lowerCase)) {
                set.addAll(jmDNSImpl.getLocalHost().answers(getRecordClass(), isUnique(), DNSConstants.DNS_TTL));
            } else if (jmDNSImpl.getServiceTypes().containsKey(lowerCase)) {
                new Pointer(getName(), DNSRecordType.TYPE_PTR, getRecordClass(), isUnique()).addAnswers(jmDNSImpl, set);
            } else {
                Iterator<ServiceInfo> it = jmDNSImpl.getServices().values().iterator();
                while (it.hasNext()) {
                    addAnswersForServiceInfo(jmDNSImpl, set, (ServiceInfoImpl) it.next());
                }
            }
        }

        public boolean iAmTheOnlyOne(JmDNSImpl jmDNSImpl) {
            String lowerCase = getName().toLowerCase();
            return jmDNSImpl.getLocalHost().getName().equals(lowerCase) || jmDNSImpl.getServices().containsKey(lowerCase);
        }
    }

    DNSQuestion(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
        super(str, dNSRecordType, dNSRecordClass, z);
    }

    /* renamed from: javax.jmdns.impl.DNSQuestion$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                javax.jmdns.impl.constants.DNSRecordType[] r0 = javax.jmdns.impl.constants.DNSRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = r0
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_A     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_A6     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_AAAA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_ANY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_HINFO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0049 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_PTR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0054 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_SRV     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSRecordType     // Catch:{ NoSuchFieldError -> 0x0060 }
                javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.TYPE_TXT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSQuestion.AnonymousClass1.<clinit>():void");
        }
    }

    public static DNSQuestion newQuestion(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
        switch (AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecordType.ordinal()]) {
            case 1:
                return new DNS4Address(str, dNSRecordType, dNSRecordClass, z);
            case 2:
                return new DNS6Address(str, dNSRecordType, dNSRecordClass, z);
            case 3:
                return new DNS6Address(str, dNSRecordType, dNSRecordClass, z);
            case 4:
                return new AllRecords(str, dNSRecordType, dNSRecordClass, z);
            case 5:
                return new HostInformation(str, dNSRecordType, dNSRecordClass, z);
            case 6:
                return new Pointer(str, dNSRecordType, dNSRecordClass, z);
            case 7:
                return new Service(str, dNSRecordType, dNSRecordClass, z);
            case 8:
                return new Text(str, dNSRecordType, dNSRecordClass, z);
            default:
                return new DNSQuestion(str, dNSRecordType, dNSRecordClass, z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean answeredBy(DNSEntry dNSEntry) {
        return isSameRecordClass(dNSEntry) && isSameType(dNSEntry) && getName().equals(dNSEntry.getName());
    }

    /* access modifiers changed from: protected */
    public void addAnswersForServiceInfo(JmDNSImpl jmDNSImpl, Set<DNSRecord> set, ServiceInfoImpl serviceInfoImpl) {
        if (serviceInfoImpl != null && serviceInfoImpl.isAnnounced()) {
            if (getName().equalsIgnoreCase(serviceInfoImpl.getQualifiedName()) || getName().equalsIgnoreCase(serviceInfoImpl.getType()) || getName().equalsIgnoreCase(serviceInfoImpl.getTypeWithSubtype())) {
                set.addAll(jmDNSImpl.getLocalHost().answers(getRecordClass(), true, DNSConstants.DNS_TTL));
                set.addAll(serviceInfoImpl.answers(getRecordClass(), true, DNSConstants.DNS_TTL, jmDNSImpl.getLocalHost()));
            }
            logger.debug("{} DNSQuestion({}).addAnswersForServiceInfo(): info: {}\n{}", jmDNSImpl.getName(), getName(), serviceInfoImpl, set);
        }
    }
}
