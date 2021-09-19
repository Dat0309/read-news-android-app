package com.dinhtrongdat.readnewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ItemDetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        webView=findViewById(R.id.webview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}