package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommentListViewBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private ImageView comment_iv_back;
    private ListView comment_listview;
    private List<CommentListViewBean.DataBean.ListBean> commentList=new ArrayList<>();
    private MyCommentAdapter adapter;
    private int id=0;
    private TextView comment_tv_send;
    private EditText comment_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        initView();
        getCommentData();
    }

    private void getCommentData() {
        NetWorkRequest.getCommentListViewBean(this,"",id,new NetWorkRequest.RequestCallBack() {
            private CommentListViewBean commentListViewBean;
            @Override
            public void success(Object result) {
                commentListViewBean= (CommentListViewBean) result;
                commentList=commentListViewBean.getData().getList();
                adapter=new MyCommentAdapter();
                comment_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initView() {
        comment_listview= (ListView) findViewById(R.id.comment_listview);
        comment_iv_back= (ImageView) findViewById(R.id.comment_iv_back);
        comment_tv_send= (TextView) findViewById(R.id.comment_tv_send);
        comment_et= (EditText) findViewById(R.id.comment_et);
        comment_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        comment_tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content=comment_et.getText().toString();
                NetWorkRequest.getCommentSend(getApplicationContext(),id,content,"a5b8027e668e92ccf2cd46077c2b34dd",0, new NetWorkRequest.RequestCallBack() {
                    @Override
                    public void success(Object result) {
                        Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String result) {

                    }
                });
                getCommentData();
                comment_et.setText("");
            }
        });
    }
    class MyCommentAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return commentList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder=null;
            if(convertView==null){
                mHolder=new ViewHolder();
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.comment_listview,parent,false);
                mHolder.comment_iv_head= (ImageView) convertView.findViewById(R.id.comment_iv_head);
                mHolder.comment_tv_content= (TextView) convertView.findViewById(R.id.comment_tv_content);
                mHolder.comment_tv_username= (TextView) convertView.findViewById(R.id.comment_tv_username);
                mHolder.comment_tv_time= (TextView) convertView.findViewById(R.id.comment_tv_time);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            Picasso.with(getApplicationContext()).load(commentList.get(position).getUser().getHeadface()).into(mHolder.comment_iv_head);
            mHolder.comment_tv_username.setText(commentList.get(position).getUser().getNick());
            mHolder.comment_tv_content.setText(commentList.get(position).getContent());
            mHolder.comment_tv_time.setText(commentList.get(position).getCreateTime());
            return convertView;
        }
        class ViewHolder{
            ImageView comment_iv_head;
            TextView comment_tv_username;
            TextView comment_tv_content;
            TextView comment_tv_time;
        }
    }
}
