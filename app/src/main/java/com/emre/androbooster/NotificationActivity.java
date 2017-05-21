package com.emre.androbooster;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import com.emre.androbooster.boosterengine.BoosterService;
import java.io.IOException;

/**
 * Created by emre on 25.04.2016.
 */
public class NotificationActivity extends Activity{
  
	private CPUBoosting cpu;
	
    private void stop() {
        Intent intent = new Intent(this,BoosterService.class);
        stopService(intent);
        TerminalCommand.command("start mpdecision");
        try {
            TerminalCommand.RunAsRoot(ModeScripts.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cpu = new CPUBoosting(this);
		new Thread(new Runnable() { 
				public void run(){  
					stop();
				}
		}).start();
        finish();
    }
}
