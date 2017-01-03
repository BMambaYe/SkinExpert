package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class SkinTestResultDescription extends AppCompatActivity {
    private static SkinTestResultDescription instanceTestResultDescription =null;
    private Button btnBcak;
    private Button btnRetest;
    private WebView webView;
    private Context context;
    private SharedPreferences sp;
    private String url;
    private String token;
    private String skinCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_description);
        instanceTestResultDescription=this;
        context = SkinTestResultDescription.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        skinCode = sp.getString("skinCode",null);
        Log.i("RockTest:","测试点skincode:"+skinCode);
        url = Constant.SKINT_TEST_RESULT_URL+skinCode+".html";
    }

    private void initView() {
        btnBcak = (Button) findViewById(R.id.skin_test_result_describe_btn_back);
        btnRetest = (Button) findViewById(R.id.skin_test_result_describe_btn_retest);
        webView = (WebView) findViewById(R.id.skin_test_result_describe_webview);
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    private void setOnClick() {
        btnBcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToTestFinish = new Intent(context,SkinTestFinishActivity.class);
                startActivity(intentToTestFinish);
                finish();
            }
        });
    }
}
