package com.emre.androbooster;

import java.io.*;

/**
 * Created by ogrenci on 5.05.2017.
 */

public class CPUFreqTable {
    static String PATH_OF_FREQ_TABLE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";

    public String[] getFreqTable() {
       File file = new File(PATH_OF_FREQ_TABLE);
        StringBuilder freqTable = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
              //freqTable.append("\n");
            }

            br.close();
        } catch (Exception e) {

        }
		
		//BufferedReader  buffered_reader=null;
	
       return freqTable.toString().split(" ");
	 //  return abcde.split(" ");
    }
	
    public int MIN_FREQ = (getFreqTable().length)-2;
    public int MAX_FREQ = 0;
	
	public String maxFrequency(){
		return getFreqTable()[MAX_FREQ];
	}
	public String minFrequency(){
		return getFreqTable()[MIN_FREQ];
	}
	public String maxFrequencyForHighBoosting(){
		return getFreqTable()[MAX_FREQ+3];
	}
}
