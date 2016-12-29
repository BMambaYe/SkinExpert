package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.BuyoutOrderListBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderDetailActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TO_ADDRESS = 1;
    private TextView tv_name;
    private TextView tv_phone_num;
    private TextView tv_address;
    private LinearLayout ll_show_address;
    private ImageView img_product_pic;
    private TextView tv_product_title;
    private TextView tv_product_price;
    private TextView tv_product_totle_price;
    private TextView tv_bottom_price;
    private String pic_url;
    private String title;
    private String price;
    private SharedPreferences share_address;
    private RelativeLayout rv_if_address_choosed;
    private RelativeLayout rv_if_address_not_choosed;
    private TextView tv_totle_price;
    private RadioButton rb_alipy;
    private RadioButton rb_wechat_pay;
    private RelativeLayout rv_jijinhuangou;
    private boolean iszhifu;
    private RelativeLayout ll_order_statu;
    private TextView tv_order_staus;
    private TextView tv_left_time;
    private RelativeLayout rv_order_num;
    private TextView tv_order_num;
    private TextView tv_creat_time;
    private String buyout_id;
    private BuyoutOrderListBean.DataBean.ListBean orderDetail;
    private TextView tv_jijin_money;
    private SimpleDateFormat simpleDateFormat;
    private long creat_time;
    private long current_time;
    private long end_time;
    private TextView tv_cancel;
    private TextView tv_order_canceled;
    private RelativeLayout rv_show_zhifu;
    private TextView tv_pay_now;
    private MyCountDownTimer timer;
    private Intent intent;
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        token= ((MyApplication) getApplication()).getToken();
        initView();
        intent = getIntent();
        if (intent.getSerializableExtra("order_detail") == null) {//没有传入orderdetail，说明是立即购买后直接跳入
            loadData();
        } else {//说明是从我的订单中进入查询状态的
            orderDetail = ((BuyoutOrderListBean.DataBean.ListBean) intent.getSerializableExtra("order_detail"));
            bindData();
        }

    }

    private void loadData() {
        NetWorkRequest.postOrderList(this, token,new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                BuyoutOrderListBean buyoutOrderListBean = (BuyoutOrderListBean) result;
                if (buyoutOrderListBean.getData().getList().size() > 0) {
                    orderDetail = buyoutOrderListBean.getData().getList().get(0);
                    bindData();
                }
            }

            @Override
            public void fail(String result) {

            }
        });
    }


    private void initView() {
        tv_cancel = ((TextView) findViewById(R.id.tv_order_cancel));
        tv_order_canceled = ((TextView) findViewById(R.id.tv_order_canceled));
        tv_name = ((TextView) findViewById(R.id.tv_order_name));
        tv_phone_num = ((TextView) findViewById(R.id.tv_order_phonenum));
        tv_address = ((TextView) findViewById(R.id.tv_order_address));
        rv_if_address_choosed = ((RelativeLayout) findViewById(R.id.rv_order_if_choosed));
        ll_show_address = ((LinearLayout) findViewById(R.id.ll_order_show_address));//设置点击事件
        //隐藏控件
        ll_order_statu = ((RelativeLayout) findViewById(R.id.ll_order_statu));
        tv_order_staus = ((TextView) findViewById(R.id.tv_order_statu));
        tv_left_time = ((TextView) findViewById(R.id.tv_order_timecount));
        rv_order_num = ((RelativeLayout) findViewById(R.id.rv_order_num));
        tv_order_num = ((TextView) findViewById(R.id.tv_order_num));
        tv_creat_time = ((TextView) findViewById(R.id.tv_order_creat_time));


        ll_show_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, MyLoactionActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TO_ADDRESS);
            }
        });

        img_product_pic = ((ImageView) findViewById(R.id.img_order_product_pic));
        tv_product_title = ((TextView) findViewById(R.id.tv_order_product_title));
        tv_product_price = (TextView) findViewById(R.id.tv_order_price);
        tv_product_totle_price = ((TextView) findViewById(R.id.tv_order_product_total_price));
        tv_totle_price = ((TextView) findViewById(R.id.tv_order_totle_price));
        tv_bottom_price = ((TextView) findViewById(R.id.tv_order_bottom_totle_price));
        tv_jijin_money = ((TextView) findViewById(R.id.tv_order_jijin_money));
        rb_alipy = ((RadioButton) findViewById(R.id.rb_order_alipay));
        rb_wechat_pay = ((RadioButton) findViewById(R.id.rb_order_wechat_pay));

        rv_show_zhifu = ((RelativeLayout) findViewById(R.id.rv_order_show_zhifu));
        tv_pay_now = ((TextView) findViewById(R.id.tv_order_submitorder));
    }

    private void bindData() {

        tv_order_num.setText(orderDetail.getOrder_id());
        tv_creat_time.setText(orderDetail.getCreateTime());
        tv_product_price.setText("¥" + orderDetail.getIndividual_price() + "");
        Picasso.with(this).load(orderDetail.getImage()).into(img_product_pic);
        tv_product_title.setText(orderDetail.getTitle());
        tv_product_totle_price.setText("¥" + orderDetail.getIndividual_price() + "");
        tv_jijin_money.setText("-¥" + (orderDetail.getIndividual_price() - orderDetail.getTotal_price()) + ".00");
        tv_totle_price.setText("¥" + orderDetail.getIndividual_price() + "");
        tv_bottom_price.setText("¥" + orderDetail.getTotal_price() + "");


        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            creat_time = simpleDateFormat.parse(orderDetail.getCreateTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        current_time = System.currentTimeMillis();
        end_time = creat_time + 1000 * 60 * 15;
        if (current_time < end_time) {//位超时显示倒计时
            timer = new MyCountDownTimer(end_time - current_time, 1000);
            timer.start();
        } else {//进来时就已经超时
            tv_left_time.setText("超过时间未支付，系统已自动取消该订单");
            tv_order_staus.setText("订单状态：已超时");
            tv_cancel.setClickable(false);
            tv_cancel.setVisibility(View.INVISIBLE);
            rv_show_zhifu.setVisibility(View.GONE);
            tv_pay_now.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_TO_ADDRESS) { //// TODO: 2016/12/26 结果码也要判断
            rv_if_address_not_choosed.setVisibility(View.GONE);
            rv_if_address_choosed.setVisibility(View.VISIBLE);
            tv_name.setText(data.getStringExtra("name"));
            tv_name.setText(data.getStringExtra("phone_num"));
            tv_name.setText(data.getStringExtra("address"));
            SharedPreferences.Editor editor = share_address.edit();
            editor.putString("name", data.getStringExtra("name"));
            editor.putString("phone_num", data.getStringExtra("phone_num"));
            editor.putString("address", data.getStringExtra("address"));
            editor.commit();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_order_back:
                onBackPressed();
                break;
            case R.id.tv_order_cancel:
                if (orderDetail != null) {
                    NetWorkRequest.postCancelOrder(this, token,orderDetail.getOrder_id(), new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            tv_cancel.setClickable(false);
                            tv_cancel.setVisibility(View.INVISIBLE);
                            tv_order_staus.setVisibility(View.GONE);
                            tv_left_time.setVisibility(View.GONE);
                            tv_order_canceled.setVisibility(View.VISIBLE);
                            rv_show_zhifu.setVisibility(View.GONE);
                            tv_pay_now.setVisibility(View.GONE);

                        }

                        @Override
                        public void fail(String result) {

                        }
                    });


                }
                break;
            case R.id.tv_order_submitorder:
                //// TODO: 2016/12/26 提交订单 付款  暂时做不了 提交之前先判断选的是支付宝还是微信支付
                if (rb_alipy.isChecked()) {
                    Toast.makeText(this, "提交订单，您选择的是支付宝支付", Toast.LENGTH_SHORT).show();
                } else if (rb_wechat_pay.isChecked()) {
                    Toast.makeText(this, "提交订单，您选择的是微信支付", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }


    class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_left_time.setText("请在" + millisUntilFinished / 1000 / 60 + "分" + (millisUntilFinished / 1000) % 60 + "秒内完成支付");
        }

        @Override
        public void onFinish() {
            tv_left_time.setText("超过时间未支付，系统已自动取消该订单");
            tv_order_staus.setText("订单状态：已超时");
            tv_cancel.setClickable(false);
            tv_cancel.setVisibility(View.INVISIBLE);
            rv_show_zhifu.setVisibility(View.GONE);
            tv_pay_now.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {//activity销毁时执行取消定时器
            timer.cancel();
        }
        super.onDestroy();
    }
}
