package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.HomeDataBean;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/22.
 */

public class HomeListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeDataBean.DataBean.Top2Bean> list;

    public HomeListViewAdapter(List<HomeDataBean.DataBean.Top2Bean> list, Context context) {
        this.list = list;
        this.context = context;
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

    class ViewHolder{
        private ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_listview_top2, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_home_listView_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getBannerImage()).into(holder.imageView);
        return convertView;
    }
}
