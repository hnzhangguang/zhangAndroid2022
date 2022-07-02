package com.zhang.zhangandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zhang.zhangandroid.R;


/**
 * 简介: 动态加载fragment
 */
public class FragmentActivity2 extends AppCompatActivity {

    Button change_fragment;
    Button replace_fragment;
    FrameLayout frameLayout_fragment;
    FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);


        supportFragmentManager = getSupportFragmentManager();

        change_fragment = findViewById(R.id.change_fragment);
        replace_fragment = findViewById(R.id.replace_fragment);
        frameLayout_fragment = findViewById(R.id.frameLayout_fragment);

        change_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentById = supportFragmentManager.findFragmentById(R.id.frameLayout_fragment);
                if (fragmentById instanceof BlankFragment1){
                    replaceFragment(new BlankFragment2());
                }else {
                    replaceFragment(new BlankFragment1());
                }
            }
        });

        replace_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new BlankFragment2());

            }
        });


    }


    /**
     * 简介: commit和commitNow第二个不同：commit可添加到返回栈中，commitNow不允许添加到返回栈中。
     */
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_fragment,fragment);
        transaction.addToBackStack(null); // 回退栈时候,使用
        transaction.commit();
    }
}