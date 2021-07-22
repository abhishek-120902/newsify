package com.abhishek.newsify.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.abhishek.newsify.R;

public class WebViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        toolbar = findViewById(R.id.toolbarWebView);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        setSupportActionBar(toolbar);

        //GetUrl
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //LoadUrl
        webView.setWebViewClient(new myWebViewClient());
        webView.loadUrl(url);

    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

}