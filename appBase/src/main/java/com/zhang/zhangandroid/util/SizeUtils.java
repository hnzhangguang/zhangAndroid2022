package com.zhang.zhangandroid.util;

import android.util.TypedValue;

import com.zhang.zhangandroid.App;

public class SizeUtils {




    public static int dp2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, App.getApplication().getResources().getDisplayMetrics());
    }

    protected static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, App.getApplication().getResources().getDisplayMetrics());
    }



}
