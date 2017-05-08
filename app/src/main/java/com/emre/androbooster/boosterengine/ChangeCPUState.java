package com.emre.androbooster.boosterengine;

import com.emre.androbooster.TerminalCommand;
import com.emre.androbooster.boosterengine.CPUCores;

public class ChangeCPUState
{
	public static void onlineAllCPUs(){
		
		CPUCores c = new CPUCores();
		
		for(int i=0; i<c.getNumberOfCores(); i++){
			
			String abc = String.valueOf(i);
			
			TerminalCommand.command("echo 1 > /sys/devices/system/cpu/cpu"+abc+"/online");
			
		}
	}
}
