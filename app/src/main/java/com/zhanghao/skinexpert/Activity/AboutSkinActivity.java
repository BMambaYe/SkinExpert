package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class AboutSkinActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_skin);
        webView = ((WebView) findViewById(R.id.about_skin_webview));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.ABOUT_SKIN);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_skin_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
