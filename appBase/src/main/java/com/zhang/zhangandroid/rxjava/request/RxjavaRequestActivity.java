package com.zhang.zhangandroid.rxjava.request;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhang.zhangandroid.base.BaseActivity;

import io.reactivex.Observable;


/**
 * 简介: 自定义 观察者 并使用
 */
public class RxjavaRequestActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 被观察者
        Observable<ResponseResult> observable = LoginEngine.login("zhang", "123");
        // 注册观察者
        observable.subscribe(new CustomerObserver() { // 观察者
            @Override
            public void success(SuccessBean successBean) {

            }

            @Override
            public void error(String messsage) {

            }
        });


    }




}
