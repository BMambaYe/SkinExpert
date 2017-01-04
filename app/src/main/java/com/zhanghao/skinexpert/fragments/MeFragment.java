package com.zhanghao.skinexpert.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhanghao.skinexpert.Activity.AboutSkinActivity;
import com.zhanghao.skinexpert.Activity.LoginPromptActivity;
import com.zhanghao.skinexpert.Activity.MyIndentActivity;
import com.zhanghao.skinexpert.Activity.MyLoactionActivity;
import com.zhanghao.skinexpert.Activity.MyPostActivity;
import com.zhanghao.skinexpert.Activity.MyProductActivity;
import com.zhanghao.skinexpert.Activity.MySettingActivity;
import com.zhanghao.skinexpert.Activity.MySkinFundActivity;
import com.zhanghao.skinexpert.Activity.NotificationMsgListActivity;
import com.zhanghao.skinexpert.Activity.PersonalInformationActivity;
import com.zhanghao.skinexpert.Activity.SkinTestMainActivity;
import com.zhanghao.skinexpert.Activity.SkinTestResultDescription;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.TestResultBean;
import com.zhanghao.skinexpert.beans.TotalQuestionBean;
import com.zhanghao.skinexpert.utils.JsonAnalysisFromAssets;
import com.zhanghao.skinexpert.view.CircleImageView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private View view;
    private Button btnMessage,btnSetting,btnLogin;
    private ImageView imgLogin;
    private LinearLayout btnMySkin;
    private LinearLayout btnMyPost;
    private LinearLayout btnMyProduct;
    private LinearLayout btnMyIndent;
    private LinearLayout btnMyLocation;
    private LinearLayout btnMySkinFund;
    private LinearLayout btnMyFriend;
    private LinearLayout btnMyAbout;
    //登录栏
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private CircleImageView circleImageView;
    private TextView txtUserName;
    private TextView txtTestResult;
    private SharedPreferences sp;
    private List<TotalQuestionBean> totalQuestions ;
    private boolean isNotSkinTest =true;
    private Context context;
    private String nickname;
    private String skinCode;
    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_me,null);
        initData();
        initView();
        setOnClick();
        return view;
    }

    private void initData() {
        sp = getContext().getSharedPreferences("user_info",MODE_PRIVATE);
        nickname = sp.getString("nick",null);
        skinCode = sp.getString("skinCode",null);
    }

    private void setOnClick() {
        //通知信息
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToNotificationAct = new Intent(getContext(), NotificationMsgListActivity.class);
                    startActivity(intentToNotificationAct);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }

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

        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLoginPrompt = new Intent(getContext(), LoginPromptActivity.class);
                startActivity(intentToLoginPrompt);

            }
        });

        //我的个人信息设置
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPersonInfo = new Intent(getContext(), PersonalInformationActivity.class);
                startActivity(intentToPersonInfo);
            }
        });

        //我的肤质
        btnMySkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否登录
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    //判断是否已经进行过皮肤测试,判断"-"个数
                    isTested();
                    if (!isTested()){
                        Intent intentToResultDescription = new Intent(getContext(), SkinTestResultDescription.class);
                        startActivity(intentToResultDescription);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("提示");
                        builder.setMessage("请先完成肤质测试");
                        builder.setPositiveButton("去测试", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO
                                Intent intentToSkinTestAct = new Intent(getContext(), SkinTestMainActivity.class);
                                startActivity(intentToSkinTestAct);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }

                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
            }
        });
        //我的帖子
        btnMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToMyPostAct = new Intent(getContext(), MyPostActivity.class);
                    startActivity(intentToMyPostAct);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
            }
        });
        //我的护肤品
        btnMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToMyProductAct = new Intent(getContext(), MyProductActivity.class);
                    startActivity(intentToMyProductAct);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
            }
        });
        //我的订单
        btnMyIndent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToMyIndentAct = new Intent(getContext(), MyIndentActivity.class);
                    startActivity(intentToMyIndentAct);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
            }
        });
        //我的收货地址
        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToMyLocationActivity = new Intent(getContext(), MyLoactionActivity.class);
                    startActivity(intentToMyLocationActivity);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
            }
        });
        //美肤家基金
        btnMySkinFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
                    Intent intentToSkinFundAct = new Intent(getContext(), MySkinFundActivity.class);
                    //ToDo
                    startActivity(intentToSkinFundAct);
                }else {
                    Intent intentToLogin = new Intent(getContext(),LoginPromptActivity.class);
                    startActivity(intentToLogin);
                }
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

    private boolean isTested() {
        int num = 0;
        for (int i = 0; i <skinCode.length() ; i++) {
            if ("-".equals(skinCode.charAt(i))){
                num++;
            }
        }
        if (num<4){
            return false;
        }
        return true;
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
        linearLayout1 = (LinearLayout) view.findViewById(R.id.frgament_me_head_linearlayout1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.frgament_me_head_linearlayout2);
        circleImageView = (CircleImageView) view.findViewById(R.id.fragment_me_headimg);
        txtUserName = (TextView) view.findViewById(R.id.fragment_my_username);
        txtTestResult = (TextView) view.findViewById(R.id.fragment_my_skin_test_result);
        if (sp.getString("token",null)!=null&&!"".equals(sp.getString("token",null))){
            linearLayout1.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            setlinearLayout2View();
        }else {
            linearLayout2.setVisibility(View.INVISIBLE);
            linearLayout1.setVisibility(View.VISIBLE);
        }
    }
    //设置登录后我的界面的头布局
    private void setlinearLayout2View() {

        StringBuffer sb = new StringBuffer();
        if (skinCode==null||"".equals(skinCode)){
            txtTestResult.setText("未测试肤质");
        }else {
            totalQuestions =JsonAnalysisFromAssets.analysisJson(getContext());
            for (int i = 0; i <totalQuestions.size() ; i++) {
                List<TestResultBean> testResults = totalQuestions.get(i).getResults();
                Log.i("RockTest:","测试点:"+skinCode.length());
                for (int j = 0; j <testResults.size() ; j++) {
                    if ((skinCode.charAt(i)+"").equals(testResults.get(j).getSkinCodeChar())){
                        String title = testResults.get(j).getTitle();
                        sb.append(title.charAt(0)+title.charAt(2)+".");
                    }
                }
            }
            txtTestResult.setText(sb.toString());
        }

    }

}
