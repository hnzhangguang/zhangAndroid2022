package com.zhang.zhangandroid.rxjava.request;

public class SuccessBean {


    private String name;
    private String code;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SuccessBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
