package com.zhanghao.skinexpert.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommunityBean;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class FindFragment extends Fragment {
    private LinearLayout linearlayout;
    private LayoutInflater inflater;
    private List<CommunityBean.DataBean.ListBean> dataList=new ArrayList<>();
    private List<CommunityListViewBean.DataBean.UserBean> userList=new ArrayList<>();
    private List<CommunityListViewBean.DataBean.ListBean> contentList=new ArrayList<>();
    private ListView listview;




    public FindFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        linearlayout= (LinearLayout) view.findViewById(R.id.linearlayout);
        listview= (ListView) view.findViewById(R.id.listview);
        loadData();

        listviewData();
        return view;
    }


    private void listviewData() {
        NetWorkRequest.getCommunityListViewBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private CommunityListViewBean communityListViewBean;
            @Override
            public void success(Object result) {
                communityListViewBean=(CommunityListViewBean) result;
                contentList=communityListViewBean.getData().getList();

            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initScrollView() {
        for(int i=0;i<dataList.size();i++){
            View view=inflater.inflate(R.layout.horizontalscrollview,null);
            ImageView iv_picture= (ImageView) view.findViewById(R.id.iv_picture);
            TextView tv_textview= (TextView) view.findViewById(R.id.tv_textview);
            Picasso.with(getActivity()).load(dataList.get(i).getImage()).into(iv_picture);
            tv_textview.setText(dataList.get(i).getCategoryName());
            linearlayout.addView(view);
        }
    }

    private void loadData() {
        NetWorkRequest.getCommunityBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private CommunityBean communityBean;
            @Override
            public void success(Object result) {
                communityBean= (CommunityBean)result;
                dataList=communityBean.getData().getList();
                initScrollView();
            }

            @Override
            public void fail(String result) {

            }
        });
    }
}
