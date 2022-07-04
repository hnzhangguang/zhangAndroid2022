package com.zhang.zhangandroid.vo;

public class Fruit {

    private String name;  //水果的名字
    private int imageId;  //水果对应的照片
    private String type; // 品类


    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
        this.type = "fruit";
    }


    public Fruit(String name, int imageId,String type){
        this.name = name;
        this.imageId = imageId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", imageId=" + imageId +
                ", type='" + type + '\'' +
                '}';
    }
}
