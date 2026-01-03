package cz.blocshop.socketsforcordova;

public class SocketAdapterOptions {
    private Boolean keepAlive;
    private Boolean oobInline;
    private Integer receiveBufferSize;
    private Integer sendBufferSize;
    private Integer soLinger;
    private Integer soTimeout;
    private Integer trafficClass;

    public Boolean getKeepAlive() {
        return this.keepAlive;
    }

    public void setKeepAlive(Boolean bool) {
        this.keepAlive = bool;
    }

    public Boolean getOobInline() {
        return this.oobInline;
    }

    public void setOobInline(Boolean bool) {
        this.oobInline = bool;
    }

    public Integer getSoLinger() {
        return this.soLinger;
    }

    public void setSoLinger(Integer num) {
        this.soLinger = num;
    }

    public Integer getSoTimeout() {
        return this.soTimeout;
    }

    public void setSoTimeout(Integer num) {
        this.soTimeout = num;
    }

    public Integer getReceiveBufferSize() {
        return this.receiveBufferSize;
    }

    public void setReceiveBufferSize(Integer num) {
        this.receiveBufferSize = num;
    }

    public Integer getSendBufferSize() {
        return this.sendBufferSize;
    }

    public void setSendBufferSize(Integer num) {
        this.sendBufferSize = num;
    }

    public Integer getTrafficClass() {
        return this.trafficClass;
    }

    public void setTrafficClass(Integer num) {
        this.trafficClass = num;
    }
}
