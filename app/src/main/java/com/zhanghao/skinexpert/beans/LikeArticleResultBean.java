package com.zhanghao.skinexpert.beans;

/**
 * Created by zhanghao on 2016/12/28.
 */

public class LikeArticleResultBean {

    /**
     * error_code : 46002
     * message : 该阅读已经赞过
     * data : {}
     */

    private int error_code;
    private String message;
    private DataBean data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
