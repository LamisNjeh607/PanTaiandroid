package com.capacitorjs.plugins.haptics.arguments;

import javax.jmdns.impl.constants.DNSConstants;

public enum HapticsNotificationType implements HapticsVibrationType {
    SUCCESS("SUCCESS", new long[]{0, 35, 65, 21}, new int[]{0, DNSConstants.PROBE_WAIT_INTERVAL, 0, 180}, new long[]{0, 35, 65, 21}),
    WARNING("WARNING", new long[]{0, 30, 40, 30, 50, 60}, new int[]{255, 255, 255, 255, 255, 255}, new long[]{0, 30, 40, 30, 50, 60}),
    ERROR("ERROR", new long[]{0, 27, 45, 50}, new int[]{0, DNSConstants.KNOWN_ANSWER_TTL, 0, DNSConstants.PROBE_WAIT_INTERVAL}, new long[]{0, 27, 45, 50});
    
    private final int[] amplitudes;
    private final long[] oldSDKPattern;
    private final long[] timings;
    private final String type;

    private HapticsNotificationType(String str, long[] jArr, int[] iArr, long[] jArr2) {
        this.type = str;
        this.timings = jArr;
        this.amplitudes = iArr;
        this.oldSDKPattern = jArr2;
    }

    public static HapticsNotificationType fromString(String str) {
        for (HapticsNotificationType hapticsNotificationType : values()) {
            if (hapticsNotificationType.type.equals(str)) {
                return hapticsNotificationType;
            }
        }
        return SUCCESS;
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
