package com.zhanghao.skinexpert.utils;

/**
 * Created by RockGao on 2016/12/22.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.skinexpert.R;

import net.ugen.ugenframework.Callback;

/**
 * 封装多种弹窗
 */
public  class Dialog {

    //普通弹窗
    public static void dialogShow(Context context, final String title, String message, String positivemsg, String negativemsg, final Callback.AlertCallBack callBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positivemsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.onOkClick();
            }
        });
        builder.setNegativeButton(negativemsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callBack.onCancelClick();
            }
        });
        builder.create().show();
    }

    //底部弹窗
    public static void dialogShowButtom(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog myDialog = builder.create();
        myDialog.show();
        Window window =myDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popupAnimation);
        View view = View.inflate(context,R.layout.alert_dialog_view_layout,null);
        TextView textView1 = (TextView) view.findViewById(R.id.dialog_exit);
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_cancel);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "退出", Toast.LENGTH_SHORT).show();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        window.setContentView(view);
        //设置横向全屏
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

    }

    //底部弹窗,设置布局为ListView
    public static void dialogShowButtomAuto(final Context context,String[] strings){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog myDialog = builder.create();
        myDialog.show();
        Window window =myDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popupAnimation);
        View view = View.inflate(context,R.layout.dialog_show_buttom_auto_view,null);
        Button btnToTest = (Button) view.findViewById(R.id.dialog_show_auto_test);
        Button btnCancel = (Button) view.findViewById(R.id.dialog_show_auto_btn_cancel);
//        ListView listView = (ListView) view.findViewById(R.id.dialog_show_auto_lv);

        //重新进入测试
        btnToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        window.setContentView(view);
        //设置横向全屏
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

    }

    class MyAdapter extends BaseAdapter{
        private Context context;
        private String[] strings;

        public MyAdapter(Context context, String[] strings) {
            this.context = context;
            this.strings = strings;
        }

        @Override
        public int getCount() {
            return strings.length;
        }

        @Override
        public Object getItem(int position) {
            return strings[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dialog_show_buttom_auto_lv_item,parent,false);
            final TextView textView = (TextView) convertView.findViewById(R.id.dialog_show_auto_lv_txt);
            textView.setText(strings[position]);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return null;
        }
    }

}
