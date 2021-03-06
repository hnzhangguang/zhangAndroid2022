package com.zhang.zhangandroid.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.App;
import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.util.AppUtil;
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
     * 作者: zhangg
     */
    @Override
    protected void attachBaseContext(Context newBase) {

        // 记录当前activity
        Log.d("mmmm",getClass().getSimpleName());

        Locale newLocale;
        if (SPUtil.getBoolean(newBase, "isEN")) {
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


    public void showToast(Object object) {
        String msg = "";
        if (null == object) {
            msg = " paramer is null ,请检查!";
        }
        msg = object.toString();
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }


    // 判空
    public boolean isNull(String string) {
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return false;
    }


    //自定义toast
    public void showMidToast(String str) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.view_toast_custom,
                (ViewGroup) findViewById(R.id.layout_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo_toast);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg_toast);
        tv_msg.setText(str);
        Toast toast = new Toast(App.getApplication());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


}
