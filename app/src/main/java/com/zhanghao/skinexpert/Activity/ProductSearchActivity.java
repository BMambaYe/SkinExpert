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
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductSearchWordAdapter;
import com.zhanghao.skinexpert.beans.HotElementWordBean;
import com.zhanghao.skinexpert.beans.ProductSearchWordBean;
import com.zhanghao.skinexpert.fragments.ElementSearchFragment;
import com.zhanghao.skinexpert.fragments.ProductSearchFragment;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack {

    private final int SEARCH_WORD = 0;
    private final int SEARCH_ELEMENT = 1;

    private EditText editText;
    private TextView textView;
    private String[] searchHint = new String[]{"搜索产品", "搜索成分"};

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private ProductSearchPagerAdapter pageAdapter;
    private String[] pageName = new String[]{"产品", "成分"};

    private ListView wordListView;
    private List<String> wordList;
    private List<ProductSearchWordBean.DataBean.ListBean> productWordList;
    private List<HotElementWordBean.DataBean.ListBean> elementWordList;
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
        initSQLite();
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
        productWordList = new ArrayList<>();
        elementWordList = new ArrayList<>();
        wordAdapter = new ProductSearchWordAdapter(this, wordList);
        wordListView.setAdapter(wordAdapter);
    }

    private void initSQLite() {
        SQLiteHelper helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    private int judgeType() {
        return viewPager.getCurrentItem();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(viewPagerListener);
        editText.addTextChangedListener(textWatcher);
        textView.setOnClickListener(textListener);
        wordListView.setOnItemClickListener(wordListListener);
    }

    private void initSearchWordData(String searchWord) {
        int type = judgeType();
        if (type == SEARCH_WORD)
            NetWorkRequest.getProductSearchWords(this, searchWord, this);
        else if (type == SEARCH_ELEMENT)
            NetWorkRequest.getProductHotElementWords(this, "0", searchWord, this);
    }

    @Override
    public void success(Object result) {
        clearData();
        int type = judgeType();
        if (type == SEARCH_WORD) {
            ProductSearchWordBean searchWordBean = (ProductSearchWordBean) result;
            if (searchWordBean.getData().getList() != null && searchWordBean.getData().getList().size() > 0) {
                for (ProductSearchWordBean.DataBean.ListBean listBean : searchWordBean.getData().getList()) {
                    wordList.add(listBean.getContent());
                    productWordList.add(listBean);
                }
            }
        } else if (type == SEARCH_ELEMENT) {
            HotElementWordBean searchWordBean = (HotElementWordBean) result;
            if (searchWordBean.getData().getList() != null && searchWordBean.getData().getList().size() > 0) {
                for (HotElementWordBean.DataBean.ListBean listBean : searchWordBean.getData().getList()) {
                    wordList.add(listBean.getName());
                    elementWordList.add(listBean);
                }
            }
        }
        wordAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    public void clearData() {
        wordList.clear();
        elementWordList.clear();
        productWordList.clear();
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                editText.setHint(searchHint[position]);
            } else if (position == 1) {
                editText.setHint(searchHint[position]);
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
            String searchWord = editText.getText().toString();
            if (!"".equals(searchWord) && searchWord != null) {
                if (wordListView.getVisibility() == View.GONE) {
                    wordListView.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                }
                try {
                    initSearchWordData(URLEncoder.encode(searchWord, "UTF-8"));
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

    View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    AdapterView.OnItemClickListener wordListListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int type = judgeType();
            if (type == SEARCH_WORD) {
                int searchId = productWordList.get(position).getId();
                String searchWord = productWordList.get(position).getContent();
                saveSearchData(searchId, searchWord);
                Intent intent = new Intent(ProductSearchActivity.this, ProductLibraryActivity.class);
                intent.putExtra("search", searchWord);
                startActivity(intent);
            } else if (type == SEARCH_ELEMENT) {
                int searchId = elementWordList.get(position).getId();
                String searchWord = elementWordList.get(position).getName();
                saveSearchData(searchId, searchWord);
                Intent intent = new Intent(ProductSearchActivity.this, ElementDetailActivity.class);
                intent.putExtra("element_id", searchId);
                startActivity(intent);
            }
            finish();
        }
    };

    private void saveSearchData(int id, String searchWord) {
        if (!"".equals(searchWord) && searchWord != null) {
            int type = judgeType();
            if (type == SEARCH_WORD) {
                db.delete(SQLiteHelper.table_search_history, "search_id=?", new String[]{id + ""});
                ContentValues contentValues = new ContentValues();
                contentValues.put("search_id", id);
                contentValues.put("search", searchWord);
                db.insert(SQLiteHelper.table_search_history, null, contentValues);
            } else if (type == SEARCH_ELEMENT) {
                db.delete(SQLiteHelper.table_element_history, "search_id=?", new String[]{id + ""});
                ContentValues contentValues = new ContentValues();
                contentValues.put("search_id", id);
                contentValues.put("search", searchWord);
                db.insert(SQLiteHelper.table_element_history, null, contentValues);
            }
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
            return pageName[position];
        }
    }
}
