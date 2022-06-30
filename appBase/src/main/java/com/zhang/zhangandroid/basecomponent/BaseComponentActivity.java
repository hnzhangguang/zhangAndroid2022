package com.zhang.zhangandroid.basecomponent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.R;

import java.util.ArrayList;
import java.util.List;

public class BaseComponentActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_component);
        listView = findViewById(R.id.listView_basecomponent);


        List<String> list = new ArrayList<>();
        list.add("ImageViewActivity");

        MyAdapter adapter = new MyAdapter(list,this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);//"baseComponent"
                String junmString = "com.zhang.zhangandroid.basecomponent.ImageViewActivity";

                if (item.equals("ImageViewActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.ImageViewActivity";
                }


                ComponentName cn = new ComponentName(getApplication().getPackageName(),junmString) ;
                Intent intent = new Intent() ;
                intent.setComponent(cn) ;
                startActivity(intent) ;



            }
        });





    }



    /**
     * 简介: listView适配器
     *  作者: zhangg
     */
    class MyAdapter extends BaseAdapter {

        List<String> datas ; // 数据集
        Context context; // 上下文

        public MyAdapter(List<String> datas, Context context){
            this.datas = datas;
            this.context = context;
        }

        @Override
        public int getCount() {
            return null == datas ? 0: datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.mainactivity_list_item, null);
            TextView textView = inflate.findViewById(R.id.activity_list_item);
            textView.setText(getItem(i).toString());
            return inflate;
        }
    }



}