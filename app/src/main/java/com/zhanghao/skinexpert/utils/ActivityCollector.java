package com.zhanghao.skinexpert.utils;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RockGao on 2016/12/29.
 */

public class ActivityCollector {
    public static List<AppCompatActivity> activities = new ArrayList<AppCompatActivity>();
    public static void addActivity(AppCompatActivity activity) {
        activities.add(activity);
    }
    public static void removeActivity(AppCompatActivity activity) {
        activities.remove(activity);
    }
    public static void finishAll() {
        for (AppCompatActivity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
