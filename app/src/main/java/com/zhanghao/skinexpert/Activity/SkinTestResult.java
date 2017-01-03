package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.TestResultBean;
import com.zhanghao.skinexpert.beans.TotalQuestionBean;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.Dialog;
import com.zhanghao.skinexpert.utils.JsonAnalysisFromAssets;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import net.ugen.ugenframework.Callback;

import java.util.List;

import static com.zhanghao.skinexpert.Activity.SkinTestPageQuestion.instanceTestPageQuestion;
import static com.zhanghao.skinexpert.Activity.SkinTestPageTitleActivity.instanceTestPageTitle;

public class SkinTestResult extends AppCompatActivity {
    public static SkinTestResult instanceTestResult =null;
    private Button btnBack;
    private TextView txtTitle;
    private Button btnRetest;
    private TextView txtSkinType;
    private TextView txtSkinDeatil;
    private Button btnNextStep;
    private Context context;
    private int index;
    private int indexType;
    private MyApplication application ;
    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase db;
    private String[] testType = {"干性/油性测试","敏感/耐受性测试","色素/非色素性测试","易皱纹/紧致测试"};
    private int score;
    private SharedPreferences spuserinfo;
    private String skinCode;
    private String token;
    private int newCode;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_result);
        instanceTestResult=this;
        context = SkinTestResult.this;
        initData();
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
        btnRetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.dialogShow(context, null, "是否重新开始测试?", "确定", "取消", new Callback.AlertCallBack() {
                    @Override
                    public void onOkClick() {
                        //重新跳转至测试界面
                        application.setIndex(0);
                        Intent intentToSkinTest = new Intent(context,SkinTestPageQuestion.class);
                        startActivity(intentToSkinTest);
                        finish();
                    }

                    @Override
                    public void onCancelClick() {
                    }
                });
            }
        });

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexType<3){
                    application.setIndex(0);
                    application.setIndexType(indexType+1);
                    instanceTestPageQuestion.finish();
                    instanceTestPageTitle.finish();
                    Intent intentToTestPage= new Intent(context,SkinTestPageTitleActivity.class);
                    startActivity(intentToTestPage);
                    finish();
                }else if(indexType==3){
                    //TODO
                    //跳转到测试完成界面
                    Intent intentToResultDescribe = new Intent(context,SkinTestResultDescription.class);
                    startActivity(intentToResultDescribe);
                    finish();
                    Toast.makeText(context, "跳转到测试完成界面", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void initData() {

        spuserinfo = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = spuserinfo.edit();
        token = spuserinfo.getString("token",null);
        skinCode = spuserinfo.getString("skinCode",null);
        application= (MyApplication) getApplication();
        indexType = application.getIndexType();
        index = application.getIndex();
        sqliteHelper = new SQLiteHelper(context);
        db =sqliteHelper.getReadableDatabase();
        //检索数据库
        searchDB();
    }

    private void searchDB() {
        //查询表一
        Cursor cursor = db.query(Constant.DB_SKIN_TEST_INFO,null,null,null,null,null,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                if (cursor.getString(cursor.getColumnIndex("username")).equals("Rock")
                        &&cursor.getInt(cursor.getColumnIndex("indexType"))==indexType){
                    score+=cursor.getInt(cursor.getColumnIndex("score"));
                }
            }
        }
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_test_result_btn_back);
        btnRetest = (Button) findViewById(R.id.skin_test_result_btn_retest);
        btnNextStep = (Button) findViewById(R.id.skin_test_result_btn_next);
        txtTitle = (TextView) findViewById(R.id.skin_test_result_title);
        txtSkinDeatil = (TextView) findViewById(R.id.skin_test_result_detail);
        txtSkinType = (TextView) findViewById(R.id.skin_test_result_type);
        setShowView();
    }

    private void setShowView() {
        int scoreTotal =score;
        List<TotalQuestionBean> totalQuestions = JsonAnalysisFromAssets.analysisJson(context);
        List<TestResultBean> testResults = totalQuestions.get(indexType).getResults();
        for (int i = 0; i <testResults.size() ; i++) {
            int minscore = testResults.get(i).getMinScore();
            int maxscore = testResults.get(i).getMaxScore();
            String title = testResults.get(i).getTitle();
            String content = testResults.get(i).getContent();
            int code= testResults.get(i).getSkinCodeChar();
            if (scoreTotal>=minscore&&scoreTotal<=maxscore){
                txtSkinType.setText(title);
                txtSkinDeatil.setText(content);
                txtTitle.setText(testType[indexType]);
                newCode = code;
            }
        }
        StringBuffer sb = new StringBuffer(skinCode);
        sb.replace(indexType,indexType+1,newCode+"");
        NetRequest(sb.toString());

    }

    private void NetRequest(final String s) {
        NetWorkRequest.updateUserInfo(context, token, "skinCode", s, new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                editor.putString("skinCode",s);
                editor.commit();
            }
            @Override
            public void fail(String result) {

            }
        });
    }
}
