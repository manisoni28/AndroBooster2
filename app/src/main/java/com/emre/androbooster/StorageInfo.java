package com.emre.androbooster;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created by Emre2 on 11.8.2016.
 */
public class StorageInfo {

    public static int getTotalSize(){
        float total_sonuc = 0;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long blockSize = statFs.getBlockSize();
        long totalSize = statFs.getBlockCount()*blockSize;
     //   long availableSize = statFs.getAvailableBlocks()*blockSize;
       // long freeSize = statFs.getFreeBlocks()*blockSize;
        total_sonuc = (long) totalSize  / (1024.f * 1024.f);
        return Integer.valueOf((int) total_sonuc);
    }

    public static int megabytesAvailable() {
        float sonuc = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long bytesAvailable = 0;
            bytesAvailable = (long)stat.getBlockSizeLong() * (long)stat.getAvailableBlocksLong();
            sonuc =  bytesAvailable / (1024.f * 1024.f);
        }else {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long bytesAvailable = (long)stat.getBlockSize() * (long)stat.getAvailableBlocks();
            sonuc = bytesAvailable / (1024.f * 1024.f);
        }
        return Integer.valueOf((int) sonuc);
    }
}
