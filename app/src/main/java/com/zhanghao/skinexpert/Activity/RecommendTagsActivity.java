package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.List;

public class RecommendTagsActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView recommendtags_iv_back;
    private ListView recommendtags_left_listview;
    private ListView recommendtags_right_listview;
    private LeftAdapter leftAdapter;
    private List<RecommendTagsDataBean.DataBean> reMenList;
    private String[] tags={"热门","晒物","彩妆","护肤","参与","照片","其他"};
    private int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_tags);
        initView();
        getRecommendTagsLeft();
        getRecommendTagsRight();

    }



    private void getRecommendTagsLeft() {
        leftAdapter=new LeftAdapter();
        recommendtags_left_listview.setAdapter(leftAdapter);
        recommendtags_left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i=position;
                recommendtags_left_listview.setAdapter(leftAdapter);
            }
        });
    }


    private void getRecommendTagsRight() {
        NetWorkRequest.getRecommendTagsReMenDataBean(this, new NetWorkRequest.RequestCallBack() {
            private RecommendTagsDataBean recommendTagsDataBean;
            @Override
            public void success(Object result) {
                recommendTagsDataBean= (RecommendTagsDataBean) result;
                reMenList=recommendTagsDataBean.getData();
                RightAdapter rightAdapter=new RightAdapter();
                recommendtags_right_listview.setAdapter(rightAdapter);
                recommendtags_right_listview.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void fail(String result) {

            }
        });

    }
    private void initView() {
        recommendtags_iv_back = (ImageView) findViewById(R.id.recommendtags_iv_back);
        recommendtags_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recommendtags_left_listview= (ListView) findViewById(R.id.recommendtags_left_listview);
        recommendtags_right_listview= (ListView) findViewById(R.id.recommendtags_right_listview);
    }


    public void search(View view) {
        Intent intent = new Intent(this, SearchTagActivity.class);
        startActivity(intent);
    }

    class LeftAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return tags.length;
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
            if(convertView==null){
                mHolder=new ViewHolder();
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.recommendtags_leftlv,null);
                mHolder.recommendtags_tv_left= (TextView) convertView.findViewById(R.id.recommendtags_tv_left);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            mHolder.recommendtags_tv_left.setText(tags[position]);
            if(i==position){
                mHolder.recommendtags_tv_left.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
        class ViewHolder{
            TextView recommendtags_tv_left;
        }
    }
    class RightAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return reMenList.size();
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
            if(convertView==null){
                mHolder=new ViewHolder();
                convertView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.recommendtags_rightlv,null);
                mHolder.remen_lv_iv= (ImageView) convertView.findViewById(R.id.remen_lv_iv);
                mHolder.remen_lv_tv1= (TextView) convertView.findViewById(R.id.remen_lv_tv1);
                mHolder.remen_lv_tv2= (TextView) convertView.findViewById(R.id.remen_lv_tv2);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            Picasso.with(getApplicationContext()).load(reMenList.get(position).getImage()).into(mHolder.remen_lv_iv);
            mHolder.remen_lv_tv1.setText(reMenList.get(position).getCategoryName());
            mHolder.remen_lv_tv2.setText(reMenList.get(position).getTotalCount()+"");
            return convertView;
        }
        class ViewHolder{
            ImageView remen_lv_iv;
            TextView remen_lv_tv1;
            TextView remen_lv_tv2;
        }
    }
}