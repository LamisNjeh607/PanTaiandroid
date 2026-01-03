package org.apache.cordova.deviceorientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import net.becvert.cordova.ZeroConf;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompassListener extends CordovaPlugin implements SensorEventListener {
    public static int ERROR_FAILED_TO_START = 3;
    public static int RUNNING = 2;
    public static int STARTING = 1;
    public static int STOPPED;
    public long TIMEOUT = 30000;
    int accuracy;
    private CallbackContext callbackContext;
    float heading = 0.0f;
    long lastAccessTime;
    Sensor mSensor;
    private SensorManager sensorManager;
    int status;
    long timeStamp = 0;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public CompassListener() {
        setStatus(STOPPED);
    }

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.sensorManager = (SensorManager) cordovaInterface.getActivity().getSystemService("sensor");
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        if (str.equals("start")) {
            start();
        } else if (str.equals(ZeroConf.ACTION_STOP)) {
            stop();
        } else if (str.equals("getStatus")) {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, getStatus()));
        } else if (str.equals("getHeading")) {
            if (this.status != RUNNING) {
                if (start() == ERROR_FAILED_TO_START) {
                    callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, ERROR_FAILED_TO_START));
                    return true;
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        CompassListener.this.timeout();
                    }
                }, 2000);
            }
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, getCompassHeading()));
        } else if (str.equals("setTimeout")) {
            setTimeout(jSONArray.getLong(0));
        } else if (!str.equals("getTimeout")) {
            return false;
        } else {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float) getTimeout()));
        }
        return true;
    }

    public void onDestroy() {
        stop();
    }

    public void onReset() {
        stop();
    }

    public int start() {
        int i = this.status;
        if (i == RUNNING || i == STARTING) {
            return i;
        }
        List<Sensor> sensorList = this.sensorManager.getSensorList(3);
        if (sensorList == null || sensorList.size() <= 0) {
            setStatus(ERROR_FAILED_TO_START);
        } else {
            Sensor sensor = sensorList.get(0);
            this.mSensor = sensor;
            this.sensorManager.registerListener(this, sensor, 3);
            this.lastAccessTime = System.currentTimeMillis();
            setStatus(STARTING);
        }
        return this.status;
    }

    public void stop() {
        if (this.status != STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        setStatus(STOPPED);
    }

    /* access modifiers changed from: private */
    public void timeout() {
        if (this.status == STARTING) {
            setStatus(ERROR_FAILED_TO_START);
            CallbackContext callbackContext2 = this.callbackContext;
            if (callbackContext2 != null) {
                callbackContext2.error("Compass listener failed to start.");
            }
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        this.timeStamp = System.currentTimeMillis();
        this.heading = f;
        setStatus(RUNNING);
        if (this.timeStamp - this.lastAccessTime > this.TIMEOUT) {
            stop();
        }
    }

    public int getStatus() {
        return this.status;
    }

    public float getHeading() {
        this.lastAccessTime = System.currentTimeMillis();
        return this.heading;
    }

    public void setTimeout(long j) {
        this.TIMEOUT = j;
    }

    public long getTimeout() {
        return this.TIMEOUT;
    }

    private void setStatus(int i) {
        this.status = i;
    }

    private JSONObject getCompassHeading() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magneticHeading", (double) getHeading());
        jSONObject.put("trueHeading", (double) getHeading());
        jSONObject.put("headingAccuracy", 0);
        jSONObject.put("timestamp", this.timeStamp);
        return jSONObject;
    }
}
