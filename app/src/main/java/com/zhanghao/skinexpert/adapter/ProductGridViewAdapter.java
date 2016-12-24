package com.zhanghao.skinexpert.adapter;

import android.content.Context;
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
    private int type;

    public ProductGridViewAdapter(Context context,List<Map<String, Object>> list,int type) {
        this.context = context;
        this.list = list;
        this.type = type;
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
        switch (type){
            case 0:
                convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
                textView.setText(list.get(position).get("name")+"");
                break;
            case 1:
                convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
                TextView textView1 = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
                textView1.setText(list.get(position).get("name")+"");
                break;
            case 2:
                convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
                TextView textView2 = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
                textView2.setText(list.get(position).get("name")+"");
                break;
            case 3:
                convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
                TextView textView3 = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
                textView3.setText(list.get(position).get("name")+"");
                break;
            default:
                break;
        }
        return convertView;
    }
}
