package com.zhanghao.skinexpert.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/24.
 */

public class ElementsContainer implements Serializable {
    private List<DetailElementBean.DataBean.ListBean.ElementListBean> elements;

    public ElementsContainer(List<DetailElementBean.DataBean.ListBean.ElementListBean> elements) {
        this.elements = elements;
    }

    public List<DetailElementBean.DataBean.ListBean.ElementListBean> getElements() {
        return elements;
    }
}
