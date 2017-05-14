package com.emre.androbooster;
import android.content.*;

public class SyncManagement
{
	public static void toggleSync(final boolean enable){
		new Thread(new Runnable() { 
				public void run(){  
					ContentResolver.setMasterSyncAutomatically(enable);
				}
		}).start();
	}
}
