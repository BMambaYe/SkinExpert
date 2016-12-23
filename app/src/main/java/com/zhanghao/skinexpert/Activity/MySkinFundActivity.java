package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.zhanghao.skinexpert.R;

public class MySkinFundActivity extends AppCompatActivity {
    private Button btnBack,btnRedemption;
    private WebView webView;
    private String url = "http://www.caimiapp.com/wdwdjjo/?token=e448e66c88edea4751b58fb797ed6648";
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_fund);
        context = MySkinFundActivity.this;
        initView();
        setOnClick();
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRedemption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_fund_back);
        btnRedemption = (Button) findViewById(R.id.my_skin_fund_btn_redemption);
        webView = (WebView) findViewById(R.id.my_skin_fund_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
