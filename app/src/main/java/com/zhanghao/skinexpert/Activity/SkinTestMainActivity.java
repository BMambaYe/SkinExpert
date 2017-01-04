package com.zhanghao.skinexpert.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SkinTestMainActivity extends AppCompatActivity {
    public static SkinTestMainActivity instanceTestMainActivity  =null;
    private Button btnBack;
    private TextView btnDataSelect;
    private TextView btnNextStep;
    private Context context;
    private DatePickerDialog datePickDialog;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private int age=-1;
    private MyApplication application;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String birthDay;
    private String token;
    private String skinCode;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_main);
        instanceTestMainActivity=this;
        context = SkinTestMainActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        token = sp.getString("token",token);

        editor = sp.edit();

        skinCode = sp.getString("skinCode",null);

        birthDay = sp.getString("birthday",null);
        application = (MyApplication) getApplication();
        application.setIndexType(skinCode.indexOf("-"));
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

                        final String birthday = year + "-" + month + "-" + dayOfMonth;
                        final int age =setAgeText(year,month,dayOfMonth);
                        NetWorkRequest.updateUserInfo(context, token, "birthday",birthday , new NetWorkRequest.RequestCallBack() {
                            @Override
                            public void success(Object result) {

                                JSONObject jsonObject = (JSONObject) result;
                                try {
                                    if ("成功".equals(jsonObject.getString("message"))){
                                        editor.putString("birthday",birthday);
                                        editor.putString("age", String.valueOf(age));
                                        editor.commit();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void fail(String result) {

                            }
                        });
                    }
                },2000,01,10);
                datePickDialog.show();
            }
        });
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToTestPage1 = new Intent(context,SkinTestPageTitleActivity.class);
                startActivity(intentToTestPage1);
            }
        });
    }
    private int setAgeText(int year, int month, int dayOfMonth) {
        Calendar mycalendar = Calendar.getInstance();//获取现在时间
        int  nowyear = mycalendar.get(Calendar.YEAR);//获取年份
        int age = nowyear - year;
        if (age<=0){
            age=1;
        }
        btnDataSelect.setText(age+"");
        application.setAge(age);
        return age;
    }

    private void initView() {
        btnBack= (Button) findViewById(R.id.skin_test_main_btn_back);
        btnDataSelect = (TextView) findViewById(R.id.skin_test_main_date_pick);
        btnNextStep = (TextView) findViewById(R.id.skin_test_main_nextstep);
        if (birthDay!=null){
            int year = Integer.parseInt(birthDay.substring(0,birthDay.indexOf("-")));
            int month = Integer.parseInt(birthDay.substring(birthDay.indexOf("-")+1,birthDay.lastIndexOf("-")));
            int dayofMonth = Integer.parseInt(birthDay.substring(birthDay.lastIndexOf("-")+1,birthDay.length()));
            setAgeText(year,month,dayofMonth);
        }else {
            btnDataSelect.setText("请选择年龄");
        }
    }
}
