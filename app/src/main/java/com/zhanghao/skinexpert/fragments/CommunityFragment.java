package com.zhanghao.skinexpert.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhanghao.skinexpert.Activity.MessageActivity;
import com.zhanghao.skinexpert.Activity.PostActivity;
import com.zhanghao.skinexpert.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {
    private ViewPager viewpager;
    private List<Fragment> fragments=new ArrayList<>();
    private MyPagerAdapter adapter;
    private TabLayout tablayout;
    private ImageView iv_message;
    private ImageView iv_post;
    private String titles[]={"发现","关注"};
    public CommunityFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_community, container, false);
        /**
         * 对view进行初始化
         */
        viewpager= (ViewPager) view .findViewById(R.id.viewpager);
        tablayout= (TabLayout) view.findViewById(R.id.tablayout);
        iv_message= (ImageView) view.findViewById(R.id.iv_message);
        iv_post= (ImageView) view.findViewById(R.id.iv_post);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        /**
         * 给view设置监听（click）
         */
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MessageActivity.class);
                startActivity(intent);
            }
        });
        iv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),PostActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 获取fragments数据
         */
        getList();
        /**
         * 给viewpager添加适配器
         */
        adapter=new MyPagerAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        return view;
    }



    private void getList() {
        FindFragment findFragment=new FindFragment();
        ConcernFragment concernFragment=new ConcernFragment();
        fragments.add(findFragment);
        fragments.add(concernFragment);
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
