package com.zhanghao.skinexpert.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.MyRecyclerViewAdapter;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RockGao on 2016/12/21.
 */

public class MyProductFragment2 extends Fragment{
    private RecyclerView recyclerView;
//    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private List<Map<String,String>> dataList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    public MyProductFragment2() {
    }

    public MyProductFragment2(SQLiteDatabase db) {
//        sqLiteHelper = new SQLiteHelper(getContext());
//        db= sqLiteHelper.getReadableDatabase();
        this.db = db;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.myproduct_fragment1_layout,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_product_fragment_recyclerview);
        initData();
        adapter = new MyRecyclerViewAdapter(getContext(),dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return view;
    }
    private void initData() {
        Cursor cursor = db.query(SQLiteHelper.table_used,null,null,null,null,null,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                if (!"".equals(cursor.getString(cursor.getColumnIndex("product_pic")))){
                    Map<String,String> map = new HashMap<>();
                    map.put("product_id",cursor.getString(cursor.getColumnIndex("product_id")));
                    map.put("product_brand",cursor.getString(cursor.getColumnIndex("product_brand")));
                    map.put("product_name",cursor.getString(cursor.getColumnIndex("product_name")));
                    map.put("product_pic",cursor.getString(cursor.getColumnIndex("product_pic")));
                    dataList.add(map);
                }
            }
        }
        cursor.close();
    }
}
