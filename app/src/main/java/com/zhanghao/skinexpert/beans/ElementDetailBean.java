package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/24.
 */

public class ElementDetailBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"id":1297,"name":"PHENOXYETHANOL","func":"防腐剂,定香","elementChinaName":"苯氧乙醇","alias":"Phenoxethol, 2-phenoxyethanol，苯氧基乙醇，optiphen plus","detail":"作为防腐剂使用。属于低度皮肤敏感的防腐剂,在化妆品中使用最广泛的防腐剂。很多药妆产品的配方中，将苯氧乙醇作为唯一使用的防腐剂，复配有抗菌作用的多元醇类共同起到防腐的作用。也用作香精香水的定香剂。\r\n未稀释前对眼睛刺激性很大，稀释至2.2%则不具刺激性。属低度皮肤过敏危险性。\r\n《化妆品卫生规范2007版》《化妆品安全技术规范2015版》作为防腐剂使用，化妆品中最大允许使用浓度1.0%。","sensitization":false,"baseElement":true,"funcElement":false,"carcinogenicRisk":0,"genotoxicityRisk":0,"sensitizationRisk":0,"pimpleCaution":false,"pregnantCaution":false,"product":{"count":34822,"list":[{"id":65841,"name":"韦博士灵芝焕能精华水（菌菇水）","image":"http://img.ilikelabs.com/Uploads/139/139010_0-400-400@200w_1x.jpg","brandName":"ORIGINS","brandChinaName":"悦木之源","score":8.4,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333"],"recommendSkin":["2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]},{"id":3950,"name":"温和防晒乳液","image":"http://img.ilikelabs.com/Uploads/210/210098_0-350-350@200w_1x.jpg","brandName":"Biore","brandChinaName":"碧柔","score":8.5,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":[""]},{"id":187243,"name":"四效合一卸妆水","image":"http://img.ilikelabs.com/Uploads/167/167485_0-500-500@200w_1x.jpg","brandName":"ALOVIVI","brandChinaName":"卸妆皇后","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]}]}}
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
         * id : 1297
         * name : PHENOXYETHANOL
         * func : 防腐剂,定香
         * elementChinaName : 苯氧乙醇
         * alias : Phenoxethol, 2-phenoxyethanol，苯氧基乙醇，optiphen plus
         * detail : 作为防腐剂使用。属于低度皮肤敏感的防腐剂,在化妆品中使用最广泛的防腐剂。很多药妆产品的配方中，将苯氧乙醇作为唯一使用的防腐剂，复配有抗菌作用的多元醇类共同起到防腐的作用。也用作香精香水的定香剂。
         未稀释前对眼睛刺激性很大，稀释至2.2%则不具刺激性。属低度皮肤过敏危险性。
         《化妆品卫生规范2007版》《化妆品安全技术规范2015版》作为防腐剂使用，化妆品中最大允许使用浓度1.0%。
         * sensitization : false
         * baseElement : true
         * funcElement : false
         * carcinogenicRisk : 0
         * genotoxicityRisk : 0
         * sensitizationRisk : 0
         * pimpleCaution : false
         * pregnantCaution : false
         * product : {"count":34822,"list":[{"id":65841,"name":"韦博士灵芝焕能精华水（菌菇水）","image":"http://img.ilikelabs.com/Uploads/139/139010_0-400-400@200w_1x.jpg","brandName":"ORIGINS","brandChinaName":"悦木之源","score":8.4,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333"],"recommendSkin":["2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]},{"id":3950,"name":"温和防晒乳液","image":"http://img.ilikelabs.com/Uploads/210/210098_0-350-350@200w_1x.jpg","brandName":"Biore","brandChinaName":"碧柔","score":8.5,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":[""]},{"id":187243,"name":"四效合一卸妆水","image":"http://img.ilikelabs.com/Uploads/167/167485_0-500-500@200w_1x.jpg","brandName":"ALOVIVI","brandChinaName":"卸妆皇后","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]}]}
         */

        private int id;
        private String name;
        private String func;
        private String elementChinaName;
        private String alias;
        private String detail;
        private boolean sensitization;
        private boolean baseElement;
        private boolean funcElement;
        private int carcinogenicRisk;
        private int genotoxicityRisk;
        private int sensitizationRisk;
        private boolean pimpleCaution;
        private boolean pregnantCaution;
        private ProductBean product;

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

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

        public String getElementChinaName() {
            return elementChinaName;
        }

        public void setElementChinaName(String elementChinaName) {
            this.elementChinaName = elementChinaName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public boolean isSensitization() {
            return sensitization;
        }

        public void setSensitization(boolean sensitization) {
            this.sensitization = sensitization;
        }

        public boolean isBaseElement() {
            return baseElement;
        }

        public void setBaseElement(boolean baseElement) {
            this.baseElement = baseElement;
        }

        public boolean isFuncElement() {
            return funcElement;
        }

        public void setFuncElement(boolean funcElement) {
            this.funcElement = funcElement;
        }

        public int getCarcinogenicRisk() {
            return carcinogenicRisk;
        }

        public void setCarcinogenicRisk(int carcinogenicRisk) {
            this.carcinogenicRisk = carcinogenicRisk;
        }

        public int getGenotoxicityRisk() {
            return genotoxicityRisk;
        }

        public void setGenotoxicityRisk(int genotoxicityRisk) {
            this.genotoxicityRisk = genotoxicityRisk;
        }

        public int getSensitizationRisk() {
            return sensitizationRisk;
        }

        public void setSensitizationRisk(int sensitizationRisk) {
            this.sensitizationRisk = sensitizationRisk;
        }

        public boolean isPimpleCaution() {
            return pimpleCaution;
        }

        public void setPimpleCaution(boolean pimpleCaution) {
            this.pimpleCaution = pimpleCaution;
        }

        public boolean isPregnantCaution() {
            return pregnantCaution;
        }

        public void setPregnantCaution(boolean pregnantCaution) {
            this.pregnantCaution = pregnantCaution;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * count : 34822
             * list : [{"id":65841,"name":"韦博士灵芝焕能精华水（菌菇水）","image":"http://img.ilikelabs.com/Uploads/139/139010_0-400-400@200w_1x.jpg","brandName":"ORIGINS","brandChinaName":"悦木之源","score":8.4,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333"],"recommendSkin":["2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]},{"id":3950,"name":"温和防晒乳液","image":"http://img.ilikelabs.com/Uploads/210/210098_0-350-350@200w_1x.jpg","brandName":"Biore","brandChinaName":"碧柔","score":8.5,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":[""]},{"id":187243,"name":"四效合一卸妆水","image":"http://img.ilikelabs.com/Uploads/167/167485_0-500-500@200w_1x.jpg","brandName":"ALOVIVI","brandChinaName":"卸妆皇后","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]}]
             */

            private int count;
            private List<ListBean> list;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 65841
                 * name : 韦博士灵芝焕能精华水（菌菇水）
                 * image : http://img.ilikelabs.com/Uploads/139/139010_0-400-400@200w_1x.jpg
                 * brandName : ORIGINS
                 * brandChinaName : 悦木之源
                 * score : 8.4
                 * suitableSkin : ["3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333"]
                 * recommendSkin : ["2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"]
                 */

                private int id;
                private String name;
                private String image;
                private String brandName;
                private String brandChinaName;
                private double score;
                private List<String> suitableSkin;
                private List<String> recommendSkin;

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

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public String getBrandChinaName() {
                    return brandChinaName;
                }

                public void setBrandChinaName(String brandChinaName) {
                    this.brandChinaName = brandChinaName;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
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
}
