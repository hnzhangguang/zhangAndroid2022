package com.zhang.zhangandroid.provider;

public class Book {

    private int  _id;
    private String name;
    private int page;


    public Book() {
    }

    public Book(int _id, String name, int page) {
        this._id = _id;
        this.name = name;
        this.page = page;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
