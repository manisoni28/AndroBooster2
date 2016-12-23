package com.emre.androbooster;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.stericson.RootTools.RootTools;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ogrenci on 23.12.2016.
 */

public class BoostingAppActivity extends Activity {

    private MaterialDialog m;
    private PackageManager packageManager;
    Context context;
    private GameBooster gameBooster;
    @Override
    protected void onCreate(Bundle n){
        super.onCreate(n);
        context = BoostingAppActivity.this;
        gameBooster = new GameBooster(this);
        Bundle b = this.getIntent().getExtras();
        String packageName = b.getString("app");
        packageManager = context.getPackageManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           m = new MaterialDialog.Builder(this)
                    .title(R.string.app_name)
                    .content(getAppName(packageName)+" " +getString(R.string.boost))
                    .progress(true,100)
                    .progressIndeterminateStyle(false)
                    .contentColor(getColor(R.color.black))
                    .titleColor(getColor(R.color.black))
                    .show();
        }else {
          m = new MaterialDialog.Builder(this)
                    .title(R.string.app_name)
                    .content(getAppName(packageName)+" " +getString(R.string.boost))
                    .progress(true,100)
                    .contentColor(getResources().getColor(R.color.black))
                    .titleColor(getResources().getColor(R.color.black))
                    .progressIndeterminateStyle(false)
                    .show();
        }

        final Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        try {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    RAMBooster.cleanAllActivities(context);
                                    RAMBooster.freeMemory(context);
                                    if (RootTools.isAccessGiven()){
                                        gameBooster.boostForGameAndApps();
                                    }
                                    if (!RootTools.isAccessGiven()){
                                        gameBooster.killApps();
                                    }
                                    startActivity(intent);
                                    m.dismiss();
                                    finish();
                                }
                            });
                        }
                    },
                    2200
            );


        } catch (ActivityNotFoundException e) {

        } catch (Exception e) {

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
}
