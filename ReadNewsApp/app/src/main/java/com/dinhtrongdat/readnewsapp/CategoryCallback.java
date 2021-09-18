package com.dinhtrongdat.readnewsapp;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public interface CategoryCallback {
    void onCategoryClick(int pos, ImageView imgContainer, ImageView imgview, TextView txtTitle);
}
