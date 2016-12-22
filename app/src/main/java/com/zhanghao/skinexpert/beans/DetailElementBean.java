package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/22.
 */

public class DetailElementBean {

    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"gName":"","elementList":[{"id":762,"name":"AQUA","func":"溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"水","pimpleCaution":false,"pregnantCaution":false},{"id":1066,"name":"GLYCERIN","func":"保湿剂,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"甘油","pimpleCaution":false,"pregnantCaution":false},{"id":811,"name":"BUTYLENE GLYCOL","func":"保湿剂,抗菌,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"丁二醇","pimpleCaution":false,"pregnantCaution":false},{"id":1240,"name":"OTOGIRISOU EKISU","func":"抗过敏,肌肤调理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"贯叶连翘（HYPERICUN PERFORATUM）提取物","pimpleCaution":false,"pregnantCaution":false},{"id":1108,"name":"HAMAMELIS VIRGINIANA (WITCH HAZEL) LEAF EXTRACT","func":"抗过敏,抗衰老,抗炎,收敛,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"北美金缕梅（HAMAMELIS VIRGINIANA）叶提取物","pimpleCaution":false,"pregnantCaution":false},{"id":4297,"name":"ROSA RUBIGINOSA SEED OIL","func":"柔润剂,肌肤调理","sensitization":false,"baseElement":true,"funcElement":true,"elementChinaName":"锈红蔷薇（ROSA RUBIGINOSA）籽油","pimpleCaution":false,"pregnantCaution":false},{"id":2253,"name":"CHAMOMILLA RECUTITA (MATRICARIA) FLOWER EXTRACT","func":"抗过敏,抗菌,抗炎,抗氧化,美白,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"母菊（CHAMOMILLA RECUTITA）花提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1800,"name":"PORTULACA OLERACEA EXTRACT","func":"保湿剂,减肥,抗过敏,抗菌,抗衰老,抗炎","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"马齿苋（PORTULACA OLERACEA）提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1290,"name":"PANTHENOL","func":"保湿剂,促进肌肤修复,促进伤口愈合,抗炎,头发调理,指甲护理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"泛醇","pimpleCaution":false,"pregnantCaution":false},{"id":1297,"name":"PHENOXYETHANOL","func":"定香,防腐剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"苯氧乙醇","pimpleCaution":false,"pregnantCaution":false},{"id":837,"name":"CARBOMER","func":"增稠,乳化稳定,悬浮","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"卡波姆","pimpleCaution":false,"pregnantCaution":false},{"id":1433,"name":"TRIETHANOLAMINE","func":"酸碱调节","sensitization":true,"baseElement":true,"funcElement":false,"elementChinaName":"三乙醇胺","pimpleCaution":false,"pregnantCaution":false}]}],"securityTip":"","baseTip":"","elementListOrder":0}
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
         * list : [{"gName":"","elementList":[{"id":762,"name":"AQUA","func":"溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"水","pimpleCaution":false,"pregnantCaution":false},{"id":1066,"name":"GLYCERIN","func":"保湿剂,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"甘油","pimpleCaution":false,"pregnantCaution":false},{"id":811,"name":"BUTYLENE GLYCOL","func":"保湿剂,抗菌,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"丁二醇","pimpleCaution":false,"pregnantCaution":false},{"id":1240,"name":"OTOGIRISOU EKISU","func":"抗过敏,肌肤调理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"贯叶连翘（HYPERICUN PERFORATUM）提取物","pimpleCaution":false,"pregnantCaution":false},{"id":1108,"name":"HAMAMELIS VIRGINIANA (WITCH HAZEL) LEAF EXTRACT","func":"抗过敏,抗衰老,抗炎,收敛,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"北美金缕梅（HAMAMELIS VIRGINIANA）叶提取物","pimpleCaution":false,"pregnantCaution":false},{"id":4297,"name":"ROSA RUBIGINOSA SEED OIL","func":"柔润剂,肌肤调理","sensitization":false,"baseElement":true,"funcElement":true,"elementChinaName":"锈红蔷薇（ROSA RUBIGINOSA）籽油","pimpleCaution":false,"pregnantCaution":false},{"id":2253,"name":"CHAMOMILLA RECUTITA (MATRICARIA) FLOWER EXTRACT","func":"抗过敏,抗菌,抗炎,抗氧化,美白,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"母菊（CHAMOMILLA RECUTITA）花提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1800,"name":"PORTULACA OLERACEA EXTRACT","func":"保湿剂,减肥,抗过敏,抗菌,抗衰老,抗炎","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"马齿苋（PORTULACA OLERACEA）提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1290,"name":"PANTHENOL","func":"保湿剂,促进肌肤修复,促进伤口愈合,抗炎,头发调理,指甲护理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"泛醇","pimpleCaution":false,"pregnantCaution":false},{"id":1297,"name":"PHENOXYETHANOL","func":"定香,防腐剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"苯氧乙醇","pimpleCaution":false,"pregnantCaution":false},{"id":837,"name":"CARBOMER","func":"增稠,乳化稳定,悬浮","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"卡波姆","pimpleCaution":false,"pregnantCaution":false},{"id":1433,"name":"TRIETHANOLAMINE","func":"酸碱调节","sensitization":true,"baseElement":true,"funcElement":false,"elementChinaName":"三乙醇胺","pimpleCaution":false,"pregnantCaution":false}]}]
         * securityTip :
         * baseTip :
         * elementListOrder : 0
         */

        private String securityTip;
        private String baseTip;
        private int elementListOrder;
        private List<ListBean> list;

        public String getSecurityTip() {
            return securityTip;
        }

        public void setSecurityTip(String securityTip) {
            this.securityTip = securityTip;
        }

        public String getBaseTip() {
            return baseTip;
        }

        public void setBaseTip(String baseTip) {
            this.baseTip = baseTip;
        }

        public int getElementListOrder() {
            return elementListOrder;
        }

        public void setElementListOrder(int elementListOrder) {
            this.elementListOrder = elementListOrder;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * gName :
             * elementList : [{"id":762,"name":"AQUA","func":"溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"水","pimpleCaution":false,"pregnantCaution":false},{"id":1066,"name":"GLYCERIN","func":"保湿剂,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"甘油","pimpleCaution":false,"pregnantCaution":false},{"id":811,"name":"BUTYLENE GLYCOL","func":"保湿剂,抗菌,溶剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"丁二醇","pimpleCaution":false,"pregnantCaution":false},{"id":1240,"name":"OTOGIRISOU EKISU","func":"抗过敏,肌肤调理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"贯叶连翘（HYPERICUN PERFORATUM）提取物","pimpleCaution":false,"pregnantCaution":false},{"id":1108,"name":"HAMAMELIS VIRGINIANA (WITCH HAZEL) LEAF EXTRACT","func":"抗过敏,抗衰老,抗炎,收敛,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"北美金缕梅（HAMAMELIS VIRGINIANA）叶提取物","pimpleCaution":false,"pregnantCaution":false},{"id":4297,"name":"ROSA RUBIGINOSA SEED OIL","func":"柔润剂,肌肤调理","sensitization":false,"baseElement":true,"funcElement":true,"elementChinaName":"锈红蔷薇（ROSA RUBIGINOSA）籽油","pimpleCaution":false,"pregnantCaution":false},{"id":2253,"name":"CHAMOMILLA RECUTITA (MATRICARIA) FLOWER EXTRACT","func":"抗过敏,抗菌,抗炎,抗氧化,美白,收缩毛孔","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"母菊（CHAMOMILLA RECUTITA）花提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1800,"name":"PORTULACA OLERACEA EXTRACT","func":"保湿剂,减肥,抗过敏,抗菌,抗衰老,抗炎","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"马齿苋（PORTULACA OLERACEA）提取物\t","pimpleCaution":false,"pregnantCaution":false},{"id":1290,"name":"PANTHENOL","func":"保湿剂,促进肌肤修复,促进伤口愈合,抗炎,头发调理,指甲护理","sensitization":false,"baseElement":false,"funcElement":true,"elementChinaName":"泛醇","pimpleCaution":false,"pregnantCaution":false},{"id":1297,"name":"PHENOXYETHANOL","func":"定香,防腐剂","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"苯氧乙醇","pimpleCaution":false,"pregnantCaution":false},{"id":837,"name":"CARBOMER","func":"增稠,乳化稳定,悬浮","sensitization":false,"baseElement":true,"funcElement":false,"elementChinaName":"卡波姆","pimpleCaution":false,"pregnantCaution":false},{"id":1433,"name":"TRIETHANOLAMINE","func":"酸碱调节","sensitization":true,"baseElement":true,"funcElement":false,"elementChinaName":"三乙醇胺","pimpleCaution":false,"pregnantCaution":false}]
             */

            private String gName;
            private List<ElementListBean> elementList;

            public String getGName() {
                return gName;
            }

            public void setGName(String gName) {
                this.gName = gName;
            }

            public List<ElementListBean> getElementList() {
                return elementList;
            }

            public void setElementList(List<ElementListBean> elementList) {
                this.elementList = elementList;
            }

            public static class ElementListBean {
                /**
                 * id : 762
                 * name : AQUA
                 * func : 溶剂
                 * sensitization : false
                 * baseElement : true
                 * funcElement : false
                 * elementChinaName : 水
                 * pimpleCaution : false
                 * pregnantCaution : false
                 */

                private int id;
                private String name;
                private String func;
                private boolean sensitization;
                private boolean baseElement;
                private boolean funcElement;
                private String elementChinaName;
                private boolean pimpleCaution;
                private boolean pregnantCaution;

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

                public String getElementChinaName() {
                    return elementChinaName;
                }

                public void setElementChinaName(String elementChinaName) {
                    this.elementChinaName = elementChinaName;
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
            }
        }
    }
}
