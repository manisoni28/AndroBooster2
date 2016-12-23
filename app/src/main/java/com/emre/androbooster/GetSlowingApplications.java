package com.emre.androbooster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emre2 on 22.7.2016.
 */
public class GetSlowingApplications {
    Context context;
    private PackageManager pkgMgr = null;
    public static List appsList;
    public static ArrayList<Drawable> icons;
    public GetSlowingApplications(Context context1){
        this.context = context1;
        pkgMgr = context.getPackageManager();
    }
    public boolean isSystemApp(String packageName) {
        try {
            PackageInfo targetPkgInfo = pkgMgr.getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES);
            PackageInfo sys = pkgMgr.getPackageInfo(
                    "android", PackageManager.GET_SIGNATURES);
            return (targetPkgInfo != null && targetPkgInfo.signatures != null && sys.signatures[0]
                    .equals(targetPkgInfo.signatures[0]));
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    public String getAppName(String packagename){
        String appName = null;
        final String packageName = packagename;
        PackageManager packageManager= context.getPackageManager();
        try {
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }
    public Drawable getApplicationIconFromPackageName(String packagename){
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(packagename);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return icon;
    }
    public void getSlowingApps() {
        ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
        appsList = new ArrayList();
        icons = new ArrayList<>();
        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
                if (!rsi.service.getPackageName().equals("com.emre.androbooster")){
                    if (!rsi.service.getPackageName().equals("system")) {
                        if (!rsi.service.getPackageName().contains("cyanogenmod")) {
                            if (!rsi.service.getPackageName().equals("com.emre.notifier")) {
                            if (!rsi.service.getPackageName().equals("com.emre.ultrapowersave")) {
                                if (!rsi.service.getPackageName().equals("com.emre.androenergyv2")) {
                                if (!rsi.service.getPackageName().equals("com.android.inputmethod.latin")) {
                                if (!rsi.service.getPackageName().equals("eu.chainfire.supersu")) {
                                    if (!rsi.service.getPackageName().equals("com.google.android.gms")) {
                                        if (!rsi.service.getPackageName().equals("com.google.android.googlequicksearchbox")) {
                                            if (!rsi.service.getPackageName().equals("com.android.providers.media")) {
                                                appsList.add(getAppName(rsi.service.getPackageName()));
                                                getApplicationIconFromPackageName(rsi.service.getPackageName());
                                                icons.add(getApplicationIconFromPackageName(rsi.service.getPackageName()));
                                                Log.d("apps ",getAppName(rsi.service.getPackageName()));
                                            }
                                        }
                                    }
                                }
                                    }
                                }
                                }
                            }
                        }
                    }
                }

            }}
      /*  List<PackageInfo> packages = pkgMgr.getInstalledPackages(0);
        TerminalCommand.command("su");
        for (PackageInfo pkgInfo : packages) {
            if ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                if (!pkgInfo.applicationInfo.packageName.equals("com.emre.ultrapowersave")){
                    if (!pkgInfo.applicationInfo.packageName.equals(UltraPowerSave.defaultLauncher(context))) {
                        if (!pkgInfo.applicationInfo.packageName.equals("com.emre.androenergyv2")) {
                            if (!pkgInfo.applicationInfo.packageName.equals("eu.chainfire.supersu")) {
                                if (!pkgInfo.applicationInfo.packageName.equals(GetRecentApps.getRecentApps(context))) {
                                    Hibernater.hibernate(rsi.process);
                                }
                            }
                        }
                    }

                }
            }
        }
        */
    }

}

