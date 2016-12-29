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
    private int indexType;
    private int index;
    private int age;
    private String token="74de401fa5ea685a87b2288e6f14f3f0";
    private SharedPreferences sp_user_info;

    //    private SQLiteDatabase sqLiteDatabase;
    //    private SQLiteHelper sqLiteHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        sp = getApplicationContext().getSharedPreferences("testquestioninfo", Context.MODE_PRIVATE);
        editor = sp.edit();
        indexType = sp.getInt("indexType", 0);
        index = sp.getInt("index", 0);
        //        indexType=3;
        //        index=8;
        age = 20;
        //下面的代码被注释掉为了给一个默认的token做测试，做完后需解注释并传入真正的token值
        //       sp_user_info = getSharedPreferences("user_info", Context.MODE_PRIVATE);
  //      token = sp_user_info.getString("token", "");
    }

    public int getIndexType() {
        return indexType;
    }

    public void setIndexType(int indexType) {
        this.indexType = indexType;
        editor.putInt("indexType", indexType);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        editor.putInt("index", index);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
