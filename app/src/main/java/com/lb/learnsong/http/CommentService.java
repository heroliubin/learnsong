package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.CommentInfo;
import com.lb.learnsong.bean.WordInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CommentService {
    @FormUrlEncoded
    @POST("music/getComment")
    Call<BaseListBean<CommentInfo>> execute(@Field("token") String token,
                                            @Field("typeid") String typeid,
                                            @Field("page") String page,
                                            @Field("count") String count);
    @FormUrlEncoded
    @POST("music/addcomment")
    Call<BaseBean> execute(@Field("token") String token,
                          @Field("typeid") String typeid,
                          @Field("type") String type,
                          @Field("content") String content,
                          @Field("commentid") String commentid);

}
