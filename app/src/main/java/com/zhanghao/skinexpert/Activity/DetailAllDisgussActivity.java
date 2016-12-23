package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.DetailAllDisgussAdapter;
import com.zhanghao.skinexpert.beans.DetailAllDisgussBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class DetailAllDisgussActivity extends AppCompatActivity {
    private int cmcid;
    private TextView tv_categoryName;
    private ListView lv_show_all_disguss;
    int total = 0;
    private List<DetailAllDisgussBean.DataBean.ListBean> datalist = new ArrayList<>();
    private DetailAllDisgussBean detailAllDisgussBean;
    private DetailAllDisgussAdapter detailAllDisgussAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all_disguss);
        intent = getIntent();
        cmcid = intent.getIntExtra("cmcid", 0);
        tv_categoryName = ((TextView) findViewById(R.id.tv_all_disguss_product_detail));
        tv_categoryName.setText(intent.getStringExtra("title"));

        initView();
        loadData();


    }

    private void loadData() {
        NetWorkRequest.getDetailAllDisguss(this, cmcid, total, 0, new NetWorkRequest.RequestCallBack() {

            @Override
            public void success(Object result) {
                detailAllDisgussBean = ((DetailAllDisgussBean) result);
                datalist = detailAllDisgussBean.getData().getList();
                if (datalist.size() > 0) {
                    lastId = datalist.get(datalist.size() - 1).getId();
                    detailAllDisgussAdapter = new DetailAllDisgussAdapter(DetailAllDisgussActivity.this, datalist);
                    lv_show_all_disguss.setAdapter(detailAllDisgussAdapter);
                }

            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private boolean isToBottom;
    private int lastId;
    private boolean canDownLoad = true;

    private void initView() {
        lv_show_all_disguss = ((ListView) findViewById(R.id.lv_alldisguss_show_disguss));
        lv_show_all_disguss.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isToBottom && scrollState == SCROLL_STATE_FLING) {
                    total += 20;
                    NetWorkRequest.getDetailAllDisguss(DetailAllDisgussActivity.this, cmcid, total, lastId, new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            if (canDownLoad) {
                                DetailAllDisgussBean detailAllDisgussBean1 = (DetailAllDisgussBean) result;
                                datalist.addAll(detailAllDisgussBean1.getData().getList());
                                detailAllDisgussAdapter.notifyDataSetChanged();
                                if (detailAllDisgussBean1.getData().getList().size()>1){
                                    lastId = detailAllDisgussBean1.getData().getList().get(detailAllDisgussBean1.getData().getList().size() - 1).getId();
                                }
                            }
                        }
                        @Override
                        public void fail(String result) {
                             canDownLoad =false;
                        }
                    });
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isToBottom = firstVisibleItem + visibleItemCount == totalItemCount;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_alldisguss_back:
                onBackPressed();
                break;
            case R.id.tv_all_disguss_fabu:
                //// TODO: 2016/12/23 发布帖子功能

                break;
            default:
                break;
        }
    }
}
