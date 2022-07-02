package com.zhang.zhangandroid.rxjava.request;



public class ResponseResult {
    private int code;
    private String msg;
    private SuccessBean successBean;

//    public ResponseResult(int code, String msg, SuccessBean successBean) {
//        this.code = code;
//        this.msg = msg;
//        this.successBean = successBean;
//    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SuccessBean getSuccessBean() {
        return successBean;
    }

    public void setSuccessBean(SuccessBean successBean) {
        this.successBean = successBean;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", successBean=" + successBean +
                '}';
    }
}
