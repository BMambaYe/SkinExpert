package com.zhanghao.skinexpert.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.RVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment {

    private View view;
    private String benefitsURL = "http://www.caimiapp.com/fllbas/?token=&skin=----&source=app";
    private RecyclerView rv_show;
  private GridLayoutManager gridLayoutManager;
    private RVAdapter adapter;
    private List<BenifitsBean.DataBean.ListBean> dataList=new ArrayList<>();

    public BenefitsFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_benefits, container, false);
            rv_show= (RecyclerView) view.findViewById(R.id.rv_show_benefits);
            gridLayoutManager=new GridLayoutManager(getActivity(),2);

            loadData();



        }
        return view;
    }

    private void loadData() {
        NetWorkRequest.getBenefitsBean(getActivity(), 0, new NetWorkRequest.RequestCallBack() {
            private BenifitsBean benifitsBean;

            @Override
            public void success(Object result) {
                Log.i("110", "success: " +result);
                benifitsBean = ((BenifitsBean) result);
                dataList=benifitsBean.getData().getList();
                adapter=new RVAdapter(getActivity(),dataList);
                rv_show.setAdapter(adapter);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return adapter.isHeaderView(position) ? gridLayoutManager.getSpanCount() : 1;
                    }
                });
                rv_show.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void fail(String result) {

            }
        });
    }

}
