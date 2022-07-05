package com.zhang.zhangandroid.provider;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;

import java.util.logging.Logger;

/**
 * @author hongri
 */
public class ContentProviderActivity extends BaseActivity implements View.OnClickListener {
 
    //book uri
    private final Uri bookUri = MyContentProvider.BOOK_CONTENT_URI;
    //user uri
    private final Uri userUri = MyContentProvider.USER_CONTENT_URI;
    private Button btnInsert, btnQuery, btnQueryByUser, btnModify, btnDelete;
    private Handler mHandler = new Handler();
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
 
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        btnQueryByUser = (Button) findViewById(R.id.btnQueryByUser);
        btnModify = (Button) findViewById(R.id.btnModify);
        btnDelete = (Button) findViewById(R.id.btnDelete);
 
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnQueryByUser.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        // 注册ContentObserver监听ContentProvider的数据变化
        getContentResolver().registerContentObserver(bookUri, true, mContentObserver);
        getContentResolver().registerContentObserver(userUri, true, mContentObserver);

    }
 
    /**
     * 定义一个内容观察者【数据有更新时会调用】
     */
    private ContentObserver mContentObserver = new ContentObserver(mHandler) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            //这里selfChange都是返回false的，不用理会这个参数
            logZhang("onChange调用 --- 数据有更新");
        }
    };
 
 
    /**
     * 向 bookUri 中插入一本书
     */
    private void doInsert() {
        ContentValues values = new ContentValues();
        values.put("name", "Android框架");
        values.put("page", "1213");
        Uri uri = getContentResolver().insert(bookUri, values);
        logZhang("插入成功 ---> uri:" + uri.toString());
    }
 
    /**
     * 根据 bookUri 查询所有书籍
     */
    private void doQuery() {
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name", "page"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.set_id(bookCursor.getInt(0));
            book.setName(bookCursor.getString(1));
            book.setPage(bookCursor.getInt(2));
            logZhang("book:" + book.toString());
        }
        bookCursor.close();
    }
 
    /**
     * 查询 userUri 所有图书员
     */
    private void doQueryUser() {
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.set_id(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setSex(userCursor.getInt(2));
            logZhang("user:" + user.toString());
        }
        userCursor.close();
    }
 
    /**
     * 更新书籍【Android底层开发】
     */
    private void doUpdate() {
        ContentValues updateValues = new ContentValues();
        updateValues.put("name", "Android底层开发");
        updateValues.put("page", 3345);
        int row = getContentResolver().update(bookUri, updateValues, "name=?", new String[]{"Android框架"});
        if (row > 0) {
            logZhang("book:已修改");
        }
    }
 
    /**
     * 删除 名为"Java" 这本书
     */
    private void doDelete() {
        int count = getContentResolver().delete(bookUri, "name=?", new String[]{"Java"});
        if (count > 0) {
            logZhang("book:已进行删除操作");
        }
    }
 
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnInsert:
                /**
                 * 增
                 */
                doInsert();
                break;
 
            case R.id.btnQuery:
                /**
                 * 查
                 */
                doQuery();
                break;
 
            case R.id.btnQueryByUser:
                /**
                 * 查
                 */
                doQueryUser();
                break;
 
            case R.id.btnModify:
                /**
                 * 改
                 */
                doUpdate();
                doQuery();
                break;
 
            case R.id.btnDelete:
                /**
                 * 删
                 */
                doDelete();
                doQuery();
                break;
 
            default:
                break;
        }
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mContentObserver);
    }
}