package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.ElementDetailBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.List;

public class ElementDetailActivity extends AppCompatActivity {

    private Intent intent;
    private int element_id;
    private TextView tv_chinese_name;
    private TextView tv_english_name;
    private RelativeLayout rv_show_fengxian;
    private TextView tv_func_container;
    private TextView tv_xiangxi;
    private TextView tv_show_count;

    private ImageView img_product1;
    private TextView tv_brand1;
    private TextView tv_name1;
    private RatingBar rb_score1;
    private TextView tv_score1;

    private ImageView img_product2;
    private TextView tv_brand2;
    private TextView tv_name2;
    private RatingBar rb_score2;
    private TextView tv_score2;

    private ImageView img_product3;
    private TextView tv_brand3;
    private TextView tv_name3;
    private RatingBar rb_score3;
    private TextView tv_score3;

    private ElementDetailBean elementDetailBean;
    private List<ElementDetailBean.DataBean.ProductBean.ListBean> productList;
    private ElementDetailBean.DataBean.ProductBean product;
    private ElementDetailBean.DataBean element;
    private View rv_product1;
    private View rv_product2;
    private View rv_product3;

    private boolean sensitization;
    private boolean pimpleCaution;
    private boolean pregnantCaution;
    private LinearLayout ll_fenxian_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);
        intent = getIntent();
        element_id = intent.getIntExtra("element_id", 0);
        initView();
        loadData();
    }

    private void loadData() {
        NetWorkRequest.getElementDetailBean(this, element_id, new NetWorkRequest.RequestCallBack() {


            @Override
            public void success(Object result) {
                elementDetailBean = ((ElementDetailBean) result);
                element = elementDetailBean.getData();
                product = element.getProduct();
                productList = product.getList();
                pregnantCaution = element.isPregnantCaution();
                pimpleCaution = element.isPimpleCaution();
                sensitization = element.isSensitization();
                bindDataToView();
            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void bindDataToView() {
        tv_chinese_name.setText(element.getElementChinaName());
        tv_english_name.setText(element.getName());
        if (!pimpleCaution && !pregnantCaution && !sensitization) {
            rv_show_fengxian.setVisibility(View.GONE);
        }
        if (pimpleCaution) {
            TextView tv_dou = new TextView(this);
            tv_dou.setText("易致痘");
            tv_dou.setTextColor(Color.WHITE);
            tv_dou.setBackgroundColor(Color.RED);
            tv_dou.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(3, 3, 3, 3);
            tv_dou.setPadding(3,3,3,3);
            tv_dou.setLayoutParams(layoutParams);
            ll_fenxian_container.addView(tv_dou);
        }

        if (sensitization) {
            TextView tv_min = new TextView(this);
            tv_min.setText("易致敏");
            tv_min.setTextColor(Color.WHITE);
            tv_min.setBackgroundColor(Color.RED);
            tv_min.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv_min.setPadding(3,3,3,3);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(3, 3, 3, 3);
            tv_min.setLayoutParams(layoutParams);
            ll_fenxian_container.addView(tv_min);
        }

        if (pregnantCaution) {
            TextView tv_yun = new TextView(this);
            tv_yun.setText("孕期哺乳期慎用");
            tv_yun.setTextColor(Color.WHITE);
            tv_yun.setBackgroundColor(Color.RED);
            tv_yun.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            tv_yun.setPadding(3,3,3,3);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(3, 3, 3, 3);
            tv_yun.setLayoutParams(layoutParams);
            ll_fenxian_container.addView(tv_yun);
        }

        tv_func_container.setText(element.getFunc());
        tv_xiangxi.setText(element.getDetail());
        tv_show_count.setText("共" + product.getCount() + "个产品含有该成分");

        switch (productList.size()) {
            case 0:
                break;
            case 1:
                rv_product1.setVisibility(View.VISIBLE);
                Picasso.with(this).load(productList.get(0).getImage()).into(img_product1);
                tv_brand1.setText(productList.get(0).getBrandName()+productList.get(0).getBrandChinaName());
                tv_name1.setText(productList.get(0).getName());
                rb_score1.setRating((float) (productList.get(0).getScore()/2));
                tv_score1.setText(productList.get(0).getScore()+"");
                break;
            case 2:
                rv_product1.setVisibility(View.VISIBLE);
                rv_product2.setVisibility(View.VISIBLE);
                Picasso.with(this).load(productList.get(0).getImage()).into(img_product1);
                tv_brand1.setText(productList.get(0).getBrandName()+productList.get(0).getBrandChinaName());
                tv_name1.setText(productList.get(0).getName());
                rb_score1.setRating((float) (productList.get(0).getScore()/2));
                tv_score1.setText(productList.get(0).getScore()+"");

                Picasso.with(this).load(productList.get(1).getImage()).into(img_product2);
                tv_brand2.setText(productList.get(1).getBrandName()+productList.get(1).getBrandChinaName());
                tv_name2.setText(productList.get(1).getName());
                rb_score2.setRating((float) (productList.get(1).getScore()/2));
                tv_score2.setText(productList.get(1).getScore()+"");
                break;

            case 3:
                rv_product1.setVisibility(View.VISIBLE);
                rv_product2.setVisibility(View.VISIBLE);
                rv_product3.setVisibility(View.VISIBLE);
                Picasso.with(this).load(productList.get(0).getImage()).into(img_product1);
                tv_brand1.setText(productList.get(0).getBrandName()+productList.get(0).getBrandChinaName());
                tv_name1.setText(productList.get(0).getName());
                rb_score1.setRating((float) (productList.get(0).getScore()/2));
                tv_score1.setText(productList.get(0).getScore()+"");

                Picasso.with(this).load(productList.get(1).getImage()).into(img_product2);
                tv_brand2.setText(productList.get(1).getBrandName()+productList.get(1).getBrandChinaName());
                tv_name2.setText(productList.get(1).getName());
                rb_score2.setRating((float) (productList.get(1).getScore()/2));
                tv_score2.setText(productList.get(1).getScore()+"");

                Picasso.with(this).load(productList.get(2).getImage()).into(img_product3);
                tv_brand3.setText(productList.get(2).getBrandName()+productList.get(2).getBrandChinaName());
                tv_name3.setText(productList.get(2).getName());
                rb_score3.setRating((float) (productList.get(2).getScore()/2));
                tv_score3.setText(productList.get(2).getScore()+"");
                break;
            default:
                break;
        }

    }

    private void initView() {
        tv_chinese_name = (TextView) findViewById(R.id.tv_element_chinese_name);
        tv_english_name = (TextView) findViewById(R.id.tv_element_english_name);

        rv_show_fengxian = ((RelativeLayout) findViewById(R.id.rv_element_show_fenxian));
        ll_fenxian_container = ((LinearLayout) findViewById(R.id.ll_element_fenxian_container));
        tv_func_container = ((TextView) findViewById(R.id.tv_element_detail_func_container));
        tv_xiangxi = ((TextView) findViewById(R.id.tv_element_detail_container));
        tv_show_count = ((TextView) findViewById(R.id.tv_element_show_total_product_count));

        rv_product1 = findViewById(R.id.rv_element_product1);
        img_product1 = ((ImageView) findViewById(R.id.img_element_product1));
        tv_brand1 = ((TextView) findViewById(R.id.tv_element_product_brand1));
        tv_name1 = ((TextView) findViewById(R.id.tv_element_product_name1));
        rb_score1 = ((RatingBar) findViewById(R.id.rb_element_detail_score1));
        tv_score1 = ((TextView) findViewById(R.id.tv_element_score1));

        rv_product2 = findViewById(R.id.rv_element_product2);
        img_product2 = ((ImageView) findViewById(R.id.img_element_product2));
        tv_brand2 = ((TextView) findViewById(R.id.tv_element_product_brand2));
        tv_name2 = ((TextView) findViewById(R.id.tv_element_product_name2));
        rb_score2 = ((RatingBar) findViewById(R.id.rb_element_detail_score2));
        tv_score2 = ((TextView) findViewById(R.id.tv_element_score2));

        rv_product3 = findViewById(R.id.rv_element_product3);
        img_product3 = ((ImageView) findViewById(R.id.img_element_product3));
        tv_brand3 = ((TextView) findViewById(R.id.tv_element_product_brand3));
        tv_name3 = ((TextView) findViewById(R.id.tv_element_product_name3));
        rb_score3 = ((RatingBar) findViewById(R.id.rb_element_detail_score3));
        tv_score3 = ((TextView) findViewById(R.id.tv_element_score3));

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_element_look_all:
                //// TODO: 2016/12/24   跳到产品搜索页面
                break;

            case R.id.img_element_detail_back:
               onBackPressed();
                break;
            default:
                break;
        }
    }

}
