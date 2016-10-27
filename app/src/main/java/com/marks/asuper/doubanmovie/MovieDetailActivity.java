package com.marks.asuper.doubanmovie;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MovieDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private static final String URL = "http://blog.csdn.net/coder_pig";
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mWebView = (WebView) findViewById(R.id.detail_web_view);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mWebView.loadUrl(mUrl);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        // 设置数据库缓存路径
        settings.setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);

//        btn_clear_cache.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wView.clearCache(true);
//            }
//        });

//        btn_refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wView.reload();
//            }
//        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        // 清除WebView缓存
        mWebView.clearCache(true);
        // 下面几种方法都是
//        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        deleteDatabase("WebView.db");和deleteDatabase(“WebViewCache.db”);
//        webView.clearHistory();
//        webView.clearFormData();
//        getCacheDir().delete();
        super.onBackPressed();
    }
}

