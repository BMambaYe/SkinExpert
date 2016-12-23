package com.zhanghao.skinexpert.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.QuestionBean;
import com.zhanghao.skinexpert.beans.TotalQuestionBean;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.JsonAnalysisFromAssets;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 干性/油性测试
 */
public class SkinTestPageQuestion extends AppCompatActivity {
    private Button btnLastTopic;
    private Button btnLoginOut;
    private TextView txtTopicIndex;
    private TextView txtTopicTitle;
    private LinearLayout linearLayout;
    private List<TotalQuestionBean> totalQuestions = new ArrayList<>();
    private Context context;
    private String titleHead;
    private String titleTop;
    private int index;
    private int indextype;
    private SQLiteHelper sqLiteHelper;
    private MyApplication myApplication ;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_test_page_oily);
        myApplication = (MyApplication) getApplication();
        context =SkinTestPageQuestion.this;
        initData();
        initView();
        setOnClick();
    }

    //初始化数据
    private void initData() {
        //json解析
        totalQuestions=JsonAnalysisFromAssets.analysisJson(context);
        indextype = myApplication.getIndexType();
        index = myApplication.getIndex();
        titleHead = totalQuestions.get(0).getTitle();
        titleTop = totalQuestions.get(0).getQuestions().get(0).get(0).toString();

        sqLiteHelper =new SQLiteHelper(context);
        db= sqLiteHelper.getReadableDatabase();
    }
    //json解析，得到数据集合


    private void initView() {
        btnLastTopic = (Button) findViewById(R.id.oily_test_last_topic);
        btnLoginOut = (Button) findViewById(R.id.oily_test_login_out);
        txtTopicIndex = (TextView) findViewById(R.id.oily_test_topic_index);
        linearLayout = (LinearLayout) findViewById(R.id.oily_test_ll);
        txtTopicTitle = (TextView) findViewById(R.id.oily_test_total_title);
        setLayoutButtom();
    }

    //设置动态布局
    private void setLayoutButtom() {
        txtTopicTitle.setText(totalQuestions.get(indextype).getTitle());
        txtTopicIndex.setText(index+1+"/"+totalQuestions.get(indextype).getQuestions().size());
        if (index!=0){
            btnLastTopic.setVisibility(View.VISIBLE);
        }else {
            btnLastTopic.setVisibility(View.INVISIBLE);
        }
        float density = getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(context).inflate(R.layout.test_down_view_layout,null);
        view.setLayoutParams(layoutParams);
        TextView textViewTitle = (TextView) view.findViewById(R.id.txt_question_title);
        ListView listView = (ListView) view.findViewById(R.id.lv_question);
        titleTop = totalQuestions.get(indextype).getQuestions().get(index).get(0).toString();
        textViewTitle.setText(titleTop);
        List<Object> question = totalQuestions.get(indextype).getQuestions().get(index);
        MyAdapter adapter = new MyAdapter(question);
        listView.setAdapter(adapter);
        linearLayout.addView(view);

    }
    class MyAdapter extends BaseAdapter{
        private List<Object> question;

        public MyAdapter(List<Object> questions) {
            this.question = questions;
        }

        @Override
        public int getCount() {
            return question.size()-1;
        }

        @Override
        public Object getItem(int position) {
            QuestionBean questionBean = (QuestionBean) question.get(position+1);
            return questionBean.getTitle();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(R.layout.lv_question_item,null);
            final TextView textView = (TextView) convertView.findViewById(R.id.lv_question_item_txt);
            final QuestionBean questionBean = (QuestionBean) question.get(position+1);
            textView.setText(questionBean.getTitle().toString());
            //检索数据库，查看是否以及测过
            Cursor cursor = db.query(Constant.DB_SKIN_TEST_INFO,null,null,null,null,null,null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    if (cursor.getString(cursor.getColumnIndex("username")).equals(Constant.USERNAME)
                            &&cursor.getInt(cursor.getColumnIndex("indexType"))==indextype&&
                            cursor.getInt(cursor.getColumnIndex("index_q"))==index){
                            if (cursor.getInt(cursor.getColumnIndex("status"))==position){
                                textView.setTextColor(getResources().getColor(R.color.red));
                        }
                    }
                }

            }
            cursor.close();
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(getResources().getColor(R.color.red));
                    //修改数据库信息
                    String score =questionBean.getScore();
                    int status = position;
                    setDBSkinTest(score,status);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resetView();
                                }
                            });
                        }
                    }).start();
                }
            });
            return convertView;
        }
    }
    //将数据保存至数据库
    private void setDBSkinTest(String score,int status) {
        boolean flag = true;
        Cursor cursor = db.query(Constant.DB_SKIN_TEST_INFO,null,null,null,null,null,null);
        ContentValues values = new ContentValues();
        values.put("username","Rock");
        values.put("index_q",index);
        values.put("indexType",indextype);
        values.put("score",score);
        values.put("status",status);
        if (cursor!=null){
            while (cursor.moveToNext()){
                if (cursor.getString(cursor.getColumnIndex("username")).equals(Constant.USERNAME)
                        &&cursor.getInt(cursor.getColumnIndex("indexType"))==indextype&&
                        cursor.getInt(cursor.getColumnIndex("index_q"))==index){
                    String _id = cursor.getString(cursor.getColumnIndex("_id"));
                    db.update(Constant.DB_SKIN_TEST_INFO,values,"_id=?",new String[]{_id});
                    flag =false;
                }
            }

        }
        cursor.close();
        if (flag){
            db.insert(Constant.DB_SKIN_TEST_INFO,null,values);
        }


    }

    //重置视图
    private void resetView() {
        index++;

        if (index==totalQuestions.get(indextype).getQuestions().size()){
            Intent intentToTestResult = new Intent(context,SkinTestResult.class);
            startActivity(intentToTestResult);
            indextype++;
            index=0;
            if (indextype==totalQuestions.size()){
                //TODO

            }
        }else {
            myApplication.setIndex(index);
            linearLayout.removeAllViews();
            setLayoutButtom();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("RockTest:","onRestart");
    }


    private void setOnClick() {
        btnLastTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                myApplication.setIndex(index);
                if (index==0){
                    btnLastTopic.setVisibility(View.INVISIBLE);
                    txtTopicIndex.setText(index+1+"/"+totalQuestions.get(indextype).getQuestions().size());
                }
                linearLayout.removeAllViews();
                setLayoutButtom();

            }
    });
        btnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
