package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
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
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.LikeArticleBean;
import com.zhanghao.skinexpert.beans.LikeArticleResultBean;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;


public class ArticleActivity extends AppCompatActivity {

    private WebView webView;
    private PopupWindow popupWindow;
    private ImageView imageView;
    private boolean isCollection = false;
    private int id;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        token = ((MyApplication) getApplication()).getToken();
        if (token == null) {
            token = "";
        }
        webView = ((WebView) findViewById(R.id.wv_article));
        imageView = (ImageView) findViewById(R.id.iv_article_collecion);
        initWebView();
    }

    private void initWebView() {
        Intent intent = getIntent();
        int articleId = intent.getIntExtra("url", 0);
        if (articleId != 0) {
            id = articleId;
            webView.getSettings().setJavaScriptEnabled(true);
            String url = Constant.ARTICLE1 + articleId + Constant.ARTICLE2;
            judgeIsCollection();
            webView.loadUrl(url);
        }
    }

    private void judgeIsCollection() {
        if (!"".equals(token) && id > 0) {
            NetWorkRequest.getLikeArticle(this, token + "", id + "", new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    LikeArticleBean bean = (LikeArticleBean) result;
                    if (bean.getData() != null && bean.getData().isLiked()) {
                        imageView.setImageResource(R.mipmap.ic_collect_pressed);
                        isCollection = true;
                    }
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            });
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
                Intent intent = new Intent(this, ArticleCommentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.iv_article_weixinCircle:
                //TODO 分享朋友圈
                break;
            case R.id.iv_article_weixinFriend:
                //TODO 分享朋友
                break;
            case R.id.iv_article_collecion:
                if (isCollection) {
                    addArticle();
                } else {
                    cancelArticle();
                }
                break;
            default:
                break;
        }
    }

    private void addArticle() {
        if (!"".equals(token) && id > 0) {
            NetWorkRequest.cancelLikeArticle(this, token, id + "", new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    LikeArticleResultBean bean = (LikeArticleResultBean) result;
                    if (bean != null && "成功".equals(bean.getMessage())) {
                        imageView.setImageResource(R.mipmap.ic_collect);
                        isCollection = false;
                    } else {
                        Toast.makeText(ArticleActivity.this, "无此权限", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(this, LoginPromptActivity.class);
            startActivity(intent);
        }
    }

    private void cancelArticle() {
        if (!"".equals(token) && id > 0) {
            NetWorkRequest.addLikeArticle(this, token, id + "", new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    LikeArticleResultBean bean = (LikeArticleResultBean) result;
                    if (bean != null && "成功".equals(bean.getMessage())) {
                        imageView.setImageResource(R.mipmap.ic_collect_pressed);
                        isCollection = true;
                    } else {
                        Toast.makeText(ArticleActivity.this, "无此权限", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(this, LoginPromptActivity.class);
            startActivity(intent);
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
        params.alpha = 0.7f;
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
