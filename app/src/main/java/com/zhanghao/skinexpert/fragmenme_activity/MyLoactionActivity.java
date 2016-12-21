package com.zhanghao.skinexpert.fragmenme_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.fragmenme_activity.adapter.CommonAdapter;
import com.zhanghao.skinexpert.fragmenme_activity.bean.LocationDataBean;
import com.zhanghao.skinexpert.fragmenme_activity.view.SwipListView;
import com.zhanghao.skinexpert.fragmenme_activity.view.SwipViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyLoactionActivity extends AppCompatActivity {
    private SwipListView listView;
    private Button btnBack;
    private Context context;
    private Button btnAddNew;
    private List<LocationDataBean> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loaction);
        context = MyLoactionActivity.this;
        initView();
        initData();
        setOnClick();
        listView.setAdapter(new CommonAdapter<LocationDataBean>(this,mDatas,R.layout.item_cst_swipe) {
            @Override
            public void convert(final SwipViewHolder holder, LocationDataBean locationDataBean, final int position, View convertView) {
                holder.setText(R.id.receiver_name,locationDataBean.getName());
                holder.setText(R.id.phone_number,locationDataBean.getPhoneNum());
                holder.setText(R.id.details_location,locationDataBean.getLocationdetails());
                holder.setOnClickListener(R.id.swip_lv_item_ll, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentToChangeLocationAct = new Intent(MyLoactionActivity.this,ChangeLocationActivity.class);
                      //TODO
                        // 需要传入参数，用于在界面上显示历史的收货地址
                        startActivity(intentToChangeLocationAct);
                        finish();
                    }
                });
                holder.setOnClickListener(R.id.btnTop, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentToChangeLocationAct = new Intent(MyLoactionActivity.this,ChangeLocationActivity.class);
                        //TODO
                        // 需要传入参数，用于在界面上显示历史的收货地址
                        startActivity(intentToChangeLocationAct);
                        finish();
                    }
                });
                //点击删除ListView的对应子项目，并且删除后台对应的数据
                holder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //在ListView里，点击侧滑菜单上的选项时，如果想让擦花菜单同时关闭，调用这句话
                        ((SwipeMenuLayout) holder.getConvertView()).quickClose();
                        mDatas.remove(position);
                        notifyDataSetChanged();
                        //TODO
                        //删除数据库对应的数据
                    }
                });
            }
        });
    }
    //返回到主Activity对应的我的界面
    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddLocationAct = new Intent(context,ChangeLocationActivity.class);
                intentToAddLocationAct.putExtra("title",getResources().getString(R.string.add_location_title));
                startActivity(intentToAddLocationAct);
                finish();
            }
        });
    }

    //初始化数据
    private void initData() {
        //添加模拟数据
        for (int i = 0; i <6 ; i++) {
            LocationDataBean bean = new LocationDataBean("rock-"+i,"上海-"+i,"1831818318"+i);
            mDatas.add(bean);
        }

    }

    private void initView() {
        listView = (SwipListView) findViewById(R.id.my_location_lv);
        btnBack = (Button) findViewById(R.id.my_locationbtn_back);
        btnAddNew = (Button) findViewById(R.id.my_location_add_new);
    }
}
