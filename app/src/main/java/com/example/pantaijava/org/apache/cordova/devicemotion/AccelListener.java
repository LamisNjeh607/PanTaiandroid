package org.apache.cordova.devicemotion;

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

public class AccelListener extends CordovaPlugin implements SensorEventListener {
    public static int ERROR_FAILED_TO_START = 3;
    public static int RUNNING = 2;
    public static int STARTING = 1;
    public static int STOPPED;
    private int accuracy = 2;
    private CallbackContext callbackContext;
    private Sensor mSensor;
    private Handler mainHandler = null;
    private Runnable mainRunnable = new Runnable() {
        public void run() {
            AccelListener.this.timeout();
        }
    };
    private SensorManager sensorManager;
    private int status;
    private long timestamp = 0;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    public AccelListener() {
        setStatus(STOPPED);
    }

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.sensorManager = (SensorManager) cordovaInterface.getActivity().getSystemService("sensor");
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) {
        if (str.equals("start")) {
            this.callbackContext = callbackContext2;
            if (this.status != RUNNING) {
                start();
            }
        } else if (!str.equals(ZeroConf.ACTION_STOP)) {
            return false;
        } else {
            if (this.status == RUNNING) {
                stop();
            }
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT, "");
        pluginResult.setKeepCallback(true);
        callbackContext2.sendPluginResult(pluginResult);
        return true;
    }

    public void onDestroy() {
        stop();
    }

    private int start() {
        int i;
        int i2 = this.status;
        if (i2 == RUNNING || i2 == (i = STARTING)) {
            startTimeout();
            return this.status;
        }
        setStatus(i);
        List<Sensor> sensorList = this.sensorManager.getSensorList(1);
        if (sensorList == null || sensorList.size() <= 0) {
            setStatus(ERROR_FAILED_TO_START);
            fail(ERROR_FAILED_TO_START, "No sensors found to register accelerometer listening to.");
            return this.status;
        }
        Sensor sensor = sensorList.get(0);
        this.mSensor = sensor;
        if (this.sensorManager.registerListener(this, sensor, 2)) {
            setStatus(STARTING);
            this.accuracy = 2;
            startTimeout();
            return this.status;
        }
        setStatus(ERROR_FAILED_TO_START);
        fail(ERROR_FAILED_TO_START, "Device sensor returned an error.");
        return this.status;
    }

    private void startTimeout() {
        stopTimeout();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainHandler = handler;
        handler.postDelayed(this.mainRunnable, 2000);
    }

    private void stopTimeout() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mainRunnable);
        }
    }

    private void stop() {
        stopTimeout();
        if (this.status != STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        setStatus(STOPPED);
        this.accuracy = 0;
    }

    /* access modifiers changed from: private */
    public void timeout() {
        if (this.status == STARTING && this.accuracy >= 2) {
            this.timestamp = System.currentTimeMillis();
            win();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        if (sensor.getType() == 1 && this.status != STOPPED) {
            this.accuracy = i;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1 && this.status != STOPPED) {
            setStatus(RUNNING);
            if (this.accuracy >= 2) {
                this.timestamp = System.currentTimeMillis();
                this.x = sensorEvent.values[0];
                this.y = sensorEvent.values[1];
                this.z = sensorEvent.values[2];
                win();
            }
        }
    }

    public void onReset() {
        if (this.status == RUNNING) {
            stop();
        }
    }

    private void fail(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, jSONObject);
        pluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(pluginResult);
    }

    private void win() {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, getAccelerationJSON());
        pluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(pluginResult);
    }

    private void setStatus(int i) {
        this.status = i;
    }

    private JSONObject getAccelerationJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", (double) this.x);
            jSONObject.put("y", (double) this.y);
            jSONObject.put("z", (double) this.z);
            jSONObject.put("timestamp", this.timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
