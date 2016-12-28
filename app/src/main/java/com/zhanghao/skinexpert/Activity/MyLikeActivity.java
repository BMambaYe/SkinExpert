package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.fragments.MyLikeArticleFragment;
import com.zhanghao.skinexpert.fragments.MyLikePostFragment;

import java.util.ArrayList;
import java.util.List;

public class MyLikeActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnBack;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;
    private String[] tabs ={"帖子","文章"};
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like);
        context = MyLikeActivity.this;
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
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_my_like);
        btnBack = (Button) findViewById(R.id.my_like_btn_back);
        tabLayout = (TabLayout) findViewById(R.id.my_like_tablayout);
        viewPager = (ViewPager) findViewById(R.id.my_like_viewpager);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_red),
                getResources().getColor(R.color.refresh_red1),
                getResources().getColor(R.color.refresh_red2),
                getResources().getColor(R.color.refresh_red3));
        swipeRefreshLayout.setProgressViewOffset(false, 0, 85);
        //刷新的监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
        initFragments();
        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragments() {
        MyLikePostFragment fragment1 = new MyLikePostFragment();
        MyLikeArticleFragment fragment2 = new MyLikeArticleFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
    }

    class MyFragmentPageAdapter extends FragmentPagerAdapter{

        public MyFragmentPageAdapter(FragmentManager fm) {
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
