package com.lb.learnsong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.BaseLsActivity;
import com.lb.learnsong.ui.activity.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseLsActivity {
    @BindView(R.id.account_phone)
    EditText accountPhone;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.verify_bt)
    Button verifyBt;
    @BindView(R.id.account_pwd)
    EditText accountPwd;
    @BindView(R.id.seepsw)
    ImageView seepsw;
    @BindView(R.id.seepswll)
    LinearLayout seepswll;
    @BindView(R.id.sign_up)
    TextView signUp;
    @BindView(R.id.go_login)
    TextView goLogin;
    @BindView(R.id.go_pact_ll)
    LinearLayout goPactLl;
    @BindView(R.id.screen)
    LinearLayout screen;
    private boolean showPassword = true;
    private boolean isphone, ispsd, isver;
    private String phone, pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setCustomActionBar();
        setTitle("注册");
        String str = "已有账号?    去登录";
        int fstart = str.indexOf("去");
        int fend = fstart + "去登录".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        goLogin.setText(style);
        setwatchlisten();
    }

    @OnClick({R.id.verify_bt, R.id.seepswll,
            R.id.sign_up, R.id.go_login, R.id.go_pact_ll, R.id.screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.verify_bt:

                verifyBt.setEnabled(false);
                new CountDownTimer(60000, 1000) {
                    public void onTick(
                            long millisUntilFinished) {
                        verifyBt.setText(millisUntilFinished
                                / 1000 + "秒");
                    }

                    public void onFinish() {
                        verifyBt.setText("获取验证码");
                        verifyBt.setEnabled(true);
                    }
                }.start();
                HttpHelper.getInstance().getPhoneCode(accountPhone.getText().toString(), 0 + "", new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        makeToast(response.body().msg);
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });


                break;
            case R.id.seepswll:
                if (showPassword) {
                    seepsw.setImageDrawable(getResources().getDrawable(R.mipmap.eye));
                    accountPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    accountPwd.setSelection(accountPwd.getText().toString().length());
                    showPassword = !showPassword;
                } else {
                    accountPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    accountPwd.setSelection(accountPwd.getText().toString().length());
                    showPassword = !showPassword;
                    seepsw.setImageDrawable(getResources().getDrawable(R.mipmap.seepsw));
                }
                break;
            case R.id.sign_up:
                phone = accountPhone.getText() + "";
                if (TextUtils.isEmpty(phone)) {
                    makeToast(R.string.input_email);
                    return;
                }
                pwd = accountPwd.getText() + "";
                if (TextUtils.isEmpty(pwd)) {
                    makeToast(R.string.input_password);
                    return;
                } else if (pwd.length() < 6 || pwd.length() > 20) {
                    makeToast(R.string.password_lenght_6_22);
                    return;
                }
                if (TextUtils.isEmpty(codeEt.getText().toString())) {
                    makeToast("请输入验证码");
                    return;
                }
                if (signUp.isSelected()) {

                    HttpHelper.getInstance().doregister(accountPhone.getText().toString(), codeEt.getText().toString(),
                            accountPwd.getText().toString(), new Callback<BaseBean>() {
                                @Override
                                public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                                    makeToast(response.body().msg);
                                    if (response.body().status==0){

                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseBean> call, Throwable t) {

                                }
                            });
                }
                break;
            case R.id.go_login:
                finish();
                break;
            case R.id.go_pact_ll:
                break;
            case R.id.screen:
                InputMethodManager imm = (InputMethodManager) this
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
        }
    }

    public void setwatchlisten() {
        accountPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                isphone = s != null && s.length() > 6;
                if (isphone && ispsd && isver) {
                    signUp.setSelected(true);
                } else {
                    signUp.setSelected(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        accountPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                ispsd = s != null && s.length() >= 6;
                if (isphone && ispsd && isver) {
                    signUp.setSelected(true);
                } else {
                    signUp.setSelected(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        codeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isver = s != null && s.length() > 0;
                if (isphone && ispsd && isver) {
                    signUp.setSelected(true);
                } else {
                    signUp.setSelected(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
