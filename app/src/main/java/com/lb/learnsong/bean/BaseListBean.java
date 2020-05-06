package com.lb.learnsong.bean;

import java.io.Serializable;
import java.util.List;

public class BaseListBean<T> extends BaseBean {


    private List<T>  list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
