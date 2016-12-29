package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;

public class RegisterActivity extends AppCompatActivity {
    private Button btnBack;
    private EditText editPhone;
    private Button btnNextStep;
    private Button btnWeiXinLogin;
    private Button btnWeiBoLogin;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context context;
    private String account;
    private String pwd;
    private String pwdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context =RegisterActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("logininfo",MODE_PRIVATE);
        editor = sp.edit();
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.register_btn_back);
        editPhone = (EditText) findViewById(R.id.register_edit_phone_num);
        btnNextStep= (Button) findViewById(R.id.register_next_step);
        btnWeiXinLogin = (Button) findViewById(R.id.register_weixin_login);
        btnWeiBoLogin = (Button) findViewById(R.id.register_weibo_login);
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==11){
                    btnNextStep.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setOnClick() {

//         pwdConfirm = editPwdConfirm.getText().toString();
        //返回
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //注册
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = editPhone.getText().toString();
                if (account!=null&&account.length()==11 &&!"".equals(account.trim())){
                    Intent intentToVerification = new Intent(context,AccountVerifyActivity.class);
                    intentToVerification.putExtra("phone",account);
                    startActivity(intentToVerification);
                    finish();

                }else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //微信登录
        btnWeiXinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微信", Toast.LENGTH_SHORT).show();
            }
        });
        //微博登录
        btnWeiBoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微博", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
