package com.zhanghao.skinexpert.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by RockGao on 2016/12/28.
 */

public interface FragmentDataOnRefresh {
    public void onRefresh(List<Map<String,String>> datalist);
}
