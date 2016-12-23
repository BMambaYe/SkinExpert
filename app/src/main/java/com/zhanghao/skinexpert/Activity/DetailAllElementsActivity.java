package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommunityBeansContainer;
import com.zhanghao.skinexpert.beans.ProductDetailBean;

import java.util.List;

public class DetailAllElementsActivity extends AppCompatActivity {
private CommunityBeansContainer container;
    private List<ProductDetailBean.DataBean.ProductBean.CommunityBean> communitybeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all_elements);
        container= (CommunityBeansContainer) getIntent().getExtras().getSerializable("dis");
        communitybeans=container.getCommunitybeans();
        Log.i("110", "onCreate: "+communitybeans);

    }
}
