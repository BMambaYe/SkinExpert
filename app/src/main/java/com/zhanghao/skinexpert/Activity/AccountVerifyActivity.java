package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountVerifyActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnNextStep;
    private EditText editVerificationCode;
    private TextView txtReSend;
    private String phone;
    private Context context;
    private MyCountDownTimer myCountDownTimer;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verify);
        context = AccountVerifyActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code =editVerificationCode.getText().toString();
                if (code!=null&&code.trim().length()==6){

                    NetWorkRequest.verificationCheck(context, phone, code, new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            JSONObject jsonObject = (JSONObject) result;
                            try {
                                String message = jsonObject.getString("message");
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                if (message.equals("验证码有误")){

                                }else if (message.equals("成功")){
                                    Intent intentToUserInfoSet = new Intent(context,UserInfoSettingActivity.class);
                                    intentToUserInfoSet.putExtra("phone",phone);
                                    intentToUserInfoSet.putExtra("code",code);
                                    startActivity(intentToUserInfoSet);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void fail(String result) {

                        }
                    });
                }
            }
        });
            txtReSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("重新发送".equals(txtReSend.getText())){
                        sendVerificationCode();
                    }

                }
            });

    }

    private void initData() {
        myCountDownTimer = new MyCountDownTimer(60*1000,1000);
    }

    private void initView() {

        btnBack = (Button) findViewById(R.id.verification_btn_back);
        btnNextStep = (Button) findViewById(R.id.verification_next_step);
        editVerificationCode = (EditText) findViewById(R.id.verifaication_edit_verification_code);
        txtReSend = (TextView) findViewById(R.id.verifaication_resend);
        sendVerificationCode();
        editVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==6){
                    btnNextStep.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void sendVerificationCode() {
        txtReSend.setClickable(true);
        myCountDownTimer.start();
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        phone = getIntent().getStringExtra("phone");
        NetWorkRequest.phoneRequest(context, phone, new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {

            }
            @Override
            public void fail(String result) {

            }
        });
    }

    class MyCountDownTimer extends CountDownTimer{

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

        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished The amount of time until finished.
         */
        @Override
        public void onTick(long millisUntilFinished) {
            txtReSend.setTextColor(getResources().getColor(R.color.text_lighter_gray));
            txtReSend.setText("重新发送 "+millisUntilFinished/1000);
        }

        /**
         * Callback fired when the time is up.
         */
        @Override
        public void onFinish() {
            txtReSend.setText("重新发送");
            txtReSend.setTextColor(getResources().getColor(R.color.red));
            txtReSend.setClickable(true);
        }
    }
}
