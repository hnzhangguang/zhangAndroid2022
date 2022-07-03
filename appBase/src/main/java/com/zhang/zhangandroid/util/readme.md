

DocumentsUtils.java
然后在app启动的地方检查下是否需要授权才能操作：
if (DocumentsUtils.checkWritableRootPath(this, StringUtils.STORAGE_PATH)) {   //检查sd卡路径是否有 权限 没有显示dialog
      showOpenDocumentTree();
}


private void showOpenDocumentTree() {
        Log.e("showOpenDocumentTree", "start check sd card...");
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            StorageManager sm = getSystemService(StorageManager.class);
            StorageVolume volume = sm.getStorageVolume(new File(StringUtils.STORAGE_PATH));
            if (volume != null) {
                intent = volume.createAccessIntent(null);
            }
        }
        Log.e("showOpenDocumentTree", "intent=" + intent);
        if (intent == null) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        }
        startActivityForResult(intent, DocumentsUtils.OPEN_DOCUMENT_TREE_CODE);
    }

//................................
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DocumentsUtils.OPEN_DOCUMENT_TREE_CODE:
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    DocumentsUtils.saveTreeUri(this, StringUtils.STORAGE_PATH, uri);
                    Log.i(TAG,"DocumentsUtils.OPEN_DOCUMENT_TREE_CODE ： "  + uri);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


 按以上代码，是可以实现对外置存储卡进行读和写操作了，只要机器没关机，多关打开关闭app，都不会弹下面这个授权框：


 但是-----如果关机再重新开机，就要重新授权，这样很麻烦，用户体验也极其不好，所以又查询了很多资料，最后在stackoverflow上找到办法，关键是下面这句：

 grantUriPermission(getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                     getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                  是在ActivityResult回调里，添加上面这两行代码，就不用每次都弹授权，影响体验了

                  见
                  DocumentsUtils.png
