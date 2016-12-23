package com.zhanghao.skinexpert.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/22.
 */

public class CommunityBeansContainer implements Serializable {
    List<ProductDetailBean.DataBean.ProductBean.CommunityBean> communitybeans;

    public CommunityBeansContainer(List<ProductDetailBean.DataBean.ProductBean.CommunityBean> communitybeans) {
        this.communitybeans = communitybeans;
    }

    public List<ProductDetailBean.DataBean.ProductBean.CommunityBean> getCommunitybeans() {
        return communitybeans;
    }
}
