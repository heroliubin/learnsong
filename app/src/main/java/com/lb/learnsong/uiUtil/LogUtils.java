package com.lb.learnsong.uiUtil;

import android.util.Log;

import com.lb.learnsong.BuildConfig;


/**
 * 日志辅助类,用于调试打印输入日志({@code DEBUG, VERBOSE, INFO, WARN, ERROR}).<p>
 * <font color="#ff8800">日志级别设置:</font>
 * <ol>
 * <li>adb shell</li>
 * <li>setprop log.tag.TAG DEBUG</li>
 * </ol>
 * Created by Cuke Pie on 13-6-24.
 */
public class LogUtils {
  public static String gtag(String tag){
    if (NullUtils.isEmpty(tag)){
      tag="lb";
    }
    return tag;
  }


  public static void LOGD(final String tag, String message) {
    // 根据日志的级别判断是否打印日志
    if (BuildConfig.DEBUG) {
      Log.d(gtag(tag), message);
    }
  }
  public static void LOGM(String message) {
    // 根据日志的级别判断是否打印日志
    if (BuildConfig.DEBUG) {
      Log.e(gtag(""), message);
    }
  }

  public static void LOGD(final String tag, String message, Throwable cause) {
    if (Log.isLoggable(gtag(tag), Log.DEBUG)) {
      Log.d(gtag(tag), message, cause);
    }
  }

  public static void LOGV(final String tag, String message) {
    if (BuildConfig.DEBUG) {
      Log.v(gtag(tag), message);
    }

  }

  public static void LOGV(final String tag, String message, Throwable cause) {
    if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
      Log.v(gtag(tag), message, cause);
    }
  }

  public static void LOGI(String tag, String message) {
    Log.i(tag, message);
  }

  public static void LOGI(String tag, String message, Throwable cause) {
    Log.i(gtag(tag), message, cause);
  }

  public static void LOGW(String tag, String message) {
      if (BuildConfig.DEBUG) {

          Log.w(gtag(tag), message);
      }
  }

  public static void LOGW(String tag, String message, Throwable cause) {
    Log.w(gtag(tag), message, cause);
  }

  public static void LOGE(String tag, String message) {


      Log.e(gtag(tag), message);

  }

  public static void LOGE(String tag, String message, Throwable cause) {
    Log.e(gtag(tag), message, cause);
  }

  private LogUtils() {
  }

}
