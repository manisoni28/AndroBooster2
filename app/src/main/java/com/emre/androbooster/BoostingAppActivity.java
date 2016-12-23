package com.emre.androbooster;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stericson.RootTools.RootTools;

/**
 * Created by ogrenci on 23.12.2016.
 */

public class BoostingAppActivity extends AppCompatActivity {

    PackageManager packageManager;
    Context context;
    GameBooster gameBooster;
    @Override
    protected void onCreate(Bundle n){
        super.onCreate(n);
        context = BoostingAppActivity.this;
        gameBooster = new GameBooster(this);
        Bundle b = this.getIntent().getExtras();
        String packageName = b.getString("app");
        packageManager = context.getPackageManager();
        final MaterialDialog m = new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .content(getAppName(packageName)+" " +getString(R.string.boost))
                .progress(true,100)
                .progressIndeterminateStyle(false)
                .show();

        final Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        try {



            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
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
                    2100
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
