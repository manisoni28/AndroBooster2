package com.emre.androbooster;
import com.emre.androbooster.boosterengine.*;
import java.io.*;

/**
 * Created by ogrenci on 5.05.2017.
 */

public class SetCPUFrequencyScaling {
    CPUCores cpuCores;
    //CPUFrequencyAlgorithm cpuFrequencyAlgorithm;

    public SetCPUFrequencyScaling() {
        //cpuFrequencyAlgorithm = new CPUFrequencyAlgorithm();
        cpuCores = new CPUCores();
    }

    public void setFrequencyScaling(final String maxFreq, final String minFreq) {
		
		final int a;
        for (int i = 0; i < cpuCores.getNumberOfCores(); i++) {
			a = i;
			
			new Thread(new Runnable() { 
					public void run(){  
					String bin = String.valueOf(a);
						String ab = "chmod 777 /sys/devices/system/cpu/cpu" + bin + "/cpufreq/scaling_max_freq";
						String ac = "chmod 777 /sys/devices/system/cpu/cpu" + bin + "/cpufreq/scaling_min_freq";
						//String ad = "chmod 777 /sys/devices/system/cpu/cpu" + bin + "/cpufreq/scaling_min_freq";
						String a1 = "chmod 777 /sys/devices/system/cpu/cpu" + bin + "/online";
						String ae = "echo " + maxFreq + " > /sys/devices/system/cpu/cpu" + bin + "/cpufreq/scaling_max_freq";
						String af = "echo " + minFreq + " > /sys/devices/system/cpu/cpu" + bin + "/cpufreq/scaling_min_freq";
						String[] abc = {ab,ac,ae,af,a1};
						try
						{
							TerminalCommand.RunAsRoot(abc);
						}
						catch (IOException e)
						{
							
						}

					}
				}).start();
			 }
    }
}
