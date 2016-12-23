package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.DetailAllDisgussBean;
import com.zhanghao.skinexpert.view.PercentLinearLayout;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/23.
 */

public class DetailAllDisgussAdapter extends BaseAdapter {
    private Context context;
    private List<DetailAllDisgussBean.DataBean.ListBean> datalist;
    private LayoutInflater inflater;

    public DetailAllDisgussAdapter(Context context, List<DetailAllDisgussBean.DataBean.ListBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder =null;
        if (convertView==null){
            mHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_detail_all_disguss,null);
            mHolder.img_head= (ImageView) convertView.findViewById(R.id.img_detail_alldisguss_item_header);
            mHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_name);
            mHolder.tv_creattime= (TextView) convertView.findViewById(R.id.tv_item_alldisguss_creattime);
            mHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_comment);
            mHolder.tv_likecount= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_likecount);
            mHolder.ll_pinlun_container= (LinearLayout) convertView.findViewById(R.id.ll_item_all_disguss_punluncontainer);
            convertView.setTag(mHolder);
        }else {
            mHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datalist.get(position).getUser().getHeadface()).into(mHolder.img_head);
        mHolder.tv_name.setText(datalist.get(position).getUser().getNick());
        //// TODO: 2016/12/23  设置时间判断
        mHolder.tv_creattime.setText(datalist.get(position).getCreateTime());
        mHolder.tv_content.setText(datalist.get(position).getContent());
        mHolder.tv_likecount.setText(datalist.get(position).getLikedCount()+"");
        List<DetailAllDisgussBean.DataBean.ListBean.CommentBean> comments = datalist.get(position).getComments();
        int count =comments.size()>=3 ? 3:comments.size();
        mHolder.ll_pinlun_container.removeAllViews();
        for (int i = 0; i <count ; i++) {
            DetailAllDisgussBean.DataBean.ListBean.CommentBean commentBean = comments.get(i);
            TextView tv=new TextView(context);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv.setText(commentBean.getNick()+" :" +commentBean.getContent());
            LinearLayout.LayoutParams params=new PercentLinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            mHolder.ll_pinlun_container.addView(tv);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView img_head;
        TextView tv_name,tv_creattime,tv_content,tv_likecount;
        LinearLayout ll_pinlun_container;
    }
}
