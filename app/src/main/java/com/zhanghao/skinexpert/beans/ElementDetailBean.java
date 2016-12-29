package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/24.
 */

public class ElementDetailBean {


    /**
     * error_code : 0
     * message : 成功
     * data : {"id":1785,"name":"ALCOHOL DENAT","func":"溶剂,抗菌,清凉,收缩毛孔","elementChinaName":"变性乙醇","alias":"ALCOHOL DENATURED，变性酒精","detail":"为确保用于化妆品中的酒精无法转移为其他用途，因此经过特殊程序处理(加入不可食用成份)后的酒精，又称为 SD Alcohol (Specially Denatured alcohol)，主要为宣告其不可用以饮用，而在欧洲地区为了适用于国际名称惯例将其命名为 Alcohol Denat。\r\nSD Alcohol 仍然为酒精成份，只是据说因为经过处理，其蒸发速度较快，也因此能将化妆品的香味更快散逸而出，且停留于肌肤的时间也较短。\r\n易燃无色的化学混合物，在化妆保养品中常用以作为溶剂使用，亦为部份植物萃取方式中不可或缺的成份，使用于肌肤会有部份刺激性。\r\n能瞬间收缩毛孔形成清凉感，但效用极短，且利用渗透细胞膜进行去脂方式，长期使用会造成皮肤逐渐黯淡无光泽。有抗菌作用。","sensitization":true,"baseElement":true,"funcElement":false,"carcinogenicRisk":0.002,"genotoxicityRisk":0.003,"sensitizationRisk":0,"pimpleCaution":false,"pregnantCaution":false,"product":{"count":2723,"list":[{"id":182593,"name":"新精华肌底液-大陆版（小黑瓶）","image":"http://img.ilikelabs.com/Uploads/137/137356_0-400-400@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.1,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","2000","2003","2030","2033","2100","2103","2130","2133"],"recommendSkin":["2000","2030","2100","2130","3000","3030","3100","3130"]},{"id":74115,"name":"晒后舒润修护乳","image":"http://img.ilikelabs.com/Uploads/151/151340_0-400-400@200w_1x.jpg","brandName":"VICHY","brandChinaName":"薇姿","score":8.6,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"]},{"id":59829,"name":"新柔皙轻透防晒乳","image":"http://img.ilikelabs.com/Uploads/147/147476_0-1000-1000@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","1000","1003","1030","1033","1100","1103","1130","1133","2000","2003","2030","2033","2100","2103","2130","2133","3000","3003","3030","3033","3100","3103","3130","3133"]}]}}
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
         * id : 1785
         * name : ALCOHOL DENAT
         * func : 溶剂,抗菌,清凉,收缩毛孔
         * elementChinaName : 变性乙醇
         * alias : ALCOHOL DENATURED，变性酒精
         * detail : 为确保用于化妆品中的酒精无法转移为其他用途，因此经过特殊程序处理(加入不可食用成份)后的酒精，又称为 SD Alcohol (Specially Denatured alcohol)，主要为宣告其不可用以饮用，而在欧洲地区为了适用于国际名称惯例将其命名为 Alcohol Denat。
         SD Alcohol 仍然为酒精成份，只是据说因为经过处理，其蒸发速度较快，也因此能将化妆品的香味更快散逸而出，且停留于肌肤的时间也较短。
         易燃无色的化学混合物，在化妆保养品中常用以作为溶剂使用，亦为部份植物萃取方式中不可或缺的成份，使用于肌肤会有部份刺激性。
         能瞬间收缩毛孔形成清凉感，但效用极短，且利用渗透细胞膜进行去脂方式，长期使用会造成皮肤逐渐黯淡无光泽。有抗菌作用。
         * sensitization : true
         * baseElement : true
         * funcElement : false
         * carcinogenicRisk : 0.002
         * genotoxicityRisk : 0.003
         * sensitizationRisk : 0
         * pimpleCaution : false
         * pregnantCaution : false
         * product : {"count":2723,"list":[{"id":182593,"name":"新精华肌底液-大陆版（小黑瓶）","image":"http://img.ilikelabs.com/Uploads/137/137356_0-400-400@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.1,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","2000","2003","2030","2033","2100","2103","2130","2133"],"recommendSkin":["2000","2030","2100","2130","3000","3030","3100","3130"]},{"id":74115,"name":"晒后舒润修护乳","image":"http://img.ilikelabs.com/Uploads/151/151340_0-400-400@200w_1x.jpg","brandName":"VICHY","brandChinaName":"薇姿","score":8.6,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"]},{"id":59829,"name":"新柔皙轻透防晒乳","image":"http://img.ilikelabs.com/Uploads/147/147476_0-1000-1000@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","1000","1003","1030","1033","1100","1103","1130","1133","2000","2003","2030","2033","2100","2103","2130","2133","3000","3003","3030","3033","3100","3103","3130","3133"]}]}
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
        private double carcinogenicRisk;
        private double genotoxicityRisk;
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

        public double getCarcinogenicRisk() {
            return carcinogenicRisk;
        }

        public void setCarcinogenicRisk(double carcinogenicRisk) {
            this.carcinogenicRisk = carcinogenicRisk;
        }

        public double getGenotoxicityRisk() {
            return genotoxicityRisk;
        }

        public void setGenotoxicityRisk(double genotoxicityRisk) {
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
             * count : 2723
             * list : [{"id":182593,"name":"新精华肌底液-大陆版（小黑瓶）","image":"http://img.ilikelabs.com/Uploads/137/137356_0-400-400@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.1,"suitableSkin":["3000","3003","3030","3033","3100","3103","3130","3133","2000","2003","2030","2033","2100","2103","2130","2133"],"recommendSkin":["2000","2030","2100","2130","3000","3030","3100","3130"]},{"id":74115,"name":"晒后舒润修护乳","image":"http://img.ilikelabs.com/Uploads/151/151340_0-400-400@200w_1x.jpg","brandName":"VICHY","brandChinaName":"薇姿","score":8.6,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","0300","0303","0330","0333","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","1300","1303","1330","1333","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","2300","2303","2330","2333","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233","3300","3303","3330","3333"]},{"id":59829,"name":"新柔皙轻透防晒乳","image":"http://img.ilikelabs.com/Uploads/147/147476_0-1000-1000@200w_1x.jpg","brandName":"LANCOME","brandChinaName":"兰蔻","score":8.3,"suitableSkin":["0000","0003","0030","0033","0100","0103","0130","0133","0200","0203","0230","0233","1000","1003","1030","1033","1100","1103","1130","1133","1200","1203","1230","1233","2000","2003","2030","2033","2100","2103","2130","2133","2200","2203","2230","2233","3000","3003","3030","3033","3100","3103","3130","3133","3200","3203","3230","3233"],"recommendSkin":["0000","0003","0030","0033","0100","0103","0130","0133","1000","1003","1030","1033","1100","1103","1130","1133","2000","2003","2030","2033","2100","2103","2130","2133","3000","3003","3030","3033","3100","3103","3130","3133"]}]
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
                 * id : 182593
                 * name : 新精华肌底液-大陆版（小黑瓶）
                 * image : http://img.ilikelabs.com/Uploads/137/137356_0-400-400@200w_1x.jpg
                 * brandName : LANCOME
                 * brandChinaName : 兰蔻
                 * score : 8.1
                 * suitableSkin : ["3000","3003","3030","3033","3100","3103","3130","3133","2000","2003","2030","2033","2100","2103","2130","2133"]
                 * recommendSkin : ["2000","2030","2100","2130","3000","3030","3100","3130"]
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
