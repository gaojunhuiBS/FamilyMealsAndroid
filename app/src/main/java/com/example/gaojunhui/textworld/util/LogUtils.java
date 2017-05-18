package com.example.gaojunhui.textworld.util;

import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * Created by gaojunhui on 2017/5/4.
 */

public class LogUtils {
    public LogUtils() {
    }

    public static void logD(String TAG,Object content){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "logD: " + content);
        }
    }
}
