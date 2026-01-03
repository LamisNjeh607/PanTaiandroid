package com.example.pantaijava.com.capacitorjs.plugins.haptics.arguments;

public enum HapticsImpactType implements com.capacitorjs.plugins.haptics.arguments.HapticsVibrationType {
    LIGHT("LIGHT", new long[]{0, 50}, new int[]{0, 110}, new long[]{0, 20}),
    MEDIUM("MEDIUM", new long[]{0, 43}, new int[]{0, 180}, new long[]{0, 43}),
    HEAVY("HEAVY", new long[]{0, 60}, new int[]{0, 255}, new long[]{0, 61});
    
    private final int[] amplitudes;
    private final long[] oldSDKPattern;
    private final long[] timings;
    private final String type;

    private HapticsImpactType(String str, long[] jArr, int[] iArr, long[] jArr2) {
        this.type = str;
        this.timings = jArr;
        this.amplitudes = iArr;
        this.oldSDKPattern = jArr2;
    }

    public static HapticsImpactType fromString(String str) {
        for (HapticsImpactType hapticsImpactType : values()) {
            if (hapticsImpactType.type.equals(str)) {
                return hapticsImpactType;
            }
        }
        return HEAVY;
    }

    public long[] getTimings() {
        return this.timings;
    }

    public int[] getAmplitudes() {
        return this.amplitudes;
    }

    public long[] getOldSDKPattern() {
        return this.oldSDKPattern;
    }
}
