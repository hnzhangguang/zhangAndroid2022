package com.zhang.zhangandroid.vo;

public class Fruit {

    private String name;  //水果的名字
    private int imageId;  //水果对应的照片

    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;

    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }



}
