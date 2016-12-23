package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.BeautifulBean;

import java.util.List;

import static com.zhanghao.skinexpert.R.id.tv_username;

/**
 * Created by Administrator on 2016/12/23.
 */

public class BeautifulAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<BeautifulBean.DataBean.ListBean> datalist;

    public BeautifulAdapter(Context context, List<BeautifulBean.DataBean.ListBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.beautiful_listview, null);
            mHolder = new ViewHolder();
            mHolder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            mHolder.iv_content = (ImageView) convertView.findViewById(R.id.iv_content);
            mHolder.iv_like = (ImageView) convertView.findViewById(R.id.iv_like);
            mHolder.tv_username = (TextView) convertView.findViewById(tv_username);
            mHolder.tv_userskin = (TextView) convertView.findViewById(R.id.tv_userskin);
            mHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            mHolder.tv_like_count = (TextView) convertView.findViewById(R.id.tv_like_count);
            mHolder.ll_tags = (LinearLayout) convertView.findViewById(R.id.ll_tags);
            mHolder.ll_right = (LinearLayout) convertView.findViewById(R.id.ll_right);
            mHolder.ll_comment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datalist.get(position).getUser().getHeadface()).into(mHolder.iv_head);
        if (datalist.get(position).getImage().size()>0) {
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,330);
            mHolder.iv_content.setLayoutParams(layoutParams);
            Picasso.with(context).load(datalist.get(position).getImage().get(0)).into(mHolder.iv_content);
        }
        mHolder.tv_username.setText(datalist.get(position).getUser().getNick());
        mHolder.tv_userskin.setText(datalist.get(position).getUser().getSkinText());
        mHolder.tv_like_count.setText(datalist.get(position).getLikedCount() + "");
        mHolder.tv_content.setText(datalist.get(position).getContent());
        List<BeautifulBean.DataBean.ListBean.TagsBean> tags = datalist.get(position).getTags();
        mHolder.ll_tags.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            View view = inflater.inflate(R.layout.community_content_tags, null);
            Button btn_tags = (Button) view.findViewById(R.id.btn_tags);
            btn_tags.setText(tags.get(i).getName());
            mHolder.ll_tags.addView(view);
        }
        List<BeautifulBean.DataBean.ListBean.CommentsBean> comments = datalist.get(position).getComments();
        mHolder.ll_comment.removeAllViews();
        for (int i = 0; i < comments.size(); i++) {
            View view = inflater.inflate(R.layout.community_content_comment, null);
            TextView tv_comment_user = (TextView) view.findViewById(R.id.tv_comment_user);
            TextView tv_jiange = (TextView) view.findViewById(R.id.tv_jiange);
            TextView tv_user_content = (TextView) view.findViewById(R.id.tv_user_content);
            tv_comment_user.setText(comments.get(i).getNick());
            tv_user_content.setText(comments.get(i).getContent());
            mHolder.ll_comment.addView(view);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView iv_head;
        TextView tv_username;
        TextView tv_userskin;
        ImageView iv_content;
        TextView tv_content;
        LinearLayout ll_tags;
        ImageView iv_like;
        TextView tv_like_count;
        LinearLayout ll_right;
        LinearLayout ll_comment;
    }
}
