package com.dinhtrongdat.readnewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoryDetailActivity extends AppCompatActivity {

    ImageView imgCategory;
    TextView cateTitle;
    Category category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        initUI();
    }

    private void initUI() {
        imgCategory = findViewById(R.id.img_cate_detail);
        cateTitle = findViewById(R.id.txt_Title_cate_detail);

        category = (Category) getIntent().getExtras().getSerializable("categoryObject");
        imgCategory.setImageResource(category.getDrawableResource());
        cateTitle.setText(category.getTitle());
    }
}