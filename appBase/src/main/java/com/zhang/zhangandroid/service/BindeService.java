package com.zhang.zhangandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.MessageQueue;
import android.util.Log;

import com.zhang.zhangandroid.util.AppUtil;

public class BindeService extends Service {


    private final String TAG = "mmmm";
    private int count;
    private boolean quit = false;


    public BindeService() {
    }


    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.logZhang("BindeService->onCreate()....");
        //创建一个线程动态地修改count的值
        new Thread() {
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }



    //必须实现的方法,绑定改Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        AppUtil.logZhang("BindeService->onBind()....");
        return binder;
    }


    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind方法被调用!");
        return true;
    }


    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind方法被调用!");
        super.onRebind(intent);
    }


    //Service被关闭前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        AppUtil.logZhang("BindeService->onDestroy()....");
    }
}