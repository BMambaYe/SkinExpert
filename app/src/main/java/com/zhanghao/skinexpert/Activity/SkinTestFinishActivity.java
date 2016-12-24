package com.zhanghao.skinexpert.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhanghao.skinexpert.MainActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.TestResultBean;
import com.zhanghao.skinexpert.beans.TotalQuestionBean;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.JsonAnalysisFromAssets;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SkinTestFinishActivity extends AppCompatActivity {
    private LinearLayout btnOilyTest;
    private LinearLayout btnToleranceTest;
    private LinearLayout btnPigmentTest;
    private LinearLayout btnCompactTest;
    private LinearLayout btnAge;
    private TextView txtOily;
    private TextView txtTolerance;
    private TextView txtPigment;
    private TextView txtCompact;
    private TextView txtAge;
    private Button btnBack;
    private Button btnTestDescription;
    private Button btnFinish;
    private Context context;
    private SharedPreferences sp;
    private List<TotalQuestionBean> totalQuestions;
    private List<TestResultBean> testResults;
    private String[] ss =new String[4];
    private MyApplication application;
    private List<List<String>> listStrings = new ArrayList<>();
    private AlertDialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_finish);
        context = SkinTestFinishActivity.this;
        initData();
        initView();
        setOnClick();

    }

    private void initData() {
        application = (MyApplication) getApplication();
        totalQuestions =JsonAnalysisFromAssets.analysisJson(context);

        sp = getSharedPreferences("testresult",MODE_PRIVATE);
        int code1 = sp.getInt(Constant.USERNAME+"0",0);
        int code2 = sp.getInt(Constant.USERNAME+"1",0);
        int code3 = sp.getInt(Constant.USERNAME+"2",0);
        int code4 = sp.getInt(Constant.USERNAME+"3",0);
        int[] codes ={code1,code2,code3,code4};
        Log.i("RockTest:","codes:"+code1+"-"+code2+"-"+code3+"-"+code4);
        for (int i = 0; i <totalQuestions.size() ; i++) {
            testResults = totalQuestions.get(i).getResults();
            List<String> list = new ArrayList<>();
            for (int j = 0; j <testResults.size() ; j++) {
                String s = testResults.get(j).getTitle();
                String title = s.substring(0,s.indexOf("皮肤"));
                list.add(title);
                if (testResults.get(j).getSkinCodeChar()==codes[i]){
                    ss[i]=title;
                }
            }
            listStrings.add(list);
        }
    }
    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_test_finish_btn_back);
        btnTestDescription = (Button) findViewById(R.id.skin_test_finish_btn_decribe);
        btnFinish = (Button) findViewById(R.id.skin_test_finish_btn);
        btnOilyTest = (LinearLayout) findViewById(R.id.skin_test_finish_item1);
        btnToleranceTest = (LinearLayout) findViewById(R.id.skin_test_finish_item2);
        btnPigmentTest = (LinearLayout) findViewById(R.id.skin_test_finish_item3);
        btnCompactTest = (LinearLayout) findViewById(R.id.skin_test_finish_item4);
        btnAge= (LinearLayout) findViewById(R.id.skin_test_finish_item5);
        txtOily = (TextView) findViewById(R.id.skin_test_oily);
        txtTolerance = (TextView) findViewById(R.id.skin_test_tolerance);
        txtPigment = (TextView) findViewById(R.id.skin_test_pigment);
        txtCompact = (TextView) findViewById(R.id.skin_test_compact);
        txtAge = (TextView) findViewById(R.id.skin_test_age);

        setView();

    }

    private void setView() {
        txtOily.setText(ss[0]);
        txtTolerance.setText(ss[1]);
        txtPigment.setText(ss[2]);
        txtCompact.setText(ss[3]);
        txtAge.setText(application.getAge()+"");
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnTestDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDesciption = new Intent(context,SkinTestDescriptionShow.class);
                startActivity(intentToDesciption);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor= sp.edit();
                editor.putBoolean("isFinshed",true);
                editor.commit();
               Intent intent  = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnOilyTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowButtomAuto(context,listStrings.get(0),0);
            }
        });

        btnToleranceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowButtomAuto(context,listStrings.get(1),1);
            }
        });
        btnPigmentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowButtomAuto(context,listStrings.get(2),2);
            }
        });
        btnCompactTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowButtomAuto(context,listStrings.get(3),3);
            }
        });

        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Widget_DeviceDefault_DatePicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar mycalendar = Calendar.getInstance();//获取现在时间
                                int  nowyear = mycalendar.get(Calendar.YEAR);//获取年份
                                int age = nowyear - year;
                                if (age<=0){
                                    age=1;
                                }
                                txtAge.setText(age+"");
                                application.setAge(age);
                            }
                        },2000,01,01);
                    datePickerDialog.show();
            }
        });
    }
    /**
     * 底部弹窗,设置布局为ListView
     */
    public void dialogShowButtomAuto(final Context context, List<String> strings, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        myDialog = builder.create();
        myDialog.show();
        Window window =myDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popupAnimation);
        View view = View.inflate(context,R.layout.dialog_show_buttom_auto_view,null);
        Button btnToTest = (Button) view.findViewById(R.id.dialog_show_auto_test);
        Button btnCancel = (Button) view.findViewById(R.id.dialog_show_auto_btn_cancel);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_show_auto_ll);
        ListView listView = new ListView(context);

        float density = getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) (strings.size()*50*density));
        listView.setLayoutParams(params);
        linearLayout.addView(listView);
        MyAdapter adapter = new MyAdapter(context,strings,id);
        listView.setAdapter(adapter);
        //重新进入测试
        btnToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setIndex(0);
                application.setIndexType(id);
                Intent intent = new Intent(context,SkinTestPageQuestion.class);
                startActivity(intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        window.setContentView(view);
        //设置横向全屏
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private List<String> strings;
        private int id;
        public MyAdapter(Context context, List<String> strings,int id) {
            this.context = context;
            this.strings = strings;
            this.id =id;
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int index= position;
            convertView = LayoutInflater.from(context).inflate(R.layout.dialog_show_buttom_auto_lv_item,parent,false);
            TextView textView = (TextView) convertView.findViewById(R.id.dialog_show_auto_lv_txt);
            textView.setText(strings.get(position));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (id){
                        case 0:
                            txtOily.setText(strings.get(index));
                            myDialog.dismiss();
                            break;
                        case 1:
                            txtTolerance.setText(strings.get(index));
                            myDialog.dismiss();
                            break;
                        case 2:
                            txtPigment.setText(strings.get(index));
                            myDialog.dismiss();
                            break;
                        case 3:
                            txtCompact.setText(strings.get(index));
                            myDialog.dismiss();
                            break;
                    }
                }
            });
            return convertView;
        }
    }
}
