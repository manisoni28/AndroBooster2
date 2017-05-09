package com.emre.androbooster.boosterengine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.emre.androbooster.ApplicationAdapter;
import com.emre.androbooster.BoostingAppActivity;
import com.emre.androbooster.ModeManager;
import com.emre.androbooster.ModeScripts;
import com.emre.androbooster.R;
import com.emre.androbooster.TerminalCommand;
import java.io.IOException;
import com.emre.androbooster.*;

/**
 * Created by emre on 06.01.2017.
 */

public class CPUResting extends BroadcastReceiver {
    private ModeManager modeManager;
	private CPUBoosting cpu;
    public void stop() {
		cpu.setDefault();
        TerminalCommand.command("start mpdecision");
        try {
            TerminalCommand.RunAsRoot(ModeScripts.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void perform(int mode) {
        if (mode==2) {
            Log.d("Game","mode");
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
				cpu.setHighMode();
                TerminalCommand.RunAsRoot(ModeScripts.HIGH_MODE_SCRIPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        modeManager = new ModeManager(context);
		cpu = new CPUBoosting(context);
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            //Log.i("Check","Screen went OFF");
            new STOP().execute();
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            //Log.i("Check","Screen went ON");
            new DO().execute();
        }
    }
    private class DO extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            perform(modeManager.getMode());
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
}
