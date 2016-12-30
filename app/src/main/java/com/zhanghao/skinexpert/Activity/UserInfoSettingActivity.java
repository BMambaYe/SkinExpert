package com.zhanghao.skinexpert.Activity;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import com.zhanghao.skinexpert.MainActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.RegisterUseData;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.view.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UserInfoSettingActivity extends AppCompatActivity {
    private Button btnBack;
    private CircleImageView imgHead;
    private EditText editNickName;
    private EditText editPwd;
    private String[] dialogItems = {"拍照","本地相册选择"};
    private static final  int TAKE_PHOTO=0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private File file;
    private String nickNmae;
    private String pwd;
    private String phone;
    private String code;
    private Button btnFinsh;
    private Context context;
    private String token;
    private RegisterUseData.DataBean.UserDataBean userData;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        context = UserInfoSettingActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        sp = getSharedPreferences("user_info",MODE_PRIVATE);
        editor  = sp.edit();
    }


    private void initView() {
        btnBack = (Button) findViewById(R.id.account_info_set_btn_back);
        btnFinsh = (Button) findViewById(R.id.account_info_btn_finish);
        imgHead = (CircleImageView) findViewById(R.id.account_info_set_head_img);
        editNickName = (EditText) findViewById(R.id.account_info_edit_nickname);
        editPwd = (EditText) findViewById(R.id.account_info_edit_pwd);

    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //完成
        btnFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickNmae = editNickName.getText().toString();
                pwd = editPwd.getText().toString();
                if (nickNmae!=null&&!"".equals(nickNmae.trim())){

                    if (pwd!=null&&pwd.trim().length()>=6) {

                        NetWorkRequest.AccountInfoVerification(context, phone, pwd, nickNmae, code, new NetWorkRequest.RequestCallBack() {
                            @Override
                            public void success(Object result) {
                                JSONObject jsonObject = (JSONObject) result;
                                try {
                                    Log.i("RockTest:", "测试点:" + jsonObject.getString("message"));
                                    if ("成功".equals(jsonObject.getString("message"))) {
                                        setJsonDATA(jsonObject);
                                        editor.putString("token", token);
                                        editor.putString("nick", userData.getNick());
                                        editor.putString("skinCode", userData.getSkinCode());
                                        editor.putString("skinType", userData.getSkinType());
                                        editor.commit();
                                        Intent intentToMainAct = new Intent(context, MainActivity.class);
                                        intentToMainAct.putExtra("isToFragmentMe", true);
                                        startActivity(intentToMainAct);
                                        finish();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void fail(String result) {

                            }
                        });
                    }else {
                        Toast.makeText(context, "请输入6位以上密码", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //设置头像
        imgHead.setOnClickListener(new View.OnClickListener() {
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
    }

    private void setJsonDATA(JSONObject jsonObject) {
        userData= new RegisterUseData.DataBean.UserDataBean();
        JSONObject jsonObjectData = null;
        try {
            jsonObjectData = jsonObject.getJSONObject("data");
            token = jsonObjectData.getString("userToken");
            JSONObject jsonUserData = jsonObjectData.getJSONObject("userData");
            userData.setUid((Integer) jsonUserData.get("uid")) ;
            userData.setGender(jsonObjectData.getString("gender"));
            userData.setNick(jsonObjectData.getString("nick"));
            userData.setMobile(jsonObjectData.getString("mobile"));
            userData.setCommunityCategoryAttentionData(jsonObjectData.getString("communityCategoryAttentionData"));
            userData.setSkinCode(jsonObjectData.getString("skinCode"));
            userData.setSkinType(jsonObjectData.getString("skinType"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                    imgHead.setImageBitmap(bitmap);
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                Uri uri =data.getData();
                Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
                imgHead.setImageBitmap(bitmap);

        }
    }
}
