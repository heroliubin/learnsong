package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.Lyrics;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * create by: dh
 * on: 2020/6/1
 */
public interface Homeservice {

    @FormUrlEncoded
    @POST("music/getHomePage")
    Call<BaseListBean<Lyrics>> execute(@QueryMap Map<String, String> map);


}
