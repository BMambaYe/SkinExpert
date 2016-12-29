package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/28.
 */

public class HotElementWordBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"id":1225,"name":"羟苯甲酯"},{"id":1297,"name":"苯氧乙醇"},{"id":1785,"name":"变性乙醇"},{"id":1375,"name":"透明质酸钠"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1225
             * name : 羟苯甲酯
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
}
