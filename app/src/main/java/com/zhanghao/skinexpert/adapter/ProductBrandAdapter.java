package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.ProductBrandBean;

import java.util.List;

public class ProductBrandAdapter extends BaseAdapter implements SectionIndexer {
    private List<ProductBrandBean> list;
    private Context context;
    private String colorName;

    public ProductBrandAdapter(Context context, List<ProductBrandBean> list) {
        this.context = context;
        this.list = list;
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.product_brand_sort_item, null);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_brand_sort_item);
        TextView tvLetter = (TextView) convertView.findViewById(R.id.tv_title_sort_item);
        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            tvLetter.setVisibility(View.VISIBLE);
            tvLetter.setText(list.get(position).getSortLetters());
        } else {
            tvLetter.setVisibility(View.GONE);
        }
        String name = list.get(position).getName() + list.get(position).getEnglishName();
        tvTitle.setText(name);
        if (name.equals(colorName)) {
            tvTitle.setTextColor(Color.parseColor("#FF6D72"));
        }
        return convertView;
    }

    //根据ListView的当前位置获取分类的首字母的Char ascii值
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    //根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    //提取英文的首字母，非英文字母用#代替。
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        //正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    public void setColor(String name) {
        colorName = name;
        notifyDataSetChanged();
    }
}