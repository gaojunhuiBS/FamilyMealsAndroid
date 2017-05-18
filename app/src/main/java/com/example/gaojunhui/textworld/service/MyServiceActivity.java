package com.example.gaojunhui.textworld.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Button;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojunhui on 2017/5/15.
 */

public class MyServiceActivity extends BaseActivity {
    private Button btnStart, btnStop, btnBind, btnUnbind;
    private Intent intent;
    private MyService.MyBinder myBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MyService.MyBinder) iBinder;
            myBinder.startDownload();
            myBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            showToast("unbind");
        }
    };

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_service;
    }
    @Override
    protected void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnBind = (Button) findViewById(R.id.btn_bind);
        btnUnbind = (Button) findViewById(R.id.btn_unBind);
        startService();
        stopService();
        bindService();
        unBindService();
    }

    private void startService() {
        RxView.clicks(btnStart)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    intent = new Intent(this, MyService.class);
                    startService(intent);
                });
    }

    private void stopService() {
        RxView.clicks(btnStop)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Intent stopIntent = new Intent(this, MyService.class);
                    unBindService();
                    stopService(stopIntent);
                });
    }

    /**
     * 若对一个服务同时调用了startService()和bindService(),
     * 要同时调用stopService()和unBindService()方法
     * 才能使服务销毁，服务的onDestroy()方法才能运行
     */
    private void bindService() {
        RxView.clicks(btnBind)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Intent bindIntent = new Intent(this, MyService.class);
                    bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);//绑定服务
                });
    }

    private void unBindService() {
        RxView.clicks(btnUnbind)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    unBindService();
                });
    }
}
