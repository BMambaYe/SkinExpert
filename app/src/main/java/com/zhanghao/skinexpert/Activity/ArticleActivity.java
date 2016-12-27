package com.zhanghao.skinexpert.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

public class ArticleActivity extends AppCompatActivity {

    private WebView webView;
    private int articleNumber;
    private String url;
    private PopupWindow popupWindow;
    private ImageView imageView;
    private boolean isCollection = false;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        webView = ((WebView) findViewById(R.id.wv_article));
        Intent intent = getIntent();
        articleNumber = intent.getIntExtra("url", 0);
        if (articleNumber != 0) {
            webView.getSettings().setJavaScriptEnabled(true);
            url = Constant.HOMEPIC1 + articleNumber + Constant.HMOEPIC2;
            judgeIsCollection();
            webView.loadUrl(url);
        }
    }

    private void judgeIsCollection() {
        imageView = (ImageView) findViewById(R.id.iv_article_collecion);
        SQLiteHelper helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.table_name, null);
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex("sid")) == articleNumber) {
                imageView.setImageResource(R.mipmap.ic_collect_pressed);
                isCollection = true;
                return;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
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
                if (!isCollection && articleNumber != 0) {
                    imageView.setImageResource(R.mipmap.ic_collect_pressed);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("sid", articleNumber);
                    contentValues.put("url", url);
                    db.insert(SQLiteHelper.table_name, null, contentValues);
                    isCollection = true;
                } else if (isCollection && articleNumber != 0) {
                    imageView.setImageResource(R.mipmap.ic_collect);
                    db.delete(SQLiteHelper.table_name, "sid=?", new String[]{articleNumber + ""});
                    isCollection = false;
                }
                break;
            default:
                break;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_share, null);
        //设置popupWindow焦点
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
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
        params.alpha =0.7f;
        getWindow().setAttributes(params);
        //显示popupWindow
        popupWindow.showAtLocation(findViewById(R.id.activity_article), Gravity.BOTTOM, 0, 0);
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
