package com.lb.learnsong.bean;

import java.io.Serializable;
import java.util.List;

public class BaseBeantoobj<T> implements Serializable {
    public int status;
    public String msg;
    public int num;
    public T obj;
    public List list;

    public BaseBeantoobj(T obj) {
        this.obj = obj;
    }
}
