package com.lb.learnsong.model;

public interface IModel {
    void LoadData();
    interface OnLoadListener {
        void OnComplete();
    }
}
