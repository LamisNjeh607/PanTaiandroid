package com.capacitorjs.plugins.screenorientation;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;

public class ScreenOrientation {
    private AppCompatActivity activity;
    private int configOrientation;

    public ScreenOrientation(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    public String getCurrentOrientationType() {
        if (Build.VERSION.SDK_INT >= 30) {
            return fromRotationToOrientationType(this.activity.getDisplay().getRotation());
        }
        return fromRotationToOrientationType(getLegacyDisplayRotation());
    }

    public void lock(String str) {
        this.activity.setRequestedOrientation(fromOrientationTypeToEnum(str));
    }

    public void unlock() {
        this.activity.setRequestedOrientation(-1);
    }

    public boolean hasOrientationChanged(int i) {
        if (i == this.configOrientation) {
            return false;
        }
        this.configOrientation = i;
        return true;
    }

    private String fromRotationToOrientationType(int i) {
        if (i == 1) {
            return "landscape-primary";
        }
        if (i == 2) {
            return "portrait-secondary";
        }
        if (i != 3) {
            return "portrait-primary";
        }
        return "landscape-secondary";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int fromOrientationTypeToEnum(java.lang.String r5) {
        /*
            r4 = this;
            r5.hashCode()
            int r0 = r5.hashCode()
            r1 = 1
            r2 = 0
            r3 = -1
            switch(r0) {
                case -147105566: goto L_0x003b;
                case 96748: goto L_0x0030;
                case 1430647483: goto L_0x0025;
                case 1862465776: goto L_0x001a;
                case 2012187074: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            r5 = r3
            goto L_0x0045
        L_0x000f:
            java.lang.String r0 = "portrait-secondary"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0018
            goto L_0x000d
        L_0x0018:
            r5 = 4
            goto L_0x0045
        L_0x001a:
            java.lang.String r0 = "landscape-primary"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0023
            goto L_0x000d
        L_0x0023:
            r5 = 3
            goto L_0x0045
        L_0x0025:
            java.lang.String r0 = "landscape"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x002e
            goto L_0x000d
        L_0x002e:
            r5 = 2
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "any"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0039
            goto L_0x000d
        L_0x0039:
            r5 = r1
            goto L_0x0045
        L_0x003b:
            java.lang.String r0 = "landscape-secondary"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0044
            goto L_0x000d
        L_0x0044:
            r5 = r2
        L_0x0045:
            switch(r5) {
                case 0: goto L_0x004e;
                case 1: goto L_0x004d;
                case 2: goto L_0x004c;
                case 3: goto L_0x004c;
                case 4: goto L_0x0049;
                default: goto L_0x0048;
            }
        L_0x0048:
            return r1
        L_0x0049:
            r5 = 9
            return r5
        L_0x004c:
            return r2
        L_0x004d:
            return r3
        L_0x004e:
            r5 = 8
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.capacitorjs.plugins.screenorientation.ScreenOrientation.fromOrientationTypeToEnum(java.lang.String):int");
    }

    private int getLegacyDisplayRotation() {
        return this.activity.getWindowManager().getDefaultDisplay().getRotation();
    }
}
