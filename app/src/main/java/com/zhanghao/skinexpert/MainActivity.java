package com.zhanghao.skinexpert;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD

public class MainActivity extends AppCompatActivity {
=======
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
private FragmentTabHost tabHost;
    private String[] tabnames=new String[]{"首页","福利","社区","我的"};
    private String[] tags=new String[]{"home","fuli","sequ","me"};
>>>>>>> e51d5d211a95610e320d7000724245a678053256

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost= (FragmentTabHost) findViewById(R.id.frgtabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.frg_container);
        initTabs();

    }

    private void initTabs() {
        for (int i = 0; i < tabnames.length; i++) {
            TabHost.TabSpec tabspec=tabHost.newTabSpec(tags[i]).setIndicator(getView(i));
        }   tabHost.addTab(tabspec,);
    }

    private View getView(int i) {
    }
}
