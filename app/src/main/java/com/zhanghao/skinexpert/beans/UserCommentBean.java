package com.zhanghao.skinexpert.beans;

/**
 * Created by zhanghao on 2016/12/29.
 */

public class UserCommentBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"id":4960,"rid":574,"uid":391121,"oid":0,"ouid":0,"createTime":"2016-12-29 16:33:01","content":"1","user":{"uid":391121,"gender":"","nick":"MFJ39873","headface":"","userType":"unActivated","mobile":"13913302017","title":"","userInfo":"","skin":"","birthday":"0000-00-00","location":"","defaultMessageRead":0,"lastUpdateTimestamp":1482974835,"signin_count":0,"communityCategoryAttentionData":"","createTime":"2016-12-29 09:27:15","skinCode":"----","skinType":"----"},"onick":""}
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
         * id : 4960
         * rid : 574
         * uid : 391121
         * oid : 0
         * ouid : 0
         * createTime : 2016-12-29 16:33:01
         * content : 1
         * user : {"uid":391121,"gender":"","nick":"MFJ39873","headface":"","userType":"unActivated","mobile":"13913302017","title":"","userInfo":"","skin":"","birthday":"0000-00-00","location":"","defaultMessageRead":0,"lastUpdateTimestamp":1482974835,"signin_count":0,"communityCategoryAttentionData":"","createTime":"2016-12-29 09:27:15","skinCode":"----","skinType":"----"}
         * onick :
         */

        private int id;
        private int rid;
        private int uid;
        private int oid;
        private int ouid;
        private String createTime;
        private String content;
        private UserBean user;
        private String onick;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getOuid() {
            return ouid;
        }

        public void setOuid(int ouid) {
            this.ouid = ouid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getOnick() {
            return onick;
        }

        public void setOnick(String onick) {
            this.onick = onick;
        }

        public static class UserBean {
            /**
             * uid : 391121
             * gender :
             * nick : MFJ39873
             * headface :
             * userType : unActivated
             * mobile : 13913302017
             * title :
             * userInfo :
             * skin :
             * birthday : 0000-00-00
             * location :
             * defaultMessageRead : 0
             * lastUpdateTimestamp : 1482974835
             * signin_count : 0
             * communityCategoryAttentionData :
             * createTime : 2016-12-29 09:27:15
             * skinCode : ----
             * skinType : ----
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
        }
    }
}
