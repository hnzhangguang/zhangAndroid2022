package com.zhang.zhangandroid.basecomponent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhang.zhangandroid.R;

public class NotificationActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

        findViewById(R.id.showNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }


    public void showNotification() {

        String id = "channelId";
        String name = "channelName";
        Notification notification;

        //将Intent对象传入PendingIntent对象的getActivity方法中
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Intent intent = new Intent(this, NotificationActivity2.class);

            //用intent表现出我们要启动Notification的意图
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);

            notification = new NotificationCompat.Builder(this)
                    .setChannelId(id)  // 这句很关键!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!, 没有它不显示通知
                    .setContentTitle("This is content title")
                    //设置通知栏中的标题
                    .setContentText("hello world!")
                    //设置通知栏中的内容
                    .setWhen(System.currentTimeMillis())
                    //设置通知出现的时间，此时为事件响应后立马出现通知
                    .setSmallIcon(R.mipmap.ic_launcher)
                    //设置通知出现在手机顶部的小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    //设置通知栏中的大图标
                    .setContentIntent(pi)
                    //将PendingIntent对象传入该方法中，表明点击通知后进入到NotificationActivity.class页面
                    .setAutoCancel(true)
                    //点击通知后，通知自动消失
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    //默认选项，根据手机当前的环境来决定通知发出时播放的铃声，震动，以及闪光灯
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    //设置通知的权重
                    .build();

            //用于显示通知，第一个参数为id，每个通知的id都必须不同。第二个参数为具体的通知对象
        }else {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true);
            notification = notificationBuilder.build();
        }
        manager.notify(1, notification);

    }


}