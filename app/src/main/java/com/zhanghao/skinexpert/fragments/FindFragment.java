package com.zhanghao.skinexpert.fragments;

import android.content.Intent;
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
import com.zhanghao.skinexpert.Activity.BeautifulActivity;
import com.zhanghao.skinexpert.Activity.RecommendTagsActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.CommunityAdapter;
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
    private View viewHead;
    private List<CommunityBean.DataBean.ListBean> dataList = new ArrayList<>();
    private List<CommunityListViewBean.DataBean.ListBean> allList = new ArrayList<>();
    private ListView listview;
    private ImageView iv_listview_header;
    private TextView tv_more;
    private CommunityAdapter adapter;
    private View view;
    private ImageView community_like_iv;
    public FindFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_find, container, false);
            listview = (ListView) view.findViewById(R.id.listview);
            viewHead = inflater.inflate(R.layout.community_listview_header, null);
            iv_listview_header = (ImageView) viewHead.findViewById(R.id.iv_listview_header);
            //head图片跳转
            iv_listview_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BeautifulActivity.class);
                    startActivity(intent);
                }
            });
            //more监听
            tv_more= (TextView) viewHead.findViewById(R.id.tv_more);
            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), RecommendTagsActivity.class);
                    startActivity(intent);
                }
            });
            linearlayout = (LinearLayout) viewHead.findViewById(R.id.linearlayout);
            loadData();
            listviewData();

        }
        return view;
    }


    private void listviewData() {
        NetWorkRequest.getCommunityListViewBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private CommunityListViewBean communityListViewBean;

            @Override
            public void success(Object result) {
                //对一些bean类进行初始化
                communityListViewBean = (CommunityListViewBean) result;
                allList = communityListViewBean.getData().getList();
                adapter = new CommunityAdapter(getActivity(), allList);
                listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initScrollView() {
        for (int i = 0; i < dataList.size(); i++) {
            View view = inflater.inflate(R.layout.horizontalscrollview, null);
            ImageView iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            TextView tv_textview = (TextView) view.findViewById(R.id.tv_textview);
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
                communityBean = (CommunityBean) result;
                dataList = communityBean.getData().getList();
                initScrollView();
                listview.addHeaderView(viewHead);
            }

            @Override
            public void fail(String result) {

            }
        });
    }
}
