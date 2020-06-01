package com.lb.learnsong.http;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.BaseobjBean;
import com.lb.learnsong.bean.CommentInfo;
import com.lb.learnsong.bean.Lyrics;
import com.lb.learnsong.bean.LyricsInfo;
import com.lb.learnsong.bean.UserInfo;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.ui.activity.ui.login.LoginActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class HttpHelper {
    private Retrofit retrofit;
    public static class HttpHelperHolder{
        public static HttpHelper intance=new HttpHelper();
    }

    public static HttpHelper getInstance() {

        return HttpHelperHolder.intance;
    }

    private HttpHelper() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder().baseUrl(url).
                client(new OkHttpClient.Builder().addInterceptor(logging).build()).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();

    }

    private String url = "http://120.24.185.40//app/";


    public <T> T initservice(Class<T> service){
        return retrofit.create(service);
    }



    /**
     * 注册
     */
    interface RegisterService {
        @FormUrlEncoded
        @POST("user/register")
        Call<BaseBean> execute(@Field("phone") String phone, @Field("yzcode") String yzcode, @Field("userpwd") String userpwd);

    }


    public void doregister(String phone, String yzcode, String userpwd, Callback<BaseBean> callback) {
        RegisterService service = retrofit.create(RegisterService.class);
        service.execute(phone, yzcode, userpwd).enqueue(callback);
    }

    /**
     * 登录
     */
    interface LoginService {
        @FormUrlEncoded
        @POST("user/login")
        Call<BaseBean> execute(@Field("phone") String phone, @Field("userpwd") String userpwd);

    }


    public void dologin(String phone, String userpwd, Callback<BaseBean> callback) {
        LoginService service = retrofit.create(LoginService.class);
        service.execute(phone, userpwd).enqueue(callback);
    }

    /**
     * 获取验证码
     */
    interface getPhoneCodeService {
        @FormUrlEncoded
        @POST("user/getPhoneCode")
        Call<BaseBean> execute(@Field("phone") String phone, @Field("type") String type);

    }


    public void getPhoneCode(String phone, String type, Callback<BaseBean> callback) {
        getPhoneCodeService service = retrofit.create(getPhoneCodeService.class);
        service.execute(phone, type).enqueue(callback);
    }

    /**
     * 获取个人信息
     */
    interface getUserInfoService {
        @FormUrlEncoded
        @POST("user/getUserInfo")
        Call<BaseBeantoobj<UserInfo>> execute(@Field("token") String token);

    }


    public void getUserInfo(String token, Callback<BaseBeantoobj<UserInfo>> callback) {
        getUserInfoService service = retrofit.create(getUserInfoService.class);
        service.execute(token).enqueue(callback);
    }

    /**
     * 修改个人信息
     */
    interface EditUserInfoService {
        @FormUrlEncoded
        @POST("user/editUserInfo")
        Call<BaseBean> execute(@Field("token") String token, @Field("headimg") String headimg, @Field("nickname") String nickname,
                               @Field("userDesc") String userDesc);

    }


    public void EditUserInfo(String token, String headimg, String nickname, String userDesc,
                             Callback<BaseBean> callback) {
        EditUserInfoService service = retrofit.create(EditUserInfoService.class);
        service.execute(token, headimg, nickname, userDesc).enqueue(callback);
    }

    /**
     * 歌曲详情
     */
    interface getLyricsByIdService {
        @FormUrlEncoded
        @POST("music/getLyricsById")
        Call<BaseBeantoobj<LyricsInfo>> execute(@Field("token") String token, @Field("id") String id);

    }


    public void getLyricsById(String token, String id,
                              Callback<BaseBeantoobj<LyricsInfo>> callback) {
        getLyricsByIdService service = retrofit.create(getLyricsByIdService.class);
        service.execute(token, id).enqueue(callback);
    }

    /**
     * 收藏
     */
    interface MusicCollectService {
        @FormUrlEncoded
        @POST("music/musicCollect")
        Call<BaseBean> execute(@Field("token") String token, @Field("type") String typem, @Field("typeid") String typeid
                , @Field("status") String status);

    }

    /**
     * @param token
     * @param type     0:歌手 1：专辑 2：歌曲 3：评论 4：单词
     * @param typeid   类型id
     * @param status   1：收藏 2：取消收藏
     * @param callback
     */
    public void MusicCollect(String token, String type, String typeid, String status,
                             Callback<BaseBean> callback) {
        MusicCollectService service = retrofit.create(MusicCollectService.class);
        service.execute(token, type, typeid, status).enqueue(callback);
    }

    /**
     * 点赞
     */
    interface MusicPraiseService {
        @FormUrlEncoded
        @POST("music/musicCollect")
        Call<BaseBean> execute(@Field("token") String token, @Field("type") String typem, @Field("typeid") String typeid
                , @Field("status") String status);

    }

    /**
     * @param token
     * @param type     0:歌手 1：专辑 2：歌曲 3：评论 4：单词
     * @param typeid   类型id
     * @param status   1：点赞 2：取消点赞
     * @param callback
     */
    public void MusicPraise(String token, String type, String typeid, String status,
                            Callback<BaseBean> callback) {
        MusicPraiseService service = retrofit.create(MusicPraiseService.class);
        service.execute(token, type, typeid, status).enqueue(callback);
    }

    /**
     * 单词详情
     */



    public void getWordInfo(String token, String word,
                            Callback<BaseBeantoobj<WordInfo>> callback) {
        WordService service = retrofit.create(WordService.class);
        service.execute(token, word).enqueue(callback);
    }

    /**
     * 单词库
     */



    public void getCollectWord(String token, String page, String count,
                               Callback<BaseListBean<WordInfo>> callback) {
        WordService service = retrofit.create(WordService.class);
        service.execute(token, page, count).enqueue(callback);
    }

    /**
     *添加单词
     * @param token
     */
    public void addtWord(String token, String targetWord, String phonetic,String chineseWord,String voiceFileUrl,String file
                              , Callback<BaseBean> callback) {
        WordService service = retrofit.create(WordService.class);
        service.execute(token, targetWord, phonetic,chineseWord,voiceFileUrl,file).enqueue(callback);
    }


    /**
     * 上传图片
     */
    interface UploadimgService {
        @Multipart
        @POST("uploadimg")
        Call<BaseBean> execute(@Part("img") RequestBody description,
                               @Part MultipartBody.Part file);
    }


    public void uploadimg(RequestBody img, MultipartBody.Part file,
                          Callback<BaseBean> callback) {
        UploadimgService service = retrofit.create(UploadimgService.class);
        service.execute(img, file).enqueue(callback);
    }

    /**
     * 获取评论列表
     */
    public void getCommentlist(String token, String typeid, String page, String count, Callback<BaseListBean<CommentInfo>>
            callback) {
        CommentService service = retrofit.create(CommentService.class);
        service.execute(token, typeid, page, count).enqueue(callback);
    }

    /**
     * 发送评论
     */
    public void addComment(String token, String typeid, String type, String content, String commentid, Callback<BaseBean>
            callback) {
        CommentService service = retrofit.create(CommentService.class);
        service.execute(token, typeid, type, content, commentid).enqueue(callback);
    }
    /**
     * 添加歌手
     */
    public void addSongster(String token, String name, String headimg, String synopsis, Callback<BaseBean>
            callback) {
        LyricService service = retrofit.create(LyricService.class);
        service.execute(token, name, headimg, synopsis).enqueue(callback);
    }
 /**
     * 添加歌曲
     */
    public void addLyrics(String token, String songsterId, String albumId, String cover,String synopsis,
                          String type,String name,String translate,Callback<BaseBean>
            callback) {
        LyricService service = retrofit.create(LyricService.class);
        service.execute(token, songsterId, albumId,cover,synopsis,type,name,translate).enqueue(callback);
    }
    /**
     * 添加专辑
     */
    public void addAlbum(String token, String songsterId, String cover,String synopsis,
                         String name,Callback<BaseBean>
            callback) {
        LyricService service = retrofit.create(LyricService.class);
        service.execute(token, songsterId, cover,synopsis,name).enqueue(callback);
    }


}
