package com.example.gaojunhui.textworld.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by gaojunhui on 2017/5/15.
 */

public class MyService extends Service {
    private MyBinder myBinder=new MyBinder();
    class MyBinder extends Binder{
        public void startDownload(){
            Log.i("----", "startDownload: ");
        }
        public int getProgress(){
            Log.i("----", "getProgress: ");
            return 0;
        }
    }
    /*服务创建的时候调用*/
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("----", "onCreate: ");
    }

    /*服务启动的时候调用*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("----", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    /*服务销毁的时候调用*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("----", "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
