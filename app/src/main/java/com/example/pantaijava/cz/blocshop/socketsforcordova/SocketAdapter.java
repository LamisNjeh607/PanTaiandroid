package cz.blocshop.socketsforcordova;

import java.io.IOException;
import java.net.SocketException;

public interface SocketAdapter {
    void close() throws IOException;

    void open(String str, int i);

    void setCloseEventHandler(Consumer<Boolean> consumer);

    void setDataConsumer(Consumer<byte[]> consumer);

    void setErrorEventHandler(Consumer<String> consumer);

    void setOpenErrorEventHandler(Consumer<String> consumer);

    void setOpenEventHandler(Consumer<Void> consumer);

    void setOptions(SocketAdapterOptions socketAdapterOptions) throws SocketException;

    void shutdownWrite() throws IOException;

    void write(byte[] bArr) throws IOException;
}
