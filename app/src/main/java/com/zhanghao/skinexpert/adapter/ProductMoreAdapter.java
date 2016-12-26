package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

    public ProductMoreAdapter(Context context, List<ProductMoreBean.DataBean.ListBean> listBeen) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listBeen = listBeen;
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
        TextView title, score, commit, effect;
        RatingBar ratingBar;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.product_more_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_product_more_title);
            holder.score = (TextView) convertView.findViewById(R.id.tv_product_more_score);
            holder.commit = (TextView) convertView.findViewById(R.id.tv_product_more_commit);
            holder.effect = (TextView) convertView.findViewById(R.id.tv_product_more_effect);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.rb_product_more);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_product_more);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductMoreBean.DataBean.ListBean bean = listBeen.get(position);
        holder.title.setText(bean.getBrandChinaName() + bean.getName());
        int count = bean.getReviewCount();
        if (count == 0) {
            holder.commit.setText("暂无评论");
        } else {
            holder.commit.setText("评论：" + count);
        }
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
        return convertView;
    }
}
