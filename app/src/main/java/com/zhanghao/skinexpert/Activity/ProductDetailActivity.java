package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.DetailDisgussAdapter;
import com.zhanghao.skinexpert.beans.DetailCommentBean;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ProductBean;
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
    private Button btn_buy_now;
    private Intent intent;
    private Button btn_ask_to_expert;
    private Button btn_show_all_chenfen;
    private TextView tv_look_all_disguss;
    private String tb_url;
    private int cmcid;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        intent = getIntent();
        id_fromlast = intent.getIntExtra("id", 0);
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
        btn_buy_now = ((Button) headView.findViewById(R.id.btn_detail_buy_now));

        String buy = intent.getStringExtra("buy");
        if (buy.equals("立即购买")) {
            btn_buy_now.setText(buy);
        } else if (buy.equals("totaobao")) {
            tv_show_price_now.setVisibility(View.GONE);
            tv_show_price_original.setVisibility(View.GONE);
            tv_show_weight.setVisibility(View.GONE);
            tb_url = producebean.getTaobao_url();
            if (tb_url.startsWith("http")) {
                btn_buy_now.setText("至官方旗舰店购买");
            } else {
                btn_buy_now.setVisibility(View.GONE);
            }
        }
        btn_buy_now.setOnClickListener(onClickListener);
        img_expert_headpic = ((ImageView) headView.findViewById(R.id.img_detail_expert_headerpic));
        Picasso.with(this).load(producebean.getHeadface()).into(img_expert_headpic);
        tv_show_expert_zhiwei = ((TextView) headView.findViewById(R.id.tv_detail_expert_zhiwei));
        tv_show_expert_zhiwei.setText(producebean.getUserExpert());
        tv_show_expert_name = ((TextView) headView.findViewById(R.id.tv_detail_expert_name));
        tv_show_expert_name.setText(producebean.getNick());
        btn_ask_to_expert = ((Button) headView.findViewById(R.id.btn_detail_ask_to_expert));
        btn_ask_to_expert.setOnClickListener(onClickListener);
        tv_show_ask_num = ((TextView) headView.findViewById(R.id.tv_detail_show_ask_people_num));
        tv_show_ask_num.setText("已有" + producebean.getApplySkinSuggestionCount() + "人咨询该产品");
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

        tv_look_all_disguss = ((TextView) headView.findViewById(R.id.tv_detail_look_all_disguss));
        tv_look_all_disguss.setOnClickListener(onClickListener);

        img_expert_dianpin = ((ImageView) headView.findViewById(R.id.img_detail_expert_headerpic_dianpin));
        Picasso.with(this).load(producebean.getHeadface()).into(img_expert_dianpin);

        tv_expert_name_dianpin = ((TextView) headView.findViewById(R.id.tv_detail_expert_name_dianpin));
        tv_expert_name_dianpin.setText(producebean.getNick());

        tv_expert_zhiwei_dianpin = ((TextView) headView.findViewById(R.id.tv_detail_expert_zhiwei_dianpin));
        tv_expert_zhiwei_dianpin.setText(producebean.getUserExpert());

        tv_expert_speak = ((TextView) headView.findViewById(R.id.tv_expert_speaking));
        tv_expert_speak.setText(producebean.getEffectAbstract());

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_detail_buy_now:
                    if (((Button) v).getText().equals("立即购买")) {
                        // TODO: 2016/12/23  写好提交订单界面
                        Intent intent = new Intent(ProductDetailActivity.this, SubmitOrderActivity.class);
                        startActivity(intent);
                    } else if (((Button) v).getText().equals("至官方旗舰店购买")) {
                        // // TODO: 2016/12/23 写从淘宝购买界面 只有一个web
                        Intent intent = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                        intent.putExtra("id", producebean.getId() + "");
                        intent.putExtra("title", "");
                        intent.putExtra("tb_url", tb_url);
                        startActivity(intent);
                    }
                    break;
                case R.id.btn_detail_ask_to_expert:
                    Intent intent = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent.putExtra("id", producebean.getId() + "");
                    intent.putExtra("title", "使用建议");
                    startActivity(intent);
                    break;
                case R.id.rv_detail_gongxiao_:
                    Intent intent1 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent1.putExtra("id", producebean.getId() + "");
                    intent1.putExtra("title", "功效成分");
                    startActivity(intent1);
                    break;

                case R.id.rv_detail_fangfuji_:
                    Intent intent2 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent2.putExtra("id", producebean.getId() + "");
                    intent2.putExtra("title", "防腐剂");
                    startActivity(intent2);
                    break;
                case R.id.rv_detail_yizhidou_:
                    Intent intent3 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent3.putExtra("id", producebean.getId() + "");
                    intent3.putExtra("title", "易致痘");
                    startActivity(intent3);
                    break;

                case R.id.rv_detail_yizhimin_:
                    Intent intent4 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent4.putExtra("id", producebean.getId() + "");
                    intent4.putExtra("title", "易致敏");
                    startActivity(intent4);
                    break;

                case R.id.rv_detail_shenyong_:
                    Intent intent5 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent5.putExtra("id", producebean.getId() + "");
                    intent5.putExtra("title", "孕期、哺乳期慎用");
                    startActivity(intent5);
                    break;
                case R.id.btn_detail_look_all_chenfen:
                    Intent intent6 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent6.putExtra("id", producebean.getId() + "");
                    intent6.putExtra("title", "产品成分");
                    startActivity(intent6);
                    break;
                case R.id.tv_detail_look_all_disguss:
                    Intent intent7 = new Intent(ProductDetailActivity.this, DetailAllDisgussActivity.class);
                    intent7.putExtra("cmcid",cmcid);
                    intent7.putExtra("title",productDetailBean.getData().getProduct().getTitle());
                    startActivity(intent7);
                    break;
                default:
                    break;
            }
        }
    };

    private void loadData() {
        NetWorkRequest.getProductDetailBean(this, id_fromlast, new NetWorkRequest.RequestCallBack() {


            @Override
            public void success(Object result) {
                productDetailBean = (ProductDetailBean) result;
                producebean = productDetailBean.getData().getProduct();
                communitybeans = productDetailBean.getData().getProduct().getCommunity();
                bindHeaderView();
                pid = producebean.getPid();
                NetWorkRequest.getProductBean(ProductDetailActivity.this, pid, new NetWorkRequest.RequestCallBack() {
                    @Override
                    public void success(Object result) {
                        ProductBean productBean = (ProductBean) result;
                        cmcid = productBean.getData().getList().get(0).getCmcid();
                    }

                    @Override
                    public void fail(String result) {

                    }
                });
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

        NetWorkRequest.getDetailElementBean(this, id_fromlast, new NetWorkRequest.RequestCallBack() {

            @Override
            public void success(Object result) {
                detailElementbean = ((DetailElementBean) result);
                elements = detailElementbean.getData().getList().get(0).getElementList();
                btn_look_all_chenfen = ((Button) headView.findViewById(R.id.btn_detail_look_all_chenfen));
                btn_look_all_chenfen.setOnClickListener(onClickListener);
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
                    RelativeLayout rv_gongxiao = (RelativeLayout) headView.findViewById(R.id.rv_detail_gongxiao_);
                    rv_gongxiao.setOnClickListener(onClickListener);

                }
                //// TODO: 2016/12/23 找出防腐剂成分数据来源
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
                    RelativeLayout rv_doudou = (RelativeLayout) headView.findViewById(R.id.rv_detail_yizhidou_);
                    rv_doudou.setOnClickListener(onClickListener);
                }

                if (num_guoming_element == 0) {
                    ll_guomin_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_yizhimin_have));
                    ll_guomin_have.setVisibility(View.GONE);
                    tv_guomin_wu = ((TextView) headView.findViewById(R.id.tv_detail_yizhimin_wu));
                    tv_guomin_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_guomin = ((TextView) headView.findViewById(R.id.tv_detail_show_num_yizhimin));
                    tv_num_guomin.setText(num_guoming_element + "种");
                    RelativeLayout rv_guomin = (RelativeLayout) headView.findViewById(R.id.rv_detail_yizhimin_);
                    rv_guomin.setOnClickListener(onClickListener);
                }

                if (num_yunfu_element == 0) {
                    ll_yunfu_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_shenyong_have));
                    ll_yunfu_have.setVisibility(View.GONE);
                    tv_yunfu_wu = ((TextView) headView.findViewById(R.id.tv_detail_shenyong_wu));
                    tv_yunfu_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_yunfu = ((TextView) headView.findViewById(R.id.tv_detail_show_num_shenyong));
                    tv_num_yunfu.setText(num_yunfu_element + "种");
                    RelativeLayout rv_yunfu = (RelativeLayout) headView.findViewById(R.id.rv_detail_shenyong_);
                    rv_yunfu.setOnClickListener(onClickListener);
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
                Intent intent = new Intent(this, DetailAllDisgussActivity.class);
                intent.putExtra("cmcid", cmcid);
                intent.putExtra("title",productDetailBean.getData().getProduct().getTitle());
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
