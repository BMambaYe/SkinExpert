package com.zhanghao.skinexpert.utils;

import com.zhanghao.skinexpert.beans.RecommendTagsDataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class RecommentTagsEvent {
    private List<RecommendTagsDataBean.DataBean> tagsList;
    public RecommentTagsEvent(List<RecommendTagsDataBean.DataBean> tagsList){
        this.tagsList=tagsList;
    }

    public List<RecommendTagsDataBean.DataBean> getTagsList() {
        return tagsList;
    }
}
