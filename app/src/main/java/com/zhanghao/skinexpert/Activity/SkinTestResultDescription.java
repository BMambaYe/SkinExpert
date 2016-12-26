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

public class SkinTestResultDescription extends AppCompatActivity {
    private Button btnBcak;
    private Button btnRetest;
    private WebView webView;
    private Context context;
    private SharedPreferences sp;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_description);
        context = SkinTestResultDescription.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("testresult",MODE_PRIVATE);
        int code1 = sp.getInt(Constant.USERNAME+"0",0);
        int code2 = sp.getInt(Constant.USERNAME+"1",0);
        int code3 = sp.getInt(Constant.USERNAME+"2",0);
        int code4 = sp.getInt(Constant.USERNAME+"3",0);
        url = Constant.SKINT_TEST_RESULT_URL+code1+code2+code3+code4+".html";
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
