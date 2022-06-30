package com.zhang.zhangandroid.layout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.zhangandroid.R;


/**
 * 简介:
 * DrawerLayout其实是一个布局控件，跟LinearLayout等控件是一样的，但是drawerLayout带有滑动的功能。只要按照drawerLayout的规定布局方式写完布局，就能有侧滑的效果。
 * <p>
 * 1）在DrawerLayout中，第一个子View必须是显示内容的view，并且设置它的layout_width和layout_height属性是match_parent.
 * <p>
 * 2)第二个view是抽屉view,并且设置属性layout_gravity="left|right",表示是从左边滑出还是右边滑出。设置它的layout_height="match_parent"
 * ActionBarDrawerToggle就是DrawerLayout事件的监听器。
 * ActionBarDrawerToggle有3个方法可以被复写，分别用来实现DrawerLayout打开，关闭，滑动的事件监听：
 * onDrawerOpened DrawerLayout滑出时调用
 * onDrawerClosed DrawerLayout关闭时调用
 * onDrawerSlide DrawerLayout滑动时调用
 */
public class DrawerLayoutActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private TextView view;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        view = (TextView) findViewById(R.id.drawer_text);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.open,
                R.string.close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                view.setText("close");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                view.setText("dakai");
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                view.setText("huachu");
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }


}