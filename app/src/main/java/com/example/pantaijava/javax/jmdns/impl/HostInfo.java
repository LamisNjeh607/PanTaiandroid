package javax.jmdns.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import javax.jmdns.NetworkTopologyDiscovery;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.NameRegister;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostInfo implements DNSStatefulObject {
    private static Logger logger = LoggerFactory.getLogger(HostInfo.class.getName());
    protected InetAddress _address;
    protected NetworkInterface _interfaze;
    protected String _name;
    private final HostInfoState _state;

    private static final class HostInfoState extends DNSStatefulObject.DefaultImplementation {
        private static final long serialVersionUID = -8191476803620402088L;

        public HostInfoState(JmDNSImpl jmDNSImpl) {
            setDns(jmDNSImpl);
        }
    }

    public static HostInfo newHostInfo(InetAddress inetAddress, JmDNSImpl jmDNSImpl, String str) {
        InetAddress inetAddress2;
        String str2 = str != null ? str : "";
        if (inetAddress == null) {
            try {
                String property = System.getProperty("net.mdns.interface");
                if (property != null) {
                    inetAddress2 = InetAddress.getByName(property);
                } else {
                    inetAddress2 = InetAddress.getLocalHost();
                    if (inetAddress2.isLoopbackAddress()) {
                        InetAddress[] inetAddresses = NetworkTopologyDiscovery.Factory.getInstance().getInetAddresses();
                        if (inetAddresses.length > 0) {
                            inetAddress2 = inetAddresses[0];
                        }
                    }
                }
                if (inetAddress2.isLoopbackAddress()) {
                    logger.warn("Could not find any address beside the loopback.");
                }
            } catch (IOException e) {
                logger.warn("Could not initialize the host network interface on " + inetAddress + "because of an error: " + e.getMessage(), (Throwable) e);
                inetAddress2 = loopbackAddress();
                if (str == null || str.length() <= 0) {
                    str = "computer";
                }
            }
        } else {
            inetAddress2 = inetAddress;
        }
        if (str2.length() == 0) {
            str2 = inetAddress2.getHostName();
        }
        if (str2.contains("in-addr.arpa") || str2.equals(inetAddress2.getHostAddress())) {
            if (str == null || str.length() <= 0) {
                str = inetAddress2.getHostAddress();
            }
            str2 = str;
        }
        int indexOf = str2.indexOf(".local");
        if (indexOf > 0) {
            str2 = str2.substring(0, indexOf);
        }
        return new HostInfo(inetAddress2, str2.replaceAll("[:%\\.]", "-") + ".local.", jmDNSImpl);
    }

    private static InetAddress loopbackAddress() {
        try {
            return InetAddress.getByName((String) null);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private HostInfo(InetAddress inetAddress, String str, JmDNSImpl jmDNSImpl) {
        this._state = new HostInfoState(jmDNSImpl);
        this._address = inetAddress;
        this._name = str;
        if (inetAddress != null) {
            try {
                this._interfaze = NetworkInterface.getByInetAddress(inetAddress);
            } catch (Exception e) {
                logger.warn("LocalHostInfo() exception ", (Throwable) e);
            }
        }
    }

    public String getName() {
        return this._name;
    }

    public InetAddress getInetAddress() {
        return this._address;
    }

    /* access modifiers changed from: package-private */
    public Inet4Address getInet4Address() {
        if (getInetAddress() instanceof Inet4Address) {
            return (Inet4Address) this._address;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Inet6Address getInet6Address() {
        if (getInetAddress() instanceof Inet6Address) {
            return (Inet6Address) this._address;
        }
        return null;
    }

    public NetworkInterface getInterface() {
        return this._interfaze;
    }

    public boolean conflictWithRecord(DNSRecord.Address address) {
        DNSRecord.Address dNSAddressRecord = getDNSAddressRecord(address.getRecordType(), address.isUnique(), DNSConstants.DNS_TTL);
        if (dNSAddressRecord == null || !dNSAddressRecord.sameType(address) || !dNSAddressRecord.sameName(address) || dNSAddressRecord.sameValue(address)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized String incrementHostName() {
        String incrementName;
        incrementName = NameRegister.Factory.getRegistry().incrementName(getInetAddress(), this._name, NameRegister.NameType.HOST);
        this._name = incrementName;
        return incrementName;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldIgnorePacket(DatagramPacket datagramPacket) {
        InetAddress address;
        boolean z = false;
        if (getInetAddress() == null || (address = datagramPacket.getAddress()) == null) {
            return false;
        }
        if ((getInetAddress().isLinkLocalAddress() || getInetAddress().isMCLinkLocal()) && !address.isLinkLocalAddress()) {
            z = true;
        }
        if (!address.isLoopbackAddress() || getInetAddress().isLoopbackAddress()) {
            return z;
        }
        return true;
    }

    /* renamed from: javax.jmdns.impl.HostInfo$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.HostInfo.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public DNSRecord.Address getDNSAddressRecord(DNSRecordType dNSRecordType, boolean z, int i) {
        int i2 = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecordType.ordinal()];
        if (i2 == 1) {
            return getDNS4AddressRecord(z, i);
        }
        if (i2 == 2 || i2 == 3) {
            return getDNS6AddressRecord(z, i);
        }
        return null;
    }

    private DNSRecord.Address getDNS4AddressRecord(boolean z, int i) {
        if (!(getInetAddress() instanceof Inet4Address)) {
            return null;
        }
        return new DNSRecord.IPv4Address(getName(), DNSRecordClass.CLASS_IN, z, i, getInetAddress());
    }

    private DNSRecord.Address getDNS6AddressRecord(boolean z, int i) {
        if (!(getInetAddress() instanceof Inet6Address)) {
            return null;
        }
        return new DNSRecord.IPv6Address(getName(), DNSRecordClass.CLASS_IN, z, i, getInetAddress());
    }

    /* access modifiers changed from: package-private */
    public DNSRecord.Pointer getDNSReverseAddressRecord(DNSRecordType dNSRecordType, boolean z, int i) {
        int i2 = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecordType.ordinal()];
        if (i2 == 1) {
            return getDNS4ReverseAddressRecord(z, i);
        }
        if (i2 == 2 || i2 == 3) {
            return getDNS6ReverseAddressRecord(z, i);
        }
        return null;
    }

    private DNSRecord.Pointer getDNS4ReverseAddressRecord(boolean z, int i) {
        if (!(getInetAddress() instanceof Inet4Address)) {
            return null;
        }
        return new DNSRecord.Pointer(getInetAddress().getHostAddress() + ".in-addr.arpa.", DNSRecordClass.CLASS_IN, z, i, getName());
    }

    private DNSRecord.Pointer getDNS6ReverseAddressRecord(boolean z, int i) {
        if (!(getInetAddress() instanceof Inet6Address)) {
            return null;
        }
        return new DNSRecord.Pointer(getInetAddress().getHostAddress() + ".ip6.arpa.", DNSRecordClass.CLASS_IN, z, i, getName());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("local host info[");
        sb.append(getName() != null ? getName() : "no name");
        sb.append(", ");
        sb.append(getInterface() != null ? getInterface().getDisplayName() : "???");
        sb.append(":");
        sb.append(getInetAddress() != null ? getInetAddress().getHostAddress() : "no address");
        sb.append(", ");
        sb.append(this._state);
        sb.append("]");
        return sb.toString();
    }

    public Collection<DNSRecord> answers(DNSRecordClass dNSRecordClass, boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        DNSRecord.Address dNS4AddressRecord = getDNS4AddressRecord(z, i);
        if (dNS4AddressRecord != null && dNS4AddressRecord.matchRecordClass(dNSRecordClass)) {
            arrayList.add(dNS4AddressRecord);
        }
        DNSRecord.Address dNS6AddressRecord = getDNS6AddressRecord(z, i);
        if (dNS6AddressRecord != null && dNS6AddressRecord.matchRecordClass(dNSRecordClass)) {
            arrayList.add(dNS6AddressRecord);
        }
        return arrayList;
    }

    public JmDNSImpl getDns() {
        return this._state.getDns();
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this._state.advanceState(dNSTask);
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._state.removeAssociationWithTask(dNSTask);
    }

    public boolean revertState() {
        return this._state.revertState();
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._state.associateWithTask(dNSTask, dNSState);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._state.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean cancelState() {
        return this._state.cancelState();
    }

    public boolean closeState() {
        return this._state.closeState();
    }

    public boolean recoverState() {
        return this._state.recoverState();
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

    public boolean waitForAnnounced(long j) {
        return this._state.waitForAnnounced(j);
    }

    public boolean waitForCanceled(long j) {
        if (this._address == null) {
            return true;
        }
        return this._state.waitForCanceled(j);
    }
}
