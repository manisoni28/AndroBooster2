package com.emre.androbooster.boosterengine;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.emre.androbooster.MainActivity;
import com.emre.androbooster.ModeScripts;
import com.emre.androbooster.NotificationActivity;
import com.emre.androbooster.R;
import com.emre.androbooster.TerminalCommand;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by emre on 23.04.2016.
 */
public class BoosterService extends Service{
    Context context = BoosterService.this;
    int mode = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            mode = intent.getIntExtra("mode",0);
        }
        notif();
        return START_NOT_STICKY;
    }
    public void createNotification(Context context, String text) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification noti = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(text).setSmallIcon(R.drawable.fab_icon)
                    .setContentIntent(pIntent)
                    .setOngoing(true)
                    .addAction(R.drawable.ic_content_clear, getString(R.string.dis), PendingIntent.getActivity(context,1,new Intent(context, NotificationActivity.class),PendingIntent.FLAG_CANCEL_CURRENT)).build();
        }
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(12, noti);
    }

    public void closeNotif() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


    public void perform() {
                if (mode==2) {
                    Log.d("Game","mode");
                        TerminalCommand.command("stop mpdecision");
                        TerminalCommand.command("echo 1 > /sys/devices/system/cpu/cpu1/online");
                        TerminalCommand.command("echo 1 > /sys/devices/system/cpu/cpu2/online");
                        TerminalCommand.command("echo 1 > /sys/devices/system/cpu/cpu3/online");
                    try {
                        TerminalCommand.RunAsRoot(ModeScripts.GAME_MODE_SCRIPT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (mode==1) {
                    try {
                        TerminalCommand.RunAsRoot(ModeScripts.HIGH_MODE_SCRIPT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
    }
    void notif(){
        Log.d("notify","");
        if (mode==2){
            createNotification(context, getString(R.string.ultra_text));
        }
        if (mode==1){
            createNotification(context, getString(R.string.high_text));
        }
    }
    public void stop() {
        TerminalCommand.command("start mpdecision");
        try {
            TerminalCommand.RunAsRoot(ModeScripts.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isApplied = false;
    Timer zamanlayıcı;
    KeyguardManager km;
    @Override
    public void onCreate(){
        super.onCreate();
        zamanlayıcı = new Timer();
        zamanlayıcı.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                km = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
              if (!isApplied){
                  if (!km.inKeyguardRestrictedInputMode()){
                      perform();
                      isApplied = true;
                  }else {

                  }
              }
                if (isApplied){
                    if (km.inKeyguardRestrictedInputMode()){
                        isApplied = false;
                        stop();
                    }else {

                    }
                }
            }
        },0,1000);


    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        zamanlayıcı.cancel();
        closeNotif();
        stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
