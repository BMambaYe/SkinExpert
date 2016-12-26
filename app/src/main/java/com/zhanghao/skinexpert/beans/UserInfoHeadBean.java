package com.zhanghao.skinexpert.beans;

/**
 * Created by Administrator on 2016/12/26.
 */

public class UserInfoHeadBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"gender":"女","nick":"Vivi","headface":"http://img.ilikelabs.com/Uploads/172/172254_0-140-140@1e_160w_160h_1c_0i_1o_1x.jpg","userType":"expert","title":"化妆品配方工程师","userInfo":"从事化妆品行业10年，对国内外化妆品原料、配方、品牌发展有深入研究，精通化妆品生产制造工艺流程。","signin_count":0,"communityCategoryAttentionData":"{\"105\":42544,\"9\":156032,\"114\":156042}","skinCode":"3333","skinType":"OSNT","likeSum":2575}
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
         * gender : 女
         * nick : Vivi
         * headface : http://img.ilikelabs.com/Uploads/172/172254_0-140-140@1e_160w_160h_1c_0i_1o_1x.jpg
         * userType : expert
         * title : 化妆品配方工程师
         * userInfo : 从事化妆品行业10年，对国内外化妆品原料、配方、品牌发展有深入研究，精通化妆品生产制造工艺流程。
         * signin_count : 0
         * communityCategoryAttentionData : {"105":42544,"9":156032,"114":156042}
         * skinCode : 3333
         * skinType : OSNT
         * likeSum : 2575
         */

        private String gender;
        private String nick;
        private String headface;
        private String userType;
        private String title;
        private String userInfo;
        private int signin_count;
        private String communityCategoryAttentionData;
        private String skinCode;
        private String skinType;
        private int likeSum;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getHeadface() {
            return headface;
        }

        public void setHeadface(String headface) {
            this.headface = headface;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(String userInfo) {
            this.userInfo = userInfo;
        }

        public int getSignin_count() {
            return signin_count;
        }

        public void setSignin_count(int signin_count) {
            this.signin_count = signin_count;
        }

        public String getCommunityCategoryAttentionData() {
            return communityCategoryAttentionData;
        }

        public void setCommunityCategoryAttentionData(String communityCategoryAttentionData) {
            this.communityCategoryAttentionData = communityCategoryAttentionData;
        }

        public String getSkinCode() {
            return skinCode;
        }

        public void setSkinCode(String skinCode) {
            this.skinCode = skinCode;
        }

        public String getSkinType() {
            return skinType;
        }

        public void setSkinType(String skinType) {
            this.skinType = skinType;
        }

        public int getLikeSum() {
            return likeSum;
        }

        public void setLikeSum(int likeSum) {
            this.likeSum = likeSum;
        }
    }
}
