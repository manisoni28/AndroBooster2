package com.emre.androbooster;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StatFs;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.stericson.RootTools.RootTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by emre on 01.05.2016.
 */
public class ClearingCache extends Activity {

    private HashMap<String, Boolean> cacheCleaned;

    ArcProgress arcProgress;
    private int pStatus = 0;
    CleanCache cleanCache;
    private Method mGetPackageSizeInfoMethod, mFreeStorageAndNotifyMethod;
    ExternalCache externalCache;
    RootCacheCleaner rootCacheCleaner;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        setContentView(R.layout.clearing_cache);
        cacheCleaned = AppStorage.getCacheCleaned();
        rootCacheCleaner = new RootCacheCleaner(this);
        externalCache = new ExternalCache(this);
        try {
            mGetPackageSizeInfoMethod = getPackageManager().getClass().getMethod(
                    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);

            mFreeStorageAndNotifyMethod = getPackageManager().getClass().getMethod(
                    "freeStorageAndNotify", long.class, IPackageDataObserver.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        arcProgress = (ArcProgress) findViewById(R.id.clearing_progress);
        cleanCache = new CleanCache(this);
        final int ilk_doluluk = StorageInfo.megabytesAvailable();
        StatFs stat = new StatFs(Environment.getDataDirectory().getAbsolutePath());


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (RootTools.isAccessGiven()){
                    rootCacheCleaner.clearAllCache();
                }
                externalCache.clearAllCacheOnSDCard();
            }
        }).start();



        try {
            mFreeStorageAndNotifyMethod.invoke(getPackageManager(),
                    (long) stat.getBlockCount() * (long) stat.getBlockSize(),
                    new IPackageDataObserver.Stub() {
                        @Override
                        public void onRemoveCompleted(String packageName, boolean succeeded)
                                throws RemoteException {

                        }
                    });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            arcProgress.setProgress(pStatus);
                        }
                    });
                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cleanCache = new CleanCache(ClearingCache.this);
                        cleanCache.cleanAllAppsCache();
                        cleanCache.clearCache();
                        ClearingCache.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(ClearingCache.this,getString(R.string.ccleaned), Toast.LENGTH_LONG).show();
                                }
                        });
                        finish();
                    }
                },
                4000
        );

    }
   @Override
    public void onResume() {
        super.onResume();

    }

}
