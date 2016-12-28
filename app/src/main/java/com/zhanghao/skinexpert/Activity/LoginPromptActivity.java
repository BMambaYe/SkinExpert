package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;

public class LoginPromptActivity extends AppCompatActivity {
    private ImageView btnClose;
    private LinearLayout btnWeiXinLogin;
    private LinearLayout btnWeiBoLogin;
    private LinearLayout btnPhoneLogin;
    private Button btnRegister;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginPromptActivity.this;
        setContentView(R.layout.activity_login_prompt);
        initView();
        setOnClick();
    }

    private void initView() {
        btnClose = (ImageView) findViewById(R.id.login_prompt_close);
        btnWeiXinLogin = (LinearLayout) findViewById(R.id.weixin_login);
        btnWeiBoLogin = (LinearLayout) findViewById(R.id.weibo_login);
        btnPhoneLogin = (LinearLayout) findViewById(R.id.phone_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    private void setOnClick() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnWeiXinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微信", Toast.LENGTH_SHORT).show();
            }
        });
        btnWeiBoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微博", Toast.LENGTH_SHORT).show();
            }
        });
        //手机登录
        btnPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(context,MyLoginAcitvity.class);
                startActivity(intentToLogin);
                finish();
            }
        });
        //注册账号
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(context,RegisterActivity.class);
                startActivity(intentToRegister);
                finish();
            }
        });
    }
}
