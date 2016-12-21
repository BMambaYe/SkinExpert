package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhanghao.skinexpert.R;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void cancel(View view) {
        finish();
    }

    public void send(View view) {

    }

    public void add(View view) {
        Intent intent=new Intent(this,AddActivity.class);
        startActivity(intent);
    }
}
