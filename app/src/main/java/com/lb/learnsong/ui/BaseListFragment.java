package com.lb.learnsong.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseListFragment extends BaseFragment{

    private View rootview;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(getLayout(), null, false);
        initView(rootview);
        return rootview;
    }
    protected void initView(View view) {

    }

    protected abstract int getLayout();
    protected abstract void lazyload();
    

    @Override
    public void onResume() {
        super.onResume();
        if (isfirstload){
            lazyload();
            isfirstload=false;
        }
    }


}
