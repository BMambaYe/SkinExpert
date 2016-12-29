package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommentArticleBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghao on 2016/12/29.
 */

public class CommentArticleAdapter extends BaseAdapter {

    private List<CommentArticleBean.DataBean.ListBean> list;
    private Context context;
    private LayoutInflater inflater;
    private SimpleDateFormat sdf;

    public CommentArticleAdapter(List<CommentArticleBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    class ViewHolder {
        TextView commentDate, commentName, commentContent;
        ImageView headPic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment_article_item, parent, false);
            holder.commentName = (TextView) convertView.findViewById(R.id.tv_title_comment_article_item);
            holder.commentContent = (TextView) convertView.findViewById(R.id.tv_content_comment_article_item);
            holder.commentDate = (TextView) convertView.findViewById(R.id.tv_date_comment_article_item);
            holder.headPic = (ImageView) convertView.findViewById(R.id.iv_head_comment_article_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.commentName.setText(list.get(position).getUser().getNick());
        try {
            long commentTime = sdf.parse(list.get(position).getCreateTime()).getTime();
            long currentTime = new Date().getTime();
            int time = (int) ((currentTime - commentTime) / 86400000);
            if (time>=1){
                holder.commentDate.setText(time+"天前");
            }else{
                holder.commentDate.setText(sdf.format((int) ((currentTime - commentTime) % 86400000)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if ("".equals(list.get(position).getOnick())||list.get(position).getOnick()==null){
            holder.commentContent.setText(list.get(position).getContent());
        }else{
            holder.commentContent.setText("@"+list.get(position).getOnick()+" "+list.get(position).getContent());
            SpannableStringBuilder builder = new SpannableStringBuilder(holder.commentContent.getText().toString());
            ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#FF6D72"));
            builder.setSpan(redSpan, 0, (list.get(position).getOnick()+"").length()+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            holder.commentContent.setText(builder);
        }
        Picasso.with(context).load(list.get(position).getUser().getHeadface()).into(holder.headPic);
        return convertView;
    }
}
