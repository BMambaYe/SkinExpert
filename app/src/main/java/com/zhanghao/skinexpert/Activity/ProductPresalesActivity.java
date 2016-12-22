package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zhanghao.skinexpert.R;

public class ProductPresalesActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_presales);
        webView = ((WebView) findViewById(R.id.wb_productPresales));
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_productPresales:
                finish();
                break;
            default:
                break;
        }
    }
}
