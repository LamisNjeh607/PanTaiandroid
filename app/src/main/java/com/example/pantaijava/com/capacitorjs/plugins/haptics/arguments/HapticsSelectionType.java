package com.capacitorjs.plugins.haptics.arguments;

public class HapticsSelectionType implements HapticsVibrationType {
    private static final int[] amplitudes = {0, 100};
    private static final long[] oldSDKPattern = {0, 70};
    private static final long[] timings = {0, 100};

    public long[] getTimings() {
        return timings;
    }

    public int[] getAmplitudes() {
        return amplitudes;
    }

    public long[] getOldSDKPattern() {
        return oldSDKPattern;
    }
}
