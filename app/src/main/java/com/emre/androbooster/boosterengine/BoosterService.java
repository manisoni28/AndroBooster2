package com.emre.androbooster.boosterengine;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.emre.androbooster.MainActivity;
import com.emre.androbooster.ModeManager;
import com.emre.androbooster.ModeScripts;
import com.emre.androbooster.NotificationActivity;
import com.emre.androbooster.R;
import com.emre.androbooster.TerminalCommand;
import com.emre.androbooster.CPUBoosting;
import java.io.IOException;

/**
 * Created by emre on 23.04.2016.
 */
public class BoosterService extends Service{
    Context context = BoosterService.this;
    ModeManager modeManager;
	private CPUBoosting cpu;
    int mode = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
                 //   Log.d("Game","mode");
                        TerminalCommand.command("stop mpdecision");
                        ChangeCPUState.onlineAllCPUs(); 
						cpu.setUltraGamingMode();
                    try {
                        TerminalCommand.RunAsRoot(ModeScripts.GAME_MODE_SCRIPT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (mode==1) {
                    try {
                        TerminalCommand.RunAsRoot(ModeScripts.HIGH_MODE_SCRIPT);
						cpu.setHighMode();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        		if (mode==0) {
           				stop();
        		}
    }
    void notif(){
        //Log.d("notify","");
        if (mode==2){
            createNotification(context, getString(R.string.ultra_text));
        }
        if (mode==1){
            createNotification(context, getString(R.string.high_text));
        }
    }
    public void stop() {
        modeManager.saveMode(0);
		cpu.setDefault();
        try {
            TerminalCommand.RunAsRoot(ModeScripts.DEFAULT);
			TerminalCommand.command("start mpdecision");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    IntentFilter screenStateFilter = new IntentFilter();
    CPUResting cpuResting = new CPUResting();
    @Override
    public void onCreate(){
        super.onCreate();
        modeManager = new ModeManager(this);
        mode = modeManager.getMode();
		cpu = new CPUBoosting(this);
        if (mode>0){
            notif();
        }
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerCPUResting();
        new DO().execute();
    }
    private class DO extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            perform();
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private class STOP extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            stop();
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private void registerCPUResting(){
        registerReceiver(cpuResting, screenStateFilter);
    }
    private void unregisterCPUResting(){
        unregisterReceiver(cpuResting);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        closeNotif();
        unregisterCPUResting();
        new STOP().execute();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
