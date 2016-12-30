package com.zhanghao.skinexpert.fragments;


import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.zhanghao.skinexpert.Activity.LoginPromptActivity;
import com.zhanghao.skinexpert.Activity.ProductDetailActivity;
import com.zhanghao.skinexpert.Activity.ProductLibraryActivity;
import com.zhanghao.skinexpert.Activity.ProductMoreActivity;
import com.zhanghao.skinexpert.Activity.ProductPresalesActivity;
import com.zhanghao.skinexpert.Activity.ProductSearchActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.HomeGridViewAdapter;
import com.zhanghao.skinexpert.adapter.HomeListViewAdapter;
import com.zhanghao.skinexpert.application.MyApplication;
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
    private TextView productLibraries;
    private String topPic;
    //TOP1
    private LinearLayout top1LinearLayout;
    private ImageView top1Pic;
    private TextView top1Oil;
    private TextView top1Sensitive;
    private TextView top1Title;
    private TextView top1Effect;
    private TextView top1Score;
    private RatingBar top1ScoreRB;
    private LinearLayout top1SensitiveLL;
    //TOP2
    private List<HomeDataBean.DataBean.Top2Bean> listViewList;
    private HomeListViewAdapter listViewAdapter;
    private TextView top1ProductMore;
    private Button productSearch;

    private String token;
    private String skinCode;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = ((MyApplication) getActivity().getApplication()).getToken();
        skinCode = ((MyApplication) getActivity().getApplication()).getSkinCode();
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.lv_home);
        initTopLayout();
        initTop1Layout();
        initTop2Layout();
        initSwipeRefreshLayout();
        listView.setOnItemClickListener(listViewItemListener);
        initHomeData();
    }

    private void initTopLayout() {
        topLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_listview_top, null);
        gridView = ((GridView) topLinearLayout.findViewById(R.id.home_gv));
        headPic = ((ImageView) topLinearLayout.findViewById(R.id.iv_home_head));
        productLibraries = ((TextView) topLinearLayout.findViewById(R.id.tv_home_top1_library));
        productSearch = ((Button) topLinearLayout.findViewById(R.id.btn_home_search));

        gridViewList = new ArrayList<>();
        gridViewAdapter = new HomeGridViewAdapter(getActivity(), gridViewList);
        gridView.setAdapter(gridViewAdapter);
        listView.addHeaderView(topLinearLayout, null, true);

        productLibraries.setOnClickListener(libraryListener);
        headPic.setOnClickListener(headPicListener);
        gridView.setOnItemClickListener(gridViewListener);
        productSearch.setOnClickListener(searchListener);
    }

    private void initTop1Layout() {
        top1LinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_listview_top1, null);
        top1Pic = ((ImageView) top1LinearLayout.findViewById(R.id.iv_home_top1));
        top1Title = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_title));
        top1Effect = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_effect));
        top1Score = (TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_score);
        top1ScoreRB = (RatingBar) top1LinearLayout.findViewById(R.id.rb_home_top1_score);
        top1ProductMore = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_moreProduct));
        top1Oil = ((TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_oil));
        top1Sensitive = (TextView) top1LinearLayout.findViewById(R.id.tv_home_top1_sensitive);
        top1SensitiveLL = (LinearLayout) top1LinearLayout.findViewById(R.id.ll_home_top1_sensitive);
        listView.addHeaderView(top1LinearLayout, null, true);

        top1ProductMore.setOnClickListener(productMoreListener);
    }

    private void initTop2Layout() {
        listViewList = new ArrayList<>();
        listViewAdapter = new HomeListViewAdapter(listViewList, getActivity());
        listView.setAdapter(listViewAdapter);
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

    private void initHomeData() {
        NetWorkRequest.getHomeDataBean(getActivity(), token, "0", this);
    }

    private void initTop() {
        if (homeDataBean.getData().getBannerImage() != null) {
            topPic = homeDataBean.getData().getBannerImage();
            if (!"".equals(topPic))
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
    }

    private void initTop1() {
        if (homeDataBean.getData().getTop1() != null) {
            String top1name1 = homeDataBean.getData().getTop1().getBrandChinaName();
            String top1name2 = homeDataBean.getData().getTop1().getName();
            String top1pic = homeDataBean.getData().getTop1().getImage();
            float top1score = Float.parseFloat(homeDataBean.getData().getTop1().getReviewScore());
            String top1effect = homeDataBean.getData().getTop1().getEffectAbstract();

            if (!"".equals(top1pic))
                Picasso.with(getActivity()).load(top1pic).into(top1Pic);
            top1Title.setText(top1name1 + "" + top1name2);
            top1Effect.setText(top1effect);
            if (top1score == 0.0) {
                top1ScoreRB.setRating(top1score);
                top1Score.setText("暂无评分");
            } else {
                top1ScoreRB.setRating(top1score);
                top1Score.setText("评分：" + top1score);
            }
            top1ScoreRB.setRating(top1score / 2.0f);
            if (!"".equals(token) && token != null && !"----".equals(skinCode) && !"".equals(skinCode) && skinCode != null && homeDataBean.getData().getTop1().isSkinSuggestionApplied()) {
                judgeSkinCode1();
                top1SensitiveLL.setVisibility(View.VISIBLE);
            } else {
                top1Oil.setText("尚未咨询");
                top1Oil.setTextColor(Color.parseColor("#00D1C1"));
                top1SensitiveLL.setVisibility(View.GONE);
            }
        }
    }

    private void judgeSkinCode1() {
        int skinId = Integer.parseInt(skinCode);
        int oilCode = skinId / 1000;
        switch (oilCode) {
            case 0:
                judgeSkinCode2(homeDataBean.getData().getTop1().getSkinSuggestion().isDryHeavy(), "重干");
                break;
            case 1:
                judgeSkinCode2(homeDataBean.getData().getTop1().getSkinSuggestion().isDryLight(), "轻干");
                break;
            case 2:
                judgeSkinCode2(homeDataBean.getData().getTop1().getSkinSuggestion().isOilLight(), "轻油");
                break;
            case 3:
                judgeSkinCode2(homeDataBean.getData().getTop1().getSkinSuggestion().isOilHeavy(), "重油");
                break;
        }
        int sensitiveCode = skinId % 1000 / 100;
        switch (sensitiveCode) {
            case 0:
                judgeSkinCode3(homeDataBean.getData().getTop1().getSkinSuggestion().isToleranceHeavy());
                break;
            case 1:
                judgeSkinCode3(homeDataBean.getData().getTop1().getSkinSuggestion().isToleranceLight());
                break;
            case 2:
                judgeSkinCode3(homeDataBean.getData().getTop1().getSkinSuggestion().isSensitiveLight());
                break;
            case 3:
                judgeSkinCode3(homeDataBean.getData().getTop1().getSkinSuggestion().isSensitiveHeavy());
                break;
        }
    }

    private void judgeSkinCode2(boolean isMake, String name) {
        if (isMake) {
            top1Oil.setText(name + "适用");
            top1Oil.setTextColor(Color.parseColor("#00D1C1"));
        } else {
            top1Oil.setText(name + "慎用");
            top1Oil.setTextColor(Color.parseColor("#FF6D72"));
        }
    }

    private void judgeSkinCode3(boolean isMake) {
        if (isMake) {
            top1Sensitive.setText("低");
            top1Sensitive.setTextColor(Color.parseColor("#00D1C1"));
        } else {
            top1Sensitive.setText("高");
            top1Sensitive.setTextColor(Color.parseColor("#FF6D72"));
        }
    }

    private void initTop2() {
        listViewList.clear();
        if (homeDataBean.getData().getTop2() != null) {
            List<HomeDataBean.DataBean.Top2Bean> list = homeDataBean.getData().getTop2();
            for (HomeDataBean.DataBean.Top2Bean top2Bean : list) {
                listViewList.add(top2Bean);
            }
            listViewAdapter.notifyDataSetChanged();
        }
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
        swipeRefreshLayout.setRefreshing(false);
    }

    AdapterView.OnItemClickListener listViewItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (homeDataBean != null) {
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    intent.putExtra("id", homeDataBean.getData().getTop1().getPid());
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
        }
    };

    View.OnClickListener headPicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!"".equals(topPic) && !"".equals(token)) {
                Intent intent = new Intent(getActivity(), InterTestActivity.class);
                startActivity(intent);
            } else if (!"".equals(topPic)) {
                Intent intent = new Intent(getActivity(), LoginPromptActivity.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener productMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ProductMoreActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener libraryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ProductLibraryActivity.class);
            startActivity(intent);
        }
    };

    AdapterView.OnItemClickListener gridViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (gridViewList != null && gridViewList.size() > 0) {
                int productId = getResources().getIntArray(R.array.homeGridViewId)[position];
                Intent intent = new Intent(getActivity(), ProductLibraryActivity.class);
                intent.putExtra("classifyId", productId);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ProductSearchActivity.class);
            startActivity(intent);
        }
    };
}
