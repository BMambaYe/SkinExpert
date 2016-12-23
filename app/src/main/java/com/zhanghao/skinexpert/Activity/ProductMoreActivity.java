package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductMoreAdapter;
import com.zhanghao.skinexpert.beans.ProductMoreBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class ProductMoreActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack {

    private Button backBtn;
    private ListView listView;
    private List<ProductMoreBean.DataBean.ListBean> listBean;
    private ProductMoreAdapter productMoreAdapter;
    private ProductMoreBean productMoreBean;
    private int lastId = 0;
    private int total = 0;
    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_more);
        listView = ((ListView) findViewById(R.id.lv_product_more));
        backBtn = ((Button) findViewById(R.id.btn_product_more));
        backBtn.setOnClickListener(btnListener);
        listBean = new ArrayList<>();
        productMoreAdapter = new ProductMoreAdapter(this, listBean);
        listView.setAdapter(productMoreAdapter);
        listView.setOnScrollListener(scrollListener);
        listView.setOnItemClickListener(clickListener);
        InitData();
    }

    private void InitData() {
        NetWorkRequest.getProductListMoreBean(this, lastId + "", total + "", "", this);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public void success(Object result) {
        productMoreBean = (ProductMoreBean) result;
        List<ProductMoreBean.DataBean.ListBean> list = productMoreBean.getData().getList();
        for (ProductMoreBean.DataBean.ListBean bean : list) {
            listBean.add(bean);
        }
        productMoreAdapter.notifyDataSetChanged();
        isRefresh = true;
    }

    @Override
    public void fail(String result) {

    }

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem + visibleItemCount == totalItemCount&&isRefresh&&listBean!=null) {
                total+=20;
                lastId = listBean.get(listBean.size()-1).getId();
                InitData();
                isRefresh = false;
            }
        }
    };

    AbsListView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ProductMoreActivity.this,ProductDetailActivity.class);
            intent.putExtra("id",listBean.get(position).getId());
            intent.putExtra("buy","totaobao");
            startActivity(intent);
        }
    };
}
