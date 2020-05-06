package com.lb.learnsong;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Trace;

import androidx.annotation.NonNull;

import com.lb.learnsong.ui.ExceptionHandler;
import com.youdao.sdk.app.YouDaoApplication;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class LsApplication extends Application {
    private static LsApplication mAppction;

    @Override
    public void onCreate() {
        super.onCreate();
        if(YouDaoApplication.getApplicationContext() == null)
            YouDaoApplication.init(this, "7651baa973737cf1");
        mAppction = this;
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//
//
//        });
//        initCrashHandler();

    }

    public static LsApplication getInstance() {
        return mAppction;
    }

    private void initCrashHandler() {
        //ExceptionHander的使用
        ExceptionHandler.install((thread, throwable) -> {
            if (throwable != null) {
//                    ToastUtils.showToast("程序异常信息：" + throwable.getMessage().toString() + "");
//                    Trace.e("ErrorMsg", "错误日志-----" + throwable.getMessage() + "");
                String filePath = "/sdcard/SupermarketError/";
                String fileName = "errorMsg.txt";
                Calendar calendar = Calendar.getInstance();
                //获取系统的日期
                //年
                int year = calendar.get(Calendar.YEAR);
                //月
                int month = calendar.get(Calendar.MONTH) + 1;
                //日
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //获取系统时间
                //小时
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                //分钟
                int minute = calendar.get(Calendar.MINUTE);
                //秒
                int second = calendar.get(Calendar.SECOND);
                String errorMsg = "触发时间：" +
                        year + "年" + month + "月" + day + "日"
                        + hour + ":" + minute + ":"
                        + second + "\r\n" + "包名："
                        + "\r\n" + "版本号："
                         + "\r\n"
                        + "错误日志："
                        + throwable.getMessage().toString();
                //将错误日志打印到本地
                writeTxtToFile(errorMsg, filePath, fileName);
            }

        });
    }
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);
        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
//                Trace.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
//            Trace.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
//            Trace.i("error:", e + "");
        }
    }
}
