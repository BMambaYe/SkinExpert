package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;

public class SkinTestPageTitleActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView btnDataSelect;
    private TextView btnNextStep;
    private TextView txtTestType;
    private TextView txtDescribe;
    private Context context;
    private int indexType;

    private MyApplication application ;
    private String[] testType = {"干性/油性测试","敏感/耐受性测试","色素/非色素性测试","易皱纹/紧致测试"};
    private String[] testdescribe=new String[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_page1);
        context = SkinTestPageTitleActivity.this;
        application = (MyApplication) getApplication();
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        indexType = application.getIndexType();
        testdescribe[0] = getString(R.string.skin_test_question2_describe1);
        testdescribe[1] = getString(R.string.skin_test_question2_describe2);
        testdescribe[2] = getString(R.string.skin_test_question2_describe3);
        testdescribe[3] = getString(R.string.skin_test_question2_describe4);
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOilyTest = new Intent(context,SkinTestPageQuestion.class);
                startActivity(intentToOilyTest);
                finish();
            }
        });

    }

    private void initView() {
        btnBack= (Button) findViewById(R.id.skin_test_page1_btn_back);
        btnNextStep = (TextView) findViewById(R.id.skin_test_page1_nextstep);
        txtTestType = (TextView) findViewById(R.id.skin_test_page_type);
        txtDescribe = (TextView) findViewById(R.id.skin_test_page_describe);
        txtTestType.setText(testType[indexType]);
        txtDescribe.setText(testdescribe[indexType]);
    }
}
