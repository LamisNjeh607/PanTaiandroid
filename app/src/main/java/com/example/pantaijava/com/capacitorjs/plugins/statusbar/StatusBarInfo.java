package com.example.pantaijava.com.capacitorjs.plugins.statusbar;

import java.io.Serializable;

public class StatusBarInfo implements Serializable {
    private String color;
    private int height;
    private boolean overlays;
    private String style;
    private boolean visible;

    public boolean isOverlays() {
        return this.overlays;
    }

    public void setOverlays(boolean z) {
        this.overlays = z;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String str) {
        this.style = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }
}
