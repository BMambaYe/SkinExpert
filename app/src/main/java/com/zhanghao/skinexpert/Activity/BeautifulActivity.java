package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView tv_tagsname;
    private int id=0;
    private String tagname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        tagname=intent.getStringExtra("tagname");
        beautiful_listview= (ListView) findViewById(R.id.beautiful_listview);
        iv_beautiful_back= (ImageView) findViewById(R.id.iv_beautiful_back);
        tv_tagsname= (TextView) findViewById(R.id.tv_tagsname);
        tv_tagsname.setText(tagname);
        iv_beautiful_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i("1609", "onCreate: "+id);
        getData();

    }

    private void getData() {
        NetWorkRequest.getBeautifulBean(this,id,new NetWorkRequest.RequestCallBack() {
            private BeautifulBean beautifulBean;
            @Override
            public void success(Object result) {
                beautifulBean= (BeautifulBean) result;
                datalist=beautifulBean.getData().getList();
                Log.i("1609", "success: "+datalist.size());
                adapter=new BeautifulAdapter(getApplicationContext(),datalist);
                beautiful_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }
}
