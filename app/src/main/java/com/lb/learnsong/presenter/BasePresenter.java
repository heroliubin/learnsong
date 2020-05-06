package com.lb.learnsong.presenter;

import com.lb.learnsong.view.IView;

import java.lang.ref.WeakReference;

public class BasePresenter <T extends IView> {
    WeakReference<T> iView;
    public void attachView(T view){
        iView=new WeakReference<>(view);
    }
    public void detachView(){
        if (iView!=null){
            iView.clear();
            iView=null;
        }
    }

}
