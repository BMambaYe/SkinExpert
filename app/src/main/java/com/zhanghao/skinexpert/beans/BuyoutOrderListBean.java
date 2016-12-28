package com.zhanghao.skinexpert.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/28.
 */

public class BuyoutOrderListBean implements Serializable {

    /**
     * error_code : 0
     * message : 成功
     * data : {"list":[{"order_id":"2016122819846923","individual_price":99,"quantity":1,"status":0,"createTime":"2016-12-28 14:13:46","prepaid_price":0,"title":"美肤家清痘净颜精华露 30g","image":"http://img.ilikelabs.com/Uploads/217/217536_0-750-750@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":99,"dataType":"teMai"},{"order_id":"2016122819846922","individual_price":99,"quantity":1,"status":0,"createTime":"2016-12-28 14:09:07","prepaid_price":0,"title":"美肤家清痘净颜精华露 30g","image":"http://img.ilikelabs.com/Uploads/217/217536_0-750-750@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":99,"dataType":"teMai"},{"order_id":"2016122819846921","individual_price":140,"quantity":1,"status":0,"createTime":"2016-12-28 11:39:43","prepaid_price":0,"title":"CLIO珂莱欧素颜霜 50ml","image":"http://img.ilikelabs.com/Uploads/213/213677_0-500-500@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":135,"dataType":"teMai"},{"order_id":"2016122819846920","individual_price":99,"quantity":1,"status":-1,"createTime":"2016-12-28 11:12:54","prepaid_price":0,"title":"美肤家清痘净颜精华露 30g","image":"http://img.ilikelabs.com/Uploads/217/217536_0-750-750@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":99,"dataType":"teMai"},{"order_id":"2016122719846914","individual_price":99,"quantity":1,"status":-2,"createTime":"2016-12-27 20:59:08","prepaid_price":0,"title":"美肤家清痘净颜精华露 30g","image":"http://img.ilikelabs.com/Uploads/217/217536_0-750-750@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":99,"dataType":"teMai"},{"order_id":"2016122719846913","individual_price":140,"quantity":1,"status":-2,"createTime":"2016-12-27 20:35:25","prepaid_price":0,"title":"CLIO珂莱欧素颜霜 50ml","image":"http://img.ilikelabs.com/Uploads/213/213677_0-500-500@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":135,"dataType":"teMai"},{"order_id":"2016122719846912","individual_price":140,"quantity":1,"status":-2,"createTime":"2016-12-27 20:34:02","prepaid_price":0,"title":"CLIO珂莱欧素颜霜 50ml","image":"http://img.ilikelabs.com/Uploads/213/213677_0-500-500@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":135,"dataType":"teMai"},{"order_id":"2016122719846911","individual_price":140,"quantity":1,"status":-2,"createTime":"2016-12-27 20:31:27","prepaid_price":0,"title":"CLIO珂莱欧素颜霜 50ml","image":"http://img.ilikelabs.com/Uploads/213/213677_0-500-500@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":135,"dataType":"teMai"},{"order_id":"2016122719846910","individual_price":140,"quantity":1,"status":-2,"createTime":"2016-12-27 20:29:45","prepaid_price":0,"title":"CLIO珂莱欧素颜霜 50ml","image":"http://img.ilikelabs.com/Uploads/213/213677_0-500-500@750w_1x.jpg","description":" ","systemTime":"2016-12-28 14:13:58","total_price":135,"dataType":"teMai"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * order_id : 2016122819846923
             * individual_price : 99
             * quantity : 1
             * status : 0
             * createTime : 2016-12-28 14:13:46
             * prepaid_price : 0
             * title : 美肤家清痘净颜精华露 30g
             * image : http://img.ilikelabs.com/Uploads/217/217536_0-750-750@750w_1x.jpg
             * description :
             * systemTime : 2016-12-28 14:13:58
             * total_price : 99
             * dataType : teMai
             */

            private String order_id;
            private int individual_price;
            private int quantity;
            private int status;
            private String createTime;
            private int prepaid_price;
            private String title;
            private String image;
            private String description;
            private String systemTime;
            private int total_price;
            private String dataType;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getIndividual_price() {
                return individual_price;
            }

            public void setIndividual_price(int individual_price) {
                this.individual_price = individual_price;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getPrepaid_price() {
                return prepaid_price;
            }

            public void setPrepaid_price(int prepaid_price) {
                this.prepaid_price = prepaid_price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSystemTime() {
                return systemTime;
            }

            public void setSystemTime(String systemTime) {
                this.systemTime = systemTime;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }
        }
    }
}
