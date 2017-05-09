package com.emre.androbooster;

import java.io.*;
import java.util.*;

/**
 * Created by ogrenci on 5.05.2017.
 */

public class CPUFreqTable {
    static String PATH_OF_FREQ_TABLE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";
	
	
    public String[] getFreqTableAsString() {
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
			
       return freqTable.toString().split(" ");
	 
    }
	public int[] getFreqTable(){
		int[] abcd = StringArrToIntArr(getFreqTableAsString());
		Arrays.sort(abcd);
		reverse(abcd);
		return abcd;
	}
	public int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = Integer.parseInt(s[i]);
		}
		return result;
	}
	public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
	}
	
    public int MIN_FREQ = (getFreqTable().length)-2;
    public int MAX_FREQ = 0;
	
	public String maxFrequency(){
		return String.valueOf(getFreqTable()[MAX_FREQ]);
	}
	
	public String minFrequency(){
		return String.valueOf(getFreqTable()[MIN_FREQ]);
	}
	
	public String maxFrequencyForHighBoosting(){
		return String.valueOf(getFreqTable()[MAX_FREQ+3]);
	}
}
