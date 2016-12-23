package com.zhanghao.skinexpert.Activity;

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
import android.widget.PopupWindow;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class InviteFriendsActivity extends AppCompatActivity {

    private WebView webView;
    private String htmlData;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        webView = ((WebView) findViewById(R.id.wv_inviteFriends));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constant.INVITEFRIENDS);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inviteFriends:
                finish();
                break;
            case R.id.btn_inviteFriends_jump:
                initPopupWindow();
                break;
            default:
                break;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popu_window_share, null);
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
        popupWindow.showAtLocation(findViewById(R.id.activity_invite_friends), Gravity.BOTTOM, 0, 0);
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
