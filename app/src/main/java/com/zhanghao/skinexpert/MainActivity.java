package com.zhanghao.skinexpert;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zhanghao.skinexpert.fragments.BenefitsFragment;
import com.zhanghao.skinexpert.fragments.CommunityFragment;
import com.zhanghao.skinexpert.fragments.HomeFragment;
import com.zhanghao.skinexpert.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost tabHost;
    private String[] tabnames = new String[]{"首页", "福利", "社区", "我的"};
    private String[] tags = new String[]{"home", "fuli", "sequ", "me"};
    private Class[] fragmentClasses = new Class[]{HomeFragment.class, BenefitsFragment.class, CommunityFragment.class, MeFragment.class};
<<<<<<< HEAD
    private int[] tab_pics = new int[]{R.drawable.home_selector, R.drawable.benefits_selector, R.drawable.community_selector, R.drawable.me_selector};
=======
private int[] tab_pics=new int[]{R.drawable.home_selector,R.drawable.benefits_selector,R.drawable.community_selector,R.drawable.me_selector};
>>>>>>> fcd0c8fca5787d2752efeba1f5de6580b7b48000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(R.id.frgtabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.frg_container);
        initTabs();

    }

    private void initTabs() {
        for (int i = 0; i < tabnames.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tags[i]).setIndicator(getView(i));
            tabHost.addTab(tabSpec, fragmentClasses[i], null);
        }

    }

    private View getView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_view, null);
        ImageView img_tabpic = (ImageView) view.findViewById(R.id.tabhost_pic);
        TextView tv_tabname = (TextView) view.findViewById(R.id.tabhost_name);
        img_tabpic.setImageResource(tab_pics[i]);
        tv_tabname.setText(tabnames[i]);
        return view;
    }
}
