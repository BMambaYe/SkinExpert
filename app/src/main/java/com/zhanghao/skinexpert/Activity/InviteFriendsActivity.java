package com.zhanghao.skinexpert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;

public class InviteFriendsActivity extends AppCompatActivity {

    private WebView webView;
    private String htmlData;

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
            default:
                break;
        }
    }
}
