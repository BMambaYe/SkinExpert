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
import com.zhanghao.skinexpert.beans.BuyoutOrderListBean;
import com.zhanghao.skinexpert.beans.CollectionResultBean;
import com.zhanghao.skinexpert.beans.CommentListViewBean;
import com.zhanghao.skinexpert.beans.CommunityBean;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;
import com.zhanghao.skinexpert.beans.DetailAllDisgussBean;
import com.zhanghao.skinexpert.beans.DetailCommentBean;
import com.zhanghao.skinexpert.beans.DetailElementBean;
import com.zhanghao.skinexpert.beans.ElementDetailBean;
import com.zhanghao.skinexpert.beans.FatieBackBean;
import com.zhanghao.skinexpert.beans.HomeDataBean;
import com.zhanghao.skinexpert.beans.HotElementWordBean;
import com.zhanghao.skinexpert.beans.HotSearchWordBean;
import com.zhanghao.skinexpert.beans.LikeArticleBean;
import com.zhanghao.skinexpert.beans.LikeArticleResultBean;
import com.zhanghao.skinexpert.beans.ProductBean;
import com.zhanghao.skinexpert.beans.ProductDetailBean;
import com.zhanghao.skinexpert.beans.ProductLibraryBean;
import com.zhanghao.skinexpert.beans.ProductMoreBean;
import com.zhanghao.skinexpert.beans.ProductSearchWordBean;
import com.zhanghao.skinexpert.beans.RecommendOtherTagsBean;
import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;
import com.zhanghao.skinexpert.beans.RecommendTagsNameBean;
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

    /***********************************************秦如臻**************************************************************************/
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


    public static void getBeautifulBean(Context context, int id, final RequestCallBack callBack) {
        String path = "http://www.caimiapp.com/api_270/community/getCommunityList?token=&type=all&cmcid=" + id + "&collectionId=0&total=0&lastId=0&isFirst=1";
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BeautifulBean> beanRequest = new BeanRequest<>(path, BeautifulBean.class, new Response.Listener<BeautifulBean>() {
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

    public static void getRecommendTagsDataBean(Context context, final RequestCallBack callBack) {
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

    public static void getRecommendOtherTagsDataBean(Context context, int gid, final RequestCallBack callBack) {
        String path = "http://www.caimiapp.com/api_270/community/getAllRecommendCategoryIdByGenre?gid=" + gid;
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<RecommendOtherTagsBean> beanRequest = new BeanRequest<>(path, RecommendOtherTagsBean.class, new Response.Listener<RecommendOtherTagsBean>() {
            @Override
            public void onResponse(RecommendOtherTagsBean response) {
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

    public static void getRecommendTagsNameBean(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<RecommendTagsNameBean> beanRequest = new BeanRequest<>(Constant.RECOMMENDTAGSNAME, RecommendTagsNameBean.class, new Response.Listener<RecommendTagsNameBean>() {
            @Override
            public void onResponse(RecommendTagsNameBean response) {
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

    public static void getCommentListViewBean(Context context, int id, final RequestCallBack callBack) {
        String path = "http://www.caimiapp.com/api_301/community/getCommunityThreadComment?cmid=" + id + "&token=&total=0&lastId=0";
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<CommentListViewBean> beanRequest = new BeanRequest<>(path, CommentListViewBean.class, new Response.Listener<CommentListViewBean>() {
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

    public static void getUserInfo(Context context, final int uid, final int lastId, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<UserInfoContentBean> beanRequest = new BeanRequest<UserInfoContentBean>(Request.Method.POST, UserInfoContentBean.class, Constant.UserInfo, new Response.Listener<UserInfoContentBean>() {
            @Override
            public void onResponse(UserInfoContentBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token + "");
                map.put("uid", uid + "");
                map.put("lastId", lastId + "");
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    public static void getCommentSend(Context context, final int cmid, final String content, final String token, final int oid, final RequestCallBack callBack) {
        String path = "http://www.caimiapp.com/api_301/community/addCommunityThreadComment";
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token + "");
                map.put("cmid", cmid + "");
                map.put("content", content + "");
                map.put("oid", oid + "");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void getUserInfoHead(Context context, final int uid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<UserInfoHeadBean> beanRequest = new BeanRequest<UserInfoHeadBean>(Request.Method.POST, UserInfoHeadBean.class, Constant.UserInfoHead, new Response.Listener<UserInfoHeadBean>() {
            @Override
            public void onResponse(UserInfoHeadBean response) {
                callBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("uid", uid + "");
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    /*******************************************秦如臻******************************************************************************/

    /**
     * by RockGao
     */

    public static void addJSONRequest(Context context, String url, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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

    //手机号POST请求
    public static void phoneRequest(Context context, final String telephone, final RequestCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PHONE_REQUEST_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success(response);
                Log.i("RockTest:", "测试点:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("areaCode", "86");
                map.put("telephone", telephone);
                map.put("type", "1");
                return map;
            }

        };

        requestQueue.add(stringRequest);
    }

    //手机验证码请求

    public static void verificationCheck(Context context, final String telephone, final String code, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PHONE_REQUEST_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
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
                Map<String, String> map = new HashMap<>();
                map.put("areaCode", "86");
                map.put("telephone", telephone);
                map.put("verificationCode", code);
                map.put("type", "1");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    /*********************************************叶丙林****************************************************************************/
    //得到订单列表
    public static void postOrderList(Context context, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BuyoutOrderListBean> beanRequest = new BeanRequest<BuyoutOrderListBean>(Request.Method.POST, BuyoutOrderListBean.class, Constant.GET_ORDER_LIST,
                new Response.Listener<BuyoutOrderListBean>() {
                    @Override
                    public void onResponse(BuyoutOrderListBean response) {
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }


    //福利页面数据

    public static void getBenefitsBean(Context context, String token, int total, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<BenifitsBean> beanRequest = new BeanRequest<BenifitsBean>(Constant.BENIFITSBEAN + token + Constant.BENIFITSBEAN1 + total,
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

    //商品详情
    public static void getProductDetailBean(Context context, int id, String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductDetailBean> beanRequest = new BeanRequest<ProductDetailBean>(Constant.PRODUCT_DETAIL + id + Constant.PRODUCT_DETAIL1 + token, ProductDetailBean.class, new Response.Listener<ProductDetailBean>() {
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

    //得到商品底部评论
    public static void getDetailCommentBean(Context context, int pid, String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailCommentBean> beanRequest = new BeanRequest<>(Constant.PRODUCT_DETAIL_COMMENT + pid + Constant.PRODUCT_DETAIL_COMMENT1 + token + Constant.PRODUCT_DETAIL_COMMENT2, DetailCommentBean.class, new Response.Listener<DetailCommentBean>() {
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

    //得到商品成分详情列表
    public static void getDetailElementBean(Context context, int id, String token, String skinCode, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailElementBean> beanRequest = new BeanRequest<>(Constant.PRODUCT_DETAIL_ELMENT + id + Constant.PRODUCT_DETAIL_ELMENT1 + token + Constant.PRODUCT_DETAIL_ELMENT2 + skinCode, DetailElementBean.class, new Response.Listener<DetailElementBean>() {
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

    //立即购买请求
    public static void postBuyNow(Context context, final String token, final String buyout_id, final String type, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BUYNOW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success("申请购买成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("buyout_id", buyout_id + "");
                map.put("type", type);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    //商品想用请求
    public static void postCollection(Context context, final String token, final int pid, final String type, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<CollectionResultBean> beanRequest = new BeanRequest<CollectionResultBean>(Request.Method.POST, CollectionResultBean.class, Constant.POST_COLLECTION,
                new Response.Listener<CollectionResultBean>() {
                    @Override
                    public void onResponse(CollectionResultBean response) {
                        callBack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("pid", pid + "");
                map.put("type", type);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    //保存打分请求
    public static void getBaocunUsefeeling(Context context, String token, int id, int score, String comment) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constant.USEFEELING + token + Constant.USEFEELING1 + id + Constant.USEFEELING2 + score + Constant.USEFEELING3 + comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public static void getDetailAllDisguss(Context context, String token, int cmcid, int total, int lastid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<DetailAllDisgussBean> beanRequest = new BeanRequest<DetailAllDisgussBean>(Constant.DETAILALLDISGUSS + token + Constant.DETAILALLDISGUSS1 + cmcid + Constant.DETAILALLDISGUSS2 + total + Constant.DETAILALLDISGUSS3 + lastid + Constant.DETAILALLDISGUSS4, DetailAllDisgussBean.class, new Response.Listener<DetailAllDisgussBean>() {
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

    //社区评论喜欢
    public static void getAddCommunityLike(Context context, String token, int cmcid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constant.ADD_COMMUNITY_LIKE + token + Constant.ADD_COMMUNITY_LIKE1 + cmcid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success("点赞成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(stringRequest);
    }

    public static void getCancelCommunityLike(Context context, String token, int cmcid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constant.CANCEL_COMMUNITY_LIKE + token + Constant.CANCEL_COMMUNITY_LIKE1 + cmcid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success("取消成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.fail("访问有误");
            }
        });
        requestQueue.add(stringRequest);
    }

    //成分详情
    public static void getElementDetailBean(Context context, int element_id, String skinCode, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ElementDetailBean> beanRequest = new BeanRequest<>(Constant.ELEMENT_DETAIL + element_id + Constant.ELEMENTDETAIL1 + skinCode, ElementDetailBean.class, new Response.Listener<ElementDetailBean>() {
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

    public static void postSubmitOrder(Context context, final String token, final String id, final String credit, final String type, final String identityNumber, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SUBMITORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success("提交成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("id", id + "");
                map.put("credit", credit);
                map.put("type", type);
                map.put("identityNumber", identityNumber);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void postCancelOrder(Context context, final String token, final String id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.POST_CANCEL_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.success("取消成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("id", id);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void getProductBean(Context context, String token, int pid, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductBean> beanRequest = new BeanRequest<ProductBean>(Constant.PRODUCT + pid + Constant.PRODUCT1 + token, ProductBean.class, new Response.Listener<ProductBean>() {
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

    //发帖
    public static void getFatieBackBean(Context context, final String token, final String cmcid, final String image, final String content, final String type,
                                        final String tag_ids, final String tag_custom, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<FatieBackBean> beanRequest = new BeanRequest<FatieBackBean>(Request.Method.POST, FatieBackBean.class, Constant.FATIE,
                new Response.Listener<FatieBackBean>() {
                    @Override
                    public void onResponse(FatieBackBean response) {
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
                map.put("cmcid", cmcid);
                map.put("image", image);
                map.put("content", content);
                map.put("type", type);
                map.put("tag_ids", tag_ids);
                map.put("tag_custom", tag_custom);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    /*********************************************叶丙林****************************************************************************/

    //*******************************张浩****************************************
    //首页数据
    public static void getHomeDataBean(Context context, final String token, final String total, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<HomeDataBean> beanRequest = new BeanRequest<HomeDataBean>(Request.Method.POST, HomeDataBean.class, Constant.HOME_LIST,
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

    //产品库数据
    public static void getProductListDataBean(Context context, final String fid, final String bid, final String cid, final String price_id, final String eid,
                                              final String selectType, final String total, final String skinCode, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductLibraryBean> beanRequest = new BeanRequest<ProductLibraryBean>(Request.Method.POST, ProductLibraryBean.class, Constant.PRODUCT_LIBRARY_LIST,
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
                map.put("skinCode", skinCode);
                map.put("token", token);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    //搜索产品库数据
    public static void getProductKeyListDataBean(Context context, final String fid, final String bid, final String cid, final String price_id, final String eid,
                                                 final String selectType, final String key, final String total, final String skinCode, final String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        BeanRequest<ProductLibraryBean> beanRequest = new BeanRequest<ProductLibraryBean>(Request.Method.POST, ProductLibraryBean.class, Constant.PRODUCT_LIBRARY_LIST,
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
                map.put("keyword", key);
                map.put("total", total);
                map.put("skinCode", skinCode);
                map.put("token", token);
                return map;
            }
        };
        requestQueue.add(beanRequest);
    }

    //往期更多产品数据
    public static void getProductListMoreBean(Context context, String lastId, String total, String token, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.PRODUCT_MORE_LIST + "?lastId=" + lastId + "&total=" + total + "&token=" + token + "&sortType=" + "publishTime";
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

    //产品搜索数据
    public static void getProductSearchWords(Context context, String key, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.PRODUCT_SEARCH_WORD + key;
        BeanRequest<ProductSearchWordBean> beanRequest = new BeanRequest<ProductSearchWordBean>(Request.Method.GET, ProductSearchWordBean.class, url,
                new Response.Listener<ProductSearchWordBean>() {
                    @Override
                    public void onResponse(ProductSearchWordBean response) {
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

    //热门产品数据
    public static void getProductHotSearchWords(Context context, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.HOT_SEARCH_WORD;
        BeanRequest<HotSearchWordBean> beanRequest = new BeanRequest<HotSearchWordBean>(Request.Method.GET, HotSearchWordBean.class, url,
                new Response.Listener<HotSearchWordBean>() {
                    @Override
                    public void onResponse(HotSearchWordBean response) {
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

    //热门成分数据
    public static void getProductHotElementWords(Context context, String isRand, String keyword, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.HOT_ELEMENT_WORD + isRand + "&keyword=" + keyword;
        BeanRequest<HotElementWordBean> beanRequest = new BeanRequest<HotElementWordBean>(Request.Method.GET, HotElementWordBean.class, url,
                new Response.Listener<HotElementWordBean>() {
                    @Override
                    public void onResponse(HotElementWordBean response) {
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

    //文章信息数据
    public static void getLikeArticle(Context context, String token, String id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.GET_ARTICLE + token + "&id=" + id;
        BeanRequest<LikeArticleBean> beanRequest = new BeanRequest<LikeArticleBean>(Request.Method.GET, LikeArticleBean.class, url,
                new Response.Listener<LikeArticleBean>() {
                    @Override
                    public void onResponse(LikeArticleBean response) {
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

    //添加喜爱文章
    public static void addLikeArticle(Context context, String token, String id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.ADD_LIKE_ARTICLE + token + "&id=" + id;
        BeanRequest<LikeArticleResultBean> beanRequest = new BeanRequest<LikeArticleResultBean>(Request.Method.GET, LikeArticleResultBean.class, url,
                new Response.Listener<LikeArticleResultBean>() {
                    @Override
                    public void onResponse(LikeArticleResultBean response) {

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

    //取消喜爱文章
    public static void cancelLikeArticle(Context context, String token, String id, final RequestCallBack callBack) {
        requestQueue = Volley.newRequestQueue(context);
        String url = Constant.CANCEL_ARTICLE + token + "&id=" + id;
        BeanRequest<LikeArticleResultBean> beanRequest = new BeanRequest<LikeArticleResultBean>(Request.Method.GET, LikeArticleResultBean.class, url,
                new Response.Listener<LikeArticleResultBean>() {
                    @Override
                    public void onResponse(LikeArticleResultBean response) {
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
    //*********************************张浩******************************************

    /*
     通过此接口与用户可在需要访问网络的地方获取结果
      */
    public interface RequestCallBack {
        void success(Object result);

        void fail(String result);
    }

}
