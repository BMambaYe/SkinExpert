package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.DetailDisgussAdapter;
import com.zhanghao.skinexpert.beans.CollectionResultBean;
import com.zhanghao.skinexpert.beans.DetailCommentBean;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ElementsContainer;
import com.zhanghao.skinexpert.beans.ProductBean;
import com.zhanghao.skinexpert.beans.ProductDetailBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;
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
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> funcElements = new ArrayList<DetailElementBean.DataBean.ListBean.ElementListBean>();
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> fangfuElements = new ArrayList<DetailElementBean.DataBean.ListBean.ElementListBean>();
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> doudouElements = new ArrayList<DetailElementBean.DataBean.ListBean.ElementListBean>();
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> guominElements = new ArrayList<DetailElementBean.DataBean.ListBean.ElementListBean>();
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> yunfuElements = new ArrayList<DetailElementBean.DataBean.ListBean.ElementListBean>();
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
    private TextView tv_look_all_disguss;
    private String tb_url;
    private int cmcid;
    private int pid;
    private RelativeLayout rv_used;
    private CheckBox cb_used;
    private CheckBox cb_wanted;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private boolean isFirstChanged = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        sqLiteHelper = new SQLiteHelper(this);
        db = sqLiteHelper.getReadableDatabase();
        intent = getIntent();
        id_fromlast = intent.getIntExtra("id", 0);
        initView();
        loadData();

    }

    private int wanted_status = 1;

    private static final int REQUEST_CODE_TO_USE_FEELING = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_TO_USE_FEELING && resultCode == 110) {
            if (data.getBooleanExtra("baocun", false)) {
                cb_used.setChecked(true);
            }
        }
    }

    private void initView() {
        lv_show = (ListView) findViewById(R.id.lv_detail_show_disguss);
        rv_used = ((RelativeLayout) findViewById(R.id.rv_detail_cb_used));
        cb_used = ((CheckBox) findViewById(R.id.cb_detail_used));
        cb_wanted = ((CheckBox) findViewById(R.id.cb_detail_want));
        //给想用的产品checkbox设置事件并加入到数据库

        rv_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, UsingFeelingActivity.class);
                intent.putExtra("id_fromlast", id_fromlast);
                startActivityForResult(intent, REQUEST_CODE_TO_USE_FEELING);
            }
        });
        layoutInflater = LayoutInflater.from(this);
        headView = layoutInflater.inflate(R.layout.pruduct_detail_header, null);
        lv_show.addHeaderView(headView);
        //加底部布局，查看全部评论
        TextView tv_all_disguss = new TextView(this);
        tv_all_disguss.setTextColor(Color.parseColor("#ff7074"));
        tv_all_disguss.setText("查看全部");
        tv_all_disguss.setHeight(100);
        tv_all_disguss.setGravity(Gravity.CENTER);
        tv_all_disguss.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv_all_disguss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, DetailAllDisgussActivity.class);
                intent.putExtra("cmcid", cmcid);
                intent.putExtra("title", productDetailBean.getData().getProduct().getTitle());
                startActivity(intent);
            }
        });
        lv_show.addFooterView(tv_all_disguss);

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
        cb_used.setChecked(producebean.isUsed());
        cb_wanted.setChecked(producebean.isCollection());
        cb_wanted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetWorkRequest.postCollection(ProductDetailActivity.this, pid, "add", new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            CollectionResultBean resultBean = (CollectionResultBean) result;
                            Toast.makeText(ProductDetailActivity.this, ((CollectionResultBean) result).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(String result) {

                        }
                    });
                } else {
                    NetWorkRequest.postCollection(ProductDetailActivity.this, pid, "del", new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            CollectionResultBean resultBean = (CollectionResultBean) result;
                            Toast.makeText(ProductDetailActivity.this, ((CollectionResultBean) result).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(String result) {

                        }
                    });
                }
            }
        });

        tv_show_price_now = ((TextView) headView.findViewById(R.id.tv_detail_show_price_now));
        tv_show_price_original = ((TextView) headView.findViewById(R.id.tv_detail_show_price_original));
        tv_show_weight = ((TextView) headView.findViewById(R.id.tv_detail_show_weight));

        btn_buy_now = ((Button) headView.findViewById(R.id.btn_detail_buy_now));

        String buy = producebean.getBuy_price();
        if (buy != null) {
            btn_buy_now.setText("立即购买");
            tv_show_price_now.setText("￥" + producebean.getBuy_price());
            tv_show_price_original.setText("￥" + producebean.getBuy_price_original());
            tv_show_weight.setText(producebean.getBuy_specifications());
        } else if (buy == null) {
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
        img_expert_headpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, UserInfoActivity.class);
                intent.putExtra("uid", producebean.getUid());
                intent.putExtra("userskin", "");
                startActivity(intent);
            }
        });
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
            tv_tag.setBackgroundColor(Color.WHITE);
            tv_tag.setText(funcArrBean.getName());
            LinearLayout.LayoutParams tvparams = new PercentLinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvparams.setMargins(10, 10, 10, 10);
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

        //专家头像点击进入
        img_expert_dianpin = ((ImageView) headView.findViewById(R.id.img_detail_expert_headerpic_dianpin));
        img_expert_dianpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, UserInfoActivity.class);
                intent.putExtra("uid", producebean.getUid());
                intent.putExtra("userskin", "");
                startActivity(intent);
            }
        });
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
                        Intent intent = new Intent(ProductDetailActivity.this, SubmitOrderActivity.class);
                        intent.putExtra("img", producebean.getPic());
                        intent.putExtra("title", producebean.getTitle() + " " + producebean.getSpecification());
                        intent.putExtra("price", producebean.getBuy_price() + "");
                        startActivity(intent);
                    } else if (((Button) v).getText().equals("至官方旗舰店购买")) {
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
                    intent1.putExtra("elements", new ElementsContainer(funcElements));
                    startActivity(intent1);
                    break;

                case R.id.rv_detail_fangfuji_:
                    Intent intent2 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent2.putExtra("id", producebean.getId() + "");
                    intent2.putExtra("title", "防腐剂");
                    intent2.putExtra("elements", new ElementsContainer(fangfuElements));
                    startActivity(intent2);
                    break;
                case R.id.rv_detail_yizhidou_:
                    Intent intent3 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent3.putExtra("id", producebean.getId() + "");
                    intent3.putExtra("title", "易致痘");
                    intent3.putExtra("elements", new ElementsContainer(doudouElements));
                    startActivity(intent3);
                    break;

                case R.id.rv_detail_yizhimin_:
                    Intent intent4 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent4.putExtra("id", producebean.getId() + "");
                    intent4.putExtra("title", "易致敏");
                    intent4.putExtra("elements", new ElementsContainer(guominElements));
                    startActivity(intent4);
                    break;

                case R.id.rv_detail_shenyong_:
                    Intent intent5 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent5.putExtra("id", producebean.getId() + "");
                    intent5.putExtra("title", "孕期、哺乳期慎用");
                    intent5.putExtra("elements", new ElementsContainer(yunfuElements));
                    startActivity(intent5);
                    break;
                case R.id.btn_detail_look_all_chenfen:
                    Intent intent6 = new Intent(ProductDetailActivity.this, CommonWebviewActivity.class);
                    intent6.putExtra("id", producebean.getId() + "");
                    intent6.putExtra("title", "产品成分");
                    intent6.putExtra("elements", new ElementsContainer(elements));
                    startActivity(intent6);
                    break;
                case R.id.tv_detail_look_all_disguss:
                    Intent intent7 = new Intent(ProductDetailActivity.this, DetailAllDisgussActivity.class);
                    intent7.putExtra("cmcid", cmcid);
                    intent7.putExtra("title", productDetailBean.getData().getProduct().getTitle());
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
                        funcElements.add(elementListBean);
                    }
                    if (elementListBean.getFunc().contains("防腐剂")) {
                        num_base_element++;
                        fangfuElements.add(elementListBean);
                    }
                    if (elementListBean.isPimpleCaution()) {
                        num_doudou_element++;
                        doudouElements.add(elementListBean);
                    }
                    if (elementListBean.isSensitization()) {
                        num_guoming_element++;
                        guominElements.add(elementListBean);
                    }
                    if (elementListBean.isPregnantCaution()) {
                        num_yunfu_element++;
                        yunfuElements.add(elementListBean);
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

                if (num_base_element == 0) {
                    ll_base_have = ((LinearLayout) headView.findViewById(R.id.ll_detail_if_fangfuji_have));
                    ll_base_have.setVisibility(View.GONE);
                    tv_base_wu = ((TextView) headView.findViewById(R.id.tv_detail_nouse_fangfuji));
                    tv_base_wu.setVisibility(View.VISIBLE);
                } else {
                    tv_num_base = ((TextView) headView.findViewById(R.id.tv_detail_show_num_fangfuji));
                    tv_num_base.setText(num_base_element + "种");
                    RelativeLayout rv_fangfuji = (RelativeLayout) headView.findViewById(R.id.rv_detail_fangfuji_);
                    rv_fangfuji.setOnClickListener(onClickListener);
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
                intent.putExtra("title", productDetailBean.getData().getProduct().getTitle());
                startActivity(intent);
                break;
            case R.id.img_detail_back:
                onBackPressed();
                break;
            case R.id.tv_share_pdetail:
                //// TODO: 2016/12/22  分享
                initPopupWindow();
                break;
            default:
                break;
        }
    }

    private PopupWindow popupWindow;

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_share, null);
        TextView tv_share = (TextView) contentView.findViewById(R.id.tv_pop_win_share);
        tv_share.setText("分享这个产品");
        //设置popupWindow焦点
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        //创建popupWindow
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //点击popupWindow以外隐藏
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //popupWindow动画
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        //popupWindow以外的透明度
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //显示popupWindow
        popupWindow.showAtLocation(findViewById(R.id.activity_product_detail), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);
        Button popupBtn = (Button) contentView.findViewById(R.id.btn_popu);
        popupBtn.setOnClickListener(dismissPopupListener);
        //TODO 分享
    }

    View.OnClickListener dismissPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closePopupWindow();
        }
    };

    PopupWindow.OnDismissListener popupDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            closePopupWindow();
        }
    };

    private void closePopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        }
    }

}
