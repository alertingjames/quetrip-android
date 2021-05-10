package com.app.quetrip.models;

import android.graphics.Bitmap;

import java.io.File;

public class Picture {
    int idx = 0;
    File file = null;
    Bitmap bitmap = null;
    int imageRes = 0;
    String url = "";

    public Picture(){}

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdx() {
        return idx;
    }

    public File getFile() {
        return file;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getUrl() {
        return url;
    }
}
