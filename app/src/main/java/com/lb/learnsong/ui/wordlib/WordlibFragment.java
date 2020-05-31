package com.lb.learnsong.ui.wordlib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.lb.learnsong.R;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.ui.BaseFragment;
import com.lb.learnsong.uiUtil.LogUtils;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WordlibFragment extends BaseFragment {


    @BindView(R.id.wordlist_recyclerview)
    RecyclerView wordlistRecyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private WordlibViewModel wordlibViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wordlibViewModel = new ViewModelProvider(this).get(WordlibViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wordlistRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        wordlibViewModel.getListword().observe(getViewLifecycleOwner(), wordInfos -> {
            LogUtils.LOGM("wordinfos,size="+wordInfos.size());
            wordlistRecyclerview.setAdapter(new WordlibAdapter(wordInfos,getContext()));
            refreshlayout.setRefreshing(false);
        });
        wordlibViewModel.getdata(getToken(), 1 + "", 10 + "");
        refreshlayout.setOnRefreshListener(() -> wordlibViewModel.getdata(getToken(), 1 + "", 10 + ""));
    }

}
