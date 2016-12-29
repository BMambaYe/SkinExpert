package com.zhanghao.skinexpert.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RecommendOtherTagsBean;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.beans.RecommendTagsNameBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.RecommentTagsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public class RecommentTagsLeftFragment extends Fragment {
    private List<RecommendTagsNameBean.DataBean> nameList=new ArrayList<>();
    private List<String> tags=new ArrayList<>();
    private ListView recommendleft_lv;
    private MyAdapter adapter;
    private List<RecommendTagsDataBean.DataBean> tagsList=new ArrayList<>();
    private List<String> idList=new ArrayList<>();
    private int i=0;


    public RecommentTagsLeftFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recommenttags_leftfragment,container,false);
        recommendleft_lv= (ListView) view.findViewById(R.id.recommendleft_lv);
        getTagsName();
        adapter=new MyAdapter();
        recommendleft_lv.setAdapter(adapter);
        getHotTagData();
        recommendleft_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i=position;
                if(position==0){
                    getHotTagData();
                    Toast.makeText(getActivity(),position+":"+tags.get(position),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),position+":"+tags.get(position),Toast.LENGTH_SHORT).show();
                    int gid=nameList.get(position-1).getId();
                    NetWorkRequest.getRecommendOtherTagsDataBean(getActivity(),gid, new NetWorkRequest.RequestCallBack() {
                        private RecommendOtherTagsBean recommendOtherTagsBean;
                        @Override
                        public void success(Object result) {
                            recommendOtherTagsBean= (RecommendOtherTagsBean) result;
                            idList=recommendOtherTagsBean.getData().getList();
                            Log.i("1609", "idList: "+idList.size());
                        }

                        @Override
                        public void fail(String result) {

                        }
                    });
                }
                recommendleft_lv.setAdapter(adapter);


            }
        });

        return view;
    }


    private void getTagsName() {
        NetWorkRequest.getRecommendTagsNameBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private RecommendTagsNameBean recommendTagsNameBean;
            @Override
            public void success(Object result) {
                recommendTagsNameBean= (RecommendTagsNameBean) result;
                nameList=recommendTagsNameBean.getData();
                tags.add("热门");
                for(int i=0;i<nameList.size();i++){
                    tags.add(nameList.get(i).getName());
                }
            }
            @Override
            public void fail(String result) {

            }
        });
    }

    private void getHotTagData() {
        NetWorkRequest.getRecommendTagsDataBean(getActivity(), new NetWorkRequest.RequestCallBack() {
            private RecommendTagsDataBean recommendTagsDataBean;
            @Override
            public void success(Object result) {
                recommendTagsDataBean= (RecommendTagsDataBean) result;
                tagsList=recommendTagsDataBean.getData();
                EventBus.getDefault().post(new RecommentTagsEvent(tagsList));
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
            return tags.size();
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
                mHolder.btn_left= (TextView) convertView.findViewById(R.id.btn_left);
                convertView.setTag(mHolder);
            }else{
                mHolder= (ViewHolder) convertView.getTag();
            }
            mHolder.btn_left.setText(tags.get(position));
            if(position==i){
                mHolder.btn_left.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
        class ViewHolder{
            TextView btn_left;
        }
    }
}
