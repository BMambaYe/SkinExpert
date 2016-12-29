package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.VoteListViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class VoteAdapter extends BaseAdapter {
    private Context context;
    private List<VoteListViewBean.DataBean.ListBean> voteList;


    public VoteAdapter(Context context, List<VoteListViewBean.DataBean.ListBean> voteList) {
        this.context = context;
        this.voteList = voteList;
    }

    @Override
    public int getCount() {
        return voteList.size();
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
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.beautiful2_listview, null);
            mHolder.b2lv_iv_head = (ImageView) convertView.findViewById(R.id.b2lv_iv_head);
            mHolder.b2lv_iv_more = (ImageView) convertView.findViewById(R.id.b2lv_iv_more);
            mHolder.b2lv_iv_comment = (ImageView) convertView.findViewById(R.id.b2lv_iv_comment);
            mHolder.b2lv_tv_username = (TextView) convertView.findViewById(R.id.b2lv_tv_username);
            mHolder.b2lv_tv_usertime = (TextView) convertView.findViewById(R.id.b2lv_tv_usertime);
            mHolder.b2lv_tv_content = (TextView) convertView.findViewById(R.id.b2lv_tv_content);
            mHolder.b2lv_tv_votecount = (TextView) convertView.findViewById(R.id.b2lv_tv_votecount);
            mHolder.b2lv_tv_commentcount = (TextView) convertView.findViewById(R.id.b2lv_tv_commentcount);
            mHolder.b2lv_re = (RelativeLayout) convertView.findViewById(R.id.b2lv_re);
            mHolder.b2lv_ll_progressbar = (LinearLayout) convertView.findViewById(R.id.b2lv_ll_progressbar);
            mHolder.b2lv_ll_viewpager= (LinearLayout) convertView.findViewById(R.id.b2lv_ll_viewpager);
            convertView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(voteList.get(position).getUser().getHeadface()).into(mHolder.b2lv_iv_head);
        mHolder.b2lv_tv_username.setText(voteList.get(position).getUser().getNick());
        mHolder.b2lv_tv_usertime.setText(voteList.get(position).getCreateTime());
        mHolder.b2lv_tv_content.setText(voteList.get(position).getContent());
        mHolder.b2lv_tv_votecount.setText(voteList.get(position).getVote().getVoteCount() + "");
        mHolder.b2lv_tv_commentcount.setText(voteList.get(position).getCommentCount() + "");
        List<String> images = new ArrayList<>();
        images = voteList.get(position).getImage();
        if(images.size()>0) {
            ViewPager viewpager = new ViewPager(context);
            MyPagerAdapter adapter = new MyPagerAdapter(images);
            viewpager.setAdapter(adapter);
            mHolder.b2lv_ll_viewpager.addView(viewpager);
        }
        return convertView;
    }

    class ViewHolder {
        RelativeLayout b2lv_re;
        ImageView b2lv_iv_head;
        TextView b2lv_tv_username;
        TextView b2lv_tv_usertime;
        TextView b2lv_tv_content;
        LinearLayout b2lv_ll_progressbar;
        TextView b2lv_tv_votecount;
        TextView b2lv_tv_commentcount;
        ImageView b2lv_iv_more;
        ImageView b2lv_iv_comment;
        LinearLayout b2lv_ll_viewpager;
    }
    class MyPagerAdapter extends PagerAdapter{
        private  List<String> images;

        public MyPagerAdapter(List<String> images){
            this.images=images;
        }
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=new ImageView(context);
            Picasso.with(context).load(images.get(position)).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof View) {
                container.removeView((View) object);
            }
        }
    }
}
