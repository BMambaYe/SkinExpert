package com.zhanghao.skinexpert.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhanghao.skinexpert.R;

/**
 * Created by RockGao on 2016/12/22.
 */

public class MyLikePostFragment extends Fragment {
    private ListView listView;
    public MyLikePostFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mylike_post_fragment,null);
        listView = (ListView) view.findViewById(R.id.mylike_post_lv);
        return view;
    }
}
