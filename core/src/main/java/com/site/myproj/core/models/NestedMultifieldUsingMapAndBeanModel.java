package com.site.myproj.core.models;

import com.site.myproj.core.bean.NestedMultifieldBean;

import java.util.List;

public interface NestedMultifieldUsingMapAndBeanModel {

    List<NestedMultifieldBean> getNestedBookDetailsBean();

    List<NestedMultifieldBean> getMultifieldItems();
}