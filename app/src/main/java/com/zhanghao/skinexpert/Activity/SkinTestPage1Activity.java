package com.zhanghao.skinexpert.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

public class SkinTestPage1Activity extends AppCompatActivity {
    private Button btnBack;
    private TextView btnDataSelect;
    private TextView btnNextStep;
    private Context context;
    private DatePickerDialog datePickDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_page1);
        context = SkinTestPage1Activity.this;
        initView();
        setOnClick();
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
        btnBack= (Button) findViewById(R.id.skin_test_page1_btn_back);
        btnNextStep = (TextView) findViewById(R.id.skin_test_page1_nextstep);
    }
}
