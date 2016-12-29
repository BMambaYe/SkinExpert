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

import com.zhanghao.skinexpert.Activity.ElementDetailActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductElementAdapter;
import com.zhanghao.skinexpert.beans.HotElementWordBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementSearchFragment extends Fragment implements NetWorkRequest.RequestCallBack {

    private ListView listView;
    private List<HotElementWordBean.DataBean.ListBean> list;
    private ProductElementAdapter adapter;
    private SQLiteDatabase db;
    private int titleNumber = -1;

    public ElementSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_element_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView = ((ListView) view.findViewById(R.id.lv_element_search));
        list = new ArrayList<>();
        adapter = new ProductElementAdapter(getActivity(), list);
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
        NetWorkRequest.getProductHotElementWords(getActivity(), "1", null, this);
    }

    @Override
    public void success(Object result) {
        list.clear();
        HotElementWordBean searchWords = (HotElementWordBean) result;
        if (searchWords.getData().getList() != null && searchWords.getData().getList().size() > 0) {
            HotElementWordBean.DataBean.ListBean titleBean = new HotElementWordBean.DataBean.ListBean();
            titleBean.setName("title");
            list.add(titleBean);
            for (HotElementWordBean.DataBean.ListBean bean : searchWords.getData().getList()) {
                list.add(bean);
            }
            Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.table_element_history, null);
            List<HotElementWordBean.DataBean.ListBean> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                HotElementWordBean.DataBean.ListBean bean = new HotElementWordBean.DataBean.ListBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("search_id")));
                bean.setName(cursor.getString(cursor.getColumnIndex("search")));
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
                String searchName = list.get(position).getName();
                saveSearchData(searchId, searchName);
                Intent intent = new Intent(getActivity(), ElementDetailActivity.class);
                intent.putExtra("element_id", searchId);
                startActivity(intent);
                getActivity().finish();
            }
        }
    };

    private void saveSearchData(int searchId, String searchName) {
        if (!"".equals(searchName) && searchName != null) {
            db.delete(SQLiteHelper.table_element_history, "search_id=?", new String[]{searchId + ""});
            ContentValues contentValues = new ContentValues();
            contentValues.put("search_id", searchId);
            contentValues.put("search", searchName);
            db.insert(SQLiteHelper.table_element_history, null, contentValues);
        }
    }
}
