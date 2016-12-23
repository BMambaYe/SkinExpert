package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.BeautifulAdapter;
import com.zhanghao.skinexpert.beans.BeautifulBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class BeautifulActivity extends AppCompatActivity {
    private ListView beautiful_listview;
    private BeautifulAdapter adapter;
    private List<BeautifulBean.DataBean.ListBean> datalist=new ArrayList<>();
    private ImageView iv_beautiful_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful);
        beautiful_listview= (ListView) findViewById(R.id.beautiful_listview);
        iv_beautiful_back= (ImageView) findViewById(R.id.iv_beautiful_back);
        iv_beautiful_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();

    }

    private void getData() {
        NetWorkRequest.getBeautifulBean(this, new NetWorkRequest.RequestCallBack() {
            private BeautifulBean beautifulBean;
            @Override
            public void success(Object result) {
                beautifulBean= (BeautifulBean) result;
                datalist=beautifulBean.getData().getList();
                adapter=new BeautifulAdapter(getApplicationContext(),datalist);
                beautiful_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }
}
