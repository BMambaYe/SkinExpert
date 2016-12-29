package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.BeautifulAdapter;
import com.zhanghao.skinexpert.beans.BeautifulBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class BeautifulActivity extends AppCompatActivity {
    private ListView beautiful_listview;
    private BeautifulAdapter adapter;
    private List<BeautifulBean.DataBean.ListBean> datalist = new ArrayList<>();
    private ImageView iv_beautiful_back;
    private TextView tv_tagsname;
    private int id = 0;
    private String tagname = "";
    private Button beautiful_btn_post;
    private TextView beautiful_tv_concern;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        tagname = intent.getStringExtra("tagname");
        initView();
        getData();

    }

    private void initView() {
        beautiful_listview = (ListView) findViewById(R.id.beautiful_listview);
        iv_beautiful_back = (ImageView) findViewById(R.id.iv_beautiful_back);
        tv_tagsname = (TextView) findViewById(R.id.tv_tagsname);
        tv_tagsname.setText(tagname);
        beautiful_tv_concern = (TextView) findViewById(R.id.beautiful_tv_concern);
        beautiful_tv_concern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    beautiful_tv_concern.setText("已关注");
                    beautiful_tv_concern.setTextColor(Color.GRAY);
                    Toast.makeText(getApplicationContext(),"关注成功",Toast.LENGTH_SHORT).show();
                    flag=true;
                } else {
                    beautiful_tv_concern.setText("关注");
                    beautiful_tv_concern.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(),"取消关注",Toast.LENGTH_SHORT).show();
                    flag=false;
                }
            }
        });
        iv_beautiful_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        beautiful_btn_post = (Button) findViewById(R.id.beautiful_btn_post);
        beautiful_btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        NetWorkRequest.getBeautifulBean(this,"",id, new NetWorkRequest.RequestCallBack() {
            private BeautifulBean beautifulBean;

            @Override
            public void success(Object result) {
                beautifulBean = (BeautifulBean) result;
                datalist = beautifulBean.getData().getList();
                //Log.i("1609", "success: " + datalist.size());
                adapter = new BeautifulAdapter(getApplicationContext(), datalist);
                beautiful_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }
}
