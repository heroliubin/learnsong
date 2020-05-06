package com.lb.learnsong.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lb.learnsong.presenter.BasePresenter;
import com.lb.learnsong.view.IView;

public abstract class BaseActivity<T extends BasePresenter,V extends IView> extends BaseLsActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=creatPresenter();
        presenter.attachView((V)this);
    }
    protected T presenter;
    protected abstract T creatPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
