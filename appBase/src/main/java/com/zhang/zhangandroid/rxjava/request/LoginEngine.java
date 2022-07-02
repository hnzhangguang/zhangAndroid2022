package com.zhang.zhangandroid.rxjava.request;

import io.reactivex.Observable;

public class LoginEngine {



    // 返回起点
    public static Observable<ResponseResult> login(String name,String password){


        ResponseResult responseResult = new ResponseResult();

        // 成功
        if ("zhang".equals(name)&& password.equals("123")){
            SuccessBean successBean = new SuccessBean();
            successBean.setCode("code....");
            successBean.setName("zhansan");

            responseResult.setSuccessBean(successBean);
            responseResult.setMsg("success");
            responseResult.setCode(200);

        }else {

            // 失败
            responseResult.setSuccessBean(null);
            responseResult.setMsg("error....");
            responseResult.setCode(404);
        }


        return Observable.just(responseResult);


    }
}
