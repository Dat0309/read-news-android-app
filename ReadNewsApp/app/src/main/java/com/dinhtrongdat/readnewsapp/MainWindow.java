package com.dinhtrongdat.readnewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import java.util.ArrayList;

public class MainWindow extends AppCompatActivity implements AdapterArticle.ListItemClickListener {
    RecyclerView articleRecycle;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        articleRecycle = findViewById(R.id.my_recycler);
        initUI();
    }

    private void initUI() {
        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});

        articleRecycle.setHasFixedSize(true);
        articleRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Article> articleLocation = new ArrayList<>();
        articleLocation.add(new Article(R.drawable.vnexpresss, gradient1));
        articleLocation.add(new Article(R.drawable.thanhnien, gradient2));
        articleLocation.add(new Article(R.drawable.tuoitre, gradient3));

        adapter = new AdapterArticle(articleLocation, this);
        articleRecycle.setAdapter(adapter);
    }


    @Override
    public void onArticleListClick(int clickedItemIndex) {

    }
}