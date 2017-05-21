package com.emre.androbooster;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;

/**
 * Created by Emre2 on 26.7.2016.
 */
 
public class KillManager {
    public static void killProcess(String packagename, Context context){
        ActivityManager  manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> listOfProcesses = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : listOfProcesses)
        {
            if (process.processName.contains(packagename))
            {
                manager.killBackgroundProcesses(packagename);
                manager.restartPackage(process.processName);
                break;
            }
        }
    }
}
