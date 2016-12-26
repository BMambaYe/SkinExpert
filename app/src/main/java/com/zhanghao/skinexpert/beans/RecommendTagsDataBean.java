package com.zhanghao.skinexpert.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public class RecommendTagsDataBean implements Serializable{

    /**
     * error_code : 0
     * message : 成功
     * data : [{"id":9,"categoryName":"提问互助","image":"http://img.ilikelabs.com/Uploads/210/210010_0-654-654.jpg@200w_1x.jpg","categoryType":"thread","totalCount":7522,"type":0},{"id":114,"categoryName":"提交产品","image":"http://img.ilikelabs.com/Uploads/180/180292_0-200-200@200w_1x.jpg","categoryType":"thread","totalCount":1811,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":22,"categoryName":"心得分享","image":"http://img.ilikelabs.com/Uploads/198/198039_0-750-750@200w_1x.jpg","categoryType":"thread","totalCount":1443,"type":0},{"id":0,"categoryName":"","image":"http://img.ilikelabs.com/Uploads/197/197258_0-120-120","categoryType":"","totalCount":0,"type":0},{"id":100,"categoryName":"剁手","image":"http://img.ilikelabs.com/Uploads/173/173726_0-509-509.jpg@200w_1x.jpg","categoryType":"thread","totalCount":627,"type":0},{"id":77,"categoryName":"海淘","image":"http://img.ilikelabs.com/Uploads/197/197847_0-630-630.jpg@200w_1x.jpg","categoryType":"thread","totalCount":608,"type":0},{"id":1,"categoryName":"自拍","image":"http://img.ilikelabs.com/Uploads/197/197843_0-1200-1200.jpg@200w_1x.jpg","categoryType":"thread","totalCount":538,"type":0},{"id":20,"categoryName":"便宜大碗","image":"http://img.ilikelabs.com/Uploads/197/197848_0-748-748.jpg@200w_1x.jpg","categoryType":"thread","totalCount":460,"type":0},{"id":104,"categoryName":"基金交流","image":"http://img.ilikelabs.com/Uploads/179/179230_0-575-575.jpg@200w_1x.jpg","categoryType":"thread","totalCount":446,"type":0},{"id":23,"categoryName":"美食","image":"http://img.ilikelabs.com/Uploads/156/156027_0-630-630.jpg@200w_1x.jpg","categoryType":"thread","totalCount":408,"type":0},{"id":99,"categoryName":"空瓶记","image":"http://img.ilikelabs.com/Uploads/197/197856_0-646-646.jpg@200w_1x.jpg","categoryType":"thread","totalCount":394,"type":0},{"id":75,"categoryName":"百元好物","image":"http://img.ilikelabs.com/Uploads/169/169387_0-470-470@200w_1x.jpg","categoryType":"thread","totalCount":376,"type":0}]
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

    public static class DataBean{
        /**
         * id : 9
         * categoryName : 提问互助
         * image : http://img.ilikelabs.com/Uploads/210/210010_0-654-654.jpg@200w_1x.jpg
         * categoryType : thread
         * totalCount : 7522
         * type : 0
         */

        private int id;
        private String categoryName;
        private String image;
        private String categoryType;
        private int totalCount;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(String categoryType) {
            this.categoryType = categoryType;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
