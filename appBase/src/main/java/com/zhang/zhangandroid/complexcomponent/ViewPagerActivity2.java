package com.zhang.zhangandroid.complexcomponent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.fragment.BlankFragment1;
import com.zhang.zhangandroid.fragment.BlankFragment2;

import java.util.ArrayList;
import java.util.List;


/**
 * 简介: Fragment ViewPager
 *  作者: zhangg
 */
public class ViewPagerActivity2 extends AppCompatActivity {


    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);


        viewPager = findViewById(R.id.viewPager);
        List<Fragment> list = new ArrayList<>();
        list.add(new BlankFragment1());
        list.add(new BlankFragment2());
        list.add(new BlankFragment1());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));



    }
}

 class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
}

