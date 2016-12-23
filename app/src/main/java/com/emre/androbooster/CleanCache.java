package com.emre.androbooster;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emre2 on 11.8.2016.
 */
public class CleanCache {
    private static final long CACHE_APP = Long.MAX_VALUE;
    private CachePackageDataObserver mClearCacheObserver;

    Context context1;

    CleanCache(Context context){
        context1 = context;

    }

    public void cleanAllAppsCache(){
        List<String> appps = new ArrayList<String>();
        PackageManager pm = context1.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        Log.d("updated"," code 4324");
        for (ApplicationInfo packageInfo : apps) {
           appps.add(packageInfo.packageName);
        }
        cleanAppCache(appps);
    }
    public void cleanAppCache(List<String> list) {
        PackageManager packageManager = context1.getPackageManager();
        for (String info : list) {
            String packageName = info;
            String strDeleteApplicationCacheFiles = "deleteApplicationCacheFiles";
            try {
                Method deleteApplicationCacheFiles = packageManager.getClass().getDeclaredMethod(strDeleteApplicationCacheFiles, String.class, IPackageDataObserver.class);
                Log.i("Z-AppCleanEngine", "cleanAppCache: packageName:" + packageName);
                deleteApplicationCacheFiles.invoke(packageManager, packageName, new IPackageDataObserver() {
                    @Override
                    public IBinder asBinder() {
                        return null;
                    }

                    @Override
                    public void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException {

                    }
                });
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public void clearCache()
    {
        if (mClearCacheObserver == null)
        {
            mClearCacheObserver=new CachePackageDataObserver();
        }

        PackageManager mPM= context1.getPackageManager();

        @SuppressWarnings("rawtypes")
        final Class[] classes= { Long.TYPE, IPackageDataObserver.class };

        Long localLong=Long.valueOf(CACHE_APP);

        try
        {
            Method localMethod=
                    mPM.getClass().getMethod("freeStorageAndNotify", classes);


            try
            {
                localMethod.invoke(mPM, localLong, mClearCacheObserver);
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
      /*
       * End of inner try-catch block
       */
        }
        catch (NoSuchMethodException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private class CachePackageDataObserver extends IPackageDataObserver.Stub
    {
        public void onRemoveCompleted(String packageName, boolean succeeded)
        {


        }
    }






}
