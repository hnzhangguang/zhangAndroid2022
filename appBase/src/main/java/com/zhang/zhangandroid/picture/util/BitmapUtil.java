package com.zhang.zhangandroid.picture.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class BitmapUtil {


    /**
     * 简介:
     * 功能: 选择 相机 or 相册
     */
    public void select(Activity activity, int SELECT_PICTURE, int SELECT_CAMER) {
        CharSequence[] items = {"相册", "相机"};
        new AlertDialog.Builder(activity)
                .setTitle("选择图片来源")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == SELECT_PICTURE) {//相册
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("image/*");
                            activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                        } else {
                            // 相机
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activity.startActivityForResult(intent, SELECT_CAMER);
                        }
                    }
                })
                .create().show();
    }


    Bitmap bmp;


    /** 方式1
     * 简介: 直接处理返回图片：
     */
    protected void onActivityResult1(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //选择图片
            Uri uri = data.getData();
            ContentResolver cr = activity.getContentResolver();
            try {
                if (bmp != null)//如果不释放的话，不断取图片，将会内存不够
                    bmp.recycle();
                bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("the bmp toString: " + bmp);
        } else {
            Toast.makeText(activity, "请重新选择图片", Toast.LENGTH_SHORT).show();
        }
    }



    /** 方式2
     * 简介: 获得图片的地址再处理：
     */
    protected void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            String [] proj={MediaStore.Images.Media.DATA};
            Cursor cursor = activity.getContentResolver().query( uri,
                    proj, // Which columns to return
                    null, // WHERE clause; which rows to return (all rows)
                    null, // WHERE clause selection arguments (none)
                    null); // Order-by clause (ascending by name)
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            bmp = BitmapFactory.decodeFile(path);
            System.out.println("the path is :" + path);
        }else{
            Toast.makeText(activity, "请重新选择图片", Toast.LENGTH_SHORT).show();
        }
    }




}
