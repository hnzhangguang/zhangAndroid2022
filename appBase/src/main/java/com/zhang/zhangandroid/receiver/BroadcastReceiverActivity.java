package com.zhang.zhangandroid.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhang.zhangandroid.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        Button receiver_btn = findViewById(R.id.receiver_btn);
        receiver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                //设置intent的动作;后面字符串是自定义的
                intent.setAction("android.intent.action.receiverdata");
                intent.putExtra("msg","羊村各位村民开会了");

                //Google说明的，android 8.0（API26）开始，对清单文件中静态注册广播接收者增加了限制，建议不要在清单文件中静态注册广播接收者。否则会接收不到。如果一定要静态注册，需要在发送广播的时候添加以下代码：
                intent.setComponent(new ComponentName(getApplication().getPackageName(), "com.zhang.zhangandroid.receiver.MyReceiver"));//应用间广播
//               或者 intent.setClassName("包名", "包名.类名");//应用内广播


                BroadcastReceiverActivity.this.sendBroadcast(intent);






            }
        });
    }
}