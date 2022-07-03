package com.zhang.zhangandroid.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
 
import java.util.Locale;


/**
 * 简介: 语种的切换工具类型
 *  作者:
 *
 *
 *  使用方式:
 *
 *  @Override
 *     protected void attachBaseContext(Context newBase) {
 *         Locale newLocale;
 *         if (SPUtil.getBoolean(newBase,"isEN")) {
 *             //设置英文
 *             newLocale = Locale.ENGLISH;
 *         } else {
 *             //设置中文
 *             newLocale = Locale.SIMPLIFIED_CHINESE;
 *         }
 *         Context context = MyContextWrapper.wrap(newBase, newLocale);
 *         super.attachBaseContext(context);
 *     }
 *
 *     直接在你继承的BaseActivity里面重载（@Override）attachBaseContext方法即可。
 *
 */
public class MyContextWrapper  extends ContextWrapper {
 
    public MyContextWrapper(Context base) {
        super(base);
    }
 
    public static ContextWrapper wrap(Context context, Locale newLocale) {
 
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
 
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
 
            configuration.setLocale(newLocale);
            LocaleList localeList = new LocaleList(newLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context = context.createConfigurationContext(configuration);
 
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
 
            configuration.setLocale(newLocale);
            context = context.createConfigurationContext(configuration);
 
        }
 
        return new ContextWrapper(context);
    }
}