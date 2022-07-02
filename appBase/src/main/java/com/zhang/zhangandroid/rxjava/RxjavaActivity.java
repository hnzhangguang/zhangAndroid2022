package com.zhang.zhangandroid.rxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 简介: 响应式编程思维
 *  起点  --> xxx -> xxx -> xxx  -> 终点
 *
 */
public class RxjavaActivity extends BaseActivity {

    private String TAG = "mmmm";

    private Button Observable_btn;
    private Button Observer_btn;
    private Button subscribe_btn;
    private ImageView rxjava_image;
    private Button downloadimage_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        Observable_btn = findViewById(R.id.Observable_btn);//被观察者
        Observer_btn = findViewById(R.id.Observer_btn); // 观察者
        subscribe_btn = findViewById(R.id.subscribe_btn); // 订阅
        rxjava_image = findViewById(R.id.rxjava_image);
        downloadimage_btn = findViewById(R.id.downloadimage_btn);


        Observable_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable();
            }
        });

        Observer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observer();
            }
        });


        subscribe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribe();
            }
        });

        downloadimage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoadImage();
            }
        });


    }


    // 定义观察者
    public void Observer() {
        //创建Observer（观察者）
        Observer<Integer> observer = new Observer<Integer>() {

            // 观察者接收事件前  ，当 Observable 被订阅时，观察者onSubscribe方法会自动被调用
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            // 当被观察者生产Next事件
            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件作出响应" + value);
            }

            // 当被观察者生产Error事件
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 当被观察者生产Complete事件
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };


        //Subscriber类 = RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            // 观察者接收事件前 ，当 Observable 被订阅时，观察者onSubscribe方法会自动被调用
            @Override
            public void onSubscribe(Subscription s) {

            }

            // 当被观察者生产Next事件
            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件作出响应" + value);
            }

            // 当被观察者生产Error事件
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 当被观察者生产Complete事件
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }


        };


    }


    // 创建被观察者
    public void Observable() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter：定义需要发送的事件 & 向观察者发送事件

                emitter.onNext(1);
                emitter.onComplete();
            }
        });


//        RxJava 提供了其他方法用于 创建被观察者对象Observable


        // 方法1：just(T...)：直接将传入的参数依次发送出来
        Observable observable2 = Observable.just("A", "B", "C");
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();

        // 方法2：fromArray(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
        String[] words = {"A", "B", "C"};
        Observable observable3 = Observable.fromArray(words);
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();


        //以上两种方法创建出来的观察者都是继承Observable，比如ObservableCreate、ObservableFromArray、ObservableMap...，


    }


    //观察者和被观察者通过subscribe订阅 ( 起点 - 终点  )
    public void subscribe() {
//        观察者和被观察者通过subscribe订阅，订阅完成后被观察者就可以像观察者发送数据
        Observable.create(new ObservableOnSubscribe<Integer>() {// 定义被观察者 起点

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {// 变换向下传递的类型

            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).map(new Function<String, String>() { //变换向下传递的类型
            @Override
            public String apply(@NonNull String s) throws Exception {
                return s+"zhangg";
            }
        }).subscribe(new Observer<String>() {  // 注册观察者 终点

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "对Next事件 [" + value + "] 作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "对Complete事件作出响应");
            }

        });


    }


    public void downLoadImage(){
        String PATH = "https://pics5.baidu.com/feed/b64543a98226cffc53a4a8beb5decf9af703ea1d.jpeg?token=3e41a326bc8795a1a0f762b1151bff92";
        Observable.just(PATH).map(new Function<String, Bitmap>() {  // 1, 起点
            @Override
            public Bitmap apply(@NonNull String path) throws Exception { //2,  中间转换

                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(6000);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return  bitmap;
                    }


                }catch (Exception e){

                    logZhang(e);

                }
                return null;
            }
        })
                .subscribeOn(Schedulers.io())  // 给上面的分配异步线程(图片下载)
                .observeOn(AndroidSchedulers.mainThread()) // 给观察者分配线程main线程
                .subscribe(new Observer<Bitmap>() {   // 3, 终点
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Bitmap bitmap) {
                rxjava_image.setImageBitmap(bitmap);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}


