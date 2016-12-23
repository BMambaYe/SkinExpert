package com.zhanghao.skinexpert.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.Activity.ProductDetailActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.RVAdapter;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

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
    private List<BenifitsBean.DataBean.ListBean> dataList = new ArrayList<>();
    int totle = 0;
    private BenifitsBean benifitsBean;
    private boolean canDownLoad = true;
    private String server_pic_url = "http://www.caimiapp.com/fllbas/images/server.png";
    private ImageView img_server;

    public BenefitsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_benefits, container, false);
            rv_show = (RecyclerView) view.findViewById(R.id.rv_show_benefits);
            img_server = ((ImageView) view.findViewById(R.id.img_benefits_server));
            Picasso.with(getActivity()).load(server_pic_url).into(img_server);
            gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            loadData();
            rv_show.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (isSlideToBottom(recyclerView) && canDownLoad) {
                        totle += 20;
                        NetWorkRequest.getBenefitsBean(getActivity(), totle, new NetWorkRequest.RequestCallBack() {
                            @Override
                            public void success(Object result) {
                                BenifitsBean benifitsBean = (BenifitsBean) result;
                                dataList.addAll(benifitsBean.getData().getList());
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void fail(String result) {
                                canDownLoad = false;
                            }
                        });
                    }
                }
            });
        }
        return view;
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null)
            return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    private void loadData() {
        NetWorkRequest.getBenefitsBean(getActivity(), 0, new NetWorkRequest.RequestCallBack() {

            @Override
            public void success(Object result) {
                Log.i("110", "success: " + result);
                benifitsBean = ((BenifitsBean) result);
                dataList = benifitsBean.getData().getList();
                adapter = new RVAdapter(getActivity(), dataList);
                adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(BenifitsBean.DataBean.ListBean bean) {
                        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                        intent.putExtra("id", bean.getPid());
                        intent.putExtra("buy", bean.getBeginDateText());
                        startActivity(intent);
                    }
                });
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
