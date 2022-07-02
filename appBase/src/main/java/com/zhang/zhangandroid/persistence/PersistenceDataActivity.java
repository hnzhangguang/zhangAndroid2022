package com.zhang.zhangandroid.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.persistence.sqlite.Contact;
import com.zhang.zhangandroid.persistence.sqlite.DatabaseHelper;

import java.time.temporal.ValueRange;
import java.util.List;

/**
 * 简介: 数据持久化
 * 功能: 1,  SharedPreferences
 *      2,
 *  作者: zhangg
 */
public class PersistenceDataActivity extends BaseActivity {


    Button addData_persisten;
    Button update_persisten;
    Button delete_persisten;
    Button quer_persisten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistence_data);

        addData_persisten = findViewById(R.id.addData_persisten);
        update_persisten = findViewById(R.id.update_persisten);
        delete_persisten = findViewById(R.id.delete_persisten);
        quer_persisten = findViewById(R.id.quer_persisten);

        addData_persisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        update_persisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
        delete_persisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
        quer_persisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryData();
            }
        });

        SharedPreferences();

        SqliteHelper();


    }



    /**
     * 简介: SharedPreferences 的使用
     *  作者: zhangg
     */
    public void SharedPreferences() {

        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("name", "Karl");
        editor.putString("habit", "sleep");
        //提交当前数据
        editor.commit();
        //使用toast信息提示框提示成功写入数据
        Toast.makeText(this, "数据成功写入SharedPreferences！",
                Toast.LENGTH_LONG).show();


        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String name =sharedPreferences.getString("name", "");
        String habit =sharedPreferences.getString("habit", "");
        //使用toast信息提示框显示信息

        Toast.makeText(this, "读取数据如下："+"\n"+"name：" + name + "\n" + "habit：" + habit,
                Toast.LENGTH_LONG).show();

    }



    public void addData(){

            DatabaseHelper helper = new DatabaseHelper(this);
            Contact contact = new Contact("zhansan","123");
            helper.addContact(contact);

    }


    public void updateData(){

        DatabaseHelper helper = new DatabaseHelper(this);

        List<Contact> allContacts = helper.getAllContacts();
        if(allContacts != null && allContacts.size()>0){
            Contact contact = allContacts.get(0);
            contact.setName(contact.getName()+"a");
            helper.updateContact(contact);
        }


    }

    public void deleteData(){
        DatabaseHelper helper = new DatabaseHelper(this);
        List<Contact> allContacts = helper.getAllContacts();
        if(allContacts != null && allContacts.size()>0){
            Contact contact = allContacts.get(0);
            helper.deleteContact(contact);
        }
    }

    public void queryData(){
        DatabaseHelper helper = new DatabaseHelper(this);

        List<Contact> allContacts = helper.getAllContacts();
        for (Contact allContact : allContacts) {
            logZhang(allContact);
        }

    }


    public void SqliteHelper(){

//        DatabaseHelper helper = new DatabaseHelper(this);
//        Contact contact = new Contact(1,"zhansan","123");
//        helper.addContact(contact);
//
//        contact.setName("lisi");
//        helper.updateContact(contact);
//
//        List<Contact> allContacts = helper.getAllContacts();
//        for (Contact allContact : allContacts) {
//            logZhang(allContact);
//        }
//
//        helper.deleteContact(contact);
//
//
//        int contactsCount = helper.getContactsCount();
//        logZhang("共 "+ contactsCount +" 条数据!");


    }
}