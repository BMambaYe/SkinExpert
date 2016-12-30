package com.zhanghao.skinexpert.utils;

/**
 * Created by 黑曼巴ye on 2016/12/21.
 */

public class Constant {

    //public static String TOKEN = "a5b8027e668e92ccf2cd46077c2b34dd";
    public static final String ACCOUNT = "15676319144";
    public static final String PASSWORD = "hz12345678";

    //********************高岩*********************//
    public static final String USERNAME = "Rock";
    public static final String SKINT_TEST_RESULT_URL = "http://www.caimiapp.com/syjftda_c/";
    public static final String SKINT_TEST_DESCRIPTION = "http://www.caimiapp.com/sy16_c/";
    public static final String SKIN_TEST_RESULT_DB = "skintestresult";
    public static final String DB_SKIN_TEST_INFO = "skintestinfo";
    public static final String SKIN_FUND_URL = "http://www.caimiapp.com/wdwdjjo/?token=a5b8027e668e92ccf2cd46077c2b34dd";
    public static final String SKIN_FUND_REDEMPTION_URL_GET = "http://www.caimiapp.com/api_301/buyout/getBuyoutList?token=";
    public static final String IS_RECEIVER_NOTIFICATION_GET = "http://www.caimiapp.com/api_301/user/getUserMessageOpt?token=";
    public static final String SET_RECEIVER_NOTIFICATION_GET = "http://www.caimiapp.com/api_301/user/setUserMessageOpt?token=";
    public static final String MY_PRODUCT_WANGTED = "http://www.caimiapp.com/api_301/user/myProduct?token=";
    public static final String MY_PRODUCT_USED = "http://www.caimiapp.com/api_301/user/myProduct?token=";
    public static final String PHONE_REQUEST_POST = "http://www.caimiapp.com/api_301/user/sendUserVerificationCode";
    public static final String VERFICATION_CODE_CHECK = "http://www.caimiapp.com/api_301/user/checkVerificationCode";
    //********************高岩*********************//

    //***********************叶丙林*****************************************//
    public static final String BENIFITSBEAN = "http://www.caimiapp.com/api_301/buy/getAllBuyProductList?token=";
    public static final String BENIFITSBEAN1 = "&total=";
    public static final String PRODUCT_DETAIL = "http://www.caimiapp.com/api_301/product/getProductDetail?uniqueId=864394010202606&id=";
    public static final String PRODUCT_DETAIL1 = "&token=";
    public static final String PRODUCT_DETAIL_COMMENT = "http://www.caimiapp.com/api_290/product/getProductUserReviewList?pid=";
    public static final String PRODUCT_DETAIL_COMMENT1 = "&token=";
    public static final String PRODUCT_DETAIL_COMMENT2 = "&type=top10";
    public static final String PRODUCT_DETAIL_ELMENT = "http://www.caimiapp.com/api_290/product/getProductElement?pid=";
    public static final String PRODUCT_DETAIL_ELMENT1 = "&token=";
    public static final String PRODUCT_DETAIL_ELMENT2 = "&skinCode=";

    public static final String USEFEELING = "http://www.caimiapp.com/api_301/product/setIsUse?token=";
    public static final String USEFEELING1 = "&id=";
    public static final String USEFEELING2 = "&score=";
    public static final String USEFEELING3 = "&comment=";

    public static final String EXPERTSUGGESTION = "http://www.caimiapp.com/cpzjjyu/?token=";
    public static final String EXPERTSUGGESTION1 = "&id=";
    public static final String EXPERTSUGGESTION2 = "&skin=";

    public static final String DETAILALLDISGUSS = "http://www.caimiapp.com/api_301/community/getCommunityList?token=";
    public static final String DETAILALLDISGUSS1 = "&type=allProduct&cmcid=";
    public static final String DETAILALLDISGUSS2 = "&total=";
    public static final String DETAILALLDISGUSS3 = "&lastId=";
    public static final String DETAILALLDISGUSS4 = "&isFirst=1";

    public static final String ADD_COMMUNITY_LIKE = "http://www.caimiapp.com/api_301/community/addCommunityThreadLike?token=";
    public static final String ADD_COMMUNITY_LIKE1 = "&cmid=";

    public static final String CANCEL_COMMUNITY_LIKE = "http://www.caimiapp.com/api_301/community/cancelCommunityThreadLike?token=";
    public static final String CANCEL_COMMUNITY_LIKE1 = "&cmid=";

    public static final String ELEMENT_DETAIL = "http://www.caimiapp.com/api_240/product/getElementDetail?id=";
    public static final String ELEMENTDETAIL1 = "&skinCode=";

    public static final String SUBMITORDER = "http://www.caimiapp.com/api_301/buyout/placeBuyoutOrder";

    public static final String GET_ORDER_LIST = "http://www.caimiapp.com/api_301/buyout/getBuyoutOrderList";

    public static final String POST_CANCEL_ORDER = "http://www.caimiapp.com/api_301/buyout/cancelBuyoutOrder";

    public static final String PRODUCT = "http://www.caimiapp.com/api_301/product/getProductList?pid=";
    public static final String PRODUCT1 = "&token=";

