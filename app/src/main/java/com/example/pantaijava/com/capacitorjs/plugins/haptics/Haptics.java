package com.capacitorjs.plugins.haptics;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import com.capacitorjs.plugins.haptics.arguments.HapticsSelectionType;
import com.capacitorjs.plugins.haptics.arguments.HapticsVibrationType;

public class Haptics {
    private boolean selectionStarted = false;
    private final Vibrator vibrator;

    Haptics(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            this.vibrator = ((VibratorManager) context.getSystemService("vibrator_manager")).getDefaultVibrator();
        } else {
            this.vibrator = getDeprecatedVibrator(context);
        }
    }

    private Vibrator getDeprecatedVibrator(Context context) {
        return (Vibrator) context.getSystemService("vibrator");
    }

    public void vibrate(int i) {
        this.vibrator.vibrate(VibrationEffect.createOneShot((long) i, -1));
    }

    private void vibratePre26(int i) {
        this.vibrator.vibrate((long) i);
    }

    private void vibratePre26(long[] jArr) {
        this.vibrator.vibrate(jArr, -1);
    }

    public void selectionStart() {
        this.selectionStarted = true;
    }

    public void selectionChanged() {
        if (this.selectionStarted) {
            performHaptics(new HapticsSelectionType());
        }
    }

    public void selectionEnd() {
        this.selectionStarted = false;
    }

    public void performHaptics(HapticsVibrationType hapticsVibrationType) {
        this.vibrator.vibrate(VibrationEffect.createWaveform(hapticsVibrationType.getTimings(), hapticsVibrationType.getAmplitudes(), -1));
    }
}
