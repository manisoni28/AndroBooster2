package com.emre.androbooster.boosterengine;

import android.app.Notification;
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
import com.emre.androbooster.*;

/**
 * Created by emre on 23.04.2016.
 */
public class BoosterService extends Service{
    Context context = BoosterService.this;
    ModeManager modeManager;
	//boolean abcd = false;
	CPUCores cpuCores;
	private CPUBoosting cpu;
    int mode = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
    public void createNotification(Context context, String text) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
     //   NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification noti = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(text).setSmallIcon(R.drawable.fab_icon)
                    .setContentIntent(pIntent)
                    .setOngoing(true)
                    .addAction(R.drawable.ic_content_clear, getString(R.string.dis), PendingIntent.getActivity(context,1,new Intent(context, NotificationActivity.class),PendingIntent.FLAG_CANCEL_CURRENT)).build();
        }
        noti.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        startForeground(13, noti);
    }
    
    public void perform() {
                if (mode==2) {
                 //   Log.d("Game","mode");
                        TerminalCommand.command("stop mpdecision");
                        ChangeCPUState.onlineAllCPUs(); 
						cpu.setUltraGamingMode();
						/*if(abcd){
							SyncManagement.toggleSync(false);
						}*/
						
                    try {
                        TerminalCommand.RunAsRoot(ModeScripts.GAME_MODE_SCRIPT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (mode==1) {
					/*if(abcd){
					 SyncManagement.toggleSync(false);
					 }*/
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
		/*if(abcd){
		 SyncManagement.toggleSync(false);
		 }*/
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
		cpuCores = new CPUCores();
        mode = modeManager.getMode();
		cpu = new CPUBoosting(this);
		//abcd = cpuCores.getNumberOfCores()<3;
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
        unregisterCPUResting();
        new STOP().execute();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
