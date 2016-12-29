package com.zhanghao.skinexpert.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.MyRecyclerViewAdapter;
import com.zhanghao.skinexpert.interfaces.FragmentDataOnRefresh;

import java.util.List;
import java.util.Map;

/**
 * Created by RockGao on 2016/12/21.
 */

public class MyProductFragment1 extends Fragment{
    private RecyclerView recyclerView;
    private FragmentDataOnRefresh onRefresh;
    private static  List<Map<String,String>> dataLists;
    private static MyRecyclerViewAdapter adapter;

    public MyProductFragment1() {
    }

    public MyProductFragment1(List<Map<String,String>> dataList) {
        dataLists=dataList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.myproduct_fragment1_layout,null);
        Log.i("RockTest:","测试点:这里");
        recyclerView = (RecyclerView) view.findViewById(R.id.my_product_fragment_recyclerview);
        adapter = new MyRecyclerViewAdapter(getContext(),dataLists);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return view;
    }
    public static void onRereshData1(List<Map<String, String>> dataList){
      dataLists =dataList;
      adapter.notifyDataSetChanged();
    }
}
