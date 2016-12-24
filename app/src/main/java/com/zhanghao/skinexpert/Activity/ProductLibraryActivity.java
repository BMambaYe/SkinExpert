package com.zhanghao.skinexpert.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.BrandSortAdapter;
import com.zhanghao.skinexpert.adapter.ProductGridViewAdapter;
import com.zhanghao.skinexpert.adapter.ProductListViewAdapter;
import com.zhanghao.skinexpert.beans.BrandSortBean;
import com.zhanghao.skinexpert.beans.ProductLibrariesBean;
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
    private ProductLibrariesBean productLibrariesBean;
    private List<ProductLibrariesBean.DataBean.ListBean> listBeen;
    private ProductListViewAdapter listViewAdapter;

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
    private List<BrandSortBean> brandList;
    private BrandSortAdapter brandAdapter;

    private View view;
    private View functionView;
    private View classifyView;
    private View brandView;
    private View priceView;
    private PopupWindow popupWindow;

    private SideBar sideBar;
    private TextView dialog;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_library);
        initProductLayout();
        initClassifyLayout();
        initFunctionLayout();
        initPriceLayout();
        initBrandLayout();
    }

    private void initProductLayout() {
        view = findViewById(R.id.view_product_library);
        listView = ((ListView) findViewById(R.id.lv_product_library));
        initProductListData();
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

        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(letterChangedListener);
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

    private void initProductClassifyData() {
        classifyList = new ArrayList<>();
        classifyAdapter = new ProductGridViewAdapter(this, classifyList, 0);
        classifyGridView.setAdapter(classifyAdapter);
        String classifyJsonData = getDataJson("claslist.json");
        getSearchData(classifyJsonData, classifyList, classifyAdapter);
    }

    private void initProductFunctionData() {
        functionList = new ArrayList<>();
        functionAdapter = new ProductGridViewAdapter(this, functionList, 1);
        functionGridView.setAdapter(functionAdapter);
        String functionJsonData = getDataJson("funclist.json");
        getSearchData(functionJsonData, functionList, functionAdapter);
    }

    private void initProductBrandData() {
        brandList = new ArrayList<>();
        brandAdapter = new BrandSortAdapter(this, brandList);
        brandListView.setAdapter(brandAdapter);
        String brandJsonData = getDataJson("androidBrand.json");
        getBrandData(brandJsonData);
    }

    private void initProductPriceData() {
        priceList = new ArrayList<>();
        priceAdapter = new ProductGridViewAdapter(this, priceList, 3);
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

    private void getSearchData(String data, List<Map<String, Object>> dataList, ProductGridViewAdapter adapter) {
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

    private void getBrandData(String data) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> map = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map.put("id", jsonObject.getString("id"));
                map.put("name", jsonObject.getString("brandChinaName") + jsonObject.getString("name"));
                list.add(map);
            }
            for (int i = 0; i < list.size(); i++) {
                BrandSortBean brandSortBean = new BrandSortBean();
                brandSortBean.setName(list.get(i).get("name") + "");
                brandSortBean.setId(Integer.parseInt(list.get(i).get("id") + ""));
                //汉字转换成拼音
                String pinyin = characterParser.getSelling(list.get(i).get("name") + "");
                String sortString = pinyin.substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]")) {
                    brandSortBean.setSortLetters(sortString.toUpperCase());
                } else {
                    brandSortBean.setSortLetters("#");
                }
                brandList.add(brandSortBean);
            }
            Collections.sort(brandList, pinyinComparator);
            brandAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initProductData() {
        NetWorkRequest.getProductListDataBean(this, "0", "0", "0", "0", "0", "0", "0", "", this);
    }

    @Override
    public void success(Object result) {
        productLibrariesBean = (ProductLibrariesBean) result;
        List<ProductLibrariesBean.DataBean.ListBean> list = productLibrariesBean.getData().getList();
        for (ProductLibrariesBean.DataBean.ListBean bean : list) {
            listBeen.add(bean);
        }
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String result) {

    }

    SideBar.OnTouchingLetterChangedListener letterChangedListener = new SideBar.OnTouchingLetterChangedListener() {
        @Override
        public void onTouchingLetterChanged(String s) {
            int position = brandAdapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                brandListView.setSelection(position);
            }
        }
    };

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
                //TODO
                break;
            default:
                break;
        }
    }

    private void initPopupWindow(View contentView) {
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
