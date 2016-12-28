package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.fragments.RecommentTagsRightFragment;

import java.io.Serializable;
import java.util.List;

public class RecommendTagsActivity extends AppCompatActivity  {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView recommendtags_iv_back;
    private List<RecommendTagsDataBean.DataBean> tagsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_tags);
        recommendtags_iv_back = (ImageView) findViewById(R.id.recommendtags_iv_back);
        recommendtags_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getRightFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        RecommentTagsRightFragment rightFragment = new RecommentTagsRightFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tagsList", (Serializable) tagsList);
        rightFragment.setArguments(bundle);
        transaction.replace(R.id.ll_recommend_right, rightFragment);
        transaction.commit();

    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchTagActivity.class);
        startActivity(intent);
    }
    
}