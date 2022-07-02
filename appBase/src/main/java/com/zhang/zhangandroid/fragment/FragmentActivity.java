package com.zhang.zhangandroid.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zhang.zhangandroid.R;


/**
 * 简介: 静态加载fragment
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}