package com.zhanghao.skinexpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanghao.skinexpert.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class ProductGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<Map<String, Object>> list;
    private int type;

    public ProductGridViewAdapter(Context context, int type) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.type = type;

        switch (type) {
            case 0:
                String data = getDataJson("claslist.json");
                getData(data);
                break;
            case 1:
                String data1 = getDataJson("funclist.json");
                getData(data1);
                break;
            case 2:
                String data2 = getDataJson("androidBrand.json");
                getData(data2);
                break;
            case 3:
                String data3 = getDataJson("pricelist.json");
                getData1(data3);
                break;
            default:
                break;
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (type){
            case 0:
                convertView = inflater.inflate(R.layout.product_gridview_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_product_gridview_item);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return convertView;
    }

    private String getDataJson(String name) {
        ByteArrayOutputStream baos = null;
        try {
            InputStream inputStream = context.getAssets().open(name);
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                baos.flush();
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void getData(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> map = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map.put("id",jsonObject.getString("id"));
                map.put("name",jsonObject.getString("name"));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getData1(String data3) {
        try {
            JSONArray jsonArray = new JSONArray(data3);
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> map = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map.put("id",jsonObject.getString("id"));
                map.put("name",jsonObject.getString("brandChinaName")+jsonObject.getString("name"));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
