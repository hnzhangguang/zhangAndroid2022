package com.zhang.zhangandroid.vo;

public class Apple extends Fruit{


    private String type; // 品种


    public Apple(String name, int imageId) {
        super(name,imageId);
        this.type = "apple";
    }

    public Apple(String name, int imageId, String type) {
        super(name,imageId,type);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Apple{" +
                "type='" + type + '\'' +
                '}';
    }
}
