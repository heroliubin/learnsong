package com.lb.learnsong.ui.notifications;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lb.learnsong.MainActivity;
import com.lb.learnsong.R;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.UserInfo;
import com.lb.learnsong.ui.BaseLsActivity;
import com.lb.learnsong.ui.viewmodel.UtilViewModel;
import com.lb.learnsong.uiUtil.ImageLoad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoActivity extends BaseLsActivity {
    @BindView(R.id.ll_headimg)
    ImageView llHeadimg;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.username_edittext)
    EditText usernameEdittext;
    @BindView(R.id.ll_username)
    LinearLayout llUsername;
    @BindView(R.id.ll_userdesc_edittext)
    EditText llUserdescEdittext;
    @BindView(R.id.ll_userdesc)
    LinearLayout llUserdesc;
    @BindView(R.id.signout)
    Button signout;
    private NotificationsViewModel viewModel;
    private UtilViewModel utilViewModel;
    private File cameraImage;
    private Uri imageUri;
    public static final int PHOTOHRAPH = 1;
    public static final int PHOTOZOOM = 2;

    private Dialog avatarDialog;
    private View avatarView;
    private String head="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        setCustomActionBar();
        setTitle("个人信息");
        getRight();
        setRightTitle("保存");
        viewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        utilViewModel = new ViewModelProvider(this).get(UtilViewModel.class);
        viewModel.loadinfo(getToken());
        viewModel.getText().observe(this, userInfoBaseBeantoobj -> {
            usernameEdittext.setText(userInfoBaseBeantoobj.obj.getNickname());
            llUserdescEdittext.setText(userInfoBaseBeantoobj.obj.getUserDesc());
            ImageLoad.LoadCircleImage(this,userInfoBaseBeantoobj.obj.getHeadimg(),llHeadimg);
        });
        viewModel.getEdit().observe(
                this, baseBean -> {
                    makeToast(baseBean.msg);
                    if (baseBean.status==0){
                        Intent intent=new Intent(this, MainActivity.class);
                        setResult(Activity.RESULT_OK,intent);
                        finish();
                    }
                }
        );
        utilViewModel.getUpimgresult().observe(this, baseBean -> {
            if (baseBean.status==0){
                head=baseBean.obj;
                ImageLoad.LoadCircleImage(UserInfoActivity.this,baseBean.obj,llHeadimg);
            }
        });
    }

    @Override
    protected void onrightclick() {

        viewModel.loadedit(getToken(), head, usernameEdittext.getText().toString(), llUserdescEdittext.getText().toString());
    }

    @OnClick({R.id.ll_headimg, R.id.signout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_headimg:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    dophoto();
                } else {
                   photo();
                }
                break;
            case R.id.signout:
                getSharedPreferences("init", MODE_PRIVATE).edit().putString("token", "").commit();
                finish();
                break;
        }
    }

    public void dophoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    10);
        } else { //有权限直接调用系统相机拍照
            photo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            dophoto();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTOHRAPH) {
                utilViewModel.uphead(imageUri,getContentResolver());
            }
            if (requestCode == PHOTOZOOM) {
                if (data == null) {
                    return;
                }
                utilViewModel.uphead(data.getData(),getContentResolver());
            }
        }
    }

    public void photo() {
        if (avatarDialog == null) {
            avatarDialog = new Dialog(this, R.style.dialog);
            avatarView = getLayoutInflater().inflate(
                    R.layout.layout_chnage_head, null);
            Window window = avatarDialog.getWindow();
            window.setGravity(Gravity.BOTTOM); // 可设置dialog的位置
            window.setWindowAnimations(R.style.AnimBottom);
            window.getDecorView().setPadding(0, 0, 0, 0); // 消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 设置宽度充满屏幕
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            avatarDialog.setContentView(avatarView);
        }
        avatarDialog.show();
        avatarView.findViewById(R.id.from_cancel).setOnClickListener(
                v -> avatarDialog.dismiss());
        avatarView.findViewById(R.id.from_camera).setOnClickListener(
                v -> {
                    avatarDialog.dismiss();
                    cameraImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/learnsong/" + System.currentTimeMillis() + ".jpg");
                    cameraImage.getParentFile().mkdirs();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        imageUri = FileProvider.getUriForFile(UserInfoActivity.this,
                                "com.lb.learnsong.fileprovider", cameraImage);
                    } else {
                        imageUri = Uri.fromFile(cameraImage);
                    }
                    //启动相机程序
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, PHOTOHRAPH);
                });
        avatarView.findViewById(R.id.from_album).setOnClickListener(
                v -> {
                    avatarDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, PHOTOZOOM);
                });
    }

}
