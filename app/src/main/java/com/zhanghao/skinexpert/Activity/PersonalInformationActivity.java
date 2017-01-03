package com.zhanghao.skinexpert.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.LruChacheImgResource;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.view.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class PersonalInformationActivity extends AppCompatActivity {
    private Button btnBack;
    private LinearLayout btnHeadImgSelect;
    private LinearLayout btnNickSet;
    private LinearLayout btnBirthdaySet;
    private LinearLayout btnGenderSet;
    private LinearLayout btnCitySelect;
    private CircleImageView circleImageView;
    private TextView txtNick;
    private TextView txtBirthday;
    private TextView txtGender;
    private TextView txtCity;
    private String[] dialogItems = {"拍照","本地相册选择"};
    private static final  int TAKE_PHOTO=0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private static final int NICK_REQUEST= 2;
    private File file;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String imgPath;
    private Bitmap bitmap;
    private DatePickerDialog datePickDialog;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        context = PersonalInformationActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sp.edit();
        imgPath = sp.getString("headimg_path",null);
        if (imgPath!=null){
            bitmap = BitmapFactory.decodeFile(imgPath);
        }
        token = sp.getString("token",token);
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.person_info_btn_back);
        btnHeadImgSelect = (LinearLayout) findViewById(R.id.person_info_btn_headimg_select);
        btnNickSet = (LinearLayout) findViewById(R.id.person_info_btn_nick_modify);
        btnBirthdaySet = (LinearLayout) findViewById(R.id.person_info_btn_birthday_select);
        btnGenderSet = (LinearLayout) findViewById(R.id.person_info_btn_gender_select);
        btnCitySelect = (LinearLayout) findViewById(R.id.person_info_btn_city_select);
        circleImageView = (CircleImageView) findViewById(R.id.person_info_imghead);
        txtNick = (TextView) findViewById(R.id.person_info_nick);
        txtBirthday = (TextView) findViewById(R.id.person_info_birthday);
        txtGender = (TextView) findViewById(R.id.person_info_gender);
        txtCity = (TextView) findViewById(R.id.person_info_city);
        //设置View
        setView();

    }

    private void setView() {
        if (bitmap!=null){
            circleImageView.setImageBitmap(bitmap);
        }else {
            circleImageView.setBackgroundResource(R.mipmap.ysf_def_avatar_staff);
        }
        if (sp.getString("nick",null)!=null){
            txtNick.setText(sp.getString("nick",null));
        }else {
            txtNick.setText("未填写");
        }
        if (sp.getString("birthday",null)!=null){
            txtBirthday.setText(sp.getString("birthday",null));
        }else {
            txtBirthday.setText("未填写");
        }
        if (sp.getString("gender",null)!=null){
            txtGender.setText(sp.getString("gender",null));
        }else {
            txtGender.setText("未填写");
        }
        if (sp.getString("location",null)!=null){
            txtCity.setText(sp.getString("location",null));
        }else {
            txtCity.setText("未填写");
        }
    }

    private void setOnClick() {
        //返回
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //头像选择
        /**
         * 这里的头像设置是同时长传到后台的，并且要发送更新用户信息请求，这里暂存本地
         */
        btnHeadImgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buider = new AlertDialog.Builder(context);
                final AlertDialog myDialog = buider.create();
                buider.setTitle("更换头像");
                buider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                });

                buider.setItems(dialogItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                takePhoto();
                                break;
                            case 1:
                                selectPhotoFromLocal();
                                Toast.makeText(context, "本地相册选择", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
                buider.create().show();
            }

        });
        //昵称设置，后台同步更新
        btnNickSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNickModifyAct = new Intent(context,NickModifyActivity.class);
                startActivityForResult(intentToNickModifyAct,NICK_REQUEST);
            }
        });
        //生日设置
        btnBirthdaySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String birthday = year+"-"+month+"-"+dayOfMonth;
                        txtBirthday.setText(birthday);
                        NetWorkRequest.updateUserInfo(context, token, "birthday", birthday, new NetWorkRequest.RequestCallBack() {
                            @Override
                            public void success(Object result) {
                                JSONObject jsonObject = (JSONObject) result;
                                try {
                                    if ("成功".equals(jsonObject.getString("message"))){
                                        editor.putString("birthday",birthday);
                                        editor.commit();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void fail(String result) {

                            }
                        });

                    }
                },2000,01,10);
                datePickDialog.show();
            }
        });


        //性别设置
        btnGenderSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final AlertDialog myDialog = builder.create();
                builder.setTitle("选择性别");
                builder.setItems(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                txtGender.setText("男");
                                NetWorkRequest.updateUserInfo(context, token, "gender", "男", new NetWorkRequest.RequestCallBack() {
                                    @Override
                                    public void success(Object result) {
                                        JSONObject jsonObject = (JSONObject) result;
                                        try {
                                            if ("成功".equals(jsonObject.getString("message"))){

                                                editor.putString("gender","男");
                                                editor.commit();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void fail(String result) {

                                    }
                                });
                                break;
                            case 1:
                                txtGender.setText("女");
                                NetWorkRequest.updateUserInfo(context, token, "gender", "女", new NetWorkRequest.RequestCallBack() {
                                    @Override
                                    public void success(Object result) {
                                        JSONObject jsonObject = (JSONObject) result;
                                        try {
                                            if ("成功".equals(jsonObject.getString("message"))){

                                                editor.putString("gender","女");
                                                editor.commit();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void fail(String result) {

                                    }
                                });
                                break;
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        //城市设置
        btnCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Uri uri;
    private void takePhoto() {
        file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+"headimg.png");
        if (file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(intent,TAKE_PHOTO);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectPhotoFromLocal() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,PHOTO_REQUEST_GALLERY);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK){
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    editor.putString("headimg_path",file.getAbsolutePath());
                    editor.commit();
                    circleImageView.setImageBitmap(bitmap);
                    LruChacheImgResource.saveBitmapToSDCard("headimg.png",bitmap);
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                Uri uri =data.getData();
                Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
                editor.putString("headimg_path",uri.getPath());
                editor.commit();
                circleImageView.setImageBitmap(bitmap);
                LruChacheImgResource.saveBitmapToSDCard("headimg.png",bitmap);
            case NICK_REQUEST:

                if (resultCode==NickModifyActivity.RESPONSE_CODE){
                    String nick = data.getStringExtra("nickname");
                    txtNick.setText(nick);
                }
        }
    }

}
