package cz.blocshop.socketsforcordova;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketAdapterImpl implements SocketAdapter {
    private final int INPUT_STREAM_BUFFER_SIZE = 16384;
    private Consumer<Boolean> closeEventHandler;
    private Consumer<byte[]> dataConsumer;
    private Consumer<String> errorEventHandler;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Consumer<String> openErrorEventHandler;
    private Consumer<Void> openEventHandler;
    /* access modifiers changed from: private */
    public final Socket socket = new Socket();

    public void open(final String str, final int i) {
        this.executor.submit(new Runnable() {
            public void run() {
                try {
                    SocketAdapterImpl.this.socket.connect(new InetSocketAddress(str, i));
                    SocketAdapterImpl.this.invokeOpenEventHandler();
                    SocketAdapterImpl.this.submitReadTask();
                } catch (IOException e) {
                    Logging.Error(SocketAdapterImpl.class.getName(), "Error during connecting of socket", e.getCause());
                    SocketAdapterImpl.this.invokeOpenErrorEventHandler(e.getMessage());
                }
            }
        });
    }

    public void write(byte[] bArr) throws IOException {
        this.socket.getOutputStream().write(bArr);
    }

    public void shutdownWrite() throws IOException {
        this.socket.shutdownOutput();
    }

    public void close() throws IOException {
        invokeCloseEventHandler(false);
        this.socket.close();
    }

    public void setOptions(SocketAdapterOptions socketAdapterOptions) throws SocketException {
        if (socketAdapterOptions.getKeepAlive() != null) {
            this.socket.setKeepAlive(socketAdapterOptions.getKeepAlive().booleanValue());
        }
        if (socketAdapterOptions.getOobInline() != null) {
            this.socket.setOOBInline(socketAdapterOptions.getOobInline().booleanValue());
        }
        if (socketAdapterOptions.getSoLinger() != null) {
            this.socket.setSoLinger(true, socketAdapterOptions.getSoLinger().intValue());
        }
        if (socketAdapterOptions.getSoTimeout() != null) {
            this.socket.setSoTimeout(socketAdapterOptions.getSoTimeout().intValue());
        }
        if (socketAdapterOptions.getReceiveBufferSize() != null) {
            this.socket.setReceiveBufferSize(socketAdapterOptions.getReceiveBufferSize().intValue());
        }
        if (socketAdapterOptions.getSendBufferSize() != null) {
            this.socket.setSendBufferSize(socketAdapterOptions.getSendBufferSize().intValue());
        }
        if (socketAdapterOptions.getTrafficClass() != null) {
            this.socket.setTrafficClass(socketAdapterOptions.getTrafficClass().intValue());
        }
    }

    public void setOpenEventHandler(Consumer<Void> consumer) {
        this.openEventHandler = consumer;
    }

    public void setOpenErrorEventHandler(Consumer<String> consumer) {
        this.openErrorEventHandler = consumer;
    }

    public void setDataConsumer(Consumer<byte[]> consumer) {
        this.dataConsumer = consumer;
    }

    public void setCloseEventHandler(Consumer<Boolean> consumer) {
        this.closeEventHandler = consumer;
    }

    public void setErrorEventHandler(Consumer<String> consumer) {
        this.errorEventHandler = consumer;
    }

    /* access modifiers changed from: private */
    public void submitReadTask() {
        this.executor.submit(new Runnable() {
            public void run() {
                SocketAdapterImpl.this.runRead();
            }
        });
    }

    /* access modifiers changed from: private */
    public void runRead() {
        Class<SocketAdapterImpl> cls = SocketAdapterImpl.class;
        boolean z = false;
        try {
            runReadLoop();
            try {
                this.socket.close();
            } catch (IOException e) {
                Logging.Error(cls.getName(), "Error during closing of socket", e);
            } catch (Throwable th) {
                invokeCloseEventHandler(false);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                this.socket.close();
            } catch (IOException e2) {
                Logging.Error(cls.getName(), "Error during closing of socket", e2);
            } catch (Throwable th3) {
                invokeCloseEventHandler(z);
                throw th3;
            }
            invokeCloseEventHandler(z);
            throw th2;
        }
        invokeCloseEventHandler(z);
    }

    private void runReadLoop() throws IOException {
        byte[] bArr;
        byte[] bArr2 = new byte[16384];
        while (true) {
            int read = this.socket.getInputStream().read(bArr2);
            if (read >= 0) {
                if (16384 == read) {
                    bArr = bArr2;
                } else {
                    bArr = Arrays.copyOfRange(bArr2, 0, read);
                }
                invokeDataConsumer(bArr);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void invokeOpenEventHandler() {
        Consumer<Void> consumer = this.openEventHandler;
        if (consumer != null) {
            Void voidR = null;
            consumer.accept(null);
        }
    }

    /* access modifiers changed from: private */
    public void invokeOpenErrorEventHandler(String str) {
        Consumer<String> consumer = this.openErrorEventHandler;
        if (consumer != null) {
            consumer.accept(str);
        }
    }

    private void invokeDataConsumer(byte[] bArr) {
        Consumer<byte[]> consumer = this.dataConsumer;
        if (consumer != null) {
            consumer.accept(bArr);
        }
    }

    private void invokeCloseEventHandler(boolean z) {
        Consumer<Boolean> consumer = this.closeEventHandler;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
    }

    private void invokeExceptionHandler(String str) {
        Consumer<String> consumer = this.errorEventHandler;
        if (consumer != null) {
            consumer.accept(str);
        }
    }
}
