package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class RecommendTagsNameBean {

    /**
     * error_code : 0
     * message : 成功
     * data : [{"id":6,"name":"晒物"},{"id":5,"name":"彩妆"},{"id":4,"name":"护肤"},{"id":7,"name":"参与"},{"id":3,"name":"照片"},{"id":8,"name":"其他"}]
     */

    private int error_code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * name : 晒物
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
