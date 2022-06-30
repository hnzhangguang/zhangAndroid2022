package com.zhang.zhangandroid.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhang.zhangandroid.MainActivity;
import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.base.MyListViewSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends BaseActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        listView = findViewById(R.id.listView_layout);

        List<String> list = new ArrayList<>();
        list.add("DrawerLayoutActivity");
        list.add("DrawerLayoutViewPagerActivity");//DrawerLayout + ViewPager 解决滑动冲突问题

        MyListViewSimpleAdapter adapter = new MyListViewSimpleAdapter(list,this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);//"baseComponent"
                String junmString = "com.zhang.zhangandroid.layout.DrawerLayoutActivity";

                if (item.equals("DrawerLayoutActivity")){
                    junmString = "com.zhang.zhangandroid.layout.DrawerLayoutActivity";
                }
                if (item.equals("DrawerLayoutViewPagerActivity")){
                    junmString = "com.zhang.zhangandroid.layout.DrawerLayoutViewPagerActivity";
                }




                // 打开activity
                openActivity(junmString);



            }
        });
    }
}