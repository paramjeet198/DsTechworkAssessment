package com.example.myapplication.videotask.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Video {
    private  Uri uri;
    private  String name;
    private  int duration;
    private  int size;
    private Bitmap thumbnail;

    public Video(Uri uri, String name, int duration, int size, Bitmap thumbnail) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.thumbnail = thumbnail;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
