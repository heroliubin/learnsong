package com.lb.learnsong.ui.activity.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.activity.data.LoginRepository;
import com.lb.learnsong.ui.activity.data.Result;
import com.lb.learnsong.ui.activity.data.model.LoggedInUser;
import com.lb.learnsong.uiUtil.NullUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    public MutableLiveData<String> getToken() {
        return token;
    }

    private MutableLiveData<String> token = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        HttpHelper.getInstance().dologin(username, password, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().status == 0) {
                        if (NullUtils.isNotEmpty(response.body().obj)){

                            token.setValue(response.body().obj);
                        }else {
                            token.setValue("error:" + response.body().msg);
                        }
                    } else {
                        token.setValue("error:" + response.body().msg);
                    }
                } else {
                    token.setValue("error:" + response.message());
                }

            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                token.setValue("error:" + t.getMessage());
            }
        });
        // can be launched in a separate asynchronous job
//        Result<LoggedInUser> result = loginRepository.login(username, password);
//
//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
