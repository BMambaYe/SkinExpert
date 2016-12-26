package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
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
import com.zhanghao.skinexpert.beans.UserInfoContentBean;
import com.zhanghao.skinexpert.beans.UserInfoHeadBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {
    private ListView userinfo_listview;
    private ImageView userinfo_iv_back;
    private TextView userinfo_tv_username;
    private int uid = 0;
    private String userskin="";
    private List<UserInfoContentBean.DataBean.ListBean> userContentList = new ArrayList<>();
    private MyUserContentAdapter adapter;
    private LayoutInflater inflater;
    private UserInfoHeadBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        inflater = LayoutInflater.from(this);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        userskin = intent.getStringExtra("userskin");
        getUserContentData();
        initView();
        getUserHeadData();
    }

    private void getUserHeadData() {
        NetWorkRequest.getUserInfoHead(this, uid, new NetWorkRequest.RequestCallBack() {
            private UserInfoHeadBean userInfoHeadBean;

            @Override
            public void success(Object result) {
                userInfoHeadBean = (UserInfoHeadBean) result;
                dataBean = userInfoHeadBean.getData();
                initHeader();
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initHeader() {
        View view = inflater.inflate(R.layout.userinfo_header, null);
        ImageView header_iv_head = (ImageView) view.findViewById(R.id.header_iv_head);
        TextView header_tv_skin = (TextView) view.findViewById(R.id.header_tv_skin);
        TextView header_ok_count = (TextView) view.findViewById(R.id.header_ok_count);
        if (!dataBean.getTitle().equals("")) {
            TextView header_tv_title = (TextView) view.findViewById(R.id.header_tv_title);
            header_tv_title.setText(dataBean.getTitle());
        }
        if(!dataBean.getUserInfo().equals("")){
            TextView header_tv_userinfo= (TextView) view.findViewById(R.id.header_tv_userinfo);
            header_tv_userinfo.setText(dataBean.getUserInfo());
        }
        Picasso.with(this).load(dataBean.getHeadface()).into(header_iv_head);
        header_tv_skin.setText(userskin);
        header_ok_count.setText(dataBean.getLikeSum() + "");
        userinfo_tv_username.setText(dataBean.getNick());
        userinfo_listview.addHeaderView(view);
    }

    private void getUserContentData() {
        NetWorkRequest.getUserInfo(this, uid, 0, "", new NetWorkRequest.RequestCallBack() {

            private UserInfoContentBean userInfoContentBean;

            @Override
            public void success(Object result) {
                userInfoContentBean = (UserInfoContentBean) result;
                userContentList = userInfoContentBean.getData().getList();
                adapter = new MyUserContentAdapter();
                userinfo_listview.setAdapter(adapter);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void initView() {
        userinfo_listview = (ListView) findViewById(R.id.userinfo_listview);
        userinfo_iv_back = (ImageView) findViewById(R.id.userinfo_iv_back);
        userinfo_tv_username = (TextView) findViewById(R.id.userinfo_tv_username);
        userinfo_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MyUserContentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userContentList.size();
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
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.userinfo_listview, null);
                mHolder.userinfo_iv_head = (ImageView) convertView.findViewById(R.id.userinfo_iv_head);
                mHolder.community_more_iv = (ImageView) convertView.findViewById(R.id.community_more_iv);
                mHolder.community_comment_iv = (ImageView) convertView.findViewById(R.id.community_comment_iv);
                mHolder.userinfo_iv_content = (ImageView) convertView.findViewById(R.id.userinfo_iv_content);
                mHolder.userinfo_tv_username = (TextView) convertView.findViewById(R.id.userinfo_tv_username);
                mHolder.userinfo_tv_time = (TextView) convertView.findViewById(R.id.userinfo_tv_time);
                mHolder.userinfo_tv_like_count = (TextView) convertView.findViewById(R.id.userinfo_tv_like_count);
                mHolder.userinfo_tv_comment_count = (TextView) convertView.findViewById(R.id.userinfo_tv_comment_count);
                mHolder.userinfo_tv_content = (TextView) convertView.findViewById(R.id.userinfo_tv_content);
                mHolder.ll_userinfo_tags = (LinearLayout) convertView.findViewById(R.id.ll_userinfo_tags);
                mHolder.ll_userinfo_product = (LinearLayout) convertView.findViewById(R.id.ll_userinfo_product);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            Picasso.with(getApplicationContext()).load(userContentList.get(position).getUser().getHeadface()).into(mHolder.userinfo_iv_head);
            if (userContentList.get(position).getImage().size() > 0) {
                Picasso.with(getApplicationContext()).load(userContentList.get(position).getImage().get(0)).into(mHolder.userinfo_iv_content);
            }
            mHolder.userinfo_tv_username.setText(userContentList.get(position).getUser().getNick());
            mHolder.userinfo_tv_time.setText(userContentList.get(position).getCreateTime());
            mHolder.userinfo_tv_comment_count.setText(userContentList.get(position).getCommentCount() + "");
            mHolder.userinfo_tv_like_count.setText(userContentList.get(position).getLikedCount() + "");
            mHolder.userinfo_tv_content.setText(userContentList.get(position).getContent());
            List<UserInfoContentBean.DataBean.ListBean.TagsBean> tags = userContentList.get(position).getTags();
            mHolder.ll_userinfo_tags.removeAllViews();
            for (int i = 0; i < tags.size(); i++) {
                View view = inflater.inflate(R.layout.community_content_tags, null);
                Button btn_tags = (Button) view.findViewById(R.id.btn_tags);
                btn_tags.setText(tags.get(i).getName());
                mHolder.ll_userinfo_tags.addView(view);
            }
            List<UserInfoContentBean.DataBean.ListBean.ProductBean> products = userContentList.get(position).getTags_product();
            mHolder.ll_userinfo_product.removeAllViews();
            if (products.size() > 0) {
                for (int i = 0; i < products.size(); i++) {
                    View view = inflater.inflate(R.layout.community_product, null);
                    TextView product_tv = (TextView) view.findViewById(R.id.product_tv);
                    ImageView product_iv = (ImageView) view.findViewById(R.id.product_iv);
                    Picasso.with(getApplicationContext()).load(products.get(i).getImage()).into(product_iv);
                    product_tv.setText(products.get(i).getName());
                    mHolder.ll_userinfo_product.addView(view);
                    mHolder.ll_userinfo_product.setBackgroundColor(0xfff0f0f0);
                }
            }

            return convertView;
        }

        class ViewHolder {
            ImageView userinfo_iv_head;
            TextView userinfo_tv_username;
            TextView userinfo_tv_time;
            LinearLayout ll_userinfo_tags;
            TextView userinfo_tv_like_count;
            TextView userinfo_tv_comment_count;
            LinearLayout ll_userinfo_product;
            ImageView community_more_iv;
            ImageView community_comment_iv;
            TextView userinfo_tv_content;
            ImageView userinfo_iv_content;
        }
    }
}
