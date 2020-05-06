package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.WordInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LyricService {
    /**
     * 添加歌手
     */

    @FormUrlEncoded
    @POST("music/addSongster")
    Call<BaseBean> execute(@Field("token") String token, @Field("name") String name,
                           @Field("headimg") String headimg, @Field("synopsis") String synopsis);

    /**
     * 添加歌词
     */

    @FormUrlEncoded
    @POST("music/addLyrics")
    Call<BaseBean> execute(@Field("token") String token, @Field("songsterId") String songsterId,
                           @Field("albumId") String albumId, @Field("cover") String cover, @Field("synopsis") String synopsis
            , @Field("type") String type, @Field("name") String name, @Field("translate") String translate);

    /**
     * 添加专辑
     */

    @FormUrlEncoded
    @POST("music/addAlbum")
    Call<BaseBean> execute(@Field("token") String token, @Field("songsterId") String songsterId,
                           @Field("cover") String cover, @Field("synopsis") String synopsis
            , @Field("name") String name);


}
