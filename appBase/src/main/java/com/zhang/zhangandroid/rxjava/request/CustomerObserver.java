package com.zhang.zhangandroid.rxjava.request;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CustomerObserver implements Observer<ResponseResult> {

    public abstract void success(SuccessBean successBean);
    public abstract void error(String messsage);

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull ResponseResult responseResult) {

        if (responseResult == null){
            error(responseResult.getMsg()+" 请求出错了,请检查日志信息....");
        }else {
            success(responseResult.getSuccessBean());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
            error(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
