package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductBrandAdapter;
import com.zhanghao.skinexpert.adapter.ProductGridViewAdapter;
import com.zhanghao.skinexpert.adapter.ProductListViewAdapter;
import com.zhanghao.skinexpert.beans.ProductBrandBean;
import com.zhanghao.skinexpert.beans.ProductLibraryBean;
import com.zhanghao.skinexpert.utils.CharacterParser;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.PinyinComparator;
import com.zhanghao.skinexpert.view.SideBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductLibraryActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack {

    private ListView listView;
    private ProductLibraryBean productLibraryBean;
    private List<ProductLibraryBean.DataBean.ListBean> listBeen;
    private ProductListViewAdapter listViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView classifyTextView;
    private TextView functionTextView;
    private TextView brandTextView;
    private TextView priceTextView;

    private GridView classifyGridView;
    private List<Map<String, Object>> classifyList;
    private ProductGridViewAdapter classifyAdapter;

    private GridView functionGridView;
    private List<Map<String, Object>> functionList;
    private ProductGridViewAdapter functionAdapter;

    private ListView priceListView;
    private List<Map<String, Object>> priceList;
    private ProductGridViewAdapter priceAdapter;

    private ListView brandListView;
    private List<ProductBrandBean> brandList;
    private ProductBrandAdapter brandAdapter;
    private SideBar sideBar;
    private TextView dialog;
    private View headerView;
    private TextView headerTextView;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;

    private View view;
    private View functionView;
    private View classifyView;
    private View brandView;
    private View priceView;
    private PopupWindow popupWindow;

    private int classifyId = 0;
    private int functionId = 0;
    private int brandId = 0;
    private int priceId = 0;
    private int elementId = 0;
    private int total = 0;
    private String keyWord = null;

    private boolean isRefresh = false;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_library);

        initClassifyLayout();
        initFunctionLayout();
        initPriceLayout();
        initBrandLayout();

        initProductLayout();
        InitSwipeRefreshLayout();
        initListener();

        getProductData();
        initProductListData();
    }

    private void initProductLayout() {
        view = findViewById(R.id.view_product_library);
        titleText = ((TextView) findViewById(R.id.tv_product_library_search));
        listView = ((ListView) findViewById(R.id.lv_product_library));
        classifyTextView = (TextView) findViewById(R.id.tv_product_library_classify);
        functionTextView = (TextView) findViewById(R.id.tv_product_library_function);
        brandTextView = (TextView) findViewById(R.id.tv_product_library_brand);
        priceTextView = (TextView) findViewById(R.id.tv_product_library_price);
    }

    private void InitSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_product_library);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_red, R.color.refresh_red1, R.color.refresh_red2, R.color.refresh_red3);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                total = 0;
                listBeen.clear();
                initProductData();
            }
        });
    }

    private void initClassifyLayout() {
        classifyView = LayoutInflater.from(this).inflate(R.layout.popup_product_classify, null);
        classifyGridView = (GridView) classifyView.findViewById(R.id.gv_classify);
        initProductClassifyData();
    }

    private void initFunctionLayout() {
        functionView = LayoutInflater.from(this).inflate(R.layout.popup_product_classify, null);
        functionGridView = (GridView) functionView.findViewById(R.id.gv_classify);
        initProductFunctionData();
    }

    private void initBrandLayout() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        brandView = LayoutInflater.from(this).inflate(R.layout.popup_product_brand, null);
        sideBar = (SideBar) brandView.findViewById(R.id.sb_product_brand_listView);
        dialog = (TextView) brandView.findViewById(R.id.tv_product_brand_listView);
        brandListView = (ListView) brandView.findViewById(R.id.lv_product_brand_listView);
        headerView = LayoutInflater.from(this).inflate(R.layout.product_brand_header_item, null);
        headerTextView = (TextView) headerView.findViewById(R.id.tv_product_brand_header_item);

        headerTextView.setText("全部品牌");
        brandListView.addHeaderView(headerView);
        sideBar.setTextView(dialog);
        initProductBrandData();
    }

    private void initPriceLayout() {
        priceView = LayoutInflater.from(this).inflate(R.layout.popup_product_price, null);
        priceListView = (ListView) priceView.findViewById(R.id.lv_popup_product_price);
        initProductPriceData();
    }

    private void initProductListData() {
        listBeen = new ArrayList<>();
        listViewAdapter = new ProductListViewAdapter(this, listBeen);
        listView.setAdapter(listViewAdapter);
        initProductData();
    }

    private void getProductData() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("search");
        classifyId = intent.getIntExtra("classifyId", 0);
        functionId = intent.getIntExtra("functionId", 0);
        brandId = intent.getIntExtra("brandId", 0);
        priceId = intent.getIntExtra("priceId", 0);
        elementId = intent.getIntExtra("elementName", 0);

        if (classifyId == 0) {
            classifyTextView.setText("分类");
            classifyAdapter.setColor("全部分类");
        } else {
            for (Map<String, Object> map : classifyList) {
                if (classifyId == Integer.parseInt(map.get("id") + "")) {
                    String classifyName = map.get("name") + "";
                    classifyTextView.setText(classifyName);
                    classifyAdapter.setColor(classifyName);
                    break;
                }
            }
        }

        if (functionId == 0) {
            functionTextView.setText("功效");
            functionAdapter.setColor("全部功效");
        } else {
            for (Map<String, Object> map : functionList) {
                if (functionId == Integer.parseInt(map.get("id") + "")) {
                    String functionName = map.get("name") + "";
                    functionTextView.setText(functionName);
                    functionAdapter.setColor(functionName);
                    break;
                }
            }
        }

        if (priceId == 0) {
            priceTextView.setText("价格");
            priceAdapter.setColor("全部价格");
        } else {
            for (Map<String, Object> map : priceList) {
                if (priceId == Integer.parseInt(map.get("id") + "")) {
                    String priceName = map.get("name") + "";
                    priceTextView.setText(priceName);
                    priceAdapter.setColor(priceName);
                    break;
                }
            }
        }

        if (brandId == 0) {
            brandTextView.setText("品牌");
            headerTextView.setTextColor(Color.parseColor("#FF6D72"));
        } else {
            for (ProductBrandBean productBrandBean : brandList) {
                if (brandId == productBrandBean.getId()) {
                    String brandName = productBrandBean.getName();
                    brandTextView.setText(brandName);
                    brandAdapter.setColor(brandName + productBrandBean.getEnglishName());
                    break;
                }
            }
        }

        if (key == null || "".equals(key)) {
            keyWord = null;
            titleText.setText("产品库");
        } else {
            keyWord = key;
            titleText.setText(keyWord);
        }
    }

    private void initProductClassifyData() {
        classifyList = new ArrayList<>();
        classifyAdapter = new ProductGridViewAdapter(this, classifyList);
        classifyGridView.setAdapter(classifyAdapter);
        String classifyJsonData = getDataJson("claslist.json");
        getSearchData(classifyJsonData, classifyList, classifyAdapter);
    }

    private void initProductFunctionData() {
        functionList = new ArrayList<>();
        functionAdapter = new ProductGridViewAdapter(this, functionList);
        functionGridView.setAdapter(functionAdapter);
        String functionJsonData = getDataJson("funclist.json");
        getSearchData(functionJsonData, functionList, functionAdapter);
    }

    private void initProductBrandData() {
        brandList = new ArrayList<>();
        brandAdapter = new ProductBrandAdapter(this, brandList);
        brandListView.setAdapter(brandAdapter);
        String brandJsonData = getDataJson("androidBrand.json");
        getBrandData(brandJsonData);
    }

    private void initProductPriceData() {
        priceList = new ArrayList<>();
        priceAdapter = new ProductGridViewAdapter(this, priceList);
        priceListView.setAdapter(priceAdapter);
        String priceJsonData = getDataJson("pricelist.json");
        getSearchData(priceJsonData, priceList, priceAdapter);
    }

    private String getDataJson(String name) {
        ByteArrayOutputStream baos = null;
        try {
            InputStream inputStream = getAssets().open(name);
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                baos.flush();
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void getSearchData(String data, List<Map<String, Object>> dataList,
                               ProductGridViewAdapter adapter) {
        if (data != null && !"".equals(data)) {
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    map.put("id", jsonObject.getString("id"));
                    map.put("name", jsonObject.getString("name"));
                    dataList.add(map);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getBrandData(String data) {
        if (data != null && !"".equals(data)) {
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ProductBrandBean productBrandBean = new ProductBrandBean();
                    productBrandBean.setName(jsonObject.getString("brandChinaName"));
                    productBrandBean.setEnglishName(jsonObject.getString("name"));
                    productBrandBean.setId(Integer.parseInt(jsonObject.getString("id")));
                    //汉字转换成拼音
                    String pinyin = characterParser.getSelling(productBrandBean.getName()
                            + productBrandBean.getEnglishName());
                    String sortString = pinyin.substring(0, 1).toUpperCase();
                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        productBrandBean.setSortLetters(sortString.toUpperCase());
                    } else {
                        productBrandBean.setSortLetters("#");
                    }
                    brandList.add(productBrandBean);
                }
                Collections.sort(brandList, pinyinComparator);
                brandAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initProductData() {
        if (functionId >= 0 && brandId >= 0 && classifyId >= 0 && priceId >= 0 && elementId >= 0 && total >= 0) {
            if (keyWord == null)
                NetWorkRequest.getProductListDataBean(this, functionId + "", brandId + "", classifyId + "", priceId + "", elementId + "", "0", total + "", "----", "", this);
            else
                NetWorkRequest.getProductKeyListDataBean(this, functionId + "", brandId + "", classifyId + "", priceId + "", elementId + "", "0", keyWord, total + "", "----", "", this);
        }
    }

    private void initListener() {
        listView.setOnScrollListener(scrollListener);
        listView.setOnItemClickListener(clickListener);
        sideBar.setOnTouchingLetterChangedListener(letterChangedListener);
        classifyGridView.setOnItemClickListener(classifyGridViewListener);
        functionGridView.setOnItemClickListener(functionGridViewListener);
        priceListView.setOnItemClickListener(priceGridViewListener);
        brandListView.setOnItemClickListener(brandListener);
    }

    @Override
    public void success(Object result) {
        productLibraryBean = (ProductLibraryBean) result;
        if (productLibraryBean.getData().getList() != null && productLibraryBean.getData().getList().size() > 0) {
            List<ProductLibraryBean.DataBean.ListBean> list = productLibraryBean.getData().getList();
            for (ProductLibraryBean.DataBean.ListBean bean : list) {
                listBeen.add(bean);
            }
        }
        listViewAdapter.notifyDataSetChanged();
        isRefresh = true;
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void fail(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem + visibleItemCount == totalItemCount && isRefresh) {
                total += 20;
                initProductData();
                isRefresh = false;
            }
            boolean enable = false;
            if (listView != null && listView.getChildCount() > 0) {
                boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                enable = firstItemVisible && topOfFirstItemVisible;
            } else if (listView != null && listView.getChildCount() == 0) {
                enable = true;
            }
            swipeRefreshLayout.setEnabled(enable);
        }
    };

    SideBar.OnTouchingLetterChangedListener letterChangedListener = new SideBar.OnTouchingLetterChangedListener() {
        @Override
        public void onTouchingLetterChanged(String s) {
            int position = brandAdapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                brandListView.setSelection(position);
            }
        }
    };

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (listBeen.size() > 0) {
                ProductLibraryBean.DataBean.ListBean listBean = listBeen.get(position);
                Intent intent = new Intent(ProductLibraryActivity.this, ProductDetailActivity.class);
                intent.putExtra("id", listBean.getId());
                intent.putExtra("buy", "totaobao");
                startActivity(intent);
            }
        }
    };

    AdapterView.OnItemClickListener classifyGridViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = (String) classifyList.get(position).get("name");
            classifyAdapter.setColor(name);
            if (name.startsWith("全部")) {
                name = name.substring(2);
            }
            classifyTextView.setText(name);
            classifyId = Integer.parseInt(classifyList.get(position).get("id") + "");
            refreshProductList();
        }
    };

    AdapterView.OnItemClickListener functionGridViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = (String) functionList.get(position).get("name");
            functionAdapter.setColor(name);
            if (name.startsWith("全部")) {
                name = name.substring(2);
            }
            functionTextView.setText(name);
            functionId = Integer.parseInt(functionList.get(position).get("id") + "");
            refreshProductList();
        }
    };

    AdapterView.OnItemClickListener brandListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                brandAdapter.setColor("");
                brandTextView.setText("品牌");
                headerTextView.setTextColor(Color.parseColor("#FF6D72"));
                brandId = 0;
            } else {
                String name = brandList.get(position - 1).getName();
                String englishName = brandList.get(position - 1).getEnglishName();
                brandAdapter.setColor(name + englishName);
                brandTextView.setText(name);
                //TODO 颜色修改
                headerTextView.setTextColor(Color.parseColor("#848484"));
                brandId = brandList.get(position - 1).getId();
            }
            refreshProductList();
        }
    };

    AdapterView.OnItemClickListener priceGridViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = (String) priceList.get(position).get("name");
            priceAdapter.setColor(name);
            if (name.startsWith("全部")) {
                name = name.substring(2);
            }
            priceTextView.setText(name);
            priceId = Integer.parseInt(priceList.get(position).get("id") + "");
            refreshProductList();
        }
    };

    private void refreshProductList() {
        closePopupWindow();
        total = 0;
        swipeRefreshLayout.setRefreshing(true);
        listBeen.clear();
        isRefresh = false;
        listView.setSelection(0);
        initProductData();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_product_library_classify:
                initPopupWindow(classifyView);
                break;
            case R.id.tv_product_library_function:
                initPopupWindow(functionView);
                break;
            case R.id.tv_product_library_brand:
                initPopupWindow(brandView);
                break;
            case R.id.tv_product_library_price:
                initPopupWindow(priceView);
                break;
            case R.id.btn_product_library_back:
                finish();
                break;
            case R.id.btn_product_library_search:
                Intent intent = new Intent(this, ProductSearchActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void initPopupWindow(View contentView) {
        if (popupWindow == null) {
            //获取焦点
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);
            //创建popupWindow
            popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
            //点击popupWindow以外隐藏
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            //显示popupWindow
            View view1 = findViewById(R.id.activity_product_library);
            LinearLayout linearLayout = (LinearLayout) view1.findViewById(R.id.ll_product_library);
            popupWindow.showAsDropDown(linearLayout);
            view.setVisibility(View.VISIBLE);
            popupWindow.setOnDismissListener(popupDismissListener);
        }
    }

    PopupWindow.OnDismissListener popupDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            closePopupWindow();
        }
    };

    private void closePopupWindow() {
        popupWindow.dismiss();
        view.setVisibility(View.GONE);
        popupWindow = null;
    }
}
