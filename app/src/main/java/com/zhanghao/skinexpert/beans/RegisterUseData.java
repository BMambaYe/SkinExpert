package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by RockGao on 2016/12/29.
 */

public class RegisterUseData {

    /**
     * error_code : 0
     * message : 成功
     * data : {"userToken":"e448e66c88edea4751b58fb797ed6648","userData":{"uid":389214,"gender":"男","nick":"MFJ67404","headface":"","userType":"unActivated","mobile":"18317173185","title":"","userInfo":"","skin":"","birthday":"1992-07-20","location":"","defaultMessageRead":0,"lastUpdateTimestamp":1482978631,"signin_count":0,"communityCategoryAttentionData":"{\"247955\":157705,\"263115\":155095}","createTime":"2016-12-21 10:14:21","skinCode":"1203","skinType":"DSPT","completeDegree":0.33,"associatedPartner":["weibo"]},"dataType":"existUser"}
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
         * userToken : e448e66c88edea4751b58fb797ed6648
         * userData : {"uid":389214,"gender":"男","nick":"MFJ67404","headface":"","userType":"unActivated","mobile":"18317173185","title":"","userInfo":"","skin":"","birthday":"1992-07-20","location":"","defaultMessageRead":0,"lastUpdateTimestamp":1482978631,"signin_count":0,"communityCategoryAttentionData":"{\"247955\":157705,\"263115\":155095}","createTime":"2016-12-21 10:14:21","skinCode":"1203","skinType":"DSPT","completeDegree":0.33,"associatedPartner":["weibo"]}
         * dataType : existUser
         */

        private String userToken;
        private UserDataBean userData;
        private String dataType;

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public UserDataBean getUserData() {
            return userData;
        }

        public void setUserData(UserDataBean userData) {
            this.userData = userData;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public static class UserDataBean {
            /**
             * uid : 389214
             * gender : 男
             * nick : MFJ67404
             * headface :
             * userType : unActivated
             * mobile : 18317173185
             * title :
             * userInfo :
             * skin :
             * birthday : 1992-07-20
             * location :
             * defaultMessageRead : 0
             * lastUpdateTimestamp : 1482978631
             * signin_count : 0
             * communityCategoryAttentionData : {"247955":157705,"263115":155095}
             * createTime : 2016-12-21 10:14:21
             * skinCode : 1203
             * skinType : DSPT
             * completeDegree : 0.33
             * associatedPartner : ["weibo"]
             */

            private int uid;
            private String gender;
            private String nick;
            private String headface;
            private String userType;
            private String mobile;
            private String title;
            private String userInfo;
            private String skin;
            private String birthday;
            private String location;
            private int defaultMessageRead;
            private int lastUpdateTimestamp;
            private int signin_count;
            private String communityCategoryAttentionData;
            private String createTime;
            private String skinCode;
            private String skinType;
            private double completeDegree;
            private List<String> associatedPartner;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getSkin() {
                return skin;
            }

            public void setSkin(String skin) {
                this.skin = skin;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getDefaultMessageRead() {
                return defaultMessageRead;
            }

            public void setDefaultMessageRead(int defaultMessageRead) {
                this.defaultMessageRead = defaultMessageRead;
            }

            public int getLastUpdateTimestamp() {
                return lastUpdateTimestamp;
            }

            public void setLastUpdateTimestamp(int lastUpdateTimestamp) {
                this.lastUpdateTimestamp = lastUpdateTimestamp;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public double getCompleteDegree() {
                return completeDegree;
            }

            public void setCompleteDegree(double completeDegree) {
                this.completeDegree = completeDegree;
            }

            public List<String> getAssociatedPartner() {
                return associatedPartner;
            }

            public void setAssociatedPartner(List<String> associatedPartner) {
                this.associatedPartner = associatedPartner;
            }
        }
    }
}
