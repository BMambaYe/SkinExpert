package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.DetailDisgussAdapter;
import com.zhanghao.skinexpert.beans.CommunityBeansContainer;
import com.zhanghao.skinexpert.beans.DetailCommentBean;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ProductDetailBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.view.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private int id_fromlast;
    private ListView lv_show;
    private List<ProductDetailBean.DataBean.ProductBean.CommunityBean> communitybeans = new ArrayList<>();
    private ProductDetailBean productDetailBean;
    private DetailDisgussAdapter detailDisgussAdapter;
    private LayoutInflater layoutInflater;
    private View headView;
    private ImageView main_pic;
    private TextView tv_title;
    private ProductDetailBean.DataBean.ProductBean producebean;
    private RatingBar rb_score;
    private TextView tv_show_score;
    private TextView tv_show_num_of_pinlun;
    private TextView tv_show_price_now;
    private TextView tv_show_price_original;
    private TextView tv_show_weight;
    private ImageView img_expert_headpic;
    private TextView tv_show_expert_zhiwei;
    private TextView tv_show_expert_name;
    private List<DetailCommentBean.DataBean.ListBean> listbeans;
    private DetailCommentBean commentBean;
    private DetailElementBean detailElementbean;
    private TextView tv_show_ask_num;
    private LinearLayout ll_show_tags;
    private TextView tv_show_laiyuan;
    private TextView tv_show_guige;
    private TextView tv_show_guangfang_price;
    private ImageView img_expert_dianpin;
    private TextView tv_expert_name_dianpin;
    private TextView tv_expert_zhiwei_dianpin;
    private TextView tv_expert_speak;
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> elements;
    private TextView tv_num_gongxiao;
    private TextView tv_gongxiao_wu;
    private LinearLayout ll_gongxiao_have;

    private TextView tv_num_guomin;
    private TextView tv_guomin_wu;
    private LinearLayout ll_guomin_have;

    private TextView tv_num_base;
    private TextView tv_base_wu;
    private LinearLayout ll_base_have;

    private TextView tv_num_doudou;
    private TextView tv_doudou_wu;
    private LinearLayout ll_doudou_have;

    private TextView tv_num_yunfu;
    private TextView tv_yunfu_wu;
    private LinearLayout ll_yunfu_have;

    private Button btn_look_all_chenfen;

    private int num_func_element = 0;
    private int num_doudou_element = 0;
    private int num_guoming_element = 0;
    private int num_yunfu_element = 0;
    private int num_base_element = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();

        id_fromlast = intent.getIntExtra("id", 0);
        Log.i("110", "onCreate: " + id_fromlast);
        initView();
        loadData();

    }

    private void initView() {
        lv_show = (ListView) findViewById(R.id.lv_detail_show_disguss);
        layoutInflater = LayoutInflater.from(this);
        headView = layoutInflater.inflate(R.layout.pruduct_detail_header, null);
        lv_show.addHeaderView(headView);


    }

    private void bindHeaderView() {
        main_pic = (ImageView) headView.findViewById(R.id.img_show_main);
        Picasso.with(this).load(producebean.getPic()).into(main_pic);
        tv_title = ((TextView) headView.findViewById(R.id.tv_detail_show_title));
        tv_title.setText(producebean.getTitle());
        rb_score = ((RatingBar) headView.findViewById(R.id.rb_detail_score));
        rb_score.setRating((float) (producebean.getProduct_vote_score() / 2));
        tv_show_score = ((TextView) headView.findViewById(R.id.tv_detail_show_score));
        tv_show_score.setText(producebean.getProduct_vote_score() + "");
        tv_show_num_of_pinlun = ((TextView) headView.findViewById(R.id.tv_detail_num_of_pinlun));
        tv_show_num_of_pinlun.setText("共计" + producebean.getProduct_vote_count() + "人评分");
        tv_show_price_now = ((TextView) headView.findViewById(R.id.tv_detail_show_price_now));
        tv_show_price_now.setText("￥" + producebean.getBuy_price());
        tv_show_price_original = ((TextView) headView.findViewById(R.id.tv_detail_show_price_original));
        tv_show_price_original.setText("￥" + producebean.getBuy_price_original());
        tv_show_weight = ((TextView) headView.findViewById(R.id.tv_detail_show_weight));
        tv_show_weight.setText(producebean.getBuy_specifications());
        img_expert_headpic = ((ImageView) headView.findViewById(R.id.img_detail_expert_headerpic));
        Picasso.with(this).load(producebean.getHeadface()).into(img_expert_headpic);
        tv_show_expert_zhiwei = ((TextView) headView.findViewById(R.id.tv_detail_expert_zhiwei));
        tv_show_expert_zhiwei.setText(producebean.getUserExpert());
        tv_show_expert_name = ((TextView) headView.findViewById(R.id.tv_detail_expert_name));
        tv_show_expert_name.setText(producebean.getNick());
        tv_show_ask_num = ((TextView) headView.findViewById(R.id.tv_detail_show_ask_people_num));
        tv_show_ask_num.setText("已有" + producebean.getApplySkinSuggestionCount() + "人咨询该产品");

        //// TODO: 2016/12/22
        ll_show_tags = ((LinearLayout) headView.findViewById(R.id.ll_detail_show_tags));
        List<ProductDetailBean.DataBean.ProductBean.FuncArrBean> funcArr = producebean.getFuncArr();
        for (int i = 0; i < funcArr.size(); i++) {
            ProductDetailBean.DataBean.ProductBean.FuncArrBean funcArrBean = funcArr.get(i);
            TextView tv_tag = new TextView(this);
            tv_tag.setText(funcArrBean.getName());
            LinearLayout.LayoutParams tvparams = new PercentLinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvparams.setMargins(5, 5, 5, 5);
            tv_tag.setLayoutParams(tvparams);
            ll_show_tags.addView(tv_tag);
        }

        tv_show_laiyuan = ((TextView) headView.findViewById(R.id.tv_detail_show_laiyuan));
        tv_show_laiyuan.setText(producebean.getElementListSource());

        tv_show_guige = ((TextView) headView.findViewById(R.id.tv_detail_show_guige));
        tv_show_guige.setText(producebean.getSpecification());

        tv_show_guangfang_price = ((TextView) headView.findViewById(R.id.tv_detail_show_guangfang_price));
        tv_show_guangfang_price.setText(producebean.getPrice());

        img_expert_dianpin = ((ImageView) headView.findViewById(R.id.img_detail_expert_headerpic_dianpin));
        Picasso.with(this).load(producebean.getHeadface()).into(img_expert_dianpin);

        tv_expert_name_dianpin = ((TextView) headView.findViewById(R.id.tv_detail_expert_name_dianpin));
        tv_expert_name_dianpin.setText(producebean.getNick());

        tv_expert_zhiwei_dianpin = ((TextView) headView.findViewById(R.id.tv_detail_expert_zhiwei_dianpin));
        tv_expert_zhiwei_dianpin.setText(producebean.getUserExpert());

        tv_expert_speak = ((TextView) headView.findViewById(R.id.tv_expert_speaking));
        tv_expert_speak.setText(producebean.getEffectAbstract());

    }

    private void loadData() {
        NetWorkRequest.getProductDetailBean(this, id_fromlast, new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                productDetailBean = (ProductDetailBean) result;
                producebean = productDetailBean.getData().getProduct();
                communitybeans = productDetailBean.getData().getProduct().getCommunity();
                bindHeaderView();
            }

            @Override
            public void fail(String result) {

            }
        });

        NetWorkRequest.getDetailCommentBean(this, new NetWorkRequest.RequestCallBack() {

            @Override
            public void success(Object result) {
                commentBean = ((DetailCommentBean) result);
                listbeans = commentBean.getData().getList();
                detailDisgussAdapter = new DetailDisgussAdapter(listbeans, ProductDetailActivity.this);
                lv_show.setAdapter(detailDisgussAdapter);

            }

            @Override
            public void fail(String result) {

            }
        });

        NetWorkRequest.getDetailElementBean(this, new NetWorkRequest.RequestCallBack() {


            @Override
            public void success(Object result) {
                detailElementbean = ((DetailElementBean) result);
                elements = detailElementbean.getData().getList().get(0).getElementList();
                btn_look_all_chenfen = ((Button) headView.findViewById(R.id.btn_detail_look_all_chenfen));
                btn_look_all_chenfen.setText("查看全部" + elements.size() + "种成分");
                for (int i = 0; i < elements.size(); i++) {
                    DetailElementBean.DataBean.ListBean.ElementListBean elementListBean = elements.get(i);
                    if (elementListBean.isFuncElement()) {
                        num_func_element++;
                    }
                    if (elementListBean.isPimpleCaution()) {
                        num_doudou_element++;
                    }
                    if (elementListBean.isSensitization()) {
                        num_guoming_element++;
                    }
                    if (elementListBean.isPregnantCaution()) {
                        num_yunfu_element++;
                    }
                    if (elementListBean.isBaseElement()) {
                        num_base_element++;
                    }
                }
                if (num_func_element == 0) {
                    ll_gongxiao_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_gongxiao_have));
                    ll_gongxiao_have.setVisibility(View.GONE);
                    tv_gongxiao_wu = ((TextView) headView.findViewById(R.id.tv_detail_gongxiao_wu));
                    tv_gongxiao_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_gongxiao = ((TextView) headView.findViewById(R.id.tv_detail_show_num_gongxiao));
                    tv_num_gongxiao.setText(num_func_element + "种");
                }

                if (num_base_element == 0) {
                    ll_base_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_fangfuji_have));
                    ll_base_have.setVisibility(View.GONE);
                    tv_base_wu = ((TextView) headView.findViewById(R.id.tv_detail_nouse_fangfuji));
                    tv_base_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_base = ((TextView) headView.findViewById(R.id.tv_detail_show_num_fangfuji));
                    tv_num_base.setText(num_base_element + "种");
                }

                if (num_doudou_element == 0) {
                    ll_doudou_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_yizhidou_have));
                    ll_doudou_have.setVisibility(View.GONE);
                    tv_doudou_wu = ((TextView) headView.findViewById(R.id.tv_detail_yizhidou_wu));
                    tv_doudou_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_doudou = ((TextView) headView.findViewById(R.id.tv_detail_show_num_yizhidou));
                    tv_num_doudou.setText(num_doudou_element + "种");
                }

                if (num_guoming_element == 0) {
                    ll_guomin_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_yizhimin_have));
                    ll_guomin_have.setVisibility(View.GONE);
                    tv_guomin_wu = ((TextView) headView.findViewById(R.id.tv_detail_yizhimin_wu));
                    tv_guomin_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_guomin = ((TextView) headView.findViewById(R.id.tv_detail_show_num_yizhimin));
                    tv_num_guomin.setText(num_guoming_element + "种");
                }

                if (num_yunfu_element == 0) {
                    ll_yunfu_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_shenyong_have));
                    ll_yunfu_have.setVisibility(View.GONE);
                    tv_yunfu_wu = ((TextView) headView.findViewById(R.id.tv_detail_shenyong_wu));
                    tv_yunfu_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_yunfu = ((TextView) headView.findViewById(R.id.tv_detail_show_num_shenyong));
                    tv_num_yunfu.setText(num_yunfu_element + "种");
                }
            }

            @Override
            public void fail(String result) {
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_disguss_in_comunity:
                Intent intent = new Intent(this, DetailAllElementsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("dis",new CommunityBeansContainer(communitybeans));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img_detail_back:
                onBackPressed();
                break;
            case R.id.tv_share_pdetail:
                //// TODO: 2016/12/22  分享
                break;
            default:
                break;
        }
    }

}
