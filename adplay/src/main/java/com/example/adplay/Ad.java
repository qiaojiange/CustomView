package com.example.adplay;

/**
 * Created by qiaojiange on 2017/6/1.
 */

public class Ad {
    private int resId;
    private String text;

    public Ad(int resId, String text) {
        this.resId = resId;
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
