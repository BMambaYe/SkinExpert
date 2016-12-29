package com.zhanghao.skinexpert.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationSettingActivity extends AppCompatActivity {
    private Button btnBack;
    private ListView listView;
    private Context context;
    private String[] notifications = {"评论","回复","赞","投票"};
    private MyAdapter adapter;
    private boolean[] isReceives =new boolean[4];
    private String[] receiveStrings ={"isComment","isReply","isLiked","isVoted"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);
        context = NotificationSettingActivity.this;
        initData();
        initView();
        setOnClick();
    }

    private void initData() {
        NetWorkRequest.addJSONRequest(context, Constant.IS_RECEIVER_NOTIFICATION_GET+"a5b8027e668e92ccf2cd46077c2b34dd", new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                JSONObject jsonObject = (JSONObject) result;
                try {
                    JSONObject jsonObjectData= jsonObject.getJSONObject("data");
                    isReceives[0] = Boolean.parseBoolean(jsonObjectData.getString("isComment"));
                    isReceives[1] = Boolean.parseBoolean(jsonObjectData.getString("isReply"));
                    isReceives[2] = Boolean.parseBoolean(jsonObjectData.getString("isLiked"));
                    isReceives[3] = Boolean.parseBoolean(jsonObjectData.getString("isVoted"));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(String result) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.notification_setting_btn_back);
        listView = (ListView) findViewById(R.id.notification_setting_lv);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }
    class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return notifications.length;
        }

        @Override
        public Object getItem(int position) {
            return notifications[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.notification_setting_lv_item,parent,false);
            TextView textView = (TextView) convertView.findViewById(R.id.notification_set_lv_item_txt);
            ToggleButton toggleButton = (ToggleButton) convertView.findViewById(R.id.set_lv_item_togglebutton);
            if (isReceives[position]){
                toggleButton.setChecked(true);
            }else {
                toggleButton.setChecked(false);
            }
            textView.setText(notifications[position]);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int value;
                    if (isChecked){
                        value=1;
                    }else {
                        value=0;
                    }
                    NetWorkRequest.addJSONRequest(context, Constant.SET_RECEIVER_NOTIFICATION_GET +"a5b8027e668e92ccf2cd46077c2b34dd&attr="+ receiveStrings[position] + "&value=" + value, new NetWorkRequest.RequestCallBack() {
                        @Override
                        public void success(Object result) {
                        }
                        @Override
                        public void fail(String result) {

                        }
                    });

                }
            });
            return convertView;
        }
    }

}
