package com.zhanghao.skinexpert.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public class RecommentTagsRightFragment extends Fragment {
    private ListView recommentright_lv;
    private MyAdapter adapter;
    private List<RecommendTagsDataBean.DataBean> tagsList = new ArrayList<>();

    public RecommentTagsRightFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommenttags_rightfragment, container, false);
        recommentright_lv = (ListView) view.findViewById(R.id.recommentright_lv);
        Bundle bundle = getArguments();
        tagsList = (List<RecommendTagsDataBean.DataBean>) bundle.getSerializable("tagsList");
        //Log.i("1609", "tagsList:" + tagsList.size()); 有数据20条
        adapter = new MyAdapter();
        recommentright_lv.setAdapter(adapter);
        return view;
    }

    class MyAdapter extends BaseAdapter {


        public MyAdapter() {

        }

        @Override
        public int getCount() {
            return tagsList.size();
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
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.recommenttags_rightlv, null);
                mHolder.recommenttags_lv_iv = (ImageView) convertView.findViewById(R.id.recommenttags_lv_iv);
                mHolder.recommenttags_lv_tv1 = (TextView) convertView.findViewById(R.id.recommenttags_lv_tv1);
                mHolder.recommenttags_lv_tv2 = (TextView) convertView.findViewById(R.id.recommenttags_lv_tv2);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            Picasso.with(getActivity()).load(tagsList.get(position).getImage()).into(mHolder.recommenttags_lv_iv);
            mHolder.recommenttags_lv_tv1.setText(tagsList.get(position).getCategoryName()+"");
            mHolder.recommenttags_lv_tv2.setText(tagsList.get(position).getTotalCount()+"");
            return convertView;
        }

        class ViewHolder {
            ImageView recommenttags_lv_iv;
            TextView recommenttags_lv_tv1;
            TextView recommenttags_lv_tv2;
        }
    }
}
