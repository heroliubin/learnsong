package com.lb.learnsong.ui.home;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lb.baselib.model.FailModel;
import com.lb.learnsong.R;
import com.lb.learnsong.bean.Lyrics;
import com.lb.learnsong.ui.BaseListFragment;
import com.lb.learnsong.ui.activity.AddSongActivity;
import com.lb.learnsong.ui.activity.LyricinfoActivity;
import com.lb.learnsong.ui.viewmodel.HomeViewModel;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseListFragment {
    private int p = 1;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ImageView addima;
    private List<Lyrics> listdata;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void initView(View root) {
        recyclerView = root.findViewById(R.id.home_recyclerview);
        addima = root.findViewById(R.id.home_adaimg);
        refreshLayout = root.findViewById(R.id.smrtrefreshlayout);
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
        homeViewModel.loadData(p, 10);
        listdata = new ArrayList<>();
        adapter = new HomeAdapter(getContext(), listdata);
        recyclerView.setAdapter(adapter);
        adapter.setItemclicklisenter(p -> {
            Intent intent = new Intent(getActivity(), LyricinfoActivity.class);
            intent.putExtra("id", listdata.get(p).getId() + "");
            startActivity(intent);
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<Lyrics>>() {
                    @Override
                    public void onChanged(List<Lyrics> data) {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                        listdata.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                }
        );
        homeViewModel.getFailData().observe(getViewLifecycleOwner(), new Observer<FailModel>() {
            @Override
            public void onChanged(FailModel failModel) {
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
                if (p>1){
                    p--;
                }
                Toast.makeText(getActivity(),failModel.getMsg(),Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                p = 1;
                listdata.clear();
                homeViewModel.loadData(p, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                p++;
                homeViewModel.loadData(p,10);
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
                        Intent intent = new Intent(getActivity(), AddSongActivity.class);
                        intent.putExtra("type", "singer");
                        startActivity(intent);
                        break;

                    case R.id.add_lyric:
                        Intent intent1 = new Intent(getActivity(), AddSongActivity.class);
                        intent1.putExtra("type", "lyric");
                        startActivity(intent1);
                        break;
                    case R.id.add_album:
                        Intent intent2 = new Intent(getActivity(), AddSongActivity.class);
                        intent2.putExtra("type", "album");
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
