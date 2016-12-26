package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.zhanghao.skinexpert.R;

public class FundBuyInstructionsActivity extends AppCompatActivity {
    private Button btnBack;
    private WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_buy_instructions);
        getData();
        initView();
    }


    private void getData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("instructionUrl");

    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.fund_buy_instruction_btn_back);
        webView = (WebView) findViewById(R.id.fund_buy_instruction_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
