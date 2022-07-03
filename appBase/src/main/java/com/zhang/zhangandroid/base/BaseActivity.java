package com.zhang.zhangandroid.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.util.MyContextWrapper;
import com.zhang.zhangandroid.util.SPUtil;

import java.util.Locale;

/**
 * 简介: activity基类
 * 功能: 1, 公共方法
 * 2,工具方法等
 * 作者: zhangg
 */
public class BaseActivity extends AppCompatActivity {



    /**
     * 简介:处理多语问题
     *  作者: zhangg
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale;
        if (SPUtil.getBoolean(newBase,"isEN")) {
            //设置英文
            newLocale = Locale.ENGLISH;
        } else {
            //设置中文
            newLocale = Locale.SIMPLIFIED_CHINESE;
        }
        Context context = MyContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }


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
