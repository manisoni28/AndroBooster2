package com.emre.androbooster;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.util.*;
import com.stericson.RootTools.*;
import java.util.*;

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
		ApplicationInfo app = null;
		try
		{
			app = pkgMgr.getApplicationInfo(packageName, 0);
		}
		catch (PackageManager.NameNotFoundException e)
		{

		}        
		return !app.sourceDir.startsWith("/data/app/");
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
            final ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
                if (!Arrays.asList(packages).contains(rsi.service.getPackageName())){
					new Thread(new Runnable() {
							@Override
							public void run() {
								RootTools.killProcess(rsi.process);
								Hibernater.ForceStopPackage(rsi.service.getPackageName());

							}
						}).start();
                                               
                    }
	    }
            }

        }

    
    public void killApps() {
        ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo
                    rsi = rs.get(i);
            if (!isSystemApp(rsi.service.getPackageName())){
				if (!Arrays.asList(packages).contains(rsi.service.getPackageName())){
                                                KillManager.killProcess(rsi.service.getPackageName(),context);
                                                RootTools.killProcess(rsi.process);
                                            //    Log.d("appler", rsi.service.getPackageName());
                                            }
                                        }
                                    }
                               }
    
}
