package com.zhanghao.skinexpert.fragments;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.HomeGridViewAdapter;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NetWorkRequest.RequestCallBack {


    private GridView gridView;
    private List<Map<String, Object>> gridViewList;
    private HomeGridViewAdapter adapter;

    private ImageView headPic;
    private ImageView top1Pic;
    private TextView top1Count;
    private TextView top1Title;
    private TextView top1Effect;
    private TextView top1Score;
    private RatingBar top1ScoreRB;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridView = ((GridView) view.findViewById(R.id.home_gv));
        headPic = ((ImageView) view.findViewById(R.id.iv_home_head));
        top1Pic = ((ImageView) view.findViewById(R.id.iv_home_top1));
        top1Count = ((TextView) view.findViewById(R.id.tv_home_top1_count));
        top1Title = ((TextView) view.findViewById(R.id.tv_home_top1_title));
        top1Effect = ((TextView) view.findViewById(R.id.tv_home_top1_effect));
        top1Score = (TextView) view.findViewById(R.id.tv_home_top1_score);
        top1ScoreRB = (RatingBar) view.findViewById(R.id.rb_home_top1_score);
        initGridView();
        initHomeData();
    }

    private void initHomeData() {
        NetWorkRequest.postMainData(getActivity(), Constant.MAINLIST, "", "0", this);
    }

    private void initGridView() {
        gridViewList = new ArrayList<>();
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
        adapter = new HomeGridViewAdapter(getActivity(), gridViewList);
        gridView.setAdapter(adapter);
    }

    @Override
    public void success(Object result) {
        try {
            String resultTap = (String) result;
            JSONObject jsonObject = new JSONObject(resultTap);
            JSONObject jsonObject2 = jsonObject.getJSONObject("data");
            String topPic = jsonObject2.getString("BannerImage");

            JSONObject jsonObject3 = jsonObject2.getJSONObject("top1");
            String name1 = jsonObject3.getString("brandChinaName");
            String name2 = jsonObject3.getString("name");
            String pic = jsonObject3.getString("image");
            int count = jsonObject3.getInt("reviewCount");
            float score = Float.parseFloat(jsonObject3.getString("reviewScore"));
            String effect = jsonObject3.getString("effectAbstract");

            Picasso.with(getActivity()).load(topPic).into(headPic);
            Picasso.with(getActivity()).load(pic).into(top1Pic);
            top1Title.setText(name1+""+name2);
            top1Effect.setText(effect);
            if (count == 0) {
                top1Count.setText("暂无评论");
            } else {
                top1Count.setText("评论：" + count);
            }
            if (count == 0.0){
                top1ScoreRB.setRating(count);
                top1Score.setText("暂无评分");
            }else{
                top1ScoreRB.setRating(count);
                top1Score.setText("评分："+score);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail(String result) {
        Toast.makeText(getActivity(), "" + result, Toast.LENGTH_SHORT).show();
    }
}
