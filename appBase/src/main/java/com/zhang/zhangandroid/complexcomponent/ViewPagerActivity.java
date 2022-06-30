package com.zhang.zhangandroid.complexcomponent;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 使用viewPager的步骤如下：
 * (1)在布局中放入viewPager的控件
 * (2)设置加载到viewPager中的view
 * (3)编写viewPager特有的adapter
 * (4)实例化viewPager并给他绑定上步设置的adapter
 * <p>
 * 作者: zhangg
 */
public class ViewPagerActivity extends BaseActivity {

    private ViewPager mpager;
    private List<View> myview = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mpager = findViewById(R.id.view_pager);

        LayoutInflater mInflater = getLayoutInflater();
        View [] pagers = {mInflater.inflate(R.layout.activity_view_pager_item1 ,null),
                mInflater.inflate(R.layout.activity_view_pager_item2 , null),
                mInflater.inflate(R.layout.activity_view_pager_item3 , null)};

        for(int i = 0; i < pagers.length ; i++) {
            myview.add(pagers[i]);
        }
        ViewPagerActivity.Adapter ad = new ViewPagerActivity.Adapter(myview);
        mpager.setAdapter(ad);
        mpager.setCurrentItem(0);



        mpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                logZhang("aa: "+position);
                int index = position;
                if(index == -1){
                    //注意这里pagers数组，不是myviews数组
                    index = pagers.length;
                }else if(position == pagers.length + 1){
                    index = 0;
                }
                if(position != index){
                    mpager.setCurrentItem(index, false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





    }

    class Adapter extends PagerAdapter {

        private List<View> views;

        public Adapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public Object instantiateItem(View view, int position) {
            ((ViewPager) view).addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }


}