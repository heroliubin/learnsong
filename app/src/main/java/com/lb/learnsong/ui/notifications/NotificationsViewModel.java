package com.lb.learnsong.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.UserInfo;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.viewmodel.BaseViewModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsViewModel extends BaseViewModel {

    private MutableLiveData<BaseBeantoobj<UserInfo>> userInfo;


    private MutableLiveData<BaseBean> edit;

    public MutableLiveData<BaseBean> getEdit() {

        return edit;
    }



    public NotificationsViewModel() {
        userInfo = new MutableLiveData<>();
        edit=new MutableLiveData<>();

    }

    public LiveData<BaseBeantoobj<UserInfo>> getText() {
        return userInfo;
    }
    public void loadedit(String token,String headimg,String nickname,String userDesc){
        HttpHelper.getInstance().EditUserInfo(token,headimg,nickname, userDesc,new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()){
                    if (response.body().status==0){
                        edit.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });


    }
    public void loadinfo(String token){
        HttpHelper.getInstance().getUserInfo(token, new Callback<BaseBeantoobj<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseBeantoobj<UserInfo>> call, Response<BaseBeantoobj<UserInfo>> response) {
                if (response.isSuccessful()){
                    if (response.body().status==0){
                        userInfo.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseBeantoobj<UserInfo>> call, Throwable t) {

            }
        });

    }



}