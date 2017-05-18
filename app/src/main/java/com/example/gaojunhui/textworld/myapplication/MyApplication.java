package com.example.gaojunhui.textworld.myapplication;

import android.app.Application;

import com.example.gaojunhui.textworld.textcrash.CustomActivityOnCrash;
import org.litepal.LitePal;

/**
 * Created by gaojunhui on 2017/5/2.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
        LitePal.initialize(this);
    }
}
