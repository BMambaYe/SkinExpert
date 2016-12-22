package com.zhanghao.skinexpert.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.beans.HomeDataBean;
import com.zhanghao.skinexpert.beans.ProductListBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黑曼巴ye on 2016/12/21.
 */

public class NetWorkRequest {
    private static RequestQueue requestQueue;

    public static void getBenefitsBean(Context context, int i, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BenifitsBean> beanRequest = new BeanRequest<BenifitsBean>(Constant.BENIFITSBEAN,
                BenifitsBean.class, new Response.Listener<BenifitsBean>() {
            @Override
            public void onResponse(BenifitsBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("网络连接错误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getHomeDataBean(Context context, final String token, final String total, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<HomeDataBean> beanRequest = new BeanRequest<HomeDataBean>(Request.Method.POST, HomeDataBean.class, Constant.MAINLIST,
                new Response.Listener<HomeDataBean>() {
                    @Override
                    public void onResponse(HomeDataBean response) {
                        callBack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("网络连接错误");
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
        requestQueue.add(beanRequest);
    }

    public static void getProductListDataBean(Context context, final String fid, final String bid, final String cid, final String price_id, final String eid,
                                          final String selectType, final String total, final String skinCode, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductListBean> beanRequest = new BeanRequest<ProductListBean>(Request.Method.POST, ProductListBean.class, Constant.PRODUCTLIBRARYLIST,
                new Response.Listener<ProductListBean>() {
                    @Override
                    public void onResponse(ProductListBean response) {
                        callBack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("网络连接错误");
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
