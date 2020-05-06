package com.lb.learnsong.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.CommentInfo;
import com.lb.learnsong.http.HttpHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends BaseViewModel {
    private MutableLiveData<BaseListBean<CommentInfo>> commentlist;

    public MutableLiveData<BaseBean> getCommentresult() {
        return commentresult;
    }

    private MutableLiveData<BaseBean> commentresult;

    public CommentViewModel() {
        commentlist = new MutableLiveData<>();
        commentresult=new MutableLiveData<>();
    }

    public MutableLiveData<BaseListBean<CommentInfo>> getCommentlist() {
        return commentlist;
    }
    public void Commentlist(String token, String typeid, String page, String count) {
        HttpHelper.getInstance().getCommentlist(token, typeid, page, count, new Callback<BaseListBean<CommentInfo>>() {
            @Override
            public void onResponse(Call<BaseListBean<CommentInfo>> call, Response<BaseListBean<CommentInfo>> response) {
                if (response.isSuccessful()){
                    commentlist.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseListBean<CommentInfo>> call, Throwable t) {

            }
        });
    }
    public void addComment(String token,String typeid,String tpye,String content,String commentid){
        HttpHelper.getInstance().addComment(token, typeid, tpye, content, commentid, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()){
                    commentresult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
}
