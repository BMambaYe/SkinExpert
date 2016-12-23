package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhanghao.skinexpert.R;

public class NotificationMsgListActivity extends AppCompatActivity {
    private Button btnBack,btnNotificationSetting;
    private LinearLayout btnSkinMsg;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_msglist);
        context = NotificationMsgListActivity.this;
        initView();
        setOnClick();
    }

    private void setOnClick() {
        //点击返回到上一个界面
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //通知设置
        btnNotificationSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO
                Intent intentToNotficationSetAct = new Intent(context,NotificationSettingActivity.class);
                startActivity(intentToNotficationSetAct);
            }
        });
        //消息详情
        btnSkinMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO
            }
        });
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.notification_msglist_btn_back);
        btnNotificationSetting = (Button) findViewById(R.id.notification_msglist_setting);
        btnSkinMsg = (LinearLayout) findViewById(R.id.notification_msglist_skin_btn);
    }
}
