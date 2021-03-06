package com.zhang.zhangandroid.complexcomponent;

import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.complexcomponent.adapter.FruitAdapter;
import com.zhang.zhangandroid.vo.Apple;
import com.zhang.zhangandroid.vo.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends BaseActivity {


    SwipeRefreshLayout recycleView_swipeRefreshLayout;
    RecyclerView recyclerView;



    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        //初始化数据
        initFruits();


        recycleView_swipeRefreshLayout = findViewById(R.id.recycleView_swipeRefreshLayout);
        recyclerView = findViewById(R.id.recycleView);


        // 更改下拉刷新圈圈颜色
        recycleView_swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        // 下拉刷新事件
        recycleView_swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SystemClock.sleep(2000);
                recycleView_swipeRefreshLayout.setRefreshing(false);
            }
        });



        //1.1 LinearLayoutManager
        //1.2 GridLayoutManager
        //1.3 StaggeredGridLayoutManager 交错的网格布局

//        GridLayoutManager(this, 3)
//        GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true)
//        GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        
//        StaggeredGridLayoutManager 交错的网格布局

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        //LayoutManager用于指定RecyclerView的布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //给layoutManager 的展示方式设置为竖直方向
        layoutManager .setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // 适配器
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        adapter.setRecycleViewOnItemClick(new FruitAdapter.RecycleViewOnItemClick() {
            @Override
            public void onItemClick(Fruit fruit) {
                showToast(fruit);
            }
        });




    }


    //初始化数据
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
            fruitList.add(apple);
            Apple banana = new Apple("Banana",R.mipmap.apple,"apple");
            fruitList.add(banana);
            Apple orange = new Apple("Orange", R.mipmap.apple,"apple");
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.mipmap.ic_launcher);
            fruitList.add(watermelon);
            Apple pear = new Apple("Pear", R.mipmap.apple,"apple");
            fruitList.add(pear);
            Apple grape = new Apple("Grape", R.mipmap.apple,"apple");
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.mipmap.ic_launcher);
            fruitList.add(pineapple);
            Apple strawberry = new Apple("Strawberry", R.mipmap.apple,"apple");
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.mipmap.ic_launcher);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango",R.mipmap.ic_launcher);
            fruitList.add(mango);

        }
    }





}