package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastId = 0;
    private int total = 0;
    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_more);
        listView = ((ListView) findViewById(R.id.lv_product_more));
        backBtn = ((Button) findViewById(R.id.btn_product_more));

        listBean = new ArrayList<>();
        productMoreAdapter = new ProductMoreAdapter(this, listBean);
        listView.setAdapter(productMoreAdapter);

        initSwipeRefreshLayout();
        initData();

        backBtn.setOnClickListener(btnListener);
        listView.setOnScrollListener(scrollListener);
        listView.setOnItemClickListener(clickListener);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_product_more);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_red, R.color.refresh_red1, R.color.refresh_red2, R.color.refresh_red3);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                total = 0;
                listBean.clear();
                initData();
            }
        });
    }

    private void initData() {
        if (lastId >= 0 && total >= 0)
            NetWorkRequest.getProductListMoreBean(this, lastId + "", total + "", "", this);
    }

    @Override
    public void success(Object result) {
        productMoreBean = (ProductMoreBean) result;
        if (productMoreBean.getData().getList() != null && productMoreBean.getData().getList().size() > 0) {
            List<ProductMoreBean.DataBean.ListBean> list = productMoreBean.getData().getList();
            for (ProductMoreBean.DataBean.ListBean bean : list) {
                listBean.add(bean);
            }
        }
        productMoreAdapter.notifyDataSetChanged();
        isRefresh = true;
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void fail(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem + visibleItemCount == totalItemCount && isRefresh && listBean != null) {
                total += 20;
                lastId = listBean.get(listBean.size() - 1).getId();
                initData();
                isRefresh = false;
            }
            boolean enable = false;
            if (listView != null && listView.getChildCount() > 0) {
                boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                enable = firstItemVisible && topOfFirstItemVisible;
            } else if (listView != null && listView.getChildCount() == 0) {
                enable = true;
            }
            swipeRefreshLayout.setEnabled(enable);
        }
    };

    AbsListView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (listBean.size() > 0) {
                Intent intent = new Intent(ProductMoreActivity.this, ProductDetailActivity.class);
                intent.putExtra("id", listBean.get(position).getId());
                intent.putExtra("buy", "totaobao");
                startActivity(intent);
            }
        }
    };
}
