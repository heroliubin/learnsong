package com.lb.learnsong.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lb.baselib.viewmodel.BaseViewModel;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.http.HttpHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSongViewModel extends BaseViewModel {
    private MutableLiveData<BaseBean> songresult;
    private MutableLiveData<BaseBean> lyricresult;
    private MutableLiveData<BaseBean> albumresult;

    public AddSongViewModel() {
        songresult = new MutableLiveData<>();
        lyricresult = new MutableLiveData<>();
        albumresult = new MutableLiveData<>();
    }

    public MutableLiveData<BaseBean> getSongresult() {
        return songresult;
    }

    public MutableLiveData<BaseBean> getLyricresult() {
        return lyricresult;
    }

    public MutableLiveData<BaseBean> getAlbumresult() {
        return albumresult;
    }
    public void addsong(String token, String name, String headimg, String synopsis){
        HttpHelper.getInstance().addSongster(token, name, headimg, synopsis, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
    public void addlyric(String token, String songsterId, String albumId, String cover,String synopsis,
                         String type,String name,String translate){
        HttpHelper.getInstance().addLyrics(token, songsterId, albumId, cover, synopsis, type, name,
                translate, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
    public void addalbum(String token, String songsterId, String cover,String synopsis,
                         String name){
        HttpHelper.getInstance().addAlbum(token, songsterId, cover, synopsis, name, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
}
