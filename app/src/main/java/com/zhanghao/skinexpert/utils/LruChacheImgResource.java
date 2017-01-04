package com.zhanghao.skinexpert.utils;

/**
 * Created by ${Rock} on 2016/11/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片三级缓存
 */

public class LruChacheImgResource  {
    private static  String externalCacheDirPath;
    private String path;
    private LruCache<String,Bitmap> lruCache ;
    public Bitmap bitmapnet;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                 bitmapnet = (Bitmap) msg.obj;
                if (bitmapnet==null){
                    Log.i("RockTest:","测试点2为空");
                }


            }
        }
    };
    public LruChacheImgResource(String path,String externalCacheDirPath) {
        this.path = path;
        externalCacheDirPath = this.externalCacheDirPath;
    }

    public Bitmap getBitmap(){
        long maxsize = Runtime.getRuntime().maxMemory();
        lruCache = new LruCache<String, Bitmap>((int) (maxsize/8)){
            /**
             *
             * @param key:从内存中读取或者存入的对象的名字
             * @param value：存取的对象
             * @return：返回的是对象所占的缓存
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        //当用户需要图片时，到缓存中找
        Bitmap bitmap = lruCache.get(getName(path));
        if (bitmap!=null){
           return bitmap;
        }else {
            //缓存找不到图片，到SD卡中查找
            Bitmap bitmap1 =getBitmapFromSDCard(getName(path));
            if (bitmap1!=null){
                lruCache.put(getName(path),bitmap1);
                return bitmap1;
            }else {
                getBitmapFromNet(getName(path));
                if (bitmapnet!=null){
                    return bitmapnet;
                }

            }
        }
        return null;
    }

    private void getBitmapFromNet(String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    if (conn.getResponseCode()==200){
                        Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                        lruCache.put(getName(path),bitmap);
                        saveBitmapToSDCard(getName(path),bitmap);
                        Message message = Message.obtain();
                        message.what=1;
                        message.obj=bitmap;

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static  void saveBitmapToSDCard(String name, Bitmap bitmap) {
        BufferedOutputStream bos = null;
        //将bitmap写入SD卡中
        try {
            bos =new BufferedOutputStream(new FileOutputStream(new File(externalCacheDirPath,name)));
            if (name.endsWith("png")||name.endsWith("PNG")){
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
//                bos.flush();
            }else {
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromSDCard(String name) {
        //获取存储卡中缓存的图片，需要传入activity

        Bitmap bitmap = BitmapFactory.decodeFile(externalCacheDirPath+File.separator+name);
        return bitmap;
    }

    //获取Bitmap为url信息的名字
    private String getName(String path) {
        String name = path.substring(path.lastIndexOf("/")+1,path.length());
        return name;
    }

}
