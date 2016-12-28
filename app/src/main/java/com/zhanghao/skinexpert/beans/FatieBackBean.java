package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/27.
 */

public class FatieBackBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"uid":390828,"tags":[{"tag_id":90,"image":"http://img.ilikelabs.com/Uploads/172/172264_0-345-345.jpg@120w_1x.jpg","name":"欲望清单 wish list","type":0,"objectId":0},{"tag_id":171335,"image":"http://img.ilikelabs.com/Uploads/197/197650_0-200-200.jpg@120w_1x.jpg","name":"Miss zhang","type":0,"objectId":0},{"tag_id":263285,"image":"","name":"乳液elixir","type":0,"objectId":0}],"content":"你这是干什么","status":9,"type":0,"credit":0,"id":157803,"image":["http://img.ilikelabs.com/Uploads/218/218755_0-256-256.jpg@750w_1x.jpg","http://img.ilikelabs.com/Uploads/218/218756_0-144-144.jpg@750w_1x.jpg"],"createTime":"2016-12-27 19:03:53","tags_product":[{"tag_id":168948,"image":"http://img.ilikelabs.com/Uploads/191/191070_0-500-500@120w_1x.jpg","name":"苏芊玫瑰果保湿日霜","type":1,"objectId":2929604,"suitableSkin":["1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233"],"recommendSkin":[""]}]}
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
         * uid : 390828
         * tags : [{"tag_id":90,"image":"http://img.ilikelabs.com/Uploads/172/172264_0-345-345.jpg@120w_1x.jpg","name":"欲望清单 wish list","type":0,"objectId":0},{"tag_id":171335,"image":"http://img.ilikelabs.com/Uploads/197/197650_0-200-200.jpg@120w_1x.jpg","name":"Miss zhang","type":0,"objectId":0},{"tag_id":263285,"image":"","name":"乳液elixir","type":0,"objectId":0}]
         * content : 你这是干什么
         * status : 9
         * type : 0
         * credit : 0
         * id : 157803
         * image : ["http://img.ilikelabs.com/Uploads/218/218755_0-256-256.jpg@750w_1x.jpg","http://img.ilikelabs.com/Uploads/218/218756_0-144-144.jpg@750w_1x.jpg"]
         * createTime : 2016-12-27 19:03:53
         * tags_product : [{"tag_id":168948,"image":"http://img.ilikelabs.com/Uploads/191/191070_0-500-500@120w_1x.jpg","name":"苏芊玫瑰果保湿日霜","type":1,"objectId":2929604,"suitableSkin":["1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233"],"recommendSkin":[""]}]
         */

        private int uid;
        private String content;
        private int status;
        private int type;
        private int credit;
        private int id;
        private String createTime;
        private List<TagsBean> tags;
        private List<String> image;
        private List<TagsProductBean> tags_product;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<TagsProductBean> getTags_product() {
            return tags_product;
        }

        public void setTags_product(List<TagsProductBean> tags_product) {
            this.tags_product = tags_product;
        }

        public static class TagsBean {
            /**
             * tag_id : 90
             * image : http://img.ilikelabs.com/Uploads/172/172264_0-345-345.jpg@120w_1x.jpg
             * name : 欲望清单 wish list
             * type : 0
             * objectId : 0
             */

            private int tag_id;
            private String image;
            private String name;
            private int type;
            private int objectId;

            public int getTag_id() {
                return tag_id;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getObjectId() {
                return objectId;
            }

            public void setObjectId(int objectId) {
                this.objectId = objectId;
            }
        }

        public static class TagsProductBean {
            /**
             * tag_id : 168948
             * image : http://img.ilikelabs.com/Uploads/191/191070_0-500-500@120w_1x.jpg
             * name : 苏芊玫瑰果保湿日霜
             * type : 1
             * objectId : 2929604
             * suitableSkin : ["1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233"]
             * recommendSkin : [""]
             */

            private int tag_id;
            private String image;
            private String name;
            private int type;
            private int objectId;
            private List<String> suitableSkin;
            private List<String> recommendSkin;

            public int getTag_id() {
                return tag_id;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getObjectId() {
                return objectId;
            }

            public void setObjectId(int objectId) {
                this.objectId = objectId;
            }

            public List<String> getSuitableSkin() {
                return suitableSkin;
            }

            public void setSuitableSkin(List<String> suitableSkin) {
                this.suitableSkin = suitableSkin;
            }

            public List<String> getRecommendSkin() {
                return recommendSkin;
            }

            public void setRecommendSkin(List<String> recommendSkin) {
                this.recommendSkin = recommendSkin;
            }
        }
    }
}
