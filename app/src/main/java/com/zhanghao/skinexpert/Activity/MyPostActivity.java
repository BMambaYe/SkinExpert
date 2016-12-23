package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhanghao.skinexpert.R;

public class MyPostActivity extends AppCompatActivity {
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        initView();
        initOnClick();
    }

    private void initOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.my_post_btn_back);
    }
}
