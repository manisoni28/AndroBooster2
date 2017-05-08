package com.emre.androbooster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.stericson.RootTools.RootTools;
import java.util.List;

/**
 * Created by Emre2 on 13.7.2016.
 */
public class HibernateUserApps {
    Context context;
    private PackageManager pkgMgr = null;
    public HibernateUserApps(Context context1){
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
    String[] packages = {"com.emre.androbooster",
			 "system",
			 "cyanogenmod",
			 "com.android.inputmethod.latin",
			 "eu.chainfire.supersu",
			 "com.google.android.gms",
			 "com.google.android.googlequicksearchbox",
			 "com.android.providers.media"};
			 
	
    public void hibernateApps() {
        ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
                if (!Arrays.asList(packages).contains(rsi.service.getPackageName())){
                   
                                                    Hibernater.ForceStopPackage(rsi.service.getPackageName());
                                                    RootTools.killProcess(rsi.process);
                                                    //Log.d("appler", rsi.service.getPackageName());
                                                
                            
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
    public void killApps() {
        ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
                if (!rsi.service.getPackageName().equals("com.emre.androbooster")){
                    if (!rsi.service.getPackageName().equals("system")) {
                        if (!rsi.service.getPackageName().contains("cyanogenmod")) {
                            if (!rsi.service.getPackageName().equals("com.android.inputmethod.latin")) {
                                if (!rsi.service.getPackageName().equals("eu.chainfire.supersu")) {
                                    if (!rsi.service.getPackageName().equals("com.google.android.gms")) {
                                        if (!rsi.service.getPackageName().equals("com.google.android.googlequicksearchbox")) {
                                            if (!rsi.service.getPackageName().equals("com.android.providers.media")) {
                                                KillManager.killProcess(rsi.service.getPackageName(),context);
                                                RootTools.killProcess(rsi.process);
                                                Log.d("appler", rsi.service.getPackageName());
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
}
