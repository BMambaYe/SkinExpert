package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class MySkinFundActivity extends AppCompatActivity {
    private Button btnBack,btnRedemption;
    private WebView webView;
    private SharedPreferences sp;
    private String token ;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_fund);
        context = MySkinFundActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        token = sp.getString("token",null);
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
                //跳转基金详情界面
                Intent intentToFundRedemption = new Intent(context,SkinFundRedemption.class);
                startActivity(intentToFundRedemption);
            }
        });
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_fund_back);
        btnRedemption = (Button) findViewById(R.id.my_skin_fund_btn_redemption);
        webView = (WebView) findViewById(R.id.my_skin_fund_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(Constant.SKIN_FUND_URL+token);
    }
}
