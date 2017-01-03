package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class NickModifyActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView btnSave;
    private EditText editText;
    private ImageView btnDelete;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String editContent;
    private String token;
    public static final  int RESPONSE_CODE=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_modify);
        context = NickModifyActivity.this;
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sp.edit();
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        editContent = sp.getString("nick",null);
        token = sp.getString("token",null);
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.nick_modify_btn_back);
        btnDelete = (ImageView) findViewById(R.id.nick_modify_delete);
        btnSave = (TextView) findViewById(R.id.nick_modify_save);
        editText = (EditText) findViewById(R.id.nick_modify_edit);
        editText.setText(editContent);
    }

    private void setOnClick() {
        //返回
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //保存设置
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickName = editText.getText().toString();
                NetWorkRequest.updateUserInfo(context, token, "nick", nickName, new NetWorkRequest.RequestCallBack() {
                    @Override
                    public void success(Object result) {
                        JSONObject jsonObject = (JSONObject) result;
                        try {
                            if ("成功".equals(jsonObject.getString("message"))){
                                editor.putString("nick",nickName);
                                editor.commit();
                                Intent intent = new Intent();
                                intent.putExtra("nickname",nickName);
                                setResult(RESPONSE_CODE,intent);
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
                        Toast.makeText(context, "设置失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //删除编辑的内容
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空
                editText.setText("");
            }
        });
    }
}
