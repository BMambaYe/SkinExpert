package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.DetailElementBean;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/24.
 */

public class AllElementsAdapter extends BaseAdapter {
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> elements;
    private Context context;
    private LayoutInflater inflater;

    public AllElementsAdapter(List<DetailElementBean.DataBean.ListBean.ElementListBean> elements, Context context) {
        this.elements = elements;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.item_all_elements,null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_all_element_name);
        ImageView img_isFun = (ImageView) view.findViewById(R.id.img_all_element_isfun);
        LinearLayout ll_show_cation = (LinearLayout) view.findViewById(R.id.ll_all_element_cation);
        LinearLayout ll_show_func = (LinearLayout) view.findViewById(R.id.ll_all_element_func);
        tv_name.setText(elements.get(position).getElementChinaName());
        if (!elements.get(position).isFuncElement()){
            img_isFun.setVisibility(View.INVISIBLE);
        }
        if (elements.get(position).isSensitization()){
            TextView tv_min=new TextView(context);
            tv_min.setText("敏");
            tv_min.setTextColor(Color.RED);
            LinearLayout.LayoutParams prams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            prams.setMargins(3,3,3,3);
            tv_min.setLayoutParams(prams);
            ll_show_cation.addView(tv_min);
        }

        if (elements.get(position).isPimpleCaution()){
            TextView tv_dou=new TextView(context);
            tv_dou.setText("痘");
            tv_dou.setTextColor(Color.RED);
            LinearLayout.LayoutParams prams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            prams.setMargins(3,3,3,3);
            tv_dou.setLayoutParams(prams);
            ll_show_cation.addView(tv_dou);
        }

        if (elements.get(position).isPregnantCaution()){
            TextView tv_yun=new TextView(context);
            tv_yun.setText("孕");
            tv_yun.setTextColor(Color.RED);
            LinearLayout.LayoutParams prams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            prams.setMargins(3,3,3,3);
            tv_yun.setLayoutParams(prams);
            ll_show_cation.addView(tv_yun);
        }


        String[] funs=elements.get(position).getFunc().split(",");
        for (int i = 0; i < (funs.length >=3 ? 3:funs.length); i++) {
            TextView tv_fun=new TextView(context);
            tv_fun.setText(funs[i]);
            tv_fun.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity= Gravity.CENTER_HORIZONTAL;
            tv_fun.setLayoutParams(params);
            ll_show_func.addView(tv_fun);
        }
        return view;
    }

}
