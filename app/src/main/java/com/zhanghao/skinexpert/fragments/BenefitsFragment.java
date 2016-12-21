package com.zhanghao.skinexpert.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.utils.RVAdapter;

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
    private List<BenifitsBean.DataBean.ListBean> dataList;

    public BenefitsFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_benefits, container, false);
            rv_show= (RecyclerView) view.findViewById(R.id.rv_show_benefits);
            gridLayoutManager=new GridLayoutManager(getActivity(),2);
            rv_show.setLayoutManager(gridLayoutManager);
            adapter=new RVAdapter(getActivity(),dataList);
            rv_show.setAdapter(adapter);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return adapter.isHeaderView(position) ? gridLayoutManager.getSpanCount() : 1;
                }
            });

        }
        return view;
    }

}
