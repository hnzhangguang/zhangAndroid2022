package com.zhang.zhangandroid.provider;

public class User {

    private int  _id;
    private String name;
    private int sex;

    public User() {
    }

    public User(int _id, String name, int sex) {
        this._id = _id;
        this.name = name;
        this.sex = sex;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
