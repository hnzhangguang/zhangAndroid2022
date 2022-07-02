package com.zhang.zhangandroid.persistence.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhang.zhangandroid.persistence.sqlite.Contact;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库版本
    private static final int DATABASE_VERSION = 1;

    // 数据库名
    private static final String DATABASE_NAME = "contactsManager";

    //Contact表名
    private static final String TABLE_CONTACTS = "contacts";

    //Contact表的列名
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // 更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除旧表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        //再次创建表
        onCreate(db);
    }

    /**
     * 增删改查操作
     */

    // 增加新的联系人
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        if (db.isOpen()) {
            // 插入行
            db.insert(TABLE_CONTACTS, null, values);
            db.close(); // 关闭数据库的连接
        }

    }

    // 获取联系人
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        if (db.isOpen()) {
            Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                            KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));

            cursor.close();
            db.close();

            return contact;
        }
        return null;

    }

    // 获取所有联系人
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();

                    // 下面是获取字段的标准写法
                    contact.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                    contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));

                    // 放入集合中
                    contactList.add(contact);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
        }

        return contactList;
    }

    // 更新单个联系人
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PH_NO, contact.getPhoneNumber());

            //更新行
            int update = db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(contact.getID())});

            db.close();
            return update;
        }

        return -2;
    }

    // 删除单个联系人
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (db.isOpen()) {
            db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                    new String[]{String.valueOf(contact.getID())});
            db.close();
        }
    }


    // 获取联系人数量
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();

            // 先取出数据后再关闭cursor
            cursor.close();
            db.close();
            return count;
        }

        return 0;
    }


}