package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/27.
 */

public class ProductSearchWordBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"id":731,"content":"1908Mask Family 1908"},{"id":892,"content":"10度"},{"id":1880,"content":"12ways"},{"id":2919,"content":"12month"},{"id":2189,"content":"章光101"}]}
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
             * id : 731
             * content : 1908Mask Family 1908
             */

            private int id;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