    public static final String BENEFITS_IMG1 = "http://www.caimiapp.com/fllbas/images/yushou.png";
    public static final String BENEFITS_IMG2 = "http://www.caimiapp.com/fllbas/images/shuoming.png";

    public static final String FATIE = "http://www.caimiapp.com/api_301/community/addCommunityThread";
    public static final String POST_COLLECTION = "http://www.caimiapp.com/api_301/product/doCollect";
    public static final String BUYNOW = "http://www.caimiapp.com/api_301/buyout/orderConfirmation";

    //**********************叶丙林**************************************//

    //*************************秦如臻*********************************//

    //发现页面listview（热门精选）的url
    public static final String COMMUNITYLISTVIEW = "http://www.caimiapp.com/api_301/community/getCommunityList?token=";
    public static final String COMMUNITYLISTVIEW2 = "&type=recommend&cmcid=0&total=0&lastId=0&isFirst=1";

    //发现页面horizontalscrollview中标签的url
    public static final String COMMUNITYTAGS = "http://www.caimiapp.com/api_301/community/getCommunityRecommendTagList";

    //普通标签的url
    public static final String ALLTAGS = "http://www.caimiapp.com/api_270/community/getCommunityList?token=";
    public static final String ALLTAGS2 = "&type=all&cmcid=";
    public static final String ALLTAGS3 = "&collectionId=0&total=0&lastId=0&isFirst=1";

    //投票标签的url
    public static final String VOTELISTVIEW = "http://www.caimiapp.com/api_270/community/getCommunityList?token=";
    public static final String VOTELISTVIEW2 = "&type=all&cmcid=";
    public static final String VOTELISTVIEW3 = "&total=0&lastId=0&isFirst=1&categoryType=vote";

    //评论的url
    public static final String COMMENTLISTVIEW = "http://www.caimiapp.com/api_301/community/getCommunityThreadComment?cmid=";
    public static final String COMMENTLISTVIEW2 = "&token=";
    public static final String COMMENTLISTVIEW3 = "&total=0&lastId=0";

    //用户发帖信息的url
    public static final String UserInfo = "http://www.caimiapp.com/api_301/user/getUserCommunityThread";
    public static final String UserInfoHead = "http://www.caimiapp.com/api_301/user/getUserBasicInfo";

    //发送评论的post请求url
    public static final String COMMENTSEND = "http://www.caimiapp.com/api_301/community/addCommunityThreadComment";

    //更多点进去的热门分类所对应的所有标签的url
    public static final String RECOMMENDTAGSREMEN = "http://www.caimiapp.com/api_270/community/getCategoryByTitle?title=&lastId=0&total=0&isHot=1";

    //*************************秦如臻************************************//

    //*******************************张浩****************************************//
    //首页
    public static final String HOME_LIST = "http://www.caimiapp.com/api_301/main/getMainList";
    //产品库
    public static final String PRODUCT_LIBRARY_LIST = "http://www.caimiapp.com/api_301/product/getProductLibraryList";
    //进入测试肤质
    public static final String INTER_TEST = "http://www.caimiapp.com/syhyjsu/";
    //开始测试肤质
    public static final String START_TEST = "http://www.caimiapp.com/csjst/";
    //更多往期产品
    public static final String PRODUCT_MORE_LIST = "http://www.caimiapp.com/api_301/product/getProductList";
    //邀请好友
    public static final String INVITE_FRIENDS = "http://www.caimiapp.com/wdfxhyp/?uid=0&source=app";
    //关于美肤家
    public static final String ABOUT_SKIN = "http://www.caimiapp.com/html/2.30/wode_guanyumeifujia/";
    //首页文章
    public static final String ARTICLE1 = "http://www.caimiapp.com/ydxqj/?id=";
    public static final String ARTICLE2 = "&source=app&token=";
    //搜索热门产品
    public static final String HOT_SEARCH_WORD = "http://www.caimiapp.com/api_301/product/getProductSearchPromptHot";
    //搜索热门成分
    public static final String HOT_ELEMENT_WORD = "http://www.caimiapp.com/api_301/product/getHotElementList?isRand=";
    //搜索提示
    public static final String PRODUCT_SEARCH_WORD = "http://www.caimiapp.com/api_301/product/getProductSearchPrompt?key=";
    //判断文章是否收藏
    public static final String GET_ARTICLE = "http://www.caimiapp.com/api_301/Read/getReadDetail?token=";
    //文章收藏
    public static final String ADD_LIKE_ARTICLE = "http://www.caimiapp.com/api_301/Read/addReadLike?token=";
    //取消文章收藏
    public static final String CANCEL_ARTICLE = "http://www.caimiapp.com/api_301/Read/cancelReadLike?token=";
    //文章评论
    public static final String COMMENT_ARTICLE = "http://www.caimiapp.com/api_301/Read/getReadComment?id=";
    //用户举报
    public static final String REPORT_USER = "http://www.caimiapp.com/api_301/Read/reportReadComment?id=";
    //用户评论
    public static final String COMMENT_USER_ARTICLE = "http://www.caimiapp.com/api_301/Read/addReadComment";
    //*******************************张浩****************************************//
}
