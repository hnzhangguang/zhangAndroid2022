package com.zhang.zhangandroid.service;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

/**
 * 简介: 2. 在通话监听服务中，监听通话状态，当通话状态为CALL_STATE_RINGING时处理通话。
 */
public class InCallService extends Service {
    private TelecomManager mTelecomManager;
    private TelephonyManager mTelephonymanager;

    // 监听器
    MyPhoneStateListener mMyPhoneStateListener = new MyPhoneStateListener();

    @Override
    public void onCreate() {
        Log.e("InCallService", "onCreate()");
        super.onCreate();

        //获取电话服务 6.0后添加的 TELECOM_SERVICE 服务
        mTelecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        mTelephonymanager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mTelephonymanager == null) {
            mTelephonymanager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        }

        // 给Telephonymanager 添加监听器
        mTelephonymanager.listen(mMyPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        return super.onStartCommand(intent, flags, startId);

    }

    //监听通过话
    public class MyPhoneStateListener extends PhoneStateListener {

        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void onCallStateChanged(int state, final String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    //通话空闲状态
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //通话来电状态
                    try {
                        Log.e("C_M_BlackCallService", "Monitor incoming calls");
                        //拦截来电判断来电时黑名单or白名单
                        splitWhiteAndBlack(incomingNumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //电话挂起状态
                    break;
            }

        }
    }

    //incomingNumber:来电号码 依据来电号码判断黑白名单
    private void splitWhiteAndBlack(String incomingNumber) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {


            if (incomingNumber.startsWith("180")) {  // 认为是黑名单
                //1.黑名单自动挂断
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    mTelecomManager.endCall();
                    return;
                }
            }

            //2.白名单自动接听
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mTelecomManager.acceptRingingCall();
            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}