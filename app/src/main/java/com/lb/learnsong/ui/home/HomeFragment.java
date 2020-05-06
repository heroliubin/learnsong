package com.lb.learnsong.ui.home;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.Lyrics;
import com.lb.learnsong.ui.BaseListFragment;
import com.lb.learnsong.ui.activity.AddSongActivity;
import com.lb.learnsong.ui.viewmodel.BaseViewModel;
import com.lb.learnsong.ui.activity.LyricinfoActivity;
import com.lb.learnsong.ui.lsview.RefreshRecyclerView;
import com.lb.learnsong.ui.viewmodel.HomeViewModel;

import java.util.List;


public class HomeFragment extends BaseListFragment {
    private int p = 1;
    private HomeViewModel homeViewModel;
    private RefreshRecyclerView recyclerView;
    private HomeAdapter adapter;
    private ImageView addima;

    @Override
    protected void initView(View root) {
        recyclerView = root.findViewById(R.id.home_recyclerview);
        addima = root.findViewById(R.id.home_adaimg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        addima.setOnClickListener(v -> showPopMenu(v));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void lazyload() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.loadData(p, 15, BaseViewModel.TYPE_FRESH);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<Lyrics>>() {
                    @Override
                    public void onChanged(List<Lyrics> data) {
                        adapter = new HomeAdapter(getContext(), data);
                        recyclerView.setAdapter(adapter);
                        recyclerView.refreshFinish();
                        adapter.setItemclicklisenter(p -> {
                            //跳转详情页
//                            homeViewModel.loadLyricsinfo(getToken(),data.get(p).getId()+"");
                            Intent intent = new Intent(getActivity(), LyricinfoActivity.class);
                            intent.putExtra("id", data.get(p).getId() + "");
                            startActivity(intent);
                        });
                    }
                }
        );
        recyclerView.setOnPullListener(new RefreshRecyclerView.OnPullListener() {
            @Override
            public void onRefresh() {
                p = 1;
                homeViewModel.loadData(p, 15, BaseViewModel.TYPE_FRESH);

            }

            @Override
            public void onLoadMore() {
                p++;
                homeViewModel.loadData(p, 10, BaseViewModel.TYPE_MORE);
            }
        });

    }

    public void showPopMenu(View view) {
        PopupMenu menu = new PopupMenu(getContext(), view);
        menu.getMenuInflater().inflate(R.menu.menu_homeadd, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_singer:
                        Intent intent=new Intent(getActivity(), AddSongActivity.class);
                        intent.putExtra("type","singer");
                        startActivity(intent);
                        break;

                    case R.id.add_lyric:
                        Intent intent1=new Intent(getActivity(), AddSongActivity.class);
                        intent1.putExtra("type","lyric");
                        startActivity(intent1);
                        break;
                    case R.id.add_album:
                        Intent intent2=new Intent(getActivity(), AddSongActivity.class);
                        intent2.putExtra("type","album");
                        startActivity(intent2);
                        break;

                }
                return true;
            }
        });
        menu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });
        menu.show();
    }


}
