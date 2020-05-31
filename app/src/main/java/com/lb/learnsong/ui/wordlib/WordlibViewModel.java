package com.lb.learnsong.ui.wordlib;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lb.baselib.viewmodel.BaseViewModel;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.http.HttpHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordlibViewModel extends BaseViewModel {
    private MutableLiveData<List<WordInfo>> listword;

    public MutableLiveData<List<WordInfo>> getListword() {
        return listword;
    }

    public WordlibViewModel() {
       listword=new MutableLiveData<>();
    }
    public void getdata(String token,String page,String count){
        HttpHelper.getInstance().getCollectWord(token, page, count, new Callback<BaseListBean<WordInfo>>() {
            @Override
            public void onResponse(Call<BaseListBean<WordInfo>> call, Response<BaseListBean<WordInfo>> response) {
                if (response.isSuccessful()){
                    if (response.body().status==0){
                        listword.setValue(response.body().getList());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseListBean<WordInfo>> call, Throwable t) {

            }
        });
    }


}