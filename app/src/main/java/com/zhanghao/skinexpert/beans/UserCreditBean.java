package com.zhanghao.skinexpert.beans;

/**
 * Created by 黑曼巴ye on 2016/12/29.
 */

public class UserCreditBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"credit":5001,"total_credit":5001}
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
         * credit : 5001
         * total_credit : 5001
         */

        private int credit;
        private int total_credit;

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getTotal_credit() {
            return total_credit;
        }

        public void setTotal_credit(int total_credit) {
            this.total_credit = total_credit;
        }
    }
}
