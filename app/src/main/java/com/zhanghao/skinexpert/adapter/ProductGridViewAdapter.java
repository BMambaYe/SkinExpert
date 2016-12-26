package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class ProductGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<Map<String, Object>> list = new ArrayList<>();
    private String colorName;

    public ProductGridViewAdapter(Context context, List<Map<String, Object>> list) {
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
        convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
        String name = (String) list.get(position).get("name");
        textView.setText(name);
        if (name.equals(colorName)) {
            textView.setTextColor(Color.parseColor("#FF6D72"));
        }
        return convertView;
    }

    public void setColor(String name) {
        colorName = name;
        notifyDataSetChanged();
    }
}
