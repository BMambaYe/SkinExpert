package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/23.
 */

public class ProductBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"cmcid":13367,"threadCount":1,"id":187231,"name":"臻薄物理日光防护乳","title":"修丽可臻薄物理日光防护乳SPF30+ PA+++","func":"防晒,修饰肤色","price":"390.00","pic":"http://img.ilikelabs.com/Uploads/172/172204_0-433-433@0e_500w_500h_1c_0i_1o_1x.jpg","usedNum":0,"likedCount":75,"security":-1,"brand":"5621","activeTime":"2016-12-13 06:00:00","suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"skinSuggestion":{"dryHeavy":true,"dryLight":true,"oilLight":true,"oilHeavy":true,"toleranceHeavy":true,"toleranceLight":true,"sensitiveLight":true,"sensitiveHeavy":true},"skinSuggestionApplied":true,"recommendSkin":[""],"reviewCount":8,"reviewScore":"9.7","star":0,"brandChinaName":"修丽可","brandEnName":"SKIN CEUTICALS","effectAbstract":"修丽可-SKINCEUTICALS欧莱雅集团旗下的品牌，网上也被叫做杜克。品牌源自美国，有浓厚的医学背景，在中国通常会在高端美容院或者医院销售，价格也比较高，当然也可以从官方旗舰店购买。修丽可臻薄物理日光防护乳是一款纯物理防晒产品，含5%氧化锌、6%二氧化钛，防晒值可以达到SPF50 PA+++。通常纯物理防晒剂很难达到这么高的防晒指数，但是经过特殊表面处理的氧化锌和二氧化钛是可以的，欧莱雅在防晒产品上的优秀技术是公认。值得注意的是卤虫提取物，可以抵御紫外线在内的各种环境压力，缓解皮肤老化问题。高含量的物理防晒产品容易有拔干的感觉，所以这个产品中润肤成分含量比较高，包括长链烷烃、硅油、合成酯等，同时它们也起到溶剂的作用，与高分子聚合物一起，使物理粉末均匀分布。产品没有添加香精，防腐剂主要是苯氧乙醇，防晒剂体系温和。"}],"productBannerImage":"http://img.ilikelabs.com/Uploads/206/206272_0-750-300@750w_1x.jpg","productStock":15,"productBeginDate":"2016-06-09 20:00:00","currentDate":"2016-12-23 19:44:55","objectId":102}
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
         * list : [{"cmcid":13367,"threadCount":1,"id":187231,"name":"臻薄物理日光防护乳","title":"修丽可臻薄物理日光防护乳SPF30+ PA+++","func":"防晒,修饰肤色","price":"390.00","pic":"http://img.ilikelabs.com/Uploads/172/172204_0-433-433@0e_500w_500h_1c_0i_1o_1x.jpg","usedNum":0,"likedCount":75,"security":-1,"brand":"5621","activeTime":"2016-12-13 06:00:00","suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"skinSuggestion":{"dryHeavy":true,"dryLight":true,"oilLight":true,"oilHeavy":true,"toleranceHeavy":true,"toleranceLight":true,"sensitiveLight":true,"sensitiveHeavy":true},"skinSuggestionApplied":true,"recommendSkin":[""],"reviewCount":8,"reviewScore":"9.7","star":0,"brandChinaName":"修丽可","brandEnName":"SKIN CEUTICALS","effectAbstract":"修丽可-SKINCEUTICALS欧莱雅集团旗下的品牌，网上也被叫做杜克。品牌源自美国，有浓厚的医学背景，在中国通常会在高端美容院或者医院销售，价格也比较高，当然也可以从官方旗舰店购买。修丽可臻薄物理日光防护乳是一款纯物理防晒产品，含5%氧化锌、6%二氧化钛，防晒值可以达到SPF50 PA+++。通常纯物理防晒剂很难达到这么高的防晒指数，但是经过特殊表面处理的氧化锌和二氧化钛是可以的，欧莱雅在防晒产品上的优秀技术是公认。值得注意的是卤虫提取物，可以抵御紫外线在内的各种环境压力，缓解皮肤老化问题。高含量的物理防晒产品容易有拔干的感觉，所以这个产品中润肤成分含量比较高，包括长链烷烃、硅油、合成酯等，同时它们也起到溶剂的作用，与高分子聚合物一起，使物理粉末均匀分布。产品没有添加香精，防腐剂主要是苯氧乙醇，防晒剂体系温和。"}]
         * productBannerImage : http://img.ilikelabs.com/Uploads/206/206272_0-750-300@750w_1x.jpg
         * productStock : 15
         * productBeginDate : 2016-06-09 20:00:00
         * currentDate : 2016-12-23 19:44:55
         * objectId : 102
         */

        private String productBannerImage;
        private int productStock;
        private String productBeginDate;
        private String currentDate;
        private int objectId;
        private List<ListBean> list;

        public String getProductBannerImage() {
            return productBannerImage;
        }

        public void setProductBannerImage(String productBannerImage) {
            this.productBannerImage = productBannerImage;
        }

        public int getProductStock() {
            return productStock;
        }

        public void setProductStock(int productStock) {
            this.productStock = productStock;
        }

        public String getProductBeginDate() {
            return productBeginDate;
        }

        public void setProductBeginDate(String productBeginDate) {
            this.productBeginDate = productBeginDate;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cmcid : 13367
             * threadCount : 1
             * id : 187231
             * name : 臻薄物理日光防护乳
             * title : 修丽可臻薄物理日光防护乳SPF30+ PA+++
             * func : 防晒,修饰肤色
             * price : 390.00
             * pic : http://img.ilikelabs.com/Uploads/172/172204_0-433-433@0e_500w_500h_1c_0i_1o_1x.jpg
             * usedNum : 0
             * likedCount : 75
             * security : -1
             * brand : 5621
             * activeTime : 2016-12-13 06:00:00
             * suitableSkin : ["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"]
             * skinSuggestion : {"dryHeavy":true,"dryLight":true,"oilLight":true,"oilHeavy":true,"toleranceHeavy":true,"toleranceLight":true,"sensitiveLight":true,"sensitiveHeavy":true}
             * skinSuggestionApplied : true
             * recommendSkin : [""]
             * reviewCount : 8
             * reviewScore : 9.7
             * star : 0
             * brandChinaName : 修丽可
             * brandEnName : SKIN CEUTICALS
             * effectAbstract : 修丽可-SKINCEUTICALS欧莱雅集团旗下的品牌，网上也被叫做杜克。品牌源自美国，有浓厚的医学背景，在中国通常会在高端美容院或者医院销售，价格也比较高，当然也可以从官方旗舰店购买。修丽可臻薄物理日光防护乳是一款纯物理防晒产品，含5%氧化锌、6%二氧化钛，防晒值可以达到SPF50 PA+++。通常纯物理防晒剂很难达到这么高的防晒指数，但是经过特殊表面处理的氧化锌和二氧化钛是可以的，欧莱雅在防晒产品上的优秀技术是公认。值得注意的是卤虫提取物，可以抵御紫外线在内的各种环境压力，缓解皮肤老化问题。高含量的物理防晒产品容易有拔干的感觉，所以这个产品中润肤成分含量比较高，包括长链烷烃、硅油、合成酯等，同时它们也起到溶剂的作用，与高分子聚合物一起，使物理粉末均匀分布。产品没有添加香精，防腐剂主要是苯氧乙醇，防晒剂体系温和。
             */

            private int cmcid;
            private int threadCount;
            private int id;
            private String name;
            private String title;
            private String func;
            private String price;
            private String pic;
            private int usedNum;
            private int likedCount;
            private int security;
            private String brand;
            private String activeTime;
            private SkinSuggestionBean skinSuggestion;
            private boolean skinSuggestionApplied;
            private int reviewCount;
            private String reviewScore;
            private int star;
            private String brandChinaName;
            private String brandEnName;
            private String effectAbstract;
            private List<String> suitableSkin;
            private List<String> recommendSkin;

            public int getCmcid() {
                return cmcid;
            }

            public void setCmcid(int cmcid) {
                this.cmcid = cmcid;
            }

            public int getThreadCount() {
                return threadCount;
            }

            public void setThreadCount(int threadCount) {
                this.threadCount = threadCount;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFunc() {
                return func;
            }

            public void setFunc(String func) {
                this.func = func;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getUsedNum() {
                return usedNum;
            }

            public void setUsedNum(int usedNum) {
                this.usedNum = usedNum;
            }

            public int getLikedCount() {
                return likedCount;
            }

            public void setLikedCount(int likedCount) {
                this.likedCount = likedCount;
            }

            public int getSecurity() {
                return security;
            }

            public void setSecurity(int security) {
                this.security = security;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getActiveTime() {
                return activeTime;
            }

            public void setActiveTime(String activeTime) {
                this.activeTime = activeTime;
            }

            public SkinSuggestionBean getSkinSuggestion() {
                return skinSuggestion;
            }

            public void setSkinSuggestion(SkinSuggestionBean skinSuggestion) {
                this.skinSuggestion = skinSuggestion;
            }

            public boolean isSkinSuggestionApplied() {
                return skinSuggestionApplied;
            }

            public void setSkinSuggestionApplied(boolean skinSuggestionApplied) {
                this.skinSuggestionApplied = skinSuggestionApplied;
            }

            public int getReviewCount() {
                return reviewCount;
            }

            public void setReviewCount(int reviewCount) {
                this.reviewCount = reviewCount;
            }

            public String getReviewScore() {
                return reviewScore;
            }

            public void setReviewScore(String reviewScore) {
                this.reviewScore = reviewScore;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getBrandChinaName() {
                return brandChinaName;
            }

            public void setBrandChinaName(String brandChinaName) {
                this.brandChinaName = brandChinaName;
            }

            public String getBrandEnName() {
                return brandEnName;
            }

            public void setBrandEnName(String brandEnName) {
                this.brandEnName = brandEnName;
            }

            public String getEffectAbstract() {
                return effectAbstract;
            }

            public void setEffectAbstract(String effectAbstract) {
                this.effectAbstract = effectAbstract;
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

            public static class SkinSuggestionBean {
                /**
                 * dryHeavy : true
                 * dryLight : true
                 * oilLight : true
                 * oilHeavy : true
                 * toleranceHeavy : true
                 * toleranceLight : true
                 * sensitiveLight : true
                 * sensitiveHeavy : true
                 */

                private boolean dryHeavy;
                private boolean dryLight;
                private boolean oilLight;
                private boolean oilHeavy;
                private boolean toleranceHeavy;
                private boolean toleranceLight;
                private boolean sensitiveLight;
                private boolean sensitiveHeavy;

                public boolean isDryHeavy() {
                    return dryHeavy;
                }

                public void setDryHeavy(boolean dryHeavy) {
                    this.dryHeavy = dryHeavy;
                }

                public boolean isDryLight() {
                    return dryLight;
                }

                public void setDryLight(boolean dryLight) {
                    this.dryLight = dryLight;
                }

                public boolean isOilLight() {
                    return oilLight;
                }

                public void setOilLight(boolean oilLight) {
                    this.oilLight = oilLight;
                }

                public boolean isOilHeavy() {
                    return oilHeavy;
                }

                public void setOilHeavy(boolean oilHeavy) {
                    this.oilHeavy = oilHeavy;
                }

                public boolean isToleranceHeavy() {
                    return toleranceHeavy;
                }

                public void setToleranceHeavy(boolean toleranceHeavy) {
                    this.toleranceHeavy = toleranceHeavy;
                }

                public boolean isToleranceLight() {
                    return toleranceLight;
                }

                public void setToleranceLight(boolean toleranceLight) {
                    this.toleranceLight = toleranceLight;
                }

                public boolean isSensitiveLight() {
                    return sensitiveLight;
                }

                public void setSensitiveLight(boolean sensitiveLight) {
                    this.sensitiveLight = sensitiveLight;
                }

                public boolean isSensitiveHeavy() {
                    return sensitiveHeavy;
                }

                public void setSensitiveHeavy(boolean sensitiveHeavy) {
                    this.sensitiveHeavy = sensitiveHeavy;
                }
            }
        }
    }
}
