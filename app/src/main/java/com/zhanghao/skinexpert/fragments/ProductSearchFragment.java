package com.zhanghao.skinexpert.fragments;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhanghao.skinexpert.Activity.ProductLibraryActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductSearchAdapter;
import com.zhanghao.skinexpert.beans.HotSearchWordBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSearchFragment extends Fragment implements NetWorkRequest.RequestCallBack {

    private ListView listView;
    private List<HotSearchWordBean.DataBean.ListBean> list;
    private ProductSearchAdapter adapter;
    private SQLiteDatabase db;
    private int titleNumber = -1;

    public ProductSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView = ((ListView) view.findViewById(R.id.lv_product_search));
        list = new ArrayList<>();
        adapter = new ProductSearchAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        initSQLite();
        listView.setOnItemClickListener(itemClickListener);
        initData();
    }

    private void initSQLite() {
        SQLiteHelper helper = new SQLiteHelper(getActivity());
        db = helper.getReadableDatabase();
    }

    private void initData() {
        NetWorkRequest.getProductHotSearchWords(getActivity(), this);
    }

    @Override
    public void success(Object result) {
        list.clear();
        HotSearchWordBean searchWords = (HotSearchWordBean) result;
        if (searchWords.getData().getList() != null && searchWords.getData().getList().size() > 0) {
            HotSearchWordBean.DataBean.ListBean titleBean = new HotSearchWordBean.DataBean.ListBean();
            titleBean.setContent("title");
            list.add(titleBean);
            for (HotSearchWordBean.DataBean.ListBean bean : searchWords.getData().getList()) {
                list.add(bean);
            }
            Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.table_search_history, null);
            List<HotSearchWordBean.DataBean.ListBean> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                HotSearchWordBean.DataBean.ListBean bean = new HotSearchWordBean.DataBean.ListBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("search_id")));
                bean.setContent(cursor.getString(cursor.getColumnIndex("search")));
                listData.add(bean);
            }
            if (listData.size() > 0) {
                list.add(titleBean);
                titleNumber = list.size() - 1;
                for (int i = listData.size() - 1; i >= 0; i--) {
                    list.add(listData.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String result) {
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position != 0 && position != titleNumber && list != null & list.size() > 0) {
                int searchId = list.get(position).getId();
                String searchName = list.get(position).getContent();
                saveSearchData(searchId, searchName);
                Intent intent = new Intent(getActivity(), ProductLibraryActivity.class);
                intent.putExtra("search", searchName);
                startActivity(intent);
                getActivity().finish();
            }
        }
    };

    private void saveSearchData(int searchId, String searchName) {
        if (!"".equals(searchName) && searchName != null) {
            db.delete(SQLiteHelper.table_search_history, "search_id=?", new String[]{searchId + ""});
            ContentValues contentValues = new ContentValues();
            contentValues.put("search_id", searchId);
            contentValues.put("search", searchName);
            db.insert(SQLiteHelper.table_search_history, null, contentValues);
        }
    }
}
