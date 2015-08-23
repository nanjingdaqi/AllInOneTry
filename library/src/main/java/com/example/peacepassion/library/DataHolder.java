package com.example.peacepassion.library;

import android.graphics.drawable.Drawable;

/**
 * Created by peacepassion on 15/8/23.
 */
public class DataHolder {

    Drawable back;
    Drawable front;
    String title;

    public DataHolder(Drawable back, Drawable front, String title) {
        this.back = back;
        this.front = front;
        this.title = title;
    }
}
