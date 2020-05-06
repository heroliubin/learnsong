package com.lb.learnsong.ui;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.lb.learnsong.uiUtil.NullUtils;

public class BaseFragment extends Fragment {
    protected boolean isfirstload = true;
    protected boolean isSign() {

        return NullUtils.isNotEmpty(getToken());
    }

    protected String getToken() {

        return getActivity().getSharedPreferences("init", Context.MODE_PRIVATE).getString("token", null);
    }
    @Override
    public void onDestroyView() {
        isfirstload=true;
        Log.e("lb",getClass().getSimpleName()+"onDestroyView");
        super.onDestroyView();
    }
}
