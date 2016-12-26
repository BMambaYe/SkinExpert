package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class SkinTestDescriptionShow extends AppCompatActivity {
    private Button btnBack;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_description_show);
        initView();
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_test_describe_btn_back);
        webView  = (WebView) findViewById(R.id.skin_test_describe_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.SKINT_TEST_DESCRIPTION);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
