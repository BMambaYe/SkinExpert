package com.zhanghao.skinexpert.fragments;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhanghao.skinexpert.Activity.ProductLibraryActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductSearchAdapter;
import com.zhanghao.skinexpert.beans.SearchFragmentEventBean;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSearchFragment extends Fragment {

    private ListView listView;
    private List<String> list;
    private ProductSearchAdapter adapter;
    private SQLiteDatabase db;

    public ProductSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = ((ListView) view.findViewById(R.id.lv_product_search));
        list = new ArrayList<>();
        adapter = new ProductSearchAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        initData();
        listView.setOnItemClickListener(itemClickListener);
    }

    private void initData() {
        list.add("热门搜索");
        String[] stringArray = getResources().getStringArray(R.array.productSearch);
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
        SQLiteHelper helper = new SQLiteHelper(getActivity());
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.table_search_history, null);
        List<String> listData = new ArrayList<>();
        while (cursor.moveToNext()) {
            listData.add(cursor.getString(cursor.getColumnIndex("search")));
        }
        if (listData.size() > 0) {
            list.add("热门历史");
            for (int i = listData.size() - 1; i >= 0; i--) {
                list.add(listData.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position != 0 && position != 5) {
                String s = list.get(position);
                saveSearchData(s);
                Intent intent = new Intent(getActivity(), ProductLibraryActivity.class);
                intent.putExtra("search", s);
                startActivity(intent);
                getActivity().finish();
            }
        }
    };

    private void saveSearchData(String s) {
        if (!"".equals(s) && s != null) {
            SQLiteHelper helper = new SQLiteHelper(getActivity());
            db = helper.getReadableDatabase();
            db.delete(SQLiteHelper.table_search_history, "search=?", new String[]{s});
            ContentValues contentValues = new ContentValues();
            contentValues.put("search", s);
            db.insert(SQLiteHelper.table_search_history, null, contentValues);
        }
    }

    @Subscribe
    public void onEventMainThread(SearchFragmentEventBean bean) {
        if (bean.isRefresh()&&list!=null) {
            list.clear();
            initData();
        }
    }
}
