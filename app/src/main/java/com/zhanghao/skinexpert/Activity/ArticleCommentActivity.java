package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.CommentArticleAdapter;
import com.zhanghao.skinexpert.beans.CommentArticleBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class ArticleCommentActivity extends AppCompatActivity {

    private ListView commentListView;
    private List<CommentArticleBean.DataBean.ListBean> commentList;
    private CommentArticleAdapter commentArticleAdapter;

    private EditText commitEditText;

    private int id = 0;
    private int pageNum = 0;
    private int lastId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_comment);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        initBottom();
        initListView();
    }

    private void initBottom() {
        commitEditText = (EditText) findViewById(R.id.et_comment_article);
    }

    private void initListView() {
        commentListView = (ListView) findViewById(R.id.lv_comment_article);
        commentList = new ArrayList<>();
        commentArticleAdapter = new CommentArticleAdapter(commentList,this);
        commentListView.setAdapter(commentArticleAdapter);
        initData();
    }

    private void initData() {
        NetWorkRequest.getCommentArticle(this, id+"", pageNum+"", lastId+"", new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                CommentArticleBean commentArticleBean = (CommentArticleBean) result;
                if (commentArticleBean.getData().getList()!=null&&commentArticleBean.getData().getList().size()>0){
                    for (CommentArticleBean.DataBean.ListBean listBean : commentArticleBean.getData().getList()) {
                        commentList.add(listBean);
                    }
                }
                commentArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String result) {
                Toast.makeText(ArticleCommentActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
