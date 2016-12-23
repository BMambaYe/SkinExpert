package com.zhanghao.skinexpert.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.android.volley.Request.Method.HEAD;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static String sql_name = "skin_sql";
    public static int sql_version = 1;
    public static String table_name = "skin_collection";
    public String createTable = "create table if not exists " + table_name +
            " (_id integer primary key autoincrement,sid Integer," +
            "url varchar)";

    public String alter_table = "alter table "+table_name+" rename to temp_"+table_name;
    public String insert_new_table = "insert into "+table_name+" select *,'' from temp_"+table_name;
    public String create_new_table = "create table if not exists " + table_name +
            " (_id integer primary key autoincrement,sid Integer," +
            "url varchar)";
    public String drop_temp_table = "drop table temp_"+table_name;
    public String createTestInfoTable = "create table if not exists " + "skintestinfo" +
            " (_id integer primary key autoincrement,username varchar,indexType Integer," +
            "index_q Integer,score Integer,status Integer)";

    public SQLiteHelper(Context context) {
        super(context, sql_name, null, sql_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
        db.execSQL(createTestInfoTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL(alter_table);
            db.execSQL(create_new_table);
            db.execSQL(insert_new_table);
            db.execSQL(drop_temp_table);
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }
}
