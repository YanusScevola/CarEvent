package com.yanus.carevent.model;

import android.net.Uri;

import java.net.URL;

public class EventModel {

    Uri image;
    String description;

    public EventModel(int image, String description) {
        image = image;
        this.description = description;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(int image) {
        image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
