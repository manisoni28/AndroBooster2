package com.emre.androbooster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.stericson.RootTools.RootTools;

import java.util.List;
import java.util.*;

/**
 * Created by Emre2 on 13.7.2016.
 */
public class GameBooster {
    Context context;
    private PackageManager pkgMgr = null;
    public GameBooster(Context context1){
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

	
    public void boostForGameAndApps() {
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
		TerminalCommand.command("exit");
     }
                               
    public void killApps() {
        ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
                if (!Arrays.asList(packages).contains(rsi.service.getPackageName())){
                            if (!rsi.service.getPackageName().equals(GetRecentApps.getRecentApps(context))) {
                          KillManager.killProcess(rsi.service.getPackageName(), context);
                                                RootTools.killProcess(rsi.process);
                                               // Log.d("appler", rsi.service.getPackageName());
                                            }
                                        }
                                    }
								}
                             }
}
