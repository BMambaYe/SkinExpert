package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.AllElementsAdapter;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ElementsContainer;
import com.zhanghao.skinexpert.utils.Constant;

import java.util.List;

public class CommonWebviewActivity extends AppCompatActivity {

    private String id;
    private String title;
    private TextView tv_title;
    private WebView webView;
    private Intent intent;
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> elements;
    private ElementsContainer elementsContainer;
    private RelativeLayout rv_show_all_elements;
    private ListView lv_show_all_elements;
    private AllElementsAdapter allElementsAdapter;
    private RelativeLayout rv_all_elements;
    private String token="";
    private String skinCode="----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        token= ((MyApplication) getApplication()).getToken();
        skinCode=((MyApplication) getApplication()).getSkinCode();
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
                webView.loadUrl(Constant.EXPERTSUGGESTION + token + Constant.EXPERTSUGGESTION1+id+Constant.EXPERTSUGGESTION2+skinCode);
                break;
            case "功效成分":
                initElementView();
                break;
            case "防腐剂":
                initElementView();
                break;
            case "易致痘":
                initElementView();
                break;
            case "易致敏":
                initElementView();
                break;
            case "孕期、哺乳期慎用":
                initElementView();
                break;
            case "产品成分":
                initElementView();
                break;
            case "":
                webView.loadUrl(intent.getStringExtra("tb_url"));
                break;
            default:
                break;
        }
    }

    private void initElementView() {
        webView.setVisibility(View.GONE);
        rv_all_elements = ((RelativeLayout) findViewById(R.id.rv_all_elements));
        rv_all_elements.setVisibility(View.VISIBLE);
        elementsContainer = (ElementsContainer) intent.getSerializableExtra("elements");
        elements = elementsContainer.getElements();
        rv_show_all_elements = ((RelativeLayout) findViewById(R.id.rv_all_elements));
        rv_show_all_elements.setVisibility(View.VISIBLE);
        lv_show_all_elements = ((ListView) findViewById(R.id.lv_show_all_elements));
        allElementsAdapter = new AllElementsAdapter(elements,this);
        lv_show_all_elements.setAdapter(allElementsAdapter);
        lv_show_all_elements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int element_id = elements.get(position).getId();
                Intent intent=new Intent(CommonWebviewActivity.this,ElementDetailActivity.class);
                intent.putExtra("element_id",element_id);
                startActivity(intent);
            }
        });

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
