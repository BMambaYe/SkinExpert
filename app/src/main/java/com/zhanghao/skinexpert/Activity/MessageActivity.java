package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

public class MessageActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_setting;
    private TextView tv_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        initView();
        /**
         * 设置监听
         */
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);

            }
        });
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Message2Activity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 对view进行初始化
     */
    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_setting= (TextView) findViewById(R.id.tv_setting);
        tv_message= (TextView) findViewById(R.id.tv_message);
    }
}
