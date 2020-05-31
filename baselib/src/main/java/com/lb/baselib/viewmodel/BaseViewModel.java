package com.lb.baselib.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lb.baselib.model.FailModel;

public class BaseViewModel extends ViewModel {
    private MutableLiveData<FailModel> failData;

    public BaseViewModel() {
        failData=new MutableLiveData<>() ;
    }

    public MutableLiveData<FailModel> getFailData() {
        return failData;
    }
}
