package com.zhang.zhangandroid.picture.imagecrop.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.picture.imagecrop.fragment.MainFragment;

/**
 *
 * 《Android 拍照、选择图片并裁剪》
 *
 * https://github.com/xuehuayous/Android-ImageCrop
 *
 *
 */
public class ImageCorpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_imagecrop);
        initMainFragment();
    }

    /**
     * 初始化内容Fragment
     *
     * @return void
     */
    public void initMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MainFragment mFragment = MainFragment.newInstance();
        transaction.replace(R.id.main_act_container, mFragment, mFragment.getClass().getSimpleName());
        transaction.commit();
    }
}
