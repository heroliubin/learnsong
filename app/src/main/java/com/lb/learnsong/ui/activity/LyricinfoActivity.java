package com.lb.learnsong.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lb.learnsong.R;
import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.LyricsInfo;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.ui.BaseLsActivity;
import com.lb.learnsong.ui.adapter.CommentAdapter;
import com.lb.learnsong.ui.lsview.BackView;
import com.lb.learnsong.ui.selectetextview.OnWordClickListener;
import com.lb.learnsong.ui.selectetextview.SelectableTextView;
import com.lb.learnsong.ui.viewmodel.CommentViewModel;
import com.lb.learnsong.ui.viewmodel.LyricsViewModel;
import com.lb.learnsong.uiUtil.NullUtils;
import com.lb.learnsong.uiUtil.ScreenUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class LyricinfoActivity extends BaseLsActivity {


    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.lyrics_text)
    SelectableTextView lyricsText;
    @BindView(R.id.backimg)
    ImageView backimg;
    @BindView(R.id.coverimg)
    ImageView coverimg;
    @BindView(R.id.sname_text)
    TextView snameText;
    @BindView(R.id.lname_text)
    TextView lnameText;
    @BindView(R.id.lyrics_collect)
    ImageView lyricsCollect;
    @BindView(R.id.lyrics_collectcount)
    TextView lyricsCollectcount;
    @BindView(R.id.comment_recyclerview)
    RecyclerView commentRecyclerview;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.voicesendimg)
    ImageView voicesendimg;
    @BindView(R.id.sendmsg)
    TextView sendmsg;
    @BindView(R.id.msgcontent_ed)
    EditText msgcontentEd;
    @BindView(R.id.back)
    BackView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.divide)
    View divide;
    @BindView(R.id.topback)
    ImageView topback;
    @BindView(R.id.nestedscrollview)
    NestedScrollView nestedscrollview;
    private LyricsViewModel viewModel;
    private CommentViewModel commentViewModel;
    private LyricsInfo lyricsInfo;
    private ConstraintLayout sendlayout;
    private String commenttype = "0";
    private CommentAdapter adapter;
    private AlertDialog dialog = null;
    private View view1;
    private ImageView voice, collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyricinfo);
        ButterKnife.bind(this);
        init();
        commentViewModel.getCommentlist().observe(this, commentInfoBaseListBean -> {
            adapter = new CommentAdapter(commentInfoBaseListBean.getList(), this);
            commentRecyclerview.setAdapter(adapter);
            adapter.setZanListener((type, typeid, status) -> viewModel.Praise(getToken(), type, typeid, status));
        });
        viewModel.getLycicsinfo().observe(this, lyricsInfoBaseBeantoobj -> {
            lyricsInfo = lyricsInfoBaseBeantoobj.obj;
            lyricsText.setText(lyricsInfoBaseBeantoobj.obj.getSynopsis());
            getEachWord(lyricsText);
            lyricsText.setMovementMethod(LinkMovementMethod.getInstance());
            lnameText.setText(lyricsInfoBaseBeantoobj.obj.getLname());
            snameText.setText(lyricsInfoBaseBeantoobj.obj.getSname());
            title.setText(lyricsInfoBaseBeantoobj.obj.getLname());
            lyricsCollect.setSelected(lyricsInfoBaseBeantoobj.obj.getIsCollcet() == 1);
            lyricsCollectcount.setText(lyricsInfoBaseBeantoobj.obj.getCollectCount() + "");
            List<TextView> listtext = new ArrayList<>();
            listtext.clear();
            listtext.add(lnameText);
            listtext.add(snameText);
            listtext.add(title);

            Glide.with(this).asBitmap().load(lyricsInfoBaseBeantoobj.obj.getCover()).into(
                    new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            if (resource == null) {
                                return;
                            }
                            coverimg.setImageBitmap(resource);
//                            ImageLoad.setPaletteColor(resource, listtext);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
            Glide.with(this).load(lyricsInfoBaseBeantoobj.obj.getCover()).apply(RequestOptions.bitmapTransform(
                    new BlurTransformation(25, 2))).into(backimg);
            Glide.with(this).asBitmap().load(lyricsInfoBaseBeantoobj.obj.getCover()).apply(RequestOptions.bitmapTransform(
                    new BlurTransformation(25, 2))).into(new CustomTarget<Bitmap>() {


                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    if (resource == null) {
                        return;
                    }
                    topback.setImageBitmap(resource);
                    Palette.from(resource).maximumColorCount(10).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(@NonNull Palette palette) {

                            Palette.Swatch s1 = palette.getVibrantSwatch();       //获取到充满活力的这种色调
                            if (s1 != null) {
                                lnameText.setTextColor(s1.getBodyTextColor());
                                snameText.setTextColor(s1.getBodyTextColor());
                                title.setTextColor(s1.getBodyTextColor());
                                lnameText.setBackgroundColor(s1.getRgb());
                                snameText.setBackgroundColor(s1.getRgb());
                                getWindow().setStatusBarColor(s1.getRgb());
                                back.setColor(s1.getBodyTextColor());
                            }


                        }
                    });

                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });

        });

        viewModel.getWordinfo().observe(this, wordInfoBaseBeantoobj -> {
            wordshow(wordInfoBaseBeantoobj);
        });
        viewModel.getCollectresult().observe(this, baseBean -> {
            if (dialog != null && dialog.isShowing()) {
                boolean sta = collect.isSelected();
                collect.setSelected(!sta);
                return;
            }
            if (baseBean.status == 0) {
                boolean status = lyricsCollect.isSelected();
                lyricsCollect.setSelected(!status);
            } else {
                makeToast(baseBean.msg + "");
            }
        });
        viewModel.getPraisresult().observe(this, baseBean -> makeToast(baseBean.msg));
    }

    @OnClick({R.id.voicesendimg, R.id.sendmsg, R.id.msgcontent_ed, R.id.lyrics_collect, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.voicesendimg:
                break;
            case R.id.sendmsg:
                if (sendmsg.isSelected()) {
                    commentViewModel.addComment(getToken(), lyricsInfo.getId() + "", commenttype, msgcontentEd.getText().toString(),
                            "");
                }
                break;
            case R.id.msgcontent_ed:
                break;
            case R.id.lyrics_collect:
                if (lyricsInfo == null) {
                    makeToast("lyricsInfo == null");
                    return;
                }
                if (lyricsCollect.isSelected()) {
                    viewModel.Collect(getToken(), lyricsInfo.getType() + "", lyricsInfo.getId() + "", "1");
                } else {
                    viewModel.Collect(getToken(), lyricsInfo.getType() + "", lyricsInfo.getId() + "", "2");
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }


    private void init() {
        topback.setVisibility(View.GONE);
        title.setMaxWidth(ScreenUtil.getScreenWidth(this) / 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commentRecyclerview.setLayoutManager(layoutManager);
        sendlayout = findViewById(R.id.sendlayout);
        nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY>ScreenUtil.dip2px(LyricinfoActivity.this,232)){
                    float a=scrollY-ScreenUtil.dip2px(LyricinfoActivity.this,232);
                    float b=ScreenUtil.dip2px(LyricinfoActivity.this,48);
                    topback.setAlpha(a/b);
                    topback.setVisibility(View.VISIBLE);
                }else {
                    topback.setVisibility(View.GONE);
                }
            }
        });
        viewModel = new ViewModelProvider(this).get(LyricsViewModel.class);
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        viewModel.loadLyricsinfo(getToken(), getIntent().getStringExtra("id"));
        commentViewModel.Commentlist(getToken(), getIntent().getStringExtra("id"), "1", "20");
        floatingActionButton.setOnClickListener(v -> {
            if (sendlayout.isShown()) {
                floatingActionButton.animate().translationY(0).setDuration(500).start();
                sendlayout.setAlpha(1);
                sendlayout.animate().setDuration(500).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        sendlayout.setVisibility(View.GONE);
                    }
                }).start();
            } else {
                floatingActionButton.animate().translationY(-150).setDuration(500).start();
                sendlayout.setVisibility(View.VISIBLE);
                sendlayout.setAlpha(0);
                sendlayout.animate().setDuration(500).alpha(1).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        sendlayout.setVisibility(View.VISIBLE);
                    }
                }).start();
            }

        });
        msgcontentEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (NullUtils.isNotEmpty(s.toString())) {
                    sendmsg.setSelected(true);
                } else {
                    sendmsg.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        commentViewModel.getCommentresult().observe(this, new Observer<BaseBean>() {
            @Override
            public void onChanged(BaseBean baseBean) {
                if (baseBean.status == 0) {
                    commentViewModel.Commentlist(getToken(), getIntent().getStringExtra("id"), "1",
                            "20");
                    msgcontentEd.setText("");
                }
                makeToast(baseBean.msg);

            }
        });
    }


    public void getEachWord(SelectableTextView selectableTextView) {

        selectableTextView.setOnWordClickListener(new OnWordClickListener() {
            @Override
            protected void onNoDoubleClick(String word) {
//                Toast.makeText(LyricinfoActivity.this, word, Toast.LENGTH_SHORT).show();
                viewModel.loadwordinfo(getToken(), word);
            }
        });
    }

    private void wordshow(BaseBeantoobj<WordInfo> wordInfoBaseBeantoobj) {
        if (dialog == null) {
            view1 = View.inflate(this, R.layout.worddialog, null);
            dialog = new AlertDialog.Builder(this).setView(view1).create();
        }
        TextView target = view1.findViewById(R.id.targetword);
        TextView phonetic = view1.findViewById(R.id.phonetic);
        TextView chineseword = view1.findViewById(R.id.chineseword);
        voice = view1.findViewById(R.id.voice);
        collect = view1.findViewById(R.id.collect);
        target.setText(wordInfoBaseBeantoobj.obj.getTargetword());
        phonetic.setText(wordInfoBaseBeantoobj.obj.getPhonetic());
        chineseword.setText(wordInfoBaseBeantoobj.obj.getChineseword());
        if (wordInfoBaseBeantoobj.obj.getIsCollect() == 1) {
            collect.setSelected(true);
        }else {
            collect.setSelected(false);

        }
        voice.setOnClickListener(v -> {
            if (NullUtils.isNotEmpty(wordInfoBaseBeantoobj.obj.getVoicefileurl())) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(wordInfoBaseBeantoobj.obj.getVoicefileurl());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status;
                if (v.isSelected()) {
                    status = "2";
                } else {
                    status = "1";
                }
                viewModel.Collect(getToken(), "4", wordInfoBaseBeantoobj.obj.getId() + "", status);
            }
        });
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) ScreenUtil.getScreenheight(LyricinfoActivity.this) * 4 / 5; // 高度
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lp.height);
        view1.findViewById(R.id.wordll).setLayoutParams(params);
        dialogWindow.setAttributes(lp);
    }


}
