package com.zhang.zhangandroid.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;

import java.io.PushbackReader;

public class BroadcastReceiverActivity extends BaseActivity {


    public static final  String MSG_ACTION = "android.intent.action.receiverdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        Button receiver_btn = findViewById(R.id.receiver_btn);// 静态注册广播

        receiver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                staticRegisterReceiver();
            }
        });


        Button receiver_btn2 = findViewById(R.id.receiver_btn2);//动态注册广播
        receiver_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicRegisterReceiver();
            }
        });


    }


    /**
     * 简介: 静态注册广播
     */
    private void staticRegisterReceiver(){
        Intent intent=new Intent();
        //设置intent的动作;后面字符串是自定义的
        intent.setAction(MSG_ACTION);
        intent.putExtra("msg","羊村各位村民开会了");

        //Google说明的，android 8.0（API26）开始，对清单文件中静态注册广播接收者增加了限制，建议不要在清单文件中静态注册广播接收者。否则会接收不到。如果一定要静态注册，需要在发送广播的时候添加以下代码：
        intent.setComponent(new ComponentName(getApplication().getPackageName(), "com.zhang.zhangandroid.receiver.MyReceiver"));//应用间广播
//               或者 intent.setClassName("包名", "包名.类名");//应用内广播


        BroadcastReceiverActivity.this.sendBroadcast(intent);

    }


    /**
     * 简介: 动态注册广播
     */
    private void dynamicRegisterReceiver(){

        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        Reveiver Broadcast = new Reveiver();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction(MSG_ACTION);// 只有持有相同的action的接受者才能接收此广播
        // 3. 动态注册：调用Context的registerReceiver（）方法
        registerReceiver(Broadcast, intentFilter);


        // 发送动态广播
        Intent intent = new Intent(MSG_ACTION);
        intent.putExtra("msg"," 发送动态广播");
        sendBroadcast(intent);

    }

    // 定义动态广播
    class Reveiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            String action = intent.getAction();
            if (action.equals(MSG_ACTION)){
                logZhang(msg);
            }
        }
    }




}