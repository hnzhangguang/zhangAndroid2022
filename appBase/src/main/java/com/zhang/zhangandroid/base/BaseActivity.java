package com.zhang.zhangandroid.base;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 简介: activity基类
 * 功能: 1, 公共方法
 * 2,工具方法等
 * 作者: zhangg
 */
public class BaseActivity extends AppCompatActivity {


    /**
     * 简介: 打开activity
     * 作者: zhangg
     */
    public void openActivity(String jumpString) {
        ComponentName cn = new ComponentName(getApplication().getPackageName(), jumpString);
        Intent intent = new Intent();
        intent.setComponent(cn);
        startActivity(intent);
    }


    public void logZhang(Object log) {
        if (null == log) {
            Log.e("mmmm", "参数 log is null");
        } else {
            Log.e("mmmm", log.toString());
        }
    }

}
