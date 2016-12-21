package com.zhanghao.skinexpert.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.fragmenme_activity.AboutSkinActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MyIndentActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MyLoactionActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MyPostActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MyProductActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MySettingActivity;
import com.zhanghao.skinexpert.fragmenme_activity.MySkinFundActivity;
import com.zhanghao.skinexpert.fragmenme_activity.NotificationMsgListActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private View view;
    private Button btnMessage,btnSetting,btnLogin;
    private ImageView imgLogin;
    private LinearLayout btnMySkin,btnMyPost,btnMyProduct,btnMyIndent,
            btnMyLocation,btnMySkinFund,btnMyFriend,btnMyAbout;
    private boolean isNotSkinTest =true;
    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_me,null);
        initView();
        setOnClick();
        return view;
    }

    private void setOnClick() {
        //通知信息
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNotificationAct = new Intent(getContext(), NotificationMsgListActivity.class);
                startActivity(intentToNotificationAct);
            }
        });
        //我的设置
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSettingAct = new Intent(getContext(),MySettingActivity.class);
                startActivity(intentToSettingAct);
            }
        });
        //我的肤质
        btnMySkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotSkinTest){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示");
                    builder.setMessage("请先完成肤质测试");
                    builder.setPositiveButton("去测试", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO
                            Toast.makeText(getContext(), "待添加", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
            }
        });
        //我的帖子
        btnMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyPostAct = new Intent(getContext(), MyPostActivity.class);
                startActivity(intentToMyPostAct);
            }
        });
        //我的护肤品
        btnMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyProductAct = new Intent(getContext(), MyProductActivity.class);
                startActivity(intentToMyProductAct);
            }
        });
        //我的订单
        btnMyIndent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyIndentAct = new Intent(getContext(), MyIndentActivity.class);
                startActivity(intentToMyIndentAct);
            }
        });
        //我的收货地址
        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMyLocationActivity = new Intent(getContext(), MyLoactionActivity.class);
                startActivity(intentToMyLocationActivity);
            }
        });
        //美肤家基金
        btnMySkinFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSkinFundAct = new Intent(getContext(), MySkinFundActivity.class);
                //ToDo
                startActivity(intentToSkinFundAct);
            }
        });
        //邀请朋友
        //TODO
        //关于
        btnMyAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAboutAct = new Intent(getContext(), AboutSkinActivity.class);
                startActivity(intentToAboutAct);
            }
        });
    }

    private void initView() {
        btnMessage = (Button) view.findViewById(R.id.fragment_my_btn_message);
        btnSetting = (Button) view.findViewById(R.id.fragment_my_btn_setting);
        btnLogin = (Button) view.findViewById(R.id.fragment_my_btn_login);
        imgLogin = (ImageView) view.findViewById(R.id.fragment_my_btn_login_right);
        btnMySkin = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myskin);
        btnMyPost = (LinearLayout) view.findViewById(R.id.fragment_my_btn_mypost);
        btnMyProduct = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myproduct);
        btnMyIndent = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myindent);
        btnMyLocation = (LinearLayout) view.findViewById(R.id.fragment_my_btn_mylocation);
        btnMySkinFund = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myskin_fund);
        btnMyFriend = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myfriend);
        btnMyAbout = (LinearLayout) view.findViewById(R.id.fragment_my_btn_myabout);

    }

}
