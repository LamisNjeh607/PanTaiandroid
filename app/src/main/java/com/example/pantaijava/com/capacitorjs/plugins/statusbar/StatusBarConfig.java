package com.capacitorjs.plugins.statusbar;

import com.getcapacitor.util.WebColor;

public class StatusBarConfig {
    private Integer backgroundColor = Integer.valueOf(WebColor.parseColor("#000000"));
    private boolean overlaysWebView = true;
    private String style = "DEFAULT";

    public boolean isOverlaysWebView() {
        return this.overlaysWebView;
    }

    public void setOverlaysWebView(boolean z) {
        this.overlaysWebView = z;
    }

    public Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Integer num) {
        this.backgroundColor = num;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String str) {
        this.style = str;
    }
}
