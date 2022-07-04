package com.zhang.zhangandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by Jay on 2015/8/18 0018.
 */
public class AIDLService extends Service {


    // AIDL接口实现类实例
    private IBinder binder = new PersonQueryBinder();


    private String[] names = {"B神","艹神","基神","J神","翔神"};


    // 接口中方法实现
    private String query(int num)
    {
        if(num > 0 && num < 6){
            return names[num - 1];
        }
        return null;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // AIDL接口实现类
    private final class PersonQueryBinder extends IPerson.Stub {
        @Override
        public String queryPerson(int num) throws RemoteException {
            return query(num);
        }
    }
}