package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.WordInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WordService {
    /**
     * 单词库
     */

    @FormUrlEncoded
    @POST("music/getCollectWord")
    Call<BaseListBean<WordInfo>> execute(@Field("token") String token, @Field("page") String page,
                                         @Field("count") String count);

    /**
     * 单词详情
     */

    @FormUrlEncoded
    @POST("music/getWordIsHaveByTargetWord")
    Call<BaseBeantoobj<WordInfo>> execute(@Field("token") String token, @Field("targetWord") String word);


    //添加单词
    @FormUrlEncoded
    @POST("music/addWord")
    Call<BaseBean> execute(@Field("token") String token, @Field("targetWord") String word,@Field("phonetic") String phonetic,
                           @Field("chineseWord") String chineseWord,@Field("voiceFileUrl") String voiceFileUrl,
                           @Field("file") String file);


}
