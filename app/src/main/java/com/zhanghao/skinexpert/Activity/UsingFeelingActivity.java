package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UsingFeelingActivity extends AppCompatActivity {

    private RatingBar rb_score;
    private EditText et_conment;
    private Intent intent;
    private int id_fromlast;
    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_feeling);
        intent = getIntent();
        id_fromlast = intent.getIntExtra("id_fromlast",0);
        rb_score = ((RatingBar) findViewById(R.id.rb_usefeeling_score));
        et_conment = ((EditText) findViewById(R.id.et_usefeeling));

        share=getSharedPreferences("isUsed",Context.MODE_PRIVATE);
        rb_score.setRating(share.getFloat("score"+id_fromlast,0));
        et_conment.setText(share.getString("comment"+id_fromlast,""));
    }

    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_usefeeling_quxiao:
                    finish();
                    break;
                case R.id.tv_usefeeling_baocun:
                    if (rb_score.getRating()==0){
                        Toast.makeText(this,"亲,请您给我们评分哦!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (et_conment.getText().toString().equals("")){
                        Toast.makeText(this,"亲，请谈谈您的使用感受哦!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    share = getSharedPreferences("isUsed", Context.MODE_PRIVATE);
                    editor = share.edit();
                    editor.putFloat("score"+id_fromlast,rb_score.getRating());
                    editor.putString("comment"+id_fromlast,et_conment.getText().toString());
                    editor.commit();
                    try {
                        NetWorkRequest.getBaocunUsefeeling(this,token,id_fromlast, (int) (rb_score.getRating()*2), URLEncoder.encode(et_conment.getText().toString(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent();
                    intent.putExtra("baocun",true);
                    setResult(110,intent);
                    finish();
                    break;
                default:
                    break;
            }
    }
}
