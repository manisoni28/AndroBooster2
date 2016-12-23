package com.emre.androbooster;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.io.File;
import java.util.List;

/**
 * Created by Emre2 on 12.8.2016.
 */
public class ExternalCache {

        Context mContext;
        ExternalCache(Context context){
            mContext = context;
        }

    public void clearAllCacheOnSDCard(){
        PackageManager pm = mContext.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        for(ApplicationInfo app : apps) {
            String app_cache_path_string = "/sdcard/Android/data/"+app.packageName+"/cache";
            deleteDirectory(new File(app_cache_path_string));
        }
    }

    public static boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

}
