package com.dinhtrongdat.readnewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    TextView txtNews, txtDes;
    Animation txtAnimation, layoutAnimation,titleAnimation;
    LottieAnimationView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        txtAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fall_down);
        layoutAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_bot_to_top);
        titleAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_top);

        txtNews = findViewById(R.id.txt_title);
        txtDes = findViewById(R.id.txt_description);
        splash = findViewById(R.id.splash_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.setVisibility(View.VISIBLE);
                splash.setAnimation(layoutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtNews.setVisibility(View.VISIBLE);
                        txtDes.setVisibility(View.VISIBLE);

                        txtNews.setAnimation(titleAnimation);
                        txtDes.setAnimation(txtAnimation);
                    }
                }, 1000);
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MainWindow.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }
}