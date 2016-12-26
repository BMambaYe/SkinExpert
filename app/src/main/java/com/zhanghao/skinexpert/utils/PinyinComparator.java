package com.zhanghao.skinexpert.utils;

import com.zhanghao.skinexpert.beans.ProductBrandBean;

import java.util.Comparator;

/**
 * Created by zhanghao on 2016/12/24.
 */
public class PinyinComparator implements Comparator<ProductBrandBean> {

    public int compare(ProductBrandBean o1, ProductBrandBean o2) {
        if ("@".equals(o1.getSortLetters())
                || "#".equals(o2.getSortLetters())) {
            return -1;
        } else if ("#".equals(o1.getSortLetters())
                || "@".equals(o2.getSortLetters())) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}
