package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.fragments.MyProductFragment1;
import com.zhanghao.skinexpert.fragments.MyProductFragment2;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhanghao.skinexpert.fragments.MyProductFragment1.onRereshData1;
import static com.zhanghao.skinexpert.fragments.MyProductFragment2.onRereshData2;

public class MyProductActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnBack;
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;
    private String[] tabs = {"想用","用过"};
    private SQLiteHelper sqLiteHelper;
    private SharedPreferences sp;
    private String token ;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Map<String,String>> wantedLists = new ArrayList<>() ;
    private List<Map<String,String>> usedLists= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        context = MyProductActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        token = sp.getString("token",null);
        NetWorkRequest.addJSONRequest(context, Constant.MY_PRODUCT_USED+token+"&lastId=0&type=1", new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                JSONObject jsonObject = (JSONObject) result;
                usedLists.removeAll(usedLists);
                usedLists.addAll(jsonAnalysis(jsonObject));
                adapter.notifyDataSetChanged();
                onRereshData2(usedLists);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(String result) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

            }
        });
        NetWorkRequest.addJSONRequest(context, Constant.MY_PRODUCT_WANGTED+token+"&lastId=0&type=2", new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                JSONObject jsonObject = (JSONObject) result;
                wantedLists.removeAll(wantedLists);
                wantedLists.addAll(jsonAnalysis(jsonObject));
//                adapter.notifyDataSetChanged();
                onRereshData1(wantedLists);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(String result) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<Map<String,String>> jsonAnalysis(JSONObject jsonObject) {
        List<Map<String,String>> lists =new ArrayList<>();
        try {
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObjectData.getJSONArray("list");
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObjectProduct = jsonArray.getJSONObject(i);
                Map<String,String> map = new HashMap<>();
                map.put("id",jsonObjectProduct.getString("id"));
                map.put("name",jsonObjectProduct.getString("name"));
                map.put("brandEnName",jsonObjectProduct.getString("brandEnName"));
                map.put("brandChinaName",jsonObjectProduct.getString("brandChinaName"));
                map.put("image",jsonObjectProduct.getString("image"));
                lists.add(map);
            }

            return lists;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_my_product);
        btnBack = (Button) findViewById(R.id.my_product_btn_back);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_red),
                getResources().getColor(R.color.refresh_red1),
                getResources().getColor(R.color.refresh_red2),
                getResources().getColor(R.color.refresh_red3));
        swipeRefreshLayout.setProgressViewOffset(false, 0, 85);
        //设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAutoRefresh();
            }
        });
        setAutoRefresh();
        getFragment();
    }

    private void setAutoRefresh() {
        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                initData();


            }
        });
    }

    private void getFragment() {

        MyProductFragment1 fragment1 = new MyProductFragment1(wantedLists);
        MyProductFragment2 fragment2 = new MyProductFragment2(usedLists);
        fragments.add(fragment1);
        fragments.add(fragment2);
        adapter.notifyDataSetChanged();
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
