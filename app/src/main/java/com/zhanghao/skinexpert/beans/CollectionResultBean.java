package com.zhanghao.skinexpert.beans;

/**
 * Created by 黑曼巴ye on 2016/12/28.
 */

public class CollectionResultBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"pid":2930886,"doCollect":"add"}
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
        /**
         * pid : 2930886
         * doCollect : add
         */

        private int pid;
        private String doCollect;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getDoCollect() {
            return doCollect;
        }

        public void setDoCollect(String doCollect) {
            this.doCollect = doCollect;
        }
    }
}
