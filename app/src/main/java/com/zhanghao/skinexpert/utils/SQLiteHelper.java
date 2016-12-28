package com.zhanghao.skinexpert.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            " (_id integer primary key autoincrement,username varchar,age Integer,indexType Integer," +
            "index_q Integer,score Integer,status Integer)";
//    public String createTestResult = "create table if not exists " + "skintestresult" +
//            " (_id integer primary key autoincrement,username varchar,age Integer,oily varchar,tolerance varchar,pigment varchar" +
//            ",compact varchar)";
    public static final String table_used="product_used";
    public String create_used_product_table="create table if not exists " + table_used +
        " (_id integer primary key autoincrement,product_id integer,product_brand varchar,product_name varchar," +
        "product_pic varchar)";
    public static final String table_wanted="product_wanted";
    public String create_wanted_product_table="create table if not exists " + table_wanted +
            " (_id integer primary key autoincrement,product_id integer,product_brand varchar,product_name varchar," +
            "product_pic varchar)";
    public static final String table_search_history="product_search";
    public String create_search_history_table="create table if not exists " + table_search_history +
            " (_id integer primary key autoincrement,search_id integer,search varchar)";
    public static final String table_element_history="product_element_search";
    public String create_element_history_table="create table if not exists " + table_element_history +
            " (_id integer primary key autoincrement,search_id integer,search varchar)";

    public SQLiteHelper(Context context) {
        super(context, sql_name, null, sql_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
        db.execSQL(createTestInfoTable);
//        db.execSQL(createTestResult);
        db.execSQL(create_used_product_table);
        db.execSQL(create_wanted_product_table);
        db.execSQL(create_search_history_table);
        db.execSQL(create_element_history_table);
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
