package com.emre.androbooster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by emre on 24.12.2016.
 */

public class ChargeReciever extends BroadcastReceiver {
    FastCharge fastCharge;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        try {
            fastCharge = new FastCharge(context);
            if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
                if (!fastCharge.dontShow()) {
                    Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.slickways.quickcharge");
                    if (intent1 != null) {

                    } else {
                        createUFCNotification(context);
                    }
                    Log.d("abc", "b");
                }
            }
        }catch (Exception e){
            Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.slickways.quickcharge");
            if (intent1 != null) {

            } else {
                createUFCNotification(context);
            }
        }
    }
    private void createUFCNotification(Context context){
        Intent intent = new Intent(context, UFC.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification noti = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(context)
                    .setContentTitle(context.getString(R.string.UFC_TITLE))
                    .setContentText(context.getString(R.string.UFC)).setSmallIcon(R.drawable.ic_battery_charging_full)
                    .setContentIntent(pIntent)
                    .setOngoing(false).build();
        }
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(12, noti);
    }
}
