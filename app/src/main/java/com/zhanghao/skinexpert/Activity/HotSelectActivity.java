package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class HotSelectActivity extends AppCompatActivity {
    private ListView hotselect_lv;
    private MyHotSelectAdapter adapter;
    private List<CommunityListViewBean.DataBean.ListBean> allList = new ArrayList<>();
    private LayoutInflater inflater;
    private ImageView hotselect_iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater=LayoutInflater.from(this);
        setContentView(R.layout.activity_hot_select);
        hotselect_lv= (ListView) findViewById(R.id.hotselect_lv);
        listviewData();
        hotselect_iv_back= (ImageView) findViewById(R.id.hotselect_iv_back);
        hotselect_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void listviewData() {
        NetWorkRequest.getCommunityListViewBean(this, new NetWorkRequest.RequestCallBack() {
            private CommunityListViewBean communityListViewBean;

            @Override
            public void success(Object result) {
                //对一些bean类进行初始化
                communityListViewBean = (CommunityListViewBean) result;
                allList = communityListViewBean.getData().getList();
                adapter=new MyHotSelectAdapter();
                hotselect_lv.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }
    class MyHotSelectAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return allList.size();
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
            ViewHolder mHolder=null;
            if(convertView==null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.hotselect_listview, null);
                mHolder = new ViewHolder();
                mHolder.iv_head= (ImageView) convertView.findViewById(R.id.iv_head);
                mHolder.iv_content= (ImageView) convertView.findViewById(R.id.iv_content);
                mHolder.iv_like= (ImageView) convertView.findViewById(R.id.iv_like);
                mHolder.iv_comment= (ImageView) convertView.findViewById(R.id.iv_comment);
                mHolder.tv_username= (TextView) convertView.findViewById(R.id.tv_username);
                mHolder.tv_userskin= (TextView) convertView.findViewById(R.id.tv_userskin);
                mHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
                mHolder.tv_like_count= (TextView) convertView.findViewById(R.id.tv_like_count);
                mHolder.tv_comment_count= (TextView) convertView.findViewById(R.id.tv_comment_count);
                mHolder.tv_all= (TextView) convertView.findViewById(R.id.tv_all);
                mHolder.tv_usercomment_count= (TextView) convertView.findViewById(R.id.tv_usercomment_count);
                mHolder.tv_all1= (TextView) convertView.findViewById(R.id.tv_all1);
                mHolder.ll_tags= (LinearLayout) convertView.findViewById(R.id.ll_tags);
                mHolder.ll_right= (LinearLayout) convertView.findViewById(R.id.ll_right);
                mHolder.ll_comment= (LinearLayout) convertView.findViewById(R.id.ll_comment);
                mHolder.ll_product= (LinearLayout) convertView.findViewById(R.id.ll_product);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            Picasso.with(getApplicationContext()).load(allList.get(position).getUser().getHeadface()).into(mHolder.iv_head);
            mHolder.tv_username.setText(allList.get(position).getUser().getNick());
            mHolder.tv_userskin.setText(allList.get(position).getCreateTime()+"");
            Picasso.with(getApplicationContext()).load(allList.get(position).getImage().get(0)).into(mHolder.iv_content);
            mHolder.tv_content.setText(allList.get(position).getContent().replaceAll(" ",""));
            mHolder.tv_like_count.setText(allList.get(position).getLikedCount()+"");
            mHolder.tv_comment_count.setText(allList.get(position).getCommentCount()+"");
            mHolder.tv_usercomment_count.setText(allList.get(position).getCommentCount()+"");
            List<CommunityListViewBean.DataBean.ListBean.ProductBean> products = allList.get(position).getTags_product();
            mHolder.ll_product.removeAllViews();
            if (products.size() > 0) {
                View view=inflater.inflate(R.layout.community_product,null);
                TextView product_tv= (TextView) view.findViewById(R.id.product_tv);
                ImageView product_iv= (ImageView) view.findViewById(R.id.product_iv);
                Picasso.with(getApplicationContext()).load(products.get(0).getImage()).into(product_iv);
                product_tv.setText(products.get(0).getName());
                mHolder.ll_product.addView(view);
                mHolder.ll_product.setBackgroundColor(0xfff0f0f0);
            }
            List<CommunityListViewBean.DataBean.ListBean.TagsBean> tags = allList.get(position).getTags();
            mHolder.ll_tags.removeAllViews();
            for(int i=0;i<tags.size();i++){
                View view=inflater.inflate(R.layout.community_content_tags,null);
                Button btn_tags= (Button) view.findViewById(R.id.btn_tags);
                btn_tags.setText(tags.get(i).getName());
                mHolder.ll_tags.addView(view);
            }
            List<CommunityListViewBean.DataBean.ListBean.CommentsBean> comments = allList.get(position).getComments();
            mHolder.ll_comment.removeAllViews();
            for(int i=0;i<comments.size();i++){
                View view=inflater.inflate(R.layout.community_content_comment,null);
                TextView tv_comment_user= (TextView) view.findViewById(R.id.tv_comment_user);
                TextView tv_jiange= (TextView) view.findViewById(R.id.tv_jiange);
                TextView tv_user_content= (TextView) view.findViewById(R.id.tv_user_content);
                tv_comment_user.setText(comments.get(i).getNick());
                tv_user_content.setText(comments.get(i).getContent());
                mHolder.ll_comment.addView(view);
            }
            return convertView;
        }
        class ViewHolder{
            ImageView iv_head;
            TextView tv_username;
            TextView tv_userskin;
            ImageView iv_content;
            TextView tv_content;
            LinearLayout ll_tags;
            ImageView iv_like;
            TextView tv_like_count;
            ImageView iv_comment;
            TextView tv_comment_count;
            LinearLayout ll_right;
            TextView tv_all;
            TextView tv_usercomment_count;
            TextView tv_all1;
            LinearLayout ll_comment;
            LinearLayout ll_product;
        }
    }
}
