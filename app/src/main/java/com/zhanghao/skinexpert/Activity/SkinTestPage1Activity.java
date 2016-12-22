package com.zhanghao.skinexpert.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

import java.util.Calendar;

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

        btnDataSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar mycalendar = Calendar.getInstance();//获取现在时间
                        int  nowyear = mycalendar.get(Calendar.YEAR);//获取年份
                        int age = nowyear - year;
                        if (age<=0){
                            age=1;
                        }
                        btnDataSelect.setText(age+"");

                    }
                },2000,1,1);
                datePickDialog.show();
            }

        });
    }

    private void initView() {
        btnBack= (Button) findViewById(R.id.skin_test_page1_btn_back);
        btnNextStep = (TextView) findViewById(R.id.skin_test_page1_nextstep);
    }
}
