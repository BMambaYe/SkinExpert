package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.DataCleanManager;
import com.zhanghao.skinexpert.utils.Dialog;

public class MySettingActivity extends AppCompatActivity {
    private Button btnBack;
    private Context context;
    private TextView txtCache;
    private String currentCache;
    private LinearLayout btnClearCache,btnCurrentVersion,btnMySupport,btnNotificationSet
            ,btnAccountBind,btnLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        context = MySettingActivity.this;
        inintView();
        initData();
        setOnClick();
    }

    private void initData() {
        try {
            currentCache = DataCleanManager.getTotalCacheSize(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //清除缓存
        btnClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache(context);
                try {
                   String  cache = DataCleanManager.getTotalCacheSize(context);
                    if (cache!=null){
                        txtCache.setText(cache);
                        Toast.makeText(context, "清除成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //我赞过的
        btnMySupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyLikeAct = new Intent(context,MyLikeActivity.class);
                startActivity(intentToMyLikeAct);
                //finish();
            }
        });
        //通知
        btnNotificationSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNotificationSetAct = new Intent(context,NotificationSettingActivity.class);
                startActivity(intentToNotificationSetAct);
            }
        });
        //账号绑定
        btnAccountBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAccountBindAct = new Intent(context,AccountBindActivity.class);
                startActivity(intentToAccountBindAct);
            }
        });
        //退出登录
        btnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.dialogShowButtom(context);
            }
        });
    }

    private void inintView() {
        btnBack = (Button) findViewById(R.id.my_setting_btn_back);
        btnClearCache = (LinearLayout) findViewById(R.id.my_setting_btn_clearcache);
        btnCurrentVersion = (LinearLayout) findViewById(R.id.my_setting_btn_currentversion);
        btnMySupport = (LinearLayout) findViewById(R.id.my_setting_btn_mysupport);
        btnNotificationSet = (LinearLayout) findViewById(R.id.my_setting_btn_notifyset);
        btnAccountBind = (LinearLayout) findViewById(R.id.my_setting_btn_bindaccount);
        btnLoginOut = (LinearLayout) findViewById(R.id.my_setting_btn_loginout);
        txtCache = (TextView) findViewById(R.id.my_setting_cache_show);
        if (currentCache!=null&&!"".equals(currentCache)){
            txtCache.setText(currentCache );
        }
    }
}
