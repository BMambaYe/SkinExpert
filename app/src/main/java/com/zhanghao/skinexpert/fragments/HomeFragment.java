package com.zhanghao.skinexpert.fragments;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.Activity.AboutSkinActivity;
import com.zhanghao.skinexpert.Activity.ArticleActivity;
import com.zhanghao.skinexpert.Activity.InterTestActivity;
import com.zhanghao.skinexpert.Activity.InviteFriendsActivity;
import com.zhanghao.skinexpert.Activity.ProductDetailActivity;
import com.zhanghao.skinexpert.Activity.ProductMoreActivity;
import com.zhanghao.skinexpert.Activity.ProductPresalesActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.HomeGridViewAdapter;
import com.zhanghao.skinexpert.adapter.HomeListViewAdapter;
import com.zhanghao.skinexpert.beans.HomeDataBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NetWorkRequest.RequestCallBack {

    private View view;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    //数据
    private HomeDataBean homeDataBean;
    //TOP
    private LinearLayout topLinearLayout;
    private ImageView headPic;
    private GridView gridView;
    private List<Map<String, Object>> gridViewList;
    private HomeGridViewAdapter gridViewAdapter;
    //TOP1
    private LinearLayout top1LinearLayout;
    private ImageView top1Pic;
    private TextView top1Count;
    private TextView top1Title;
    private TextView top1Effect;
    private TextView top1Score;
    private RatingBar top1ScoreRB;
    //TOP2
    private List<HomeDataBean.DataBean.Top2Bean> listViewList;
    private HomeListViewAdapter listViewAdapter;
    private TextView top1ProductMore;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.lv_home);
        listView.setOnItemClickListener(listViewItemListener);
        initSwipeRefreshLayout();
        initHomeData();
        initTopLayout();
        initTop1Layout();
        initTop2Layout();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_red, R.color.refresh_red1, R.color.refresh_red2, R.color.refresh_red3);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initHomeData();
            }
        });
    }

    private void initTopLayout() {
        topLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_listview_top, null);
        gridView = ((GridView) topLinearLayout.findViewById(R.id.home_gv));
        headPic = ((ImageView) topLinearLayout.findViewById(R.id.iv_home_head));

        headPic.setOnClickListener(headPicListener);

        gridViewList = new ArrayList<>();
        gridViewAdapter = new HomeGridViewAdapter(getActivity(), gridViewList);
        gridView.setAdapter(gridViewAdapter);
        listView.addHeaderView(topLinearLayout, null, true);
    }

    private void initTop1Layout() {
        top1LinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_listview_top1, null);
        top1Pic = ((ImageView) top1LinearLayout.findViewById(R.id.iv_home_top1));
        top1Count = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_count));
        top1Title = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_title));
        top1Effect = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_effect));
        top1Score = (TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_score);
        top1ScoreRB = (RatingBar) top1LinearLayout.findViewById(R.id.rb_home_top1_score);
        top1ProductMore = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_moreProduct));
        top1ProductMore.setOnClickListener(productMoreListener);
        listView.addHeaderView(top1LinearLayout, null, true);
    }

    private void initTop2Layout() {
        listViewList = new ArrayList<>();
        listViewAdapter = new HomeListViewAdapter(listViewList, getActivity());
        listView.setAdapter(listViewAdapter);
    }

    private void initHomeData() {
        NetWorkRequest.getHomeDataBean(getActivity(), "", "0", this);
    }

    private void initTop() {
        String topPic = homeDataBean.getData().getBannerImage();
        Picasso.with(getActivity()).load(topPic).into(headPic);

        gridViewList.clear();
        String[] gridViewNames = getResources().getStringArray(R.array.homeGridViewNames);
        TypedArray gridViewPics = getResources().obtainTypedArray(R.array.homeGridViewPics);
        if (gridViewNames.length == gridViewPics.length()) {
            for (int i = 0; i < gridViewNames.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("pic", gridViewPics.getDrawable(i));
                map.put("name", gridViewNames[i]);
                gridViewList.add(map);
            }
        }
        gridViewAdapter.notifyDataSetChanged();
    }

    private void initTop1() {
        String top1name1 = homeDataBean.getData().getTop1().getBrandChinaName();
        String top1name2 = homeDataBean.getData().getTop1().getName();
        String top1pic = homeDataBean.getData().getTop1().getImage();
        int top1count = homeDataBean.getData().getTop1().getReviewCount();
        float top1score = Float.parseFloat(homeDataBean.getData().getTop1().getReviewScore());
        String top1effect = homeDataBean.getData().getTop1().getEffectAbstract();

        Picasso.with(getActivity()).load(top1pic).into(top1Pic);
        top1Title.setText(top1name1 + "" + top1name2);
        top1Effect.setText(top1effect);
        if (top1count == 0) {
            top1Count.setText("暂无评论");
        } else {
            top1Count.setText("评论：" + top1count);
        }
        if (top1count == 0.0) {
            top1ScoreRB.setRating(top1count);
            top1Score.setText("暂无评分");
        } else {
            top1ScoreRB.setRating(top1count);
            top1Score.setText("评分：" + top1score);
        }
    }

    private void initTop2() {
        listViewList.clear();
        List<HomeDataBean.DataBean.Top2Bean> list = homeDataBean.getData().getTop2();
        for (HomeDataBean.DataBean.Top2Bean top2Bean : list) {
            listViewList.add(top2Bean);
        }
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void success(Object result) {
        swipeRefreshLayout.setRefreshing(false);
        homeDataBean = (HomeDataBean) result;
        initTop();
        initTop1();
        initTop2();
    }

    @Override
    public void fail(String result) {
        Toast.makeText(getActivity(), "" + result, Toast.LENGTH_SHORT).show();
    }

    AdapterView.OnItemClickListener listViewItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 1) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("id",homeDataBean.getData().getTop1().getPid());
                intent.putExtra("buy","totaobao");
                startActivity(intent);
            }
            if (position == 2) {
                Intent intent = new Intent(getActivity(), ProductPresalesActivity.class);
                intent.putExtra("url", homeDataBean.getData().getTop2().get(0).getParam().get(0));
                startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(getActivity(), InviteFriendsActivity.class);
                startActivity(intent);
            } else if (position == 4) {
                Intent intent = new Intent(getActivity(), AboutSkinActivity.class);
                startActivity(intent);
            } else if (position > 4) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("url", homeDataBean.getData().getTop2().get(position - 2).getObjectId());
                startActivity(intent);
            }
        }
    };

    View.OnClickListener headPicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), InterTestActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener productMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ProductMoreActivity.class);
            startActivity(intent);
        }
    };
}
