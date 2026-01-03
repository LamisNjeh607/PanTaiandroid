package javax.jmdns.impl;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;

public class ServiceEventImpl extends ServiceEvent {
    private static final long serialVersionUID = 7107973622016897488L;
    private final ServiceInfo _info;
    private final String _name;
    private final String _type;

    public ServiceEventImpl(JmDNSImpl jmDNSImpl, String str, String str2, ServiceInfo serviceInfo) {
        super(jmDNSImpl);
        this._type = str;
        this._name = str2;
        this._info = serviceInfo;
    }

    public JmDNS getDNS() {
        return (JmDNS) getSource();
    }

    public String getType() {
        return this._type;
    }

    public String getName() {
        return this._name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getSimpleName()).append('@').append(System.identityHashCode(this)).append("\n\tname: '").append(getName()).append("' type: '").append(getType()).append("' info: '").append(getInfo()).append("']");
        return sb.toString();
    }

    public ServiceInfo getInfo() {
        return this._info;
    }

    public ServiceEventImpl clone() {
        return new ServiceEventImpl((JmDNSImpl) getDNS(), getType(), getName(), new ServiceInfoImpl(getInfo()));
    }
}
