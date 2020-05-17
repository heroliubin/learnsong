package com.lb.learnsong.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lb.learnsong.bean.BaseBean;
import com.lb.learnsong.bean.BaseBeantoobj;
import com.lb.learnsong.bean.BaseListBean;
import com.lb.learnsong.bean.BaseobjBean;
import com.lb.learnsong.bean.CommentInfo;
import com.lb.learnsong.bean.LyricsInfo;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.http.HttpHelper;
import com.lb.learnsong.ui.youdao.TranslateData;
import com.lb.learnsong.uiUtil.LogUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.common.Constants;
import com.youdao.sdk.common.YouDaoLog;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 歌曲详情,收藏,评论
 */
public class LyricsViewModel extends BaseViewModel {
    private MutableLiveData<BaseBeantoobj<LyricsInfo>> lycicsinfo;
    private MutableLiveData<BaseBeantoobj<WordInfo>> wordinfo;
    private MutableLiveData<BaseBean> collectresult;
    private MutableLiveData<BaseBean> praisresult;

    public MutableLiveData<BaseBean> getCollectresult() {
        return collectresult;
    }

    public MutableLiveData<BaseBean> getPraisresult() {
        return praisresult;
    }

    public LyricsViewModel() {
        lycicsinfo = new MutableLiveData<>();
        wordinfo = new MutableLiveData<>();
        collectresult = new MutableLiveData<>();
        praisresult = new MutableLiveData<>();

    }

    public MutableLiveData<BaseBeantoobj<LyricsInfo>> getLycicsinfo() {
        return lycicsinfo;
    }

    public MutableLiveData<BaseBeantoobj<WordInfo>> getWordinfo() {
        return wordinfo;
    }

    public void loadLyricsinfo(String token, String id) {
        HttpHelper.getInstance().getLyricsById(token, id, new Callback<BaseBeantoobj<LyricsInfo>>() {
            @Override
            public void onResponse(Call<BaseBeantoobj<LyricsInfo>> call, Response<BaseBeantoobj<LyricsInfo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().status == 0) {
                        lycicsinfo.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseBeantoobj<LyricsInfo>> call, Throwable t) {

            }
        });
    }

    public void loadwordinfo(String token, String word) {
        HttpHelper.getInstance().getWordInfo(token, word, new Callback<BaseBeantoobj<WordInfo
                >>() {
            @Override
            public void onResponse(Call<BaseBeantoobj<WordInfo>> call, Response<BaseBeantoobj<WordInfo>> response) {
                if (response.isSuccessful()) {

                    if (response.body().status == 0) {

                        if (response.body().msg.equals("操作成功")) {
                            wordinfo.setValue(response.body());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<BaseBeantoobj<WordInfo>> call, Throwable t) {

                addword(token, word);
            }
        });
    }

    public void Praise(String token, String type, String typeid, String status) {
        HttpHelper.getInstance().MusicPraise(token, type, typeid, status, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()) {
                    praisresult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }

    public void Collect(String token, String type, String typeid, String status) {
        HttpHelper.getInstance().MusicPraise(token, type, typeid, status, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()) {
                    collectresult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }

    private void addword(String token, String word) {
        Language from = LanguageUtils.getLangByName("英文");
        Language to = LanguageUtils.getLangByName("中文");
        TranslateParameters tps = new TranslateParameters.Builder()
                .source("youdao").from(from).to(to).sound(Constants.SOUND_OUTPUT_MP3).
                        voice(Constants.VOICE_GIRL_US).timeout(2000).build();
        Translator.getInstance(tps).lookup(word, word, new TranslateListener() {
            @Override
            public void onError(TranslateErrorCode translateErrorCode, String s) {
                LogUtils.LOGM(translateErrorCode.toString());
            }

            @Override
            public void onResult(Translate translate, String s, String s1) {
                TranslateData td = new TranslateData(
                        System.currentTimeMillis(), translate);

//                LogUtils.LOGM("翻译结果：\n" + td.translates());
//                LogUtils.LOGM("发音：" + td.phonetic());
//                LogUtils.LOGM("网络释义：\n" + td.webMeans());
//                LogUtils.LOGM("音频：\n" + translate.getSpeakUrl());


                WordInfo words = new WordInfo(word,td.phonetic(),"翻译结果:"+td.webMeans(),
                        translate.getSpeakUrl(),"");
                BaseBeantoobj<WordInfo> wordInfoBaseBeantoobj = new BaseBeantoobj<>(words);
                wordinfo.postValue(wordInfoBaseBeantoobj);
                HttpHelper.getInstance().addtWord(token, word, td.phonetic(), td.webMeans(),
                        translate.getSpeakUrl(), "", new Callback<BaseBean>() {
                            @Override
                            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

                            }

                            @Override
                            public void onFailure(Call<BaseBean> call, Throwable t) {

                            }
                        });

            }

            @Override
            public void onResult(List<Translate> list, List<String> list1, List<TranslateErrorCode> list2, String s) {
                LogUtils.LOGM("onresultlist");
            }
        });

    }

}
