package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.MainActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RegisterUseData;
import com.zhanghao.skinexpert.utils.ActivityCollector;
import com.zhanghao.skinexpert.utils.EncryptHelper;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
    private RegisterUseData.DataBean.UserDataBean userData;
    private String token;
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
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sp.edit();
        accountSP = sp.getString("phone",null);
        pwdSP = sp.getString("pwd",null);
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
                if (account!=null&&!"".equals(account)&pwd!=null&&!"".equals(pwd)){
                    //MD5加密
                    String result = MD5Encryption(pwd);
                    NetWorkRequest.AccountLogin(context, account, result, new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                            JSONObject jsonObject = (JSONObject) result;
                            try {
                                if ("成功".equals(jsonObject.getString("message"))){
                                    setJsonDATA(jsonObject);
                                    editor.putString("token", token);
                                    editor.putString("nick", userData.getNick());
                                    editor.putString("skinCode", userData.getSkinCode());
                                    editor.putString("skinType", userData.getSkinType());
                                    editor.commit();
                                    ActivityCollector.finishAll();
                                    Intent intentToMainAct = new Intent(context, MainActivity.class);
                                    intentToMainAct.putExtra("isToFragmentMe", true);

                                    startActivity(intentToMainAct);
                                    finish();
                                }else {
                                    Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        @Override
                        public void fail(String result) {
                            Toast.makeText(MyLoginAcitvity.this, result, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {

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
    //给密码加密
    private String MD5Encryption(String pwd) {
        String result = EncryptHelper.md5Encode(pwd);
        return result;
    }
    //json数据解析
    private void setJsonDATA(JSONObject jsonObject) {
        userData= new RegisterUseData.DataBean.UserDataBean();
        JSONObject jsonObjectData = null;
        try {
            jsonObjectData = jsonObject.getJSONObject("data");
            token = jsonObjectData.getString("userToken");
            JSONObject jsonUserData = jsonObjectData.getJSONObject("userData");
            userData.setUid((Integer) jsonUserData.get("uid")) ;
            userData.setGender(jsonObjectData.getString("gender"));
            userData.setNick(jsonObjectData.getString("nick"));
            userData.setMobile(jsonObjectData.getString("mobile"));
            userData.setCommunityCategoryAttentionData(jsonObjectData.getString("communityCategoryAttentionData"));
            userData.setSkinCode(jsonObjectData.getString("skinCode"));
            userData.setSkinType(jsonObjectData.getString("skinType"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
