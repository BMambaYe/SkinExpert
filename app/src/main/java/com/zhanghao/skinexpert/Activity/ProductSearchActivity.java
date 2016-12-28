package com.zhanghao.skinexpert.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductSearchWordAdapter;
import com.zhanghao.skinexpert.beans.ProductSearchWordBean;
import com.zhanghao.skinexpert.fragments.ElementSearchFragment;
import com.zhanghao.skinexpert.fragments.ProductSearchFragment;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack {

    private String key;

    private EditText editText;
    private TextView textView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private ProductSearchPagerAdapter pageAdapter;
    private String[] searchPage = new String[]{"产品", "成分"};

    private ListView wordListView;
    private List<String> wordList;
    private ProductSearchWordBean wordBean;
    private ProductSearchWordAdapter wordAdapter;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        initTopLayout();
        initViewPager();
        initTabLayout();
        initListView();
        initListener();
    }

    private void initTopLayout() {
        editText = ((EditText) findViewById(R.id.et_product_search));
        textView = ((TextView) findViewById(R.id.tv_product_search));
    }

    private void initViewPager() {
        viewPager = ((ViewPager) findViewById(R.id.vp_product_search));
        fragmentList = new ArrayList<>();
        ProductSearchFragment fragment = new ProductSearchFragment();
        ElementSearchFragment fragment1 = new ElementSearchFragment();
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
        pageAdapter = new ProductSearchPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tl_product_search);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initListView() {
        wordListView = ((ListView) findViewById(R.id.lv_product_search_word));
        wordList = new ArrayList<>();
        wordAdapter = new ProductSearchWordAdapter(this, wordList);
        wordListView.setAdapter(wordAdapter);
        initSearchWordData();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(viewPagerListener);
        editText.addTextChangedListener(textWatcher);
        editText.setOnEditorActionListener(actionListener);
        textView.setOnClickListener(textListener);
        wordListView.setOnItemClickListener(wordListListener);
    }

    private void initSearchWordData() {
        NetWorkRequest.getProductSearchWords(this, key, this);
    }

    @Override
    public void success(Object result) {
        wordList.clear();
        wordBean = (ProductSearchWordBean) result;
        if (wordBean.getData().getList() != null && wordBean.getData().getList().size() > 0) {
            for (ProductSearchWordBean.DataBean.ListBean listBean : wordBean.getData().getList()) {
                wordList.add(listBean.getContent());
            }
        }
        wordAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                editText.setHint("搜索产品");
            } else if (position == 1) {
                editText.setHint("搜索成分");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String searchContent = editText.getText().toString();
            if (!"".equals(searchContent) && searchContent != null) {
                if (wordListView.getVisibility() == View.GONE) {
                    wordListView.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                }
                try {
                    key = URLEncoder.encode(searchContent, "UTF-8");
                    initSearchWordData();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                if (wordListView.getVisibility() == View.VISIBLE) {
                    wordListView.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                saveSearchData();
                Intent intent = new Intent(ProductSearchActivity.this, ProductLibraryActivity.class);
                intent.putExtra("search", key);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        }
    };

    View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    AdapterView.OnItemClickListener wordListListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String s = wordList.get(position);
            Intent intent = new Intent(ProductSearchActivity.this, ProductLibraryActivity.class);
            intent.putExtra("search", s);
            startActivity(intent);
            finish();
        }
    };

    private void saveSearchData() {
        if (!"".equals(key) && key != null) {
            SQLiteHelper helper = new SQLiteHelper(this);
            db = helper.getReadableDatabase();
            String s = null;
            try {
                s = URLDecoder.decode(key, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            db.delete(SQLiteHelper.table_search_history, "search=?", new String[]{s});
            ContentValues contentValues = new ContentValues();
            contentValues.put("search", s);
            db.insert(SQLiteHelper.table_search_history, null, contentValues);
        }
    }

    class ProductSearchPagerAdapter extends FragmentPagerAdapter {

        public ProductSearchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList == null ? null : fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return searchPage[position];
        }
    }
}
