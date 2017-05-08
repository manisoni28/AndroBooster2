package com.emre.androbooster;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.stericson.RootTools.RootTools;

/**
 * Created by emre on 01.05.2016.
 */
public class BoostingActivity extends Activity {

    ArcProgress arcProgress;
    HibernateUserApps hibernateUserApps;
    private int pStatus = 0;
    private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        setContentView(R.layout.boosting_activity);
        hibernateUserApps = new HibernateUserApps(this);
        arcProgress = (ArcProgress) findViewById(R.id.boosting_progress);
        final ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        final long percentAvail = mi.availMem;

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
                        RAMBooster.cleanAllActivities(BoostingActivity.this);
                        if (RootTools.isAccessGiven()){
                            hibernateUserApps.hibernateApps();
                        }else {
                            hibernateUserApps.killApps();
                        }
                        final ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        am.getMemoryInfo(memoryInfo);
                        final long availRam = memoryInfo.availMem;
                        final long cikar = availRam - percentAvail;
                        final long sonuc = cikar/1000000;

                        BoostingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (sonuc>1) {
                                    Toast.makeText(BoostingActivity.this, String.valueOf(sonuc) + " "+ getString(R.string.ram_CLEANED), Toast.LENGTH_LONG).show();
                                }else if (sonuc<1){
                                    Toast.makeText(BoostingActivity.this, getString(R.string.no_need_For_boost), Toast.LENGTH_LONG).show();
                                }
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
