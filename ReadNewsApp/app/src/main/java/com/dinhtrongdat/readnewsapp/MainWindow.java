package com.dinhtrongdat.readnewsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.core.util.Pair;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainWindow extends AppCompatActivity implements AdapterArticle.ListItemClickListener, CategoryCallback {
    RecyclerView articleRecycle, cateRecycle;
    RecyclerView.Adapter adapter,cateAdapter;
    private List<Category> mdata;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public ArrayList<String> arrLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        articleRecycle = findViewById(R.id.my_recycler);
        cateRecycle = findViewById(R.id.rv_category);
        appBarLayout = findViewById(R.id.AppBar);
        collapsingToolbarLayout = findViewById(R.id.CollabToolbar);

        String[] link = getResources().getStringArray(R.array.category_vnexpress);
        arrLink = new ArrayList<>(Arrays.asList(link));

        initUI();
    }

    private void initUI() {
        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});

        articleRecycle.setHasFixedSize(true);
        articleRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Article> articleLocation = new ArrayList<>();
        articleLocation.add(new Article(R.drawable.vnexpresss, gradient1));
        articleLocation.add(new Article(R.drawable.thanhnien, gradient2));

        adapter = new AdapterArticle(articleLocation, this);
        adapter.notifyDataSetChanged();
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
        cateAdapter.notifyDataSetChanged();
        cateRecycle.setAdapter(cateAdapter);
        setAnimation(R.anim.layout_right_left);
    }

    @Override
    public void onArticleListClick(int clickedItemIndex) {
        String[] link;
        switch (clickedItemIndex){
            case 0:
                mdata.clear();
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
                cateAdapter.notifyDataSetChanged();

                setAnimation(R.anim.layout_right_left);
                link = getResources().getStringArray(R.array.category_vnexpress);
                arrLink = new ArrayList<>(Arrays.asList(link));
                break;
            case 1:
                mdata.clear();
                mdata.add(new Category("Tin xem nhiều",R.drawable.xemnhieu_tn));
                mdata.add(new Category("Tin nổi bật",R.drawable.noibat_tn));
                mdata.add(new Category("Kinh doanh",R.drawable.kinhdoanh_tn));
                mdata.add(new Category("Thời sự",R.drawable.thoisu_tn));
                mdata.add(new Category("Giải trí",R.drawable.giaitri_tn));
                mdata.add(new Category("Pháp luật",R.drawable.phapluat_tn));
                mdata.add(new Category("Sức khỏe",R.drawable.suckhoe_tn));
                mdata.add(new Category("Khoa học",R.drawable.khoahoc_tn));
                mdata.add(new Category("Du lịch",R.drawable.dulich_tn));
                mdata.add(new Category("Thế giới",R.drawable.thegioi_tn));
                mdata.add(new Category("Startup",R.drawable.startup_tn));
                mdata.add(new Category("Giáo dục",R.drawable.giaoduc_tn));
                mdata.add(new Category("Đời sống",R.drawable.doisong_tn));
                mdata.add(new Category("Số hóa",R.drawable.sohoa_tn));
                cateAdapter.notifyDataSetChanged();

                setAnimation(R.anim.layout_right_left);
                link = getResources().getStringArray(R.array.category_thanhnien);
                arrLink = new ArrayList<>(Arrays.asList(link));
                break;

            default:
                link = getResources().getStringArray(R.array.category_vnexpress);
                arrLink = new ArrayList<>(Arrays.asList(link));
                break;
        }
    }

    @Override
    public void onCategoryClick(int pos, ImageView imgContainer, ImageView imgview, TextView txtTitle) {
        Intent intent = new Intent(this, CategoryDetailActivity.class);
        intent.putExtra("categoryObject",mdata.get(pos));
        Bundle bundle = new Bundle();
        bundle.putString("link",arrLink.get(pos));
        intent.putExtras(bundle);

        Pair<View,String> p1 = Pair.create((View)imgContainer, "container");
        Pair<View,String> p2 = Pair.create((View)imgview, "transition_img");
        Pair<View,String> p3 = Pair.create((View)txtTitle, "transition_title");

        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2,p3);

        startActivity(intent,option.toBundle());
    }
    private void setAnimation(int anim){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, anim);
        cateRecycle.setLayoutAnimation(layoutAnimationController);
    }
    private void showToast(String text){
        Toast toast = new Toast(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.layout_custom_toast));
        TextView txtMess = view.findViewById(R.id.txtView);
        txtMess.setText("Đã chọn "+text);
        toast.setView(view);

        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

}