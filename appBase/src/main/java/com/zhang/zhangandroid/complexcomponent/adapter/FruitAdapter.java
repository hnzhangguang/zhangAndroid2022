package com.zhang.zhangandroid.complexcomponent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.vo.Apple;
import com.zhang.zhangandroid.vo.Fruit;

import java.util.List;

/**
 * 简介: 适配器
 */
public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Fruit> mFruitList;  //数据源，在new此类的时候传入

    //静态内部类， 每个条目对应的布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruitname);
        }

    }

    static class ViewHolderApple extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolderApple(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruitname);
        }

    }


    //FruitAdapter的构造方法，加入了数据源参数，在构造的时候赋值给mFruitList
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    //用于创建ViewHolder实例,并把加载的布局传入到ViewHolder的构造函数去
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // fruit
        if (1 == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }else {
            // apple
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_apple, parent, false);
            ViewHolderApple holder = new ViewHolderApple(view);
            return holder;
        }

    }

    //是用于对子项的数据进行赋值,会在每个子项被滚动到屏幕内时执行。position得到当前项的Fruit实例
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder){
            // fruit
            ViewHolder holder1 = (ViewHolder) holder;
            Fruit fruit = mFruitList.get(position);
            holder1.fruitImage.setImageResource(fruit.getImageId());
            holder1.fruitName.setText(fruit.getName());
            // 点击事件
            holder1.fruitImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewOnItemClick !=null){
                        recycleViewOnItemClick.onItemClick(fruit);
                    }
                }
            });
        }else {
            // apple
            ViewHolderApple holder1 = (ViewHolderApple) holder;
            Apple fruit = (Apple) mFruitList.get(position);
            holder1.fruitImage.setImageResource(fruit.getImageId());
            holder1.fruitName.setText(fruit.getName()+"_apple");
            // 点击事件
            holder1.fruitImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewOnItemClick !=null){
                        recycleViewOnItemClick.onItemClick(fruit);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        Fruit fruit = mFruitList.get(position);
        if (fruit instanceof Apple){
            return 2;  // apple
        }
        return 1; // fruit
    }

    //返回RecyclerView的子项数目
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    private RecycleViewOnItemClick recycleViewOnItemClick;

    public interface RecycleViewOnItemClick{
        void onItemClick(Fruit fruit);
    }


    public void setRecycleViewOnItemClick(RecycleViewOnItemClick recycleViewOnItemClick){
        this.recycleViewOnItemClick = recycleViewOnItemClick;
    }



}