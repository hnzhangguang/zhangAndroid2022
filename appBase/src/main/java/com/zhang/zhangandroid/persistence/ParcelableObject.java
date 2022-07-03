package com.zhang.zhangandroid.persistence;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简介: 序列号对象android
 * ParcelableObject 为要实现序列化的实体类
 *  android 开发尽量使用 parcleable 实现序列号, 性能较高
 */
public class ParcelableObject implements Parcelable {

    private int  id;
    private String name;

    // 反序列化时候调用(从parcel对象中读取处理,赋值给成员变量)
    protected ParcelableObject(Parcel in) {//读取的顺序和写入的顺序必须一致,否则报错
        id = in.readInt();
        name = in.readString();
    }

    // 把成员变量写入parcle对象中
    @Override
    public void writeToParcel(Parcel parcel, int i) {//读取的顺序和写入的顺序必须一致,否则报错
        parcel.writeInt(id);
        parcel.writeString(name);
    }


    //
    public static final Creator<ParcelableObject> CREATOR = new Creator<ParcelableObject>() {
        @Override
        public ParcelableObject createFromParcel(Parcel in) {
            return new ParcelableObject(in);
        }

        @Override
        public ParcelableObject[] newArray(int size) {
            return new ParcelableObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
