package com.emre.androbooster;

import android.content.*;
import java.io.*;

public class DefaultFreqs
{
	Context context;
	DefaultFreqs(Context context1){
		context = context1;
	}
	static String PATH_OF_MAX_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";
	static String PATH_OF_MIN_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq";
	static String MAX = "/data/data/com.emre.androbooster/files/max";
	static String MIN = "/data/data/com.emre.androbooster/files/min";

	public void saveDefaultMaxFreq(String freq) {
        try {
            FileOutputStream fOut = context.openFileOutput("max",  context.MODE_PRIVATE);
            fOut.write(freq.getBytes());
            fOut.flush();
            fOut.close();
        }
        catch (Exception e) {
            //Log.e("Hata kodu3", "File write failed: " + e.toString());
        }
    }
	
	public void saveDefaultMinFreq(String freq) {
        try {
            FileOutputStream fOut = context.openFileOutput("min",  context.MODE_PRIVATE);
            fOut.write(freq.getBytes());
            fOut.flush();
            fOut.close();
        }
        catch (Exception e) {
            //Log.e("Hata kodu3", "File write failed: " + e.toString());
        }
    }
	
	public String getDefaultMaxFreq() {
		File file = new File(MAX);
        StringBuilder freqTable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
		return freqTable.toString();
    }
	
	public String getDefaultMinFreq() {
		File file = new File(MIN);
        StringBuilder freqTable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
		return freqTable.toString();
    }
	
	public String defaultMaxFreq() {
		File file = new File(PATH_OF_MAX_FREQ);
        StringBuilder freqTable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
		return freqTable.toString();
    }

	public String defaultMinFreq() {
		File file = new File(PATH_OF_MIN_FREQ);
        StringBuilder freqTable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
		return freqTable.toString();
    }
}
