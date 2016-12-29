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
import com.zhanghao.skinexpert.adapter.VoteAdapter;
import com.zhanghao.skinexpert.beans.VoteListViewBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class Beautiful2Activity extends AppCompatActivity {
    private ImageView iv_beautiful2_back;
    private TextView tv_beautiful2_tagsname;
    private TextView beautiful2_tv_concern;
    private Button beautiful2_btn_post;
    private ListView beautiful2_listview;
    private VoteAdapter adapter;
    private List<VoteListViewBean.DataBean.ListBean> voteList=new ArrayList<>();
    private int id = 0;
    private String tagname = "";
    private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful2);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        tagname = intent.getStringExtra("tagname");
        initView();
        getVoteData();
    }

    private void getVoteData() {
        NetWorkRequest.getVoteListViewBean(this,"",id, new NetWorkRequest.RequestCallBack() {
            private VoteListViewBean voteListViewBean;
            @Override
            public void success(Object result) {
                voteListViewBean= (VoteListViewBean) result;
                voteList=voteListViewBean.getData().getList();
                adapter=new VoteAdapter(getApplicationContext(),voteList);
                beautiful2_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initView() {
        iv_beautiful2_back= (ImageView) findViewById(R.id.iv_beautiful2_back);
        iv_beautiful2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_beautiful2_tagsname= (TextView) findViewById(R.id.tv_beautiful2_tagsname);
        tv_beautiful2_tagsname.setText(tagname);
        beautiful2_tv_concern= (TextView) findViewById(R.id.beautiful2_tv_concern);
        beautiful2_tv_concern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    beautiful2_tv_concern.setText("已关注");
                    beautiful2_tv_concern.setTextColor(Color.GRAY);
                    Toast.makeText(getApplicationContext(),"关注成功",Toast.LENGTH_SHORT).show();
                    flag=true;
                } else {
                    beautiful2_tv_concern.setText("关注");
                    beautiful2_tv_concern.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(),"取消关注",Toast.LENGTH_SHORT).show();
                    flag=false;
                }
            }
        });
        beautiful2_btn_post= (Button) findViewById(R.id.beautiful2_btn_post);
        beautiful2_btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VoteActivity.class);
                startActivity(intent);
            }
        });
        beautiful2_listview= (ListView) findViewById(R.id.beautiful2_listview);
    }
}
