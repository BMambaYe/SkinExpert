package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.MainActivity;
import com.zhanghao.skinexpert.R;

public class MyLoginAcitvity extends AppCompatActivity {
    private Button btnBack;
    private TextView btnRegistre;
    private EditText editPhoneNum;
    private EditText editPwd;
    private TextView btnForgetPwd;
    private Button btnLogin;
    private Button btnWeiXinLogin;
    private Button btnWeiBoLogin;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String accountSP;
    private String pwdSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login_acitvity);
        context = MyLoginAcitvity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("logininfo",MODE_PRIVATE);
        editor = sp.edit();
        accountSP = sp.getString("phone",null);
        pwdSP = sp.getString("pwd",null);
        Log.i("RockTest:","测试点:"+"phone:"+accountSP+",pwd:"+pwdSP);
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.login_btn_back);
        btnRegistre = (TextView) findViewById(R.id.login_btn_register);
        editPhoneNum = (EditText) findViewById(R.id.login_edit_phone_num);
        editPwd = (EditText) findViewById(R.id.login_edit_pwd);
        btnForgetPwd = (TextView) findViewById(R.id.login_forget_pwd);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        btnWeiXinLogin = (Button) findViewById(R.id.login_weixin_login);
        btnWeiBoLogin = (Button) findViewById(R.id.login_weibo_login);
    }

    private void setOnClick() {


        //返回
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //跳转至注册界面
        btnRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(context,RegisterActivity.class);
                startActivity(intentToRegister);
                finish();
            }
        });
        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String account = editPhoneNum.getText().toString();
                 String pwd = editPwd.getText().toString();
                if (accountSP!=null&&accountSP.equals(account)
                        &&pwdSP!=null&&pwdSP.equals(pwd)){
                    editor.putBoolean("isLogin",true);
                    editor.commit();
                    Intent intentToFragmentMe = new Intent(context, MainActivity.class);
                    intentToFragmentMe.putExtra("isToFragmentMe",true);
                    startActivity(intentToFragmentMe);
                    finish();
                }else {
                    Toast.makeText(context, "请输入正确的账号或密码！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnWeiBoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微博", Toast.LENGTH_SHORT).show();
            }
        });
        btnWeiXinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "请先绑定微信", Toast.LENGTH_SHORT).show();
            }
        });
        btnForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
