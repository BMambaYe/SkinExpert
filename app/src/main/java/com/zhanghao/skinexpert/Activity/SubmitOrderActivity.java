package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.view.ScrollPickerView;
import com.zhanghao.skinexpert.view.StringScrollPicker;

import java.util.Arrays;

public class SubmitOrderActivity extends AppCompatActivity {

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
    private Intent intent;
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
    private String buyout_id;
    private int credit = 0;
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        token= ((MyApplication) getApplication()).getToken();
        intent = getIntent();
        pic_url = intent.getStringExtra("img");
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        buyout_id = intent.getStringExtra("buyout_id");
        initView();
        bindData();
        judeAddress();

    }

    private void judeAddress() {
        share_address = getSharedPreferences("address", Context.MODE_PRIVATE);
        String tempname = share_address.getString("name", "");
        if (tempname.equals("")) {
            rv_if_address_choosed.setVisibility(View.GONE);
        } else {
            rv_if_address_not_choosed.setVisibility(View.GONE);
            tv_name.setText(share_address.getString("name", ""));
            tv_name.setText(share_address.getString("phone_num", ""));
            tv_name.setText(share_address.getString("address", ""));
        }
    }

    private void bindData() {
        Picasso.with(this).load(pic_url).into(img_product_pic);
        tv_product_title.setText(title);
        tv_product_price.setText("¥" + price);
        tv_product_totle_price.setText("+¥" + price);
        tv_bottom_price.setText("¥" + price);
        tv_totle_price.setText("¥" + price);
    }

    private void initView() {

        tv_name = ((TextView) findViewById(R.id.tv_submit_order_name));
        tv_phone_num = ((TextView) findViewById(R.id.tv_submit_order_phonenum));
        tv_address = ((TextView) findViewById(R.id.tv_submitorder_address));
        rv_if_address_choosed = ((RelativeLayout) findViewById(R.id.rv_sublmit_order_if_choosed));
        rv_if_address_not_choosed = ((RelativeLayout) findViewById(R.id.rv_sublmit_order_if_not_choosed));
        ll_show_address = ((LinearLayout) findViewById(R.id.ll_submit_order_show_address));//设置点击事件
        ll_show_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitOrderActivity.this, MyLoactionActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TO_ADDRESS);
            }
        });

        img_product_pic = ((ImageView) findViewById(R.id.img_submit_order_product_pic));
        tv_product_title = ((TextView) findViewById(R.id.tv_submit_order_product_title));
        tv_product_price = (TextView) findViewById(R.id.tv_submit_order_price);
        tv_product_totle_price = ((TextView) findViewById(R.id.tv_submit_order_product_total_price));
        tv_totle_price = ((TextView) findViewById(R.id.tv_submit_order_totle_price));
        tv_bottom_price = ((TextView) findViewById(R.id.tv_submit_order_bottom_totle_price));

        //选择基金换购弹出自定义的滑动选择View
        rv_jijinhuangou = ((RelativeLayout) findViewById(R.id.rv_submit_order_jijindikou));
        rv_jijinhuangou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitOrderActivity.this);
                View view = LayoutInflater.from(SubmitOrderActivity.this).inflate(R.layout.dialog_jijin_picker, null);
                builder.setView(view);
                AlertDialog dialog = builder.show();

                initDialog(view, dialog);
            }
        });

        rb_alipy = ((RadioButton) findViewById(R.id.rb_submit_order_alipay));
        rb_wechat_pay = ((RadioButton) findViewById(R.id.rb_submit_order_wechat_pay));
    }

    private int jijin_select_position;
    private String[] jijin_select = new String[]{"(不使用基金)", "使用基金(仅剩0基金)"};

    private void initDialog(View view, final AlertDialog dialog) {
        TextView tv_quxiao = (TextView) view.findViewById(R.id.dialog_jijin_quxiao);
        TextView tv_quedin = (TextView) view.findViewById(R.id.dialog_jijin_quedin);
        StringScrollPicker str_pic = (StringScrollPicker) view.findViewById(R.id.dialog_jijin_string_picker);
        str_pic.setDisallowInterceptTouch(true);
        str_pic.setData(Arrays.asList(jijin_select));
        str_pic.setSelectedPosition(0);
        str_pic.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                jijin_select_position = position;
            }
        });

        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_quedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (jijin_select_position) {
                    //// TODO: 2016/12/26  基金暂时没法做，吐司一下
                    case 0:
                        Toast.makeText(SubmitOrderActivity.this, "您选择了不使用基金", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                    case 1:
                        Toast.makeText(SubmitOrderActivity.this, "您选择了使用基金", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
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

    private long order_num;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_submit_order_back:
                onBackPressed();
                break;
            case R.id.tv_submit_order_submitorder:
                //// TODO: 2016/12/26 提交订单  暂时做不了 提交之前先判断选的是支付宝还是微信支付
                if (rb_alipy.isChecked()) {
                    Toast.makeText(this, "提交订单，您选择的是支付宝支付", Toast.LENGTH_SHORT).show();
                } else if (rb_wechat_pay.isChecked()) {
                    Toast.makeText(this, "提交订单，您选择的是微信支付", Toast.LENGTH_SHORT).show();
                }
                NetWorkRequest.postSubmitOrder(this, token,buyout_id, credit + "", "teMai", "", new NetWorkRequest.RequestCallBack() {
                    @Override
                    public void success(Object result) {
                        Intent intent = new Intent(SubmitOrderActivity.this, OrderDetailActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void fail(String result) {

                    }
                });

                break;
            default:
                break;
        }
    }
}
