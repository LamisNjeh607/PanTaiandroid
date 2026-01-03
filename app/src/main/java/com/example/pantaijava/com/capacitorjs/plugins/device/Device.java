package com.capacitorjs.plugins.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import nz.co.soltius.cordova.BluetoothClassicSerial;

public class Device {
    private Context context;

    Device(Context context2) {
        this.context = context2;
    }

    public long getMemUsed() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public String getPlatform() {
        return "android";
    }

    public String getUuid() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    public float getBatteryLevel() {
        int i;
        Intent registerReceiver = this.context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int i2 = -1;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("level", -1);
            i = registerReceiver.getIntExtra("scale", -1);
            i2 = intExtra;
        } else {
            i = -1;
        }
        return ((float) i2) / ((float) i);
    }

    public boolean isCharging() {
        Intent registerReceiver = this.context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return false;
        }
        int intExtra = registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        if (intExtra == 2 || intExtra == 5) {
            return true;
        }
        return false;
    }

    public boolean isVirtual() {
        return Build.FINGERPRINT.contains("generic") || Build.PRODUCT.contains("sdk");
    }

    public String getName() {
        return Settings.Global.getString(this.context.getContentResolver(), BluetoothClassicSerial.DEVICE_NAME);
    }

    public String getWebViewVersion() {
        PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
        if (currentWebViewPackage != null) {
            return currentWebViewPackage.versionName;
        }
        return Build.VERSION.RELEASE;
    }

    private PackageInfo getWebViewVersionSubAndroid26() throws PackageManager.NameNotFoundException {
        return this.context.getPackageManager().getPackageInfo("com.android.chrome", 0);
    }
}
