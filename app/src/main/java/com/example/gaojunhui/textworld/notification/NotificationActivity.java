package com.example.gaojunhui.textworld.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojunhui on 2017/5/12.
 */

public class NotificationActivity extends BaseActivity {
    private Button btnSend;
    private NotificationManager notificationManager;
    private Notification notification;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        btnSend= (Button) findViewById(R.id.btn_send);
    }

    @Override
    protected void initData() {
        super.initData();
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RxView.clicks(btnSend)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                   send();
                });
    }
    private void send(){
        /*通过PendingIntent进行跳转*/
        Intent intent=new Intent(this,IntentActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        notification= new NotificationCompat.Builder(this)
                .setContentTitle("content title")
                .setContentText("content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)//设置intent
                //.setSound(Uri.fromFile(new File("音频路径")))//添加一段音乐
                //.setVibrate(new long[]{0,1000,1000,1000})//添加震动，（0：手机静止时间，1000：静止时长，1000：静止时长...一次类推）
                //                                        如：立刻震动1秒，静止1秒，再震动1秒（添加VIBRATE权限）
                //.setLights(Color.GREEN,1000,1000)//添加LED灯的闪烁（颜色，亮起的时长，暗去的时长）
                .setDefaults(NotificationCompat.DEFAULT_ALL)//使用手机默认的通知效果（会根据当前手机的环境播放什么铃声和如何震动）
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1,notification);


    }
}
