package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.SearchFragmentEventBean;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/27.
 */

public class ProductSearchAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public ProductSearchAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0 || position == 5) {
            convertView = inflater.inflate(R.layout.product_search_top_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_product_search_top_item);
            TextView textView1 = (TextView) convertView.findViewById(R.id.tv_product_search_top_item_cleaner);
            if (position == 0) {
                textView1.setVisibility(View.GONE);
            }
            textView.setText(list.get(position));
            textView1.setOnClickListener(listener);
        } else {
            convertView = inflater.inflate(R.layout.product_search_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_product_search_item);
            textView.setText(list.get(position));
        }
        return convertView;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteHelper helper = new SQLiteHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            db.delete(SQLiteHelper.table_search_history, null, null);
            SearchFragmentEventBean bean = new SearchFragmentEventBean();
            bean.setRefresh(true);
            EventBus.getDefault().post(bean);
        }
    };
}
