package com.lb.learnsong.ui.viewmodel;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.MutableLiveData;

import com.lb.baselib.model.FailModel;
import com.lb.baselib.viewmodel.BaseViewModel;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.http.HttpHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilViewModel extends BaseViewModel {
    private MutableLiveData<BaseBean> upimgresult;

    public UtilViewModel() {

        upimgresult=new MutableLiveData<>();
    }

    public MutableLiveData<BaseBean> getUpimgresult() {
        return upimgresult;
    }

    public void upimg(RequestBody img, MultipartBody.Part file) {
        HttpHelper.getInstance().uploadimg(img, file, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()) {
                    upimgresult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                getFailData().setValue(new FailModel());
            }
        });
    }
    public void uphead(Uri uri, ContentResolver resolver){

        final Bitmap photo;
        try {
            photo = MediaStore.Images.Media.getBitmap(resolver, uri);
            RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),Bitmap2Bytes(photo));
            MultipartBody.Part typeByteArray= MultipartBody.Part.createFormData("img","headphoto", requestBody);
            upimg(requestBody,typeByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
}
