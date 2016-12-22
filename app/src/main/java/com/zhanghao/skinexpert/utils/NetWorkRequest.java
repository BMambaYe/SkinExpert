package com.zhanghao.skinexpert.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.beans.CommunityBean;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黑曼巴ye on 2016/12/21.
 */

public class NetWorkRequest {
    private static RequestQueue requestQueue;

    public static void postMainData(Context context, String path, final String token, final String total, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callBack.success(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.fail("访问有误");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("total", total);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void postDetailData(Context context, final String path, final String fid, final String bid, final String cid, final String price_id, final String eid,
                                      final String selectType, final String total, final String skinCode, final String token, final RequestCallBack callBack) {

        requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callBack.success(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.fail("访问有误");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("fid", fid);
                map.put("bid", bid);
                map.put("cid", cid);
                map.put("price_id", price_id);
                map.put("eid", eid);
                map.put("selectType", selectType);
                map.put("total", total);
                map.put("skinCode", skinCode);
                map.put("token", token);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
    public static void getBenefitsBean(Context context, int i, final RequestCallBack callBack){
        requestQueue=Volley.newRequestQueue(context);
        BeanRequest<BenifitsBean> beanRequest =new BeanRequest<BenifitsBean>(Constant.BENIFITSBEAN,
                BenifitsBean.class, new Response.Listener<BenifitsBean>() {
            @Override
            public void onResponse(BenifitsBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(beanRequest);
    }
    public static void getCommunityBean(Context context,final RequestCallBack callBack){
        requestQueue=Volley.newRequestQueue(context);
        BeanRequest<CommunityBean> beanRequest =new BeanRequest<CommunityBean>(Constant.COMMUNITYTAGS,
                CommunityBean.class, new Response.Listener<CommunityBean>() {
            @Override
            public void onResponse(CommunityBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(beanRequest);
    }
    public static void getCommunityListViewBean(Context context,final RequestCallBack callBack){
        requestQueue=Volley.newRequestQueue(context);
        BeanRequest<CommunityListViewBean> beanRequest =new BeanRequest<CommunityListViewBean>(Constant.COMMUNITYLISTVIEW,
                CommunityListViewBean.class, new Response.Listener<CommunityListViewBean>() {
            @Override
            public void onResponse(CommunityListViewBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(beanRequest);
    }

    /*
     通过此接口与用户可在需要访问网络的地方获取结果
      */
    public interface RequestCallBack {
        void success(Object result);

        void fail(String result);
    }

}
