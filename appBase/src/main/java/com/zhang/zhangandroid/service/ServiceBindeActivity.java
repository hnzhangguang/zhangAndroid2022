package com.zhang.zhangandroid.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.util.AppUtil;


/**
 * 简介:
 * 功能: 1,   BinderService
 *       2,   IntentService
 *       3,   AIDL Service
 *
 *
 */
public class ServiceBindeActivity extends AppCompatActivity {


    private Button btnbind;
    private Button btncancel;
    private Button btnstatus;


    private EditText edit_num;
    private Button btn_query;
    private TextView txt_name;
    private IPerson iPerson;
    private PersonConnection conn2 = new PersonConnection();




    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    BindeService.MyBinder binder;
    private ServiceConnection conn1 = new ServiceConnection() {

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
                bindService(intent, conn1, Service.BIND_AUTO_CREATE);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解除service绑定
                unbindService(conn1);
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


        // AIDL service
        aidlService();



    }



    private final class PersonConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPerson = IPerson.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName name) {
            iPerson = null;
        }
    }



    // 测试时候, AIDL服务应该和当前测试程序在不同的进程里面才行!!!
    private void  aidlService(){

        edit_num = (EditText) findViewById(R.id.edit_num);
        btn_query = (Button) findViewById(R.id.btn_query);
        txt_name = (TextView) findViewById(R.id.txt_name);

        //绑定远程Service
        Intent service = new Intent("android.intent.action.AIDLService");
        service.setPackage(getPackageName());

        bindService(service, conn2, BIND_AUTO_CREATE);


        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = edit_num.getText().toString();
                int num = Integer.valueOf(number);
                try {
                    txt_name.setText(iPerson.queryPerson(num));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                edit_num.setText("");
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