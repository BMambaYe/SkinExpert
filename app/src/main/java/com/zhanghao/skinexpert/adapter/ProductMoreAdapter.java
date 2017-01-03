package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.ProductMoreBean;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class ProductMoreAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<ProductMoreBean.DataBean.ListBean> listBeen;
    private String token;
    private String skinCode;

    public ProductMoreAdapter(Context context, List<ProductMoreBean.DataBean.ListBean> listBeen,String token,String skinCode) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listBeen = listBeen;
        this.token = token;
        this.skinCode = skinCode;
    }

    @Override
    public int getCount() {
        return listBeen == null ? 0 : listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen == null ? null : listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView title, score, textOil,textSensitive, effect;
        RatingBar ratingBar;
        ImageView imageView;
        LinearLayout linearLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.product_more_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_product_more_title);
            holder.score = (TextView) convertView.findViewById(R.id.tv_product_more_score);
            holder.effect = (TextView) convertView.findViewById(R.id.tv_product_more_effect);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.rb_product_more);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_product_more);
            holder.textOil = (TextView) convertView.findViewById(R.id.tv_product_more_oil);
            holder.textSensitive = (TextView) convertView.findViewById(R.id.tv_product_more_sensitive);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_product_more_sensitive);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductMoreBean.DataBean.ListBean bean = listBeen.get(position);
        holder.title.setText(bean.getBrandChinaName() + bean.getName());
        int count = bean.getReviewCount();
        float count2 = Float.parseFloat(bean.getReviewScore());
        if (count2 == 0.0) {
            holder.score.setText("暂无评分");
        } else {
            holder.score.setText("评分：" + count2);
        }
        holder.effect.setText(bean.getEffectAbstract());
        holder.ratingBar.setRating(Float.parseFloat(bean.getReviewScore()) / 2.0f);
        if (!"".equals(bean.getPic()))
            Picasso.with(context).load(bean.getPic()).into(holder.imageView);
        if (!"".equals(token)&&token!=null&&!"----".equals(skinCode)&&!"".equals(skinCode)&&skinCode!=null&&listBeen.get(position).isSkinSuggestionApplied()) {
            judgeSkinCode1(position,holder);
            holder.linearLayout.setVisibility(View.VISIBLE);
        }else{
            holder.textOil.setText("尚未咨询");
            holder.textOil.setTextColor(Color.parseColor("#00D1C1"));
            holder.linearLayout.setVisibility(View.GONE);
        }
        return convertView;
    }

    private void judgeSkinCode1(int position,ViewHolder holder) {
        int skinId = Integer.parseInt(skinCode);
        int oilCode = skinId / 1000;
        switch (oilCode) {
            case 0:
                judgeSkinCode2(holder,listBeen.get(position).getSkinSuggestion().isDryHeavy(), "重干");
                break;
            case 1:
                judgeSkinCode2(holder,listBeen.get(position).getSkinSuggestion().isDryLight(), "轻干");
                break;
            case 2:
                judgeSkinCode2(holder,listBeen.get(position).getSkinSuggestion().isOilLight(), "轻油");
                break;
            case 3:
                judgeSkinCode2(holder,listBeen.get(position).getSkinSuggestion().isOilHeavy(), "重油");
                break;
        }
        int sensitiveCode = skinId % 1000 / 100;
        switch (sensitiveCode) {
            case 0:
                judgeSkinCode3(holder,listBeen.get(position).getSkinSuggestion().isToleranceHeavy());
                break;
            case 1:
                judgeSkinCode3(holder,listBeen.get(position).getSkinSuggestion().isToleranceLight());
                break;
            case 2:
                judgeSkinCode3(holder,listBeen.get(position).getSkinSuggestion().isSensitiveLight());
                break;
            case 3:
                judgeSkinCode3(holder,listBeen.get(position).getSkinSuggestion().isSensitiveHeavy());
                break;
        }
    }

    private void judgeSkinCode2(ViewHolder holder, boolean isMake, String name) {
        if (isMake) {
            holder.textOil.setText(name + "适用");
            holder.textOil.setTextColor(Color.parseColor("#00D1C1"));
        } else {
            holder.textOil.setText(name + "慎用");
            holder.textOil.setTextColor(Color.parseColor("#FF6D72"));
        }
    }

    private void judgeSkinCode3(ViewHolder holder, boolean isMake) {
        if (isMake) {
            holder.textSensitive.setText("低");
            holder.textSensitive.setTextColor(Color.parseColor("#00D1C1"));
        } else {
            holder.textSensitive.setText("高");
            holder.textSensitive.setTextColor(Color.parseColor("#FF6D72"));
        }
    }
}
