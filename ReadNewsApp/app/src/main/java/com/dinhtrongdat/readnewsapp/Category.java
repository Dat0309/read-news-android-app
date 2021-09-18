package com.dinhtrongdat.readnewsapp;

import java.io.Serializable;

public class Category implements Serializable {
    private String title;
    private int image;
    private int drawableResource;

    public Category() {
    }

    public Category(int drawableResource) {
        this.drawableResource = drawableResource;
    }

    public Category(String title, int drawableResource) {
        this.title = title;
        this.drawableResource = drawableResource;
    }

    public Category(String title, int image, int drawableResource) {
        this.title = title;
        this.image = image;
        this.drawableResource = drawableResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }
}
