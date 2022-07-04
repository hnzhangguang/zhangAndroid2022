package com.zhang.zhangandroid.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.util.AppUtil;

public class ServiceBindeActivity extends AppCompatActivity {


    private Button btnbind;
    private Button btncancel;
    private Button btnstatus;


    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    BindeService.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            AppUtil.logZhang("------Service DisConnected-------");
        }

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppUtil.logZhang("------Service Connected-------");
            binder = (BindeService.MyBinder) service;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_binde);


        btnbind = (Button) findViewById(R.id.btnbind);
        btncancel = (Button) findViewById(R.id.btncancel);
        btnstatus  = (Button) findViewById(R.id.btnstatus);

        // 5.0 以后写法必须是这样的
        final Intent intent = new Intent();
        intent.setAction("com.zhang.zhangandroid.service.aaa");//action 过滤器
        intent.setPackage(getPackageName()); // 必须加!!


        btnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定service
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解除service绑定
                unbindService(conn);
            }
        });

        btnstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtil.logZhang("Service的count的值为:"
                        + binder.getCount());

                Toast.makeText(getApplicationContext(), "Service的count的值为:"
                        + binder.getCount(), Toast.LENGTH_SHORT).show();
            }
        });


        //测试 IntentService
        findViewById(R.id.test_intentservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testIntentService();
            }
        });




    }


    private void testIntentService(){
        Intent it1 = new Intent("com.test.intentservice");
        it1.setPackage(getPackageName());
        Bundle b1 = new Bundle();
        b1.putString("param", "s1");
        it1.putExtras(b1);

        Intent it2 = new Intent("com.test.intentservice");
        it2.setPackage(getPackageName());
        Bundle b2 = new Bundle();
        b2.putString("param", "s2");
        it2.putExtras(b2);

        Intent it3 = new Intent("com.test.intentservice");
        it3.setPackage(getPackageName());
        Bundle b3 = new Bundle();
        b3.putString("param", "s3");
        it3.putExtras(b3);

        //接着启动多次IntentService,每次启动,都会新建一个工作线程
        //但始终只有一个IntentService实例
        startService(it1);
        startService(it2);
        startService(it3);

    }
}