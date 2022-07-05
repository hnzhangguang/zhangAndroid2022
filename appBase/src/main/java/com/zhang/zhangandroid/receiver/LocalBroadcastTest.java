package com.zhang.zhangandroid.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.zhang.zhangandroid.base.BaseActivity;

//本地广播
public class LocalBroadcastTest {

    public void testLocalBroadcast(BaseActivity activity){

        // 1.获取本地广播的manager
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(activity);

        // 2.初始化广播接收者，设置过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jay.mybcreceiver.LOGIN_OTHER");

        // 3.广播接收器
        MyBcReceiver localReceiver = new MyBcReceiver();
        // 4.注入本地广播
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        // 5.发送本地广播
        Intent intent = new Intent("com.jay.mybcreceiver.LOGIN_OTHER");
        localBroadcastManager.sendBroadcast(intent);

        // 6.注销广播
        localBroadcastManager.unregisterReceiver(localReceiver);

    }
}


class MyBcReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("警告：");
        dialogBuilder.setMessage("您的账号在别处登录，请重新登陆~");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, BroadcastReceiverActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setType(
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}

