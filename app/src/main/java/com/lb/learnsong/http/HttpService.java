package com.lb.learnsong.http;

import com.lb.learnsong.bean.Lyrics;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HttpService {
    Retrofit retrofit;


    String url="http://120.24.185.40//app/music/";
    interface GetHomeService{
        @FormUrlEncoded
        @POST("getHomePage")
        Call<Lyrics> execute();

    };
    public void getHomeData(Callback callback){
        GetHomeService service=retrofit.create(GetHomeService.class);
        service.execute().enqueue(callback);
    }

    public HttpService() {
        retrofit=new Retrofit.Builder().baseUrl(url).
                client(new OkHttpClient.Builder().build()).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

}
