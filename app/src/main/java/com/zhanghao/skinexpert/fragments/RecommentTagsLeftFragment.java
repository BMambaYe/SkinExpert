package com.zhanghao.skinexpert.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public class RecommentTagsLeftFragment extends Fragment {
    private OnSuccessListener onSuccessListener;
    private String[] tags={"热门","晒物","彩妆","护肤","参与","照片","其他"};
    private ListView recommendleft_lv;
    private MyAdapter adapter;
    private List<RecommendTagsDataBean.DataBean> tagsList=new ArrayList<>();
    private int i=0;
    public RecommentTagsLeftFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onSuccessListener= (OnSuccessListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recommenttags_leftfragment,container,false);
        recommendleft_lv= (ListView) view.findViewById(R.id.recommendleft_lv);
        adapter=new MyAdapter();
        recommendleft_lv.setAdapter(adapter);
        recommendleft_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i=position;
                adapter.notifyDataSetChanged();
            }
        });
        getHotTagData();
        return view;
    }

    private void getHotTagData() {
        NetWorkRequest.getRecommendTagsDataBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private RecommendTagsDataBean recommendTagsDataBean;
            @Override
            public void success(Object result) {
                recommendTagsDataBean= (RecommendTagsDataBean) result;
                tagsList=recommendTagsDataBean.getData();
                onSuccessListener.Onsuccess(tagsList);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    class MyAdapter extends BaseAdapter{

        public MyAdapter(){

        }

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
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.recommenttags_button,null);
                mHolder.btn_left= (Button) convertView.findViewById(R.id.btn_left);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            mHolder.btn_left.setText(tags[position]);
            if(position==i){
                mHolder.btn_left.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
        class ViewHolder{
            Button btn_left;
        }
    }
    public interface OnSuccessListener{
       public void Onsuccess(List<RecommendTagsDataBean.DataBean> tagsList);
    }
}
