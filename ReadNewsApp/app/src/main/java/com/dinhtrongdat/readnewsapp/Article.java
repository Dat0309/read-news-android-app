package com.dinhtrongdat.readnewsapp;

import android.graphics.drawable.GradientDrawable;

public class Article {
    int image;
    GradientDrawable color;

    public Article(int image, GradientDrawable color) {
        this.image = image;
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public GradientDrawable getColor() {
        return color;
    }

    public void setColor(GradientDrawable color) {
        this.color = color;
    }
}
