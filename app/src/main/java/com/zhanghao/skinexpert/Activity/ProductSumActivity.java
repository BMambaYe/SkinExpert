package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductLibrariesAdapter;
import com.zhanghao.skinexpert.beans.ProductLibrariesBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class ProductSumActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack{

    private TextView brand;
    private TextView function;
    private TextView price;
    private TextView classify;
    private ListView listView;
    private List<ProductLibrariesBean.DataBean.ListBean> listBeen;
    private ProductLibrariesAdapter listViewAdapter;
    private ProductLibrariesBean productLibrariesBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sum);
        brand = ((TextView) findViewById(R.id.tv_product_sum_brand));
        function = ((TextView) findViewById(R.id.tv_product_sum_function));
        price = ((TextView) findViewById(R.id.tv_product_sum_price));
        classify = ((TextView) findViewById(R.id.tv_product_sum_classify));
        listView = ((ListView) findViewById(R.id.lv_product_sum));
        listBeen = new ArrayList<>();
        listViewAdapter = new ProductLibrariesAdapter(this,listBeen);
        listView.setAdapter(listViewAdapter);
        initData();
    }

    private void initData() {
        NetWorkRequest.getProductListDataBean(this,"0","0","0","0","0","0","0","",this);
    }

    @Override
    public void success(Object result) {
        productLibrariesBean = (ProductLibrariesBean) result;
        List<ProductLibrariesBean.DataBean.ListBean> list = productLibrariesBean.getData().getList();
        for (ProductLibrariesBean.DataBean.ListBean bean : list) {
            listBeen.add(bean);
        }
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String result) {

    }
}
