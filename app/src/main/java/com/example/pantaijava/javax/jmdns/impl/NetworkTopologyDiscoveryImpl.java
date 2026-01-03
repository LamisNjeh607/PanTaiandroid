package javax.jmdns.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import javax.jmdns.NetworkTopologyDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkTopologyDiscoveryImpl implements NetworkTopologyDiscovery {
    private static final Logger logger = LoggerFactory.getLogger(NetworkTopologyDiscoveryImpl.class.getName());

    public void lockInetAddress(InetAddress inetAddress) {
    }

    public void unlockInetAddress(InetAddress inetAddress) {
    }

    public InetAddress[] getInetAddresses() {
        HashSet hashSet = new HashSet();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    logger.trace("Found NetworkInterface/InetAddress: {} -- {}", (Object) nextElement, (Object) nextElement2);
                    if (useInetAddress(nextElement, nextElement2)) {
                        hashSet.add(nextElement2);
                    }
                }
            }
        } catch (SocketException e) {
            logger.warn("Error while fetching network interfaces addresses: " + e);
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    public boolean useInetAddress(NetworkInterface networkInterface, InetAddress inetAddress) {
        try {
            if (networkInterface.isUp() && networkInterface.supportsMulticast() && !networkInterface.isLoopback()) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
