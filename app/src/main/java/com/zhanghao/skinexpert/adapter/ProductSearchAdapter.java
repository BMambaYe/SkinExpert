package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.HotSearchWordBean;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/27.
 */

public class ProductSearchAdapter extends BaseAdapter {

    private Context context;
    private List<HotSearchWordBean.DataBean.ListBean> list;
    private LayoutInflater inflater;
    private int titleNumber = 0;

    public ProductSearchAdapter(Context context, List<HotSearchWordBean.DataBean.ListBean> list) {
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
        if (position == 0) {
            convertView = inflater.inflate(R.layout.product_search_top_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_product_search_top_item);
            TextView textView1 = (TextView) convertView.findViewById(R.id.tv_product_search_top_item_cleaner);
            textView1.setVisibility(View.GONE);
            textView.setText("热门搜索");
        } else {
            String name = list.get(position).getContent();
            if ("title".equals(name)) {
                titleNumber = position;
                convertView = inflater.inflate(R.layout.product_search_top_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_product_search_top_item);
                TextView textView1 = (TextView) convertView.findViewById(R.id.tv_product_search_top_item_cleaner);
                textView.setText("搜索历史");
                textView1.setOnClickListener(listener);
            } else {
                convertView = inflater.inflate(R.layout.product_search_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_product_search_item);
                textView.setText(list.get(position).getContent());
            }
        }
        return convertView;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteHelper helper = new SQLiteHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            db.delete(SQLiteHelper.table_search_history, null, null);
            for (int i = list.size() - 1; i >= titleNumber; i--) {
                list.remove(i);
            }
            notifyDataSetChanged();
        }
    };
}
