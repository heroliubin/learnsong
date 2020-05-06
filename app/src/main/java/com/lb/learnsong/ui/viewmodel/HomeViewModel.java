package com.lb.learnsong.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.Lyrics;
import com.lb.learnsong.bean.LyricsInfo;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.viewmodel.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<List<Lyrics>> data;

    private List<Lyrics> dataList;

    public HomeViewModel() {
        data = new MutableLiveData<>();

    }

    public void loadData(int p, int c, int type) {
        HttpHelper.getInstance().getHomeData(p, c, new Callback<BaseListBean<Lyrics>>(){
            @Override
            public void onResponse(Call<BaseListBean<Lyrics>> call, Response<BaseListBean<Lyrics>> response) {
                if (!response.isSuccessful()){
                    return;
                }
                if (response.body().getList() != null && response.body().getList().size() > 0) {
                    if (type == BaseViewModel.TYPE_FRESH) {
                        dataList = response.body().getList();
                    }else {
                        dataList.addAll(response.body().getList());
                    }
                    data.setValue(dataList);

                }

            }

            @Override
            public void onFailure(Call<BaseListBean<Lyrics>> call, Throwable t) {

            }

        });
    }

    public LiveData<List<Lyrics>> getText() {
        return data;
    }


}