package com.zhanghao.skinexpert.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.adapter.CommentArticleAdapter;
import com.zhanghao.skinexpert.application.MyApplication;
import com.zhanghao.skinexpert.beans.CommentArticleBean;
import com.zhanghao.skinexpert.beans.LikeArticleResultBean;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

public class ArticleCommentActivity extends AppCompatActivity {

    private ListView commentListView;
    private List<CommentArticleBean.DataBean.ListBean> commentList;
    private CommentArticleAdapter commentArticleAdapter;
    private PopupWindow popupWindow;

    private EditText commitEditText;

    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefresh = false;

    private int id = 0;
    private int pageNum = 0;
    private int lastId = 0;
    private String token;
    private CommentArticleBean commentArticleBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_comment);
        getId();
        initBottom();
        initListView();
        initSwipeRefreshLayout();
        initListViewListener();
        getCommentArticleData();
    }

    private void getId() {
        token = ((MyApplication) getApplication()).getToken();
        if (token == null) {
            token = "";
        }
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    private void initBottom() {
        commitEditText = (EditText) findViewById(R.id.et_comment_article);
    }

    private void initListView() {
        commentListView = (ListView) findViewById(R.id.lv_comment_article);
        initData();
    }

    private void initData() {
        commentList = new ArrayList<>();
        commentArticleAdapter = new CommentArticleAdapter(commentList, this);
        commentListView.setAdapter(commentArticleAdapter);
    }

    private void initListViewListener() {
        commentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initPopupWindow(position);
            }
        });
        commentListView.setOnScrollListener(scrollListener);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_article_comment);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_red, R.color.refresh_red1, R.color.refresh_red2, R.color.refresh_red3);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 0;
                lastId = 0;
                commentList.clear();
                getCommentArticleData();
            }
        });
    }

    private void getCommentArticleData() {
        if (id > 0 && pageNum >= 0 && lastId >= 0) {
            NetWorkRequest.getCommentArticle(this, id + "", pageNum + "", lastId + "", new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    commentArticleBean = (CommentArticleBean) result;
                    if (commentArticleBean.getData().getList() != null && commentArticleBean.getData().getList().size() > 0) {
                        for (CommentArticleBean.DataBean.ListBean listBean : commentArticleBean.getData().getList()) {
                            commentList.add(listBean);
                        }
                        isRefresh = true;
                    }
                    commentArticleAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleCommentActivity.this, result, Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_comment_article:
                finish();
                break;
            case R.id.tv_comment_article:
                if (token != null && !"".equals(token)) {
                    String content = commitEditText.getText().toString();
                    if (!"".equals(content)) {
                        getCommentResult(content);
                    }
                } else {
                    Intent intent = new Intent(this, LoginPromptActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    private void getCommentResult(String content) {
        if (token != null && !"".equals(token) && id > 0) {
            NetWorkRequest.getCommentUserResult(this, token, id + "", "0", content, new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    commentList.clear();
                    getCommentArticleData();
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleCommentActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(this, LoginPromptActivity.class);
            startActivity(intent);
        }
    }

    private void initPopupWindow(int position) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_comment_article_user, null);
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
        popupWindow.showAtLocation(findViewById(R.id.activity_article_comment), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);

        popupWindowListener(contentView, position);

    }

    private void popupWindowListener(View contentView, final int position) {
        TextView copyTV = (TextView) contentView.findViewById(R.id.tv_copy_popup_comment_item);
        TextView backTV = (TextView) contentView.findViewById(R.id.tv_back_popup_comment_item);
        TextView replyTV = (TextView) contentView.findViewById(R.id.tv_reply_popup_comment_item);
        TextView reportTV = (TextView) contentView.findViewById(R.id.tv_report_popup_comment_item);
        copyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", commentList.get(position).getContent());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(ArticleCommentActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                closePopupWindow();
            }
        });
        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        replyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitEditText.setText("@" + commentList.get(position).getUser().getNick() + " ");
                closePopupWindow();
            }
        });
        reportTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReportResult(position);
                closePopupWindow();
            }
        });
    }

    private void getReportResult(int position) {
        if (token != null && !"".equals(token)) {
            NetWorkRequest.getReportUserResult(ArticleCommentActivity.this, commentList.get(position).getUser().getUid() + "", token, new NetWorkRequest.RequestCallBack() {
                @Override
                public void success(Object result) {
                    LikeArticleResultBean bean = (LikeArticleResultBean) result;
                    if (bean != null) {
                        if ("成功".equals(bean.getMessage())) {
                            Toast.makeText(ArticleCommentActivity.this, "举报成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ArticleCommentActivity.this, "举报失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void fail(String result) {
                    Toast.makeText(ArticleCommentActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(ArticleCommentActivity.this, LoginPromptActivity.class);
            startActivity(intent);
        }
    }

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

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem + visibleItemCount == totalItemCount && isRefresh && commentList != null) {
                pageNum = commentList.size() + 1;
                lastId = commentList.get(commentList.size() - 1).getId();
                getCommentArticleData();
                isRefresh = false;
            }
            boolean enable = false;
            if (commentListView != null && commentListView.getChildCount() > 0) {
                boolean firstItemVisible = commentListView.getFirstVisiblePosition() == 0;
                boolean topOfFirstItemVisible = commentListView.getChildAt(0).getTop() == 0;
                enable = firstItemVisible && topOfFirstItemVisible;
            } else if (commentListView != null && commentListView.getChildCount() == 0) {
                enable = true;
            }
            swipeRefreshLayout.setEnabled(enable);
        }
    };
}
