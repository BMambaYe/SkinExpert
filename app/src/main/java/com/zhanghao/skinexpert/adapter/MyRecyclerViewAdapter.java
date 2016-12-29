package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.Activity.ProductDetailActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.interfaces.ItemClickCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by RockGao on 2016/12/27.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    List<Map<String,String>> dataList;
    private int id;
    public MyRecyclerViewAdapter(Context context, List<Map<String,String>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public ItemClickCallback itemClickCallback;

    public ItemClickCallback getItemClickCallback() {
        return itemClickCallback;
    }

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.my_product_fragment_recyclerview_item,parent,false);
        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (dataList.get(position).get("image")!=null&&!"".equals(dataList.get(position).get("image"))){
            Picasso.with(context).load(dataList.get(position).get("image")).into(holder.imagView);
        }
        holder.textView1.setText(dataList.get(position).get("brandChinaName")+dataList.get(position).get("brandEnName"));
        holder.textView2.setText(dataList.get(position).get("name"));

        holder.imagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到产品详情界面
                id = Integer.parseInt(String.valueOf(dataList.get(position).get("id")));
                Intent intentToProductDetail = new Intent(context, ProductDetailActivity.class);
                intentToProductDetail.putExtra("id",id);
                context.startActivity(intentToProductDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagView ;
        private TextView textView1;
        private TextView textView2;
        public MyViewHolder(View itemView) {
            super(itemView);
            imagView = (ImageView) itemView.findViewById(R.id.recycler_item_imgview);
            textView1 = (TextView) itemView.findViewById(R.id.recycler_item_txt1);
            textView2 = (TextView) itemView.findViewById(R.id.recycler_item_txt2);
//            imagView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //跳转到产品详情界面
//                    Intent intentToProductDetail = new Intent(context, ProductDetailActivity.class);
//                    intentToProductDetail.putExtra("id",id);
//                    context.startActivity(intentToProductDetail);
//                }
//            });
        }
    }
}
