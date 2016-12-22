package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zhanghao.skinexpert.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettingActivity extends AppCompatActivity {
    private Button btnBack;
    private ListView listView;
    private Context context;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);
        context = NotificationSettingActivity.this;
        initView();
        initData();
        setOnClick();
    }

    private void initData() {
        for (int i = 0; i <4 ; i++) {
            datas.add("this+"+i);
        }
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.notification_setting_btn_back);
        listView = (ListView) findViewById(R.id.notification_setting_lv);

    }


}
