package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

public class ProductPresalesActivity extends AppCompatActivity {

    private WebView webView;
    private TextView tv_presale;
    private TextView tv_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_presales);
        webView = ((WebView) findViewById(R.id.wb_productPresales));
        tv_presale = ((TextView) findViewById(R.id.tv_presele));
        tv_buy = ((TextView) findViewById(R.id.tv_presale_buy));
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url != null || !"".equals(url)) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }
        if (intent.getBooleanExtra("from_buy",false)){
            tv_presale.setVisibility(View.INVISIBLE);
            tv_buy.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_productPresales:
                finish();
                break;
            case R.id.tv_presale_buy:
                Intent intent=new Intent(this,ProductDetailActivity.class);
                intent.putExtra("id",2930886);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
