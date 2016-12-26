package com.zhanghao.skinexpert.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RockGao on 2016/12/23.
 */

public class MyApplication extends Application {
   private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //初始化测试题的index
    private int indexType ;
    private int index;
    private int age;
//    private SQLiteDatabase sqLiteDatabase;
//    private SQLiteHelper sqLiteHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        sp =getApplicationContext().getSharedPreferences("testquestioninfo", Context.MODE_PRIVATE);
        editor = sp.edit();
        indexType = sp.getInt("indexType",0);
        index = sp.getInt("index",0);
//        indexType=3;
//        index=8;
        age=20;
    }

    public int getIndexType() {
        return indexType;
    }
    public void setIndexType(int indexType) {
        this.indexType = indexType;
        editor.putInt("indexType",indexType);
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
        editor.putInt("index",index);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
