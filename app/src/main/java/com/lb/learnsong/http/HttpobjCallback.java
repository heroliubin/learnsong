package com.lb.learnsong.http;

import android.renderscript.BaseObj;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseobjBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpobjCallback<T> implements Callback<BaseobjBean> {


    @Override
    public void onResponse(Call<BaseobjBean> call, Response<BaseobjBean> response) {
        if (!response.isSuccessful()){
            return;
        }
        if (response.body()!=null){
            if (response.body().status==0&&response.body().getList()!=null){

                onsucess((T)response.body().getList());
            }else {
                onfail("错误信息: "+response.body().msg);
            }
        }
    }

    protected abstract void onsucess(T body);

    protected abstract void onfail(String fail);

    @Override
    public void onFailure(Call<BaseobjBean> call, Throwable t) {
        onfail("网络连接错误");
    }
}
