package com.emre.androbooster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.List;

/**
 * Created by emre on 24.04.2016.
 */
 
public class RAMBooster {
    public static void freeMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(100);
        for (int i = 1; i < taskInfo.size(); i++) {
            activityManager.killBackgroundProcesses(((ActivityManager.RunningTaskInfo) taskInfo.get(i)).topActivity.getPackageName());
        }
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
    public static void cleanAllActivities(Context context){
        List<ApplicationInfo> packages;
        PackageManager pm;
        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ApplicationInfo packageInfo : packages) {
            if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
            mActivityManager.killBackgroundProcesses(packageInfo.packageName);
        }
    }
}
