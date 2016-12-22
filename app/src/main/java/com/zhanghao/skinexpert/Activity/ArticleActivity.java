package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.PopupWindow;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class ArticleActivity extends AppCompatActivity {

    private WebView webView;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        webView = ((WebView) findViewById(R.id.wv_article));
        Intent intent = getIntent();
        int articleNumber = intent.getIntExtra("url", 0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.HOMEPIC1+articleNumber+Constant.HMOEPIC2);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_article:
                finish();
                break;
            case R.id.tv_article_share:
                initPopupWindow();
                break;
            case R.id.btn_article_commit:
                //TODO 评论
                break;
            case R.id.iv_article_weixinCircle:
                //TODO 分享朋友圈
                break;
            case R.id.iv_article_weixinFriend:
                //TODO 分享朋友
                break;
            case R.id.iv_article_collecion:
                //TODO 收藏
                break;
            default:
                break;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popu_window_share, null);
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        popupWindow.showAtLocation(findViewById(R.id.activity_article), Gravity.BOTTOM, 0, 0);
    }
}
