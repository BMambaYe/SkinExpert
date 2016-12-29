package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class InterTestActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_test);
        webView = ((WebView) findViewById(R.id.wv_interTest));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.INTER_TEST);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_interTest:
                finish();
                break;
            case R.id.btn_interTest_jump:
                Intent intent = new Intent(this, StartTestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
