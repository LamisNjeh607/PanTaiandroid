package nz.co.soltius.cordova;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;
import net.becvert.cordova.ZeroConf;

public class BluetoothClassicSerialService {
    private static final boolean D = true;
    private static final UUID MY_UUID_INSECURE = UUID.fromString("23F18142-B389-4772-93BD-52BDBB2C03E9");
    private static final UUID MY_UUID_SECURE = UUID.fromString("7A9C3B55-78D0-44A7-A94E-A93E3FE118CE");
    private static final String NAME_INSECURE = "CordovaBluetoothClassicSerialServiceInSecure";
    private static final String NAME_SECURE = "CordovaBluetoothClassicSerialServiceSecure";
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    private static final String TAG = "BTCSerialService";
    private static final UUID UUID_SPP = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public String connectedUUID;
    /* access modifiers changed from: private */
    public final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    /* access modifiers changed from: private */
    public ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private int mState = 0;

    public BluetoothClassicSerialService(Handler handler) {
        this.mHandler = handler;
    }

    private synchronized void setState(int i) {
        Log.d(TAG, "setState() " + this.mState + " -> " + i);
        this.mState = i;
        this.mHandler.obtainMessage(1, i, -1).sendToTarget();
    }

    public synchronized int getState() {
        return this.mState;
    }

    public synchronized void start() {
        Log.d(TAG, "start");
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        setState(0);
    }

    public synchronized void connect(BluetoothDevice bluetoothDevice, UUID uuid, boolean z) {
        ConnectThread connectThread;
        Log.d(TAG, "connect to: " + bluetoothDevice);
        this.connectedUUID = uuid.toString();
        if (this.mState == 2 && (connectThread = this.mConnectThread) != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        ConnectThread connectThread2 = new ConnectThread(bluetoothDevice, uuid, z);
        this.mConnectThread = connectThread2;
        connectThread2.start();
        setState(2);
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice, String str) {
        Log.d(TAG, "connected, Socket Type:" + str);
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        ConnectedThread connectedThread2 = new ConnectedThread(bluetoothSocket, str);
        this.mConnectedThread = connectedThread2;
        connectedThread2.start();
        Message obtainMessage = this.mHandler.obtainMessage(4);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothClassicSerial.DEVICE_NAME, bluetoothDevice.getName());
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
        setState(3);
    }

    public synchronized void stop() {
        Log.d(TAG, ZeroConf.ACTION_STOP);
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        this.connectedUUID = "";
        setState(0);
    }

    public void write(byte[] bArr) {
        synchronized (this) {
            if (this.mState == 3) {
                ConnectedThread connectedThread = this.mConnectedThread;
                connectedThread.write(bArr);
            }
        }
    }

    /* access modifiers changed from: private */
    public void connectionFailed() {
        Message obtainMessage = this.mHandler.obtainMessage(7);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothClassicSerial.TOAST, "Unable to connect to device");
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
        start();
    }

    /* access modifiers changed from: private */
    public void connectionLost() {
        Message obtainMessage = this.mHandler.obtainMessage(5);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothClassicSerial.TOAST, "Device connection was lost");
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
        start();
    }

    private class ConnectThread extends Thread {
        private String mSocketType;
        private final BluetoothDevice mmDevice;
        private BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice bluetoothDevice, UUID uuid, boolean z) {
            BluetoothSocket bluetoothSocket;
            this.mmDevice = bluetoothDevice;
            this.mSocketType = z ? "Secure" : "Insecure";
            if (z) {
                try {
                    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
                } catch (IOException e) {
                    Log.e(BluetoothClassicSerialService.TAG, "Socket Type: " + this.mSocketType + "create() failed", e);
                    bluetoothSocket = null;
                }
            } else {
                bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
            }
            this.mmSocket = bluetoothSocket;
        }

        public void run() {
            Log.i(BluetoothClassicSerialService.TAG, "BEGIN mConnectThread SocketType:" + this.mSocketType);
            setName("ConnectThread" + this.mSocketType);
            BluetoothClassicSerialService.this.mAdapter.cancelDiscovery();
            try {
                Log.i(BluetoothClassicSerialService.TAG, "Connecting to socket...");
                this.mmSocket.connect();
                Log.i(BluetoothClassicSerialService.TAG, "Connected");
            } catch (IOException e) {
                Log.e(BluetoothClassicSerialService.TAG, e.toString());
                try {
                    Log.i(BluetoothClassicSerialService.TAG, "Trying fallback...");
                    BluetoothSocket bluetoothSocket = (BluetoothSocket) this.mmDevice.getClass().getMethod("createRfcommSocket", new Class[]{Integer.TYPE}).invoke(this.mmDevice, new Object[]{1});
                    this.mmSocket = bluetoothSocket;
                    bluetoothSocket.connect();
                    Log.i(BluetoothClassicSerialService.TAG, "Connected");
                } catch (Exception unused) {
                    Log.e(BluetoothClassicSerialService.TAG, "Couldn't establish a Bluetooth connection.");
                    try {
                        this.mmSocket.close();
                    } catch (IOException e2) {
                        Log.e(BluetoothClassicSerialService.TAG, "unable to close() " + this.mSocketType + " socket during connection failure", e2);
                    }
                    BluetoothClassicSerialService.this.connectionFailed();
                    return;
                }
            }
            synchronized (BluetoothClassicSerialService.this) {
                BluetoothClassicSerialService.this.mConnectThread = null;
            }
            BluetoothClassicSerialService.this.connected(this.mmSocket, this.mmDevice, this.mSocketType);
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
                Log.e(BluetoothClassicSerialService.TAG, "close() of connect " + this.mSocketType + " socket failed", e);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket bluetoothSocket, String str) {
            InputStream inputStream;
            Log.d(BluetoothClassicSerialService.TAG, "create ConnectedThread: " + str);
            this.mmSocket = bluetoothSocket;
            OutputStream outputStream = null;
            try {
                inputStream = bluetoothSocket.getInputStream();
                try {
                    outputStream = bluetoothSocket.getOutputStream();
                } catch (IOException e) {
                    e = e;
                    Log.e(BluetoothClassicSerialService.TAG, "temp sockets not created", e);
                    this.mmInStream = inputStream;
                    this.mmOutStream = outputStream;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                Log.e(BluetoothClassicSerialService.TAG, "temp sockets not created", e);
                this.mmInStream = inputStream;
                this.mmOutStream = outputStream;
            }
            this.mmInStream = inputStream;
            this.mmOutStream = outputStream;
        }

        public void run() {
            Log.i(BluetoothClassicSerialService.TAG, "BEGIN mConnectedThread");
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int read = this.mmInStream.read(bArr);
                    BluetoothClassicSerialService.this.mHandler.obtainMessage(2, new String(bArr, 0, read)).sendToTarget();
                    if (read > 0) {
                        BluetoothClassicSerialService.this.mHandler.obtainMessage(6, Arrays.copyOf(bArr, read)).sendToTarget();
                    }
                } catch (IOException e) {
                    Log.e(BluetoothClassicSerialService.TAG, "disconnected", e);
                    BluetoothClassicSerialService.this.connectionLost();
                    BluetoothClassicSerialService.this.start();
                    return;
                }
            }
        }

        public void write(byte[] bArr) {
            try {
                this.mmOutStream.write(bArr);
                BluetoothClassicSerialService.this.mHandler.obtainMessage(3, -1, -1, bArr).sendToTarget();
            } catch (IOException e) {
                Log.e(BluetoothClassicSerialService.TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
                Log.e(BluetoothClassicSerialService.TAG, "close() of connect socket failed", e);
            }
        }
    }
}
