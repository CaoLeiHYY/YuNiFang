package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.nababy.caokexin.R;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        WebView banner_webView = (WebView) findViewById(R.id.bannner_webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        banner_webView.loadUrl(url);
    }
}
