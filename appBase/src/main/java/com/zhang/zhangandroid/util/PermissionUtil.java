package com.zhang.zhangandroid.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 简介: 权限工具类
 *  作者: zhangg
 */
public class PermissionUtil {

    private static final String TAG = "mmmm" ;


    /**
     * 简介: 使用PermissionX 工具类申请权限
     * @param activity 上下文环境
     * @param permissionArray 申请权限集合
     * @param callback  结果回调
     *  作者: zhangg
     */
    public void requestPermissions(AppCompatActivity activity, String[] permissionArray, PermissionRequestCallback callback){

        ArrayList<String> permission = new ArrayList<>();
        // 蓝牙
        permission.add(Manifest.permission.BLUETOOTH);
        permission.add(Manifest.permission.BLUETOOTH_ADMIN);

        permission.addAll(Arrays.asList(permissionArray));

        PermissionX.init(activity)
                .permissions(permission)
                .request(
                        new RequestCallback() {
                            @Override
                            public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                                if (allGranted) {
//							        showMessage("good~");
                                    if (callback != null){
                                        callback.success(new ArrayList<>(grantedList));
                                    }
                                } else {
                                    String s = deniedList.toString();
//                                  showMessage(s + " 没有授予权限.");
                                    Log.e("mmmm",s + " 没有授予权限.");
                                    if (callback != null){
                                        callback.fail(new ArrayList<>(deniedList));
                                    }
                                }
                            }
                        });
    }


    /**
     * 简介: 判断是否拒绝过
     */
    public boolean isRequestPermissionRationale(AppCompatActivity activity){
        boolean hasrefuse = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE);
        return hasrefuse;
    }

    /**
     * 检查app是否拥有存储权限，如果没有的话，提醒用户开启权限
     */
    public static void handlePermission(AppCompatActivity activity,String permision) {
        if (null == permision){
            return;
        }
        // 检查是否开启 Manifest.permission.xxx
        // (xxx 为权限，根据自己需求添加）
        if (ActivityCompat.checkSelfPermission(activity, permision) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Permission has been allowed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "ask for permission",Toast.LENGTH_SHORT).show();
            // 请求权限
            ActivityCompat.requestPermissions(activity, new String[] {permision}, 1);
            Log.d(TAG, "handlePermission: has aksed");
        }
    }


    /**
     * 检查app是否拥有存储权限，如果没有的话，提醒用户开启权限
     */
    public static void handlePermission(AppCompatActivity activity) {
        // 检查是否开启 Manifest.permission.xxx
        // (xxx 为权限，根据自己需求添加）
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Permission has been allowed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "ask for permission",Toast.LENGTH_SHORT).show();
            // 请求权限
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Log.d(TAG, "handlePermission: has aksed");
        }
    }


}
