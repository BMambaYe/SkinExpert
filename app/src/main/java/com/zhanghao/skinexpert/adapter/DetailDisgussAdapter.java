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
import com.zhanghao.skinexpert.beans.DetailCommentBean;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/22.
 */

public class DetailDisgussAdapter extends BaseAdapter {
    private List<DetailCommentBean.DataBean.ListBean> listbeans;
    private Context context;
    private LayoutInflater layoutInflater;

    public DetailDisgussAdapter(List<DetailCommentBean.DataBean.ListBean> listbeans,Context context) {
        this.listbeans = listbeans;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listbeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listbeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder mHolder =null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_detail_disguss,null);
            mHolder=new ViewHolder();
            mHolder.img_pic= (ImageView) convertView.findViewById(R.id.img_detail_item_header);
            mHolder.img_zan= (ImageView) convertView.findViewById(R.id.img_item_zan);
            mHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_detail_item_name);
            mHolder.tv_skintext= (TextView) convertView.findViewById(R.id.tv_item_skintext);
            mHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_detail_item_comment);
            mHolder.tv_zan_num= (TextView) convertView.findViewById(R.id.tv_detail_item_num_of_zan);
            mHolder.rb= (RatingBar) convertView.findViewById(R.id.rb_detail_item_score);
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(listbeans.get(position).getUser().getHeadface()).into(mHolder.img_pic);
        mHolder.tv_name.setText(listbeans.get(position).getUser().getNick());
        mHolder.tv_skintext.setText(listbeans.get(position).getUser().getSkin());
        mHolder.tv_content.setText(listbeans.get(position).getCommentContent());
        mHolder.tv_zan_num.setText(listbeans.get(position).getUseful()+"");
        mHolder.rb.setRating(listbeans.get(position).getScore()/2);
        return convertView;
    }


    class ViewHolder{
        ImageView img_pic ,img_zan;
        TextView tv_name,tv_skintext,tv_content,tv_zan_num;
        RatingBar rb;

    }
}
