package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.fragments.MyProductFragment1;
import com.zhanghao.skinexpert.fragments.MyProductFragment2;

import java.util.ArrayList;
import java.util.List;

public class MyProductActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnBack;
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;
    private String[] tabs = {"想用","用过"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        initView();
        setOnClick();
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.my_porduct_tablayout);
        viewPager = (ViewPager) findViewById(R.id.my_product_viewpager);
        btnBack = (Button) findViewById(R.id.my_product_btn_back);
        getFragment();
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getFragment() {
        MyProductFragment1 fragment1 = new MyProductFragment1();
        MyProductFragment2 fragment2 = new MyProductFragment2();
        fragments.add(fragment1);
        fragments.add(fragment2);
    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
