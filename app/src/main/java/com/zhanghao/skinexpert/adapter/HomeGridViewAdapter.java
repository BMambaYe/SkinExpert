package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2016/12/21.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Object>> list;
    private LayoutInflater inflater;

    public HomeGridViewAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_gridview_item,parent,false);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_home_gridview_item);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_home_gridview_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).get("name")+"");
        holder.imageView.setImageDrawable((Drawable) list.get(position).get("pic"));
        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
