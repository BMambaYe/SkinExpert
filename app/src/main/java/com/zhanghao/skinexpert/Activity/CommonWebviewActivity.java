package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class CommonWebviewActivity extends AppCompatActivity {

    private String id;
    private String title;
    private TextView tv_title;
    private WebView webView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        intent = getIntent();
        id = intent.getStringExtra("id");
        title = getIntent().getStringExtra("title");
        initView();

    }

    private void initView() {
        tv_title = ((TextView) findViewById(R.id.tv_commonweb_title));
        tv_title.setText(title);
        webView = ((WebView) findViewById(R.id.web_commonweb));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        switch (title) {
        	case "使用建议":
        	    webView.loadUrl(Constant.EXPERTSUGGESTION+id+Constant.EXPERTSUGGESTION1);
        	    break;
        	case "功效成分":
                webView.loadUrl(Constant.GONGXIAOCHENFEN+id+Constant.GONGXIAOCHENFEN1);
                break;
            case "防腐剂":
                webView.loadUrl(Constant.FANGFUJI+id+Constant.FANGFUJI1);
                break;
            case "易致痘":
                webView.loadUrl(Constant.YIZHIDOU+id+Constant.YIZHIDOU1);
                break;
            case "易致敏":
                webView.loadUrl(Constant.YIZHIMIN+id+Constant.YIZHIMIN1);
                break;
            case "孕期、哺乳期慎用":
                webView.loadUrl(Constant.YUNFU+id+Constant.YUNFU1);
                break;
            case "产品成分":
                webView.loadUrl(Constant.ALLCHENFEN+id+Constant.ALLCHENFEN1);
                break;
            case "":
                webView.loadUrl(intent.getStringExtra("tb_url"));
                break;
            default:
                break;
        }
    }

    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_detail_commonweb_back:
                    onBackPressed();
                    break;
                default:
                    break;
            }
    }
}
