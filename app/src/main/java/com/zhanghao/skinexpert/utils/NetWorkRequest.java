package com.zhanghao.skinexpert.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhanghao.skinexpert.beans.BeautifulBean;
import com.zhanghao.skinexpert.beans.BenifitsBean;
import com.zhanghao.skinexpert.beans.CommentListViewBean;
import com.zhanghao.skinexpert.beans.CommunityBean;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;
import com.zhanghao.skinexpert.beans.DetailAllDisgussBean;
import com.zhanghao.skinexpert.beans.DetailCommentBean;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ElementDetailBean;
import com.zhanghao.skinexpert.beans.HomeDataBean;
import com.zhanghao.skinexpert.beans.ProductBean;
import com.zhanghao.skinexpert.beans.ProductDetailBean;
import com.zhanghao.skinexpert.beans.ProductLibraryBean;
import com.zhanghao.skinexpert.beans.ProductMoreBean;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.beans.UserInfoContentBean;
import com.zhanghao.skinexpert.beans.UserInfoHeadBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黑曼巴ye on 2016/12/21.
 */

public class NetWorkRequest {
    private static RequestQueue requestQueue;

    public static void getBenefitsBean(Context context, int i, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BenifitsBean> beanRequest = new BeanRequest<BenifitsBean>(Constant.BENIFITSBEAN + i,
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
                                              final String selectType, final String total, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductLibraryBean> beanRequest = new BeanRequest<ProductLibraryBean>(Request.Method.POST, ProductLibraryBean.class, Constant.PRODUCTLIBRARYLIST,
                new Response.Listener<ProductLibraryBean>() {
                    @Override
                    public void onResponse(ProductLibraryBean response) {
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
                map.put("skinCode", "----");
                map.put("token", token);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    public static void getProductListMoreBean(Context context, String lastId, String total, String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.PRODUCTLIST + "?lastId=" + lastId + "&total=" + total + "&token=" + token + "&sortType=publishTime";
        BeanRequest<ProductMoreBean> beanRequest = new BeanRequest<ProductMoreBean>(Request.Method.GET, ProductMoreBean.class, url,
                new Response.Listener<ProductMoreBean>() {
                    @Override
                    public void onResponse(ProductMoreBean response) {
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

    public static void getCommunityBean(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<CommunityBean> beanRequest = new BeanRequest<CommunityBean>(Constant.COMMUNITYTAGS,
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

    public static void getCommunityListViewBean(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<CommunityListViewBean> beanRequest = new BeanRequest<CommunityListViewBean>(Constant.COMMUNITYLISTVIEW,
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

    public static void getProductDetailBean(Context context, int id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductDetailBean> beanRequest = new BeanRequest<ProductDetailBean>(Constant.PRODUCT_DETAIL + id + Constant.PRODUCT_DETAIL_TAKEN, ProductDetailBean.class, new Response.Listener<ProductDetailBean>() {
            @Override
            public void onResponse(ProductDetailBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getProductBean(Context context, int pid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductBean> beanRequest = new BeanRequest<ProductBean>(Constant.PRODUCT + pid + Constant.PRODUCT1, ProductBean.class, new Response.Listener<ProductBean>() {
            @Override
            public void onResponse(ProductBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getDetailAllDisguss(Context context, int cmcid, int total, int lastid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailAllDisgussBean> beanRequest = new BeanRequest<DetailAllDisgussBean>(Constant.DETAILALLDISGUSS + cmcid + Constant.DETAILALLDISGUSS1 + total + Constant.DETAILALLDISGUSS2 + lastid + Constant.DETAILALLDISGUSS3, DetailAllDisgussBean.class, new Response.Listener<DetailAllDisgussBean>() {
            @Override
            public void onResponse(DetailAllDisgussBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getDetailCommentBean(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailCommentBean> beanRequest = new BeanRequest<>(Constant.PRODUCT_DETAIL_COMMENT, DetailCommentBean.class, new Response.Listener<DetailCommentBean>() {
            @Override
            public void onResponse(DetailCommentBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getDetailElementBean(Context context, int id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailElementBean> beanRequest = new BeanRequest<>(Constant.PRODUCT_DETAIL_ELMENT + id + Constant.PRODUCT_DETAIL_ELMENT1, DetailElementBean.class, new Response.Listener<DetailElementBean>() {
            @Override
            public void onResponse(DetailElementBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getElementDetailBean(Context context, int element_id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ElementDetailBean> beanRequest = new BeanRequest<>(Constant.ELEMENT_DETAIL + element_id + Constant.PRODUCT_DETAIL_ELMENT1, ElementDetailBean.class, new Response.Listener<ElementDetailBean>() {
            @Override
            public void onResponse(ElementDetailBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }

    public static void getBeautifulBean(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BeautifulBean> beanRequest = new BeanRequest<>(Constant.BEAUTIFULBEAN, BeautifulBean.class, new Response.Listener<BeautifulBean>() {
            @Override
            public void onResponse(BeautifulBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }
    public static void getRecommendTagsDataBean(Context context,  final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<RecommendTagsDataBean> beanRequest = new BeanRequest<>(Constant.RECOMMENDTAGS1, RecommendTagsDataBean.class, new Response.Listener<RecommendTagsDataBean>() {
            @Override
            public void onResponse(RecommendTagsDataBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }
    public static void getCommentListViewBean(Context context,  final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<CommentListViewBean> beanRequest = new BeanRequest<>(Constant.COMMENT, CommentListViewBean.class, new Response.Listener<CommentListViewBean>() {
            @Override
            public void onResponse(CommentListViewBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(beanRequest);
    }
    public static void getUserInfo(Context context, final int uid, final int lastId, final String token,final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<UserInfoContentBean> beanRequest=new BeanRequest<UserInfoContentBean>(Request.Method.POST,UserInfoContentBean.class,Constant.UserInfo, new Response.Listener<UserInfoContentBean>() {
            @Override
            public void onResponse(UserInfoContentBean response) {
                callBack.success(response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String,String>();
                map.put("token",token+"");
                map.put("uid",uid+"");
                map.put("lastId",lastId+"");
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }
    public static void getUserInfoHead(Context context, final int uid,final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<UserInfoHeadBean> beanRequest=new BeanRequest<UserInfoHeadBean>(Request.Method.POST,UserInfoHeadBean.class,Constant.UserInfoHead, new Response.Listener<UserInfoHeadBean>() {
            @Override
            public void onResponse(UserInfoHeadBean response) {
                callBack.success(response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String,String>();
                map.put("uid",uid+"");
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    /**
     * by RockGao
     */
    //BeanRequest
    public static void getMyProductBean(Context context, String url,final RequestCallBack callBack){
        requestQueue = Volley.newRequestQueue(context);

//        BeanRequest<MyProductBean.DataBean> databeanRequest = new BeanRequest<MyProductBean.DataBean>(url,
//                MyProductBean.DataBean.class, new Response.Listener<MyProductBean.DataBean>() {
//            @Override
//            public void onResponse(MyProductBean.DataBean response) {
//                callBack.success(response);
//                Log.i("RockTest:","网络访问成功");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callBack.fail("网络访问失败");
//                Log.i("RockTest:","网络访问失败");
//
//            }
//        });
//        requestQueue.add(databeanRequest);
    }
    //JsonRequest
    public static void addJSONRequest(Context context, String url, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                 callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("网络加载错误");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    //手机验证码请求

    public static void verificationCheck(Context context, final String telephone, final String code ,final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PHONE_REQUEST_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    callBack.success(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("areaCode","86");
                map.put("telephone",telephone);
                map.put("verificationCode",code);
                map.put("type","1");
                return map;
            }


        };

        requestQueue.add(stringRequest);
    }
    //手机号POST请求
    public static void phoneRequest(Context context, final String telephone,final RequestCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PHONE_REQUEST_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success(response);
                Log.i("RockTest:","测试点:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("areaCode","86");
                map.put("telephone",telephone);
                map.put("type","1");
                return map;
            }

        };

        requestQueue.add(stringRequest);
    }
    /*
     通过此接口与用户可在需要访问网络的地方获取结果
      */
    public interface RequestCallBack {
        void success(Object result);

        void fail(String result);
    }

}
