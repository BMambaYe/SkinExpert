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
import com.zhanghao.skinexpert.beans.ProductLibraryBean;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class ProductListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<ProductLibraryBean.DataBean.ListBean> listBeen;

    public ProductListViewAdapter(Context context, List<ProductLibraryBean.DataBean.ListBean> listBeen) {
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
        TextView title, score, commit;
        RatingBar ratingBar;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.product_library_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.tv_product_library_title);
            holder.score = (TextView) convertView.findViewById(R.id.tv_product_library_score);
            holder.commit = (TextView) convertView.findViewById(R.id.tv_product_library_commit);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.rb_product_library);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_product_library);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductLibraryBean.DataBean.ListBean bean = listBeen.get(position);
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
        holder.ratingBar.setRating(Float.parseFloat(bean.getReviewScore()) / 2.0f);
        if (!"".equals(bean.getPic())) {
            Picasso.with(context).load(bean.getPic()).error(R.drawable.error_pic).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.error_pic);
        }
        return convertView;
    }
}
