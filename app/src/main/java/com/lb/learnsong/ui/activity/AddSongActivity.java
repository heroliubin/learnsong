package com.lb.learnsong.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.lb.learnsong.R;
import com.lb.learnsong.ui.BaseLsActivity;
import com.lb.learnsong.ui.viewmodel.AddSongViewModel;
import com.lb.learnsong.ui.viewmodel.UtilViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSongActivity extends BaseLsActivity {
    @BindView(R.id.coverimg)
    ImageView coverimg;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.synopsis)
    EditText synopsis;
    @BindView(R.id.addfinish)
    TextView addfinish;
    private UtilViewModel utilViewModel;
    private AddSongViewModel addSongViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        ButterKnife.bind(this);
        setCustomActionBar();
        init();
        addSongViewModel = new ViewModelProvider(this).get(AddSongViewModel.class);
        utilViewModel = new ViewModelProvider(this).get(UtilViewModel.class);
    }

    private void init() {
        if (getIntent().getStringExtra("type").equals("singer")) {
            setTitle("添加歌手");
        } else if (getIntent().getStringExtra("type").equals("lyric")) {
            setTitle("添加歌曲");

        } else if (getIntent().getStringExtra("type").equals("album")) {
            setTitle("添加专辑");
        }
    }

    @OnClick({R.id.coverimg, R.id.addfinish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coverimg:
                break;
            case R.id.addfinish:
                break;
        }
    }
}
