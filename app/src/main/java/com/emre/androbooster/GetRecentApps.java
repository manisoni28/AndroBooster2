package com.emre.androbooster;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Emre2 on 14.7.2016.
 */
public class GetRecentApps {
    public static String getRecentApps(Context context){
        String topPackageName = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*10, time);
            if(stats != null) {
                SortedMap<Long,UsageStats> mySortedMap = new TreeMap<Long,UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(),usageStats);
                }
                if(!mySortedMap.isEmpty()) {
                    topPackageName =  mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        }else {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RecentTaskInfo> recentTasks = activityManager.getRecentTasks(10, ActivityManager.RECENT_IGNORE_UNAVAILABLE);
            for (ActivityManager.RecentTaskInfo recentTask : recentTasks) {
                try {
                    String packageName = recentTask.baseIntent.getComponent().getPackageName();
                    ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
                    String label = appInfo.packageName;
                    topPackageName = label;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        return topPackageName;
    }
}
