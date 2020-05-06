package com.lb.learnsong.http;


import com.lb.learnsong.bean.BaseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallback implements Callback<BaseBean> {


    @Override
    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
        if (!response.isSuccessful()){
            return;
        }
        if (response.body()!=null){
            if (response.body().status==0){

                onsucess(response.body());
            }else {
                onfail("错误信息: "+response.body().msg);
            }
        }
    }

    protected abstract void onsucess(BaseBean body);

    protected abstract void onfail(String fail);

    @Override
    public void onFailure(Call<BaseBean> call, Throwable t) {
        onfail("网络连接错误");
    }
}
