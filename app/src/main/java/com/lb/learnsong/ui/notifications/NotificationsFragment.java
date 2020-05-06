package com.lb.learnsong.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.UserInfo;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.BaseFragment;
import com.lb.learnsong.ui.BaseListFragment;
import com.lb.learnsong.ui.activity.ui.login.LoginActivity;
import com.lb.learnsong.uiUtil.ImageLoad;
import com.lb.learnsong.uiUtil.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends BaseFragment {

    @BindView(R.id.user_headimg)
    ImageView userHeadimg;
    @BindView(R.id.user_nickname)
    TextView userNickname;
    @BindView(R.id.user_mail)
    TextView userMail;
    @BindView(R.id.group)
    Group group;
    @BindView(R.id.user_change)
    TextView userChange;
    @BindView(R.id.user_notify)
    LinearLayout userNotify;
    @BindView(R.id.user_release)
    LinearLayout userRelease;
    @BindView(R.id.user_problem)
    LinearLayout userProblem;
    @BindView(R.id.user_points)
    LinearLayout userPoints;
    @BindView(R.id.user_chat)
    LinearLayout userChat;

    private NotificationsViewModel notificationsViewModel;
    private BaseBeantoobj<UserInfo> userInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<BaseBeantoobj<UserInfo>>() {
            @Override
            public void onChanged(BaseBeantoobj<UserInfo> userInfoBaseBeantoobj) {
                userInfo = userInfoBaseBeantoobj;
                userNickname.setText(userInfoBaseBeantoobj.obj.getNickname());
                userMail.setText(userInfoBaseBeantoobj.obj.getUserDesc());
                ImageLoad.LoadCircleImage(getContext(), userInfoBaseBeantoobj.obj.getHeadimg(), userHeadimg);
            }
        });
    }

    @OnClick({R.id.user_notify, R.id.user_release, R.id.user_problem, R.id.user_points, R.id.user_chat, R.id.user_change})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.user_change:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 99);
                } else {
                    Intent intent = new Intent(getActivity(), UserInfoActivity.class);
//                    intent.putExtra("username",userInfo.obj.getUserName());
//                    intent.putExtra("userdesc",userInfo.obj.getUserDesc());
//                    intent.putExtra()
                    startActivityForResult(intent, 99);

                }
                break;
            case R.id.user_notify:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_release:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_problem:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_points:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_chat:
                if (!isSign()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isfirstload) {
            isfirstload = false;
            notificationsViewModel.loadinfo(getToken());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 99:
                if (resultCode == Activity.RESULT_OK) {
                    notificationsViewModel.loadinfo(getToken());
                }
                break;
            default:
                break;
        }

    }
}
