package com.zhanghao.skinexpert.fragments;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.HomeGridViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private GridView gridView;
    private List<Map<String,Object>> gridViewList;
    private HomeGridViewAdapter adapter;

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
        initGridView();
    }

    private void initGridView() {
        gridViewList = new ArrayList<>();
        String[] gridViewNames = getResources().getStringArray(R.array.homeGridViewNames);
        TypedArray gridViewPics = getResources().obtainTypedArray(R.array.homeGridViewPics);
        if (gridViewNames.length==gridViewPics.length()){
            for (int i = 0; i < gridViewNames.length; i++) {
                Map<String,Object> map = new HashMap<>();
                map.put("pic",gridViewPics.getDrawable(i));
                map.put("name",gridViewNames[i]);
                gridViewList.add(map);
            }
        }
        adapter = new HomeGridViewAdapter(getActivity(),gridViewList);
        gridView.setAdapter(adapter);
    }
}
