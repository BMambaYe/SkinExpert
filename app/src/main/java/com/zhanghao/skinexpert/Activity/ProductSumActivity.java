package com.zhanghao.skinexpert.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.ProductLibrariesAdapter;
import com.zhanghao.skinexpert.beans.ProductLibrariesBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class ProductSumActivity extends AppCompatActivity implements NetWorkRequest.RequestCallBack{

    private TextView brand;
    private TextView function;
    private TextView price;
    private TextView classify;
    private ListView listView;
    private List<ProductLibrariesBean.DataBean.ListBean> listBeen;
    private ProductLibrariesAdapter listViewAdapter;
    private ProductLibrariesBean productLibrariesBean;
    private PopupWindow popupWindow;
    private GridView classifyGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sum);
        brand = ((TextView) findViewById(R.id.tv_product_sum_brand));
        function = ((TextView) findViewById(R.id.tv_product_sum_function));
        price = ((TextView) findViewById(R.id.tv_product_sum_price));
        classify = ((TextView) findViewById(R.id.tv_product_sum_classify));
        listView = ((ListView) findViewById(R.id.lv_product_sum));
        listBeen = new ArrayList<>();
        listViewAdapter = new ProductLibrariesAdapter(this,listBeen);
        listView.setAdapter(listViewAdapter);
        initData();
    }

    private void initData() {
        NetWorkRequest.getProductListDataBean(this,"0","0","0","0","0","0","0","",this);
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

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_product_classify, null);
        //设置popupWindow焦点
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        classifyGridView = (GridView) contentView.findViewById(R.id.gv_classify);
//        classifyGridView.setAdapter();
        //创建popupWindow
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //点击popupWindow以外隐藏
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //popupWindow动画
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        //popupWindow以外的透明度
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //显示popupWindow
        popupWindow.showAtLocation(findViewById(R.id.activity_product_sum), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);
        Button popupBtn = (Button) contentView.findViewById(R.id.btn_popu);
        popupBtn.setOnClickListener(dismissPopupListener);
        //TODO 分享
    }

    View.OnClickListener dismissPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closePopupWindow();
        }
    };

    PopupWindow.OnDismissListener popupDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            closePopupWindow();
        }
    };

    private void closePopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        }
    }
}
