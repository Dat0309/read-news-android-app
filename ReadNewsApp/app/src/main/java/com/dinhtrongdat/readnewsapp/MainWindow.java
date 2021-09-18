package com.dinhtrongdat.readnewsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import androidx.core.util.Pair;

public class MainWindow extends AppCompatActivity implements AdapterArticle.ListItemClickListener, CategoryCallback {
    RecyclerView articleRecycle, cateRecycle;
    RecyclerView.Adapter adapter,cateAdapter;
    private List<Category> mdata;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private boolean isExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        articleRecycle = findViewById(R.id.my_recycler);
        cateRecycle = findViewById(R.id.rv_category);
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

        /*
        * Custom adapter cho RecycleView Category.
        * */
        cateRecycle.setLayoutManager(new LinearLayoutManager(this));
        cateRecycle.setHasFixedSize(true);

        mdata = new ArrayList<>();
        mdata.add(new Category("Tin xem nhiều",R.drawable.xemnhieu));
        mdata.add(new Category("Tin nổi bật",R.drawable.home));
        mdata.add(new Category("Kinh doanh",R.drawable.kinhdanh));
        mdata.add(new Category("Thời sự",R.drawable.thoisu));
        mdata.add(new Category("Giải trí",R.drawable.giaitri));
        mdata.add(new Category("Pháp luật",R.drawable.phapluat));
        mdata.add(new Category("Sức khỏe",R.drawable.suckhoe));
        mdata.add(new Category("Khoa học",R.drawable.khoahoc));
        mdata.add(new Category("Du lịch",R.drawable.dulich));
        mdata.add(new Category("Thế giới",R.drawable.thegioi));
        mdata.add(new Category("Startup",R.drawable.startup));
        mdata.add(new Category("Giáo dục",R.drawable.giaoduc));
        mdata.add(new Category("Đời sống",R.drawable.doisong));
        mdata.add(new Category("Số hóa",R.drawable.sohoa));

        cateAdapter = new CategoryAdapter(mdata, this);
        cateRecycle.setAdapter(cateAdapter);

        /*
        * Expanded ToolBar when scroll*/
        appBarLayout = findViewById(R.id.AppBar);
        collapsingToolbarLayout = findViewById(R.id.CollabToolbar);
//        toolbar = findViewById(R.id.toolbar);
//        initToolBar();
//        initToolBarAnimation();
    }
//    private void initToolBar(){
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//    }
//    private void initToolBarAnimation(){
//        collapsingToolbarLayout.setTitle(getString(R.string.app_name));
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vnexpresss);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int myColor = palette.getVibrantColor(getResources().getColor(R.color.white));
//                collapsingToolbarLayout.setContentScrimColor(myColor);
//                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans));
//            }
//        });
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if(Math.abs(verticalOffset) > 200){
//                    isExpanded = false;
//                }else{
//                    isExpanded = true;
//                }
//                invalidateOptionsMenu();
//            }
//        });
//    }

    @Override
    public void onArticleListClick(int clickedItemIndex) {

    }

    @Override
    public void onCategoryClick(int pos, ImageView imgContainer, ImageView imgview, TextView txtTitle) {
        Intent intent = new Intent(this, CategoryDetailActivity.class);
        intent.putExtra("categoryObject",mdata.get(pos));

        Pair<View,String> p1 = Pair.create((View)imgContainer, "container");
        Pair<View,String> p2 = Pair.create((View)imgview, "transition_img");
        Pair<View,String> p3 = Pair.create((View)txtTitle, "transition_title");

        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2,p3);

        startActivity(intent,option.toBundle());
    }
}