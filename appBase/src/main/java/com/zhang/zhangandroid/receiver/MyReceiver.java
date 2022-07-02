package com.zhang.zhangandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //接受广播
        if (intent == null) return;
        //intent:接受从主端传递过来的数据，action数据；
        String action = intent.getAction();
        //针对上述做判断;第一个判断是否为空也可以写成action.isEmpty
        if (!TextUtils.isEmpty(action) && "android.intent.action.receiverdata".equals(action)) {
            String msg = intent.getStringExtra("msg");//不习惯可以使用Bundle
            Log.e("mmmm","喜洋洋-->  "+ msg);
        }
    }
}