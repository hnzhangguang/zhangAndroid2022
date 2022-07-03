package com.zhang.zhangandroid.util;

import java.util.ArrayList;

public interface PermissionRequestCallback {

    void  success(ArrayList<String> success);
    void fail(ArrayList<String> fails);


}
