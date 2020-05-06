package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.BaseobjBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpListcallback<T> implements Callback<BaseListBean>{
    /**
     *
     * @param body
     * @param size 0表示翻页时加载更多为0,1为正常翻页情况
     */

    private ResponseLisenter lisenter;
   protected void setlisenter(ResponseLisenter lisenter){

       this.lisenter=lisenter;
   };



    @Override
    public void onResponse(Call<BaseListBean> call, Response<BaseListBean> response) {
        if (!response.isSuccessful()){
            return;
        }
        if (response.body()!=null){
            if (response.body().status==0&&response.body().getList()!=null){
                if (response.body().getList().size()>0){
                    if (lisenter!=null){

                       lisenter. onsucess(response.body().getList(),1);
                    }
                }else {
                    if (lisenter!=null){

                       lisenter. onsucess(response.body().getList(),0);
                    }

                }
            }else {
                if (lisenter!=null){

                   lisenter.onfail("错误信息: "+response.body().msg);
                }
            }
        }
    }

    @Override
    public void onFailure(Call<BaseListBean> call, Throwable t) {
        if (lisenter!=null){
            lisenter.onfail("网络连接错误");
        }
    }
    interface ResponseLisenter{
         abstract void onsucess(List body, int size);
        abstract void onfail(String fail);
    }

}

