package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.LocationDataBean;

public class ChangeLocationActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnSave;
    private EditText editName;
    private EditText editPhoneNum;
    private EditText editDetails;
    private EditText editIDCard;
    private TextView btnLocationSelect;
    private TextView txtTitle;
    private Context context;
    private CityPicker cityPicker;
    private LocationDataBean locationDataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);
        context = ChangeLocationActivity.this;
        initView();
        setOnClick();
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyLocationAct = new Intent(context,MyLoactionActivity.class);
                startActivity(intentToMyLocationAct);
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyLocationAct = new Intent(context,MyLoactionActivity.class);
                //将修改好的数据传入后台，（是否传入地址列表界面？）
                startActivity(intentToMyLocationAct);
                finish();
            }
        });
        //点击跳出弹窗用于选择省、市、区
        btnLocationSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker = new CityPicker.Builder(context)
                        .textSize(20)
                        .title("    ")
                        .titleBackgroundColor("#ffd6d7d7")
                        .backgroundPop(0xa0000000)
                        .confirTextColor("#F73F52")
                        .cancelTextColor("#8a000000")
                        .province("北京市")
                        .district("东城区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... strings) {
                        //省份
                        String province = strings[0];
                        //城市
                        String city = strings[1];
                        //区，县
                        String district =strings[2];
                        if (province.equals(city)){
                            city="";
                        }
                        btnLocationSelect.setText(province+" "+city+" "+district);
                    }
                });
            }
        });
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.change_location_btn_back);
        btnLocationSelect = (TextView) findViewById(R.id.change_location_select_location);
        btnSave = (Button) findViewById(R.id.change_location_btn_save);
        editName = (EditText) findViewById(R.id.change_location_edit_name);
        editPhoneNum = (EditText) findViewById(R.id.change_location_edit_phone);
        editDetails = (EditText) findViewById(R.id.change_location_edit_detail);
        editIDCard = (EditText) findViewById(R.id.change_location_edit_idcard);
        txtTitle = (TextView) findViewById(R.id.change_location_title);
        String title = getIntent().getStringExtra("title");
        if (title!=null&&!"".equals(title)){
            txtTitle.setText(title);
        }
    }
}
