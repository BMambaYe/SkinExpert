package com.zhanghao.skinexpert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import java.io.File;

public class PostActivity extends AppCompatActivity {

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private EditText et_post;
    private PopupWindow popupWindow;
    private LinearLayout ll_pic_container;
    private File tempFile;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private int cmcid;
    private String tag_ids = "";
    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        et_post = ((EditText) findViewById(R.id.et_post));
        ll_pic_container = ((LinearLayout) findViewById(R.id.ll_post_pic_container));
        cmcid = getIntent().getIntExtra("cmcmid", 0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_post_back:
                onBackPressed();
                break;
            case R.id.tv_post_send:
                //todo发送帖子
                if (!et_post.getText().toString().equals("")) {
                    if (cmcid != 0) {
                        NetWorkRequest.getFatieBackBean(this, Constant.TOKEN, cmcid + "", image,
                                et_post.getText().toString(), "product", tag_ids, "", new NetWorkRequest.RequestCallBack() {
                                    @Override
                                    public void success(Object result) {

                                    }

                                    @Override
                                    public void fail(String result) {
                                    }
                                });
                    } else {
                        NetWorkRequest.getFatieBackBean(this, Constant.TOKEN, cmcid + "", image,
                                et_post.getText().toString(), "", tag_ids, "", new NetWorkRequest.RequestCallBack() {
                                    @Override
                                    public void success(Object result) {
                                    }

                                    @Override
                                    public void fail(String result) {
                                    }
                                });
                    }
                    setResult(110);
                    finish();
                }
                break;
            case R.id.img_post_photo:
                //todo选取图片
                initPopupWindow();
                break;
            case R.id.img_post_add_tag:
                //todo搜索标签并选取
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(PostActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                //得到了数据图片
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ll_pic_container.addView(imageView);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    * 判断sdcard是否被挂载
      */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
          *  * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_win_creame, null);
        //设置popupWindow焦点
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        TextView tv_from_xiangce = (TextView) view.findViewById(R.id.tv_pop_creame_fromxiangce);
        TextView tv_takephoto = (TextView) view.findViewById(R.id.tv_pop_creame_takephoto);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.tv_pop_creame_quxiao);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

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
        popupWindow.showAtLocation(findViewById(R.id.activity_post), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);


        tv_from_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016/12/27 从相册中选取 照片并形成缩略图
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                closePopupWindow();

            }
        });

        tv_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016/12/27 照片并形成缩略图
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            PHOTO_FILE_NAME);
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                closePopupWindow();

            }
        });

        tv_quxiao.setOnClickListener(dismissPopupListener);

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
