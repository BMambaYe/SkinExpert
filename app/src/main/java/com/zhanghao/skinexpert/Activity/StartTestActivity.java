package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class StartTestActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        webView = ((WebView) findViewById(R.id.wv_startTest));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.STARTTEST);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_startTest:
                finish();
                break;
            case R.id.btn_startTest_jump:
                //TODO
                break;
            default:
                break;
        }
    }
}
