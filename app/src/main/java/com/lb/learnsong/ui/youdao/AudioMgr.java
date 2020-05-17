package com.lb.learnsong.ui.youdao;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.youdao.sdk.common.YouDaoLog;
import com.youdao.sdk.common.util.AsyncTasks;

import java.io.IOException;

public class AudioMgr {
    public static final String PLAY_LOG = "TranslatePlay ：";

    public interface SuccessListener {
        public void success();

        public void playover();
    }

    public static void startPlayVoice(String url, SuccessListener listener) {
//        try {
//            AsyncTasks.safeExecuteOnExecutor(new PlayTask(url, listener));
//        } catch (Exception e) {
//            YouDaoLog.e(AudioMgr.PLAY_LOG + "AudioMgr startPlayVoice", e);
//            Log.d("AudioMgr", "fail to fetch data: ", e);
//        }
        MediaPlayer mediaPlayer = PlayMgr.getInstance()
                .getMediaPlayer();
        mediaPlayer.reset();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.start();

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                if (listener != null) {
                    listener.playover();
                }

            }
        });
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();


    }

    public static void stop() {
        if (PlayMgr.getInstance()
                .getMediaPlayer() != null) {
            PlayMgr.getInstance()
                    .getMediaPlayer().stop();

        }
    }


}
