package com.lb.learnsong.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.lb.learnsong.R;
import com.lb.learnsong.uiUtil.NullUtils;

public class BaseLsActivity extends AppCompatActivity {
    private View mActionBarView;
    protected void setCustomActionBar() {
        if (getSupportActionBar() == null) {
            return;
        }
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mActionBarView,
                new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT));
        Toolbar toolbar = (Toolbar) actionBar.getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        mActionBarView.findViewById(R.id.back).setOnClickListener(v -> onleftclick());

    }

    protected void setTitle(String title) {
        TextView titletx = mActionBarView.findViewById(R.id.actionbar_title);
        titletx.setText(title);
    }

    protected void setRightTitle(String rightTitle) {
        TextView titletx = mActionBarView.findViewById(R.id.right_text);
        titletx.setText(rightTitle);
    }

    protected LinearLayout getRight() {
        LinearLayout linearLayout = mActionBarView.findViewById(R.id.actionbar_right);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.setOnClickListener(v -> onrightclick());
        return linearLayout;
    }

    protected void onleftclick() {
        finish();
    }

    protected void onrightclick() {
    }

    protected void gotoActivity(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }

    public void makeToast(CharSequence msg) {
        if (msg != null && NullUtils.isNotEmpty(msg.toString())) {

            makeToast(msg, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 弹出提示信息
     *
     * @param msg      信息
     * @param duration 弹出时间长度
     */
    public void makeToast(CharSequence msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    public void makeToast(int resId) {
        makeToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 弹出提示信息
     *
     * @param resId    资源ID
     * @param duration 弹出时间长度
     */
    public void makeToast(int resId, int duration) {
        Toast.makeText(this, resId, duration).show();
    }

    protected boolean isSign() {

        return NullUtils.isEmpty(getToken());
    }

    protected String getToken() {

        return getSharedPreferences("init", Context.MODE_PRIVATE).getString("token", null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        View decorView = window.getDecorView();
        window.setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (isfullseceen()){
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }

    }
    protected boolean isfullseceen(){
        return false;
    }

}
