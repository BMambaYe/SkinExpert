package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CommunityBean {


    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"id":22,"categoryName":"心得分享","image":"http://img.ilikelabs.com/Uploads/198/198039_0-750-750@120w_1x.jpg","categoryType":"thread","totalCount":1443,"type":0},{"id":80,"categoryName":"祛痘好物","image":"http://img.ilikelabs.com/Uploads/197/197846_0-748-748.jpg@120w_1x.jpg","categoryType":"thread","totalCount":345,"type":0},{"id":9,"categoryName":"提问互助","image":"http://img.ilikelabs.com/Uploads/210/210010_0-654-654.jpg@120w_1x.jpg","categoryType":"thread","totalCount":7522,"type":0},{"id":247955,"categoryName":"安利好物","image":"http://img.ilikelabs.com/Uploads/209/209499_0-642-642.jpg@120w_1x.jpg","categoryType":"thread","totalCount":67,"type":0},{"id":116,"categoryName":"选择困难症","image":"http://img.ilikelabs.com/Uploads/203/203966_0-1200-1200.jpg@120w_1x.jpg","categoryType":"vote","totalCount":225,"type":0},{"id":171433,"categoryName":"晒单","image":"http://img.ilikelabs.com/Uploads/199/199027_0-640-640@120w_1x.jpg","categoryType":"thread","totalCount":113,"type":0},{"id":171773,"categoryName":"试色","image":"http://img.ilikelabs.com/Uploads/201/201713_0-599-599.jpg@120w_1x.jpg","categoryType":"thread","totalCount":42,"type":0},{"id":170008,"categoryName":"精华","image":"http://img.ilikelabs.com/Uploads/208/208415_0-1280-1280@120w_1x.jpg","categoryType":"thread","totalCount":15,"type":0},{"id":169623,"categoryName":"闲置交换","image":"http://img.ilikelabs.com/Uploads/203/203337_0-1199-1200.jpg@120w_1x.jpg","categoryType":"thread","totalCount":82,"type":0},{"id":1,"categoryName":"自拍","image":"http://img.ilikelabs.com/Uploads/197/197843_0-1200-1200.jpg@120w_1x.jpg","categoryType":"thread","totalCount":538,"type":0},{"id":86,"categoryName":"口红","image":"http://img.ilikelabs.com/Uploads/198/198041_0-673-673.png@120w_1x.jpg","categoryType":"thread","totalCount":200,"type":0},{"id":85,"categoryName":"种草","image":"http://img.ilikelabs.com/Uploads/198/198048_0-749-749.png@120w_1x.jpg","categoryType":"thread","totalCount":278,"type":0},{"id":91,"categoryName":"妆前VS妆后","image":"http://img.ilikelabs.com/Uploads/198/198074_0-370-370.jpg@120w_1x.jpg","categoryType":"thread","totalCount":115,"type":0},{"id":24,"categoryName":"彩妆","image":"http://img.ilikelabs.com/Uploads/198/198044_0-749-749.png@120w_1x.jpg","categoryType":"thread","totalCount":306,"type":0},{"id":94,"categoryName":"美甲","image":"http://img.ilikelabs.com/Uploads/198/198069_0-748-748.png@120w_1x.jpg","categoryType":"thread","totalCount":94,"type":0},{"id":49,"categoryName":"美白","image":"http://img.ilikelabs.com/Uploads/198/198072_0-731-731.jpg@120w_1x.jpg","categoryType":"thread","totalCount":166,"type":0},{"id":4,"categoryName":"面膜","image":"http://img.ilikelabs.com/Uploads/197/197845_0-749-749.jpg@120w_1x.jpg","categoryType":"thread","totalCount":259,"type":0},{"id":100,"categoryName":"剁手","image":"http://img.ilikelabs.com/Uploads/173/173726_0-509-509.jpg@120w_1x.jpg","categoryType":"thread","totalCount":627,"type":0},{"id":52,"categoryName":"防晒","image":"http://img.ilikelabs.com/Uploads/198/198075_0-326-326.png@120w_1x.jpg","categoryType":"thread","totalCount":152,"type":0},{"id":2,"categoryName":"梳妆台","image":"http://img.ilikelabs.com/Uploads/147/147943_0-200-200@120w_1x.jpg","categoryType":"thread","totalCount":86,"type":0}],"top":{"top1":{"cmcid":9,"title":"问题求助","icon":"http://img.ilikelabs.com/Uploads/180/180290_0-200-200"},"top2":{"cmcid":114,"title":"产品解析","icon":"http://img.ilikelabs.com/Uploads/180/180292_0-200-200"}}}
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
         * list : [{"id":22,"categoryName":"心得分享","image":"http://img.ilikelabs.com/Uploads/198/198039_0-750-750@120w_1x.jpg","categoryType":"thread","totalCount":1443,"type":0},{"id":80,"categoryName":"祛痘好物","image":"http://img.ilikelabs.com/Uploads/197/197846_0-748-748.jpg@120w_1x.jpg","categoryType":"thread","totalCount":345,"type":0},{"id":9,"categoryName":"提问互助","image":"http://img.ilikelabs.com/Uploads/210/210010_0-654-654.jpg@120w_1x.jpg","categoryType":"thread","totalCount":7522,"type":0},{"id":247955,"categoryName":"安利好物","image":"http://img.ilikelabs.com/Uploads/209/209499_0-642-642.jpg@120w_1x.jpg","categoryType":"thread","totalCount":67,"type":0},{"id":116,"categoryName":"选择困难症","image":"http://img.ilikelabs.com/Uploads/203/203966_0-1200-1200.jpg@120w_1x.jpg","categoryType":"vote","totalCount":225,"type":0},{"id":171433,"categoryName":"晒单","image":"http://img.ilikelabs.com/Uploads/199/199027_0-640-640@120w_1x.jpg","categoryType":"thread","totalCount":113,"type":0},{"id":171773,"categoryName":"试色","image":"http://img.ilikelabs.com/Uploads/201/201713_0-599-599.jpg@120w_1x.jpg","categoryType":"thread","totalCount":42,"type":0},{"id":170008,"categoryName":"精华","image":"http://img.ilikelabs.com/Uploads/208/208415_0-1280-1280@120w_1x.jpg","categoryType":"thread","totalCount":15,"type":0},{"id":169623,"categoryName":"闲置交换","image":"http://img.ilikelabs.com/Uploads/203/203337_0-1199-1200.jpg@120w_1x.jpg","categoryType":"thread","totalCount":82,"type":0},{"id":1,"categoryName":"自拍","image":"http://img.ilikelabs.com/Uploads/197/197843_0-1200-1200.jpg@120w_1x.jpg","categoryType":"thread","totalCount":538,"type":0},{"id":86,"categoryName":"口红","image":"http://img.ilikelabs.com/Uploads/198/198041_0-673-673.png@120w_1x.jpg","categoryType":"thread","totalCount":200,"type":0},{"id":85,"categoryName":"种草","image":"http://img.ilikelabs.com/Uploads/198/198048_0-749-749.png@120w_1x.jpg","categoryType":"thread","totalCount":278,"type":0},{"id":91,"categoryName":"妆前VS妆后","image":"http://img.ilikelabs.com/Uploads/198/198074_0-370-370.jpg@120w_1x.jpg","categoryType":"thread","totalCount":115,"type":0},{"id":24,"categoryName":"彩妆","image":"http://img.ilikelabs.com/Uploads/198/198044_0-749-749.png@120w_1x.jpg","categoryType":"thread","totalCount":306,"type":0},{"id":94,"categoryName":"美甲","image":"http://img.ilikelabs.com/Uploads/198/198069_0-748-748.png@120w_1x.jpg","categoryType":"thread","totalCount":94,"type":0},{"id":49,"categoryName":"美白","image":"http://img.ilikelabs.com/Uploads/198/198072_0-731-731.jpg@120w_1x.jpg","categoryType":"thread","totalCount":166,"type":0},{"id":4,"categoryName":"面膜","image":"http://img.ilikelabs.com/Uploads/197/197845_0-749-749.jpg@120w_1x.jpg","categoryType":"thread","totalCount":259,"type":0},{"id":100,"categoryName":"剁手","image":"http://img.ilikelabs.com/Uploads/173/173726_0-509-509.jpg@120w_1x.jpg","categoryType":"thread","totalCount":627,"type":0},{"id":52,"categoryName":"防晒","image":"http://img.ilikelabs.com/Uploads/198/198075_0-326-326.png@120w_1x.jpg","categoryType":"thread","totalCount":152,"type":0},{"id":2,"categoryName":"梳妆台","image":"http://img.ilikelabs.com/Uploads/147/147943_0-200-200@120w_1x.jpg","categoryType":"thread","totalCount":86,"type":0}]
         * top : {"top1":{"cmcid":9,"title":"问题求助","icon":"http://img.ilikelabs.com/Uploads/180/180290_0-200-200"},"top2":{"cmcid":114,"title":"产品解析","icon":"http://img.ilikelabs.com/Uploads/180/180292_0-200-200"}}
         */

        private TopBean top;
        private List<ListBean> list;

        public TopBean getTop() {
            return top;
        }

        public void setTop(TopBean top) {
            this.top = top;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class TopBean {
            /**
             * top1 : {"cmcid":9,"title":"问题求助","icon":"http://img.ilikelabs.com/Uploads/180/180290_0-200-200"}
             * top2 : {"cmcid":114,"title":"产品解析","icon":"http://img.ilikelabs.com/Uploads/180/180292_0-200-200"}
             */

            private Top1Bean top1;
            private Top2Bean top2;

            public Top1Bean getTop1() {
                return top1;
            }

            public void setTop1(Top1Bean top1) {
                this.top1 = top1;
            }

            public Top2Bean getTop2() {
                return top2;
            }

            public void setTop2(Top2Bean top2) {
                this.top2 = top2;
            }

            public static class Top1Bean {
                /**
                 * cmcid : 9
                 * title : 问题求助
                 * icon : http://img.ilikelabs.com/Uploads/180/180290_0-200-200
                 */

                private int cmcid;
                private String title;
                private String icon;

                public int getCmcid() {
                    return cmcid;
                }

                public void setCmcid(int cmcid) {
                    this.cmcid = cmcid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public static class Top2Bean {
                /**
                 * cmcid : 114
                 * title : 产品解析
                 * icon : http://img.ilikelabs.com/Uploads/180/180292_0-200-200
                 */

                private int cmcid;
                private String title;
                private String icon;

                public int getCmcid() {
                    return cmcid;
                }

                public void setCmcid(int cmcid) {
                    this.cmcid = cmcid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }

        public static class ListBean {
            /**
             * id : 22
             * categoryName : 心得分享
             * image : http://img.ilikelabs.com/Uploads/198/198039_0-750-750@120w_1x.jpg
             * categoryType : thread
             * totalCount : 1443
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
}
