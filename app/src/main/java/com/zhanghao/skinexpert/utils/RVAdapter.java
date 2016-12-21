package com.zhanghao.skinexpert.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.BenifitsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/21.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    private int mHeaderCount = 1;//头部View个数
    private Context context;
    private List<BenifitsBean.DataBean.ListBean> datalist;
    private LayoutInflater layoutInflater;
    private List<ImageView> headImgs = new ArrayList<>();

    public RVAdapter(Context context, List<BenifitsBean.DataBean.ListBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getContentItemCount() {
        return datalist.size();
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_CONTENT) {
            View convertView = layoutInflater.inflate(R.layout.recycler_item, null);
            return new MyViewHolder(convertView);
        } else if (viewType == ITEM_TYPE_HEADER) {
            View convertView = layoutInflater.inflate(R.layout.recycler_header, null);
            return new HeadViewHolder(convertView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myholder = (MyViewHolder) holder;
            Picasso.with(context).load(datalist.get(position - 1).getImage().get(0)).into(myholder.img_item_pic);
            myholder.tv_item_title.setText(datalist.get(position - 1).getTitle());
            myholder.tv_item_price_now.setText(datalist.get(position - 1).getPrice_now() + "");
            myholder.tv_item_price_original.setText(datalist.get(position - 1).getPrice_original() + "");
        } else if (holder instanceof HeadViewHolder) {
            ImageView img1 = new ImageView(context);
            ImageView img2 = new ImageView(context);
            img1.setImageResource(R.mipmap.ic_launcher);
            img2.setImageResource(R.mipmap.ic_launcher);
            Picasso.with(context).load("http://www.caimiapp.com/fllbas/images/yushou.png").into(img1);
            Picasso.with(context).load("http://www.caimiapp.com/fllbas/images/shuoming.png").into(img2);
            headImgs.clear();
            headImgs.add(img1);
            headImgs.add(img2);
            MyLoopAdapter loopAdapter = new MyLoopAdapter(((HeadViewHolder) holder).rollPagerView, headImgs);
            ((HeadViewHolder) holder).rollPagerView.setAdapter(loopAdapter);


        }

    }

    @Override
    public int getItemCount() {
        return datalist.size() + mHeaderCount;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        private RollPagerView rollPagerView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            rollPagerView = (RollPagerView) itemView.findViewById(R.id.rpv_show_header);
            rollPagerView.setPlayDelay(3000);
            rollPagerView.setAnimationDurtion(1000);;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_item_pic;
        private TextView tv_item_title;
        private TextView tv_item_price_now;
        private TextView tv_item_price_original;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_item_pic = (ImageView) itemView.findViewById(R.id.img_rvitem_pic);
            tv_item_title = (TextView) itemView.findViewById(R.id.tv_rvitem_title);
            tv_item_price_now = (TextView) itemView.findViewById(R.id.tv_rvitem_price_now);
            tv_item_price_original = (TextView) itemView.findViewById(R.id.tv_rvitem_price_orignal);
        }
    }

    class MyLoopAdapter extends MLoopPagerAdapter {
        List<ImageView> imgs;

        public MyLoopAdapter(RollPagerView viewPager, List<ImageView> imgs) {
            super(viewPager);
            this.imgs = imgs;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            return imgs.get(position);
        }

        @Override
        public int getRealCount() {
            return imgs.size();
        }
    }
}
