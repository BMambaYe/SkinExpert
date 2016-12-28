package com.zhanghao.skinexpert.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;

import java.io.File;
import java.io.IOException;

public class UserInfoSettingActivity extends AppCompatActivity {
    private Button btnBack;
    private ImageView imgHead;
    private EditText editNickName;
    private EditText editPwd;
    private Button btnFinsh;
    private Context context;
    private String[] dialogItems = {"拍照","本地相册选择"};
    private static final  int TAKE_PHOTO=0;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        context = UserInfoSettingActivity.this;
        initView();
        setOnClick();
    }


    private void initView() {
        btnBack = (Button) findViewById(R.id.account_info_set_btn_back);
        btnFinsh = (Button) findViewById(R.id.account_info_btn_finish);
        imgHead = (ImageView) findViewById(R.id.account_info_set_head_img);
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
        btnFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
                                Toast.makeText(context, "拍照", Toast.LENGTH_SHORT).show();
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
    private Uri uri;
    private void takePhoto() {
        file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+"headimg");
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK){

                }
                break;

        }
    }
}
