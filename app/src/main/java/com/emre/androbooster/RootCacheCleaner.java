package com.emre.androbooster;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by Emre2 on 12.8.2016.
 */
public class RootCacheCleaner {
    Context mContext;

    RootCacheCleaner(Context context){
        mContext = context;
    }
    public void clearAllCache(){
        PackageManager pm = mContext.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);


        for(ApplicationInfo app : apps) {
                String app_cache_path_string = "/data/data/"+app.packageName+"/cache/";
                TerminalCommand.command("rm -rf "+app_cache_path_string);

        }
    }

}

