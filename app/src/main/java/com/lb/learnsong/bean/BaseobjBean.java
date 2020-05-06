package com.lb.learnsong.bean;

public class BaseobjBean<T> extends BaseBean{
   private T list;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
