package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;

public class AccountBindActivity extends AppCompatActivity {
    private Button btnBindWeixin,btnBindWeibo,btnModifyPhone,btnBcak;
    private TextView txtPhoneNum;
    private Context context;
    private boolean isNotBindWeixin=true;
    private boolean isNotBindWeibo =true;
    private int WEIXIN =1;
    private int WEIBO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_bind);
        context = AccountBindActivity.this;
        initView();
        setOnClick();
    }

    private void setOnClick() {
        btnBcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //绑定、解绑微信
        btnBindWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotBindWeixin){
                    //TODO
                    Toast.makeText(context, "功能暂无", Toast.LENGTH_SHORT).show();
                    btnBindWeixin.setText("解绑");
                    isNotBindWeixin=false;
                    btnBindWeixin.setTextColor(getResources().getColor(R.color.text_darker_gray));
                }else if (!isNotBindWeixin){

                    setDialogShow("你确定要解绑吗?","解绑后无法使用微信登录，确定解绑吗？","确定",
                            "取消",WEIXIN,btnBindWeixin);
                }
            }
        });


        //绑定、解绑新浪微博
        btnBindWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotBindWeibo){
                    //TODO
                    Toast.makeText(context, "功能暂无", Toast.LENGTH_SHORT).show();
                    btnBindWeibo.setText("解绑");
                    isNotBindWeibo=false;
                    btnBindWeibo.setTextColor(getResources().getColor(R.color.text_darker_gray));
                }else if (!isNotBindWeibo){

                    setDialogShow("你确定要解绑吗?","解绑后无法使用微博登录，确定解绑吗？","确定",
                            "取消",WEIBO,btnBindWeibo);
                }
            }
        });
        //修改绑定的手机号
        btnModifyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "待做好登录注册功能", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDialogShow(final String title, String message, String positivemsg, String negativemsg, final int type
    , final Button btn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positivemsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
                Toast.makeText(context, "解绑成功", Toast.LENGTH_SHORT).show();
                if (type==1){
                    isNotBindWeixin =true;
                }else if (type==2){
                    isNotBindWeibo = true;
                }
                Button button =btn;
                button.setText("绑定");
                button.setTextColor(getResources().getColor(R.color.red));
            }
        });
        builder.setNegativeButton(negativemsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void initView() {
      btnBcak = (Button) findViewById(R.id.account_bind_btn_back);
      btnBindWeixin = (Button) findViewById(R.id.account_bind_btnweixin);
      btnBindWeibo = (Button) findViewById(R.id.account_bind_btnweibo);
      btnModifyPhone = (Button) findViewById(R.id.account_bind_btnphone);
      txtPhoneNum = (TextView) findViewById(R.id.account_bind_phonenum_show);
    }
}
